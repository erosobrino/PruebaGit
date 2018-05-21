/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bol7_ejer3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Validado
 *
 * @author ero
 */
public class Bol7_Ejer3 extends JFrame {

    public static void main(String[] args) {
        int eleccion;
        Scanner sc = new Scanner(System.in);
        ArrayList<Juego> Juegos = new ArrayList();
        String cadenaNombreAñoFabricante;
        File archivo;
//        archivo = new File("src/archivos/juegos.txt");
        archivo = new File("src/archivos/juegos.dat");
        Juego juego;

        if (archivo.exists()) {
            try (RandomAccessFile f = new RandomAccessFile(archivo, "r")) {
                //for (int i = 0; i < f.length(); i++) {
                //while (true){
                int cantidad = f.readInt();
                for (int i = 0; i < cantidad; i++) {
                    String nombre = f.readUTF();
                    int año = f.readInt();
                    String fabricante = f.readUTF();

                    juego = new Juego(nombre, año, fabricante);
                    //System.out.println("se inicializa");
                    Juegos.add(juego);
                    //System.out.println("se añade");
                }
            } catch (IOException ex) {
                System.out.println("Error en el archivo");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Se ha producido un error en la apertura del programa");
                System.out.println(ex.toString());
                //System.err.println("Error apertura derl archivo de inicializacion de juegos");
            }
        }

//        Juego juego1 = new Juego("AoE", 1997, "Microsoft");
//        Juego juego2 = new Juego("TrackMania", 2007, "Nadeo");
//        Juego juego3 = new Juego("GTA 5", 2013, "Rockstar");
//        Juegos.add(juego1);
//        Juegos.add(juego2);
//        Juegos.add(juego3);
        do {
            System.out.println("1.Insertar juego");
            System.out.println("2.Eliminar juego");
            System.out.println("3.Confirmar si exite un juego");
            System.out.println("4.Visualizar lista juegos");
            System.out.println("5.Visualizar juegos de un año");
            System.out.println("6.Borrar lista de juegos");
            System.out.println("7.Salir");
            eleccion = sc.nextInt();
            sc.nextLine();
            switch (eleccion) {
                case 1:
                    introducirJuego(Juegos);
                    break;
                case 2:
                    int borrado;
                    boolean bandera;
                    if (Juegos.size() > 0) {
                        do {
                            System.out.println("Escribe el id del juego a borrar, index en 1, hay " + Juegos.size());
                            borrado = sc.nextInt() - 1;
                            sc.nextLine();
                            if (borrado < 0 || borrado >= Juegos.size()) {
                                bandera = true;
                                System.out.println("Opcion invalida");
                            } else {
                                bandera = false;
                            }
                        } while (bandera);
                        Juegos.remove(borrado);
                    } else {
                        System.out.println("No hay juegos");
                    }
                    break;
                case 3:
                    comprobarJuego(Juegos);
                    break;
                case 4:
                    muestraBiblioteca(Juegos);
                    break;
                case 5:
                    juegoporAño(Juegos);
                    break;
                case 6:
                    if (Juegos.size() > 0) {
                        System.out.println("Estas seguro de que deseas borralos");
                        System.out.println("Pulsa 0 para borrarlo, otro para cancelar");
                        int confirmacion = sc.nextInt();
                        sc.nextLine();
                        if (confirmacion == 0) {
                            Juegos.removeAll(Juegos);
                        }
                    } else {
                        System.out.println("No hay juegos");
                    }
                    break;
                case 7:
                    salir(archivo, Juegos);
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (eleccion != 7);
    }

    public static void salir(File archivo, ArrayList<Juego> Juegos) {
        try (RandomAccessFile f = new RandomAccessFile(archivo, "rw")) {
            f.setLength(0);
            f.writeInt(Juegos.size());
            for (int i = 0; i < Juegos.size(); i++) {
                String nombre = Juegos.get(i).getNombre();
                int año = Juegos.get(i).getAño();
                String fabricante = Juegos.get(i).getFabricante();
                f.writeUTF(nombre);
                f.writeInt(año);
                f.writeUTF(fabricante);
            }
            System.out.printf("Se han guardado %d juegos\n", Juegos.size());
            JOptionPane.showMessageDialog(null, "Se han guardado los datos correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error en el guardado");
        }
    }

    /**
     * Introduce un juego nuevo en una coleccion con la opcion de meterlo al
     * inicio o al final
     *
     * @param Juegos La coleccion a utilizar
     * @return Devuelve la coleccion con el juego añadido
     */
    public static ArrayList<Juego> introducirJuego(ArrayList<Juego> Juegos) {
        Scanner sc = new Scanner(System.in);
        Juego juego = new Juego();
        System.out.println("Introduce el nombre del juego");
        juego.setNombre(sc.nextLine());
        System.out.println("Introduce el año");
        juego.setAño(sc.nextInt());
        sc.nextLine();
        System.out.println("Introduce el fabricante");
        juego.setFabricante(sc.nextLine());
        if (Juegos.size() > 0) {
            System.out.println("Pulsa 1 para ponerlo en el inicio, otro para ponerlo en el final");
            int eleccion = sc.nextInt();
            sc.nextLine();
            if (eleccion == 1) {
                Juegos.add(0, juego);
            } else {
                Juegos.add(juego);
            }
        } else {
            Juegos.add(juego);
        }

        return Juegos;
    }

    /**
     * Saca por pantalla todos los juegos de una coleccion dada
     *
     * @param Juegos La coleccion a utilizar
     */
    public static void muestraBiblioteca(ArrayList<Juego> Juegos) {
        int cont = 1;
        for (Juego juego : Juegos) {
            System.out.println("Juego " + cont);
            System.out.printf("Titulo:%s\nAño:%d\nFabricante:%s\n\n", juego.getNombre(), juego.getAño(), juego.getFabricante());
            cont++;
        }
        System.out.println("Hay " + Juegos.size() + " juegos");
    }

    /**
     * Comprueba si existe un juego que empieze por determinadas letras dadas
     * por el usuario y lo imprime
     *
     * @param Juegos La coleccion a utilizar
     */
    public static void comprobarJuego(ArrayList<Juego> Juegos) {
        if (Juegos.size() > 0) {
            int cont = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Que juego deseas comprobar, se puede escrbir un trozo del nombre");
            String comprobarNombre = sc.nextLine().trim().toUpperCase();
            for (Juego juego : Juegos) {
                String nombreJuego = juego.getNombre();
                if (nombreJuego.startsWith(comprobarNombre)) {
                    cont++;
                    System.out.printf("Titulo:%s\nAño:%d\nFabricante:%s\n\n", juego.getNombre(), juego.getAño(), juego.getFabricante());
                }
            }
            System.out.println("Hay " + cont + " juegos");
        } else {
            System.out.println("No hay juegos en la coleccion");
        }
    }

    /**
     * Comprueba si existe un juego que sea de determinado año, dado por el
     * usuario y lo imprime
     *
     * @param Juegos La coleccion a utilizar
     */
    private static void juegoporAño(ArrayList<Juego> Juegos) {
        if (Juegos.size() > 0) {
            int cont = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el año a comprobar el juego");
            int añoComprobar = sc.nextInt();
            sc.nextLine();
            for (Juego juego : Juegos) {
                if (añoComprobar == juego.getAño()) {
                    System.out.printf("Titulo:%s\nAño:%d\nFabricante:%s\n\n", juego.getNombre(), juego.getAño(), juego.getFabricante());
                    cont++;
                }
            }
            System.out.println("Hay " + cont + " juegos");
        } else {
            System.out.println("No hay juegos en la coleccion");
        }
    }
}
