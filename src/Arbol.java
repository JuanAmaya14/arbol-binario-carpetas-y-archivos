import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa un arbol binario donde se almacenan archivos y carpetas.
 * Permite insertar, buscar y mostrar los archivos y carpetas en diferentes ordenes.
 *
 * @author Juan Amaya
 * @author Melisa Acevedo
 */
public class Arbol {
    private Nodo raiz;

    /**
     * Constructor de la clase Arbol. Inicializa el arbol vacio.
     */
    public Arbol() {
        this.raiz = null;
    }

    /**
     * Obtiene la raiz del arbol.
     *
     * @return Nodo raiz del arbol.
     */
    public Nodo getRaiz() {
        return raiz;
    }

    /**
     * Verifica si un archivo o carpeta existe en el arbol.
     *
     * @param busqueda Nombre del archivo o carpeta a buscar.
     * @return true si existe, false si no existe.
     */
    public boolean existe(String busqueda) {
        return existe(this.raiz, busqueda);
    }

    /**
     * Inserta un nuevo archivo o carpeta en el arbol.
     *
     * @param nombre Nombre del archivo o carpeta a insertar.
     * @param tipo Tipo del nodo ("Archivo" o "Carpeta").
     * @param nombreCarpeta Nombre de la carpeta donde se insertara el archivo o subcarpeta.
     */
    public void insertar(String nombre, String tipo, String nombreCarpeta) {
        // si NO existe un nodo padre
        if (this.raiz == null) {
            // el nodo padre si o si debe de ser una carpeta
            if (tipo.equals("Carpeta")) {
                this.raiz = new Nodo(nombre, tipo);
            } else {
                System.out.println("Primero debe insertar una carpeta raiz.");
            }
        } else {
            // si ya existe un nodo padre entonces busca la carpeta para insertar otra carpeta o archivo
            Nodo carpetaSeleccionada = buscarCarpeta(this.raiz, nombreCarpeta);
            // mira si la carpeta existe
            if (carpetaSeleccionada == null || !carpetaSeleccionada.getTipo().equals("Carpeta")) {
                System.out.println("La carpeta especificada no existe.");
                return;
            }
            insertarEnCarpeta(carpetaSeleccionada, nombre, tipo);
        }
    }

    /**
     * Muestra los archivos y carpetas en orden alfabetico.
     */
    public void ordenAlfabetico() {
        List<Nodo> nodos = new ArrayList<>();
        recogerNodos(this.raiz, nodos);

        // Ordena la lista de nodos alfabeticamente por nombre
        nodos.sort(Comparator.comparing(Nodo::getNombre));

        // Imprime los nodos en orden alfabetico
        for (Nodo nodo : nodos) {
            System.out.println(nodo.getNombre() + " (" + nodo.getTipo() + ")");
        }
    }

    /**
     * Cuenta el total de archivos y carpetas en el arbol.
     *
     * @return El total de nodos (archivos y carpetas).
     */
    public int contarNodos() {
        return contarNodos(this.raiz);
    }

    /**
     * Muestra la estructura jerarquica del arbol.
     */
    public void imprimirEstructura() {
        imprimirEstructura(this.raiz, 0);
    }

    // Métodos privados

    private boolean existe(Nodo nodo, String busqueda) {
        if (nodo == null) {
            return false;
        }
        if (Objects.equals(nodo.getNombre(), busqueda)) {
            return true;
        }
        return existe(nodo.getIzquierda(), busqueda) || existe(nodo.getDerecha(), busqueda);
    }

    private void insertarEnCarpeta(Nodo carpeta, String nombre, String tipo) {
        if (carpeta.getIzquierda() != null && carpeta.getDerecha() != null) {
            System.out.println("La carpeta esta llena");
            return;
        }

        if ((tipo.equals("Archivo") && archivoExiste(nombre)) || (tipo.equals("Carpeta") && carpetaExiste(nombre))) {
            System.out.println("Ya existe un " + tipo + " con el mismo nombre");
            return;
        }

        Nodo nuevoNodo = new Nodo(nombre, tipo);
        if (carpeta.getIzquierda() == null) {
            carpeta.setIzquierda(nuevoNodo);
        } else if (carpeta.getDerecha() == null) {
            carpeta.setDerecha(nuevoNodo);
        }
    }

    private boolean archivoExiste(String nombre) {
        return existe(this.raiz, nombre);
    }

    private boolean carpetaExiste(String nombre) {
        return existe(this.raiz, nombre);
    }

    private Nodo buscarCarpeta(Nodo nodo, String nombreCarpeta) {
        if (nodo == null) {
            return null;
        }

        if (nodo.getNombre().equals(nombreCarpeta) && nodo.getTipo().equals("Carpeta")) {
            return nodo;
        }

        Nodo resultado = buscarCarpeta(nodo.getIzquierda(), nombreCarpeta);
        if (resultado != null) {
            return resultado;
        }
        return buscarCarpeta(nodo.getDerecha(), nombreCarpeta);
    }

    private void recogerNodos(Nodo nodo, List<Nodo> nodos) {
        if (nodo != null) {
            nodos.add(nodo);
            recogerNodos(nodo.getIzquierda(), nodos);
            recogerNodos(nodo.getDerecha(), nodos);
        }
    }

    private int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }

    private void imprimirEstructura(Nodo nodo, int profundidad) {
        if (nodo != null) {
            for (int i = 0; i < profundidad; i++) {
                System.out.print("  ");
            }
            System.out.println(nodo.getNombre() + " (" + nodo.getTipo() + ")");
            imprimirEstructura(nodo.getIzquierda(), profundidad + 1);
            imprimirEstructura(nodo.getDerecha(), profundidad + 1);
        }
    }
}
