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
		
		return Integer.parseInt(JOptionPane.showInputDialog("ID: "));
	}

	

	public String getNumR() {
		
		return JOptionPane.showInputDialog("Id del Equipo (ej: AAAA): ");
	}

	public int getCapacidad() {
	
		return Integer.parseInt(JOptionPane.showInputDialog("Capacidad: "));
	}

	public int getPieza() {
	
		return Integer.parseInt(JOptionPane.showInputDialog("ID Pieza: "));
	}

	public int getValor() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("Valor: "));
	}

	

	public String getDNI() {
	
		return JOptionPane.showInputDialog("DNI: ");
	}

	public int getIDF() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("ID Facultad : "));
	}

	public int getPiso() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("Piso: "));
	}

	public int getIDc() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("ID del Cajero:"));
	}
	
public int getIDp() {
		
		return Integer.parseInt(JOptionPane.showInputDialog("ID del Producto:"));
	}

public int getIDm() {
	
	return Integer.parseInt(JOptionPane.showInputDialog("ID de la Maquina:"));
}

public int getFfin() {
	
	return Integer.parseInt(JOptionPane.showInputDialog("Fecha de fin: "));
}

public int getFinicio() {
	
	return Integer.parseInt(JOptionPane.showInputDialog("Fecha de inicio"));
}
	
}
