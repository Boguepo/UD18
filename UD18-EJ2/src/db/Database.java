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
				
				String Query = "create table Departamentos(\r\n"
						+ "Id int primary key,\r\n"
						+ "Nombre varchar(100) not null,\r\n"
						+ "Presupuesto int not null\r\n"
						+ ");\r\n"
						+ "\r\n"
						+ "create table Empleados(\r\n"
						+ "DNI varchar(8) primary key,\r\n"
						+ "Id_departamento int not null,\r\n"
						+ "Nombre varchar(100) not null,\r\n"
						+ "Apellidos varchar(255) not null,\r\n"
						+ "foreign key (Id_departamento) references Departamentos (Id) on delete no action on update cascade\r\n"
						+ ");";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tablas Departamentos y Empleados creadas con exito!");
		
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public void insertDataD(String db,int id, String name, int presupuesto) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO Departamentos VALUE("+id+"," 
						+ "\"" + name + "\","+presupuesto+");";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Departamentos: Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Departamentos: Error en el almacenamiento");
			}
						
		}
		
		public void insertDataE(String db,String dni, String name, String apellido, int idD, int i) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO Empleados VALUE("+dni+","+idD+"" 
						+ "\"" + name + "\","
							+ "\""+apellido+"\");";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Empleados: Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Empleados: Error en el almacenamiento");
				i--;
			}
						
		}
		
		//METODO QUE OBTIENE VALORES MYSQL
		public void getValues(String db) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM Departamentos";
				Statement st = conexion.createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					System.out.println("ID: " +  resultSet.getString("ID") + " "
							+ "Nombre: " +  resultSet.getString("Nombre") + " "
							+ "Presupuesto: " +  resultSet.getString("Presupuesto") + " "
							);
				}
				String Query2 = "SELECT * FROM Empleados";
				Statement st2 = conexion.createStatement();
				java.sql.ResultSet resultSet2;
				resultSet2 = st2.executeQuery(Query2);
				
				while (resultSet2.next()) {
					System.out.println("DNI: " +  resultSet2.getString("DNI") + " "
							+ "Nombre: " +  resultSet2.getString("Nombre") + " "
							+ "Apellido:" + resultSet2.getString("Apellido") +  " "
							+ "ID Departamento: " +  resultSet2.getString("ID_departamento") + " "
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


