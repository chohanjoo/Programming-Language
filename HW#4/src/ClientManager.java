import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class ClientManager {

	HashMap<String, Client> clientList;

	public ClientManager() {
		clientList = new HashMap<String, Client>();
		readFile();
	}

	public Client searchClient(String number) {

		if (clientList.containsKey(number))
			return clientList.get(number);

		return null;
	}

	public void deleteClient(String number) {

		if (searchClient(number) != null)
			clientList.remove(number);

		inputFile();
	}

	public void addClient(Client client) {
		clientList.put(client.number, client);
		inputFile();

	}

	public void inputFile() {
		try {
			File file = new File("C:\\Users\\한주\\eclipse-workspace\\pl#4\\custom.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

			if (file.isFile() && file.canWrite()) {

				java.util.Iterator iterator = clientList.keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Client person = clientList.get(key);
					bufferedWriter.write(person.number + "\t" + person.joinDate + "\t" + person.name + "\t"
							+ person.phoneNumber + "\t" + person.birthday + "\t" + person.coupon);
					bufferedWriter.newLine();

					for (int i = 0; i < person.order_date.size(); ++i) {
						bufferedWriter.write("\t" + person.order_date.get(i) + "\t" + person.order_menu.get(i));
						bufferedWriter.newLine();
					}

				}
				bufferedWriter.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void readFile() {
		try {
			File file = new File("C:\\Users\\한주\\eclipse-workspace\\pl#4\\custom.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);

			String line = "";
			Client client = null;
			while ((line = bufReader.readLine()) != null) {
				String word[] = line.split("\t");
				if (word[0].compareTo("") != 0) {
					client = new Client(word[0], word[1], word[2], word[3], word[4]);
					client.coupon = Integer.parseInt(word[5]);
					clientList.put(word[0], client);
				} else {
					String date = word[1];
					String menu = word[2];
					client.addOrder(date, menu);
					clientList.put(client.number, client);
				}
			}

			bufReader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
