import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arbol arbol = new Arbol();

        Menu(scanner, arbol);

//        arbol.insertar("Carpeta1", "Carpeta");
//        arbol.insertar("Carpeta2", "Carpeta");
//        arbol.insertar("Archivo1", "Archivo");
//        arbol.insertar("Archivo2", "Archivo");
//        arbol.insertar("Carpeta3", "Carpeta");
//        arbol.insertar("Archivo3", "Archivo");

        System.out.println("¿Existe 'Archivo2'? " + arbol.existe("Archivo2"));
    }

    private static void Menu(Scanner scanner, Arbol arbol) {

        int opcion;
        do {
            System.out.println("\nMENU");
            System.out.println("1. Insertar archivo o carpeta" +
                    "\n2. Buscar un archivo" +
                    "\n3. Todos los archivos y carpetas en orden alfabetico" +
                    "\n4. Total de archivos y carpertas" +
                    "\n5. Salir");
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
                    System.out.println("Contenido del sistema en orden alfabético:");
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

        if (arbol.existe(nombre)){

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
        int tipoOpcion;

        do {
            System.out.println("\nTipo de archivo" +
                    "\n1. archivo" +
                    "\n2. carpeta");
            System.out.print("Ingrese una opcion: ");
            tipoOpcion = scanner.nextInt();

            switch (tipoOpcion){

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

        arbol.insertar(nombre, tipo);
    }
}
