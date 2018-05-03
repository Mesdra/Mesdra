void formatFS(void){
  SPIFFS.format();
}

 // funcao usada para criar um novo arquivo
void createFile(String arq){
  File wFile;
 
  //Cria o arquivo se ele não existir
  if(SPIFFS.exists(arq)){
    Serial.println("Arquivo ja existe!");
  } else {
    Serial.println("Criando o arquivo...");
    wFile = SPIFFS.open(arq,"w+");
 
    //Verifica a criação do arquivo
    if(!wFile){
      Serial.println("Erro ao criar arquivo!");
    } else {
      Serial.println("Arquivo criado com sucesso!");
    }
  }
  wFile.close();
}


 
void deleteFile(String arq) {
  //Remove o arquivo
  if(SPIFFS.remove(arq)){
    Serial.println("Arquivo removido com sucesso!");
    Serial.println(arq);
  }
}


 // esta funcao escreve os dados no arquivo quando nao tem internet.
String writeFile(String msg) {
  String arq = validateFile();
  //Abre o arquivo para adição (append)
  //Inclue sempre a escrita na ultima linha do arquivo
 if(tamanhoAtual == 0){
  SPIFFS.remove((String)arquivoAtual);
   Serial.println("deletar o arquivo antes de gravar");
 }
 digitalWrite(GUARDANDODADOS,HIGH);
  File rFile = SPIFFS.open(arq,"a+");
  if(!rFile){
    Serial.println("Erro ao abrir arquivo!");
    return msg;
  } else {
    rFile.println(msg);
   Serial.print("Mensagem escrita no arquivo = ");
   Serial.print(arquivoAtual);
   Serial.print("/ posicao = ");
   Serial.print(tamanhoAtual);
   Serial.print("/ dado");
   Serial.println(msg);
  }
  delay(DELAYLED);
  digitalWrite(GUARDANDODADOS,LOW);
  rFile.close();
  return "leitura";
}


// envia os dados para o servidor sequencialmente des do dado mais antigo até o atual.  
void readFileAndSending(int arq) {
 
  boolean condicao = true;
  int i=0;
  
  File rFile = SPIFFS.open((String)arq,"r");
  Serial.println("Reading file...");
  Serial.println(arq);
  while(rFile.available()) {
    String line = rFile.readStringUntil('\n');
    i++;
    Serial.print(i);
    digitalWrite(ENVIADOMQTT, HIGH);
    Serial.print("= enviando dados para o servidor MQTT");
    char dados[line.length()];
    line.toCharArray(dados,line.length());
    if (! Public.publish(dados)) {
    Serial.println("Failed");
  } else {
    Serial.println("OK!");
  }
  delay(DELAYLED);
  digitalWrite(ENVIADOMQTT, LOW);

    if(!conectMQTTWifi()){
       bufferMode = true;
       condicao = false;
      break;
    }
  }
  if(arquivoAtual == arquivoAtualLeitura && totalArq == 1){
    tamanhoAtual =0;
    deleteFile((String)arquivoAtual);
    Serial.println("tinha apenas um arquivo para ser enviado");
     generateSettings(arquivoAtual,arquivoAtualLeitura,totalArq);
     validaEscrita = false;
     bufferMode = false;
  }else{
  if(condicao == true){
    nomeNovoArqLeitura();
    validaEscrita = true;
  generateSettings(arquivoAtual,arquivoAtualLeitura,totalArq);
  }
  }
  rFile.close();
  
}
// fecha sistema de arquivos. 
void closeFS(void){
  SPIFFS.end();
}
// abre o sistema de arquivos 
void openFS(void){
  //Abre o sistema de arquivos
  if(!SPIFFS.begin()){
    Serial.println("Erro ao abrir o sistema de arquivos");
  } else {
    Serial.println("Sistema de arquivos aberto com sucesso!");
  }
}

// valida o arquivo para leitura com algumas condicoes,como o total de dados por arquivo.
String validateFile(){
  if(tamanhoAtual >= TAMANHOARQ){
    tamanhoAtual = 0;
    contador++; // utilizado para gerar os testes
    nomeNovoArq();
     Serial.print("Arquivo cheio novo arquivo Gerado =");
     Serial.println(arquivoAtual);
     generateSettings(arquivoAtual,arquivoAtualLeitura,totalArq);
     deleteFile((String)arquivoAtual);
    return (String)arquivoAtual;
    }else 
    //valida a primeira escrita no arquivo  
    if(validaPrimeiraLeitura == true){
            deleteFile((String)arquivoAtual);
            validaPrimeiraLeitura = false;
            }
   tamanhoAtual++; 
   return (String)arquivoAtual;
  }

