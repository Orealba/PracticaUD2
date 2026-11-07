import java.util.InputMismatchException;
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

    static String butacasLibres() {
        String marcaLibre = "";
        for (int i = 0; i < (FILAS * BUTACAS_POR_FILA); i++) {
            marcaLibre += LIBRE;
        }
        return marcaLibre;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu = -1;
        String sala = butacasLibres();
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
                        System.out.println("Aqui compraremos entrada ");
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

}
