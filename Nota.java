public class Nota {
    private Estudiante estudiante;
    private String asignatura;
    private double calificacion;

    public Nota() {
        this(new Estudiante(), "Sin asignatura", 0.0);
    }

    public Nota(Estudiante estudiante, String asignatura, double calificacion) {
        setEstudiante(estudiante);
        setAsignatura(asignatura);
        setCalificacion(calificacion);
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("Estudiante no válido.");
        }
        this.estudiante = estudiante;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        if (asignatura == null || asignatura.trim().isEmpty()) {
            throw new IllegalArgumentException("La asignatura no puede estar vacía.");
        }
        this.asignatura = asignatura.trim();
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        if (calificacion < 0 || calificacion > 5) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 5.");
        }
        this.calificacion = calificacion;
    }

    public String getEstado() {
        return calificacion >= 3.0 ? "Aprobó" : "Reprobó";
    }

    @Override
    public String toString() {
        return estudiante.getNombre() + " | " + asignatura
                + " | Nota: " + String.format("%.2f", calificacion)
                + " | Estado: " + getEstado();
    }
}