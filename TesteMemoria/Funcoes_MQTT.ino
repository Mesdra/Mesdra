void writeMQTT(String msg){
   Serial.print("enviando dados para o servidor MQTT direto");
   digitalWrite(ENVIADOMQTT, HIGH);
  char dados[msg.length()];
    msg.toCharArray(dados,msg.length());
   if (!Public.publish(dados)) {
    Serial.println("Failed");
  } else {
    Serial.println("OK!");
  }
  delay(DELAYLED);
  digitalWrite(ENVIADOMQTT, LOW);
  }

void processamentoDePrioridade(String msg){
  
  
  
  }  

boolean conectMQTTWifi(){

     
    if(MQTT_connect() && wifiConect()){
        conectado = true;
        return true;
    }
      else{
        conectado = false;
      }
       return false;
  } 
 

boolean MQTT_connect() {
  int8_t ret;

  // Stop if already connected.
  if (mqtt.connected()) {
    digitalWrite(MQTTCONECT, HIGH);
    return true;
  }

  Serial.print("Connecting to MQTT... ");

  uint8_t retries = 1;
  while ((ret = mqtt.connect()) != 0) { // connect will return 0 for connected
       Serial.println(mqtt.connectErrorString(ret));
       Serial.println("Retrying MQTT connection in 5 seconds...");
       mqtt.disconnect();
       delay(2500);  // wait 5 seconds
       retries--;
       if (retries == 0) {
        // sera feita uma tentativa de coneccao caso contrario retorna falso e inicia o sistema de arquivos.
        digitalWrite(MQTTCONECT, LOW);
         return false;
         
       }
  }
  Serial.println("MQTT Connected!");
  digitalWrite(MQTTCONECT, HIGH);
  return true;
}

boolean wifiConect(){
int ret = 2;
  if(WiFi.status() == WL_CONNECTED){
    digitalWrite(CONECTADO, HIGH);
    return true;
  }
    
  Serial.print("Connecting to ");
  Serial.println(WLAN_SSID);

  WiFi.begin(WLAN_SSID, WLAN_PASS);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
    ret--;
    if(ret == 0){
      digitalWrite(CONECTADO, LOW);
      return false;
    }
  }
  Serial.println();

  Serial.println("WiFi connected");
  Serial.println("IP address: "); Serial.println(WiFi.localIP());
  digitalWrite(CONECTADO, HIGH);
  return true;
}

