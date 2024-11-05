import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arbol arbol = new Arbol();

        // Inserciones para prueba rápida
        arbol.insertar("Carpeta1", "Carpeta", ""); // Insertar Carpeta1 como raíz
        arbol.insertar("Carpeta2", "Carpeta", "Carpeta1"); // Insertar Carpeta2 dentro de Carpeta1
        arbol.insertar("Archivo1", "Archivo", "Carpeta1"); // Insertar Archivo1 dentro de Carpeta1
        arbol.insertar("Archivo2", "Archivo", "Carpeta2"); // Insertar Archivo2 dentro de Carpeta2
        arbol.insertar("Carpeta3", "Carpeta", "Carpeta2"); // Insertar Carpeta3 dentro de Carpeta2
        arbol.insertar("Archivo3", "Archivo", "Carpeta3"); // Insertar Archivo3 dentro de Carpeta3

        Menu(scanner, arbol);
    }

    private static void Menu(Scanner scanner, Arbol arbol) {
        int opcion;
        do {
            System.out.println("\nMENU");
            System.out.println("1. Insertar archivo o carpeta\n2. Buscar un archivo" +
                    "\n3. Todos los archivos y carpetas en orden alfabetico" +
                    "\n4. Total de archivos y carpetas\n5. Salir");
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
                    System.out.println("Contenido del sistema en orden alfabetico:");
                    arbol.ordenAlfabetico();
                    break;
                case 4:
                    System.out.println("\nTotal de archivos y carpetas: " + arbol.contarNodos());
                    break;
                case 5:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Esa opcion no existe.");
                    break;
            }
        } while (opcion != 5);
    }

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

    private static void preInsertado(Scanner scanner, Arbol arbol) {
        System.out.println("\nINSERTAR");
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        String tipo = "";
        String nombreCarpeta;
        int tipoOpcion;

        do {
            System.out.println("\nTipo de archivo\n1. archivo\n2. carpeta");
            tipoOpcion = scanner.nextInt();
            if (tipoOpcion == 1) {
                tipo = "Archivo";
            } else if (tipoOpcion == 2) {
                tipo = "Carpeta";
            } else {
                System.out.println("Este tipo de dato no existe");
            }
        } while (tipoOpcion != 1 && tipoOpcion != 2);

        System.out.print("Nombre de la carpeta donde se insertara: ");
        nombreCarpeta = scanner.next();
        arbol.insertar(nombre, tipo, nombreCarpeta);
    }
}
