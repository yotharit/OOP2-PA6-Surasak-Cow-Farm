package farmData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Cow")
public class Cow {
	
	@DatabaseField(id = true)
	private long serialNumber;
	@DatabaseField
	private String importedDate;
	@DatabaseField
	private double importPrice;
	@DatabaseField
	private int age;
	@DatabaseField
	private String sex;
	@DatabaseField
	private String colour;
	@DatabaseField
	private int height;
	@DatabaseField
	private int weight;
	@DatabaseField
	private String horn;
	@DatabaseField
	private String eyelash;
	@DatabaseField
	private String nose;
	@DatabaseField
	private String flaw;
	
	public String getImportedDate() {
		return importedDate;
	}
	public void setImportedDate(String importedDate) {
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
