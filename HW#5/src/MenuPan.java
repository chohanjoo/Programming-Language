import java.util.HashMap;

public class MenuPan {

	HashMap<String, Integer> menu = new HashMap<>();
	
	public MenuPan() {
		setMenu();
	}
	
	private void setMenu() {
		menu.put("���", 2500);
		menu.put("������", 3000);
		menu.put("����", 3000);
		menu.put("����", 700);
		menu.put("Ƣ��", 5000);
		
	}
	
	
}
