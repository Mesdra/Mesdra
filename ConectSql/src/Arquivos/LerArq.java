package Arquivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import TiposFormatos.DadosSantaElena;
import TiposFormatos.DadosSimepar;
import TiposFormatos.DadosSoloVivo3;
import TiposFormatos.DadosSoloVivo6;
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
			for (int i = 0; i < this.dados.length; i++) {
				String[] separacaoSimepar = dados[i].replaceAll("\"", " ").split(";");

				dadosSeparados[i] = separacaoSimepar;

			}
			return new DadosSimepar(quantDados, dadosSeparados);
		} else {
			this.quantDados = quantDados / 2;
			int contador = 0;
			String[][] dadoSeparadsEstacoes = new String[quantDados][];
			for (int i = 0; i < this.dados.length; i++) {
				String[] dadosSantaElena = dados[i].replaceAll("\"", "").split(",");
				if (dadosSantaElena.length != 1) {
					dadoSeparadsEstacoes[contador++] = dadosSantaElena;
				}
			}
			if (dadoSeparadsEstacoes[0][2].equals("SH01"))
				return new DadosSantaElena(quantDados, dadoSeparadsEstacoes);
			if (dadoSeparadsEstacoes[0][2].equals("SVTOL03"))
				return new DadosSoloVivo3(quantDados, dadoSeparadsEstacoes);
			if (dadoSeparadsEstacoes[0][2].equals("SVTOL06"))
				return new DadosSoloVivo6(quantDados, dadoSeparadsEstacoes);

			return null;
		}
	}
}
