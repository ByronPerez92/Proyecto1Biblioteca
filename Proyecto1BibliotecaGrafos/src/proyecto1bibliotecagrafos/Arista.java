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
public class Arista {
    
    private Integer n1;
    private Integer n2;
    private double peso;

    public Arista(int n1, int n2, double peso) {
        this.n1 = n1;
        this.n2 = n2;
        this.peso = peso;
    }

    public String getNodo1() {
        return "n" + n1.toString();
    }

    public String getNodo2() {
        return "n" + n2.toString();
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }

    public double getPeso() {
        return peso;
    }
}
