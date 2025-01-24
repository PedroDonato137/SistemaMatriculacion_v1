package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.Controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.*;

import javax.naming.OperationNotSupportedException;


public class MainApp {

    public static void main(String[] args) throws OperationNotSupportedException {

        System.out.println("Iniciamos la aplicación...");

        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo,vista);

        controlador.comenzar();

        System.out.println("Cerramos la aplicación...");
    }
}
