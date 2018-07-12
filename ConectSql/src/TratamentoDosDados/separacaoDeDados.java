package TratamentoDosDados;

import java.util.regex.Pattern;

public class separacaoDeDados {

	private String[][] dadosSeparados;
	
	public void separaDadosPorVirgula(String dados[]){
		dadosSeparados = new String[dados.length][];
		for (int i = 0; i < dados.length; i++) {
			String[] separacao =dados[i].split(";");
			if(separacao.length != 1)
			dadosSeparados[i] = separacao;
		}
		
	}

	public separacaoDeDados() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String[][] getDadosSeparados() {
		return dadosSeparados;
	}

	public void setDadosSeparados(String[][] dadosSeparados) {
		this.dadosSeparados = dadosSeparados;
	}
	
}
