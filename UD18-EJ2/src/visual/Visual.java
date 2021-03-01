package visual;

import javax.swing.JOptionPane;

public class Visual {

	public String getUsuario() {

		return JOptionPane.showInputDialog("Usuario:");
	}

	public String getPass() {

		return JOptionPane.showInputDialog("Password:");
	}

	public String setDB() {

		return JOptionPane.showInputDialog("Nombre de la Base de Datos");
	}

	public String getName() {

		return JOptionPane.showInputDialog("Nombre:");
	}


	public int getIdD() {

		return Integer.parseInt(JOptionPane.showInputDialog("ID Departamento: "));
	}

	public int getDinero() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("Presupuesto: "));
	}

	public int getID() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("ID del departamento: "));
	}

	public String getApellido() {
		
		return JOptionPane.showInputDialog("Apellido: ");
	}

	public String getDNI() {
		
		return JOptionPane.showInputDialog("DNI: ");
	}

}
