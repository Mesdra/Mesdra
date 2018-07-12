package Arquivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
}
