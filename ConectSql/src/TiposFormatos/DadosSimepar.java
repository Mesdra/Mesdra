package TiposFormatos;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

public class DadosSimepar implements Formatacao {
	private String[][] dados;
	private int quantDados;

	@Override
	public String[][] GerarFormato() {
		String[][] form = new String[quantDados][5];

		for (int i = 0; i < quantDados; i++) {

			form[i][0] = "Simepar";
			form[i][1] = dados[i][0];
			String datahora[] = dados[i][1].split(" ");
			String data[] = datahora[1].split("/");
			String dataForm = data[2].concat("-").concat(data[1].concat("-").concat(data[0]));		
			form[i][2] = dataForm;
			form[i][3] = datahora[2].concat(":00");
			

			try {
				JSONObject jsonObj = new JSONObject();
						
				jsonObj.put("presMed", dados[i][2]);
				jsonObj.put("presMin", dados[i][3]);
				jsonObj.put("presMax", dados[i][4]);
				jsonObj.put("tempMed", dados[i][5]);
				jsonObj.put("tempMin", dados[i][6]);
				jsonObj.put("tempMax", dados[i][7]);
				jsonObj.put("umidrel", dados[i][8]);
				jsonObj.put("radSolar", dados[i][9]);
				jsonObj.put("velVento", dados[i][10]);
				jsonObj.put("dirVento", dados[i][11]);
				jsonObj.put("velVentoMax", dados[i][12]);
				jsonObj.put("chuva", dados[i][13]);
				jsonObj.put("chuvaAc", dados[i][14]);
	
				form[i][4] = jsonObj.toString();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		}

		return form;
	}

	public DadosSimepar(int estacoes,String[][] dadosEstacoes) {
		this.dados = dadosEstacoes;
		this.quantDados = estacoes;
	}

}
