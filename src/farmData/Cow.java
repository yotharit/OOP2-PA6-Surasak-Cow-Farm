package farmData;

import java.time.LocalDate;

public class Cow {
	private LocalDate importedDate;
	private double importPrice;
	private long serialNumber;
	private int age;
	private String sex;
	private String colour;
	private int height;
	private int weight;
	private String horn;
	private String eyelash;
	private String nose;
	private String flaw;


	public Cow(LocalDate importedDate, double importPrice, long serialNumber, 
			int age, String sex, String colour,int height, int weight,
			String horn, String eyelash, String nose, String flaw) 
	{
		this.importedDate = importedDate;
		this.importPrice = importPrice;
		this.serialNumber = serialNumber;
		this.age = age;
		this.sex = sex;
		this.colour = colour;
		this.height = height;
		this.weight = weight;
		this.horn = horn;
		this.eyelash = eyelash;
		this.nose = nose;
		this.flaw = flaw;

	}
	
	public LocalDate getImportedDate() {
		return importedDate;
	}
	public void setImportedDate(LocalDate importedDate) {
		this.importedDate = importedDate;
	}
	public double getImportPrice() {
		return importPrice;
	}
	public void setImportPrice(double importPrice) {
		this.importPrice = importPrice;
	}
	public long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getHorn() {
		return horn;
	}
	public void setHorn(String horn) {
		this.horn = horn;
	}
	public String getEyelash() {
		return eyelash;
	}
	public void setEyelash(String eyelash) {
		this.eyelash = eyelash;
	}
	public String getNose() {
		return nose;
	}
	public void setNose(String nose) {
		this.nose = nose;
	}
	public String getFlaw() {
		return flaw;
	}
	public void setFlaw(String flaw) {
		this.flaw = flaw;
	}


}
