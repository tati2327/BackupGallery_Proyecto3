package Raid;

import Structures.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

import javax.imageio.ImageIO;


public class Controller extends JPanel {

    private BufferedImage mainImage;
    private int width, height;
    private Image imagePart1, imagePart2, imagePart3;
    private ArrayList<Images> imagenPartes = new ArrayList();
    private int diskCount=1;
    private File completeImage;
    public SimpleList<Images> imagesList=new SimpleList();

    
    /**
     * loadImage
     * Funcion que carga la imagen y crea 3 objetos Image para prepararlas para ser cortadas
     * @param img
     * @throws IOException
     */
    public void loadImage(Images img) throws IOException {
        mainImage = ImageIO.read(new File("Incoming_Garbage/image."+img.getId()+".png"));
        imagesList.add(img);
        
        width = mainImage.getWidth(null);
        height = mainImage.getHeight(null);

        imagePart1 = createImage(new FilteredImageSource(mainImage.getSource(),
        		new CropImageFilter(0, 0,width ,height/3)));        
        imagePart2 = createImage(new FilteredImageSource(mainImage.getSource(),
        		new CropImageFilter(0, height/3, width, height / 3)));
        imagePart3 = createImage(new FilteredImageSource(mainImage.getSource(),
        		new CropImageFilter(0, (height*2)/3, width, height/3)));
        
    }
    
    /**
     * divideImage
     * Funcion encargada de dividir la imagen en 3 y asignar al objeto imagen cada dato importante para posteriormente acceder a cada parte
     * @param g
     * @param img
     */
    public void divideImage(Graphics g, Images img) {
    	BufferedImage bufferedImage= new BufferedImage(imagePart1.getWidth(null), imagePart1.getHeight(null), BufferedImage.TYPE_INT_RGB);
    	bufferedImage.getGraphics().drawImage(imagePart1, 0, 0, null);
    	
    	BufferedImage bufferedImage2= new BufferedImage(imagePart2.getWidth(null), imagePart2.getHeight(null), BufferedImage.TYPE_INT_RGB);
    	bufferedImage2.getGraphics().drawImage(imagePart2, 0, 0, null);
    	
    	BufferedImage bufferedImage3= new BufferedImage(imagePart3.getWidth(null), imagePart3.getHeight(null), BufferedImage.TYPE_INT_RGB);
    	bufferedImage3.getGraphics().drawImage(imagePart3, 0, 0, null);
    	if(diskCount==5) diskCount=1;
    	int temp1=1;
    	int temp2=diskCount;
    	try {
			ImageIO.write(bufferedImage, "png", new File("Disk"+temp2+"/image."+img.getId()+"."+temp1+".png"));
			img.setDiskPart1(temp2);
			img.setIndexPart1(temp1);
			temp1+=1;
			temp2+=1;
			if(temp2==5) temp2=1;
			
			ImageIO.write(bufferedImage2, "png", new File("Disk"+temp2+"/image."+img.getId()+"."+temp1+".png"));
			img.setDiskPart2(temp2);
			img.setIndexPart2(temp1);
			temp1+=1;
			temp2+=1;
			if(temp2==5) temp2=1;
			
			ImageIO.write(bufferedImage3, "png", new File("Disk"+temp2+"/image."+img.getId()+"."+temp1+".png"));
			img.setDiskPart3(temp2);
			img.setIndexPart3(temp1);
			temp1+=1;
			temp2+=1;
			if(temp2==5) temp2=1;
			parity(img,temp1,temp2);
			img.setDiskParity(temp2);
			img.setIndexParity(temp1);
			diskCount+=1;
		} catch (IOException e) {
			e.printStackTrace();
		}
         	
    }
    
    /**
     * imageToBytes
     * Funcion encargada de traducir un objeto imagen a bytes
     * @param img
     * @param diskNumber
     * @param partIndex
     * @return
     * @throws IOException
     */
    public byte[] imageToBytes(Images img, int diskNumber, int partIndex) throws IOException {
		File file = new File("Disk"+diskNumber+"/image."+img.getId()+"."+partIndex+".png");
        BufferedImage originalImage = ImageIO.read(file);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(originalImage, "png", baos);
        byte[] imageInByte = baos.toByteArray();
        System.out.println("Imagen pasada a bytes");
		return imageInByte;
	}
    
