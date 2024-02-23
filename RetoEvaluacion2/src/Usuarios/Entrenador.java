package Usuarios;

import Utilidades.Util;

import java.io.File;
import java.io.Serializable;

import Equipo.Equipo;

import java.util.ArrayList;

public class Entrenador extends Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreEquipo;
	private CargoEntrenador cargo;

	public Entrenador(String nombre, String user, String contraseña, String nombreEquipo, CargoEntrenador cargo) {
		super(nombre, user, contraseña);
		this.nombreEquipo = nombreEquipo;
		this.cargo = cargo;
	}

	public Entrenador() {
		super();
		this.nombreEquipo = "";
		this.cargo = null;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public CargoEntrenador getCargo() {
		return cargo;
	}

	public void setCargo(CargoEntrenador cargo) {
		this.cargo = cargo;
	}

	public void getDatos() {

		super.getDatos();
		System.out.println("Nombre del equipo: " + this.nombreEquipo);
		System.out.println("Cargo: " + this.cargo);

	}

	public void setDatos() {

		super.setDatos();
		String str;
		boolean error = false;
		System.out.println("Introduce el nombre del equipo: ");
		this.nombreEquipo = Util.introducirCadena();

		do {
			System.out.println(
					"Introduzca el cargo del entrenador: (Primer entrenador / Segundo entrenador/ Preparador fisico)");
			str = Util.introducirCadena();
			try {
				this.cargo = CargoEntrenador.valueOf(str.toUpperCase());
				error = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e);
				error = true;
			}
		} while (error);
	}

	//
	public boolean comprobar(File fichEquipos, String nombreEquipo) {
		ArrayList<Equipo> equiposLista = new ArrayList<>();
		Util.fileToArray(fichEquipos, equiposLista);

		for (Equipo miEqui : equiposLista) {
			if (miEqui.getNombreEquipo().equalsIgnoreCase(nombreEquipo)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Entrenador{" + "nombreEquipo='" + nombreEquipo + '\'' + ", cargo=" + cargo + ", nombre='" + nombre
				+ '\'' +

				'}';
	}
}
