package Models;

import javax.sound.sampled.AudioInputStream;

public class Word {
    private String word_target;
    private String word_explain;
    private boolean isStared;
    private Pronounce pronounce;

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public boolean isStared() {
        return isStared;
    }

    public void setStared(boolean stared) {
        isStared = stared;
    }

    public Pronounce getPronounce() {
        return pronounce;
    }

    public void setPronounce(Pronounce pronounce) {
        this.pronounce = pronounce;
    }
}
