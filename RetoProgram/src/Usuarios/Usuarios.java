package Usuarios;

import Utilidades.Util;

import java.io.File;
import java.io.Serializable;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String user;
	protected String contrasena;

	public Usuarios() {
		this.nombre = "";
		this.user = "";
		this.contrasena = "";
	}

	public Usuarios(String nombre, String user, String contraseña) {
		this.nombre = nombre;
		this.user = user;
		this.contrasena = contraseña;
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
		return contrasena;
	}

	public void setContraseña(String contraseña) {
		this.contrasena = contraseña;
	}

	public void getDatos() {
		System.out.println("Nombre: " + this.nombre);
	}
	
	public void setDatos() {
		
		System.out.println("Introduce el nombre: ");
		this.nombre = Util.introducirCadena();
		System.out.println("Introduce el nombre de usuario: ");
		this.user = Util.introducirCadena();
		System.out.println("Introduce la contraseña: ");
		this.contrasena = Util.introducirCadena();
		
	}

	@Override
	public String toString() {
		return "Usuarios{" +
				"nombre='" + nombre + '\'' +
				'}';
	}
}
