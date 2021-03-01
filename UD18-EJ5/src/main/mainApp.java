package main;

import db.Database;
import operaciones.Operacion;
import visual.Visual;

public class mainApp {

	public static void main(String[] args) {
		
		Visual v = new Visual();
		Operacion op = new Operacion();
		Database db = new Database(v.getUsuario(),v.getPass());
		String dbname = v.setDB();
		db.createDB(dbname);
		op.crearTabla(dbname, db);
		op.insertF(dbname,db, v);
		op.insertA(dbname, db, v);
		db.getValues(dbname);

	}

}