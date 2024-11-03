import java.util.Objects;

class Arbol {
    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    /**
     * Verifica si existe un archivo o carpeta en el arbol
     * Recibe el nombre del archivo o carpeta y devuelve true si existe, false si no
     */
    public boolean existe(String busqueda) {
        return existe(this.raiz, busqueda);
    }

    /**
     * Metodo recursivo para buscar un nodo en el arbol
     * Recibe el nodo actual y el nombre a buscar, devolviendo true si existe
     */
    private boolean existe(Nodo nodo, String busqueda) {
        if (nodo == null) {
            return false;
        }
        // Compara dos cadenas de texto y determina si busca a la izquierda (< 0)
        // o derecha (>= 0)
        if (Objects.equals(nodo.getNombre(), busqueda)) {
            return true;
        } else if (busqueda.compareTo(nodo.getNombre()) < 0) {
            return existe(nodo.getIzquierda(), busqueda);
        } else {
            return existe(nodo.getDerecha(), busqueda);
        }
    }

    /**
     * Inserta un nuevo archivo o carpeta en el arbol alfabeticamente
     * Si el arbol esta vacio, lo establece como raiz
     */
    public void insertar(String nombre, String tipo) {
        if (this.raiz == null) {
            this.raiz = new Nodo(nombre, tipo);
        } else {
            this.insertar(this.raiz, nombre, tipo);
        }
    }

    /**
     * Metodo recursivo para insertar un nuevo nodo en el arbol
     * Recibe el nodo actual, nombre y tipo del nodo a insertar
     */
    private void insertar(Nodo padre, String nombre, String tipo) {
        // Compara dos cadenas de texto y determina si va a la derecha (> 0)
        // o izquierda (<= 0)
        if (nombre.compareTo(padre.getNombre()) > 0) {
            if (padre.getDerecha() == null) {
                padre.setDerecha(new Nodo(nombre, tipo));
            } else {
                this.insertar(padre.getDerecha(), nombre, tipo);
            }
        } else {
            if (padre.getIzquierda() == null) {
                padre.setIzquierda(new Nodo(nombre, tipo));
            } else {
                this.insertar(padre.getIzquierda(), nombre, tipo);
            }
        }
    }

    /**
     * Muestra el arbol en orden alfabetico (inorden).
     */
    public void inorden() {
        this.inorden(this.raiz);
    }

    /**
     * Metodo recursivo para mostrar el arbol en orden alfabetico.
     * Muestra el nodo actual empezando por el subarbol izquierdo y despues el derecho
     */
    private void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.getIzquierda());
            System.out.println(nodo.getNombre() + " (" + nodo.getTipo() + ")");
            inorden(nodo.getDerecha());
        }
    }

    /**
     * Cuenta la cantidad de archivos y carpetas en el arbol.
     * Devuelve el numero total de nodos en el arbol.
     */
    public int contarNodos() {
        return contarNodos(this.raiz);
    }

    /**
     * Metodo recursivo para contar los nodos en el arbol.
     * Suma uno por el nodo actual y recorre los subarboles izquierdo y derecho.
     */
    private int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }
}
