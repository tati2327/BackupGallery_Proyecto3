package com.serverManage;

import com.server.ManageConections;
import com.server.ServerJson;

public class raidManager {
	
	ServerJson myJson = new ServerJson();
	ManageConections myManageConections = new ManageConections();
	
	public void readMessage(String message) {
		int request = myJson.getRequest(message);
		
		switch (request) {
		case 6: //SEND IMAGE
			myManageConections.sendMessage("client", message);
			break;
		case 7: //CONFIRMATION
			if(!myManageConections.isDoubleConfirmation()) {
				myManageConections.sendMessage("client", message);
			}
			break;
		default:
			break;
		}		
	}
}

