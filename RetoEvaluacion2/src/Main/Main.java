package Main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
				logIn(fichUsuarios, userType);
				int menu = 0;
				switch (userType) {
				case 0:
					do {
						menuAdmin();
						seleccionAdmin(menu, fichEquipo, fichUsuarios);
					} while (menu != 3);
					break;
				case 1:
					menuEntrenador();
					seleccionEntrenador(menu, fichEquipo, fichUsuarios);
					break;
				case 2:
					menuJugador();

					break;
				}
			}
		} while (userType != 4);
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

	private static int logIn(File fichUsuarios, int userType) {
		String user, passwd;
		boolean existe = false;
		int intentos = 3;

		do {
			System.out.println("LOG IN");
			System.out.print("User: ");
			user = Util.introducirCadena();
			System.out.print("Contraseña: ");
			passwd = Util.introducirCadena();
			int realizar = consulta(fichUsuarios, user, passwd);
			if (realizar == -1) {
				System.out.println("El usuario y la contraseña introducidos no coinciden o no existen");
				existe = true;
				intentos--;
				System.out.println("Te quedan " + intentos + " intentos");
			}
			if (intentos == 0) {
				return userType = 4;
			}
		} while (existe);

		return userType;

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

	private static void introducirEntrenadores(File fich) {
		int opc;
		ObjectOutputStream oos = null;
		try {
			if (fich.exists()) {
				oos = new MyObjectOutputStream(new FileOutputStream(fich, true));
			} else {
				oos = new ObjectOutputStream(new FileOutputStream(fich));
			}

			do {
				Usuarios entrenador = new Entrenador();
				entrenador.setDatos();
				oos.writeObject(entrenador);
				opc = Util.leerInt("¿Desea añadir mas entrenadores? 1=si/2=no", 1, 2);
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

	private static void seleccionAdmin(int menu, File fichEquipo, File fichUsuarios) {
		System.out.println("Que desea hacer");
		menu = Util.leerInt(1, 3);
		Usuarios admin = new Admin();
		switch (menu) {
		case 1:
			introducirEquipo(fichEquipo);
			break;
		case 2:
			introducirEntrenadores(fichUsuarios);
			break;
		case 3:
			System.out.println("Vuelva pronto " + admin.getUser());
			break;
		}
	}

//enzo
	private static void menuEntrenador() {
		System.out.println("-------MENU-----------");
		System.out.println("1.- Programar entrenamiento");
		System.out.println("2.- Añadir jugadores");
		System.out.println("3.- Comprobar información de jugadores");
		System.out.println("4.- Eliminar jugadores");
		System.out.println("5.- Lista de entrenamientos");
		System.out.println("0.- Salir");

	}

	public static void seleccionEntrenador(int menu, File fichEquipo, File fichUsuarios) {

		menu = Util.leerInt("¿Que desea hacer?", 0, 5);
		switch (menu) {

		case 1:
			programarEntrenamiento(fichEquipo, fichUsuarios);
			break;
		case 2:
			anadirJugadores(fichUsuarios);
			break;
		case 3:
			comprobarInfoJugador(fichUsuarios);
			break;
		case 4:
			eliminarJugadores(fichUsuarios);
			break;
		case 5:
			listaEntrenamiento(fichEquipo);
			break;
		case 0:
			break;
		}
	}

	private static void programarEntrenamiento(File fichEquipo, File fichUsuarios) {
		// TODO Auto-generated method stub

	}

	private static void anadirJugadores(File fichUsuarios) {
		int opc;
		ObjectOutputStream oos = null;
		try {
			if (fichUsuarios.exists()) {
				oos = new MyObjectOutputStream(new FileOutputStream(fichUsuarios, true));
			} else {
				oos = new ObjectOutputStream(new FileOutputStream(fichUsuarios));
			}

			do {
				Usuarios jugador = new Jugador();
				jugador.setDatos();
				oos.writeObject(jugador);
				opc = Util.leerInt("¿Desea añadir mas jugadores? 1=si/2=no", 1, 2);
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

	private static void comprobarInfoJugador(File fichUsuarios) {
		// TODO Auto-generated method stub

	}

	private static void eliminarJugadores(File fichUsuarios) {
		int pos = 0;
		String nombre;
		ArrayList<Jugador> jugador = new ArrayList<Jugador>();
		ObjectInputStream ois = null;
		try {
			System.out.println("Introduce el nombre del jugador: ");
			nombre = Util.introducirCadena();

			ois = new ObjectInputStream(new FileInputStream(fichUsuarios));
			pos = Util.calculoFichero(fichUsuarios);

			for (int i = 0; i < pos; i++) {
				Jugador J1 = new Jugador();
				J1 = (Jugador) ois.readObject();
				if (J1.getNombre().equalsIgnoreCase(nombre)) {
					jugador.remove(J1);
				}
			}
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listaEntrenamiento(File fichEquipo) {
		// TODO Auto-generated method stub

	}

//omar
	private static void menuJugador() {

	}

	private static int consulta(File fich, String user, String passwd) {
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(fich));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1; // Manejar el error de archivo no encontrado
		} catch (IOException e) {
			e.printStackTrace();
			return -1; // Manejar otros errores de entrada/salida
		}

		System.out.println("Introduce el DNI que quieres buscar");

		int index = 0;
		try {
			while (true) {
				Usuarios aux = (Usuarios) ois.readObject();
				if (aux.getUser().equalsIgnoreCase(user)) {
					if (aux.getContraseña().equalsIgnoreCase(passwd)) {
						return index;
					}
				}
				index++;
			}
		} catch (EOFException e) {
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return -1;
	}

	private static int comprobar(File fichUsuarios, int userType, String user, String passwd) {
		int tipoUsuario = consulta(fichUsuarios, user, passwd);
		int contador = 0;
		ObjectInputStream ois = null;

		try {
			while (true) {
				Usuarios aux = (Usuarios) ois.readObject();
				if (contador == tipoUsuario) {

				}
			}

		} catch (EOFException e) {
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return userType;
	}
}
