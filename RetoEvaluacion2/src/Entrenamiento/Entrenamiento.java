package Entrenamiento;

import Utilidades.Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Entrenamiento {

    private LocalDateTime fetchaHoraInicio;
    private LocalDateTime fetchaHoraFin;
    private String material;
   // private ArrayList<String> materiales;

    public Entrenamiento() {
        this.fetchaHoraInicio = null;
        this.fetchaHoraFin = null;
        this.material="";
       // this.materiales = new ArrayList<>();
    }

    public Entrenamiento(LocalDateTime fetchaHoraInicio, LocalDateTime fetchaHoraFin, String material) {
        this.fetchaHoraInicio = fetchaHoraInicio;
        this.fetchaHoraFin = fetchaHoraFin;
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public LocalDateTime getFetchaHoraInicio() {
        return fetchaHoraInicio;
    }

    public void setFetchaHoraInicio(LocalDateTime fetchaHoraInicio) {
        this.fetchaHoraInicio = fetchaHoraInicio;
    }

    public LocalDateTime getFetchaHoraFin() {
        return fetchaHoraFin;
    }

    public void setFetchaHoraFin(LocalDateTime fetchaHoraFin) {
        this.fetchaHoraFin = fetchaHoraFin;
    }

    public void setDatosEntrenamiento() {
        System.out.println("Introduzca nombre del material :");
        this.material= Util.introducirCadena();
        do{
            System.out.println("Introduzca el dia con la Hora de inicio :");
            this.fetchaHoraInicio= Util.leerFechaAMDH();
            System.out.println("Introduzca el dia con la Hora de inicio :");
            this.fetchaHoraFin= Util.leerFechaAMDH();
        }while(fetchaHoraFin.isBefore(fetchaHoraInicio));

    }
    public String getDuracion(){
        Long duration=Duration.between(this.getFetchaHoraInicio(),this.getFetchaHoraFin()).toHours();
        return duration.toString();
    }
    public void getDatosEntrenamiento() {
        System.out.println("-----Datos de Entrenamiento-----");
        System.out.println("Material : " + this.material);
        System.out.println("Duraccion : " + getDuracion());
    }


}
