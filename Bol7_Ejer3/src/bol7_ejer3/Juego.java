/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bol7_ejer3;

/**
 * Validado
 *
 * @author ero
 */
public class Juego {

    private String nombre;
    private int año;
    private String fabricante;

    /**
     * Inicializa el objeto con valores predefinidos
     */
    Juego() {
        this.nombre = "Nombre";
        this.año = 0000;
        this.fabricante = "Fabricante";
    }


    /**
     * Inicializa el objeto con los valores dados, los guarda en mayusculas
     *
     * @param nombre El nombre del juego
     * @param año E año del juego
     * @param fabricante El fabricante del juego
     */
    Juego(String nombre, int año, String fabricante) {
        this.nombre = nombre.trim().toUpperCase();
        this.año = año;
        this.fabricante = fabricante.trim().toUpperCase();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre.trim().toUpperCase();
    }

    /**
     * @return the año
     */
    public int getAño() {
        return año;
    }

    /**
     * @param año the año to set
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante.trim().toUpperCase();
    }
}
