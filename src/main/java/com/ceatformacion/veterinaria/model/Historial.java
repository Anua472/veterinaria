package com.ceatformacion.veterinaria.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Historial {
    //los atributos de la clase son los que se almacenan en la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idHistorial;


    private LocalDate fecha;
    private String motivo;
    private String tratamiento;
    private String observacion;

    @ManyToOne
    @JoinColumn(name="id_mascota",nullable=false)
    private Mascota mascota;

    //getter y setter
    //crear el repositorio
    //ejecutar la aplicación
    //ver el diagrama en la bbdd


    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int id) {
        this.idHistorial = id;
    }
}
