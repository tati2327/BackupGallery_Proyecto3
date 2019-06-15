package com.serverManage;

import com.server.ManageConections;
import com.server.ServerJson;

public class raidManager {
	
	ServerJson myJson = new ServerJson();
	ManageConections myManageConections = new ManageConections();
	
	public void readMessage(String message) {
		int request = myJson.getRequest(message);
		
		switch (request) {
		case 6: //send image
			myManageConections.sendMessage("client", message);
			break;
		
		default:
			break;
		}		
	}
}

