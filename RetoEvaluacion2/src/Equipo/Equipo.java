package Equipo;

import java.io.Serializable;
import java.util.ArrayList;
import Entrenamiento.Entrenamiento;
import Utilidades.Util;

public class Equipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreEquipo;
	private String estadio;
	private int titulos;
	private ArrayList<Entrenamiento> listaEntrenamiento;

	public Equipo() {
		this.nombreEquipo = "";
		this.estadio = "";
		this.listaEntrenamiento = new ArrayList<>();
		this.titulos = 0;
	}

	public Equipo(String nombreEquipo, String estadio, int titulos) {
		this.nombreEquipo = nombreEquipo;
		this.estadio = estadio;
		this.titulos = titulos;
		this.listaEntrenamiento = new ArrayList<>();
		;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public int getTitulos() {
		return titulos;
	}

	public void setTitulos(int titulos) {
		this.titulos = titulos;
	}

	public ArrayList<Entrenamiento> getListaEntrenamiento() {
		return listaEntrenamiento;
	}

	public void setListaEntrenamiento(ArrayList<Entrenamiento> listaEntrenamiento) {
		this.listaEntrenamiento = listaEntrenamiento;
	}

	public void addEntrenamiento(Entrenamiento entrenamiento) {
		listaEntrenamiento.add(entrenamiento);
	}

	public void setDatosEquipo() {
		System.out.println("Introduzca nombre del equipo :");
		this.nombreEquipo = Util.introducirCadena();
		System.out.println("Introduzca el estadio :");
		this.estadio = Util.introducirCadena();
		System.out.println("Introduzca titulos :");
		this.titulos = Util.leerInt();
	}

	public void getDatosEquipo() {
		System.out.println("-----Datos del equipo-----");
		System.out.println("Nombre del equipo : " + this.nombreEquipo);
		System.out.println("Estadio : " + this.estadio);
		System.out.println("Titulos : " + this.titulos);

	}

	@Override
	public String toString() {
		return "Equipo{" +
				"nombreEquipo='" + nombreEquipo + '\'' +
				", estadio='" + estadio + '\'' +
				", titulos=" + titulos +
				", listaEntrenamiento=" + listaEntrenamiento +
				'}';
	}
}
