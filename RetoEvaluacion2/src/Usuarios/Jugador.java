package Usuarios;

public class Jugador extends Usuarios {
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

}
