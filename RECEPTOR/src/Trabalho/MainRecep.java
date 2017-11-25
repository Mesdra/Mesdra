package Trabalho;

import java.util.ArrayList;
import java.util.List;

public class MainRecep {

	public static void main(String[] args) {
		LerSalvaArq ler = new LerSalvaArq();
		String dados = ler.lerArquivo();
		FuncoesReceptor funcoes = new FuncoesReceptor();

		String primeiraDivi[] = dados.split("0111111001111110");
		List<String> listDados = new ArrayList<>();

		for (int i = 0; i < primeiraDivi.length; i++) {
			
			String segundaDivisao[] = primeiraDivi[i].split("01111110");
			
			for (int j = 0; j < segundaDivisao.length; j++) {
					if(!segundaDivisao[j].equals("01111110")){
						listDados.add(segundaDivisao[j]);
					}
			}

		}
		listDados = funcoes.retInsercaoDeBits(listDados);
		
		listDados = funcoes.checkSum(listDados);
		
		for (int i = 0; i < listDados.size(); i++){
			System.out.println(listDados.get(i));
		}
		

	}
}