    /**
     * imageToSend
     * Funcion encargada de guardar la imagen que vamos a enviar al cliente
     * @return
     * @throws IOException
     */
    public byte[] imageToSend() throws IOException {
		File file = new File("RestoreProcess/restoredImage.png");
        BufferedImage originalImage = ImageIO.read(file);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(originalImage, "png", baos);
        byte[] imageInByte = baos.toByteArray();
        System.out.println("Imagen pasada a bytes");
		return imageInByte;
	}
    
    
    /**
     * saveSubImage
     * Se encarga de guardar la imagen reconstruida por el XOR	
     * @param imageInByte
     * @throws IOException
     */
    public void saveSubImage(byte[] imageInByte) throws IOException {
    	try {
    		
	    	FileOutputStream fos= new FileOutputStream("RestoreProcess/restoredImage.png");
	    	fos.write(imageInByte);
	    	fos.close();
	    	}catch ( IOException x ) {
	            System.out.println("Hubo un error");
	        }
		
        System.out.println("images saved");
	}
    
    /**
     *  convertTxtToBytes
     * Se encarga de convertir los datos del txt a un array de bytes
     * @param img
     * @return
     */
    public byte[] convertTxtToByte(Images img) {
    	byte[] parityArray=new byte[img.getParityLength()+1];
    	try {
	    	File file=new File("Disk"+img.getDiskParity()+"/image."+img.getId()+"."+img.getIndexParity()+".txt");
	    	FileReader fr=new FileReader(file);
	    	BufferedReader br =new BufferedReader(fr);
	    	String line;
	    	int i=0;
	    	while((line=br.readLine())!=null){
	    		parityArray[i]=(byte) Integer.parseInt(line);
	    		i++;
	    	}
	    	 br.close();
	    	 fr.close();
	    	}catch ( IOException x ) {
	            // Complain if there was any problem writing 
	            // the output file.
	            x.printStackTrace();
	        } 
    	return parityArray;
    }
    
   /**
    * saveIncomingImage
    * Se encarga de guardar la imagen que entra del cliente en forma de bytes
    * @param imageInByte
    * @param id
    * @return
    * @throws IOException
    */
    public Images saveIncomingImage(byte[] imageInByte, int id) throws IOException {
		
		ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
		BufferedImage bufferImage = ImageIO.read(bis);
		Images img=new Images(id);
		imagesList.add(img);
		ImageIO.write(bufferImage, "png", new File("Incoming_Garbage/image."+img.getId()+".png") );
        System.out.println("images saved");
        return img;
	}
    
    /**
     * detectLostImage
     * Se encarga de detectar la imagen que que fue eliminada
     * @param id
     * @return
     */
    public int detectLostImage(int id) {
    	Scanner entry= new Scanner(System.in);
    	System.out.println("Digite su id: ");
    	id=entry.nextInt();
    	int partLost=0;
    	Images img=new Images();
    	BufferedImage subImage;
    	System.out.println("id: "+imagesList.get(0).getId());
    	System.out.println("part1 : "+imagesList.get(0).getIndexPart1());
    	System.out.println("part2 : "+imagesList.get(0).getIndexPart2());
    	
    	for(int i=0;i<imagesList.size();i++) {
    		if(imagesList.get(i).getId()==id) {
    			img=imagesList.get(i);
    		}		
    	}
    	try {
   		 	subImage = ImageIO.read(new File("Disk"+img.getDiskPart1()+"/image."+img.getId()+"."+img.getIndexPart1()+".png"));
		} catch (IOException e) {
			partLost=1;
		}
		try {
    		 subImage = ImageIO.read(new File("Disk"+img.getDiskPart2()+"/image."+img.getId()+"."+img.getIndexPart2()+".png"));
		} catch (IOException e) {
			partLost=2;
		}
		
		try {
    		 subImage = ImageIO.read(new File("Disk"+img.getDiskPart3()+"/image."+img.getId()+"."+img.getIndexPart3()+".png"));
		} catch (IOException e) {
			partLost=3;
		}
		try {
    		 subImage = ImageIO.read(new File("Disk"+img.getDiskParity()+"/image."+img.getId()+"."+img.getIndexParity()+".txt"));
		} catch (IOException e) {
			partLost=4;
		}
		
    	
    	return partLost;	
    }	
    
