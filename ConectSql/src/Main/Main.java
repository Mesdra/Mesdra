package Main;

import java.io.IOException;

import Arquivos.LerArq;
import TratamentoDosDados.separacaoDeDados;

public class Main {

	public static void main(String[] args) throws IOException {
		LerArq testeLeitura = new LerArq();
		
		testeLeitura.lerDadosArquivos();
		separacaoDeDados separacaoDeDados = new separacaoDeDados();
		String[] dados = testeLeitura.getDados();
		
		separacaoDeDados.separaDadosPorVirgula(dados);
		String[][] dadosSeparados = separacaoDeDados.getDadosSeparados();
		

	}

}
