import java.util.Objects;

public class Arbol {
    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    // Verifica si existe un archivo o carpeta en el árbol
    public boolean existe(String busqueda) {
        return existe(this.raiz, busqueda);
    }

    // Inserta un nuevo archivo o carpeta, pidiendo seleccion de carpeta
    public void insertar(String nombre, String tipo, String nombreCarpeta) {
        if (this.raiz == null) {
            if (tipo.equals("Carpeta")) {
                this.raiz = new Nodo(nombre, tipo);
            } else {
                System.out.println("Primero debe insertar una carpeta raiz.");
            }
        } else {
            Nodo carpetaSeleccionada = buscarCarpeta(this.raiz, nombreCarpeta);
            if (carpetaSeleccionada == null || !carpetaSeleccionada.getTipo().equals("Carpeta")) {
                System.out.println("La carpeta especificada no existe.");
                return;
            }
            insertarEnCarpeta(carpetaSeleccionada, nombre, tipo);
        }
    }

    // Muestra el árbol en orden alfabetico.
    public void ordenAlfabetico() {
        this.ordenAlfabetico(this.raiz);
    }

    // Cuenta la cantidad de archivos y carpetas en el árbol.
    public int contarNodos() {
        return contarNodos(this.raiz);
    }

    // Métodos privados

    // Metodo recursivo para buscar un nodo en el árbol
    private boolean existe(Nodo nodo, String busqueda) {
        if (nodo == null) {
            return false;
        }
        if (Objects.equals(nodo.getNombre(), busqueda)) {
            return true;
        } else if (busqueda.compareTo(nodo.getNombre()) < 0) {
            return existe(nodo.getIzquierda(), busqueda);
        } else {
            return existe(nodo.getDerecha(), busqueda);
        }
    }

    // Inserta en una carpeta seleccionada si hay espacio disponible
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
        } else {
            carpeta.setDerecha(nuevoNodo);
        }
    }

    // Metodo para buscar una carpeta especifica
    private Nodo buscarCarpeta(Nodo nodo, String nombreCarpeta) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getNombre().equals(nombreCarpeta) && nodo.getTipo().equals("Carpeta")) {
            return nodo;
        }

        Nodo izquierda = buscarCarpeta(nodo.getIzquierda(), nombreCarpeta);
        if (izquierda != null) {
            return izquierda;
        }

        return buscarCarpeta(nodo.getDerecha(), nombreCarpeta);
    }

    // Metodos para verificar duplicados en archivos y carpetas

    private boolean archivoExiste(String nombre) {
        return archivoExiste(this.raiz, nombre);
    }

    private boolean carpetaExiste(String nombre) {
        return carpetaExiste(this.raiz, nombre);
    }

    private boolean archivoExiste(Nodo nodo, String nombre) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getTipo().equals("Archivo") && nodo.getNombre().equals(nombre)) {
            return true;
        }
        return archivoExiste(nodo.getIzquierda(), nombre) || archivoExiste(nodo.getDerecha(), nombre);
    }

    private boolean carpetaExiste(Nodo nodo, String nombre) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getTipo().equals("Carpeta") && nodo.getNombre().equals(nombre)) {
            return true;
        }
        return carpetaExiste(nodo.getIzquierda(), nombre) || carpetaExiste(nodo.getDerecha(), nombre);
    }

    // Muestra el nodo actual empezando por el subarbol izquierdo y despues el derecho
    private void ordenAlfabetico(Nodo nodo) {
        if (nodo != null) {
            ordenAlfabetico(nodo.getIzquierda());
            System.out.println(nodo.getNombre() + " (" + nodo.getTipo() + ")");
            ordenAlfabetico(nodo.getDerecha());
        }
    }

    private int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }
}
