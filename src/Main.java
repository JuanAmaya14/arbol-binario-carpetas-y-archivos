import java.util.Scanner;

/**
 * Clase principal que contiene el metodo main y el menu interactivo
 * para la gestion de archivos y carpetas en un arbol binario.
 *
 * @author Juan Amaya
 * @author Melisa Acevedo
 */
public class Main {
    /**
     * Metodo principal que ejecuta el programa.
     *
     * @param args Argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arbol arbol = new Arbol();

        // Inserciones para prueba rapida
        arbol.insertar("Carpeta1", "Carpeta", "");
        arbol.insertar("Carpeta2", "Carpeta", "Carpeta1");
        arbol.insertar("perro", "Archivo", "Carpeta1");
        arbol.insertar("gato", "Archivo", "Carpeta2");
        arbol.insertar("Carpeta3", "Carpeta", "Carpeta2");
        arbol.insertar("documento", "Archivo", "Carpeta3");

        Menu(scanner, arbol);
    }

    /**
     * Muestra un menu interactivo para que el usuario realice diferentes acciones
     * sobre el arbol de archivos y carpetas.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     * @param arbol Objeto Arbol que contiene la estructura de archivos y carpetas.
     */
    private static void Menu(Scanner scanner, Arbol arbol) {
        int opcion;
        do {
            System.out.println("\nMENU");
            System.out.println("1. Insertar archivo o carpeta" +
                    "\n2. Buscar un archivo" +
                    "\n3. Todos los archivos y carpetas en orden alfabetico" +
                    "\n4. Total de archivos y carpetas" +
                    "\n5. Mostrar estructura del arbol" +
                    "\n6. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    preInsertado(scanner, arbol);
                    break;
                case 2:
                    buscarArchivo(scanner, arbol);
                    break;
                case 3:
                    System.out.println("Contenido del arbol en orden alfabetico:");
                    arbol.ordenAlfabetico();
                    break;
                case 4:
                    System.out.println("\nTotal de archivos y carpetas: " + arbol.contarNodos());
                    break;
                case 5:
                    System.out.println("ESTRUCTURA: ");
                    arbol.imprimirEstructura();
                    break;
                case 6:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Esa opcion no existe.");
                    break;
            }
        } while (opcion != 6);
    }

    /**
     * Permite buscar un archivo en el arbol.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     * @param arbol Objeto Arbol donde se realiza la busqueda.
     */
    private static void buscarArchivo(Scanner scanner, Arbol arbol) {
        System.out.println("\nBUSCAR ARCHIVO");
        System.out.print("Nombre de archivo a buscar: ");
        String nombre = scanner.next();
        if (arbol.existe(nombre)) {
            System.out.println("El archivo \"" + nombre + "\" existe");
        } else {
            System.out.println("El archivo \"" + nombre + "\" no existe");
        }
    }

    /**
     * Permite insertar un nuevo archivo o carpeta en el arbol.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     * @param arbol Objeto Arbol donde se realiza la insercion.
     */
    private static void preInsertado(Scanner scanner, Arbol arbol) {
        System.out.println("\nINSERTAR");
        String nombre;
        String tipo = "";
        String nombreCarpeta;
        int tipoOpcion;

        do {
            System.out.println("\nTipo." +
                    "\n1. Archivo" +
                    "\n2. Carpeta");
            System.out.print("Ingrese el tipo: ");
            tipoOpcion = scanner.nextInt();

            switch (tipoOpcion) {
                case 1:
                    tipo = "Archivo";
                    break;
                case 2:
                    tipo = "Carpeta";
                    break;
                default:
                    System.out.println("Este tipo de dato no existe");
                    break;
            }
        } while (tipoOpcion != 1 && tipoOpcion != 2);

        System.out.print("Nombre: ");
        nombre = scanner.next();

        if (arbol.getRaiz() != null){
            System.out.print("Nombre de la carpeta donde se insertara: ");
            nombreCarpeta = scanner.next();
        } else {
            nombreCarpeta = "";
        }
        arbol.insertar(nombre, tipo, nombreCarpeta);
    }
}
