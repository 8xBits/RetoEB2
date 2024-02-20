package Usuarios;

import java.io.Serializable;

public class Admin extends Usuarios  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codAdmin;

	public Admin(String nombre, String user, String contrasena, int codAdmin) {
		super(nombre, user, contrasena);
		this.codAdmin = codAdmin;
		
	}

	public Admin() {
		super();
		this.codAdmin = 0;
	}

	public int getCodAdmin() {
		return codAdmin;
	}

	public void setCodAdmin(int codAdmin) {
		this.codAdmin = codAdmin;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"codAdmin=" + codAdmin +
				", nombre='" + nombre + '\'' +

				'}';
	}
}
