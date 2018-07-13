package Arquivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import TiposFormatos.DadosSantaElena;
import TiposFormatos.DadosSimepar;
import TiposFormatos.Formatacao;

public class LerArq {
	private String[] dados;
	private int quantDados;

	public void lerDadosArquivos() throws IOException {

		BufferedReader br;
		try {
			br = new BufferedReader(
					new FileReader("C:\\Users\\vini\\Desktop\\Estagio\\dadosLeituraSensores\\SIMEPAR-2018-07-08.txt"));

			int lines = 0;
			while (br.readLine() != null)
				lines++;
			br.close();
			quantDados = lines;
			br = new BufferedReader(
					new FileReader("C:\\Users\\vini\\Desktop\\Estagio\\dadosLeituraSensores\\SIMEPAR-2018-07-08.txt"));
			int i = 0;
			dados = new String[lines];
			while (br.ready()) {
				dados[i] = br.readLine();
				i++;
			}
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public LerArq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String[] getDados() {
		return dados;
	}

	public void setDados(String[] dados) {
		this.dados = dados;
	}

	public int getQuantDados() {
		return quantDados;
	}

	public void setQuantDados(int quantDados) {
		this.quantDados = quantDados;
	}

	public Formatacao tratamentoDaMensagem() {

		final String[] separacao = dados[0].split(";");
		if (separacao.length != 1) {
			String[][] dadosSeparados = new String[quantDados][];
			for (int i = 0; i < dados.length; i++) {
				String[] separacao1 = dados[i].split(",");

				dadosSeparados[i] = separacao1;

			}
			return new DadosSimepar(quantDados, dadosSeparados);
		}
		// continuar desenvolvento as condicoes fazer o teste do formato sinepar e gerar
		// as condicoes para os tipos de estacoes dentro do segundo if
		if (dados[1] == " ") {
			this.quantDados = quantDados / 2;
			int contador = 0;
			String[][] dadosSeparados = new String[quantDados][];
			for (int i = 0; i < dados.length; i++) {
				String[] separacao1 = dados[i].split(",");
				if (separacao1.length != 1) {
					dadosSeparados[contador++] = separacao1;
				}
			}
			// dadosSeparados;
			return new DadosSantaElena(quantDados, dadosSeparados);
		}

		return null;
	}
}
