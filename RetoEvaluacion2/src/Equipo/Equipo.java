package Equipo;

import java.util.ArrayList;
import Entrenamiento.Entrenamiento;

public class Equipo {

	private String nombreEquipo;
	private String estadio;
	private int titulos;
	private ArrayList<Entrenamiento> listaEntrenamiento;

	public Equipo() {
		this.nombreEquipo = "";
		this.estadio = "";
		this.listaEntrenamiento = null;
		this.titulos = 0;
	}

	public Equipo(String nombreEquipo, String estadio, int titulos, ArrayList<Entrenamiento> listaEntrenamiento) {
		this.nombreEquipo = nombreEquipo;
		this.estadio = estadio;
		this.titulos = titulos;
		this.listaEntrenamiento = listaEntrenamiento;
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


    public void test(){
        System.out.println("Testing github");
    }
}
