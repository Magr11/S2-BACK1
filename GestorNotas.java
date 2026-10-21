import java.util.ArrayList;
import java.util.List;

public class GestorNotas {
    private final ArrayList<Estudiante> estudiantes;
    private final ArrayList<Nota> notas;

    public GestorNotas() {
        estudiantes = new ArrayList<>();
        notas = new ArrayList<>();
    }

    public boolean agregarEstudiante(Estudiante estudiante) {
        if (estudiante == null || buscarEstudiante(estudiante.getId()) != null) {
            return false;
        }

        estudiantes.add(estudiante);
        return true;
    }

    public Estudiante buscarEstudiante(int id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                return estudiante;
            }
        }
        return null;
    }

    public boolean agregarNota(Nota nota) {
        if (nota == null ||
            buscarEstudiante(nota.getEstudiante().getId()) == null) {
            return false;
        }

        notas.add(nota);
        return true;
    }

    public List<Estudiante> getEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    public List<Nota> getNotas() {
        return new ArrayList<>(notas);
    }

    public List<Nota> getNotasDeEstudiante(int id) {
        ArrayList<Nota> resultado = new ArrayList<>();

        for (Nota nota : notas) {
            if (nota.getEstudiante().getId() == id) {
                resultado.add(nota);
            }
        }

        return resultado;
    }
}