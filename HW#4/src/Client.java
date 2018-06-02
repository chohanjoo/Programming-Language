import java.util.ArrayList;
import java.util.HashMap;

import except.OutOfNameBoundException;

public class Client {

	public String number;
	public String joinDate;
	public String name;
	public String phoneNumber;
	public String birthday;

	public int countOfOrder;
	ArrayList<String> order_date;
	ArrayList<String> order_menu;

	public int coupon;

	public Client(String number, String joinDate, String name, String phoneNumber, String birthday) {

		this.number = number;
		this.joinDate = joinDate;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;

		countOfOrder = 0;
		order_date = new ArrayList<String>();
		order_menu = new ArrayList<String>();

		coupon = 0;
	}

	public void modifyJoinData(String joinDate) {
		this.joinDate = joinDate;
	}

	public void modifyPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void modifyBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void addOrder(String date, String menu) {
		order_date.add(date);
		order_menu.add(menu);
		countOfOrder++;
	}

	public int getCountOfOrder() {
		return countOfOrder;
	}

	public void setCountOfOrder(int count) {
		countOfOrder = count;
	}

}
