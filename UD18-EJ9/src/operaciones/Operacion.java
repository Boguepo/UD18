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
			db.insertDataD(dbname, v.getID(),v.getName());
		}
		
		
	}
	
	public void insertA(String dbname, Database db, Visual v) {
		for (int i = 0; i < 5; i++) {
			db.insertDataE(dbname,v.getNumR(),v.getName(),v.getIDF(),i );
		}
		
		
	}
	
	public void insertD(String dbname, Database db, Visual v) {
		for (int i = 0; i < 5; i++) {
			db.insertDataA(dbname,v.getDNI(),v.getIDF(),v.getName(),i );
		}
		
		
	}
	
	public void insertT(String dbname, Database db, Visual v) {
		for (int i = 0; i < 5; i++) {
			db.insertDataT(dbname,v.getDNI(),v.getNumR(),v.getFinicio(),v.getFfin(),i );
		}
		
		
	}

}