// quando chegar ao ultimo arquivo de dados esta funcao ira enviar os dados e acabar com o sistema de arquivos voltando a enviar os dados 
//direto pelo servidor MQTT.  
boolean validateFileRead(){
  if(arquivoAtual == arquivoAtualLeitura && bufferMode && totalArq == 1 && validaEscrita == true){
     tamanhoAtual = 0;
    File rFile = SPIFFS.open((String)arquivoAtualLeitura,"r");
         if(rFile){
            Serial.println("Verificando ultimo arquivo de dados");
            
            while(rFile.available()) {
                   String line = rFile.readStringUntil('\n');
                   digitalWrite(ENVIADOMQTT, HIGH);
                   Serial.print("enviando dados para o servidor MQTT");
                   Serial.println(line);
                   delay(DELAYLED);
                   digitalWrite(ENVIADOMQTT, LOW);
                   
            }
            }
     Serial.println("sistema de buffer finalizado, Retornando ao modo online");       
     rFile.close();
     //nomeNovoArq();
     deleteFile((String)arquivoAtual);
     generateSettings(arquivoAtual,arquivoAtualLeitura,totalArq);
     bufferMode = false;
     validaEscrita = false;
     return false;
    }
    return true;
  }
/*
 * struct FSInfo {
    size_t totalBytes;
    size_t usedBytes;
    size_t blockSize;
    size_t pageSize;
    size_t maxOpenFiles;
    size_t maxPathLength;
};variaveiz que podem ser utilizadas sobre as informacoes dos arquivos.
 
boolean memoriaCheia(){
  FSInfo fs_info;
  if(SPIFFS.info(fs_info)){
     
      if(fs_info.totalBytes != fs_info.usedBytes){
        return false;
        }else 
        return true;
    }else
     Serial.println("erro ao ler a memoria");
     return NULL;
  }
*/
// funcao usada para valivar o inicio da reduncancia ciclica de dados quando o limite de arquivo for alcancado (MEMORIATOTAL).
boolean memoriaCheia(){
  FSInfo fs_info;
  if(SPIFFS.info(fs_info)){
   if(fs_info.usedBytes >= MEMORIATOTAL){
        return true;
        Serial.println("memoriaCheia");
        }else {
        Serial.print("Memoria Total  = ");
        Serial.println(fs_info.totalBytes);
        Serial.print("/ TotalUtilizado = ");
        Serial.println(fs_info.usedBytes);
        return false;
        }
  }
    } 
// gera novo arquivo para gravacao respeitando os paramentro setados no inicio do codigo.
 void nomeNovoArq(){
  memoriaCheia();
    if(arquivoAtual +1 == arquivoAtualLeitura || arquivoAtual == TOTALARQUIVOS && arquivoAtualLeitura == 1){
      Serial.println("memoria ciclica iniciada, perdendo dados mais antigos dos sensores");
      digitalWrite(CICLO, HIGH);
      condCiclica = true;
      }else{
        Serial.println("ciclo low");
        digitalWrite(CICLO, LOW);
        condCiclica = false;
        }
    
    if(arquivoAtual == TOTALARQUIVOS){
      arquivoAtual = 1;
       if(arquivoAtual == 1 && arquivoAtualLeitura == 1)
        arquivoAtualLeitura++; 
      else 
      if(arquivoAtual+1 == arquivoAtualLeitura){
        if(arquivoAtualLeitura == TOTALARQUIVOS)
          arquivoAtualLeitura = 1;
          else
          arquivoAtualLeitura++;   
      }
      //if(arquivoAtual == 1 && TOTALARQUIVOS == arquivoAtualLeitura)
        //arquivoAtualLeitura = 1;
       if(totalArq != TOTALARQUIVOS)
        totalArq++;
    }
    else{   
      if(arquivoAtual+1 == arquivoAtualLeitura && arquivoAtualLeitura != TOTALARQUIVOS)
        arquivoAtualLeitura++;
      if(arquivoAtualLeitura == TOTALARQUIVOS && arquivoAtual+1 == arquivoAtualLeitura)
        arquivoAtualLeitura = 1;  
    arquivoAtual ++;  

    if(totalArq != TOTALARQUIVOS)
      totalArq++;
    }
  }
//deleta todos os arquivos.
  void deletALL(){
    for(int i = 0;i < TOTALARQUIVOS;i++){
      SPIFFS.remove((String)i);
     Serial.println("dados Removidos com sucesso");
     
    }
    generateSettings(1,1,1);
    }

  // gera novo arquivo para leitura respeitando os paramentro setados no inicio do codigo.
  void nomeNovoArqLeitura(){
    memoriaCheia();
    boolean val = true;
    if(arquivoAtualLeitura == TOTALARQUIVOS){
      arquivoAtualLeitura = 1;
      Serial.println("memoria ciclica finalizada");
      digitalWrite(CICLO, LOW);
      condCiclica = false;
      val = false ;
    }
    if(val == true)
      arquivoAtualLeitura++;
      if(totalArq > 1){
        totalArq--;
      }
    
  }

