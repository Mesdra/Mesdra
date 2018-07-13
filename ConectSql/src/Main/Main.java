package Main;

import java.io.IOException;

import org.json.JSONException;

import Arquivos.LerArq;
import SqlConect.ConeccaoBancodeDados;
import TiposFormatos.DadosSimepar;
import TiposFormatos.Formatacao;
import TratamentoDosDados.separacaoDeDados;

public class Main {

	public static void main(String[] args) throws IOException, JSONException {
		LerArq testeLeitura = new LerArq();

		testeLeitura.lerDadosArquivos();
		separacaoDeDados separacaoDeDados = new separacaoDeDados();

		separacaoDeDados.separaDadosPorVirgula(testeLeitura.getDados());

		Formatacao formato = new DadosSimepar(testeLeitura.getQuantDados(), separacaoDeDados.getDadosSeparados());
		formato.GerarFormato();
		
		ConeccaoBancodeDados banco = new ConeccaoBancodeDados();
		banco.conecPostgre(formato.getDados(),formato.getQuantDados());

		// tratamentoJson.teste();
	}

}
