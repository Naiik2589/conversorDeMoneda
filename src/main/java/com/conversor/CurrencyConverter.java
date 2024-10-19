import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_KEY = "b4854d70935cea8208977e48";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private static final String[] CURRENCIES = {"USD", "EUR", "COP", "GBP", "JPY", "AUD", "CAD", "CHF"}; // Lista de monedas

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Intentos infinitos
            try {
                // Hacer la solicitud HTTP
                System.out.println("Seleccione la moneda origen:");
                for (int i = 0; i < CURRENCIES.length; i++) {
                    System.out.println((i + 1) + ". " + CURRENCIES[i]);
                }

                int fromChoice = getValidIntegerInput(scanner, 1, CURRENCIES.length) - 1;
                String fromCurrency = CURRENCIES[fromChoice];

                // Construir la URL de la solicitud
                String requestUrl = API_URL + fromCurrency;

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(requestUrl))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // ** Eliminar o comentar esta línea para ocultar la respuesta de la API **
                // System.out.println("Respuesta de la API: " + response.body());

                // Analizar la respuesta JSON
                Gson gson = new Gson();
                ExchangeRates rates = gson.fromJson(response.body(), ExchangeRates.class);

                // Comprobar si rates es nulo o no tiene tasas
                if (rates == null || rates.conversion_rates == null) {
                    System.out.println("No se pudo obtener tasas de cambio.");
                    continue;
                }

                System.out.println("Seleccione la moneda destino:");
                for (int i = 0; i < CURRENCIES.length; i++) {
                    System.out.println((i + 1) + ". " + CURRENCIES[i]);
                }
                int toChoice = getValidIntegerInput(scanner, 1, CURRENCIES.length) - 1;
                String toCurrency = CURRENCIES[toChoice];

                System.out.println("Ingrese el monto:");
                double amount = getValidDoubleInput(scanner);

                // Convertir las monedas
                double result = convertCurrency(rates, fromCurrency, toCurrency, amount);

                // Redondear el resultado a dos decimales
                double resultRedondeado = Math.round(result * 100.0) / 100.0;

                // Imprimir el resultado en el nuevo formato con separador de miles
                System.out.printf("Monto convertido: %s a %s '%s' = %,.2f %s%n",
                        fromCurrency, toCurrency, amount, resultRedondeado, toCurrency);

                System.out.println("¿Desea hacer otra conversión? (s/n)");
                String again = scanner.next();
                if (!again.equalsIgnoreCase("s")) {
                    break; // Salir del bucle si no desea continuar
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    // Método para convertir monedas
    public static double convertCurrency(ExchangeRates rates, String from, String to, double amount) {
        if (rates.conversion_rates == null || !rates.conversion_rates.containsKey(from) || !rates.conversion_rates.containsKey(to)) {
            throw new IllegalArgumentException("Moneda no válida. Verifique que la moneda de origen y destino sean correctas.");
        }
        double rateFrom = rates.conversion_rates.get(from);
        double rateTo = rates.conversion_rates.get(to);
        return (amount / rateFrom) * rateTo;
    }

    // Método para obtener una entrada entera válida
    private static int getValidIntegerInput(Scanner scanner, int min, int max) {
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            try {
                choice = scanner.nextInt();
                if (choice < min || choice > max) {
                    System.out.println("Por favor, ingrese un número entre " + min + " y " + max + ".");
                } else {
                    valid = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.next(); // Limpiar el buffer
            }
        }
        return choice;
    }

    // Método para obtener una entrada de tipo double válida
    private static double getValidDoubleInput(Scanner scanner) {
        double amount = 0;
        boolean valid = false;
        while (!valid) {
            try {
                String input = scanner.next(); // Leer como String
                amount = Double.parseDouble(input); // Intentar convertir a double
                if (amount <= 0) {
                    System.out.println("El monto debe ser un número positivo.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número válido (ejemplo: 1.6 o 2.5).");
            }
        }
        return amount;
    }

    // Clase interna para representar la estructura JSON
    static class ExchangeRates {
        String base;
        Map<String, Double> conversion_rates; // Cambiado de rates a conversion_rates
    }
}




