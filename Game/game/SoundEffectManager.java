import java.io.*;

import javax.sound.sampled.*;

/**
 * This class is used to create sound effects. since this uses multithreading,
 * we found it kind of pointless to have instances of it all as classes that are
 * static since they just play a specific sound effect. Despite the specific
 * method documentation not mentioning it, all methods included in this class
 * use multithreading. this is so that multiple sound effects can be played at
 * the same time.
 */
public class SoundEffectManager {

    /**
     * used internally to avoid repeating code. can be used to play sounds that do
     * not have any functions assigned.
     *
     * @param path : path to the .wav (other formats have not been tested) file to
     *             be played.
     */
    public static void playSound(String path) {
        // make a new runnable with code that plays the sound effect.
        // will change this later to something that is more understandable.
        if (!Screen.musicOff()) {
            Runnable Runner = new Runnable() {
                @Override
                public void run() {
                    try {
                        File file = new File(path);
                        AudioInputStream stream;
                        AudioFormat format;
                        DataLine.Info info;
                        Clip clip;

                        stream = AudioSystem.getAudioInputStream(file);
                        format = stream.getFormat();
                        info = new DataLine.Info(Clip.class, format);
                        clip = (Clip) AudioSystem.getLine(info);
                        clip.open(stream);
                        clip.start();
                        clip.addLineListener(e -> {
                            if (e.getType() == LineEvent.Type.STOP) {
                                clip.stop();
                                clip.flush();
                                clip.close();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread thread = new Thread(Runner);
            thread.start();
        }
    }
}
