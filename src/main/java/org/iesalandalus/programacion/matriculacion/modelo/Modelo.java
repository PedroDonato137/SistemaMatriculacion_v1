package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.*;
import org.iesalandalus.programacion.matriculacion.vista.Consola;

import javax.naming.OperationNotSupportedException;

public class Modelo {

    // Constantes de la Clase
    public static final int CAPACIDAD = 3;

    //Atributos de la clase
    private static Alumnos alumnos = new Alumnos(CAPACIDAD);
    private static Matriculas matriculas = new Matriculas(CAPACIDAD);
    private static Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
    private static CiclosFormativos cicloFormativos = new CiclosFormativos(CAPACIDAD);

    public static void comenzar(){

    }

    public static void terminar(){

    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*------------------------------------------------ ALUMNOS ------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static void insertar(Alumno alumno){

        try {
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente");
        } catch (OperationNotSupportedException e) {
            throw new IllegalArgumentException("ERROR: No se pudo insertar el alumno");
        }
    }

    public static Alumno buscar(Alumno alumno){

        Alumno alumnoBuscado = alumnos.buscar(alumno);
        if (alumnoBuscado != null){
            return alumnoBuscado;
        }
        else{
            throw new IllegalArgumentException("ERROR: No existe ningun alumno con ese DNI");
        }
    }

    public static void borrar(Alumno alumno) throws OperationNotSupportedException {

        try {
            alumnos.borrar(alumno);
            System.out.println("Alumno borrado correctamente");
        } catch (OperationNotSupportedException e) {
            throw new IllegalArgumentException("ERROR: No se pudo borrar el alumno");
        }
    }

    public static Alumno[] getAlumnos(){

        Alumno[] alumnosAlta =  alumnos.get();

        if (alumnosAlta.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen alumnos dados de alta.");
        }else{
            return alumnosAlta;
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- ASIGNATURAS ----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static void insertar(Asignatura asignatura){

        try {
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente");
        } catch (OperationNotSupportedException e) {
            throw new IllegalArgumentException("ERROR: No se pudo insertar la asignatura");
        }
    }

    public static Asignatura buscar(Asignatura asignatura){

        Asignatura asignaturaBuscada = asignaturas.buscar(asignatura);
        if (asignaturaBuscada != null){
            return asignaturaBuscada;
        }
        else{
            throw new IllegalArgumentException("ERROR: No existe ninguna asignatura con ese codigo");
        }
    }

    public static void borrar(Asignatura asignatura) throws OperationNotSupportedException {

        try {
            asignaturas.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente");
        } catch (OperationNotSupportedException e) {
            throw new IllegalArgumentException("ERROR: No se pudo borrar la asignatura");
        }
    }

    public static Asignatura[] getAsignaturas(){

        Asignatura[] asignaturaAlta = asignaturas.get();

        if (asignaturaAlta.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen asignaturas dadas de alta.");
        }else{
            return asignaturaAlta;
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- CICLOS FORMATIVOS ---------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static void insertar(CicloFormativo cicloFormativo){

        try {
            cicloFormativos.insertar(cicloFormativo);
            System.out.println("Ciclo formativo insertada correctamente");
        } catch (OperationNotSupportedException e) {
            throw new IllegalArgumentException("ERROR: No se pudo insertar el ciclo formativo");
        }
    }

    public static CicloFormativo buscar(CicloFormativo cicloFormativo){

        CicloFormativo cicloBuscado = cicloFormativos.buscar(cicloFormativo);
        if (cicloBuscado != null){
            return cicloBuscado;
        }
        else{
            throw new IllegalArgumentException("ERROR: No existe ningun ciclo formativo con ese codigo");
        }
    }

    public static void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {

        try {
            cicloFormativos.borrar(cicloFormativo);
            System.out.println("Ciclo formativo borrado correctamente");
        } catch (OperationNotSupportedException e) {
            throw new IllegalArgumentException("ERROR: No se pudo borrar el ciclo formativo");
        }
    }

    public static CicloFormativo[] getCiclosFormativos(){

        CicloFormativo[] cicloAlta = cicloFormativos.get();

        if (cicloAlta.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen ciclos formativos dados de alta.");
        }else{
            return cicloAlta;
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- MATRICULAS -----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static void insertar(Matricula matricula){

        try {
            matriculas.insertar(matricula);
            System.out.println("Matricula insertada correctamente");
        } catch (OperationNotSupportedException e) {
            throw new IllegalArgumentException("ERROR: No se pudo insertar la matricula");
        }
    }

    public static Matricula buscar(Matricula matricula){

        Matricula matriculaBuscada = matriculas.buscar(matricula);
        if (matriculaBuscada != null){
            return matriculaBuscada;
        }
        else{
            throw new IllegalArgumentException("ERROR: No existe ninguna matricula con ese codigo");
        }
    }

    public static void borrar(Matricula matricula) throws OperationNotSupportedException {

        try {
            matriculas.borrar(matricula);
            System.out.println("Matricula borrada correctamente");
        } catch (OperationNotSupportedException e) {
            throw new IllegalArgumentException("ERROR: No se pudo borrar la matricula");
        }
    }

    public static Matricula[] getMatriculas(){

        Matricula[] matriculaAlta = matriculas.get();

        if (matriculaAlta.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen matriculas dadas de alta.");
        }else{
            return matriculaAlta;
        }
    }

    public static Matricula[] getMatriculas(Alumno alumno){

        if (alumno == null) {
            throw new IllegalArgumentException("ERROR: No existen matricula de ese alumnos");
        }else{
            return matriculas.get(alumno);
        }
    }

    public static Matricula[] getMatriculas(CicloFormativo cicloFormativo){

        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: No existen matricula de ese ciclo formativo");
        }else{
            return matriculas.get(cicloFormativo);
        }
    }

    public static Matricula[] getMatriculas(String cursoAcademico){

        if (cursoAcademico == null) {
            throw new IllegalArgumentException("ERROR: No existen matricula de ese curso academico");
        }else{
            return matriculas.get(cursoAcademico);
        }
    }

}
