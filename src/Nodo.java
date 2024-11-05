public class Nodo {
    private String nombre;
    private String tipo;
    private Nodo izquierda, derecha;

    public Nodo(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }
}
