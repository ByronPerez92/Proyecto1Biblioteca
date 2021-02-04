/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1bibliotecagrafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author byron
 */
public class Grafo {
    
    private Nodo[] nodos;
    private HashMap<Nodo, HashSet<Nodo>> grafo;
    private HashMap<Nodo, HashSet<Arista>> incidencia;
    private final int numeroNodos;
    private int numeroAristas;
    private static Formatter output;
    private Boolean peso;

    public Grafo(int numNodos) {
        this.grafo = new HashMap<Nodo, HashSet<Nodo>>();
        this.incidencia = new HashMap<Nodo, HashSet<Arista>>();
        this.numeroNodos = numNodos;
        this.nodos = new Nodo[numNodos];
        for (int i = 0; i < numNodos; i++) {
            Nodo n = new Nodo(i);
            this.nodos[i] = n;
            this.grafo.put(n, new HashSet<Nodo>());
            this.incidencia.put(n, new HashSet<Arista>());
        }
        this.peso = false;
    }
    
    public Grafo(int numNodos, String modelo) {
        this.grafo = new HashMap<Nodo, HashSet<Nodo>>();
        this.incidencia = new HashMap<Nodo, HashSet<Arista>>();
        this.numeroNodos = numNodos;
        this.nodos = new Nodo[numNodos];
        Random coorX = new Random();
        Random coorY = new Random();
        if (modelo == "geo-uniforme") {
            for (int i = 0; i < numNodos; i++) {
                Nodo n = new Nodo(i, coorX.nextDouble(), coorY.nextDouble());
                this.nodos[i] = n;
                this.grafo.put(n, new HashSet<Nodo>());
                this.incidencia.put(n, new HashSet<Arista>());
            }
        }
        this.peso = false;
    }

    public int gradoNodo(int i) {
        Nodo n1 = this.getNodo(i);
        return this.grafo.get(n1).size();
    }

    public void conectarNodos(int i, int j) {
        Nodo n1 = this.getNodo(i);
        Nodo n2 = this.getNodo(j);
        HashSet<Nodo> aristas1 = this.getEdges(i);
        HashSet<Nodo> aristas2 = this.getEdges(j);
        aristas1.add(n2);
        aristas2.add(n1);
        this.numeroAristas += 1;
    }
    
    private Boolean existeConexion(int i, int j) {
        Nodo n1 = this.getNodo(i);
        Nodo n2 = this.getNodo(j);
        HashSet<Nodo> aristas1 = this.getEdges(i);
        HashSet<Nodo> aristas2 = this.getEdges(j);
        if (aristas1.contains(n2) || aristas2.contains(n1)) {
            return true;
        } else {
            return false;
        }
    }
    
    private double distanciaNodos(Nodo n1, Nodo n2) {
        return Math.sqrt(Math.pow((n1.getX() - n2.getX()), 2)
                + Math.pow((n1.getY() - n2.getY()), 2));
    }
    
    public int getNumNodes() {
        return numeroNodos;
    }

    public int getNumEdges() {
        return numeroAristas;
    }

    public Nodo getNodo(int i) {
        return this.nodos[i];
    }

    public Boolean getPesoedFlag() {
        return this.peso;
    }

    public HashSet<Nodo> getEdges(int i) {
        Nodo n = this.getNodo(i);
        return this.grafo.get(n);
    }

    public HashSet<Arista> getPesoedEdges(int i) {
        Nodo n = this.getNodo(i);
        return this.incidencia.get(n);
    }

    public void setPeso() {
        this.peso = true;
    }

    public void setIncidencia(int i, HashSet<Arista> aristasPeso) {
        this.incidencia.put(this.getNodo(i), aristasPeso);
    }

    public void setAristaPeso(int i, int j, double peso) {
        if (!this.existeConexion(i, j)) {
            this.conectarNodos(i, j);
        }
        Arista aristaNuevaij = new Arista(i, j, peso);
        Arista aristaNuevaji = new Arista(j, i, peso);
        HashSet<Arista> aristasNodoi = this.getPesoedEdges(i);
        HashSet<Arista> aristasNodoj = this.getPesoedEdges(j);
        aristasNodoi.add(aristaNuevaij);
        aristasNodoj.add(aristaNuevaji);
        this.setIncidencia(i, aristasNodoi);
        this.setIncidencia(j, aristasNodoj);
        if (!this.getPesoedFlag()) {
            this.setPeso();
        }
    }
    
