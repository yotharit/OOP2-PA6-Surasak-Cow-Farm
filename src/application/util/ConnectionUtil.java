package application.util;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import farmData.Account;
import farmData.Bill;
import farmData.Cow;
import farmData.Setting;

public class ConnectionUtil {
	private static ConnectionUtil instance = null;
	private ConnectionSource source;
	private Dao<Cow, String> cowDao;
	private Dao<Account, String> accountDao;
	private Dao<Bill, String> billDao;
	private Dao<Setting, String> settingDao;
	
	private ConnectionUtil(){
		try {
			source = new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert","root","1234");
			cowDao = DaoManager.createDao(source, Cow.class);
			accountDao = DaoManager.createDao(source, Account.class);
			billDao = DaoManager.createDao(source, Bill.class);
			settingDao = DaoManager.createDao(source, Setting.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ConnectionUtil getInstance(){
		if(instance == null){
			instance = new ConnectionUtil();
		}
		return instance;
	}

	public ConnectionSource getSource() {
		return source;
	}

	public Dao<Cow, String> getCowDao() {
		return cowDao;
	}

	public Dao<Account, String> getAccountDao() {
		return accountDao;
	}

	public Dao<Bill, String> getBillDao() {
		return billDao;
	}

	public Dao<Setting, String> getSettingDao() {
		return settingDao;
	}
	
	public void createCowTable(){
		try {
			TableUtils.createTableIfNotExists(source, Cow.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createAccountTable(){
		try {
			TableUtils.createTableIfNotExists(source, Account.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createBillTable(){
		try {
			TableUtils.createTableIfNotExists(source, Bill.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createSettingTable(){
		try {
			TableUtils.createTableIfNotExists(source, Setting.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeConnection(){
		try {
			source.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
