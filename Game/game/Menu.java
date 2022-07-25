import org.jsfml.graphics.*;
import org.jsfml.system.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing menu in a game with immutable menus
 */
public class Menu {
	private final MenuElement[] titleScreen = new MenuElement[5];
	private final MenuElement[] inGameMenu = new MenuElement[4];
	private final MenuElement[] levelSelectionScreen = new MenuElement[6];
	private final MenuElement[] aboutMenu = new MenuElement[2];
	private final MenuElement[] endOfLevel = new MenuElement[3];

	public Menu() {

		titleScreen[0] = new MenuElement("assets/Menu/Home_Background.png", 0);
		titleScreen[1] = new MenuElement("assets/Menu/Play.png", 1);
		titleScreen[2] = new MenuElement("assets/Menu/Levels.png", -2);
		titleScreen[3] = new MenuElement("assets/Menu/About.png", -3);
		titleScreen[4] = new MenuElement("assets/Menu/Quit.png", -100);

		inGameMenu[0] = new MenuElement("assets/Menu/Level_Background.png", 0);
		inGameMenu[1] = new MenuElement("assets/Menu/Resume.png", -200);
		inGameMenu[2] = new MenuElement("assets/Menu/Toggle Music Button.png", -201);
		inGameMenu[3] = new MenuElement("assets/Menu/Quit.png", 0);

		levelSelectionScreen[0] = new MenuElement("assets/Menu/Level_Background.png", 0);
		levelSelectionScreen[1] = new MenuElement("assets/Menu/Tutorial_Button.png", 1);
		levelSelectionScreen[2] = new MenuElement("assets/Menu/WasteLand_Button.png", 2);
		levelSelectionScreen[3] = new MenuElement("assets/Menu/HellsPoint_Button.png", 3);
		levelSelectionScreen[4] = new MenuElement("assets/Menu/IceCaves_Button.png", 4);
		levelSelectionScreen[5] = new MenuElement("assets/Menu/Left_Arrow_Sign.png", 0);

		aboutMenu[0] = new MenuElement("assets/Menu/Level_Background.png", 0);
		aboutMenu[1] = new MenuElement("assets/Menu/Left_Arrow_Sign.png", 0);

		endOfLevel[0] = null;
		endOfLevel[1] = new MenuElement("assets/Menu/Quit.png", 0);
		endOfLevel[2] = new MenuElement("assets/Menu/Next Level Button.png", -300);
	}

	/**
	 * Method returns menu specified by id
	 * 
	 * @param id id of a menu
	 * @return menu represented by specified id
	 */
	public ArrayList<Drawable> getMenu(int id, Vector2f pos, Vector2f size) {
		ArrayList<Drawable> list = new ArrayList<>();
		switch (id) {
			case 0:
				titleScreen[0].setPosition(new Vector2f(pos.x - size.x / 2, pos.y - size.y / 2));
				list.add(titleScreen[0]);
				for (int i = 1; i < titleScreen.length; i++) {
					titleScreen[i].setPosition(new Vector2f(pos.x - titleScreen[i].getTexture().getSize().x / 2,
							pos.y - (size.y / 2) + 225 + i * 125));
					list.add(titleScreen[i]);
				}
				break;
			case -1:
				inGameMenu[0].setPosition(new Vector2f(pos.x - size.x / 2, pos.y - size.y / 2));
				list.add(inGameMenu[0]);
				for (int i = 1; i < inGameMenu.length; i++) {
					inGameMenu[i].setPosition(new Vector2f(pos.x - inGameMenu[i].getTexture().getSize().x / 2,
							pos.y - (size.y / 2) + 225 + i * 125));
					list.add(inGameMenu[i]);
				}
				break;
			case -2:
				levelSelectionScreen[0].setPosition(new Vector2f(pos.x - size.x / 2, pos.y - size.y / 2));
				list.add(levelSelectionScreen[0]);
				levelSelectionScreen[levelSelectionScreen.length - 1]
						.setPosition(new Vector2f(pos.x - size.x / 2 + 100, pos.y - size.y / 2 + 605));
				list.add(levelSelectionScreen[levelSelectionScreen.length - 1]);
				for (int i = 1; i < levelSelectionScreen.length - 1; i++) {
					levelSelectionScreen[i]
							.setPosition(new Vector2f(pos.x - levelSelectionScreen[i].getTexture().getSize().x / 2,
									pos.y - (size.y / 2) + 225 + i * 125));
					list.add(levelSelectionScreen[i]);
				}
				break;
			case -3:
				aboutMenu[0].setPosition(new Vector2f(pos.x - size.x / 2, pos.y - size.y / 2));
				list.add(aboutMenu[0]);
				aboutMenu[1].setPosition(new Vector2f(pos.x - (size.x / 2) + 100, pos.y - (size.y / 2) + 605));
				list.add(aboutMenu[1]);
				list.add(getAboutInfo(new Vector2f(pos.x - size.x / 2, pos.y - size.y / 2)));
				break;
			case -4:
				list.add(null);
				endOfLevel[1].setPosition(new Vector2f(pos.x - 700, pos.y + 300));
				list.add(endOfLevel[1]);
				endOfLevel[2].setPosition(new Vector2f(pos.x + 250, pos.y + 300));
				list.add(endOfLevel[2]);
				break;
			case -100:
				System.exit(0);
				break;
		}
		return list;
	}

	private Drawable getAboutInfo(Vector2f pos) {
		Text t = new Text();
		try {
			Scanner scanner = new Scanner(new File("additionalFiles/aboutInfo.txt"));
			String about = "";
			while (scanner.hasNextLine()) {
				about += scanner.nextLine() + "\n";
			}
			scanner.close();
			t.setString(about);
			t.setFont(Screen.font);
			t.setPosition(new Vector2f(pos.x + 280, pos.y + 100));
			t.setCharacterSize(30);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
