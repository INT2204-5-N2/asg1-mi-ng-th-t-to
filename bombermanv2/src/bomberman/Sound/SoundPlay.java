package bomberman.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlay {
    public static final String LEVEL_1_SOUND=null;
    public static final String MENU_SOUND=null;
    public static final String START_SOUND="C:\\Github\\bombermanv2\\res\\BombermanSound\\03 Start.wav" ;
    public static void playSound(String soundPath)  {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);
            clip.close();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        playSound(START_SOUND);
    }
}


