package Entrenamiento;

import Utilidades.Util;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Entrenamiento implements Serializable {

	private static final long serialVersionUID = 1L;

	private int codigoEntrenamiento;
	private LocalDateTime fetchaHoraInicio;
	private LocalDateTime fetchaHoraFin;
	private String material;
	// private ArrayList<String> materiales;

	public Entrenamiento() {
		codigoEntrenamiento=0;
		this.fetchaHoraInicio = null;
		this.fetchaHoraFin = null;
		this.material = "";
		// this.materiales = new ArrayList<>();
	}

	public Entrenamiento(int codigoEntrenamiento ,LocalDateTime fetchaHoraInicio, LocalDateTime fetchaHoraFin, String material) {
		this.codigoEntrenamiento=codigoEntrenamiento;
		this.fetchaHoraInicio = fetchaHoraInicio;
		this.fetchaHoraFin = fetchaHoraFin;
		this.material = material;
	}

	public int getCodigoEntrenamiento() {
		return codigoEntrenamiento;
	}

	public void setCodigoEntrenamiento(int codigoEntrenamiento) {
		this.codigoEntrenamiento = codigoEntrenamiento;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public LocalDateTime getFetchaHoraInicio() {
		return fetchaHoraInicio;
	}

	public void setFetchaHoraInicio(LocalDateTime fetchaHoraInicio) {
		this.fetchaHoraInicio = fetchaHoraInicio;
	}

	public LocalDateTime getFetchaHoraFin() {
		return fetchaHoraFin;
	}

	public void setFetchaHoraFin(LocalDateTime fetchaHoraFin) {
		this.fetchaHoraFin = fetchaHoraFin;
	}

	public void setDatosEntrenamiento() {
		System.out.println("Introduzca el código del Entrenamiento :");
		this.codigoEntrenamiento = Util.leerInt();
		System.out.println("Introduzca nombre del material :");
		this.material = Util.introducirCadena();
		do {
			System.out.println("Introduzca el dia con la Hora de inicio :");
			this.fetchaHoraInicio = Util.leerFechaAMDH();
			System.out.println("Introduzca el dia con la Hora de inicio :");
			this.fetchaHoraFin = Util.leerFechaAMDH();
		} while (fetchaHoraFin.isBefore(fetchaHoraInicio));

	}

	public String getDuracion() {
		Long duration = Duration.between(this.getFetchaHoraInicio(), this.getFetchaHoraFin()).toHours();
		return duration.toString();
	}

	public void getDatosEntrenamiento() {
		System.out.println("-----Datos de Entrenamiento-----");
		System.out.println("Código del entrenamiento : " + this.codigoEntrenamiento);
		System.out.println("Fecha y hora de inicio : " + this.fetchaHoraInicio);
		System.out.println("Duraccion : " + getDuracion() + "h");
		System.out.println("Material : " + this.material);
	}

}
