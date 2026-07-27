// DadosClimaticos.java
package model;

public record DadosClimaticos(
    String cidade,
    String pais,
    String dataHora,
    float temperatura,
    float sensacaoTermica,
    String condicao,
    int umidade,
    float velocidadeVento,
    float pressao
) {}
