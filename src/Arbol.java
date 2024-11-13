import java.util.*;

public class Arbol {
    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    // Verifica si existe un archivo en el arbol
    public boolean existe(String busqueda) {
        return existe(this.raiz, busqueda);
    }

    // Inserta un nuevo archivo o carpeta, pidiendo seleccion de carpeta
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

    // Muestra el arbol en orden alfabetico.
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

    // Cuenta la cantidad de archivos y carpetas en el árbol.
    public int contarNodos() {
        return contarNodos(this.raiz);
    }

    // imprimir la estructura del arbol en su orden de jerarquía
    public void imprimirEstructura() {
        imprimirEstructura(this.raiz, 0);
    }


    // Métodos privados

    // buscar un nodo en el arbol sin considerar el orden
    private boolean existe(Nodo nodo, String busqueda) {
        // si el nodo no exite devuelve falso
        if (nodo == null) {
            return false;
        }
        // si existe devuelve verdadero
        if (Objects.equals(nodo.getNombre(), busqueda)) {
            return true;
        }
        // Busca en ambos subarboles sin seguir un orden específico usando recursividad
        return existe(nodo.getIzquierda(), busqueda) || existe(nodo.getDerecha(), busqueda);
    }


    // Inserta en una carpeta seleccionada si hay espacio disponible
    private void insertarEnCarpeta(Nodo carpeta, String nombre, String tipo) {
        // mira si la carpeta esta llena (tiene 2 hijos)
        if (carpeta.getIzquierda() != null && carpeta.getDerecha() != null) {
            System.out.println("La carpeta esta llena");
            return;
        }

        // no pueden haber archivos o carpetas con el mismo nombre
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

        System.out.println(nuevoNodo.getTipo() + " de nombre " + nuevoNodo.getNombre() + " se inserto correctamente en la carpeta " + carpeta.getNombre());
    }

    // Metodo para buscar una carpeta especifica
    private Nodo buscarCarpeta(Nodo nodo, String nombreCarpeta) {
        if (nodo == null) {
            return null;
        }

        // si el nodo actual tiene el mismo nombre especificado y ademas si su tipo es "Carpeta".
        if (nodo.getNombre().equals(nombreCarpeta) && nodo.getTipo().equals("Carpeta")) {
            return nodo;
        }

        //busca sub arbol izquierdo
        Nodo izquierda = buscarCarpeta(nodo.getIzquierda(), nombreCarpeta);
        if (izquierda != null) {
            return izquierda;
        }

        //Busca sub arbol derecho usando recursividad
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

    // recorrer el arbol y recoger los nodos en la lista
    private void recogerNodos(Nodo nodo, List<Nodo> nodos) {
        if (nodo != null) {
            nodos.add(nodo);
            // izquierda y derecha usando recusividad
            recogerNodos(nodo.getIzquierda(), nodos);
            recogerNodos(nodo.getDerecha(), nodos);
        }
    }

    private int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        //recorre los nodos de izquierda a derecha usando recursividad y los va contando
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }

    private void imprimirEstructura(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }

        // Indentacion basada en el nivel de profundidad
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        // Imprime el nombre y tipo del nodo actual
        System.out.println(nodo.getNombre() + " (" + nodo.getTipo() + ")");

        // Llama recursivamente para los subnodos izquierdo y derecho
        imprimirEstructura(nodo.getIzquierda(), nivel + 1);
        imprimirEstructura(nodo.getDerecha(), nivel + 1);
    }
}
