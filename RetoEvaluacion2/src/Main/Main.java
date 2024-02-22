package Main;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import Entrenamiento.Entrenamiento;
import Equipo.Equipo;
import Usuarios.*;
import Utilidades.ExcepcionLogIn;
import Utilidades.ExcepcionUser;
import Utilidades.MyObjectOutputStream;
import Utilidades.Util;
import resources.Data;

public class Main {

	public static void main(String[] args) {

		File fichEquipo = new File("equipo.dat");
		File fichUsuarios = new File("usuarios.dat");

		initializeFiles(fichEquipo, fichUsuarios);

		createAdmin(fichUsuarios);
		launchNewSession(fichUsuarios, fichEquipo);

	}

	private static void initializeFiles(File fichEquipo, File fichUsuarios) {
		if (!fichEquipo.exists() || Util.calculoFichero(fichEquipo) == 0) {
			ArrayList<Equipo> equipoList = new ArrayList<>();
			Data.fillDataEquipos(equipoList);
			Util.arrayToFile(equipoList, fichEquipo);
		}
		if (!fichUsuarios.exists() || Util.calculoFichero(fichUsuarios) == 0) {
			ArrayList<Usuarios> userList = new ArrayList<>();
			Data.fillDataEntrenadores(userList);
			Data.fillDataJugadores(userList);
			Util.arrayToFile(userList, fichUsuarios);
		}
	}

	private static void createAdmin(File fich) {
		ArrayList<Usuarios> usuarios = new ArrayList<>();
		Util.fileToArray(fich, usuarios);
		Usuarios admin = new Admin("Admin", "admin", "admin", 1);
		usuarios.add(admin);
		Util.arrayToFile(usuarios, fich);
	}

	private static int logIn(Usuarios usuario) {
		if (usuario instanceof Admin) {
			return 0;
		} else if (usuario instanceof Entrenador) {
			return 1;
		} else if (usuario instanceof Jugador) {
			return 2;
		}
		return -1;
	}