    @Override
    public String toString() {
        String archivogv;
        if (this.getPesoedFlag()) {
            archivogv = "graph {\n";
            for (int i = 0; i < this.getNumNodes(); i++) {
                archivogv += this.getNodo(i).getNombre() + " [label=\""
                        + this.getNodo(i).getNombre() + " (" + this.getNodo(i).getDistancia()
                        + ")\"];\n";
            }
            for (int i = 0; i < this.getNumNodes(); i++) {
                HashSet<Arista> aristas = this.getPesoedEdges(i);
                archivogv = aristas.stream().map(e -> e.getNodo1() + " -- " + e.getNodo2() + " [weight=" + e.getPeso() + "" + " label=" + e.getPeso() + "" + "];\n").reduce(archivogv, String::concat);
            }
            archivogv += "}\n";
        } else { //esta es para grafos sin pesos ni etiquetas
            archivogv = "graph {\n";
            for (int i = 0; i < this.getNumNodes(); i++) {
                archivogv += this.getNodo(i).getNombre() + ";\n";
            }
            for (int i = 0; i < this.getNumNodes(); i++) {
                HashSet<Nodo> aristas = this.getEdges(i);
                for (Nodo n : aristas) {
                    archivogv += this.getNodo(i).getNombre() + " -- " + n.getNombre() + ";\n";
                }
            }
            archivogv += "}\n";
        }
        return archivogv;
    }
    
    public void genErdosRenyi(int numAristas) {
        Random randomNum1 = new Random();
        Random randomNum2 = new Random();
        while (this.getNumEdges() < numAristas) {
            int num1 = randomNum1.nextInt(this.getNumNodes());
            int num2 = randomNum2.nextInt(this.getNumNodes());
            if (num1 != num2) {
                if (!existeConexion(num1, num2)) {
                    conectarNodos(num1, num2);
                }
            }
        }
    }
    
    public void genGilbert(double probabilidad) {
        Random randomNum = new Random();
        
        for (int i = 0; i < this.getNumNodes(); i++) {
            for (int j = 0; j < this.getNumNodes(); j++) {
                if ((i != j) && (randomNum.nextDouble() <= probabilidad)) {
                    if (!existeConexion(i, j)) {
                        conectarNodos(i, j);
                    }
                }
            }
        }
    }
    
    public void genGeografico(double r) {
        for (int i = 0; i < this.getNumNodes(); i++) {
            for (int j = i + 1; j < this.getNumNodes(); j++) {
                double distancia = distanciaNodos(this.getNodo(i), this.getNodo(j));
                if (distancia <= r) {
                    conectarNodos(i, j);
                }
            }
        }
    }
    
    public void genBarabasiAlbert(int d) {
        Random volado = new Random();
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < i; j++) {
                if (!existeConexion(i, j)) {
                    conectarNodos(i, j);
                }
            }
        }
        
        for (int i = d; i < this.getNumNodes();) {
            for (int j = 0; j < i; j++) {
                double probabilidad
                        = (double) gradoNodo(j) / (double) this.getNumEdges();
                if (volado.nextDouble() <= probabilidad) {
                    if (!existeConexion(i, j) && (gradoNodo(i) < d)) {
                        conectarNodos(i, j);
                    }
                }
            }
            if (gradoNodo(i) >= d) {
                i++;
            }
        }
    }
    
    public void escribirArchivo(String nombre) {
        try {
            File folder = null;
            String rutaAbsoluta = new File("").getAbsolutePath();
            folder = new File(rutaAbsoluta + "\\Archivos\\");
            if (!folder.exists())
            {
                folder.mkdirs();
            }
            String ruta = rutaAbsoluta + "\\Archivos\\" + nombre;
            output = new Formatter(ruta);
        } catch (SecurityException securityException) {
            System.err.println("No hay permiso de escritura.");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
            System.err.println("Error al abrir el archivo.");
            System.exit(1);
        }
        try {
            output.format("%s", this);
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error al escribir al archivo");
        }
        if (output != null) {
            output.close();
        }
    }
}
