
// sensores
#include <SoftwareSerial.h>
SoftwareSerial serial1(D8, D7); // RX, TX

// bibliotecas utilizadas no sistema de arquivos e 0 foramto json.
#include <ArduinoJson.h>
#include <FS.h>

// bibliotecas para coneccao com Wifi e coneccoes com o servidor mqtt.
#include <ESP8266WiFi.h>
#include "Adafruit_MQTT.h"
#include "Adafruit_MQTT_Client.h"

// bibliotecas pra uso do RTC
#include <Wire.h> 
#include <RtcDS3231.h> //RTC library

//
RtcDS3231<TwoWire> rtcObject(Wire);

/************************* WiFi Access Point *********************************/

#define WLAN_SSID       "celtabiot"
#define WLAN_PASS       "loraceltab"

/************************* Adafruit.io Setup *********************************/

#define AIO_SERVER      "192.168.0.120"
#define AIO_SERVERPORT  1883                   // use 8883 for SSL
#define AIO_USERNAME    "celtabIot"
#define AIO_KEY         "root"

/*****************************************************************************/
// define dos pinos de saida sinais dos leds.
#define CONECTADO D7
#define MQTTCONECT D6
#define CICLO D0 
#define ENVIADOMQTT D4
#define GUARDANDODADOS D3
#define BUFFER D5 
#define DELAYLED 400

#define MEMORIATOTAL 500000  // definir o tamanho da memoria disponivel para os arquivos, 2000000(limite),
#define TOTALARQUIVOS 20     //quantidade de arquivos maximos no sistema. 
#define TAMANHOARQ 30        // quantidade de dados que cada arquivo deve conter.                             
#define QUANTIDADESENSORES 2 // quantidade de sensores existentes no sistema.
#define JSONBUFFERSIZE 400  // para utilizar mais sensores o tamanho do json buffer deve ser alterado .
#define INTERVALOLEITURA 20000 // intervalo de leitura entre um sensor e outro em milis.


String buf;

// funcoes Arq


void formatFS(void);
 
void createFile(String arq);
 
void deleteFile(String arq);

String writeFile(String msg);

void readFileAndSending(int arq);
 
void closeFS(void);

void openFS(void);

boolean validateFileRead();

String validateFile();

boolean memoriaCheia();

int quantArquivos();

void nomeNovoArq();

void nomeNovoArqLeitura();

void deletALL();

//funcoes Json

String generateData(int tipoSensor[QUANTIDADESENSORES],int dados[QUANTIDADESENSORES],String hora);

void initialSettings();

void generateSettings(int arq,int arqLeitura,int total);

// funcoes mqtt

String writeMQTT(String msg);

boolean conectMQTTWifi();

boolean MQTT_connect();

boolean wifiConect();

// sensores

void leituraSensores();



// estrutura usada pela funcao memoriaCheia() para receber os dados da memoria disponivel e memoria em uso.
typedef struct FSInfo FSInfo;

// buffer estatico para gerar texto no formato json. 
StaticJsonBuffer<JSONBUFFERSIZE> jsonBuffer;
// variaveis de validacao para o sistema de arquivos para entrar um modo de leitura e gravacao.
boolean conectado = true;
boolean bufferMode = false;
boolean validaEscrita = false;
boolean validaPrimeiraLeitura = true;

// variaveis das configuracoes iniciais(a funcao initialSettings() ira alterar estas variaveis com os valores reais dos arquivos)
int tamanhoAtual =0;
int arquivoAtual =1;
int arquivoAtualLeitura = 1;
int totalArq =1;
int val =0;
boolean condCiclica = false;
String dadosGerados = "leitura";
// utilizados apenas para testes gerando os valores dos sensores.
int contador= 0;

// variaveis que recebem os dados para gerar uma string no arquivo json
int tipoSensor[QUANTIDADESENSORES];
int dados[QUANTIDADESENSORES];
//posicao 0 1 e 2 correspondem a mm/dd/ano.
int data[3];
float hora;

/************ Global State (you don't need to change this!) ******************/

// Create an ESP8266 WiFiClient class to connect to the MQTT server.
WiFiClient client;

