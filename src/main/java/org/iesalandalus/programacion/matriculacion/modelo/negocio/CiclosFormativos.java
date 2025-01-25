package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import javax.naming.OperationNotSupportedException;

public class CiclosFormativos {

    private int capacidad;
    private int tamano;
    private CicloFormativo[] coleccionCiclosFormativos;

    //Constructor
    public CiclosFormativos(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    // Constructor copia
    public CicloFormativo[] get() {
        return copiaProfundaCicloFormativo();
    }

    private CicloFormativo[] copiaProfundaCicloFormativo() {
        CicloFormativo[] copiaCicloFormativo = new CicloFormativo[tamano];

        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaCicloFormativo[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copiaCicloFormativo;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        int indice = 0;

        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }

        indice = buscarIndice(cicloFormativo);

        if (indice != -1) {
            throw new IllegalArgumentException("ERROR: Ya existe ese ciclo formativo");
        } else {
            if (capacidadSuperada(tamano)){
                throw new IllegalArgumentException("ERROR: No se aceptan mas ciclos formativos");
            }else{
                for (int i = 0; i < coleccionCiclosFormativos.length; i++)
                {
                    if(coleccionCiclosFormativos[i] == null){
                        coleccionCiclosFormativos[tamano] = new CicloFormativo(cicloFormativo);
                        tamano++;
                        break;
                    }
                }
            }
        }
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {
        int cicloEncontrado = -1;

        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar el índice de un ciclo formativo nulo.");
        }

        for (int i = 0; i < coleccionCiclosFormativos.length; i++){
            if(coleccionCiclosFormativos[i] == null){
                break;
            }
            if(coleccionCiclosFormativos[i].getCodigo() == cicloFormativo.getCodigo()){
                cicloEncontrado = i;
                break;
            }
        }

        return cicloEncontrado;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {

        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);

        if(indice == -1){
            return null;
        }else{
            return new CicloFormativo(coleccionCiclosFormativos[indice]);
        }
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
            System.out.println("Ciclo formativo borrado correctamente");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; i < tamano - 1; i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
        }
        coleccionCiclosFormativos[tamano - 1] = null;
        tamano--;
    }

}
