package resources;
import  Entrenamiento.Entrenamiento;
import  Equipo.Equipo;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Data {

    public void fillDataEquipos(){
        Equipo athleticBilbao = new Equipo("Athletic Club de Bilbao", "San Mamés", 8);
        Equipo atleticoMadrid = new Equipo("Atlético Madrid", "Wanda Metropolitano", 11);
        Equipo barcelona = new Equipo("Barcelona", "Camp Nou", 26);
        Equipo realMadrid = new Equipo("Real Madrid", "Santiago Bernabéu", 34);
        Equipo sevilla = new Equipo("Sevilla", "Ramón Sánchez-Pizjuán", 1);
    }
    public void fillDataEntrenamiento(){
        ArrayList<Entrenamiento>  athleticBilbaoEntr= new ArrayList<>();
        Entrenamiento entrenamiento1 = new Entrenamiento(LocalDateTime.of(2024, 2, 20, 10, 0), LocalDateTime.of(2024, 2, 20, 12, 30) ,"test Material");
        Entrenamiento entrenamiento2 = new Entrenamiento(LocalDateTime.of(2024, 2, 23, 18, 30), LocalDateTime.of(2024, 2, 23, 21, 30) ,"Material2");
        athleticBilbaoEntr.add(entrenamiento1);
        athleticBilbaoEntr.add(entrenamiento2);

    }


    public void fillDataEntrenadores(){

    }

    public void fillDataJugadores(){

    }

}
