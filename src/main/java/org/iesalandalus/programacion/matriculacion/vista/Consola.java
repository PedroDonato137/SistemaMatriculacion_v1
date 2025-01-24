package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    private Consola() {
        //Constructor privado para no poder instanciarlo
    }

    public static void mostrarMenu(){
        System.out.println("=== Opciones del menú ===");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }
    public static Opcion elegirOpcion(){

        int ordinalOpcion = -1;
        do {
            System.out.print("Selecciona que opción quiere realizar: ");
            ordinalOpcion = Entrada.entero();
        } while (ordinalOpcion < 0 || ordinalOpcion > Opcion.values().length);

        return Opcion.values()[ordinalOpcion];
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*------------------------------------------------ ALUMNOS ------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static Alumno leerAlumno(){

        String nombreAlumno;
        String dniAlumno;
        String correoAlumno;
        String telefonoAlumno;

        do {
            System.out.print("Introduce el nombre del Alumno: ");
            nombreAlumno = Entrada.cadena();
        } while (nombreAlumno.isEmpty());

        do {
            System.out.print("Introduce el DNI del Alumno: ");
            dniAlumno = Entrada.cadena();
        } while (dniAlumno.isEmpty());

        do {
            System.out.print("Introduce el correo del Alumno: ");
            correoAlumno = Entrada.cadena();
        } while (correoAlumno.isEmpty());

        do {
            System.out.print("Introduce el telefono del Alumno: ");
            telefonoAlumno = Entrada.cadena();
        } while (telefonoAlumno.isEmpty());

        try {
            return new Alumno(nombreAlumno, dniAlumno, correoAlumno, telefonoAlumno, leerFecha("Introduce la fecha de nacimiento(dd/mm/yyyy): "));
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el alumno con el DNI proporcionado.", e);
        }
    }

    public static Alumno getAlumnoPorDni() {

        String dniAlumno;
        // Crear datos ficticios para el alumno
        String nombreFicticio = "Ficticio";
        String telefonoFicticio = "609822699";
        String correoFicticio = "correo@ficticio.com";
        LocalDate fechaNacimientoFicticio = LocalDate.of(1990, 6, 9); // Fecha ficticia válida (+16)

        do {
            System.out.print("Introduce el DNI del Alumno: ");
            dniAlumno = Entrada.cadena();
        } while (dniAlumno.isEmpty());

        try {
            return new Alumno(nombreFicticio, dniAlumno, correoFicticio, telefonoFicticio, fechaNacimientoFicticio);
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el alumno con el DNI proporcionado.", e);
        }
    }

    public static LocalDate leerFecha(String mensaje){
        LocalDate fecha = null;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (fecha == null) {
            System.out.print(mensaje);
            String fechaLeida = Entrada.cadena();

            try {
                fecha = LocalDate.parse(fechaLeida, formatoFecha);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha incorrecta");
            }
        }

        return fecha;
    }

    public static Grado leerGrado(){

        int opcionGrado;
        System.out.println("Seleccione un grado de la lista:");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionGrado = Entrada.entero();
        } while (opcionGrado < 0 || opcionGrado > Grado.values().length);

        return Grado.values()[opcionGrado];
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- CICLOS FORMATIVOS ---------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static CicloFormativo leerCicloFormativo(){

        int codigoCiclo;
        String familiaProfesionalCiclo;
        Grado gradoCiclo;
        String nombreCiclo;
        int horasCiclo;

        CicloFormativo nuevoGrado = null;

        do {
            System.out.print("Introduce el código del ciclo formativo: ");
            codigoCiclo = Entrada.entero();
        } while (codigoCiclo < 1000 || codigoCiclo > 9999);

        do {
            System.out.print("Introduce la familia profesional: ");
            familiaProfesionalCiclo = Entrada.cadena();
        } while (familiaProfesionalCiclo.isEmpty());

        gradoCiclo = Consola.leerGrado();

        do {
            System.out.print("Introduce el nombre del ciclo formativo: ");
            nombreCiclo = Entrada.cadena();
        } while (nombreCiclo.isEmpty());

        do {
            System.out.print("Introduce el horas del ciclo formativo: ");
            horasCiclo = Entrada.entero();
        } while (horasCiclo < 0 || horasCiclo > 2000);

        nuevoGrado = new CicloFormativo(codigoCiclo, familiaProfesionalCiclo, gradoCiclo, nombreCiclo, horasCiclo);

        return new CicloFormativo(nuevoGrado);

    }

    public static void mostrarCiclosFormativos(CicloFormativo[] ciclosFormativos){


        if (ciclosFormativos.length == 0) {
            //throw new IllegalArgumentException("ERROR: No existen ciclos formativos para mostrar.");
            System.out.println("Error: No hay datos para mostrar");
        }

        for (CicloFormativo cicloFormativo : ciclosFormativos) {
            System.out.println(cicloFormativo.imprimir());
        }


    }

    public static CicloFormativo getCicloFormativoPorCodigo() {

        int codigoCiclo;
        // Crear datos ficticios para el ciclo formativo
        String familiaProfesionalCiclo = "Familia profesional Ficticia";
        Grado gradoCiclo = Grado.GDCFGB;
        String nombreCiclo = "Ficticio";
        int horasCiclo = 25;

        do {
            System.out.print("Introduce el Codigo del ciclo formativo: ");
            codigoCiclo = Entrada.entero();
        } while (codigoCiclo == 0);

        try {
            return new CicloFormativo(codigoCiclo, familiaProfesionalCiclo, gradoCiclo, nombreCiclo, horasCiclo );
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el ciclo formativo con ese código", e);
        }
    }

    public static Curso leerCurso(){

        int opcionCurso;
        System.out.println("Seleccione un curso de la lista:");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionCurso = Entrada.entero();
        } while (opcionCurso < 0 || opcionCurso > Curso.values().length);

        return Curso.values()[opcionCurso];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado(){

        int opcionEspecialidad;
        System.out.println("Seleccione una especialidad de la lista:");
        for (EspecialidadProfesorado especialidadProfesorado : EspecialidadProfesorado.values()) {
            System.out.println(especialidadProfesorado.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionEspecialidad = Entrada.entero();
        } while (opcionEspecialidad < 0 || opcionEspecialidad > EspecialidadProfesorado.values().length);

        return EspecialidadProfesorado.values()[opcionEspecialidad];
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- ASIGNATURAS ----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static Asignatura leerAsignatura(CiclosFormativos cicloFormativos){

        String codigoAsignatura;
        String nombreAsignatura;
        int horasAnualesAsignatura;
        Curso cursoAsignatura;
        int horasDesdobleAsignatura;
        EspecialidadProfesorado especialidadProfesoradoAsignatura;
        CicloFormativo cicloFormativoAsignatura;

        Asignatura nuevaAsignatura = null;

        //Variables auxiliales
        boolean existeCiclo = false;

        do {
            System.out.print("Introduce el Codigo de la asignatura: ");
            codigoAsignatura = Entrada.cadena();
        } while (codigoAsignatura.isEmpty());

        do {
            System.out.print("Introduce el nombre de la asignatura: ");
            nombreAsignatura = Entrada.cadena();
        } while (nombreAsignatura.isEmpty());

        do {
            System.out.print("Introduce las horas anuales de la asignatura: ");
            horasAnualesAsignatura = Entrada.entero();
        } while (horasAnualesAsignatura == 0);

        cursoAsignatura = Consola.leerCurso();

        do {
            System.out.print("Introduce las horas de desdoble de la asignatura: ");
            horasDesdobleAsignatura = Entrada.entero();
        } while (horasDesdobleAsignatura == 0);

        especialidadProfesoradoAsignatura = Consola.leerEspecialidadProfesorado();

        //Ciclo Formativo
        cicloFormativoAsignatura = getCicloFormativoPorCodigo(); // Creo un ciclo formativo solo con el codigo
        CicloFormativo[] ciclosExistentes = cicloFormativos.get(); // Recupero los ciclos formativos que existen

        for (CicloFormativo ciclosExistente : ciclosExistentes) {
            if (ciclosExistente.getCodigo() == cicloFormativoAsignatura.getCodigo()) {
                cicloFormativoAsignatura = ciclosExistente;
                existeCiclo = true;
            }
        }
        if (existeCiclo) {
            nuevaAsignatura = new Asignatura(codigoAsignatura, nombreAsignatura, horasAnualesAsignatura, cursoAsignatura, horasDesdobleAsignatura, especialidadProfesoradoAsignatura, cicloFormativoAsignatura);
            return new Asignatura(nuevaAsignatura);
        }else {
            return null;
        }
    }

    public static Asignatura getAsignaturaPorCodigo(){

        String codigoAsignatura;
        String nombreAsignatura = "Base de datos Ficticia";
        int horasAnualesAsignatura = 200;
        Curso cursoAsignatura = Curso.PRIMERO;
        int horasDesdobleAsignatura = 2;
        EspecialidadProfesorado especialidadProfesoradoAsignatura = EspecialidadProfesorado.INFORMATICA;
        CicloFormativo cicloFormativoAsignatura = new CicloFormativo(1001, "Informática y Comunicaciones", Grado.GDCFGB, "DAW", 500 );

        do {
            System.out.print("Introduce el Codigo de la asignatura: ");
            codigoAsignatura = Entrada.cadena();
        } while (codigoAsignatura.isEmpty());

        try {
            return new Asignatura(codigoAsignatura, nombreAsignatura, horasAnualesAsignatura, cursoAsignatura, horasDesdobleAsignatura, especialidadProfesoradoAsignatura, cicloFormativoAsignatura);
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear la asignatura con ese código", e);
        }

    }

    public static void mostrarAsignautras(Asignatura[] asignaturas){

        if (asignaturas.length == 0) {
            //throw new IllegalArgumentException("ERROR: No existen asignaturas para mostrar.");
            System.out.println("Error: No existen datos");
        }

        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura.imprimir());
        }

    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- MATRICULAS -----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    private static boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura){

        if (asignaturasMatricula == null || asignatura == null) {
            throw new IllegalArgumentException("ERROR: Ni la lista ni la asignatura pueden ser nulas.");
        }

        for (Asignatura asignaturaEnLista : asignaturasMatricula) {
            if (asignaturaEnLista != null && asignaturaEnLista.getCodigo().equalsIgnoreCase(asignatura.getCodigo())){
                return true; // La asignatura ya está en la lista.
            }
        }

        return false; // La asignatura no está en la lista.
    }

    public static Matricula leerMatricula(Alumnos alumnos, Asignaturas Asignaturas) throws OperationNotSupportedException {

        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;
        Alumno alumno = null;
        Asignatura[] coleccionAsignaturas = new Asignatura[3];

        Matricula nuevaMatricula = null;

        // Variables auxiliales
        Asignatura nuevaAsignaturas;
        int i = 0; // Varible auxiliar para las asignaturas

        do {
            System.out.print("Introduce el ID de la matricula: ");
            idMatricula = Entrada.entero();
        } while (idMatricula == 0);

        do {
            System.out.print("Introduce el Curso academico: ");
            cursoAcademico = Entrada.cadena();
        } while (cursoAcademico.isEmpty());

        fechaMatriculacion = leerFecha("Introduce la fecha de matriculación: ");

        //Buscar alumno
        Alumno alumnoBuscar = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoBuscado = null;

        alumnoBuscado = alumnos.buscar(alumnoBuscar);
        if (alumnoBuscado != null){
            alumno = alumnoBuscado;
        }
        else{
            System.out.println("No existe ningun alumno con ese DNI");
        }

        // Buscar Asignaturas
        do {
            nuevaAsignaturas = getAsignaturaPorCodigo();
            Asignatura AsignaturaBuscada = Asignaturas.buscar(nuevaAsignaturas);
            if(AsignaturaBuscada != null){
                if(!asignaturaYaMatriculada(coleccionAsignaturas, nuevaAsignaturas)){
                    coleccionAsignaturas[i] = nuevaAsignaturas;
                    i++;
                }else{
                    System.out.println("Error: Ya esta matriculado en esa asignatura");
                }
            }else{
                System.out.println("La asignatura no existe");
            }
        } while(i != 3); // He puesto que tiene que matricularse en 3 asignaturas obligatoriamente

        nuevaMatricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, coleccionAsignaturas );
        return new Matricula(nuevaMatricula);
    }

    public static Matricula getMatriculaPorIdentificador(){

        int idMatricula;
        // Datos ficticios
        String cursoAcademico = "24-25";
        //Fecha matricula
        String fechaMatriculacion = "21/01/2025";
        LocalDate fecha = null;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fecha = LocalDate.parse(fechaMatriculacion, formatoFecha);

        LocalDate fechaNacimientoFicticio = LocalDate.of(1990, 6, 9); // Fecha ficticia válida (+16)

        Alumno alumno = new Alumno("Pedro", "54119272L", "pedrodonatogarcia@gmail.com", "609822699", fechaNacimientoFicticio);
        Asignatura[] coleccionAsignaturas = new Asignatura[3];
        CicloFormativo cicloFicticio = new CicloFormativo(1001, "Informatica", Grado.GDCFGS, "Informatica", 2000);
        Asignatura asignaturaFicticia = new Asignatura("1001", "Base Datos", 100, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cicloFicticio);
        coleccionAsignaturas[0] = asignaturaFicticia;

        Matricula nuevaMatricula = null;

        do {
            System.out.print("Introduce el ID de la matrícula: ");
            idMatricula = Entrada.entero();
        } while (idMatricula == 0);

        nuevaMatricula = new Matricula(idMatricula, cursoAcademico, fecha, alumno, coleccionAsignaturas );
        return new Matricula(nuevaMatricula);
    }

}
