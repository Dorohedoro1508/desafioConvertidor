import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Consulta {
    Scanner lectura = new Scanner(System.in);
    String apiKey = "4b83e1e5f0b6354f36fdbc6f";
    String direccion = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/";
    String moneda1 = "MXN";
    String moneda2 = "USD";
    String moneda3 = "EUR";
    double cantidad = 1.0;

    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .create();

    public void muestraMenu() throws IOException, InterruptedException {
        var opcion = -1;
        var opcion2 = -1;
        while (opcion != 0 || opcion2 != 0) {
            var mensaje = """
                    Bienvenido a tu convertidor de monedas, tu moneda base:
                    1) Peso Mexicano
                    2) Dolar Americano
                    3) Euro
                    0) Salir
                    """;
            System.out.println(mensaje);
            opcion = lectura.nextInt();
            lectura.nextLine();

            if (opcion == 0) {
                break;
            }

            var mensaje2 = """
                    A qué moneda deseas convertir?
                    1) Peso Mexicano
                    2) Dolar Americano
                    3) Euro
                    0) Salir
                    """;
            System.out.println(mensaje2);
            opcion2 = lectura.nextInt();
            lectura.nextLine();

            var mensaje3 = """
                    ¿Qué cantidad deseas cambiar?
                    """;
            System.out.println(mensaje3);
            cantidad = lectura.nextDouble();
            lectura.nextLine();

            if (opcion2 == 0) {
                break;
            }

            switch (opcion) {
                case 1:
                    if (opcion2 == 1) {
                        System.out.println("Es la misma moneda");
                    } else if (opcion2 == 2) {
                        convertirMoneda(moneda1, moneda2, cantidad);
                    } else if (opcion2 == 3) {
                        convertirMoneda(moneda1, moneda3, cantidad);
                    } else {
                        System.out.println("Opción no válida");
                    }
                    break;
                case 2:
                    if (opcion2 == 1) {
                        convertirMoneda(moneda2, moneda1, cantidad);
                    } else if (opcion2 == 2) {
                        System.out.println("Es la misma moneda");
                    } else if (opcion2 == 3) {
                        convertirMoneda(moneda2, moneda3, cantidad);
                    } else {
                        System.out.println("Opción no válida");
                    }
                    break;
                case 3:
                    if (opcion2 == 1) {
                        convertirMoneda(moneda3, moneda1, cantidad);
                    } else if (opcion2 == 2) {
                        convertirMoneda(moneda3, moneda2, cantidad);
                    } else if (opcion2 == 3) {
                        System.out.println("Es la misma moneda");
                    } else {
                        System.out.println("Opción no válida");
                    }
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) throws IOException, InterruptedException {
        String url = direccion + monedaOrigen + "/" + monedaDestino + "/" + cantidad;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        DatosMonedas datosMonedas = gson.fromJson(json, DatosMonedas.class);
        System.out.println("Conversión: " + datosMonedas);
    }
}

