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

	List<String> lerArquivo(){
		ProcuraArquivo escolhaArq = new ProcuraArquivo();
		List<String> lista = new ArrayList<String>();
		File arquivo = escolhaArq.escolherArquivo();
		
		
		try {
			

			Scanner sc = new Scanner(arquivo);
			String resto = new String();
			while (sc.hasNext()) {
				String linha = sc.next();
				int cont1=0;
				int cont2=5;
				if(resto.length() != 0 ){
					int total = -(resto.length() -5);
					String contatenacao = resto.concat(linha.substring(0, total));
					lista.add(contatenacao);
					cont1= total;
					cont2= total + 5;
					resto = new String();
					
				}
				while(cont2 <= linha.length()){
					
						lista.add(linha.substring(cont1, cont2));
						cont1 = cont1 +5;
						cont2 = cont2 +5;
				}
				
				if(cont1-linha.length() != 0){
					resto = linha.substring(cont1,linha.length());
				}
			}
			if(resto.length() != 0)
				lista.add(resto);
			sc.close();
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
			for (int i=0;i<lista.size();i++) {
				fw.append(lista.get(i));
				 fw.append("\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
