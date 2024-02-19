package Main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Formatter;

import Equipo.Equipo;
import Entrenamiento.Entrenamiento;
import Usuarios.*;
import Utilidades.Util;
import Utilidades.MyObjectOutputStream;

public class Main {

	public static void main(String[] args) {

		File fichEquipo = new File("equipo.dat");
		File fichUsuarios = new File("usuarios.dat");

		crearAdmin(fichUsuarios);
		launchNewSession(fichUsuarios, fichEquipo);

	}// main

	public static void launchNewSession(File fichUsuarios, File fichEquipo) {
		// usertype 0 es admin, el 1 es entrenador y el 2 jugador
		int userType = -99;
		do {
			int opcion = Util.leerInt("Desea iniciar sesion 3=si/4=no", 3, 4);
			if (opcion == 3) {
				Usuarios connectedUser = consulta(fichUsuarios);
				userType = logIn(connectedUser);
				// int menu = 0;
				switch (userType) {
				case -1:
					// just debuging
					System.out.println("returned user by consulta function is null and logIn function returned -1");
				case 0:
					seleccionAdmin(fichEquipo, fichUsuarios, (Admin) connectedUser);
					break;
				case 1:
					seleccionEntrenador(fichEquipo, fichUsuarios, (Entrenador) connectedUser);
					break;
				case 2:
					seleccionJugador(fichUsuarios, fichEquipo, (Jugador) connectedUser);
					break;
				}
			}
		} while (userType > 3);
	}

	private static void crearAdmin(File fich) {
		// solo ejemplo
		ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
		Util.fileToArray(fich, usuarios);
		Usuarios admin = new Admin("Admin", "admin", "admin", 1);
		usuarios.add(admin);
		Util.arrayToFile(usuarios, fich);

//        ObjectOutputStream oos = null;
//        if (!fich.exists()) {
//            try {
//                oos = new ObjectOutputStream(new FileOutputStream(fich));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Usuarios admin = new Admin("Admin", "admin", "admin", 1);
//            try {
//                oos.writeObject(admin);
//            } catch (IOException e) {
//                System.out.println("Error al crear el administrador");
//            }
//        }
	}

