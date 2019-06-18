package com.raid;

import java.io.IOException;

public class Game {
	Controller myController;
	
	public void saveImage(byte[] incoming, int id) throws IOException {
		Images img=myController.saveIncomingImage(incoming,id);	
		myController.loadImage(img);
		myController.divideImage(null, img);	
	}
	
	/*public byte[] getImage(int id) {
		
	}*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
