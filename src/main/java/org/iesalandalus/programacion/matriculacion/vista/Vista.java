package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.Controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.util.Scanner;

public class Vista {

    private static Controlador controller;

    public void setController(Controlador controller)
    {
        if(controller != null) {
            this.controller = controller;
        }
    }

    public void comenzar() throws OperationNotSupportedException {

        // Esto es para que tenga que presionar el usuario por cada opcion
        Scanner continuar = new Scanner(System.in);

        Opcion opcionElegida;
        do {
            Consola.mostrarMenu();
            opcionElegida = Consola.elegirOpcion();
            ejecutarOpcion(opcionElegida);

            System.out.println("\n\t\tPRESIONE ENTER PARA CONTINUAR..."); //Mensaje en pantalla
            continuar.nextLine();
        } while (!opcionElegida.equals(Opcion.SALIR));
    }

    public void terminar()
    {
        controller.terminar();
        System.out.println("Cerramos la vista");
    }


    //Metodos
    private static void ejecutarOpcion(Opcion opcion) throws OperationNotSupportedException {

        switch (opcion) {
            //Alumnos
            case INSERTAR_ALUMNO -> insertarAlumno();
            case BUSCAR_ALUMNO -> buscarAlumno();
            case BORRAR_ALUMNO -> borrarAlumno();
            case MOSTRAR_ALUMNOS -> mostarAlumnos();

            //Ciclos Formativos
            case INSERTAR_CICLO_FORMATIVO -> insertarCicloFormativo();
            case BUSCAR_CICLO_FORMATIVO -> buscarCicloFormativo();
            case BORRAR_CICLO_FORMATIVO -> borrarCicloFormativo();
            case MOSTRAR_CICLOS_FORMATIVOS -> mostarCiclosFormativos();

            //Asignaturas
            case INSERTAR_ASIGNATURA -> insertarAsignatura();
            case BUSCAR_ASIGNATURA -> buscarAsignatura();
            case BORRAR_ASIGNATURA -> borrarAsignatura();
            case MOSTRAR_ASIGNATURAS -> mostrarAsignaturas();

            //Matriculas
            case INSERTAR_MATRICULA -> insertarMatricula();
            case BUSCAR_MATRICULA -> buscarMatricula();
            case MOSTRAR_MATRICULAS -> mostrarMatriculas();
            case MOSTRAR_MATRICULAS_POR_ALUMNO -> mostrarMatriculasPorAlumno();
            case MOSTRAR_MATRICULAS_POR_CICLO_FORMATIVO -> mostrarMatriculasPorCicloFormativo();
            case MOSTRAR_MATRICULAS_POR_CURSO_ACADEMICO -> mostrarMatriculasPorCursoAcademico();
            case ANULAR_MATRICULA -> anularMatricula();
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*------------------------------------------------ ALUMNOS ------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    private static void insertarAlumno(){

        Alumno alumno = new Alumno(Consola.leerAlumno());

        controller.insertar(alumno);
        System.out.println("Alumno insertado correctamente");

    }

    private static void buscarAlumno() {
        Alumno alumno = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoBuscado = null;

        alumnoBuscado = controller.buscar(alumno);
        if (alumnoBuscado != null){
            System.out.print(alumnoBuscado.imprimir());
        }
        else{
            throw new IllegalArgumentException("No existe ningun alumno con ese DNI");
        }
    }

    private static void borrarAlumno() throws OperationNotSupportedException {

        controller.borrar(Consola.getAlumnoPorDni());
    }

    private static void mostarAlumnos(){

        Alumno[] alumnosMostar =  controller.getAlumnos();

        if (alumnosMostar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen alumnos para mostrar.");
        }

        for (Alumno alumno : alumnosMostar) {
            System.out.println(alumno.imprimir());
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- ASIGNATURAS ----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    private static void insertarAsignatura(){
        CicloFormativo cicloFormativoAsignatura = Consola.getCicloFormativoPorCodigo();
        CicloFormativo ciclosExistentes = controller.buscar(cicloFormativoAsignatura);

        if (ciclosExistentes != null) {
            Asignatura asignatura = new Asignatura(Consola.leerAsignatura(ciclosExistentes));
            controller.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente");
        }else{
            throw new IllegalArgumentException("ERROR: No se ha podido ingresar la asignatura");
        }
    }

    private static void buscarAsignatura(){

        Asignatura asignatura = new Asignatura(Consola.getAsignaturaPorCodigo());
        Asignatura asigBuscado = null;

        asigBuscado = controller.buscar(asignatura);
        if (asigBuscado != null){
            System.out.println(asigBuscado.imprimir());
        }
        else{
            throw new IllegalArgumentException("ERROR:: No existe ninguna asignatura con ese codigo");
        }
    }

    private static void borrarAsignatura() throws OperationNotSupportedException {
        controller.borrar(Consola.getAsignaturaPorCodigo());
    }

    private static void mostrarAsignaturas(){

        Consola.mostrarAsignautras(controller.getAsignaturas());
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- CICLOS FORMATIVOS ---------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/
    private static void insertarCicloFormativo(){

        CicloFormativo cicloFormativo = new CicloFormativo(Consola.leerCicloFormativo());

        controller.insertar(cicloFormativo);
        System.out.println("Ciclo Formativo insertado correctamente");

    }

    private static void buscarCicloFormativo (){

        CicloFormativo cicloFormativo = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
        CicloFormativo cicloBuscado = null;

        cicloBuscado = controller.buscar(cicloFormativo);
        if (cicloBuscado != null){
            System.out.println(cicloBuscado.imprimir());
        }
        else{
            throw new IllegalArgumentException("ERROR: No existe ningun ciclo formativo con ese codigo");
        }
    }

    private static void borrarCicloFormativo() throws OperationNotSupportedException {
        controller.borrar(Consola.getCicloFormativoPorCodigo());
    }

    private static void mostarCiclosFormativos(){

        Consola.mostrarCiclosFormativos(controller.getCicloformativos());

    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- MATRICULAS -----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    private static void insertarMatricula() throws OperationNotSupportedException {

        // Conseguir el usuario
        Alumno alumnoMatriculado = Consola.getAlumnoPorDni();
        Alumno alumnoExistente = controller.buscar(alumnoMatriculado);

        // Conseguir las asignaturas
        Asignatura[] asignaturasRegistradas = controller.getAsignaturas();
        if (asignaturasRegistradas == null){
            throw new IllegalArgumentException("ERROR: No existen asignaturas registradas");
        }

        Asignatura[] asignaturasMatricula = Consola.elegirAsignaturasMatricula(asignaturasRegistradas);

        Matricula matricula = new Matricula(Consola.leerMatricula(alumnoExistente, asignaturasMatricula));

        controller.insertar(matricula);
        System.out.println("Matricula insertado correctamente");
    }

    private static void buscarMatricula(){

        Matricula matricula = new Matricula(Consola.getMatriculaPorIdentificador());
        Matricula matriculaBuscado = null;

        matriculaBuscado = controller.buscar(matricula);
        if (matriculaBuscado != null){
            System.out.println(matriculaBuscado.imprimir());
        }
        else{
            throw new IllegalArgumentException("ERROR: No existe ninguna matricula con ese codigo");
        }
    }

    private static void anularMatricula() throws OperationNotSupportedException {

        controller.borrar(Consola.getMatriculaPorIdentificador());

    }

    private static void mostrarMatriculas() {

        Matricula[] matriculaMostar =  controller.getMatriculas();

        if (matriculaMostar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen datos");
        }

        for (Matricula matricula : matriculaMostar) {
            if (matricula != null){
                System.out.println(matricula.imprimir());
            }else{
                break;
            }
        }
    }

    private static void mostrarMatriculasPorAlumno(){

        Alumno alumnoBuscado = Consola.getAlumnoPorDni();
        Alumno nuevoAlumno = controller.buscar(alumnoBuscado);

        if (nuevoAlumno == null) {
            throw new IllegalArgumentException("ERROR: No existen matricula de ese alumnos para mostrar.");
        }else{
            Matricula[] matriculaMostrar = controller.getMatriculas(nuevoAlumno);
            for (Matricula matricula : matriculaMostrar) {
                if (matricula != null){
                    System.out.println(matricula.imprimir());
                }else{
                    break;
                }
            }
        }
    }

    private static void mostrarMatriculasPorCicloFormativo(){

        CicloFormativo cicloFormativoMostrar = Consola.getCicloFormativoPorCodigo();
        Matricula[] matriculaMostrar = controller.getMatriculas(cicloFormativoMostrar);

        if (matriculaMostrar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen matriculas con ese ciclo formativo para mostrar.");
        }

        for (Matricula matricula : matriculaMostrar) {
            if (matricula != null){
                System.out.println(matricula.imprimir());
            }else{
                break;
            }
        }
    }

    private static void mostrarMatriculasPorCursoAcademico(){

        System.out.print("Introduzca el curso academicio:");
        String cursoAcademico = Entrada.cadena();


        Matricula[] matriculaMostrar = controller.getMatriculas(cursoAcademico);

        if (matriculaMostrar.length == 0) {
            throw new IllegalArgumentException("ERROR: No existen matriculas con ese curso academico para mostrar.");
        }

        for (Matricula matricula : matriculaMostrar) {
            if (matricula != null){
                System.out.println(matricula.imprimir());
            }else{
                break;
            }
        }
    }
}
