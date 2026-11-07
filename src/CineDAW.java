

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class CineDAW {
    //declaramos antes del método  para que todas las var se puedan usar dentro de los metodos
    static final int FILAS =6;
    static final int BUTACAS_POR_FILA = 8;
    static final char LIBRE = '-';
    static final char OCUPADA = 'X';

    static final int PRECIO_INFANTIL = 500;
    static final int PRECIO_SENIOR = 600;
    static final int PRECIO_GENERAL = 850;
    static final int SUPLEMENTO_FIN_SEMANA = 150;

    static String salaVacia() {
        String marcaLibre = "";
        for (int i = 0; i < (FILAS * BUTACAS_POR_FILA); i++) {
            marcaLibre += LIBRE;
        }
        return marcaLibre;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu = -1;
        String sala = salaVacia();
        int entradasVendidas = 0;
        int totalDia = 0;

        final String MENU = """
              ===== CineDAW =====
               1. Ver sala
               2. Comprar entrada
               3. Estadísticas
               0. Salir
               Elige una opción:\s""";
        do {
            System.out.println(MENU);
            try {
                opcionMenu = scanner.nextInt();
                scanner.nextLine();

                switch (opcionMenu) {
                    case 1: verSala(sala);

                        break;
                    case 2:
                        sala = comprarEntrada(sala, scanner);
                        break;
                    case 3:
                        System.out.println("AQui van las estadisticas ");
                        break;
                    case 0:
                        System.out.println("saliendo de cinedaw entradas vendidas");
                        break;
                    default:
                        System.out.println("opción no valida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida");
                scanner.nextLine();

            }

        } while (opcionMenu !=0);
        scanner.close();




    }
    static void verSala(String sala) {
        for ( int i = 0; i < FILAS; i++) {
            for (int j= 0; j < BUTACAS_POR_FILA; j++ ){
                System.out.print(sala.charAt( j+(i * BUTACAS_POR_FILA)));
            }
            System.out.println();
        }

    }

    static String comprarEntrada (String sala, Scanner scanner) {
        //si est{a completa muestra el aviso y vuelve al menu
        if(sala.indexOf('-') == -1 ) {
            System.out.println("La sala esta llena");
            return;
        }
        System.out.println("Dame la fila donde quieres sentarte, entre A y F:");
         String seleccionFila = scanner.nextLine().trim().toUpperCase();
        while (seleccionFila.length() != 1 || seleccionFila.charAt(0) < 'A' || seleccionFila.charAt(0) > 'F'){
            System.out.println("Error. Dame la fila donde quieres sentarte, entre A y F:");
            seleccionFila = scanner.nextLine().trim().toUpperCase();
        }



        //aqui ponemos una var en -1 porque las butacas van del 1 al 8
    //y haremos el try catch por si el usuario mete algo que no debe para que no de error
    //y hacemos un nextLine para limpiar

    int seleccionButaca = -1;
        do {
        System.out.println("Introduce la butaca entre 1 y 8:");
            try {
                 seleccionButaca= scanner.nextInt();
                scanner.nextLine();

                    if ( seleccionButaca< 1 ||  seleccionButaca > BUTACAS_POR_FILA ) {
                    System.out.println("Lo siento, la butaca debe ser un numero entre 1 y " + BUTACAS_POR_FILA + ",");
                    }

                } catch (InputMismatchException e) {
                System.out.println("Hay que introducir un num entero");
                scanner.nextLine();
                }
            } while (seleccionButaca < 1 || seleccionButaca > BUTACAS_POR_FILA);

        System.out.println();
        return sala;
    }
}
