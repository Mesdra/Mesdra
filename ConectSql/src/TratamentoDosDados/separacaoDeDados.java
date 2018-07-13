package TratamentoDosDados;

import java.util.regex.Pattern;

public class separacaoDeDados {

	private String[][] dadosSeparados;

	public void separaDadosPorVirgula(String dados[]) {
		int contador = 0;
		dadosSeparados = new String[dados.length][];
		for (int i = 0; i < dados.length; i++) {
			String[] separacao = dados[i].split(",");
			if (separacao.length != 1) {
				dadosSeparados[contador++] = separacao;
			}
		}

	}

	public separacaoDeDados() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String[][] getDadosSeparados() {
		return dadosSeparados;
	}


}
