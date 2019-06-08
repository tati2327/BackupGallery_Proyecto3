package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		final String HOST = "127.0.0.1";
		final int PUERTO = 5000;
		DataInputStream in;
		DataOutputStream out;
		
		try {
			Socket socket = new Socket(HOST, PUERTO);
			
			in = new DataInputStream(socket.getInputStream()); //Recibir mensaje
			out = new DataOutputStream(socket.getOutputStream()); //Enviar mensaje
			
			out.writeUTF("Hola Server");
			
			String message = in.readUTF();
			System.out.println(message);
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
