package Transmisor;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Funcoes_Tranzacao funcoes = new Funcoes_Tranzacao();

		Ler_Grava_Arq ler_Grava = new Ler_Grava_Arq();

		List<String> lista = ler_Grava.lerArquivo();

		List<String> lista2 = new ArrayList<String>();
		for (int i = 0; i < lista.size(); i++) {

			String dadoCheckSum = gerarCheckSum(lista.get(i));
			String Dado_Check = lista.get(i).concat(dadoCheckSum);
			lista2.add(Dado_Check);
			System.out.println(lista.get(i));
			System.out.println(dadoCheckSum);
	

		}
		lista = new ArrayList<String>();

		lista = funcoes.insercaoDeBits(lista2);

		String conc = funcoes.inserirFlags(lista);
        
		ler_Grava.salvarFrameArquivo(conc);


	}

	static String gerarCheckSum(String dado) {
		String string = "00000";
		String polinomio = "110010";

		dado = dado.concat(string);

		char[] gerador = polinomio.toCharArray();
		char[] dados = dado.toCharArray();

		int contGerador = 0;
		int contDados = 0;
		boolean condicaoParada = true;

		while (dados[contDados] != '1') {
			contDados++;

		}

		while (condicaoParada) {
			if (gerador[contGerador] != dados[contDados]) {
				dados[contDados] = '1';

			} else {
				dados[contDados] = '0';
			}
			if (contGerador == 5) {
				contGerador = -1;
				condicaoParada = false;
				for (int j = 0; j < dados.length - 5; j++) {
					if (dados[j] == '1') {
						j--;
						contDados = j;
						condicaoParada = true;
						break;
					}

				}

			}

			contGerador++;
			contDados++;
		}
		
		String resposta = new String(dados);
		String checkSum = resposta.substring(resposta.length()-5, resposta.length());
		
		return checkSum;
	}


}
