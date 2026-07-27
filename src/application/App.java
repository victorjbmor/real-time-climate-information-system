package application;

import model.DadosClimaticos;
import service.WeatherApiService;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WeatherApiService apiService = new WeatherApiService();

        System.out.println("==========================================");
        System.out.println("   SISTEMA DE INFORMAÇÕES CLIMÁTICAS CLI   ");
        System.out.println("==========================================");
        System.out.print("Digite o nome da cidade: ");
        String cidade = sc.nextLine().trim();

        if (cidade.isBlank()) {
            System.out.println("Erro: O nome da cidade não pode ser vazio.");
            sc.close();
            return;
        }

        try {
            var climaOptional = apiService.buscarClima(cidade);

            if (climaOptional.isPresent()) {
                exibirRelatorioClimatico(climaOptional.get());
            } else {
                System.out.println("\nLocalização não encontrada ou erro na consulta. Verifique o nome digitado.");
            }

        } catch (IllegalStateException e) {
            System.err.println("\n[Erro de Configuração] " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n[Erro Inesperado] Falha ao consultar a API: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static void exibirRelatorioClimatico(DadosClimaticos clima) {
        System.out.println("\n------------------------------------------");
        System.out.printf("Informações Meteorológicas para %s, %s%n", clima.cidade(), clima.pais());
        System.out.println("------------------------------------------");
        System.out.printf("Data e Hora Atualização : %s%n", clima.dataHora());
        System.out.printf("Temperatura Atual       : %.1f°C%n", clima.temperatura());
        System.out.printf("Sensação Térmica        : %.1f°C%n", clima.sensacaoTermica());
        System.out.printf("Condição do Tempo       : %s%n", clima.condicao());
        System.out.printf("Umidade Ar              : %d%%%n", clima.umidade());
        System.out.printf("Velocidade do Vento     : %.1f km/h%n", clima.velocidadeVento());
        System.out.printf("Pressão Atmosférica     : %.1f mb%n", clima.pressao());
        System.out.println("------------------------------------------");
    }
}