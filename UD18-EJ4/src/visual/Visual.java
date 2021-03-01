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



	public int getDinero() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("Presupuesto: "));
	}

	public int getID() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("ID Pelicula: "));
	}

	

	public String getNumR() {
		
		return JOptionPane.showInputDialog("Numero de referencia (ej: AAAA): ");
	}

	public int getCapacidad() {
	
		return Integer.parseInt(JOptionPane.showInputDialog("Edad minima: "));
	}

	public String getLugar() {
	
		return JOptionPane.showInputDialog("Nombre: ");
	}

	public int getValor() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("Valor: "));
	}

}
