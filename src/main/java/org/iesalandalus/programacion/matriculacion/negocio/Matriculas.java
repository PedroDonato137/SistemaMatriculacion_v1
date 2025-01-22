package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import javax.naming.OperationNotSupportedException;

public class Matriculas {

    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;

    //Constructor
    public Matriculas(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        setCapacidad(capacidad);
        setTamano(0);
        coleccionMatriculas = new Matricula[capacidad];
    }

    // Constructor copia
    public Matricula[] get() {
        return copiaProfundaMatriculas();
    }

    public Matricula[] get(Alumno alumno) {

        Matricula[] matriculaAlumno = new Matricula[3];
        int i = 0;

        if (alumno == null) {
            throw new NullPointerException("ERROR: No se pueden buscar un alumno nulo.");
        }
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null){
                if (matricula.getAlumno().getDni().equals(alumno.getDni())) {
                    matriculaAlumno[i] = matricula;
                    i++;
                }
            }else{
                break;
            }
        }

        return matriculaAlumno;
    }

    public Matricula[] get(String cursoAcademico) {

        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: No se pueden buscar un curso nulo.");
        }

        Matricula[] cursoPermanencia = new Matricula[3];
        int i = 0;

        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null){
                if (matricula.getCursoAcademico().equals(cursoAcademico)) {
                    cursoPermanencia[i] = matricula;
                    i++;
                }
            }else{
                break;
            }
        }

        return cursoPermanencia;
    }

    public Matricula[] get(CicloFormativo cicloFormativo) {
        Matricula[] matriculasConCiclo = new Matricula[3]; // Array a devolver
        int contadorMatriculas = 0;

        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se pueden buscar un ciclo formativo nulo.");
        }else{
            for (int i = 0; i < tamano; i++) { // For para sacar las asignaturas
                Asignatura[] asignaturasMatricula = new Asignatura[10]; // Creo un array temporal por cada matricula sacar las asignaturas
                Matricula matricula = coleccionMatriculas[i]; // consigo solo una matricula
                asignaturasMatricula = matricula.getColeccionAsignaturas(); // Saco las asignaturas de esa matricula
                for (int j = 0; j < asignaturasMatricula.length; j++) { // Este for es para recorrer las asignaturas
                    if (asignaturasMatricula[j].getCicloFormativo().getCodigo() == cicloFormativo.getCodigo()) { // comparar el ciclo formativo de cada asignatura con el pasado por parametro
                        matriculasConCiclo[contadorMatriculas] = coleccionMatriculas[i]; // si coincide lo mete en un array independiente
                        contadorMatriculas++;
                    }
                }
            }
        }

        return matriculasConCiclo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    private Matricula[] copiaProfundaMatriculas() {
        Matricula[] copiaMatricula = new Matricula[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaMatricula[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copiaMatricula;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) throws OperationNotSupportedException {
        if (tamano >= coleccionMatriculas.length) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        return indice >= capacidad;
    }

    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nulo.");
        }

        int indice = buscarIndice(matricula);

        if(indice == -1){
            return null;
        }else{
            return new Matricula(coleccionMatriculas[indice]);
        }
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)) {
            //throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula de ese alumno");
            System.out.println("Error: No existe esa matricula para borrar");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
            System.out.println("Matricula borrada correctamente");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i+1];
        }
        coleccionMatriculas[i] = null;
        tamano--;
    }

    private int buscarIndice(Matricula matricula) {
        int matriculaEncontrado = -1;

        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar el índice de una asignatura nula.");
        }

        for (int i = 0; i < coleccionMatriculas.length; i++){
            if(coleccionMatriculas[i] == null){
                break;
            }
            if(coleccionMatriculas[i].getIdMatricula() == matricula.getIdMatricula()){
                matriculaEncontrado = i;
                break;
            }
        }
        return matriculaEncontrado;
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        int indice = 0;

        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matricula nulo.");
        }

        indice = buscarIndice(matricula);

        if (indice != -1) {
            System.out.println("ERROR: Ya existe una matricula con ese codigo.");
        } else {
            if (capacidadSuperada(tamano)){
                System.out.println("ERROR: No se aceptan mas matriculas");
            }else{
                for (int i = 0; i < coleccionMatriculas.length; i++)
                {
                    if(coleccionMatriculas[i] == null){
                        coleccionMatriculas[tamano] = new Matricula(matricula);
                        tamano++;
                        break;
                    }
                }
            }
        }
    }

}


