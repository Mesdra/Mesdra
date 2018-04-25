 
// bibliotecas utilizadas no sistema de arquivos e 0 foramto json.
#include <ArduinoJson.h>
#include <FS.h>

// bibliotecas para coneccao com Wifi e coneccoes com o servidor mqtt.
#include <ESP8266WiFi.h>
#include "Adafruit_MQTT.h"
#include "Adafruit_MQTT_Client.h"

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
#define CICLO D5 
#define ENVIADOMQTT D1
#define GUARDANDODADOS D4
#define BUFFER D2 
#define DELAYLED 400

#define MEMORIATOTAL 500000  // definir o tamanho da memoria disponivel para os arquivos, 2000000(limite),
#define TOTALARQUIVOS 20     //quantidade de arquivos maximos no sistema. 
#define TAMANHOARQ 30        // quantidade de dados que cada arquivo deve conter.                             
#define QUANTIDADESENSORES 6 // quantidade de sensores existentes no sistema.
#define JSONBUFFERSIZE 400  // para utilizar mais sensores o tamanho do json buffer deve ser alterado .


String buf;
// botao para simular a coneccao com o wifi.
const int botaoConectado = 0;

// estrutura usada pela funcao memoriaCheia() para receber os dados da memoria disponivel e memoria em uso.
typedef struct FSInfo FSInfo;
// funcoes Arq


void formatFS(void);
 
void createFile(String arq);
 
void deleteFile(String arq);

void writeFile(String msg,String arq);

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

String generateData(int tipoSensor[QUANTIDADESENSORES],int dados[QUANTIDADESENSORES],int data[3],float hora);

void initialSettings();

void generateSettings(int arq,int arqLeitura,int total);

// funcoes mqtt

void writeMQTT(String msg);

boolean conectMQTTWifi();

boolean MQTT_connect();

boolean wifiConect();

// funcao botoes

void validaBotao();
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


void setup() {
  pinMode(botaoConectado,INPUT);
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
}


void loop() {
     // funcoes valida a coneccao Wifi e MQTT.
      conectMQTTWifi();
     // funcar recebe um tipo de comando do Servidor.
     Adafruit_MQTT_Subscribe *subscription;
    while ((subscription = mqtt.readSubscription(5000))) {
     if (subscription == &Subs) {
      Serial.print(F("Got: "));
      Serial.println((char *)Subs.lastread);
    }
   }
     // caso tenha coneccao com a internet e tenha algo para enviar esta funcao deve enviar todo o conteudo dos arquivos.
        if(validateFileRead() && conectado && bufferMode){           
          readFileAndSending(arquivoAtualLeitura);         
        }
     // quando nao tiver coneccao com o servidor os dados serao gravados dentro dos arquivos.
        if(bufferMode && !conectado){
          for(int i =0;i < QUANTIDADESENSORES;i++){
          tipoSensor[i]=random(0,8);
          dados[i] =random(0,1);
           }
         data[0] = contador;
         data[1] = random(1,31);
         data[2] = 2018;
         hora = random(0,24);
         writeFile(generateData(tipoSensor,dados,data,hora));
        }
     // enviar os dados dos sensores direto para o servidor quando nao tem nem um arquivo para ser enviado para o servidor.    
        if(conectado && !bufferMode){
          for(int i =0;i < QUANTIDADESENSORES;i++){
          tipoSensor[i]=random(0,8);
          dados[i] =random(0,1);
           }
         data[0] = contador;
         data[1] = random(1,31);
         data[2] = 2018;
         hora = random(0,24);
          writeMQTT(generateData(tipoSensor,dados,data,hora));
          }
          if(!conectado){
            bufferMode = true;
            }
            if(arquivoAtual != arquivoAtualLeitura){
              bufferMode = true;
              digitalWrite(BUFFER,HIGH);
            }else
              digitalWrite(BUFFER,LOW);
            
   delay(400);
   
}
