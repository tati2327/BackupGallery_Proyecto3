package Sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException {

        try {  
            StringBuffer inputLine = new StringBuffer();
            ServerSocket serverSocket = new ServerSocket(4444);

            while(true){

                System.out.println("Waiting to receive ---->> ");
                Socket clientSocket = serverSocket.accept();
                System.out.println("After receive ---->> ");
                DataInputStream dis=new DataInputStream(clientSocket.getInputStream());                     
                String tmp; 
                while ((tmp = dis.readLine()) != null) {
                    inputLine.append(tmp);
                    System.out.println(tmp);
                }
                //use inputLine.toString(); here it would have whole source
                dis.close();            
                clientSocket.close();  
          }

        }
        catch(Exception e)
        {
          // e.printStackTrace();
        }
	}
}
