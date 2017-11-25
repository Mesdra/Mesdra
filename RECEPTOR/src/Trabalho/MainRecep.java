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
		
		List<String> listaSaida = new ArrayList<>();
		List<String> listaAsctoBin = new ArrayList<>();
		
		for (int i = 1; i < listDados.size(); i++){
			String check = funcoes.checkSum(listDados.get(i));
			if(check.equals("00000")){
				listaSaida.add(listDados.get(i).concat(check));
				System.out.println("\ndado Correto");
				System.out.println(check);
				System.out.println(listDados.get(i));
				listaAsctoBin.add(listDados.get(i).substring(0, listDados.get(i).length()-5));
			}else{
				listaSaida.add(listDados.get(i).concat(check));
				System.out.println("\ndado Incorreto");
				System.out.println(check);
				System.out.println(listDados.get(i));
			}
		}
		ler.salvaFrameSaida(listaSaida);
		
		ler.salvarFrameArquivo(listaAsctoBin);
		
		
		

	}
}
