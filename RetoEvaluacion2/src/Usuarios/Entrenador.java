package Usuarios;

public class Entrenador extends Usuarios {
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
		
	}

}
