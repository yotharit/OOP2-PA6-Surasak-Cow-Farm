package farmData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Setting")
public class Setting {

	@DatabaseField(id = true)
	private String setting = "default";
	@DatabaseField
	private String currentUser;
	@DatabaseField
	private double cowPrice = 1000;
	@DatabaseField
	private double fertilizerPrize = 300;
	
	public String getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	public double getCowPrice() {
		return cowPrice;
	}
	public void setCowPrice(double cowPrice) {
		this.cowPrice = cowPrice;
	}
	public double getFertilizerPrize() {
		return fertilizerPrize;
	}
	public void setFertilizerPrize(double fertilizerPrize) {
		this.fertilizerPrize = fertilizerPrize;
	}
	
}
