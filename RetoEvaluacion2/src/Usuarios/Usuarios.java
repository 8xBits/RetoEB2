package Usuarios;

public class Usuarios {
	protected String nombre;
	protected String user;
	protected String contraseña;

	public Usuarios() {
		this.nombre = "";
		this.user = "";
		this.contraseña = "";
	}

	public Usuarios(String nombre, String user, String contraseña) {
		this.nombre = nombre;
		this.user = user;
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void getDatos() {
		System.out.println("Nombre: " + this.nombre);
	}
}
