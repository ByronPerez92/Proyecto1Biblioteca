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
public class Proyecto1BibliotecaGrafos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo uno = new Grafo(30);
        uno.genErdosRenyi(16);
        uno.escribirArchivo("genErdosRenyi30.gv");

        Grafo dos = new Grafo(100);
        dos.genErdosRenyi(43);
        dos.escribirArchivo("genErdosRenyi100.gv");

        Grafo tres = new Grafo(500);
        tres.genErdosRenyi(113);
        tres.escribirArchivo("genErdosRenyi500.gv");

        Grafo cuatro = new Grafo(30);
        cuatro.genGilbert(0.25);
        cuatro.escribirArchivo("genGilbert30.gv");

        Grafo cinco = new Grafo(100);
        cinco.genGilbert(0.25);
        cinco.escribirArchivo("genGilbert100.gv");

        Grafo seis = new Grafo(500);
        cinco.genGilbert(0.25);
        seis.escribirArchivo("genGilbert500.gv");

        Grafo siete = new Grafo(30, "geo-uniforme");
        siete.genGeografico(0.3);
        siete.escribirArchivo("genGeografico30.gv");

        Grafo ocho = new Grafo(100, "geo-uniforme");
        ocho.genGeografico(.3);
        ocho.escribirArchivo("genGeografico100.gv");

        Grafo nueve = new Grafo(500, "geo-uniforme");
        nueve.genGeografico(0.3);
        nueve.escribirArchivo("genGeografico500.gv");

        Grafo diez = new Grafo(30);
        diez.genBarabasiAlbert(13);
        diez.escribirArchivo("genBarabasiAlbert30.gv");

        Grafo once = new Grafo(100);
        once.genBarabasiAlbert(42);
        once.escribirArchivo("genBarabasiAlbert100.gv");

        Grafo doce = new Grafo(500);
        doce.genBarabasiAlbert(137);
        doce.escribirArchivo("genBarabasiAlbert500.gv");
    }
    
}
