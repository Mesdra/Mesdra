package Main;

import java.io.IOException;

import org.json.JSONException;

import Arquivos.LerArq;
import SqlConect.ConeccaoBancodeDados;
import TiposFormatos.Formatacao;

public class Main {

	public static void main(String[] args) throws IOException, JSONException {
		LerArq leituras = new LerArq();

		leituras.lerDadosArquivos();
		
		Formatacao formato = leituras.tratamentoDaMensagem();

		formato.GerarFormato();
		
		ConeccaoBancodeDados banco = new ConeccaoBancodeDados();
	
		banco.conecPostgre(formato.getDados(),formato.getQuantDados());

		
	}

}
