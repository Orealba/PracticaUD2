import java.util.Scanner;

public class LabTexto {
    static  final int LONGITUD_MIN_PASS = 8;
    static final int LETRAS_ALFABETO = 26;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        final String MENU = """
             ===== LabTexto =====
             1. Analizar frase
             2. Cifrado César
             3. Validar contraseña
             0. Salir
             Elige una opción:\s """;

        do {
            System.out.print(MENU);
            String entradaUsuario = scanner.nextLine();
            try {
                opcion = Integer.parseInt(entradaUsuario);
                switch (opcion) {
                    case 1:
                        System.out.println("aqui va el análisis");
                        break;
                    case 2:
                        System.out.println("Aqui va el cesar");
                        break;
                    case 3:
                        System.out.println("saliendo");
                        break;
                    default:
                        System.out.println("opción no válida");
                        break;

                }

            } catch (NumberFormatException e) {
                System.out.println("Aviso :\"" + entradaUsuario + "\" no es una opción válida. ");
            }
        }while (opcion != 0);
        scanner.close();

    }
}
