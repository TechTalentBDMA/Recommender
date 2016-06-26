package upc.bdam.recommender.kafka;

import upc.bdam.recommender.config.PropertiesLoader;

/**
 * Bean de caracter general para el env�o de informaci�n a la cola kafka
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class KafkaBean {
	public static final byte KAFKA_VIDEO=1;
	public static final byte KAFKA_AUDIO=2;
	public static final byte KAFKA_TEXTO=3;
	public static final byte KAFKA_WEB=4;
	
	private static final String USER_DATA_NICK_NAME="nickName";
	private static final String USER_DATA_NICK="name";
	private static final String USER_DATA_SURNAME="surname";
	private static final String USER_DATA_BORN="born";	
	private static final String USER_DATA_SEX="sex";
	private static final String USER_DATA_PROFESSION="profession";
	private static final String USER_DATA_HOBBY="hobby";
	private static final String USER_DATA_INTERESTED_IN="interestedIn";
	
	//declaraci�n de atributos del usuario
	private String name;
	private String surname;
	private String born;
	private String sex;
	private String profession;
	private String hobby;
	private String interestedIn;
	
	//declaraci�n de atributos	
	private String metadata;
	private String content;
	private String id=new String();
	private String status=new String();
	private String mimeType=new String();
	private String palabras=new String();
	private String nickName=new String();;
	private String fichero=new String();
	private String url=new String();

	
	/**
	 * En el constructor del bean, y a falta de interfaz gr�fica que permita pedir 
	 * los datos al usuario, los cargaremos de un fichero de configuraci�n para la demo
	 */
	public KafkaBean(){
		PropertiesLoader properties=PropertiesLoader.getInstance();
		nickName=properties.getProperty(USER_DATA_NICK_NAME);
		name=properties.getProperty(USER_DATA_NICK);
		surname=properties.getProperty(USER_DATA_SURNAME);
		born=properties.getProperty(USER_DATA_BORN);
		sex=properties.getProperty(USER_DATA_SEX);
		profession=properties.getProperty(USER_DATA_PROFESSION);
		hobby=properties.getProperty(USER_DATA_HOBBY);
		interestedIn=properties.getProperty(USER_DATA_INTERESTED_IN);		
	}
	
	
	public String getUrl() {
		return url;
	}

	public String getPalabras() {
		return palabras;
	}

	public void setPalabras(String palabras) {
		this.palabras = palabras;
	}
	
	public String getStatus() {
		return status;
	}

	public String getFichero() {
		return fichero;
	}

	public String getNickName() {
		return nickName;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getBorn() {
		return born;
	}
	public String getSex() {
		return sex;
	}
	public String getProfession() {
		return profession;
	}
	public String getHobby() {
		return hobby;
	}
	public String getInterestedIn() {
		return interestedIn;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setBorn(String born) {
		this.born = born;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public void setInterestedIn(String interestedIn) {
		this.interestedIn = interestedIn;
	}

	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getType() {
		return status;
	}
	public void setType(String type) {
		this.status = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	//DECLARACI�N DE M�TODOS GETTERS
	public String getMetadata() {
		return metadata;
	}
	public String getContent() {
		return content;
	}
	
	//DECLARACI�N DE M�TODOS SETTERS
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
