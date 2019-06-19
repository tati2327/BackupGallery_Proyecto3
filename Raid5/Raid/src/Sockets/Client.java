package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	static final String HOST = "127.0.0.1";
	static final int PUERTO = 54000;
	Socket mySocket;
	ManageMessages myManage;
	ClientJson myJson =  new ClientJson();
	
	DataInputStream in;
	DataOutputStream out;
	Boolean init = true;
	
	String message, toSend;
	
	public void newClient() throws InterruptedException {
		try {
			Socket socket = new Socket(HOST, PUERTO);
			
			in = new DataInputStream(socket.getInputStream()); //Recibir mensaje
			out = new DataOutputStream(socket.getOutputStream()); //Enviar mensaje
			
			while(true) {
				if(init) {
					Thread.sleep (1000);
					toSend = myJson.serializeName("raid");
					out.writeUTF(toSend);
					System.out.println("RAID: "+toSend);
					init = false;
				}
				
				message = in.readUTF();
				System.out.println("SERVER: "+message);
				Thread.sleep(1000);
				toSend = myManage.readMessage(message);
				System.out.println("RAID: "+message);
				out.writeUTF(toSend);
			}	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * La aplicación se corre desde aquí!!!!!!
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Client c = new Client();
		c.newClient();
	}
}
