package Usuarios;

import Utilidades.Util;

import java.io.Serializable;

public class Jugador extends Usuarios implements Serializable, Comparable<Jugador> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreEquipo;
	private int dorsal;
	private int goles;
	private int asistencias;

	public Jugador(String nombre, String user, String contraseña, String nombreEquipo, int dorsal, int goles,
			int asistencias) {
		super(nombre, user, contraseña);
		this.nombreEquipo = nombreEquipo;
		this.dorsal = dorsal;
		this.goles = goles;
		this.asistencias = asistencias;
	}

	public Jugador() {
		super();
		this.nombreEquipo = "";
		this.dorsal = 0;
		this.goles = 0;
		this.asistencias = 0;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	public int getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}

	public void getDatos() {
		super.getDatos();
		System.out.println("Nombre del equipo: " + this.nombreEquipo);
		System.out.println("Dorsal: " + this.dorsal);
		System.out.println("Goles: " + this.goles);
		System.out.println("Asistencias: " + this.asistencias);
	}

	public void setDatos() {
		super.setDatos();
		System.out.println("Introduce el nombre del equipo: ");
		this.nombreEquipo = Util.introducirCadena();
		System.out.println("Introduce el dorsal del jugador: ");
		this.dorsal = Util.leerInt();
		System.out.println("Introduce la cantidad de goles: ");
		this.goles = Util.leerInt();
		System.out.println("Introduce la cantidad de asistencias: ");
		this.asistencias = Util.leerInt();
	}

	@Override
	public String toString() {
		return "Jugador{" + "nombreEquipo='" + nombreEquipo + '\'' + ", dorsal=" + dorsal + ", goles=" + goles
				+ ", asistencias=" + asistencias + ", nombre='" + nombre + '\'' +

				'}';
	}

	@Override
	public int compareTo(Jugador o) {
		return this.getDorsal() - o.getDorsal();
	}
}
