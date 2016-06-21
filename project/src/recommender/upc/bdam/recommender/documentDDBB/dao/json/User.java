package upc.bdam.recommender.documentDDBB.dao.json;

public class User {
	private String nickName=new String();
	private String name=new String();
	private String surname=new String();
	private String born=new String();
	private String sex=new String();
	private String profession=new String();
	private String hobby=new String();
	private String interestedIn=new String();
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
	
	
}
