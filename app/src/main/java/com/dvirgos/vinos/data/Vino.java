package com.dvirgos.vinos.data;

import java.util.Objects;

public class Vino {

    private long id;
    private String nombre;
    private String bodega;
    private String color;
    private String origen;
    private int fecha;
    private double graduacion;

    public Vino() {
        this(0, null, null, null, null, 0, 0.0);
    }

    public Vino(long id, String nombre, String bodega, String color, String origen, int fecha, double graduacion) {
        this.id = id;
        this.nombre = nombre;
        this.bodega = bodega;
        this.color = color;
        this.origen = origen;
        this.fecha = fecha;
        this.graduacion = graduacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getBodega() {
        return bodega;
    }

    public String getColor() {
        return color;
    }

    public String getOrigen() {
        return origen;
    }

    public int getFecha() {
        return fecha;
    }

    public double getGraduacion() {
        return graduacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public void setGraduacion(double graduacion) {
        this.graduacion = graduacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vino{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", bodega='" + bodega + '\'' +
                ", color='" + color + '\'' +
                ", origen='" + origen + '\'' +
                ", fecha=" + fecha +
                ", graduacion=" + graduacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vino vino = (Vino) o;
        return id == vino.id && fecha == vino.fecha && Double.compare(vino.graduacion, graduacion) == 0 && Objects.equals(nombre, vino.nombre) && Objects.equals(bodega, vino.bodega) && Objects.equals(color, vino.color) && Objects.equals(origen, vino.origen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, bodega, color, origen, fecha, graduacion);
    }

    //Si dos objetos son iguales su hascode tiene que ser igual
    //pero si el hasCode de dos objetos es el mismo no significa que los objetos sean iguales

}
