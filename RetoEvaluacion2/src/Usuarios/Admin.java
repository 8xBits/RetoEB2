package Usuarios;

public class Admin extends Usuarios {
	private int codAdmin;

	public Admin(String nombre, String user, String contraseña, int codAdmin) {
		super(nombre, user, contraseña);
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

}