// Setup the MQTT client class by passing in the WiFi client and MQTT server and login details.
Adafruit_MQTT_Client mqtt(&client, AIO_SERVER, AIO_SERVERPORT, AIO_USERNAME, AIO_KEY);

/****************************** Feeds ***************************************/

// Setup a feed called 'photocell' for publishing.
// Notice MQTT paths for AIO follow the form: <username>/feeds/<feedname>
Adafruit_MQTT_Publish Public = Adafruit_MQTT_Publish(&mqtt, "/Sala/TemperaturaH",1);

// Setup a feed called 'onoff' for subscribing to changes.
Adafruit_MQTT_Subscribe Subs = Adafruit_MQTT_Subscribe(&mqtt, "/Sala/TempetaturaH",1);

/*************************** Sketch Code ************************************/


// sensores
long lastMsg = 0;
char msg[50];
int value = 0;



void setup() {
  pinMode(CONECTADO,OUTPUT);
  pinMode(MQTTCONECT,OUTPUT);
  pinMode(CICLO,OUTPUT);
  pinMode(ENVIADOMQTT,OUTPUT);
  pinMode(GUARDANDODADOS,OUTPUT);
  pinMode(BUFFER,OUTPUT); 
  /**********************Wifi Teste de coneccao***********************/
  Serial.println(F("Adafruit MQTT demo"));

  // Connect to WiFi access point.
  Serial.println(); Serial.println();
  Serial.print("Connecting to ");
  Serial.println(WLAN_SSID);

  WiFi.begin(WLAN_SSID, WLAN_PASS);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
    Serial.print("loop");
  }
  Serial.println();

  Serial.println("WiFi connected");
  Serial.println("IP address: "); Serial.println(WiFi.localIP());

  // Setup MQTT subscription for onoff feed.
  mqtt.subscribe(&Subs);
  /*********************************************************************/
  openFS();
 Serial.begin(115200);
 delay(5000);
 //carrega dados de controle do sistema
 //generateSettings(1,1,1);
 initialSettings();
  
  // sensores
  Serial.println("Starting...");
  //pinMode(BUILTIN_LED, OUTPUT);     // Initialize the BUILTIN_LED pin as an output

  // dataHora
  
  rtcObject.Begin();    //Starts I2C
  //RtcDateTime currentTime = RtcDateTime(18, 5, 3, 14, 9, 0); //define date and time object
  //rtcObject.SetDateTime(currentTime);   
}

void loop() {
    long now = millis();
  if (now - lastMsg > INTERVALOLEITURA || !dadosGerados.equals("leitura")) {
    lastMsg = now;
    // funcoes valida a coneccao Wifi e MQTT.
      conectMQTTWifi();
      
  /* sera utilizado nao codigo com os dados originais captados pelos sesores.
   * while (serial1.available() > 0) {
     String recebido = leStringSerial();
     int posicaoTemp = recebido.indexOf("Temp:");
     int posicaoUmid = recebido.indexOf("Umidade :");
     String Temp = recebido.substring(posicaoTemp+6,posicaoTemp+11);
     String Umid = recebido.substring(posicaoUmid+9,posicaoUmid+15);
     Serial.println(Temp);
     Serial.println(Umid);
     Serial.println("publish...");
  }*/
    if(dadosGerados.equals("leitura")){
      leituraSensores();
      dadosGerados = generateData(tipoSensor,dados,dataHoraAtual());
    }
     // quando nao tiver coneccao com o servidor os dados serao gravados dentro dos arquivos.
        if(bufferMode && !conectado){
         dadosGerados = writeFile(dadosGerados);
        }
     // enviar os dados dos sensores direto para o servidor quando nao tem nem um arquivo para ser enviado para o servidor.    
        if(conectado && !bufferMode){
          dadosGerados = writeMQTT(dadosGerados);
          } 

   
           }
           // caso tenha coneccao com a internet e tenha algo para enviar esta funcao deve enviar todo o conteudo dos arquivos.
        if(validateFileRead() && conectado && bufferMode){           
          readFileAndSending(arquivoAtualLeitura);         
        }
           if(!conectado){
            bufferMode = true;
            }
            if(arquivoAtual != arquivoAtualLeitura){
              bufferMode = true;
              digitalWrite(BUFFER,HIGH);
            }else
              digitalWrite(BUFFER,LOW);  
  delay(500);
}