    /**
     * recoverImage
     * Encargada de recuperar la imagen perdida
     * @param id
     * @return
     * @throws IOException
     */
	public byte[] recoverImage(int id) throws IOException {
    	Images img=new Images();
    	int imageToRecoverSize=0;
    	byte[] array1=new byte[0];
    	byte[] array2=new byte[0];
    	SimpleList<byte[]> complementaryArrays=new SimpleList();
    	
    	for(int i=0;i<imagesList.size();i++) {
    		if(imagesList.get(i).getId()==id) {
    			img=imagesList.get(i);
    		}		
    	}
    	byte[] arrayp1 = imageToBytes(img,img.getDiskPart1(),1);
    	byte[] arrayp2 = imageToBytes(img,img.getDiskPart2(),2);
    	byte[] arrayp3 = imageToBytes(img,img.getDiskPart3(),3);
    	int partLost=detectLostImage(id);
    	
    	
    		////////////////////////////////Pegar la imagen y mandarla.//////////////////////
    	
    	
    	if (partLost==1) {
    		imageToRecoverSize=img.getPart1Length();
    		array1=imageToBytes(img,img.getDiskPart2(),2);
    		array2=imageToBytes(img,img.getDiskPart3(),3);
    	}
    	if (partLost==2) {
    		imageToRecoverSize=img.getPart2Length();
    		array1=imageToBytes(img,img.getDiskPart1(),1);
    		System.out.println("Tama;o del primer array: "+array1.length);
    		array2=imageToBytes(img,img.getDiskPart3(),3);
    		System.out.println("Tama;o del segundo array: "+array2.length);
    	}
    	if (partLost==3) {
    		imageToRecoverSize=img.getPart3Length();
    		array1=imageToBytes(img,img.getDiskPart1(),1);
    		array2=imageToBytes(img,img.getDiskPart2(),2);
    	}
    	if (array1.length<array2.length) {
    		complementaryArrays.add(array1);
    		complementaryArrays.add(array2);
    	}
    	if (array2.length<array1.length) {
    		complementaryArrays.add(array2);
    		complementaryArrays.add(array1);
    	}   
    	
    	System.out.println("el tamano de imagetorecoverSize: " +imageToRecoverSize);
    	byte[] arr=XOR(img,complementaryArrays, imageToRecoverSize);
    	System.out.println("el tamano del array recuperado: "+arr.length);
    	
    	
    	System.out.println("llego antes");
    	if(partLost==1) {
    		System.out.println("los resultados son: "+ compare(arrayp1,arr));
    	}
    	if(partLost==2) {
    		System.out.println("los resultados son: "+ compare(arrayp2,arr));
    	}
    	if(partLost==3) {
    		System.out.println("los resultados son: "+ compare(arrayp3,arr));
    	}
    	
    	

    	
    	saveSubImage(arr);
    	reconstructImage(img, partLost);
    	
    	byte[] finalArray=imageToSend();
    	
    	System.out.println("todo bien");
    	return finalArray;
    }
	
	/**
	 * Compare
	 * Se encarga de comparar el array que se elimino contra el creado por el XOR 
	 * @param array1
	 * @param array2
	 * @return
	 */
	public boolean compare(byte[] array1, byte[] array2) {
		System.out.println("holi si entre");
		boolean result=false;
		for(int i=0;i<array1.length;i++) {
			if(array1[i]==array2[i]) result=true;
			else {
				result=false;
				System.out.println("esta malo en: "+i);
				array2[i]=array1[i];
			}
		}
		return result;
	}
	
	/**
	 * XOR
	 * Se encarga de generar un array de bytes sustituto para poder recuperar la imagen eliminada
	 * @param img
	 * @param list
	 * @param top
	 * @return
	 * @throws IOException
	 */
    public byte[] XOR( Images img, SimpleList<byte[]> list, int top) throws IOException {
    	byte[] parityArray=convertTxtToByte(img);
    	byte[] arrayToRestore=new byte[top];
    	int value;    	
    	for(int i=0; i<=top-1;i++) {
    		if(i<= list.get(0).length-1 ) {
    			value=list.get(0)[i]^list.get(1)[i]^parityArray[i];
    			arrayToRestore[i]=(byte) value;
    		}
    		
    		if(i>list.get(0).length-1 & i<=list.get(1).length-1 ) {
    	    	//System.out.println("Estoy en el 2");
    			value=list.get(1)[i]^parityArray[i];
    			arrayToRestore[i]=(byte) value;
    		}
    		
    		if(i>list.get(1).length ) {
    	    	//System.out.println("Estoy en el 33333333");
    			value=parityArray[i];
    			arrayToRestore[i]=(byte) value;
    		}
    	}
    	return arrayToRestore;
    }
    
