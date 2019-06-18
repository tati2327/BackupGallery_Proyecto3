package Raid;

import java.io.IOException;

public class RaidManage {
	Controller myController;
	
	public void saveImage(byte[] incoming, int id) throws IOException {
		Images img=myController.saveIncomingImage(incoming,id);	
		myController.loadImage(img);
		myController.divideImage(null, img);	
	}
	
	public byte[] getImage(int id) throws IOException {
		byte[] finalArray=myController.recoverImage(id);
		return finalArray;
	}
	
	public void delete(int id) {
		myController.delete(id);
	}
}
