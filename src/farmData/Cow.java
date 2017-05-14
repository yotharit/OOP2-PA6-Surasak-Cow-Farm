package farmData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Cow")
public class Cow {

	@DatabaseField(id = true)
	private String serialNumber;
	@DatabaseField
	private String importedDate;
	@DatabaseField
	private String importPrice;
	@DatabaseField
	private String age;
	@DatabaseField
	private String sex;
	@DatabaseField
	private String height;
	@DatabaseField
	private String weight;
	@DatabaseField
	private String shed;
	@DatabaseField
	private String stall;
	@DatabaseField
	private String type;
	@DatabaseField(canBeNull = true)
	private String specificLook;
	@DatabaseField(canBeNull = true)
	private String firstvaccineDate;
	@DatabaseField(canBeNull = true)
	private String firstvaccineInfo;
	@DatabaseField(canBeNull = true)
	private String secondvaccineDate;
	@DatabaseField(canBeNull = true)
	private String secondvaccineInfo;
	@DatabaseField(canBeNull = true)
	private String thirdvaccineDate;
	@DatabaseField(canBeNull = true)
	private String thirdvaccineInfo;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImportedDate() {
		return importedDate;
	}
	public void setImportedDate(String importedDate) {
		this.importedDate = importedDate;
	}

	public String getShed() {
		return shed;
	}
	public void setShed(String shed) {
		this.shed = shed;
	}
	public String getStall() {
		return stall;
	}
	public void setStall(String stall) {
		this.stall = stall;
	}
	public String getFirstvaccineDate() {
		return firstvaccineDate;
	}
	public void setFirstvaccineDate(String firstvaccineDate) {
		this.firstvaccineDate = firstvaccineDate;
	}
	public String getFirstvaccineInfo() {
		return firstvaccineInfo;
	}
	public void setFirstvaccineInfo(String firstvaccineInfo) {
		this.firstvaccineInfo = firstvaccineInfo;
	}
	public String getSecondvaccineDate() {
		return secondvaccineDate;
	}
	public void setSecondvaccineDate(String secondvaccineDate) {
		this.secondvaccineDate = secondvaccineDate;
	}
	public String getSecondvaccineInfo() {
		return secondvaccineInfo;
	}
	public void setSecondvaccineInfo(String secondvaccineInfo) {
		this.secondvaccineInfo = secondvaccineInfo;
	}
	public String getThirdvaccineDate() {
		return thirdvaccineDate;
	}
	public void setThirdvaccineDate(String thirdvaccineDate) {
		this.thirdvaccineDate = thirdvaccineDate;
	}
	public String getThirdvaccineInfo() {
		return thirdvaccineInfo;
	}
	public void setThirdvaccineInfo(String thirdvaccineInfo) {
		this.thirdvaccineInfo = thirdvaccineInfo;
	}
	public String getImportPrice() {
		return importPrice;
	}
	public void setImportPrice(String importPrice) {
		this.importPrice = importPrice;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getSpecificLook() {
		return specificLook;
	}
	public void setSpecificLook(String specificLook) {
		this.specificLook = specificLook;
	}
}
