package Main;

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
				logIn();
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
				comprobarDorsal(fich);
				break;
			case 2:
				verInfoEquipo(fich);
				break;
			case 3:
				System.out.println("Hasta pronto..");
				break;
			}

		} while (opc != 3);
	}

	public static void comprobarDorsal(File fich) {
		int choice;
		Jugador jugador= new Jugador();
		Equipo equipo= new Equipo();
		ArrayList<Integer> dorsalNoLibre= new ArrayList<>();
		ArrayList<Jugador> jugadorList= new ArrayList<>();
			fileToArray(fich,jugadorList);
		for(Jugador jug :jugadorList) {
			if(jug.getNombreEquipo().equalsIgnoreCase(equipo.getNombreEquipo())) {
				dorsalNoLibre.add(jug.getDorsal());
			}
		}
		System.out.println("Dorsales Libres :  " );
		for(int i=0;i<26;i++) {
			if(!dorsalNoLibre.contains(i)) {
				System.out.println(i+" " );
			}
		}
		
		do {
			System.out.println("Elege un dorsal libre que te gusta :");
			choice = Util.leerInt();
		} while (dorsalNoLibre.contains(choice) );

	}

	public static void verInfoEquipo(File fich) {
		ArrayList<Equipo> equipoList= new ArrayList<>();
		Equipo equipo= new Equipo();
		fileToArray(fich,equipoList);
		for(Equipo equip :equipoList) {
			if(equip.getNombreEquipo().equalsIgnoreCase(equipo.getNombreEquipo())) {
				equip.getDatosEquipo();
			}
			
		}

	}
	
	  private static void arrayToFile(ArrayList<Jugador> empList, File fich) {
	        ObjectOutputStream oos = null;
	        try {
	            if (fich.exists()) {
	                fich.delete();
	            }
	            oos = new ObjectOutputStream(new FileOutputStream(fich));
	            for (Jugador emp : empList) {
	                oos.writeObject(emp);
	            }
	            empList.clear();
	            oos.close();

	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    private static void fileToArray(File fich, ArrayList<Jugador> empList) {
	        if (fich.exists()) {
	            ObjectInputStream ois;
	            try {
	                ois = new ObjectInputStream(new FileInputStream(fich));
	                int cuantos = Util.calculoFichero(fich);
	                for (int i = 0; i < cuantos; i++) {
	                	Jugador emp = (Jugador) ois.readObject();
	                    empList.add(emp);
	                }
	              /*  Empleado emp = (Empleado) ois.readObject();
	                while (emp != null) {
	                    arrayFich.add(emp);
	                    emp = (Empleado) ois.readObject();
	                }*/
	                ois.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	        }
	    }

}// class
