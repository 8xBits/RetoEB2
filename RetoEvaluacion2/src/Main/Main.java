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
import java.util.*;

import Equipo.Equipo;
import Entrenamiento.Entrenamiento;
import Usuarios.*;
import Utilidades.Util;
import Utilidades.MyObjectOutputStream;
import resources.Data;

public class Main {

    public static void main(String[] args) {

        File fichEquipo = new File("equipo.dat");
        File fichUsuarios = new File("usuarios.dat");

        // it should run only first time to create and fill files with data
        if (!fichEquipo.exists() || Util.calculoFichero(fichEquipo) == 0) {
            ArrayList<Equipo> equipoList = new ArrayList<>();
            Data.fillDataEquipos(equipoList);
            Util.arrayToFile(equipoList, fichEquipo);

            if (!fichUsuarios.exists() || Util.calculoFichero(fichUsuarios) == 0) {
                ArrayList<Usuarios> userList = new ArrayList<>();
                Data.fillDataEntrenadores(userList);
                Data.fillDataJugadores(userList);
                Util.arrayToFile(userList, fichUsuarios);
            }
        }
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
        boolean existe;
        try {
            if (fich.exists()) {
                oos = new MyObjectOutputStream(new FileOutputStream(fich, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(fich));
            }

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
                    //anadirJugadores(fichUsuarios);
                    anadirJugadores2(fichUsuarios, (Entrenador) entrenadorConectado);

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

    // done
    private static void programarEntrenamiento(File fichEquipo, Entrenador entrenador) {
        int opc;
        do {
            listaEntrenamiento(fichEquipo, entrenador);
            opc = Util.leerInt("0-Salir \n 1-añadir un entrenamiento \n 2-Modifcar un entrenamiento \n  3-Borrar un entrenamiento", 1, 2);
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
                for (Entrenamiento entra : equipo.getListaEntrenamiento()) {
                    entra.setDatosEntrenamiento();
                    equipo.addEntrenamiento(entra);
                }
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
        int posequipo=-1;
        ArrayList<Equipo> equipos = new ArrayList<>();
        Util.fileToArray(fichEquipo, equipos);
        System.out.println("Elige el código del entrenamiento :");
        int codigo = Util.leerInt();
        for (Equipo equipo : equipos) {
            if (equipo.getNombreEquipo().equalsIgnoreCase(entrenador.getNombreEquipo())) {
                posequipo=equipos.indexOf(equipo);
                for (Entrenamiento entra : equipo.getListaEntrenamiento()) {
                    if (entra.getCodigoEntrenamiento() == codigo) {
                        pos = equipo.getListaEntrenamiento().indexOf(entra);
                    }
                }
            }

        }
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

    private static void comprobarInfoJugador(File fichUsuarios) {
        int pos = 0;
        String username;
        ObjectInputStream ois = null;
        try {
            System.out.println("Introduce el username del jugador que deseas ver: ");
            username = Util.introducirCadena();

            ois = new ObjectInputStream(new FileInputStream(fichUsuarios));
            pos = Util.calculoFichero(fichUsuarios);

            for (int i = 0; i < pos; i++) {
                Usuarios user = (Usuarios) ois.readObject();
                if (user instanceof Jugador && !user.getUser().equalsIgnoreCase(username)) {
                    user.getDatos();
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
        //just to debug
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
            opc = Util.leerInt("Elege una opcion", 1, 5);
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
        do {
            System.out.println("\n Elege un dorsal libre que te gusta :");
            choice = Util.leerInt();
        } while (!dorsalNoLibre.contains(choice));
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

    public static void verInfoEquipo(File fichUser, File fich, Jugador jugadorConectado) {
        ArrayList<Equipo> equipoList = new ArrayList<>();
        ArrayList<Usuarios> userList = new ArrayList<>();
        ArrayList<Jugador> jugadorList = new ArrayList<>();
        Util.fileToArray(fich, equipoList);
        Util.fileToArray(fichUser, userList);

        for (Equipo equip : equipoList) {
            if (equip.getNombreEquipo().equalsIgnoreCase(jugadorConectado.getNombreEquipo())) {
                equip.getDatosEquipo();
                //  System.out.println("Lista de jugadores de equipo :");
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
//        /*Comparator comp = new Comparator() {
//            @Override
//            public int compare(Object jugador1, Object jugador2) {
//                return (((Jugador) jugador1).getDorsal())-(((Jugador) jugador2).getDorsal());
//            }
//        };*/
        Collections.sort(jugadorList);
        for (Jugador jug : jugadorList) {
            //jug.getDatos();
            System.out.println(jug.toString());
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
        //HASHMAP POR SUMA
        //	TRRE MAPP POR ODENARADA
        int suma = 0;

        ArrayList<Usuarios> userList = new ArrayList<>();
        ArrayList<Equipo> equipoList = new ArrayList<>();

        HashMap<String, Integer> equipoSumaGoles = new HashMap<>();
        TreeMap<Integer, ArrayList<String>> goalsByEquipos = new TreeMap<>(Collections.reverseOrder());

        Util.fileToArray(fichUser, userList);
        Util.fileToArray(fichEquipo, equipoList);
        //sumar goales by equipo
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
        //copiar hashmap to treemap sin perder datos
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