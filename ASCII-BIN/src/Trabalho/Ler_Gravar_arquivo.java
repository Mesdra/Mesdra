package Trabalho;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ler_Gravar_arquivo {

	public Ler_Gravar_arquivo() {
		super();

	}

	List<String> lerArquivo() {
		ProcuraArquivo escolhaArq = new ProcuraArquivo();
		List<String> lista = new ArrayList<String>();
		File arquivo = escolhaArq.escolherArquivo();
		String texto = new String();
		boolean condicao = true;
		try {

			Scanner sc = new Scanner(arquivo);

			while (sc.hasNext()) {
				texto = texto.concat(sc.next());
				texto = texto.concat(" ");
			}
			sc.close();
			int contador = 4;
			int contador2 = 0;
			int totalCaracteres = texto.length();
			
			while (condicao) {
				
				if (totalCaracteres >= contador) {
					lista.add(texto.substring(contador2, contador));
					contador = contador + 5;
					contador2 = contador2 + 5;
				} else {
					contador = totalCaracteres;
					lista.add(texto.substring(contador2, contador));
				}

				if (contador == texto.length()) {
					condicao = false;
				}

			}
			
				String teste = texto.substring(0,texto.length());
			System.out.println(teste);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void salvarFrameArquivo(List<String> lista) {
		ProcuraArquivo escolhaArq = new ProcuraArquivo();
		File arquivo = escolhaArq.escolherArquivo();
		try {
			FileWriter fw = new FileWriter(arquivo);
			for (int i = 0; i < lista.size(); i++) {
				fw.append(lista.get(i));
				fw.append("\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
