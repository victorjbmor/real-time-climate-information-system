package service;

import model.DadosClimaticos;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class WeatherApiService {

    private static final String ENV_KEY_NAME = "WEATHER_API_KEY";
    private static final String BASE_URL = "http://api.weatherapi.com/v1/current.json";
    
    private final HttpClient httpClient;

    public WeatherApiService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public Optional<DadosClimaticos> buscarClima(String cidade) throws Exception {
        String apiKey = carregarApiKey();
        String cidadeEncoded = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
        String url = String.format("%s?key=%s&q=%s", BASE_URL, apiKey, cidadeEncoded);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Código 200 indica que a localização foi encontrada e a requisição teve sucesso
        if (response.statusCode() != 200) {
            return Optional.empty();
        }

        return Optional.of(mapearJsonParaDadosClimaticos(response.body()));
    }

    private String carregarApiKey() {
        String apiKey = System.getenv(ENV_KEY_NAME);
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                "A variável de ambiente '" + ENV_KEY_NAME + "' não foi configurada."
            );
        }
        return apiKey.trim();
    }

    private DadosClimaticos mapearJsonParaDadosClimaticos(String jsonBody) {
        JSONObject json = new JSONObject(jsonBody);
        JSONObject location = json.getJSONObject("location");
        JSONObject current = json.getJSONObject("current");

        return new DadosClimaticos(
            location.getString("name"),
            location.getString("country"),
            current.getString("last_updated"),
            current.getFloat("temp_c"),
            current.getFloat("feelslike_c"),
            current.getJSONObject("condition").getString("text"),
            current.getInt("humidity"),
            current.getFloat("wind_kph"),
            current.getFloat("pressure_mb")
        );
    }
}