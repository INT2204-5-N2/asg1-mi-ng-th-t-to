package bomberman.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlay {
    public static String LEVEL_1_SOUND;
    public static final String MENU_SOUND=null;
    public static String BOMB_FIRE;
    public static String BOMBER_RUN_SOUND;
    public static String START_SOUND;
    static {
        LEVEL_1_SOUND = SoundPlay.class.getResource("/BombermanSound/04 Level 1.wav").getFile();
        BOMBER_RUN_SOUND=SoundPlay.class.getResource("/BombermanSound/foot.wav").getFile();
        START_SOUND=SoundPlay.class.getResource("/BombermanSound/03 Start.wav").getFile();
        BOMB_FIRE = SoundPlay.class.getResource("/BombermanSound/foot.wav").getFile();
    }
    public static void playSound(String soundPath)  {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            //Thread.sleep(clip.getMicrosecondLength()/1000);
            //clip.close();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        playSound(START_SOUND);
    }
}


