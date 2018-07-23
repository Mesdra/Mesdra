package Main;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;

import Arquivos.LerArq;
import FTPConect.CaminhoRemoto;
import FTPConectFormatos.RequisiçãoDeArquivos;
import FTPConectFormatos.RequisiçãoDeArquivosSimepar;
import SqlConect.ConeccaoBancodeDados;
import TiposFormatos.Formatacao;

public class Main {


	public static void main(String[] args) throws IOException, JSONException {
		CaminhoRemoto caminho = new CaminhoRemoto();
		caminho.buscarNovaLeitura("simepar");
		
		/*final File folder = new File("/home/vinicius/Documentos/leituras");
		
		 for (final File fileEntry : folder.listFiles()) {
			 
			 
		      
			 LerArq leituras = new LerArq();

				leituras.lerDadosArquivos(fileEntry.getName());
				
				Formatacao formato = leituras.tratamentoDaMensagem();

				formato.GerarFormato();
				
				ConeccaoBancodeDados banco = new ConeccaoBancodeDados();
			
				banco.conecPostgre(formato.getDados(),formato.getQuantDados());
	
		    }*/

		
	}

}
