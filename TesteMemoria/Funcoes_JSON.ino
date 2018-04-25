String generateData(int tipoSensor[QUANTIDADESENSORES],int dados[QUANTIDADESENSORES],int data[3],float hora){
  String resposta ;
  JsonObject& root = jsonBuffer.createObject();
  root["ano"] = data[2];
  root["mes"] =data[1];
  root["dia"] = data[0];
  root["hora"] = hora;
  JsonArray& sensores = root.createNestedArray("dadosSensores");
  for(int i =0;i < QUANTIDADESENSORES;i++){
    sensores.add(tipoSensor[i]);
    sensores.add(dados[i]);
  }
   
  root.printTo(resposta);
  jsonBuffer.clear();
  return resposta;
  }
  
  // inicializa as configuracoes do sistema ao ligar o arduino.
void initialSettings(){
 //ler arquivo 0.
  File rFile = SPIFFS.open("0","r");
   if(rFile) {
        Serial.println("Lendo arquivo");
       //coloca todos os dados da primeira linha do arquivo em uma string.
       
        String line = rFile.readStringUntil('\n');
        JsonObject& root = jsonBuffer.parseObject(line);
        
        // receber dados de configuracao.
         String dados = root["arquivos"];
         String dadosLeitura = root["leitura"];
         String totalArqLeitura = root["totalArq"];
         arquivoAtual = dados.toInt();
         arquivoAtualLeitura = dadosLeitura.toInt();
         totalArq = totalArqLeitura.toInt();
         
         Serial.println("dados da posicao 0");
         Serial.println(line);
         rFile.close();     
         rFile = SPIFFS.open(dados,"r");
         if(rFile){
            Serial.println("Arquivo atual.");
            Serial.println(arquivoAtual);
            Serial.print("tamanho");
            int cont = 0;
            while(rFile.available()) {
                   rFile.readStringUntil('\n');
                   cont++;
            }
            tamanhoAtual = cont;
            Serial.println(tamanhoAtual);
            }else{
        tamanhoAtual = 0;
        Serial.println("falhou ao ler o arquivo com dados dos sensores..");
      }
    rFile.close();
    Serial.println("Dados Recebidos com Sucesso");
   
   }else
         Serial.println("Arquivo nao encontrado");
    
  }

  // altera as configuracoes de inicializacao do sistema.
  void generateSettings(int arq, int arqLeitura, int total){
    String resposta ;
     File rFile = SPIFFS.open("0","w+");
      if(rFile){
        JsonObject& root = jsonBuffer.createObject();
        root["arquivos"] = arq;
        root["leitura"] = arqLeitura;
        root["totalArq"] = total;
        root.printTo(resposta);
        rFile.println(resposta);
        Serial.println("dados das configuracoes atualizados com sucesso");
        Serial.println(resposta);
        jsonBuffer.clear();
        rFile.close();
       }else{
          Serial.println("Erro ao criar o arquivo");
          }
    }
