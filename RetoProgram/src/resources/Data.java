package resources;

import Entrenamiento.Entrenamiento;
import Equipo.Equipo;
import Usuarios.Jugador;
import Usuarios.Usuarios;
import Usuarios.Entrenador;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static Usuarios.CargoEntrenador.PRIMER_ENTRENADOR;
import static Usuarios.CargoEntrenador.SEGUNDO_ENTRENADOR;


public class Data {

    public static void fillDataEquipos(ArrayList<Equipo> equipoList) {

        Equipo equipo1 = new Equipo("Athletic Bilbao", "San Mamés", 8);
        Equipo equipo2 = new Equipo("Athletico Madrid", "Wanda Metropolitano", 11);
        Equipo equipo3 = new Equipo("FC Barcelona", "Camp Nou", 26);
        Equipo equipo4 = new Equipo("Real Madrid", "Santiago Bernabéu", 34);

        Entrenamiento entrenamiento1 = new Entrenamiento(1, LocalDateTime.of(2024, 2, 26, 10, 0), LocalDateTime.of(2024, 2, 26, 12, 0), "Balls");
        Entrenamiento entrenamiento2 = new Entrenamiento(2, LocalDateTime.of(2024, 2, 28, 11, 0), LocalDateTime.of(2024, 2, 26, 13, 0), "Cones");
        Entrenamiento entrenamiento3 = new Entrenamiento(4, LocalDateTime.of(2024, 3, 2, 16, 0), LocalDateTime.of(2024, 3, 2, 18, 0), "Cones");
        Entrenamiento entrenamiento4 = new Entrenamiento(3, LocalDateTime.of(2024, 3, 5, 15, 0), LocalDateTime.of(2024, 3, 5, 17, 0), "Balls");
        equipo1.addEntrenamiento(entrenamiento1);
        equipo1.addEntrenamiento(entrenamiento2);
        equipo1.addEntrenamiento(entrenamiento3);
        equipo1.addEntrenamiento(entrenamiento4);

        Entrenamiento entrenamiento5 = new Entrenamiento(5, LocalDateTime.of(2024, 2, 29, 9, 0), LocalDateTime.of(2024, 2, 29, 11, 0), "Balls");
        Entrenamiento entrenamiento6 = new Entrenamiento(6, LocalDateTime.of(2024, 3, 2, 10, 0), LocalDateTime.of(2024, 3, 2, 12, 0), "Cones");
        Entrenamiento entrenamiento7 = new Entrenamiento(7, LocalDateTime.of(2024, 3, 4, 16, 0), LocalDateTime.of(2024, 3, 4, 18, 0), "Balls");
        Entrenamiento entrenamiento8 = new Entrenamiento(8, LocalDateTime.of(2024, 3, 6, 17, 0), LocalDateTime.of(2024, 3, 6, 19, 0), "Cones");
        equipo2.addEntrenamiento(entrenamiento5);
        equipo2.addEntrenamiento(entrenamiento6);
        equipo2.addEntrenamiento(entrenamiento7);
        equipo2.addEntrenamiento(entrenamiento8);

        Entrenamiento entrenamiento9 = new Entrenamiento(9, LocalDateTime.of(2024, 3, 1, 9, 0), LocalDateTime.of(2024, 3, 1, 11, 0), "Balls");
        Entrenamiento entrenamiento10 = new Entrenamiento(10, LocalDateTime.of(2024, 3, 3, 10, 0), LocalDateTime.of(2024, 3, 3, 12, 0), "Cones");
        Entrenamiento entrenamiento11 = new Entrenamiento(11, LocalDateTime.of(2024, 3, 5, 16, 0), LocalDateTime.of(2024, 3, 5, 18, 0), "Balls");
        Entrenamiento entrenamiento12 = new Entrenamiento(12, LocalDateTime.of(2024, 3, 7, 17, 0), LocalDateTime.of(2024, 3, 7, 19, 0), "Cones");
        equipo3.addEntrenamiento(entrenamiento9);
        equipo3.addEntrenamiento(entrenamiento10);
        equipo3.addEntrenamiento(entrenamiento11);
        equipo3.addEntrenamiento(entrenamiento12);

        Entrenamiento entrenamiento13 = new Entrenamiento(13, LocalDateTime.of(2024, 2, 29, 9, 0), LocalDateTime.of(2024, 2, 29, 11, 0), "Balls");
        Entrenamiento entrenamiento14 = new Entrenamiento(14, LocalDateTime.of(2024, 3, 2, 10, 0), LocalDateTime.of(2024, 3, 2, 12, 0), "Cones");
        Entrenamiento entrenamiento15 = new Entrenamiento(15, LocalDateTime.of(2024, 3, 5, 16, 0), LocalDateTime.of(2024, 3, 5, 18, 0), "Balls");
        Entrenamiento entrenamiento16 = new Entrenamiento(16, LocalDateTime.of(2024, 3, 7, 17, 0), LocalDateTime.of(2024, 3, 7, 19, 0), "Cones");
        equipo4.addEntrenamiento(entrenamiento13);
        equipo4.addEntrenamiento(entrenamiento14);
        equipo4.addEntrenamiento(entrenamiento15);
        equipo4.addEntrenamiento(entrenamiento16);


        equipoList.add(equipo1);
        equipoList.add(equipo2);
        equipoList.add(equipo3);
        equipoList.add(equipo4);

        //Entrenamiento
    }


