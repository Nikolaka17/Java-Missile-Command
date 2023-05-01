import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.Serializable;

/**
 * A class that holds a sound byte to play
 */
public class SoundEffect implements Serializable{
    private transient Clip clip;
    private boolean isLooping = false;
    private long serialVersionUID = 1L;

    /**
     * Constructor to make a sound effect without a sound file
     */
    public SoundEffect(){
        clip = null;
    }

    /**
     * Constructor to make a sound effect with a specified file
     * @param path The file to use as the sound
     */
    public SoundEffect(String path){
        setFile(path);
    }

    /**
     * Construtor to create a sound effect that loops
     * @param path The file to use as the sound
     * @param loops A boolean representing if the sound should loop
     */
    public SoundEffect(String path, boolean loops){
        setFile(path);
        isLooping = loops;
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
        if(isLooping){
            clip.loop(-1);
        }else{
            clip.start();
        }
    }

    /**
     * Stops playing the sound effect
     */
    public void stopPlayback(){
        clip.stop();
    }

    /**
     * Tells if the sound effect is currently playing
     * @return A boolean representing if the sound effect is active
     */
    public boolean isPlaying(){
        return clip.isRunning();
    }
}
