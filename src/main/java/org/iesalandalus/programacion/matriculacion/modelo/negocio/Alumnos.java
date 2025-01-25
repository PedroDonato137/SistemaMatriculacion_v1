package org.iesalandalus.programacion.matriculacion.modelo.negocio;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class Alumnos {

    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;

    //Constructor
    public Alumnos(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionAlumnos = new Alumno[capacidad];
    }

    // Constructor copia
    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copiaAlumnos = new Alumno[tamano];

        for (int i = 0; i < tamano; i++) {
            copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copiaAlumnos;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {

        int indice = 0;

        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }

        indice = buscarIndice(alumno);

        if (indice != -1) {
            throw new IllegalArgumentException("ERROR: Ya existe un alumno con ese dni.");
        } else {
            if (capacidadSuperada(tamano)){
                throw new IllegalArgumentException("ERROR: No se aceptan mas alumnos");
            }else{
                for (int i = 0; i < coleccionAlumnos.length; i++)
                {
                    if(coleccionAlumnos[i] == null){
                        coleccionAlumnos[tamano] = new Alumno(alumno);
                        tamano++;
                        break;
                    }
                }
            }
        }
    }

    private int buscarIndice(Alumno alumno) {

        int alumnoEncontrado = -1;

        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar el índice de un alumno nulo.");
        }

        for (int i = 0; i < coleccionAlumnos.length; i++){
            if(coleccionAlumnos[i] == null){
                break;
            }
            if(coleccionAlumnos[i].getDni().equals(alumno.getDni())){
                alumnoEncontrado = i;
                break;
            }
        }

        return alumnoEncontrado;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public Alumno buscar(Alumno alumno)  {

        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);

        if(indice == -1){
            return null;
        }else{
            return new Alumno(coleccionAlumnos[indice]);
        }
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
            System.out.println("Alumno borrado correctamente");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; i < tamano - 1; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[tamano - 1] = null;
        tamano--;
    }

}
