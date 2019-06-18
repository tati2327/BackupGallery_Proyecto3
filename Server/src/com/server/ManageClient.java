package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.serverManage.clientManager;
import com.serverManage.dataBaseManager;
import com.serverManage.raidManager;

public class ManageClient extends Thread {

	Socket client = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	
	String name = "";
	String message = "";
	ServerJson myJson = new ServerJson();
	int request = 0;

	boolean init = true;
	clientManager myClientManager = new clientManager();
	dataBaseManager myDataBaseManager = new dataBaseManager();
	raidManager myRaidManager = new raidManager();

	public  ManageClient(Socket myClient, DataInputStream in, DataOutputStream out) {
		this.client = myClient;
		this.in = in;
		this.out = out;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {

		System.out.println("CLIENTE: Nuevo cliente conectado");
		while(true) {
			try {		
				message = in.readUTF();
				System.out.println("CLIENT: "+message);
				request = myJson.getRequest(message);

				//Configurar el nombre
				if(request==1) {
					this.name = myJson.getName(message);
					out.writeUTF(myJson.serializeConfirmation(true));
					System.out.println("CLIENTE: Soy el cliente "+name);
				}

				//----------Segun el tipo de solicitud el mensaje se manda donde un manager--------------/
				
				//Manage del cliente
				if(request >=2  & request<=7 ) {
					myClientManager.readMessage(message);
				}

				//Manage para el Raid
				if(request == 8 || request == 9) {
					myRaidManager.readMessage(message);
				}
				
				//Manage de la base de datos
				if(request >=10  || request<=13) {
					myDataBaseManager.readMessage(message);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
