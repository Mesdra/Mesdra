package Trabalho;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LerSalvaArq {
	public LerSalvaArq() {
		super();

	}

	String lerArquivo() {
		String dados = new String();
		File arquivo = escolherArquivo();
		List<String> dadosArq = new ArrayList<>();
		try {
			Scanner sc = new Scanner(arquivo);
			while (sc.hasNext()) {
				dadosArq.add(sc.next());
			}
			sc.close();
			
			for(int i=0;i < dadosArq.size();i++){
				dados=dados.concat(dadosArq.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return dados;
	}

	public void salvarFrameArquivo(List<String> listaAsctoBin) {

		File arquivo = escolherArquivo();
		try {
			String dado = new String();
			String texto = new String();
			FileWriter fw = new FileWriter(arquivo);
			for (int i = 0; i < listaAsctoBin.size(); i++) {
				dado = listaAsctoBin.get(i);
				dado = BinaryToAsc(dado);
				texto = texto.concat(dado);
			}
			fw.append(texto);

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File escolherArquivo() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Text file", "txt");
		fc.addChoosableFileFilter(textFilter);

		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}

	public static String BinaryToAsc(String input) {
		String output = "";
		for (int i = 0; i <= input.length() - 8; i += 8) {
			int k = Integer.parseInt(input.substring(i, i + 8), 2);
			output += (char) k;
		}

		return output;
	}

	public void salvaFrameSaida(List<String> listaSaida) {
		File arquivo = escolherArquivo();
		try {
			FileWriter fw = new FileWriter(arquivo);
			for (int i = 0; i < listaSaida.size(); i++) {
				String dado = listaSaida.get(i);
				String verificador = dado.substring(dado.length() - 5, dado.length());
				dado = dado.substring(0, dado.length() - 5);
				String check = dado.substring(dado.length() - 5, dado.length());
				if (verificador.equals("00000")) {
					dado = dado.substring(0, dado.length() - 5);
					dado = dado.concat("  CheckSum " + check + "(OK)");
				} else {
					dado = dado.concat("  (ERRO)");
				}
				fw.append(dado);
				fw.append("\n");
			}

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
