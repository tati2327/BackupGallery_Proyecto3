package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.structures.SimpleList;

public class Server {
	
	static final int PUERTO = 54000; //Numero de puerto
	ManageConections myConections = new ManageConections();
	
	public void multiclient() {
		Socket client = null; //Socket de un nuevo cliente
		
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(PUERTO); //Se crea un nuevo objeto socket de tipo servidor
			System.out.println("Esperando peticiones por el puerto: "+PUERTO);
			
			//ciclo donde el servidor comienza a escuchar peticiones 
			while(true) {
				//El servidor escuha nuevas conexiones
				System.out.println("SERVER: Escuchando nuevas conexiones");
				client = server.accept();
				
				//Cuando se conecta un cliente crea los objetos para enviar y recibir mensajes
				System.out.println("SERVER: Cliente conectado de forma exitosa");
				
				DataInputStream in_temp = new DataInputStream(client.getInputStream());
				DataOutputStream out_temp = new DataOutputStream(client.getOutputStream());
				
				//Se le agrega al cliente un administrador 
				ManageClient newClient = new ManageClient(client, in_temp, out_temp);
				newClient.start();
				myConections.myClients.add(newClient);
				
				//Solo para ver los clientes conectados
				/*System.out.println("Clientes conectados: ");
				for(int i=0; i < myConections.myClients.size(); i++) {
					System.out.println(myConections.myClients.get(i).name);
				}*/
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		Server myServer = new Server();
		myServer.multiclient();
		
		/*ServerJson se= new ServerJson();
		String m = se.serializeName("luis");
		String mi = se.serializeConfirmation("tryue");
		String yu = se.getName(m);
		System.out.println(mi);*/
		
	}
}