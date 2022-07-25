import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Class responsible for playing music in the game
 */
public class Music {
    private Clip clip;
    private String currentFile;

    /**
     * Plays music from specified file
     * 
     * @param musicLocation location of the file
     */
    public void playMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            currentFile = musicLocation;
            if (clip != null) {
                clip.close();
            }
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	/**
	 * Accessor for the current file that is playing
	 * @return the path to the file that is playing
	 */
    public String currentMusic() {
        return currentFile;
    }

	/**
	 * This method stops the currently playing music
	 */
    public void stopMusic() {
        if (clip != null) {
            clip.close();
            currentFile = null;
        }
    }
}
