package TiposFormatos;

import org.json.JSONException;
import org.json.JSONObject;

public class DadosSantaElena extends Formatacao{

	@Override
	public void GerarFormato() {
		String[][] form = new String[getQuantDados()][6];
		String dados[][] = getDados().clone();
		for (int i = 0; i < getQuantDados(); i++) {
			form[i][0] =  Integer.toString(dados[i][2].hashCode());
			form[i][1] = dados[i][2];
			form[i][2] = dados[i][0];
			String datahora[] = dados[i][1].split(" ");
			form[i][3] = datahora[0];
			form[i][4] = datahora[1];

			try {
				JSONObject jsonObj = new JSONObject();

				jsonObj.put("temp_ar_med", dados[i][3]);
				jsonObj.put("temp_ar_max", dados[i][4]);
				jsonObj.put("temp_ar_min", dados[i][5]);
				jsonObj.put("umid_rel", dados[i][6]);
				jsonObj.put("umid_rel_max", dados[i][7]);
				jsonObj.put("umid_rel_min", dados[i][8]);
				jsonObj.put("precip_tot", dados[i][9]);
				jsonObj.put("vel_vento_2", dados[i][10]);
				jsonObj.put("vel_vento_10", dados[i][11]);
				jsonObj.put("dir_vento_10", dados[i][12]);
				jsonObj.put("vel_vento_max_2", dados[i][13]);
				jsonObj.put("vel_vento_max_10", dados[i][14]);
				jsonObj.put("cma6_inc numeric", "null");
				jsonObj.put("cma6_ref numeric", "null");
				jsonObj.put("cma6_alb numeric", "null");
				jsonObj.put("dir_vento_2 numeric", dados[i][15]);

				form[i][5] = jsonObj.toString();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		this.setDados(form);

		
	}

	public DadosSantaElena(int estacoes, String[][] dadosEstacoes) {
		setDados(dadosEstacoes);
		setQuantDados(estacoes);
	}
	
	

}
