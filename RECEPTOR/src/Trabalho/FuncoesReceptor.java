package Trabalho;

import java.util.ArrayList;
import java.util.List;

public class FuncoesReceptor {

	public FuncoesReceptor() {
		super();
		// TODO Auto-generated constructor stub
	}

	List<String> retInsercaoDeBits(List<String> listaDados) {
		List<String> listaInc = new ArrayList<String>();

		for (int i = 0; i < listaDados.size(); i++) {
			String dado = listaDados.get(i);
			int cont = 0;
			for (int j = 0; j < dado.length(); j++) {
				if (dado.charAt(j) == '1') {
					cont++;
				} else {
					cont = 0;
				}
				if (cont == 5) {
					cont = 0;
					j++;
					StringBuilder stringBuilder = new StringBuilder(dado);
					String aux = stringBuilder.substring(0, j);
					dado = aux.concat(stringBuilder.substring(j + 1, dado.length()));
					j--;
				}

			}
			listaInc.add(dado);

		}

		return listaInc;
	}

	public String checkSum(String dado) {

		String polinomio = "110010";

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
		String checkSum = resposta.substring(resposta.length() - 5, resposta.length());

		return checkSum;

	}

}
