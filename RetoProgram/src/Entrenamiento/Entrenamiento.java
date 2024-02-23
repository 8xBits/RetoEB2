package Entrenamiento;

import Utilidades.Util;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Entrenamiento implements Serializable {

	private static final long serialVersionUID = 1L;
	public static int count;
	private int codigoEntrenamiento;
	private LocalDateTime fetchaHoraInicio;
	private LocalDateTime fetchaHoraFin;
	private String material;

	public Entrenamiento() {
		count++;
		codigoEntrenamiento = count;
		this.fetchaHoraInicio = null;
		this.fetchaHoraFin = null;
		this.material = "";
	}

	public Entrenamiento(int codigoEntrenamiento, LocalDateTime fetchaHoraInicio, LocalDateTime fetchaHoraFin,
			String material) {
		count++;
		this.codigoEntrenamiento = count;
		;
		this.fetchaHoraInicio = fetchaHoraInicio;
		this.fetchaHoraFin = fetchaHoraFin;
		this.material = material;
	}

	public int getCodigoEntrenamiento() {
		return codigoEntrenamiento;
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
		System.out.println("Introduzca nombre del material :");
		this.material = Util.introducirCadena();
		do {
			
			System.out.println("Introduzca el dia con la Hora de inicio (aaaa/mm/dd HH:mm) :");
			this.fetchaHoraInicio = Util.leerFechaAMDH();
			System.out.println("Introduzca el dia con la Hora de fin (aaaa/mm/dd HH:mm):");
			this.fetchaHoraFin = Util.leerFechaAMDH();
			
			if(fetchaHoraFin.isBefore(fetchaHoraInicio)) {
				System.out.println("Error Fecha fin is antes fecha de inicio");
			}else if(fetchaHoraInicio.isBefore(LocalDateTime.now())) {
				System.out.println("No puede haber un entrenamiento antes de la hora actual");
			}
		} while (fetchaHoraFin.isBefore(fetchaHoraInicio) ||  fetchaHoraInicio.isBefore(LocalDateTime.now()));
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
