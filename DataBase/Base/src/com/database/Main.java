package com.database;

import com.socket.Client;
import com.structures.SimpleList;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Client myClient = new Client();
		myClient.newClient();

		/*BaseNoSql Base = new BaseNoSql();
		//POSICIONES:
		//id:0 - name:1 - author:2 - year:3 - size:4 - description:4

		System.out.println("INSERT");
		Base.addImage("imagen2", "Leonard", "2008", "34MB", "NULL"); //name - author - year - size - description
		System.out.println(Base.toJson());
		Base.addImage("imagen3", "Ramses", "2009", "3MG", "NULL");
		System.out.println(Base.toJson());
		Base.addImage("imagen4", "Sheldon", "2010", "3MG", "NULL");
		System.out.println(Base.toJson());
		System.out.println();

		System.out.println("MODIFY");
		Base.modifyData(1, "image2", 3, "2008"); //Posicion del dato a cambiar - dato a cambiar - Posicion de la columna condicional - valor de la condicion
		System.out.println(Base.toJson());
		System.out.println();
		
		System.out.println("DELETE");
		Base.deleteImage(2); //Se elimina por ID
		System.out.println(Base.toJson());

		Base.deleteImagebyCondition(2, "imagen2"); //Posicion del dato - condicion
		System.out.println(Base.toJson());
		System.out.println();

		System.out.println("SELECT");
		String column = "Year: ";
		SimpleList<SimpleList> metadata = Base.selectBy(3); //Posicion de la columna a retornar
		for (int i = 0; i < metadata.size(); i++) {
			for (int j = 0; j < metadata.get(i).size(); j++) {
				System.out.println("ID: "+metadata.get(i).get(j)+" "+column+metadata.get(i).get(j+1));
				j++;
			}
		}
		System.out.println();

		System.out.println(Base.toJson());*/
	}

}
