package Trabalho;

import java.util.ArrayList;
import java.util.List;
import Trabalho.Ler_Gravar_arquivo;


public class Main {

	public static void main(String[] args) {
		
		List<String> listaBin = new ArrayList<String>();
		List<String> lista = new ArrayList<String>();
		Ler_Gravar_arquivo lerGrava = new Ler_Gravar_arquivo();
		lista = lerGrava.lerArquivo();
		
		for(int i=0 ;i < lista.size();i++){
			
			String dado = AscToBinary(lista.get(i));
			listaBin.add(dado);
		}
	
		lerGrava.salvarFrameArquivo(listaBin);

	}
	
	public static String AscToBinary(String caracter) {

		byte[] bytes = caracter.getBytes();
		StringBuffer binary = new StringBuffer();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		return binary.toString();
	}
	
	public static String BinaryToAsc(String input) {
		String output = "";
		for (int i = 0; i <= input.length() - 8; i += 8) {
			int k = Integer.parseInt(input.substring(i, i + 8), 2);
			output += (char) k;
		}

		return output;
	}

}
