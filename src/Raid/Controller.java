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
import java.io.*;

import javax.imageio.ImageIO;


public class Controller extends JPanel {

    private BufferedImage mainImage;
    private int width, height;
    private Image imagePart1, imagePart2, imagePart3;
    private ArrayList<Images> imagenPartes = new ArrayList();
    private int diskCount=1;
    private int id;
    private File completeImage;
    public SimpleList<Images> imagesList=new SimpleList();

    

    private void loadImage(Images img) throws IOException {
    	System.out.print("holangas");
        mainImage = ImageIO.read(new File("garbage/image."+img.getId()+".png"));
        
        width = mainImage.getWidth(null);
        height = mainImage.getHeight(null);

        imagePart1 = createImage(new FilteredImageSource(mainImage.getSource(),
        		new CropImageFilter(0, 0,width ,height/3)));        
        imagePart2 = createImage(new FilteredImageSource(mainImage.getSource(),
        		new CropImageFilter(0, height/3, width, height / 3)));
        imagePart3 = createImage(new FilteredImageSource(mainImage.getSource(),
        		new CropImageFilter(0, (height*2)/3, width, height/3)));
        
    }

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
			img.setPart1(temp2);
			temp1+=1;
			temp2+=1;
			if(temp2==5) temp2=1;
			
			ImageIO.write(bufferedImage2, "png", new File("Disk"+temp2+"/image."+img.getId()+"."+temp1+".png"));
			img.setPart2(temp2);
			temp1+=1;
			temp2+=1;
			if(temp2==5) temp2=1;
			
			ImageIO.write(bufferedImage3, "png", new File("Disk"+temp2+"/image."+img.getId()+"."+temp1+".png"));
			img.setPart3(temp2);
			temp1+=1;
			temp2+=1;
			if(temp2==5) temp2=1;
			parity(img,temp1,temp2);
			img.setPartity(temp2);
			diskCount+=1;
		} catch (IOException e) {
			e.printStackTrace();
		}
         	
    }
    
    public byte[] imageToBytes(Images img, int diskNumber, int imagePart) throws IOException {
		File file = new File("Disk"+diskNumber+"/image."+img.getId()+"."+imagePart+".png");
        BufferedImage originalImage = ImageIO.read(file);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(originalImage, "png", baos);
        byte[] imageInByte = baos.toByteArray();
        /*if(subIndex==1) array1=imageInByte;
        if(subIndex==2) array2=imageInByte;
        if(subIndex==3) array3=imageInByte;*/
        System.out.println("Imagen pasada a bytes");
		return imageInByte;
	}
    
    public void saveSubImage(byte[] imageInByte) throws IOException {
		
		ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
		BufferedImage bufferImage = ImageIO.read(bis);
		//ImageIO.write(bufferImage, "png", new File("CortarImagen/image"+id+"."+ subIndex+".png") );
        System.out.println("images saved");
	}
    
    public void saveIncomingImage(byte[] imageInByte) throws IOException {
		
		ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
		BufferedImage bufferImage = ImageIO.read(bis);
		Images img=new Images(id);
		imagesList.add(img);
		ImageIO.write(bufferImage, "png", new File("garbage/image."+img.getId()+".png") );
        System.out.println("images saved");
	}
  public void parity(Images img,int temp1, int temp2) throws IOException {
    	byte[] array1=imageToBytes(img,img.getPart1(),1);
    	byte[] array2=imageToBytes(img,img.getPart2(),2);
    	byte[] array3=imageToBytes(img,img.getPart3(),3);
    	System.out.println(array1.length);
    	System.out.println(array2.length);
    	System.out.println(array3.length);
    	
    	img.setPart1Length(array1.length-1);
    	img.setPart2Length(array2.length-1);
    	img.setPart3Length(array3.length-1);
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
    	System.out.println("ordenada");

    	System.out.println(totalArray.get(0).length);
    	System.out.println(totalArray.get(1).length);
    	System.out.println(totalArray.get(2).length);
	try {
	    	File file=new File("Disk"+temp2+"/image."+img.getId()+"."+temp1+".txt");
	    	FileWriter fw=new FileWriter(file);
	    	BufferedWriter bf =new BufferedWriter(fw);
	    	PrintWriter pw = new PrintWriter(bf);
	    	
	    	for(int i=0; i<=totalArray.get(2).length-1;i++) {
	    		
	    		if(i<=totalArray.get(0).length-1) {
	    			int value= totalArray.get(0)[i]^totalArray.get(1)[i]^totalArray.get(2)[i];
	    			pw.write(Integer.toString(value));
	    	    	pw.append(",");
	    		}
	    		if (i>totalArray.get(0).length-1 & i <=totalArray.get(1).length-1) {
	    			int value=totalArray.get(1)[i]^totalArray.get(2)[i];
	    			pw.write(Integer.toString(value));
	    	    	pw.append(",");
	    		}
	    		if (i>totalArray.get(1).length-1) {
	    			pw.write(Integer.toString(totalArray.get(2)[i]));
	    	    	pw.append(",");
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
    
    public void reconstructImage() {
        // Array of input images.
        BufferedImage[] input = new BufferedImage[3];
         
        // Load each input image.
        // Assume they are called "image_0.png", "image_1.png",
        // etc.
        int j;
        for ( int i = 0; i < input.length; i++ ) {
            try {
            	System.out.println("intento "+i);
            	j=i+1;
                File f = new File( "CortarImagen/zorra" + j + ".png" );
                input[i] = ImageIO.read( f );
                System.out.println(i);
            }
            catch ( IOException x ) {
                // Complain if there is any problem loading 
                // an input image.
                x.printStackTrace();
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
        File f = new File( "CortarImagen/final.png" );
        try {
            ImageIO.write( output, "PNG", f );
        }
        catch ( IOException x ) {
            // Complain if there was any problem writing 
            // the output file.
            x.printStackTrace();
        } 
    }
    
    public static void main(String[] args) throws IOException {
    	Images img=new Images(5);
    	Controller co=new Controller();
    	co.loadImage(img);
    	co.divideImage(null,img);
    	
    }
}













