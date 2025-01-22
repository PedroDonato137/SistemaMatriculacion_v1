package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Curso {
    PRIMERO("PRIMERO"),
    SEGUNDO("SEGUNDO");

    private final String cadenaAMostrar;

    Curso(String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir(){
        return this.ordinal() + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Curso{" + "cadenaAMostrar='" + cadenaAMostrar + '\'' + '}';
    }
}
