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
	
		//METODO QUE ABRE LA CONEXION CON SERVER MYSQL
		public void openConnection(String u, String p) {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion=DriverManager.getConnection("jdbc:mysql://192.168.1.75:3306?useTimezone=true&serverTimezone=UTC",u,p);//Cambiar la ip a la de tu server
				System.out.print("Server Connected");
				fecha();
				
			}catch(SQLException | ClassNotFoundException ex  ){
				System.out.print("No se ha podido conectar con mi base de datos");
				fecha();
				System.out.println(ex.getMessage());
				
			}
			
		}
			
		//METODO QUE CIERRA LA CONEXION CON SERVER MYSQL
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
		
		//METODO QUE CREA LA BASE DE DATOS
		public void createDB(String name) {
			try {
				String Query="CREATE DATABASE "+ name;
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("DB creada con exito!");
				
			JOptionPane.showMessageDialog(null, "Se ha creado la DB " +name+ "de forma exitosa.");
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error creando la DB.");
			}	
		}

		//METODO QUE CREA TABLAS MYSQL
		public void createTable(String db ){
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "\r\n"
						+ "create table Peliculas(\r\n"
						+ "Id int auto_increment primary key,\r\n"
						+ "Nombre varchar(100) not null,\r\n"
						+ "CalificacionEdad int not null\r\n"
						+ ");\r\n"
						+ "\r\n"
						+ "create table Salas(\r\n"
						+ "Id int auto_increment primary key,\r\n"
						+ "Id_pelicula int not null,\r\n"
						+ "Nombre varchar(100) not null,\r\n"
						+ "foreign key (Id_pelicula) references Peliculas(Id) on delete no action on update cascade\r\n"
						+ ");\r\n"
						+ "";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tablas Salas y Peliculas creadas con exito!");
		
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public void insertDataP(String db, String lugar, int capacidad) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO Peliculas(Nombre,CalificacionEdad) VALUE(" 
						+ "\"" + lugar + "\","+capacidad+");";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Peliculas: Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Peliculas: Error en el almacenamiento");
			}
						
		}
		
		public void insertDataS(String db,String contenido, int idA, int i) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO Salas(Id_pelicula, Nombre) VALUE("+idA+"" 
						+ "\"" + contenido + "\");";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Salas: Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Salas: Error en el almacenamiento");
				i--;
			}
						
		}
		
		//METODO QUE OBTIENE VALORES MYSQL
		public void getValues(String db) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM Peliculas";
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("ID: " +  resultSet.getString("Id") + " "
							+ "Nombre: " +  resultSet.getString("Nombre") + " "
							+ "Edad minima: " +  resultSet.getString("CalificacionEdad") + " "
							);
				}
				String Query2 = "SELECT * FROM Salas";
				Statement st2 = conexion.createStatement();
				java.sql.ResultSet resultSet2;
				resultSet2 = st2.executeQuery(Query2);
				
				while (resultSet2.next()) {
					System.out.println("ID: " +  resultSet2.getString("Id") + " "
							+ "Nombre: " +  resultSet2.getString("Contenido") + " "
							+ "ID Pelicula: " +  resultSet2.getString("Id_pelicula") + " "
							);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
		
		}
		 
		//METODO QUE LIMPIA TABLAS MYSQL
		public void deleteRecord(String db, String table_name, String ID) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
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

		
		//METODO QUE ELIMINA TABLAS MYSQL
		public void deleteTAbla(String db, String table_name) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
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
		
		
		//METODO QUE MUESTRA FECHA
		public void fecha() {
			Date date = new Date();
			DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			System.out.println(" - " + hourdateFormat.format(date));
			}
		
		

	}