    public static void fillDataEntrenadores(ArrayList<Usuarios> userList) {
        //Athletic Club de Bilbao
        Entrenador ent1 = new Entrenador("Marcelino García Toral", "mgarcia", "password1", "Athletic Bilbao", PRIMER_ENTRENADOR);
        Entrenador ent2 = new Entrenador("Javier Calleja", "jcalleja", "password2", "Athletic Bilbao", SEGUNDO_ENTRENADOR);
        //Atlético Madrid
        Entrenador ent3 = new Entrenador("Diego Simeone", "dsimeone", "password1", "Athletic Madrid", PRIMER_ENTRENADOR);
        Entrenador ent4 = new Entrenador("Germán Burgos", "gburgos", "password2", "Athletic Madrid", SEGUNDO_ENTRENADOR);
        //Barcelona
        Entrenador ent5 = new Entrenador("Xavi Hernandez", "xhernandez", "password1", "FC Barcelona", PRIMER_ENTRENADOR);
        Entrenador ent6 = new Entrenador("Sergio Busquets", "sbusquets", "password2", "FC Barcelona", SEGUNDO_ENTRENADOR);
        //Real Madrid
        Entrenador ent7 = new Entrenador("Carlo Ancelotti", "cancelotti", "password1", "Real Madrid", PRIMER_ENTRENADOR);
        Entrenador ent8 = new Entrenador("David Bettoni", "dbettoni", "password2", "Real Madrid", SEGUNDO_ENTRENADOR);

        userList.add(ent1);
        userList.add(ent2);
        userList.add(ent3);
        userList.add(ent4);
        userList.add(ent5);
        userList.add(ent6);
        userList.add(ent7);
        userList.add(ent8);
    }

