# Sistema de Informações Climáticas CLI

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Aplicação em linha de comando (CLI) desenvolvida em Java para consulta de dados meteorológicos em tempo real. O sistema realiza a integração com a API da [WeatherAPI](https://www.weatherapi.com/) através de requisições HTTP nativas (`HttpClient`), processa as respostas em formato JSON e exibe relatórios estruturados no console.

---

## Funcionalidades

- **Consulta em Tempo Real:** Obtenção de dados meteorológicos atualizados por nome de localidade.
- **Relatório Detalhado:** Exibição de temperatura, sensação térmica, condições atmosféricas, umidade, velocidade do vento e pressão barométrica.
- **Tratamento Explicito de Exceções:** Manipulação de falhas de conexão, respostas com código de erro ou buscas inválidas através de tipos funcionais (`Optional`).
- **Segurança de Credenciais:** Leitura de chaves de API a partir de arquivos de configuração locais e isolados do controle de versão.

---

## Arquitetura do Projeto

O código foi estruturado seguindo os princípios de Separação de Responsabilidades (SoC) e Orientação a Objetos:

```text
src/
├── model/
│   └── DadosClimaticos.java      # Objeto de transferência/modelagem imutável dos dados
├── service/
│   └── WeatherApiService.java    # Lógica de integração HTTP, conversão JSON e gestão da API Key
└── App.java                      # Ponto de entrada da aplicação e interface CLI
```
Tecnologias Utilizadas

Java 17+ (java.net.http.HttpClient, java.nio.file.Files)
org.json (Manipulação e conversão de estruturas JSON)
Git (Controle de versão)
WeatherAPI (Provedor de dados meteorológicos)

Instruções de Execução
Pré-requisitos
Java Development Kit (JDK) 17 ou superior instalado.

Chave de acesso válida fornecida pela WeatherAPI.

Passo a Passo
Clonar o repositório:

Bash
git clone [https://github.com/SEU-USUARIO/Projeto-Sistema-De-Informacoes-Climaticas-Em-Tempo-Real.git](https://github.com/SEU-USUARIO/Projeto-Sistema-De-Informacoes-Climaticas-Em-Tempo-Real.git)
cd Projeto-Sistema-De-Informacoes-Climaticas-Em-Tempo-Real
Configurar a chave de API:

Na raiz do projeto (mesmo nível do diretório src/), crie o arquivo api-key.txt.

Insira a chave da WeatherAPI dentro do arquivo, sem espaços adicionais ou quebras de linha.

Nota de Segurança: O arquivo api-key.txt está incluso no .gitignore para prevenir a exposição não intencional de credenciais em repositórios remotos.

Executar a aplicação:

Via Ambiente de Desenvolvimento (IDE): Execute o método principal na classe App.java.

Via Terminal:

Bash
javac -d bin -srcpath src src/App.java
java -cp bin App
Exemplo de Saída no Terminal
Plaintext
==========================================
   SISTEMA DE INFORMAÇÕES CLIMÁTICAS CLI   
==========================================
Digite o nome da cidade (or 'sair' para encerrar): São Paulo

------------------------------------------
Informações Meteorológicas para São Paulo, Brazil
------------------------------------------
Data e Hora Atualização : 2026-07-27 15:00
Temperatura Atual       : 22.0°C
Sensação Térmica        : 22.0°C
Condição do Tempo       : Parcialmente nublado
Umidade Ar              : 65%
Velocidade do Vento     : 11.2 km/h
Pressão Atmosférica     : 1018.0 mb
------------------------------------------