	private static void launchNewSession(File fichUsuarios, File fichEquipo) {
		int userType;
		do {
			int opcion = Util.leerInt("Desea iniciar sesion 3=si/4=no", 3, 4);
			if (opcion == 3) {
				Usuarios connectedUser = consulta(fichUsuarios, fichEquipo);
				userType = logIn(connectedUser);

				switch (userType) {
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
			} else {
				userType = 4;
			}
		} while (userType > 3);
	}

	private static void seleccionAdmin(File fichEquipo, File fichUsuarios, Admin adminConnectado) {
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

	private static void introducirEquipo(File fich) {
		try (ObjectOutputStream oos = getOutputStream(fich)) {
			int opc;
			do {
				Equipo equipo = new Equipo();
				equipo.setDatosEquipo();
				oos.writeObject(equipo);
				opc = Util.leerInt("¿Desea añadir mas equipos? 1=si/2=no", 1, 2);
			} while (opc == 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ObjectOutputStream getOutputStream(File fich) throws IOException {
		return fich.exists() ? new MyObjectOutputStream(new FileOutputStream(fich, true))
				: new ObjectOutputStream(new FileOutputStream(fich));
	}

	private static void introducirEntrenadores(File fich) {
		try (ObjectOutputStream oos = getOutputStream(fich)) {
			int opc;
			boolean existe = false;
			do {
				Usuarios entrenador = new Entrenador();
				entrenador.setDatos();
				existe = ((Entrenador) entrenador).comprobar(fich, ((Entrenador) entrenador).getNombreEquipo());
				if (!existe) {
					System.out.println("El equipo No existe.");
				} else {
					oos.writeObject(entrenador);
				}

				opc = Util.leerInt("¿Desea añadir mas entrenadores? 1=si/2=no", 1, 2);
			} while (opc == 1);
		} catch (IOException e) {
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
				comprobarInfoJugador(fichUsuarios, entrenadorConectado, fichEquipo);
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

	// done
	private static void programarEntrenamiento(File fichEquipo, Entrenador entrenador) {
		int opc;
		do {
			listaEntrenamiento(fichEquipo, entrenador);
			opc = Util.leerInt(
					"0-Salir \n 1-añadir un entrenamiento \n 2-Modifcar un entrenamiento \n  3-Borrar un entrenamiento",
					0, 3);
			switch (opc) {
			case 0:
				break;
			case 1:
				anadirEntrenamiento(fichEquipo, entrenador);
				break;
			case 2:
				modificarEntrenamiento(fichEquipo, entrenador);
				break;
			case 3:
				borrarEntrenamiento(fichEquipo, entrenador);
				break;
			}
		} while (opc != 0);
	}

	public static void anadirEntrenamiento(File fichEquipo, Entrenador entrenador) {
		ArrayList<Equipo> equipos = new ArrayList<>();
		Util.fileToArray(fichEquipo, equipos);
		for (Equipo equipo : equipos) {
			if (equipo.getNombreEquipo().equalsIgnoreCase(entrenador.getNombreEquipo())) {
				Entrenamiento entra = new Entrenamiento();
				entra.setDatosEntrenamiento();
				equipo.addEntrenamiento(entra);
			}
		}
		Util.arrayToFile(equipos, fichEquipo);
	}

	public static void modificarEntrenamiento(File fichEquipo, Entrenador entrenador) {
		boolean existe = false;
		ArrayList<Equipo> equipos = new ArrayList<>();
		Util.fileToArray(fichEquipo, equipos);
		System.out.println("Elige el código del entrenamiento :");
		int codigo = Util.leerInt();
		for (Equipo equipo : equipos) {
			if (equipo.getNombreEquipo().equalsIgnoreCase(entrenador.getNombreEquipo())) {
				for (Entrenamiento entra : equipo.getListaEntrenamiento()) {
					if (entra.getCodigoEntrenamiento() == codigo) {
						entra.setDatosEntrenamiento();
						existe = true;
					}
				}
			}
		}
		if (!existe) {
			System.out.println("Este entrenamiento no existe");
		}
		Util.arrayToFile(equipos, fichEquipo);
	}

	public static void borrarEntrenamiento(File fichEquipo, Entrenador entrenador) {
		int pos = -1;
		int posequipo = -1;
		ArrayList<Equipo> equipos = new ArrayList<>();
		Util.fileToArray(fichEquipo, equipos);
		System.out.println("Elige el código del entrenamiento :");
		int codigo = Util.leerInt();
		for (Equipo equipo : equipos) {
			if (equipo.getNombreEquipo().equalsIgnoreCase(entrenador.getNombreEquipo())) {
				posequipo = equipos.indexOf(equipo);
				for (Entrenamiento entra : equipo.getListaEntrenamiento()) {
					if (entra.getCodigoEntrenamiento() == codigo) {
						pos = equipo.getListaEntrenamiento().indexOf(entra);
					}
				}
			}

		}
		// no funciona
		if (pos != -1) {
			equipos.get(posequipo).getListaEntrenamiento().remove(pos);
			System.out.println("Entrenamiento borrado");
		}
		if (pos == -1) {
			System.out.println("Este entrenamiento no existe");
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
//comprobar que el dorsal no esta libre
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

	private static void anadirJugadores2(File fichUsuarios, Entrenador entrenador) {
		int opc;
		ArrayList<Usuarios> userList = new ArrayList<Usuarios>();
		Util.fileToArray(fichUsuarios, userList);
		Data.fillDataJugadores(userList);
		Util.arrayToFile(userList, fichUsuarios);

	}

	private static void comprobarInfoJugador(File fichUsuarios, Usuarios entrenador, File fichEquipos) {
		int pos = 0, pos2 = 0;
		ObjectInputStream ois = null, ois2 = null;
		Equipo equipo = new Equipo();
		TreeMap<Integer, Jugador> orden = new TreeMap<>(Comparator.naturalOrder());
		Usuarios user = new Usuarios();
		try {

			ois = new ObjectInputStream(new FileInputStream(fichUsuarios));
			pos = Util.calculoFichero(fichUsuarios);
			ois2 = new ObjectInputStream(new FileInputStream(fichEquipos));
			pos2 = Util.calculoFichero(fichEquipos);

			while (!equipo.getNombreEquipo().equalsIgnoreCase(((Entrenador) entrenador).getNombreEquipo())) {
				equipo = (Equipo) ois2.readObject();
			}
			equipo.getDatosEquipo();
			System.out.println("------------");
			System.out.println("Entrenador: ");
			entrenador.getDatos();
			System.out.println("------------");
			for (int i = 0; i < pos; i++) {
				user = (Usuarios) ois.readObject();
				if (user instanceof Jugador && ((Jugador) user).getNombreEquipo()
						.equalsIgnoreCase(((Entrenador) entrenador).getNombreEquipo())) {
					orden.put(((Jugador) user).getDorsal(), (Jugador) user);
				}
			}

			for (Usuarios usuarios : orden.values()) {
				System.out.println(((Jugador) usuarios).getUser());
				usuarios.getDatos();
				System.out.println("------------");
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

	// no elimina jugador
	private static void eliminarJugadores(File fichUsuarios) {
	    String nombre;
	    ObjectInputStream ois = null;
	    ObjectOutputStream oos = null;
	    File auxFile = new File("auxFile.txt");

	    try {
	        System.out.println("Introduce el nombre de usuario del jugador: ");
	        nombre = Util.introducirCadena();

	        ois = new ObjectInputStream(new FileInputStream(fichUsuarios));
	        oos = new ObjectOutputStream(new FileOutputStream(auxFile));

	        while (true) {
	            try {
	                Usuarios jugador = (Usuarios) ois.readObject();

	                // Compare usernames case-insensitively
	                if (!jugador.getUser().equalsIgnoreCase(nombre.trim())) {
	                    oos.writeObject(jugador);
	                }
	            } catch (EOFException e) {
	                break; // End of file
	            }
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ois != null) ois.close();
	            if (oos != null) oos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Replace the original file with the modified one
	        fichUsuarios.delete();
	        auxFile.renameTo(fichUsuarios);
	    }
	}


	// done just to give a good format
	private static void listaEntrenamiento(File fichEquipo, Entrenador entrenador) {
		// just to debug
		entrenador.getDatos();
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
		System.out.println("3.- Cambiar contraseña ");
		System.out.println("4.- Goleador del equipo ");
		System.out.println("5.- BestTeam del equipo ");
		System.out.println("0.- Salir ");
	}

	private static void seleccionJugador(File fichUsuarios, File fichEquipo, Jugador jugadorConectado) {
		System.out.println("Bienvenidos " + jugadorConectado.getNombre());

		int opc;
		do {
			menuJugador();
			opc = Util.leerInt("Elege una opcion", 0, 5);
			switch (opc) {
			case 1:
				comprobarDorsal(fichUsuarios, jugadorConectado);
				break;
			case 2:
				verInfoEquipo(fichUsuarios, fichEquipo, jugadorConectado);
				break;
			case 3:
				changeMyPassword(fichUsuarios, jugadorConectado);
				break;
			case 4:
				topScorerPlayerOfTeam(fichUsuarios, jugadorConectado);
				break;
			case 5:
				topScorerTeamOrderd(fichUsuarios, fichEquipo);
				break;
			case 0:
				System.out.println("Hasta pronto " + jugadorConectado.getNombre());
				// Menu principal;
				launchNewSession(fichUsuarios, fichEquipo);
				break;
			}
		} while (opc != 0);
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
		// comprobar que el dorsal introducido esta libre
		do {
			System.out.println("\n Elege un dorsal libre que te gusta :");
			choice = Util.leerInt();
		} while (!dorsalNoLibre.contains(choice));
		// int pos =usuList.indexOf(jugadorConectado);
		// System.out.println("My size " +usuList.size());
		// System.out.println("My pos index " +pos);
		if (pos != -1 && ((Jugador) usuList.get(pos)).getDorsal() != choice) {
			((Jugador) usuList.get(pos)).setDorsal(choice);
			System.out.println("Tu nuevo dorsal es : " + choice);
		}
		Util.arrayToFile(usuList, fich);
		// System.out.println("Going Home ");
	}

	public static void verInfoEquipo(File fichUser, File fich, Jugador jugadorConectado) {
		ArrayList<Equipo> equipoList = new ArrayList<>();
		ArrayList<Usuarios> userList = new ArrayList<>();
		ArrayList<Jugador> jugadorList = new ArrayList<>();
		Util.fileToArray(fich, equipoList);
		Util.fileToArray(fichUser, userList);

		for (Equipo equip : equipoList) {
			if (equip.getNombreEquipo().equalsIgnoreCase(jugadorConectado.getNombreEquipo())) {
				equip.getDatosEquipo();
				// System.out.println("Lista de jugadores de equipo :");
			}
		}
		System.out.println("-------");
		System.out.println("Entrenadores: ");
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) instanceof Entrenador) {
				if (((Entrenador) userList.get(i)).getNombreEquipo()
						.equalsIgnoreCase(jugadorConectado.getNombreEquipo())) {
					userList.get(i).getDatos();
					System.out.println("-------");
				}
			}
		}
		// los jugadores ordenados por dorsal
		for (Usuarios jugador : userList) {
			if (jugador instanceof Jugador) {
				if (((Jugador) jugador).getNombreEquipo().equalsIgnoreCase(jugadorConectado.getNombreEquipo())) {
					jugadorList.add((Jugador) jugador);
				}
			}
		}
		Collections.sort(jugadorList);
		for (Jugador jug : jugadorList) {
			System.out.println(jug.toString());
			System.out.println("-------");
		}
	}

	// this function is working with all users now
	public static void changeMyPassword(File fich, Jugador jugadorConectado) {
		String strOldPass, strNewPass, strNewPass2;
		ArrayList<Usuarios> userList = new ArrayList<>();

		Util.fileToArray(fich, userList);
		for (Usuarios userFromList : userList) {
			// if (userConnect instanceof Jugador) {
			if (jugadorConectado.getUser().equals(userFromList.getUser())) {
				System.out.println("Introduce tu antigua contraseña :");
				strOldPass = Util.introducirCadena();
				if (userFromList.getContraseña().equals(strOldPass)) {
					System.out.println("Introduce tu nueva contraseña :");
					strNewPass = Util.introducirCadena();
					System.out.println("ReIntroduce tu nueva contraseña :");
					strNewPass2 = Util.introducirCadena();
					if (strNewPass.equals(strNewPass2)) {
						userFromList.setContraseña(strNewPass);
					}
				}

			}
			// }
		}
		Util.arrayToFile(userList, fich);
	}

	public static void topScorerTeamOrderd(File fichUser, File fichEquipo) {
		// HASHMAP POR SUMA
		// TRRE MAPP POR ODENARADA
		int suma = 0;

		ArrayList<Usuarios> userList = new ArrayList<>();
		ArrayList<Equipo> equipoList = new ArrayList<>();

		HashMap<String, Integer> equipoSumaGoles = new HashMap<>();
		TreeMap<Integer, ArrayList<String>> goalsByEquipos = new TreeMap<>(Collections.reverseOrder());

		Util.fileToArray(fichUser, userList);
		Util.fileToArray(fichEquipo, equipoList);
		// sumar goales by equipo
		for (Equipo equipo : equipoList) {
			for (Usuarios users : userList) {
				if (users instanceof Jugador) {
					if (equipo.getNombreEquipo().equalsIgnoreCase(((Jugador) users).getNombreEquipo())) {
						equipoSumaGoles.putIfAbsent(((Jugador) users).getNombreEquipo(), 0);
						suma = equipoSumaGoles.get(((Jugador) users).getNombreEquipo()) + ((Jugador) users).getGoles();
						equipoSumaGoles.put(((Jugador) users).getNombreEquipo(), suma);
					}
				}
			}
		}
		// copiar hashmap to treemap sin perder datos
		for (Map.Entry<String, Integer> entry : equipoSumaGoles.entrySet()) {
			String equipoName = entry.getKey();
			int sumaGoals = entry.getValue();

			if (goalsByEquipos.containsKey(sumaGoals)) {
				goalsByEquipos.get(sumaGoals).add(equipoName);
			} else {
				ArrayList<String> equipos = new ArrayList<>();
				equipos.add(equipoName);
				goalsByEquipos.put(sumaGoals, equipos);
			}
		}
		// Mostrar TreeMap ordenad
		for (Map.Entry<Integer, ArrayList<String>> entry : goalsByEquipos.entrySet()) {
			for (String playerName : entry.getValue()) {
				System.out.println(playerName + ": " + entry.getKey() + " goals");
			}
		}
	}

	public static void topScorerPlayerOfTeam(File fich, Jugador jugadorConectado) {
		int max = 0, pos = -1;
		boolean found = false;
		ArrayList<Usuarios> userList = new ArrayList<>();

		Util.fileToArray(fich, userList);
		for (Usuarios userFromList : userList) {
			if (jugadorConectado.getUser().equals(userFromList.getUser())) {
				if (userFromList instanceof Jugador) {
					if (((Jugador) userFromList).getGoles() > max) {
						max = ((Jugador) userFromList).getGoles();
						pos = userList.indexOf(userFromList);
						found = true;
					}
				}
			}
		}
		if (!found) {
			System.out.println("No hay ninguna jugador con mas goales en :" + jugadorConectado.getNombreEquipo());
		} else {
			System.out.println("El jogador con mas goales  es : " + jugadorConectado.getNombreEquipo() + " is :");
			((Jugador) userList.get(pos)).getDatos();
		}
	}

	private static Usuarios consulta(File fichUsuarios, File fichEquipos) {
		ObjectInputStream ois = null;
		String user, passwd;
		boolean inicioSesion = false;

		do {
			System.out.println("LOG IN");
			System.out.print("User: ");
			user = Util.introducirCadena();
			System.out.print("Contraseña: ");
			passwd = Util.introducirCadena();

			try {
				ois = new ObjectInputStream(new FileInputStream(fichUsuarios));
				Usuarios aux;

				while (true) {
					try {
						aux = (Usuarios) ois.readObject();
						if (aux == null) {
							break; // Fin del archivo
						}

						if (aux.getUser().equalsIgnoreCase(user)) {
							if (aux.getContraseña().equals(passwd)) {
								return aux; // Usuario y contraseña coinciden
							} else {
								throw new ExcepcionLogIn("El usuario y la contraseña no coinciden");
							}
						}
					} catch (EOFException e) {
						break; // Fin del archivo
					}
				}
				throw new ExcepcionUser("El usuario y la contraseña no coinciden");
			} catch (ClassNotFoundException | IOException | ExcepcionLogIn | ExcepcionUser e) {
				if (e instanceof ExcepcionLogIn) {
					System.out.println("El usuarios y la contraseña no coinciden");
					launchNewSession(fichUsuarios, fichEquipos);
				} else if (e instanceof ExcepcionUser) {
					System.out.println("El usuario no existe");
					launchNewSession(fichUsuarios, fichEquipos);
				}
			} finally {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				inicioSesion = true;
			}
		} while (!inicioSesion);

		return null;
	}

}