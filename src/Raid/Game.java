package Raid;

import java.io.IOException;

public class Game {
	Controller myController;
	/**
	 * saveImage
	 * Guarda la imagen que envia el cliente
	 * @param incoming
	 * @param id
	 * @throws IOException
	 */
	public void saveImage(byte[] incoming, int id) throws IOException {
		Images img=myController.saveIncomingImage(incoming,id);	
		myController.loadImage(img);
		myController.divideImage(null, img);	
	}
	
	/**
	 * getImage
	 * retorna la imagen en forma de array de bytes para enviarsela al cliente
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public byte[] getImage(int id) throws IOException {
		byte[] finalArray=myController.recoverImage(id);
		return finalArray;
	}
	
	/**
	 * delete
	 * Se encarga de eliminar la imagen que el cliente solicita borrar
	 * @param id
	 */
	public void delete(int id) {
		myController.delete(id);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
