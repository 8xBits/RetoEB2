package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import Equipo.Equipo;
import Entrenamiento.Entrenamiento;
import Usuarios.*;
import Utilidades.Util;
import Utilidades.Util.MyObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		int userType;
		String user, passwd;
		File fichEquipo = new File("equipo.dat");
		File fichUsuarios = new File("usuarios.dat");

		crearAdmin(fichUsuarios);

		System.out.println("LOG IN");
		System.out.print("User: ");
		user = Util.introducirCadena();
		System.out.print("Contraseña: ");
		passwd = Util.introducirCadena();

	}

	private static void crearAdmin(File fich) {
		if (!fich.exists()) {
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(fich));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Usuarios admin = new Admin("Admin", "admin", "admin", 1);
			try {
				oos.writeObject(admin);
			} catch (IOException e) {
				System.out.println("Error al crear el administrador");
			}
		}
	}

	private static void introducirEquipo(File fich) {
		int opc;
		ObjectOutputStream oos = null;
		try {
			if (fich.exists()) {
				System.out.println("El fichero ya existe, se añadiran los alumnos al final");
				oos = new MyObjectOutputStream(new FileOutputStream(fich, true));
			} else {
				System.out.println("Fichero nuevo");
				oos = new ObjectOutputStream(new FileOutputStream(fich));
			}

			do {
				Equipo equipo = new Equipo();
				equipo.setDatos();
				oos.writeObject(equipo);
				opc = Util.leerInt("¿Más alumnos? 1=si/2=no", 1, 2);
			} while (opc == 1);
			try {
				oos.close();

			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
