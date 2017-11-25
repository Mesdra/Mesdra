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

		try {
			Scanner sc = new Scanner(arquivo);
			while (sc.hasNext()) {
				dados = sc.next();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return dados;
	}

	public void salvarFrameArquivo(String dados) {

		File arquivo = escolherArquivo();
		try {
			FileWriter fw = new FileWriter(arquivo);
			fw.append(dados);
			fw.append("\n");

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

}
