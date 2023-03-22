import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

/**
 * A class that holds a sound byte to play
 */
public class SoundEffect{
    Clip clip;

    /**
     * Constructor to make a sound effect with a specified file
     * @param path The file to use as the sound
     */
    public SoundEffect(String path){
        setFile(path);
    }

    /**
     * Switches the sound file for the sound effect
     * @param path The new file to use as the sound
     */
    public void setFile(String path){
        try{
            File file = new File(path);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }catch(Exception e){

        }
    }

    /**
     * Plays the sound effect
     */
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
}
