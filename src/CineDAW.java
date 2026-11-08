

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public class CineDAW {
    //declaramos antes del método  para que todas las var se puedan usar dentro de los metodos
    static final int FILAS = 6;
    static final int BUTACAS_POR_FILA = 8;
    static final char LIBRE = '-';
    static final char OCUPADA = 'X';

    static final int PRECIO_INFANTIL = 500;
    static final int PRECIO_SENIOR = 600;
    static final int PRECIO_GENERAL = 850;
    static final int SUPLEMENTO_FIN_SEMANA = 150;
    static int entradasVendidas = 0;
    static int totalDia = 0;

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
                    case 1:
                        verSala(sala);

                        break;
                    case 2:
                        sala = comprarEntrada(sala, scanner);
                        break;
                    case 3:
                        String textoEntradas = (entradasVendidas == 1) ? "1 entrada" : entradasVendidas + " entradas";
                        int eurosRecaudacion = totalDia / 100;
                        int centimosRecaudacion = totalDia % 100;
                        String formatoCentimosRecaudacion = (centimosRecaudacion < 10) ? "0" + centimosRecaudacion : String.valueOf(centimosRecaudacion);
                        int butacasOcupadas = 0;
                        for (int i = 0; i < sala.length(); i++) {
                            if (sala.charAt(i) == LIBRE) {
                                continue;
                            }
                            butacasOcupadas++;
                        }
                        int butacasLibres = (FILAS * BUTACAS_POR_FILA) - butacasOcupadas;
                        double porcentajeOcupacion = (double) butacasOcupadas / (FILAS * BUTACAS_POR_FILA) * 100;
                        double ocupacionRedondeada = Math.round(porcentajeOcupacion * 100.0) / 100.0;

                        System.out.println("Entradas vendidas: " + textoEntradas);
                        System.out.println("Recaudación: " + eurosRecaudacion + "," + formatoCentimosRecaudacion + " €");
                        System.out.println("Butacas ocupadas: " + butacasOcupadas + " libres: " + butacasLibres);
                        System.out.println("Ocupación: " + ocupacionRedondeada + "%");
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

        } while (opcionMenu != 0);
        scanner.close();


    }
//agrego la letra, espacios y num al ver la sala
static void verSala(String sala) {
    System.out.println("      P A N T A L L A");
    System.out.println("    1 2 3 4   5 6 7 8");
    for (int i = 0; i < FILAS; i++) {
        char letraFila = (char) ('A' + i);
        System.out.print(letraFila + "   ");
        for (int j = 0; j < BUTACAS_POR_FILA; j++) {
            // AQUÍ está la clave: imprimir la butaca MÁS un espacio
            System.out.print(sala.charAt(j + (i * BUTACAS_POR_FILA)) + " ");

            // Pasillo central después de la butaca 4
            if (j == 3) {
                System.out.print("  ");
            }
        }
        System.out.println();
    }
    System.out.println("  (- libre, X ocupada)");
}

    static String comprarEntrada(String sala, Scanner scanner) {
        //si est{a completa muestra el aviso y vuelve al menu
        if (sala.indexOf('-') == -1) {
            System.out.println("La sala esta llena");
            return sala;
        }
        System.out.println("Dame la fila donde quieres sentarte, entre A y F:");
        String seleccionFila = scanner.nextLine().trim().toUpperCase();
        while (seleccionFila.length() != 1 || seleccionFila.charAt(0) < 'A' || seleccionFila.charAt(0) > 'F') {
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
                seleccionButaca = scanner.nextInt();
                scanner.nextLine();

                if (seleccionButaca < 1 || seleccionButaca > BUTACAS_POR_FILA) {
                    System.out.println("Lo siento, la butaca debe ser un numero entre 1 y " + BUTACAS_POR_FILA + ",");
                }

            } catch (InputMismatchException e) {
                System.out.println("Hay que introducir un num entero");
                scanner.nextLine();
            }
        } while (seleccionButaca < 1 || seleccionButaca > BUTACAS_POR_FILA);


        int posicion = (seleccionFila.charAt(0) - 'A') * BUTACAS_POR_FILA + (seleccionButaca - 1);
        if (sala.charAt(posicion) == OCUPADA) {
            System.out.println("Lo siento est{a ocupada la butaca");
            return sala;
        }
        //pedimos la edad y llamamos al método pedirEdad
         int edad = pedirEdad(scanner);
        int precioBase = 0;
        if (edad < 12) {
            precioBase = PRECIO_INFANTIL;
            System.out.println("precio infantil");
        }else if (edad>= 65) {
            precioBase = PRECIO_SENIOR;
            System.out.println("precio senior");
        } else {
            precioBase = PRECIO_GENERAL;
            System.out.println("precio general");
        }
        //con el día listo calculamos el precio final
        int dia = pedirDia(scanner);
        int precioFinal = precioBase;
        switch (dia) {
            case 1:
            case 2:
            case 4:
            case 5:
                break;
            case 3:
                System.out.println("Miércoles del espectador: -30%");
                precioFinal = (int)  Math.round(precioBase * 0.70);
                break;
            case 6:
            case 7:
                precioFinal += SUPLEMENTO_FIN_SEMANA;
                break;
        }
        int euros = precioFinal / 100;
        int centimos = precioFinal % 100;
        String textoCentimos = (centimos < 10) ? "0" + centimos : String.valueOf(centimos);
        System.out.println("Total a pagar: " + euros + "," + textoCentimos + " €");

        //marcamos la butaca y sumamos venta
        sala = sala.substring(0,posicion) + OCUPADA + sala.substring(posicion +1);
        entradasVendidas++;
        totalDia += precioFinal;
        System.out.println("Entrada vendida: fila " + seleccionFila + ", butaca " + seleccionButaca + ".");
        System.out.println();


        return sala;
    }

    static int pedirEdad(Scanner scanner) {
        int edad = -1;
        do {
            System.out.println("Ingresa la edad del asistente, ente 0 y 120");
            try {
                edad = scanner.nextInt();
                scanner.nextLine();
                if (edad < 0 || edad > 120) {
                    System.out.println("Error. La edad debe estar entre 0 y 120");
                }
            } catch (InputMismatchException e) {
                System.out.println("Hay que introducir un num entero");
                scanner.nextLine();
            }
        } while (edad < 0 || edad > 120);

        return edad;

    }
    //pedimos el dia con este método y volvemos a poner scanner dentro de él
    static int pedirDia(Scanner scanner) {
        int dia = -1;
        do {
            System.out.println("Dime un día de la semana, 1 = lunes … 7 = domingo");
                    try {
                dia = scanner.nextInt();
                scanner.nextLine();
                if (dia < 1 || dia > 7) {
                    System.out.println("Error debe ser entre 1 y 7");
                }
            }catch (InputMismatchException e) {
                System.out.println("debes introducir un entero");
                scanner.nextLine();
            }
        } while (dia < 1 || dia > 7);

        return dia;
    }


}

