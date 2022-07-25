import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.*;

import java.nio.file.Paths;
import java.util.*;

/**
 * Main logic for game
 */
public class Screen {

	// window stuff
	private RenderWindow window = new RenderWindow();
	private ArrayList<Drawable> elements = new ArrayList<Drawable>(); // every element to be drawn
	private View view = new View(new Vector2f(0, 0), new Vector2f(600, 340));
	private World world = new World(4f);
	private MainCharacter eugene = null;
	public static final Font font = new Font();
	public static final TextureFactory TEXTURE_FACTORY = new TextureFactory();
	public static final EntityFactory ENTITY_FACTORY = new EntityFactory();
	public static final BlockFactory BLOCK_FACTORY = new BlockFactory();
	public static final BackgroundFactory BACKGROUND_FACTORY = new BackgroundFactory();
	public static final TriggerDB TRIGGER_DB = new TriggerDB();
	private static boolean musicOff = false;
	private LoadingScreen loadingS;

	// level stuff
	private Level level = new Level(0);
	private PlayerGUI playerGUI;
	private boolean changeLevel = false;

	private Music music = new Music();
	private Text endLVLWarning = new Text();
	private Text doorWarning = new Text();
	private Text skipMessage = new Text();

	// Controls and Menu
	private boolean menuShown = false;
	private Menu menu = new Menu();
	private Controls controls = new MenuControls();
	private Level saveLevel; // for resuming to the level

