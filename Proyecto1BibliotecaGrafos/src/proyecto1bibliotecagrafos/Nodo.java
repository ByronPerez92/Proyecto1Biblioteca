/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1bibliotecagrafos;

/**
 *
 * @author byron
 */
public class Nodo {

    private String nombre;
    private Integer numAristas;
    private Integer index;
    private double x;
    private double y;

    private double distance;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.numAristas = 0;
    }
    
    public Nodo(int nombre) {
        this.index = nombre;
        this.nombre = "n" + String.valueOf(nombre);
        this.numAristas = 0;
    }
    public Nodo(int nombre, double x, double y) {
        this.index = nombre;
        this.nombre = "n" + String.valueOf(nombre);
        this.x = x;
        this.y = y;
    }
    
    public String getNombre() {
        return nombre;
    }

    public Integer getNumAristas() {
        return numAristas;
    }

    public Integer getIndex() {
        return index;
    }

    public double getDistancia() {
        return distance;
    }

    public void setDistancia(double d) {
        this.distance = d;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
