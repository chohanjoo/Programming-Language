import java.util.HashMap;

public class MenuPan {

	HashMap<String, Integer> menu = new HashMap<>();
	
	public MenuPan() {
		setMenu();
	}
	
	private void setMenu() {
		menu.put("±è¹ä", 2500);
		menu.put("¶±ººÀÌ", 3000);
		menu.put("¼ø´ë", 3000);
		menu.put("¿Àµ­", 700);
		menu.put("Æ¢±è", 5000);
		
	}
	
	
}
