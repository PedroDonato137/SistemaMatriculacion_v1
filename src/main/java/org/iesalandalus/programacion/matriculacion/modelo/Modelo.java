package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.*;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Modelo {

    // Constantes de la Clase
    public static final int CAPACIDAD = 3;

    //Atributos de la clase
    private static Alumnos alumnos;
    private static Matriculas matriculas;
    private static Asignaturas asignaturas;
    private static CiclosFormativos cicloFormativos;



    // Datos ficticios de inicio para no estar todo el rato ingresando datos(Borrar)
    public static void datosInicio() throws OperationNotSupportedException {
        // Datos de prueba:
        String cursoAcademico = "24-25";
        //Fecha matricula
        String fechaMatriculacion = "21/01/2025";
        LocalDate fecha = null;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fecha = LocalDate.parse(fechaMatriculacion, formatoFecha);

        LocalDate fechaNacimientoFicticio = LocalDate.of(1990, 6, 9); // Fecha ficticia válida (+16)

        Alumno alumno1 = new Alumno("Pedro", "54119272L", "pedrodonatogarcia@gmail.com", "609822699", fechaNacimientoFicticio);
        Asignatura[] coleccionAsignaturas1 = new Asignatura[3];

        CicloFormativo cicloFicticio1 = new CicloFormativo(1001, "Informatica", Grado.GDCFGS, "Informatica", 2000);
        Asignatura asignaturaFicticia1 = new Asignatura("1", "programacion", 100, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cicloFicticio1);
        Asignatura asignaturaFicticia2 = new Asignatura("2", "Base Datos", 100, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cicloFicticio1);
        Asignatura asignaturaFicticia3 = new Asignatura("3", "FOL", 100, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cicloFicticio1);
        coleccionAsignaturas1[0] = asignaturaFicticia1;
        coleccionAsignaturas1[1] = asignaturaFicticia2;
        coleccionAsignaturas1[2] = asignaturaFicticia3;

        Matricula matricula1= new Matricula(1, cursoAcademico, fecha, alumno1, coleccionAsignaturas1);

        alumnos.insertar(alumno1);
        cicloFormativos.insertar(cicloFicticio1);
        asignaturas.insertar(asignaturaFicticia1);
        asignaturas.insertar(asignaturaFicticia2);
        asignaturas.insertar(asignaturaFicticia3);
        matriculas.insertar(matricula1);

    }

    public void comenzar() throws OperationNotSupportedException {
        alumnos = new Alumnos(CAPACIDAD);
        matriculas = new Matriculas(CAPACIDAD);
        asignaturas = new Asignaturas(CAPACIDAD);
        cicloFormativos = new CiclosFormativos(CAPACIDAD);
        datosInicio(); // Datos ficticios
    }

    public static void terminar(){
        System.out.println("Cerramos el Modelo");
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*------------------------------------------------ ALUMNOS ------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static void insertar(Alumno alumno){

        try {
            alumnos.insertar(alumno);
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
