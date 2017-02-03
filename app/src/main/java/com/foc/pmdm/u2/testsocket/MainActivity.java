package com.foc.pmdm.u2.testsocket;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Curiosidad que no se debe de usar mucho.
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // llamamos al metodo HayConexion
        // Si hay activamos los botones par ahacer conextion


    }

    public boolean HayConexion()
    {
        boolean estadoCoenxion=false;

        ConnectivityManager cm= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=null;
        try {
            ni = cm.getActiveNetworkInfo();
            if(ni!=null && ni.isConnected()){
                estadoCoenxion=true; // Hay conexion

                if(ni.isRoaming()){
                    Toast.makeText(this,"Cuidado estas en roaming",Toast.LENGTH_LONG).show();
                }
                // Vemos todas las redes
                Network[] redes=cm.getAllNetworks();
                for(Network red:redes)
                {
                    NetworkInfo ni2=cm.getNetworkInfo(red);
                }
            }
            else
            {
                estadoCoenxion=false;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }



        return estadoCoenxion;
    }

    // Método que hace una petición via Socket
    public void getViaSocket(View v)
    {
        try {
            Socket s = new Socket("www.google.es", 80);
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String sLinea=input.readLine();

            // TODO: leer todo el contenido

            s.close();
        }
        catch(Exception e){

            e.printStackTrace();
        }
    }

    // Método que hace petición HTTP

    public void getViaHttp(View v){
        try {


            URL url = new URL("http://www.google.com");
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line=null;
            String strContenido="";
            while((line=reader.readLine())!=null)   {
                strContenido+=line;
            }
        }
        catch (Exception e)  {
           e.printStackTrace();
        }


    }

}
