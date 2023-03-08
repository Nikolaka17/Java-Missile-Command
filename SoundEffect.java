import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class SoundEffect{
    Clip clip;

    public SoundEffect(String path){
        setFile(path);
    }

    public void setFile(String path){
        try{
            File file = new File(path);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }catch(Exception e){

        }
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
}