	private static int logIn(Usuarios usuario) {
		int userType = -1;
		if (usuario == null) {
			System.out.println("El usuario y la contraseña introducidos no coinciden o no existen");
		} else if (usuario instanceof Admin) {
			userType = 0;
		} else if (usuario instanceof Entrenador) {
			userType = 1;
		} else if (usuario instanceof Jugador) {
			userType = 2;
		}
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

	// alder
	private static void menuAdmin() {

		System.out.println("MENU ADMIN");
		System.out.println("1.- Introducir equipo");
		System.out.println("2.- Introducir entrenador");
		System.out.println("3.- Salir");
	}

	private static void seleccionAdmin(File fichEquipo, File fichUsuarios, Usuarios adminConnectado) {
		System.out.println("Bienvenidos " + adminConnectado.getNombre());
		int menu;
		do {
			menuAdmin();
			System.out.println("Que desea hacer");
			menu = Util.leerInt(1, 3);
			switch (menu) {
			case 1:
				introducirEquipo(fichEquipo);
				break;
			case 2:
				introducirEntrenadores(fichUsuarios);
				break;
			case 3:
				System.out.println("Vuelva pronto " + adminConnectado.getUser());
				launchNewSession(fichUsuarios, fichEquipo);
				break;
			}
		} while (menu != 3);

	}

	// enzo
	private static void menuEntrenador() {
		System.out.println("-------MENU-----------");
		System.out.println("1.- Programar entrenamiento");
		System.out.println("2.- Añadir jugadores");
		System.out.println("3.- Comprobar información de jugadores");
		System.out.println("4.- Eliminar jugadores");
		System.out.println("5.- Lista de entrenamientos");
		System.out.println("0.- Salir");

	}

	public static void seleccionEntrenador(File fichEquipo, File fichUsuarios, Usuarios entrenadorConectado) {
		System.out.println("Bienvenidos " + entrenadorConectado.getNombre());

		int menu;
		do {
			menuEntrenador();
			menu = Util.leerInt("¿Que desea hacer?", 0, 5);
			switch (menu) {

			case 1:
				programarEntrenamiento(fichEquipo, (Entrenador) entrenadorConectado);
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
				listaEntrenamiento(fichEquipo, (Entrenador) entrenadorConectado);
				break;
			case 0:
				launchNewSession(fichUsuarios, fichEquipo);
				break;
			}

		} while (menu != 0);

	}
	//done 
	private static void programarEntrenamiento(File fichEquipo, Entrenador entrenador) {
		// just testing
		ArrayList<Equipo> equipos = new ArrayList<>();
		Util.fileToArray(fichEquipo, equipos);
		for (Equipo equipo : equipos) {
			if (equipo.getNombreEquipo().equalsIgnoreCase(entrenador.getNombreEquipo())) {
				// Entrenamiento ent0 = new Entrenamiento(LocalDateTime.of(2024, 2, 18, 10, 0),
				// LocalDateTime.of(2024, 2, 18, 12, 30) ,"test Material");
				// Entrenamiento ent1 = new Entrenamiento(LocalDateTime.of(2024, 2, 20, 10, 0),
				// LocalDateTime.of(2024, 2, 20, 12, 30) ,"test Material");
				// Entrenamiento ent2 = new Entrenamiento(LocalDateTime.of(2024, 2, 23, 18, 30),
				// LocalDateTime.of(2024, 2, 23, 21, 30) ,"Material2");
				// euip.addEntrenamiento(ent1);
				// euip.addEntrenamiento(ent0);
				for (Entrenamiento entra : equipo.getListaEntrenamiento()) {
					entra.setDatosEntrenamiento();
				}

			}
		}

		Util.arrayToFile(equipos, fichEquipo);
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
	// done just to give a good format 
	private static void listaEntrenamiento(File fichEquipo, Entrenador entrenador) {
		// entrenador.getDatos();
		LocalDateTime myDate = LocalDateTime.now();
		ArrayList<Equipo> equipoList = new ArrayList<>();
		Util.fileToArray(fichEquipo, equipoList);
		System.out.println(equipoList.size());
		for (Equipo equipo : equipoList) {

			if (equipo.getNombreEquipo().equalsIgnoreCase(entrenador.getNombreEquipo())) {
				for (Entrenamiento ent : equipo.getListaEntrenamiento()) {
					// System.out.println(equipo.getListaEntrenamiento().size());
					if (ent.getFetchaHoraInicio().isAfter(myDate)) {
						ent.getDatosEntrenamiento();
						System.out.println("Lugar : " + equipo.getEstadio());
					}

				}
			}
		}
	}

	// omar
	private static void menuJugador() {
		System.out.println("MENU Jugador");
		System.out.println("1.- Combrobar dorsal (ver disponibles)");
		System.out.println("2.- Ver info equipo ");
		System.out.println("3.- Menu Principal");
		System.out.println("4.- Cambiar contraseña ");
	}

	private static void seleccionJugador(File fichUsuarios, File fichEquipo, Jugador jugadorConectado) {
		System.out.println("Bienvenidos " + jugadorConectado.getNombre());

		int opc;
		do {
			menuJugador();
			opc = Util.leerInt("Elege una opcion", 1, 3);
			switch (opc) {
			case 1:
				comprobarDorsal(fichUsuarios, jugadorConectado);
				break;
			case 2:
				verInfoEquipo(fichEquipo, jugadorConectado);
				break;
			case 3:
				System.out.println("Hasta pronto " + jugadorConectado.getNombre());
				// menuPrincipal();
				launchNewSession(fichUsuarios, fichEquipo);
				break;
			}
		} while (opc != 3);
	}

	public static void comprobarDorsal(File fich, Jugador jugadorConectado) {
		int choice;
		int pos = -1;
		int maxJugador = 26;
		ArrayList<Integer> dorsalNoLibre = new ArrayList<>();
		ArrayList<Usuarios> usuList = new ArrayList<>();

		Util.fileToArray(fich, usuList);
		for (Usuarios jug : usuList) {
			if (jug instanceof Jugador) {
				if (((Jugador) jug).getNombreEquipo().equalsIgnoreCase(jugadorConectado.getNombreEquipo())) {
					dorsalNoLibre.add(((Jugador) jug).getDorsal());
					pos = usuList.indexOf(jug);
				}
			}
		}
		System.out.println("Tu dorsales es :  " + jugadorConectado.getDorsal());

		System.out.println("Dorsales Libres :  ");
		for (int i = 1; i < maxJugador; i++) {
			if (!dorsalNoLibre.contains(i)) {
				System.out.print("[" + i + "]   ");
			}
		}
		do {
			System.out.println("\n Elege un dorsal libre que te gusta :");
			choice = Util.leerInt();
		} while (dorsalNoLibre.contains(choice));
		// int pos =usuList.indexOf(jugadorConectado);
		// System.out.println("My size " +usuList.size());
		// System.out.println("My pos index " +pos);
		if (pos != -1) {
			((Jugador) usuList.get(pos)).setDorsal(choice);
			System.out.println("Tu nuevo dorsal es : " + choice);
		}
		Util.arrayToFile(usuList, fich);
		// System.out.println("Going Home ");
	}

	public static void verInfoEquipo(File fich, Jugador jugadorConectado) {
		ArrayList<Equipo> equipoList = new ArrayList<>();
		Util.fileToArray(fich, equipoList);
		for (Equipo equip : equipoList) {
			if (equip.getNombreEquipo().equalsIgnoreCase(jugadorConectado.getNombreEquipo())) {
				equip.getDatosEquipo();
			}
		}
	}

	private static Usuarios consulta(File fichUsuarios) {
		ObjectInputStream ois = null;
		String user, passwd;
		int intentos = 3;
		boolean inicioSesion = false;
		do {
			if (intentos != 0) {
				System.out.println("LOG IN");
				System.out.print("User: ");
				user = Util.introducirCadena();
				System.out.print("Contraseña: ");
				passwd = Util.introducirCadena();
				try {
					ois = new ObjectInputStream(new FileInputStream(fichUsuarios));
					Usuarios aux = (Usuarios) ois.readObject();
					while (aux != null) {
						if (aux.getUser().equalsIgnoreCase(user)) {
							if (aux.getContraseña().equalsIgnoreCase(passwd)) {
								// just for testing
								// System.out.println(("The user is : " + aux.getUser() + " Password is :" +
								// aux.getContraseña()));
								return aux;
							}
						}
						aux = (Usuarios) ois.readObject();
					}
				} catch (EOFException e) {
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (ois != null) {
							ois.close();
							inicioSesion = true;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("No tienes mas intentos");
			}
		} while (intentos > 0 && !inicioSesion);

		return null;
	}
}