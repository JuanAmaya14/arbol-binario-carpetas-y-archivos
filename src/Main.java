public class Main {
    public static void main(String[] args) {
        Arbol arbol = new Arbol();

        // Insertar archivos y carpetas en un orden específico
        arbol.insertar("Carpeta1", "Carpeta");
        arbol.insertar("Carpeta2", "Carpeta");
        arbol.insertar("Archivo1", "Archivo");
        arbol.insertar("Archivo2", "Archivo");
        arbol.insertar("Carpeta3", "Carpeta");
        arbol.insertar("Archivo3", "Archivo");

        System.out.println("Contenido del sistema en orden alfabético:");
        arbol.inorden();

        System.out.println("\nTotal de archivos y carpetas: " + arbol.contarNodos());
        System.out.println("¿Existe 'Archivo2'? " + arbol.existe("Archivo2"));
        System.out.println("¿Existe 'Carpeta4'? " + arbol.existe("Carpeta4"));
    }
}
