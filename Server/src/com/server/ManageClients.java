package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.serverManage.clientManager;
import com.serverManage.dataBaseManager;
import com.serverManage.raidManager;

public class ManageClients extends Thread {
	
	Socket client = null;
	String name = "";

	DataInputStream in = null;
	DataOutputStream out = null;
	ServerJson myJson = new ServerJson();
	
	String toSend = "";
	
	clientManager myClientManager = new clientManager();
	dataBaseManager myDataBaseManager = new dataBaseManager();
	raidManager myRaidManager = new raidManager();
	
	public  ManageClients(Socket myClient, DataInputStream in, DataOutputStream out) {
		this.client = myClient;
		this.in = in;
		this.out = out;
	}
	
	public void run() {
		
		System.out.println("Nuevo cliente conectado");
		
		try {
			String message = in.readUTF();
			System.out.println(message);
			
			//Configurar el nombre
			if(myJson.getRequest(message)==1) {
				this.name = myJson.getName(message);
				System.out.println("Soy el cliente "+name);
			}
			
			/*----------Segun el nombre el mensaje se manda donde un manager--------------*/
			
			if(name == "client") {
				//Manage del cliente
				myClientManager.readMessage(message);
			}
			if(name == "raid5") {
				//Manage para el Raid
				myRaidManager.readMessage(message);
			}
			if(name == "dataBase") {
				//Manage de la base de datos
				myDataBaseManager.readMessage(message);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
