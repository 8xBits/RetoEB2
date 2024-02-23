package Utilidades;

public class ExcepcionUser extends Exception {
	private static final long serialVersionUID = 1L;

	public ExcepcionUser(String mensaje) {
		super(mensaje);	
	}
}