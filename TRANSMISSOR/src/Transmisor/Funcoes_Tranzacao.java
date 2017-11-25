package Transmisor;

import java.util.ArrayList;
import java.util.List;

public class Funcoes_Tranzacao {

	
	
	List<String> insercaoDeBits(List<String> listaDados){
		List<String> listaInc = new ArrayList<String>();
		
		for(int i = 0; i < listaDados.size();i++){
			String dado = listaDados.get(i);
			int cont = 0;
			//StringBuilder stringBuilder = new StringBuilder(dado);
			for(int j=0;j < dado.length();j++ ){
				if(dado.charAt(j) == '1'){
					cont++;
				}else{
					cont = 0;
				}
				if(cont == 5){
					cont = 0;
					j++;
					StringBuilder stringBuilder = new StringBuilder(dado);
					//System.out.println(stringBuilder.length());
				    dado = stringBuilder.insert(j, '0').toString();
					//System.out.println(stringBuilder.length());
					j--;
				}

				
			}
			listaInc.add(dado);
			
		}
		
		
		return listaInc;
	}

	public Funcoes_Tranzacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String inserirFlags(List<String> lista) {
		String dados = new String();
		String dados2 = new String(); 
		String flag = "01111110";
		
		
		 
		 for(int i = 0 ;i < lista.size();i++ ){
	    dados2 = flag.concat(lista.get(i));
		dados2 = dados2.concat(flag);	 
		dados = dados.concat(dados2);	 
		 }
		
		
		return dados;
	}
}
