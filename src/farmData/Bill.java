package farmData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
/**
 * Bill class use with OrmLite
 * @author Tharit Pongsaneh
 *
 */
@DatabaseTable(tableName = "Bill")
public class Bill {

	@DatabaseField(id = true)
	private String billnumer;
	@DatabaseField
	private String buyer;
	@DatabaseField
	private String date;
	@DatabaseField
	private String sellInfomation;
	@DatabaseField
	private String sumPrice;
	
	

	public String getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getBillnumer() {
		return billnumer;
	}

	public void setBillnumer(String billnumer) {
		this.billnumer = billnumer;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSellInfomation() {
		return sellInfomation;
	}

	public void setSellInfomation(String sellInfomation) {
		this.sellInfomation = sellInfomation;
	}


}
