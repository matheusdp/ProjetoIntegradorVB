# 🏠 Casa Inteligente

## ✨ Sobre o Projeto
Este projeto foi desenvolvido para transformar uma casa antiga em um ambiente inteligente, onde é possível monitorar e armazenar informações sobre **temperatura, umidade e luminosidade**. Os dados são coletados por sensores conectados a um **Arduino UNO** e enviados para um **backend Java com Spring Boot**.

---
## 📐 Protótipos

- **Figma:** [Projeto Integrador - VB no Figma](https://www.figma.com/design/Xgx3u7HGqDx4LIzr3MSCTG/Projeto-Integrador---VB?m=auto&t=Q7X6hqhT3axgXl2g-1)  
- **Tinkercad:** [Projeto Integrador - VB no Tinkercad](https://www.tinkercad.com/things/c9xCHHRGyhO-projeto-integrador-vb?sharecode=DN-x0DvpCHifIIUqu8TIzENXT1Pn3qRYiMkdx3KIlYM)

---
## ⚙️ Tecnologias Utilizadas

### 💻 **Backend**
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** (para persistência de dados)
- **Banco de Dados H2** (pode ser trocado por outro SGBD futuramente)
- **Maven** (gerenciador de dependências)
- **Lombok** (para redução de boilerplate code)

### 🛠️ **Hardware**
- **Arduino UNO**
- **Módulo Wi-Fi ESP8266 (ou ESP32)**
- **Sensores DHT11/DHT22 (Temperatura e Umidade)**
- **Sensor LDR (Luminosidade)**

---
## 🔨 Como Executar o Projeto

### 1. Clonar o Repositório
```sh
git clone https://github.com/seu-usuario/casaInteligente.git
cd casaInteligente
```

### 2. Configurar o Banco de Dados (H2)
Por padrão, o projeto usa um **banco de dados em memória (H2)**. Caso deseje acessar a interface web do banco, habilite no arquivo `application.properties`:
```properties
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:casaInteligenteDB
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
A interface estará disponível em:  
🔗 [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
JDBC URL: `jdbc:h2:mem:casaInteligenteDB`

### 3. Compilar e Rodar a API
```sh
mvn clean install
mvn spring-boot:run
```
A API estará rodando em **http://localhost:8080**

---
## 🔄 Endpoints da API

### 📈 Listar Sensores
```http
GET /sensores
```
**Resposta:**
```json
[
  {
    "id": 101,
    "tipo": "temperatura",
    "valor": 24.0,
    "dataRegistro": "2025-04-07T15:00:00"
  },
  {
    "id": 102,
    "tipo": "umidade",
    "valor": 0.0,
    "dataRegistro": "2025-04-07T15:00:00"
  },
  {
    "id": 103,
    "tipo": "luminosidade",
    "valor": 4.0,
    "dataRegistro": "2025-04-07T15:00:00"
  }
]
```

### ➕ Cadastrar um Sensor
```http
POST /sensores
Content-Type: application/json
```
**Body:**
```json
[
  { "tipo": "temperatura", "valor": 24 },
  { "tipo": "umidade", "valor": 50 },
  { "tipo": "luminosidade", "valor": 0 }
]
```
**Resposta:**
```json
[
  {
    "id": 1,
    "tipo": "temperatura",
    "valor": 24.0,
    "dataRegistro": "2025-04-07T17:26:20.468136"
  },
  {
    "id": 2,
    "tipo": "umidade",
    "valor": 50.0,
    "dataRegistro": "2025-04-07T17:26:20.4691357"
  },
  {
    "id": 3,
    "tipo": "luminosidade",
    "valor": 0,
    "dataRegistro": "2025-04-07T17:26:20.4691357"
  }
]
```

---
## 🚀 Como Enviar Dados do Arduino
Se o Arduino estiver conectado a um módulo Wi-Fi (ESP8266 ou ESP32), utilize o seguinte código para enviar dados para a API:

```cpp
#include <WiFi.h>
#include <HTTPClient.h>

const char* ssid = "SEU_WIFI";
const char* password = "SENHA_WIFI";
const char* serverUrl = "http://192.168.1.100:8080/sensores";

void setup() {
    Serial.begin(115200);
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
        delay(1000);
        Serial.println("Conectando...");
    }
    Serial.println("Conectado ao Wi-Fi");
}

void loop() {
    if (WiFi.status() == WL_CONNECTED) {
        HTTPClient http;
        http.begin(serverUrl);
        http.addHeader("Content-Type", "application/json");

        float temperatura = 26.8; // Simula leitura do sensor
        String json = "{\"tipo\": \"temperatura\", \"valor\": " + String(temperatura) + "}";

        int httpResponseCode = http.POST(json);
        Serial.print("Resposta: ");
        Serial.println(httpResponseCode);
        http.end();
    }
    delay(5000);
}
```
---
## 📊 Futuras Melhorias
- [ ] Criar uma interface web/mobile para visualizar os dados em tempo real
- [ ] Implementar autenticação e permissões de usuários
- [ ] Melhorar a gestão de sensores, permitindo cadastro dinâmico
- [ ] Integrar com banco de dados MySQL/PostgreSQL

---
## 📄 Licença
Este projeto está sob a licença **MIT**.
