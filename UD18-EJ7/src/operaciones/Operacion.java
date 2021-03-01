package operaciones;

import db.Database;
import visual.Visual;

public class Operacion {

	public void crearTabla(String dbname, Database db) {
		db.createDB(dbname);
		db.createTable(dbname);
		
	}

	public void insertF(String dbname, Database db, Visual v) {
		for (int i = 0; i < 5; i++) {
			db.insertDataD(dbname,v.getDNI(),v.getName() );
		}
		
		
	}
	
	public void insertA(String dbname, Database db, Visual v) {
		for (int i = 0; i < 5; i++) {
			db.insertDataE(dbname,v.getName2(),v.getName(),v.getHoras(),i );
		}
		
		
	}
	
	public void insertD(String dbname, Database db, Visual v) {
		for (int i = 0; i < 5; i++) {
			db.insertDataA(dbname,v.getDNI(),v.getName2(),i );
		}
		
		
	}

}