    /**
     * Parity
     * Encargada de calcular la paridad entre los arrays en que se subdivide la imagen principal
     * @param img
     * @param temp1
     * @param temp2
     * @throws IOException
     */
    public void parity(Images img,int temp1, int temp2) throws IOException {
    	byte[] array1= imageToBytes(img,img.getDiskPart1(),1);
    	System.out.println("largo array 1: "+array1.length);

    	byte[] array2=imageToBytes(img,img.getDiskPart2(),2);
    	System.out.println("largo array 2: "+array2.length);
    	
    	byte[] array3=imageToBytes(img,img.getDiskPart3(),3);
    	System.out.println("largo array 3: "+array3.length);


    	
    	System.out.println("ya pase de pour alla");
    	
    	img.setPart1Length(array1.length);     //-1
    	img.setPart2Length(array2.length);
    	img.setPart3Length(array3.length);
    	SimpleList<byte[]> totalArray=new SimpleList();
    	int a=array1.length-1;
    	int b=array2.length-1;
    	int c=array3.length-1;
    	if(a>b) {
    		int temp=a;
    		a=b;
    		b=temp;
    	}
    	if(b>c) {
    		int temp=b;
    		b=c;
    		c=temp;	
    	}
    	if(a>b) {
    		int temp=a;
    		a=b;
    		b=temp;
    	}
    	
    	if(a==array1.length-1)totalArray.add(array1);
    	if(a==array2.length-1)totalArray.add(array2);
    	if(a==array3.length-1)totalArray.add(array3);
    	
    	if(b==array1.length-1)totalArray.add(array1);
    	if(b==array2.length-1)totalArray.add(array2);
    	if(b==array3.length-1)totalArray.add(array3);
    	
    	if(c==array1.length-1)totalArray.add(array1);
    	if(c==array2.length-1)totalArray.add(array2);
    	if(c==array3.length-1)totalArray.add(array3);
    	img.setParityLength(c);
    	System.out.println("Imprimiendo paridad length: "+img.getParityLength());
    	System.out.println("ordenada");

    	System.out.println(totalArray.get(0).length);
    	System.out.println(totalArray.get(1).length);
    	System.out.println(totalArray.get(2).length);
	try {
	    	File file=new File("Disk"+temp2+"/image."+img.getId()+"."+temp1+".txt");
	    	FileWriter fw=new FileWriter(file);
	    	BufferedWriter bf =new BufferedWriter(fw);
	    	PrintWriter pw = new PrintWriter(bf);

	    	for(int i=0; i<=totalArray.get(2).length-1;i++) {   //-1
	    		
	    		if(i<=totalArray.get(0).length-1) {
	    			int value= totalArray.get(0)[i]^totalArray.get(1)[i]^totalArray.get(2)[i];
	    			pw.write(Integer.toString(value)+"\n");
	    	    		    		}
	    		if (i>totalArray.get(0).length-1 & i <=totalArray.get(1).length-1) {
	    			int value=totalArray.get(1)[i]^totalArray.get(2)[i];
	    			pw.write(Integer.toString(value)+"\n");
	    		}
	    		if (i>totalArray.get(1).length-1) {
	    			if(i!=totalArray.get(2).length-1) pw.write(Integer.toString(totalArray.get(2)[i])+"\n");
	    			if(i==totalArray.get(2).length-1) pw.write(Integer.toString(totalArray.get(2)[i]));
	    		}
	    	}


	    	pw.close();
	    	bf.close();
	    	
    	}catch ( IOException x ) {
            // Complain if there was any problem writing 
            // the output file.
            x.printStackTrace();
        } 
    	
  }
    
