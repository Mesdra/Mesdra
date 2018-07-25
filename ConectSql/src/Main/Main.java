package Main;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;

import Arquivos.LerArq;
import FTPConect.CaminhoRemoto;
import FTPConectFormatos.RequisicaoDeArquivos;
import SqlConect.ConeccaoBancodeDados;
import TiposFormatos.Formatacao;

public class Main {

	public static void main(String[] args) throws IOException, JSONException {
		// teste com a requisicao de dados FTP.
		CaminhoRemoto caminho = new CaminhoRemoto();
		// cria instancia de RequisicaoDeArquivos de acordo com a estacao desejada.
		RequisicaoDeArquivos leiturasDosArquivos = caminho.buscarNovaLeitura("iapar");
		String caminhoDiretorio = leiturasDosArquivos.recebeArquivos();
		System.out.println(caminhoDiretorio);
		// funcionamento do sistema de insercao dos dados utilizadndo um reposit[orio
		// local

		final File folder = new File(caminhoDiretorio);

		for (final File fileEntry : folder.listFiles()) {

			// recebe a lista de arquivos dentro do diretorio. 
			LerArq leituras = new
			LerArq();

			// separa todas as leituras do arquivo atual em linhas.
			leituras.lerDadosArquivos(fileEntry.getName(), caminhoDiretorio);

			// formata os dados para a insercao no banco. 
			Formatacao formato =leituras.tratamentoDaMensagem();
			formato.GerarFormato();

			// conecta e envia os dados para o banco. 
			ConeccaoBancodeDados banco = new ConeccaoBancodeDados();

			banco.conecPostgre(formato.getDados(), formato.getQuantDados());

		}

	}

}
