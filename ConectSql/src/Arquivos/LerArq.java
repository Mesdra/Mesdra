package Arquivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LerArq {
	private String[] dados;

	public void lerDadosArquivos() throws IOException {

		BufferedReader br;
		try {
			br = new BufferedReader(
					new FileReader("C:\\Users\\vini\\Desktop\\Estagio\\dadosLeituraSensores\\2018_190_400_SH01.txt"));
	
			int lines = 0;
			while (br.readLine() != null)
				lines++;
			br.close();
			br = new BufferedReader(
					new FileReader("C:\\Users\\vini\\Desktop\\Estagio\\dadosLeituraSensores\\2018_190_400_SH01.txt"));
			int i=0 ;
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
}
