package com.example.boxyz;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient extends AsyncTask<String, Void, String> {

    public String reponse;

    @Override
    protected String doInBackground(String... message) {
        try {

            InetSocketAddress inetAddress = new InetSocketAddress("192.168.1.26", 25565);
            Socket s = new Socket();
            s.connect(inetAddress, 1000);
            s.setSoTimeout(5000);

            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            pw.write(message[0]);
            Log.d("SOCKET_CLIENT", "Message: " + message[0]);
            pw.flush();

            //Message retour
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String retour = br.readLine();
            Log.d("SOCKET_CLIENT", "Retour: " + retour);

            pw.close();
            s.close();

            return retour;
        } catch (Exception e) {
            System.out.println("Fail");
            e.printStackTrace();
            Log.e("SOCKET_CLIENT", "Exception: " + e.getMessage());
            return e.getMessage();
        }
    }
}