    public static void fillDataJugadores(ArrayList<Usuarios> userList) {
        //
        Jugador player1 = new Jugador("Iñaki Williams", "iwilliams", "password123", "Athletic Bilbao", 9, 65, 20);
        Jugador player2 = new Jugador("Unai Simón", "usimon", "password456", "Athletic Bilbao", 1, 0, 0);
        Jugador player3 = new Jugador("Yeray Álvarez", "yalvarez", "password789", "Athletic Bilbao", 5, 2, 1);
        Jugador player4 = new Jugador("Iker Muniain", "imuniain", "passwordabc", "Athletic Bilbao", 10, 40, 30);
        Jugador player5 = new Jugador("Raúl García", "rgarcia", "passworddef", "Athletic Bilbao", 22, 50, 25);
        Jugador player6 = new Jugador("Dani García", "dgarcia", "passwordghi", "Athletic Bilbao", 14, 5, 10);
        Jugador player7 = new Jugador("Oscar De Marcos", "odemarcos", "passwordjkl", "Athletic Bilbao", 15, 7, 15);
        Jugador player8 = new Jugador("Iñigo Martínez", "imartinez", "passwordmno", "Athletic Bilbao", 4, 3, 2);
        Jugador player9 = new Jugador("Ander Capa", "acapa", "passwordpqr", "Athletic Bilbao", 21, 8, 12);
        Jugador player10 = new Jugador("Iñigo Lekue", "ilekue", "passwordstu", "Athletic Bilbao", 24, 1, 5);
        Jugador player11 = new Jugador("Asier Villalibre", "avillalibre", "passwordvwx", "Athletic Bilbao", 11, 15, 5);
        userList.add(player1);
        userList.add(player2);
        userList.add(player3);
        userList.add(player4);
        userList.add(player5);
        userList.add(player6);
        userList.add(player7);
        userList.add(player8);
        userList.add(player9);
        userList.add(player10);
        userList.add(player11);
        //
        Jugador player12 = new Jugador("Jan Oblak", "joblak", "obla123", "Athletico Madrid", 13, 0, 0);
        Jugador player13 = new Jugador("Stefan Savić", "ssavic", "savi789", "Athletico Madrid", 15, 1, 0);
        Jugador player14 = new Jugador("José María Giménez", "jmgimenez", "gimeabc", "Athletico Madrid", 2, 2, 1);
        Jugador player15 = new Jugador("Renan Lodi", "rlodi", "lodi123", "Athletico Madrid", 12, 0, 3);
        Jugador player16 = new Jugador("Koke", "koke", "koke456", "Athletico Madrid", 6, 3, 12);
        Jugador player17 = new Jugador("Saúl Ñíguez", "saulniguez", "saul789", "Athletico Madrid", 8, 4, 8);
        Jugador player18 = new Jugador("Marcos Llorente", "mllorente", "llorenteabc", "Athletico Madrid", 14, 6, 9);
        Jugador player19 = new Jugador("Ángel Correa", "acorrea", "correa123", "Athletico Madrid", 10, 7, 10);
        Jugador player20 = new Jugador("João Félix", "jfelix", "felix456", "Athletico Madrid", 7, 8, 6);
        Jugador player21 = new Jugador("Luis Suárez", "lsuarez", "suarez789", "Athletico Madrid", 9, 15, 5);
        Jugador player22 = new Jugador("Kieran Trippier", "ktrippier", "trip456", "Athletico Madrid", 23, 1, 5);
        userList.add(player12);
        userList.add(player13);
        userList.add(player14);
        userList.add(player15);
        userList.add(player16);
        userList.add(player17);
        userList.add(player18);
        userList.add(player19);
        userList.add(player20);
        userList.add(player21);
        userList.add(player22);
        Jugador player23 = new Jugador("Lionel Messi", "lmessi", "messi123", "FC Barcelona", 10, 30, 15);
        Jugador player24 = new Jugador("Gerard Piqué", "gpique", "pique456", "FC Barcelona", 3, 2, 0);
        Jugador player25 = new Jugador("Jordi Alba", "jalba", "alba789", "FC Barcelona", 18, 5, 12);
        Jugador player26 = new Jugador("Sergi Roberto", "sroberto", "robertoabc", "FC Barcelona", 20, 3, 8);
        Jugador player27 = new Jugador("Frenkie de Jong", "fdejong", "dejong123", "FC Barcelona", 21, 4, 10);
        Jugador player28 = new Jugador("Pedri", "pedri", "pedri456", "FC Barcelona", 16, 6, 15);
        Jugador player29 = new Jugador("Sergio Busquets", "sbusquets", "busquets789", "FC Barcelona", 5, 1, 5);
        Jugador player30 = new Jugador("Ansu Fati", "afati", "fatiabc", "FC Barcelona", 22, 12, 8);
        Jugador player31 = new Jugador("Memphis Depay", "mdepay", "depay123", "FC Barcelona", 9, 10, 7);
        Jugador player32 = new Jugador("Ronald Araújo", "raraujo", "araujo456", "FC Barcelona", 4, 1, 2);
        Jugador player33 = new Jugador("Marc-André ter Stegen", "mterstegen", "terstegen789", "FC Barcelona", 1, 0, 0);
        userList.add(player23);
        userList.add(player24);
        userList.add(player25);
        userList.add(player26);
        userList.add(player27);
        userList.add(player28);
        userList.add(player29);
        userList.add(player30);
        userList.add(player31);
        userList.add(player32);
        userList.add(player33);
        Jugador player34 = new Jugador("Karim Benzema", "kbz", "123", "Real Madrid", 9, 25, 10);
        Jugador player35 = new Jugador("Thibaut Courtois", "tcourtois", "courtois456", "Real Madrid", 1, 0, 0);
        Jugador player36 = new Jugador("Sergio Ramos", "sramos", "ramos789", "Real Madrid", 4, 3, 2);
        Jugador player37 = new Jugador("Raphaël Varane", "rvarane", "varaneabc", "Real Madrid", 5, 2, 0);
        Jugador player38 = new Jugador("Toni Kroos", "tkroos", "kroos123", "Real Madrid", 8, 5, 15);
        Jugador player39 = new Jugador("Luka Modrić", "lmodric", "modric456", "Real Madrid", 10, 4, 10);
        Jugador player40 = new Jugador("Casemiro", "casemiro", "casemiro789", "Real Madrid", 14, 2, 8);
        Jugador player41 = new Jugador("Ferland Mendy", "fmendy", "mendyabc", "Real Madrid", 23, 1, 5);
        Jugador player42 = new Jugador("Vinícius Júnior", "vjunior", "junior123", "Real Madrid", 20, 8, 12);
        Jugador player43 = new Jugador("Rodrygo", "rodrygo", "rodrygo456", "Real Madrid", 27, 6, 8);
        Jugador player44 = new Jugador("Marco Asensio", "masensio", "asensio789", "Real Madrid", 11, 7, 5);
        userList.add(player34);
        userList.add(player35);
        userList.add(player36);
        userList.add(player37);
        userList.add(player38);
        userList.add(player39);
        userList.add(player40);
        userList.add(player41);
        userList.add(player42);
        userList.add(player43);
        userList.add(player44);

    }


}
