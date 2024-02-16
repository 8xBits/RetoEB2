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
import Utilidades.MyObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		// usertype 0 es admin, el 1 es entrenador y el 2 jugador
		int userType;
		File fichEquipo = new File("equipo.dat");
		File fichUsuarios = new File("usuarios.dat");

		crearAdmin(fichUsuarios);

		do {
			userType = Util.leerInt("Desea iniciar sesion 3=si/4=no", 3, 4);
			if (userType == 3) {
				logIn();
				int menu;
				switch (userType) {
				case 0:
					menuAdmin();

					break;
				case 1:
					menuEntrenador();

					break;
				case 2:
					menuJugador();

					break;
				}
			}
		} while (userType != 4);
	}// main

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

	private static void logIn() {
		String user, passwd;

		System.out.println("LOG IN");
		System.out.print("User: ");
		user = Util.introducirCadena();
		System.out.print("Contraseña: ");
		passwd = Util.introducirCadena();
	}

	private static void introducirEquipo(File fich) {
		int opc;
		ObjectOutputStream oos = null;
		try {
			if (fich.exists()) {
				oos = new MyObjectOutputStream(new FileOutputStream(fich, true));
			} else {
				oos = new ObjectOutputStream(new FileOutputStream(fich));
			}

			do {
				Equipo equipo = new Equipo();
				equipo.setDatosEquipo();
				oos.writeObject(equipo);
				opc = Util.leerInt("¿Desea añadir mas equipos? 1=si/2=no", 1, 2);
			} while (opc == 1);
			oos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//alder
	private static void menuAdmin() {
		System.out.println("MENU ADMIN");
		System.out.println("1.- Introducir equipo");
		System.out.println("2.- Introducir entrenador");
		System.out.println("3.- Salir");
	}

//enzo
	private static void menuEntrenador() {

	}

//omar
	private static void menuJugador() {
		int opc;
		do {
			System.out.println("MENU Jugador");
			System.out.println("1.- Combrobar dorsal (ver disponibles)");
			System.out.println("2.- Ver info equipo ");
			System.out.println("3.- Salir");
			opc = Util.leerInt();
			switch (opc) {
			case 1:
				comprobarDorsal();
				break;
			case 2:
				verInfoEquipo();
				break;
			case 3:
				System.out.println("Hasta pronto..");
				break;
			}

		} while (opc != 3);
	}

	public static void comprobarDorsal() {

	}

	public static void verInfoEquipo() {
			
	}

}// class
