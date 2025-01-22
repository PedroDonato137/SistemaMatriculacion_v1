package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.matriculacion.vista.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class MainApp {

    // Constantes de la Clase
    public static final int CAPACIDAD = 3;

    //Atributos de la clase
    private static Alumnos alumnos = new Alumnos(CAPACIDAD);
    private static Matriculas matriculas = new Matriculas(CAPACIDAD);
    private static Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
    private static CiclosFormativos cicloFormativos = new CiclosFormativos(CAPACIDAD);


    // Datos ficticios(Borrar)
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


    public static void main(String[] args) throws OperationNotSupportedException {

        // Esto es para que tenga que presionar el usuario por cada opcion
        Opcion opcionElegida;
        Scanner neme = new Scanner(System.in);

        //Datos prueba
        datosInicio();

        do {
            Consola.mostrarMenu();
            opcionElegida = Consola.elegirOpcion();
            ejecutarOpcion(opcionElegida);

            System.out.println("\n\t\tPRESIONE ENTER PARA CONTINUAR..."); //Mensaje en pantalla
            neme.nextLine();
        }while (!opcionElegida.equals(Opcion.SALIR));

        System.out.println("Hasta luego Lucas!!!!");
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

            case null, default -> throw new IllegalArgumentException("ERROR: Opcion incorrecta");
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*------------------------------------------------ ALUMNOS ------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    private static void insertarAlumno(){

        Alumno alumno = new Alumno(Consola.leerAlumno());

        try {
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void buscarAlumno() throws OperationNotSupportedException {
        Alumno alumno = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoBuscado = null;

        alumnoBuscado = alumnos.buscar(alumno);
        if (alumnoBuscado != null){
            System.out.print(alumnoBuscado.imprimir());
        }
        else{
            System.out.println("No existe ningun alumno con ese DNI");
        }
    }

    private static void borrarAlumno() throws OperationNotSupportedException {

        alumnos.borrar(Consola.getAlumnoPorDni());
    }

    private static void mostarAlumnos(){

        Alumno[] alumnosMostar =  alumnos.get();

        if (alumnosMostar.length == 0) {
            //throw new IllegalArgumentException("ERROR: No existen alumnos para mostrar.");
            System.out.println("Error: No existen datos");
        }

        for (Alumno alumno : alumnosMostar) {
            System.out.println(alumno.imprimir());
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- ASIGNATURAS ----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    private static void insertarAsignatura(){
        if(cicloFormativos == null){
            throw new IllegalArgumentException("ERROR: No existen ningun Ciclo Formativo");
        }

        Asignatura asignatura = new Asignatura(Consola.leerAsignatura(cicloFormativos));

        try {
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void buscarAsignatura(){

        Asignatura asignatura = new Asignatura(Consola.getAsignaturaPorCodigo());
        Asignatura asigBuscado = null;

        asigBuscado = asignaturas.buscar(asignatura);
        if (asigBuscado != null){
            System.out.println(asigBuscado.imprimir());
        }
        else{
            System.out.println("No existe ninguna asignatura con ese codigo");
        }
    }

    private static void borrarAsignatura() throws OperationNotSupportedException {
        asignaturas.borrar(Consola.getAsignaturaPorCodigo());
    }

    private static void mostrarAsignaturas(){

        Consola.mostrarAsignautras(asignaturas);
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- CICLOS FORMATIVOS ---------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    private static void insertarCicloFormativo(){

        CicloFormativo cicloFormativo = new CicloFormativo(Consola.leerCicloFormativo());

        try {
            cicloFormativos.insertar(cicloFormativo);
            System.out.println("Ciclo Formativo insertado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    private static void buscarCicloFormativo (){

        CicloFormativo cicloFormativo = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
        CicloFormativo cicloBuscado = null;

        cicloBuscado = cicloFormativos.buscar(cicloFormativo);
        if (cicloBuscado != null){
            System.out.println(cicloBuscado.imprimir());
        }
        else{
            System.out.println("No existe ningun ciclo formativo con ese codigo");
        }
    }

    private static void borrarCicloFormativo() throws OperationNotSupportedException {
        cicloFormativos.borrar(Consola.getCicloFormativoPorCodigo());
    }

    private static void mostarCiclosFormativos(){

        Consola.mostrarCiclosFormativos(cicloFormativos);

    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- MATRICULAS -----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    private static void insertarMatricula() throws OperationNotSupportedException {

        Matricula matricula = new Matricula(Consola.leerMatricula(alumnos, asignaturas));

        try {
            matriculas.insertar(matricula);
            System.out.println("Matricula insertado correctamente");
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void buscarMatricula(){

        Matricula matricula = new Matricula(Consola.getMatriculaPorIdentificador());
        Matricula matriculaBuscado = null;

        matriculaBuscado = matriculas.buscar(matricula);
        if (matriculaBuscado != null){
            System.out.println(matriculaBuscado.imprimir());
        }
        else{
            System.out.println("No existe ninguna matricula con ese codigo");
        }
    }

    private static void anularMatricula() throws OperationNotSupportedException {

        matriculas.borrar(Consola.getMatriculaPorIdentificador());

    }

    private static void mostrarMatriculas() {

        Matricula[] matriculaMostar =  matriculas.get();

        if (matriculaMostar.length == 0) {
            System.out.println("Error: No existen datos");
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
        Alumno nuevoAlumno = alumnos.buscar(alumnoBuscado);

        if (nuevoAlumno == null) {
            throw new IllegalArgumentException("ERROR: No existen matricula de ese alumnos para mostrar.");
        }else{
            Matricula[] matriculaMostrar = matriculas.get(nuevoAlumno);
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
        Matricula[] matriculaMostrar = matriculas.get(cicloFormativoMostrar);

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


        Matricula[] matriculaMostrar = matriculas.get(cursoAcademico);

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
