// sensores
String leStringSerial(){
  String conteudo = "";
  char caractere;
  
  // Enquanto receber algo pela serial
  while(serial1.available() > 0) {
    // Lê byte da serial
    caractere = serial1.read();
    // Ignora caractere de quebra de linha
    if (caractere != '\n'){
      // Concatena valores
      conteudo.concat(caractere);
    }
    // Aguarda buffer serial ler próximo caractere
    delay(10);
  }
    
  Serial.print("Recebi: ");
  Serial.println(conteudo);
    
  return conteudo;
}
 void leituraSensores(){

float dadosLeitura[QUANTIDADESENSORES];
dadosLeitura[0] = 50 + random(0,9);
dadosLeitura[1] = 60 + random(0,15);
  
   for(int i =0;i < QUANTIDADESENSORES;i++){
          tipoSensor[i]= i;
          dados[i] =dadosLeitura[i];
           }
           
           }