	static {
		try {
			font.loadFromFile(Paths.get("assets/runescape_uf.ttf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor
	 * This initialises the window and the mainloop
	 */
	public Screen() {
		window.create(new VideoMode(1600, 900, 8), "Eugene's Crusade");
		window.setFramerateLimit(50);
		try {
			window.setActive();
		} catch (Exception e) {
			e.printStackTrace();
		}

		endLVLWarning.setString("You must kill the boss to progress to next level");
		endLVLWarning.setColor(Color.WHITE);
		endLVLWarning.setCharacterSize(15);
		endLVLWarning.setFont(font);

		doorWarning.setString("You must kill the boss to obtain a key");
		doorWarning.setColor(Color.WHITE);
		doorWarning.setCharacterSize(15);
		doorWarning.setFont(font);

		skipMessage.setString("Press [ENTER] to skip");
		skipMessage.setFont(font);
		skipMessage.setColor(Color.YELLOW);

		loadingS = new LoadingScreen(window);
	}

	/**
	 * This method is the main loop for the game
	 */
	public void playGame() {
		while (window.isOpen()) {
			// main loop
			controls.checkEvents(this); // check for any input
			// get new Controls if level has changed
			controls = getControlsAndCheckLevel();
			if (controls instanceof LevelControls) {
				window.clear();
				world.update(this, eugene);
				playerGUI.show(window); // show the GUI
				window.display();

				// if entity is colliding with eugene, then do whatever the entity does when it
				// collides with eugene.
				ArrayList<Entity> removals = new ArrayList<Entity>();
				if (!eugene.frostbite()) {
					for (Entity e : world.getEntitiesToCheck()) {
						if (e instanceof MainCharacter) {
							continue;
						}
						if (e.isCollidingWith(eugene)) {
							((Npc) e).bounce(eugene);
						}
						moveEntity(e);
						if (!(e instanceof Lava)) {
							((Npc) e).behave(eugene, this);
							if (e.getHp() <= 0) {
								removals.add(e);
							}
						}
					}
					for (Entity e : removals) {
						e.die(this);
						world.removeEntity(e);
					}
				} else {
					for (Entity e : world.getEntitiesToCheck()) {
						e.animate(); // load new entities
					}
				}

				if (eugene.isDead()) {
					eugene.die(this);
				}
				eugene.blink(world.getBlocksToCheck(), this);
				moveEntity(eugene);
				eugene.tickCooldown(); // ticks down cooldowns of abilities.

				world.resetThingsToCheck();
			} else if (!menuShown) { // menu need to be shown just once
				window.setView(window.getDefaultView());
				elements = menu.getMenu(level.getNumber(),
						new Vector2f(window.getView().getCenter().x, window.getView().getCenter().y),
						window.getView().getSize());
				draw();
				window.display();
				menuShown = true;
				playMusic();
			}
		}

	}

	/**
	 * This method updates the screen to redraw the new positions of all the entities
	 */
	public void updateView() {

		if (view.getCenter().y - (view.getSize().y / 2) < eugene.getCenter().y - 50
				&& view.getCenter().y + (view.getSize().y / 2) > eugene.getCenter().y + 32) {
			view.setCenter(eugene.getCenter().x, view.getCenter().y);
		} else {
			view.setCenter(eugene.getCenter().x, eugene.getCenter().y);
		}
		window.setView(view);
		playerGUI.update(window); // update GUI only when eugene moved

	}

	/**
	 * Accessor for a level
	 * 
	 * @return current level instance
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Accessor for an entity -testing
	 * 
	 * @return current entity
	 */
	public MainCharacter getMainCharacter() {
		return eugene;
	}

	/**
	 * Method draws every element of the list of Drawables
	 */
	public void draw() {
		if (elements == null) {
			System.out.println("Elements are null");
			return;
		}
		for (Drawable d : elements) {
			if (d != null)
				window.draw(d);
		}
	}

	/**
	 * Mutator for level id to be loaded
	 * 
	 * @param l new level id
	 */
	public void setLevel(Level l) {
		level = l;
	}

	public void changeLevel(boolean b) {
		changeLevel = b;
	}

	/**
	 * Accessor for the render target
	 * 
	 * @return render target
	 */
	public RenderWindow getWindow() {
		return window;
	}

	/**
	 * Control of a music based on lvl id. Will change in the future probably
	 */
	public void playMusic() {
		String filepath;
		if (!musicOff) {
			switch (level.getNumber()) {
				case 0:
					filepath = "music/background.wav";
					break;
				case 1:
					filepath = "music/mystic_journey.wav";
					break;
				case 2:
					filepath = "music/Soliloquy.wav";
					break;
				case 3:
					filepath = "music/Heroic_Demise.wav";
					break;
				case 4:
					filepath = "music/radakan-old-crypt.wav";
					break;
				default:
					filepath = "music/background.wav";
			}
			if (!filepath.equals(music.currentMusic())) {
				if (level.getNumber() != -1) {
					music.playMusic(filepath);
				}
			}
		} else {
			music.stopMusic();
		}
	}

	/**
	 * Load new controls based on level id
	 * 
	 * @return
	 */
	private Controls getControlsAndCheckLevel() {
		if (level.getNumber() > 0) {
			if (controls instanceof MenuControls) {// if controls has been initialized
				startLevel();
				return new LevelControls();
			} else {
				if (changeLevel) {
					changeLevel = false;
					startLevel();
				}
				return controls;
			}
		} else {
			if (controls instanceof LevelControls) {// if controls has been initialized
				return new MenuControls();
			} else {
				return controls;
			}
		}
	}

	public World getWorld() {
		return world;
	}

	/**
	 * Accessor for list of elements to be drawn
	 * 
	 * @return list of elements to be drawn
	 */
	public List<Drawable> getElements() {
		return elements;
	}

	/**
	 * Tries to move the entity
	 *
	 * @param e entity
	 */
	private void moveEntity(Entity e) {
		if (e == null) {
			return;
		}
		if (Math.abs(e.getVelocity().x) < 0.5 && Math.abs(e.getVelocity().y) < 0.5) {
			if (e.getState() != State.IDLE) {
				e.setState(State.IDLE);
			}
			e.animate();
			return;
		}
		if (e instanceof MainCharacter) {
			updateView(); // if eugene moved, update
		}
		e.move(world.getBlocksToCheck());
	}

	private void startLevel() {
		changeLevel = false;
		world.reset();
		window.setView(window.getDefaultView());
		LevelParser lp = new LevelParser(level.getNumber(), level, eugene, this);
		Thread t = new Thread(lp);
		t.start();
		while (!lp.isReady()) {
			loadingS.update();
		}
		world.addBlocks(level.getBlocks());
		world.addAssets(level.getElements());
		world.setBackground(level.getBackground());
		world.setTriggers(level.getTriggers());
		eugene = level.getMainCharacter();
		if (playerGUI == null) {
			playerGUI = new PlayerGUI(eugene);
		}
		if (eugene == null) {
			System.out.println("Main character has not been initialized!");
			System.exit(1);
		}
		world.addEntity(level.getNpcs());
		world.addEntity(eugene);
		world.addBlocks(elements);
		if (level.getDoorBlocks() != null) {
			world.setDoorBlocks(level.getDoorBlocks());
		}
		window.clear();
		playMusic();
		// Shows intro
		if (level.getStoryIntro() != null) {
			new StoryScreen(level.getStoryIntro(), this);
		}
		updateView();
	}

	public void spawn(Vector2f pos, int id) {
		if (id < 300) {
			Entity spawned = ENTITY_FACTORY.getEntity(pos, id);
			world.addEntity(spawned);
		} else if (id < 400) {
			Item i = ItemFactory.getItem(pos, id, world.getBlocksToCheck());
			world.addItem(i);
		}
	}

	public void isMenuShown(boolean b) {
		menuShown = b;
	}

	public PlayerGUI getGUI() {
		return playerGUI;
	}

	public void resume() {
		level = saveLevel;
		controls = new LevelControls();
		playMusic();
	}

	public void saveStateOfLevel() {
		saveLevel = level;
	}

	public void showEndLevelWarning(Vector2f pos) {
		endLVLWarning.setPosition(pos.x, pos.y);
		window.draw(endLVLWarning);
	}

	public void showDoorWarning(Vector2f pos) {
		doorWarning.setPosition(pos.x, pos.y);
		window.draw(doorWarning);
	}

	public void showSkipMessage(int fontSize) {
		skipMessage.setCharacterSize(fontSize);
		skipMessage.setPosition(new Vector2f(window.getView().getCenter().x + (window.getView().getSize().x / 2) - 200,
				window.getView().getCenter().y + (window.getView().getSize().y / 2) - 30));
		window.draw(skipMessage);
	}

	public Level getSavedLevel() {
		return saveLevel;
	}

	public LevelControls getLevelControls() {
		if (controls instanceof LevelControls) {
			return (LevelControls) controls;
		} else {
			return null;
		}
	}

	public void toggleMusic(MenuElement menuElement) {
		if (musicOff) {
			musicOff = false;
		} else {
			musicOff = true;
		}
		playMusic();
	}

	public static boolean musicOff() {
		return musicOff;
	}
}
