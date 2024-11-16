/**
 * Clase que representa un nodo en el arbol binario.
 * Cada nodo puede ser un archivo o una carpeta.
 *
 * @author Juan Amaya
 * @author Melisa Acevedo
 */
public class Nodo {
    private String nombre;
    private String tipo;
    private Nodo izquierda;
    private Nodo derecha;

    /**
     * Constructor de la clase Nodo.
     *
     * @param nombre Nombre del archivo o carpeta.
     * @param tipo Tipo de nodo (Archivo o Carpeta).
     */
    public Nodo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.izquierda = null;
        this.derecha = null;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
}
