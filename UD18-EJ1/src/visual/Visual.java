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

	public int getPrecio() {

		return Integer.parseInt(JOptionPane.showInputDialog("Precio: "));
	}

	public int getIdF() {

		return Integer.parseInt(JOptionPane.showInputDialog("ID Fabricante:\n(1,2,3,4,5) "));
	}

}
