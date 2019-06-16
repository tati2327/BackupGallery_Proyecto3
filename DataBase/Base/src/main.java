import com.dataBase.BaseNoSql;

public class main {

	public static void main(String[] args) {

		BaseNoSql Base = new BaseNoSql();
		
		Base.addImage("Img", "Yo", "2008", "34MB", "Null");
		Base.addImage("Img", "Imagine", "2009", "3MG", "NULL");
		Base.addImage("bds", "Hello", "2010", "3MG", "NULL");
		
		Base.modifyData(1, 2, "YOLO");
		Base.modifyData(1, 3, "YOLO");
		Base.modifyData(2, 2, "YOLO");
		
		System.out.println(Base.selectBy(1, 2, "Img").get(0).get(1));
		System.out.println(Base.selectBy(1, 2, "Img").get(1).get(1));
		
		Base.toJson();

	}
}

	



