package upc.bdam.recommender.Big2DDBB.dao;

import java.util.List;

import upc.bdam.recommender.graphupdater.schema.Big2SGraphGuardSchema;

/**
 * Clase de interfaz para la inserci�n de datos en Big1
 * 
 * @author Grupo 9: - Antol�n Barrena Rico - Carles Castillejo - Raffaele
 *         Ghermandi - David P�rez Rodr�guez
 *
 */
public class Big2DocumentAccessObject {

	private Big2DataSource B2DS = new Big2DataSource();

	public List<Big2SGraphGuardSchema> checkBig2TextUpdate(long timestamp) {
		return B2DS.checkBig2TextUpdate(timestamp);
	}

	/**
	 * M�todo con el que se consulta si existen m�s inserciones en Big2 desde la
	 * �ltima lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2WebUpdate(long timestamp) {
		return B2DS.checkBig2WebUpdate(timestamp);
	}

	/**
	 * M�todo con el que se consulta si existen m�s inserciones en Big2 desde la
	 * �ltima lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2VideoUpdate(long timestamp) {
		return B2DS.checkBig2VideoUpdate(timestamp);
	}

	/**
	 * M�todo con el que se consulta si existen m�s inserciones en Big2 desde la
	 * �ltima lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2AudioUpdate(long timestamp) {
		return B2DS.checkBig2AudioUpdate(timestamp);
	}
}
