package upc.bdam.recommender.ontology.json;

/**
 * Cabecera del resultado de la consulta (de acuerdo con la estrucura JSon que devuelve wikidata)
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Head {
	
	private String[] vars;

	public String[] getVars() {
		return vars;
	}

	public void setVars(String[] vars) {
		this.vars = vars;
	}
}
