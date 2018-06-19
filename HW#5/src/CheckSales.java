import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class CheckSales {

	String start_date = "";
	String end_date = "";
	MenuPan menus;
	HashMap<String, Integer> sales_volume;
	int coupons = 0;

	public CheckSales(String start_date, String end_date) {
		this.start_date = start_date;
		this.end_date = end_date;
		menus = new MenuPan();
		sales_volume = new HashMap<>();
		initSales();
		readFile();
	}

	private void initSales() {
		Set<String> keys = menus.menu.keySet();

		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			sales_volume.put(key, 0);
		}

	}

	private void readFile() {
		try {
			File file = new File("C:\\Users\\«—¡÷\\eclipse-workspace\\pl#4\\custom.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);

			String line = "";
			Client client = null;
			while ((line = bufReader.readLine()) != null) {
				String word[] = line.split("\t");
				if (word[0].compareTo("") == 0) {
					String date = word[1];
					String menu = word[2];
					if (start_date.compareTo(date) <= 0 && end_date.compareTo(date) >= 0)
						sales_volume.put(menu, sales_volume.get(menu) + 1);

				}
				else
					coupons += Integer.parseInt(word[5]);
			}

			bufReader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public HashMap<String, Integer> getSalesVolume() {
		return sales_volume;
	}
	
	

}
