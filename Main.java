import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GestorNotas gestor = new GestorNotas();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE NOTAS =====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar nota");
            System.out.println("3. Mostrar estudiantes");
            System.out.println("4. Mostrar todas las notas");
            System.out.println("5. Consultar notas de un estudiante");
            System.out.println("0. Salir");

            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1:
                    registrarEstudiante();
                    break;
                case 2:
                    registrarNota();
                    break;
                case 3:
                    listarEstudiantes();
                    break;
                case 4:
                    listarNotas();
                    break;
                case 5:
                    consultarNotasEstudiante();
                    break;
                case 0:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void registrarEstudiante() {
        try {
            int id = leerEntero("ID del estudiante: ");

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            int edad = leerEntero("Edad: ");

            Estudiante estudiante = new Estudiante(id, nombre, edad);

            if (gestor.agregarEstudiante(estudiante)) {
                System.out.println("Estudiante registrado correctamente.");
            } else {
                System.out.println("El ID ya existe.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void registrarNota() {
        try {
            int id = leerEntero("ID del estudiante: ");
            Estudiante estudiante = gestor.buscarEstudiante(id);

            if (estudiante == null) {
                System.out.println("Estudiante no encontrado.");
                return;
            }

            System.out.print("Asignatura: ");
            String asignatura = scanner.nextLine();

            double calificacion = leerDouble("Nota (0 a 5): ");

            Nota nota = new Nota(estudiante, asignatura, calificacion);

            if (gestor.agregarNota(nota)) {
                System.out.println("Nota registrada correctamente.");
            } else {
                System.out.println("No se pudo registrar la nota.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarEstudiantes() {
        List<Estudiante> estudiantes = gestor.getEstudiantes();

        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }

    private static void listarNotas() {
        List<Nota> notas = gestor.getNotas();

        if (notas.isEmpty()) {
            System.out.println("No hay notas registradas.");
            return;
        }

        for (Nota nota : notas) {
            System.out.println(nota);
        }
    }

    private static void consultarNotasEstudiante() {
        int id = leerEntero("ID del estudiante: ");
        Estudiante estudiante = gestor.buscarEstudiante(id);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        List<Nota> notas = gestor.getNotasDeEstudiante(id);

        if (notas.isEmpty()) {
            System.out.println("El estudiante no tiene notas.");
            return;
        }

        double suma = 0;

        for (Nota nota : notas) {
            System.out.println(nota);
            suma += nota.getCalificacion();
        }

        double promedio = suma / notas.size();

        System.out.printf("Promedio: %.2f%n", promedio);
        System.out.println(promedio >= 3.0 ? "Aprobó" : "Reprobó");
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);

            try {
                return Double.parseDouble(
                    scanner.nextLine().trim().replace(',', '.')
                );
            } catch (NumberFormatException e) {
                System.out.println("Ingresa una nota válida.");
            }
        }
    }
}