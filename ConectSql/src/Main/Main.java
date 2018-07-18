package Main;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;

import Arquivos.LerArq;
import SqlConect.ConeccaoBancodeDados;
import TiposFormatos.Formatacao;

public class Main {

	public static void main(String[] args) throws IOException, JSONException {
		
		final File folder = new File("C:\\Users\\vini\\Desktop\\Estagio\\dadosLeituraSensores");
		
		 for (final File fileEntry : folder.listFiles()) {
			 
			 
		      
			 LerArq leituras = new LerArq();

				leituras.lerDadosArquivos(fileEntry.getName());
				
				Formatacao formato = leituras.tratamentoDaMensagem();

				formato.GerarFormato();
				
				ConeccaoBancodeDados banco = new ConeccaoBancodeDados();
			
				banco.conecPostgre(formato.getDados(),formato.getQuantDados());
	
		    }

		
	}

}
