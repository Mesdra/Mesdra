String dataHoraAtual(){
RtcDateTime currentTime = rtcObject.GetDateTime();    //get the time from the RTC
  JsonObject& root = jsonBuffer.createObject();
  root["ano"] = currentTime.Year();
  root["mes"] = currentTime.Month();
  root["dia"] = currentTime.Day();
  float minutos = (float)currentTime.Minute();
  float hora = currentTime.Hour() + minutos/100;
  root["hora"] = hora;
 
   String resposta;
  root.printTo(resposta);
  jsonBuffer.clear();
  return resposta;

}

boolean atualizarHoraInternet(){
  // fazer um mecanismo que atulize o horario via internet todos os dia em um horario especifico.
   RtcDateTime currentTime = RtcDateTime(18, 5, 3, 14, 9, 0); //define date and time object
  rtcObject.SetDateTime(currentTime); 
  }
