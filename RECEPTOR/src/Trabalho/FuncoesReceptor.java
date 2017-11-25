package Trabalho;

import java.util.ArrayList;
import java.util.List;

public class FuncoesReceptor {
	
	public FuncoesReceptor() {
		super();
		// TODO Auto-generated constructor stub
	}

	List<String> retInsercaoDeBits(List<String> listaDados){
		List<String> listaInc = new ArrayList<String>();
		
		for(int i = 0; i < listaDados.size();i++){
			String dado = listaDados.get(i);
			int cont = 0;
			StringBuilder stringBuilder = new StringBuilder(dado);
			for(int j=0;j < dado.length();j++ ){
				if(dado.charAt(j) == '1'){
					cont++;
				}else{
					cont = 0;
				}
				if(cont == 5){
					cont = 0;
					j++;
				    String aux = stringBuilder.substring(0,j);
				    dado = aux.concat(stringBuilder.substring(j+1, dado.length()));
					j--;
				}

				
			}
			listaInc.add(dado);
			
		}
		
		
		return listaInc;
	}

	public List<String> checkSum(List<String> listDados) {
		
		return null;
	}
	
	
	
	
}