    /**
     * reconstrucImage
     * Encargada de reconstruir todos los pedazos de imagen en que se subdividio la imagen principal 
     * @param img
     * @param lostPart
     */
    public void reconstructImage(Images img, int lostPart) {
        // Array of input images.
        BufferedImage[] input = new BufferedImage[3];
         
        // Load each input image.
        // Assume they are called "image_0.png", "image_1.png",
        // etc.
        if(lostPart==0 | lostPart == 4) {
        	for ( int i = 0; i < input.length; i++ ) {
                try {
                	System.out.println("intento "+i);
                	File f= new File("RestoreProcess/restoredImage.png");
            		if(i==0) f = new File( "Disk"+img.getDiskPart1()+"/image."+img.getId()+"."+img.getIndexPart1()+".png" );
            		if(i==1) f = new File( "Disk"+img.getDiskPart2()+"/image."+img.getId()+"."+img.getIndexPart2()+".png" );
            		if(i==2) f = new File( "Disk"+img.getDiskPart3()+"/image."+img.getId()+"."+img.getIndexPart3()+".png" );
                    input[i] = ImageIO.read( f );
                    System.out.println(i);
                }
                catch ( IOException x ) {
                    // Complain if there is any problem loading 
                    // an input image.
                    x.printStackTrace();
                }
            }
        }
        if(lostPart!=0 & lostPart!=4) {
	        for ( int i = 0; i < input.length; i++ ) {
	            try {
	            	System.out.println("intento "+i);
	            	File f= new File("RestoreProcess/restoredImage.png");;
	            	if(i!=lostPart-1) {
	            		if(i==0) f = new File( "Disk"+img.getDiskPart1()+"/image."+img.getId()+"."+img.getIndexPart1()+".png" );
	            		if(i==1) f = new File( "Disk"+img.getDiskPart2()+"/image."+img.getId()+"."+img.getIndexPart2()+".png" );
	            		if(i==2) f = new File( "Disk"+img.getDiskPart3()+"/image."+img.getId()+"."+img.getIndexPart3()+".png" );
	            	}
	                input[i] = ImageIO.read( f );
	                System.out.println(i);
	            }
	            catch ( IOException x ) {
	                // Complain if there is any problem loading 
	                // an input image.
	                x.printStackTrace();
	            }
	        }
        }
         
        // Create the output image.
        // It is the same size as the first
        // input image.  I had to specify the type
        // so it would keep it's transparency.
        int height=0;
        for( int i = 0; i < input.length; i++) {
        	height+=input[i].getHeight();
        }
        System.out.println(height);
        
        BufferedImage output = new BufferedImage( 
                input[0].getWidth(), 
                height, 
                BufferedImage.TYPE_INT_ARGB );
         
        // Draw each of the input images onto the
        // output image.
        Graphics g = output.getGraphics();

        g.drawImage( input[0], 0, 0, null );
        g.drawImage(input[1], 0,input[0].getHeight(),null);
        g.drawImage(input[2], 0,input[0].getHeight()+input[1].getHeight(),null);
         
        // Create the output image file and write the
        // output image to it.
        File f = new File("RestoreProcess/restoredImage.png");
        try {
            ImageIO.write( output, "PNG", f );
        }
        catch ( IOException x ) {
            // Complain if there was any problem writing 
            // the output file.
            x.printStackTrace();
        } 
    }
    /**
     * delete
     * Se encarga de eliminar la imagen que el cliente solicita eliminar
     * @param id
     */
    public void delete(int id) {
    	Images toDelete=new Images();
    	for(int i=0;i<imagesList.size();i++) {
    		if(imagesList.get(i).getId()==id) {
    			toDelete=imagesList.get(i);
    			imagesList.remove(i);
    		}		
    	}
    	File a=new File( "Disk"+toDelete.getDiskPart1()+"/image."+toDelete.getId()+"."+toDelete.getIndexPart1()+".png" );
    	File b=new File( "Disk"+toDelete.getDiskPart2()+"/image."+toDelete.getId()+"."+toDelete.getIndexPart2()+".png" );
    	File c=new File( "Disk"+toDelete.getDiskPart3()+"/image."+toDelete.getId()+"."+toDelete.getIndexPart3()+".png" );
    	File d=new File("Disk"+toDelete.getDiskParity()+"/image."+toDelete.getId()+"."+toDelete.getIndexParity()+".txt");
    	a.delete();
    	b.delete();
    	c.delete();
    	d.delete();

    }
    public byte[] txtToBytes() {
    	byte[] parityArray=new byte[24895];
    	try {
	    	File file=new File("Incoming_Garbage/prueba.txt");
	    	FileReader fr=new FileReader(file);
	    	BufferedReader br =new BufferedReader(fr);
	    	String line;
	    	int i=0;
	    	while((line=br.readLine())!=null){
	    		parityArray[i]=(byte) Integer.parseInt(line);
	    		i++;
	    	}
	    	 br.close();
	    	 fr.close();
	    	}catch ( IOException x ) {
	            // Complain if there was any problem writing 
	            // the output file.
	            x.printStackTrace();
	        } 
    	return parityArray;
    	
    }
    
    public static void main(String[] args) throws IOException {
    	//Aqui cargo la imagen en bytes que estoy almacenando en un txt.
    	Controller co=new Controller();
    	byte[] imageArray=co.txtToBytes();
    	co.saveIncomingImage(imageArray, 1);
    	
    	
    	Images img2=new Images(2);
    	Images img3=new Images(3);
    	Images img4=new Images(4);
    	Images img5=new Images(5);
    	
    	co.loadImage(img2);
    	co.divideImage(null,img2);
    	
    	co.loadImage(img3);
    	co.divideImage(null,img3);
    	
    	co.loadImage(img4);
    	co.divideImage(null,img4);
    	
    	co.loadImage(img5);
    	co.divideImage(null,img5);
    	
    	
    	/*co.recoverImage(2);
    	co.recoverImage(3);
    	co.recoverImage(4);
    	co.recoverImage(5);*/
    	
    	
    	co.delete(2);
    	co.delete(3);
    	co.delete(4);
    	co.delete(5);
    	
    
    
    	
    	
    	
    }
}
