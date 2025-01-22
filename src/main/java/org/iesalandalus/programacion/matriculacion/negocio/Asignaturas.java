package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import javax.naming.OperationNotSupportedException;

public class Asignaturas {
    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    //Constructor
    public Asignaturas(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionAsignaturas = new Asignatura[capacidad];
    }

    // Constructor copia
    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copiaAsignaturas = new Asignatura[tamano];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAsignaturas[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copiaAsignaturas;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public Asignatura buscar(Asignatura asignatura) {

        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nulo.");
        }
        int indice = buscarIndice(asignatura);

        if(indice == -1){
            return null;
        }else{
            return new Asignatura(coleccionAsignaturas[indice]);
        }
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nulo.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            //throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
            System.out.println("Error: No existe esa asignatura para borrar");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
            System.out.println("asignatura borrada correctamente");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; i < tamano - 1; i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[tamano - 1] = null;
        tamano--;
    }

    private int buscarIndice(Asignatura asignatura) {
        int asignaturaEncontrado = -1;

        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar el índice de una asignatura nula.");
        }

        for (int i = 0; i < coleccionAsignaturas.length; i++){
            if(coleccionAsignaturas[i] == null){
                break;
            }
            if(coleccionAsignaturas[i].getCodigo().equals(asignatura.getCodigo())){
                asignaturaEncontrado = i;
                break;
            }
        }

        return asignaturaEncontrado;
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        int indice = 0;

        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nulo.");
        }

        indice = buscarIndice(asignatura);

        if (indice != -1) {
            System.out.println("ERROR: Ya existe una asignatura con ese codigo.");
        } else {
            if (capacidadSuperada(tamano)){
                System.out.println("ERROR: No se aceptan mas asignaturas");
            }else{
                for (int i = 0; i < coleccionAsignaturas.length; i++)
                {
                    if(coleccionAsignaturas[i] == null){
                        coleccionAsignaturas[tamano] = new Asignatura(asignatura);
                        tamano++;
                        break;
                    }
                }
            }
        }
    }
}
