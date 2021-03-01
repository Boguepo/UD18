package db;

//CRUD A MYSQL

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Database {

	private Connection conexion;

	public Database(String user, String pswrd) {
		openConnection(user, pswrd);
	}

	// METODO QUE ABRE LA CONEXION CON SERVER MYSQL
	public void openConnection(String u, String p) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.75:3306?useTimezone=true&serverTimezone=UTC",
					u, p);// Cambiar la ip a la de tu server
			System.out.print("Server Connected");
			fecha();

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.print("No se ha podido conectar con mi base de datos");
			fecha();
			System.out.println(ex.getMessage());

		}

	}

	// METODO QUE CIERRA LA CONEXION CON SERVER MYSQL
	public void closeConnection() {
		try {

			conexion.close();
			System.out.print("Server Disconnected");
			fecha();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.print("Error cerrando conexion");
			fecha();
		}
	}

	// METODO QUE CREA LA BASE DE DATOS
	public void createDB(String name) {
		try {
			String Query = "CREATE DATABASE " + name;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("DB creada con exito!");

			JOptionPane.showMessageDialog(null, "Se ha creado la DB " + name + "de forma exitosa.");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la DB.");
		}
	}

	// METODO QUE CREA TABLAS MYSQL
	public void createTable(String db) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "create table Cajeros(\r\n"
					+ "Id int auto_increment primary key,\r\n"
					+ "NomApels varchar(255)\r\n"
					+ ");\r\n"
					+ "\r\n"
					+ "create table Productos(\r\n"
					+ "Id int auto_increment primary key,\r\n"
					+ "Nombre varchar(100),\r\n"
					+ "Precio int not null\r\n"
					+ ");\r\n"
					+ "\r\n"
					+ "create table Maquinas(\r\n"
					+ "Id int auto_increment primary key,\r\n"
					+ "Piso int not null\r\n"
					+ ");\r\n"
					+ "\r\n"
					+ "create table Venta(\r\n"
					+ "Id_cajero int,\r\n"
					+ "Id_producto int,\r\n"
					+ "Id_maquina int,\r\n"
					+ "primary key(Id_cajero,Id_producto, Id_maquina),\r\n"
					+ "foreign key (Id_cajero) references Cajeros(Id) on delete cascade on update cascade,\r\n"
					+ "foreign key (Id_producto) references Productos(Id) on delete cascade on update cascade,\r\n"
					+ "foreign key (Id_maquina) references Maquinas(Id) on delete cascade on update cascade\r\n"
					+ ");";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tablas Cajeros, Productos, Maquinas y Venta creadas con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}

	}

	// METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public void insertDataD(String db, String nombre) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO Cajeros(NomApels) Piezas(\"" + nombre + "\");";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Cajeros: Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Cajeros: Error en el almacenamiento");
		}

	}

	public void insertDataE(String db, String nombre, int precio, int i) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO Productos(Nombre,Precio) VALUE(\"" + nombre + "\", "+precio+");";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Productos: Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Productos: Error en el almacenamiento");
			i--;
		}

	}

	public void insertDataA(String db,int piso, int i) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO Maquinas(Piso) VALUE(" +piso+ ");";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Maquinas: Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Maquinas: Error en el almacenamiento");
			i--;
		}

	}
	
	public void insertDataT(String db, int idC, int idP,int idM, int i) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO Venta VALUE(" + idC + "," + idP + ","+idM+");";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Venta: Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Venta: Error en el almacenamiento");
			i--;
		}

	}

	// METODO QUE OBTIENE VALORES MYSQL
	public void getValues(String db) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "SELECT * FROM Cajeros";
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getString("Id") + " " + "Nombre: "
						+ resultSet.getString("NomApels") + " ");
			}
			String Query2 = "SELECT * FROM Productos";
			Statement st2 = conexion.createStatement();
			java.sql.ResultSet resultSet2;
			resultSet2 = st2.executeQuery(Query2);

			while (resultSet2.next()) {
				System.out.println("ID: " + resultSet2.getString("Id") + " " + "Nombre: "
						+ resultSet2.getString("Nombre") + " " + "Precio: "
						+ resultSet2.getString("Precio") 
						+ " ");
			}
			
			String Query3 = "SELECT * FROM Maquinas";
			Statement st3 = conexion.createStatement();
			java.sql.ResultSet resultSet3;
			resultSet3 = st3.executeQuery(Query3);

			while (resultSet3.next()) {
				System.out.println("ID : " + resultSet3.getString("Id") + " " + "Piso: "
						+ resultSet3.getString("Piso") 
						+ " ");
			}
			
			String Query4 = "SELECT * FROM Maquinas";
			Statement st4 = conexion.createStatement();
			java.sql.ResultSet resultSet4;
			resultSet4 = st4.executeQuery(Query4);

			while (resultSet4.next()) {
				System.out.println("ID Cajero : " + resultSet4.getString("Id_cajero") + " " + "ID Producto: "
						+ resultSet4.getString("Id_producto")+ " " + "ID Maquina: "
								+ resultSet4.getString("Id_maquina") 
						+ " ");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}

	}

	// METODO QUE LIMPIA TABLAS MYSQL
	public void deleteRecord(String db, String table_name, String ID) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Registros de tabla ELIMINADOS con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
		}
	}

	// METODO QUE ELIMINA TABLAS MYSQL
	public void deleteTAbla(String db, String table_name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "DROP TABLE " + table_name + ";";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("TABLA ELIMINADA con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando la tabla");
		}
	}

	// METODO QUE MUESTRA FECHA
	public void fecha() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		System.out.println(" - " + hourdateFormat.format(date));
	}

}
