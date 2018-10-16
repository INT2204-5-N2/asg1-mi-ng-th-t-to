package Models;

public class Word {
    private String id;
    private String word_target;
    private String word_explain;
    private String technical;
    private String synonym;
    private String eng2Eng;
    private boolean isStared;
    private Pronounce pronounce;
    private static final String NO_CONTENT = "<b> Dữ liệu đang được cập nhật </b>";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        if(technical == null || technical.equals("")){
            this.technical = NO_CONTENT;
        }
        else {
            this.technical = technical;
        }
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        if(synonym == null || synonym.equals("")){
            this.synonym = NO_CONTENT;
        }
        else {
            this.synonym = synonym;
        }
    }

    public String getEng2Eng() {
        return eng2Eng;
    }

    public void setEng2Eng(String eng2Eng) {
        if(eng2Eng == null || eng2Eng.equals("")){
            this.eng2Eng = NO_CONTENT;
        }
        else {
            this.eng2Eng = eng2Eng;
        }
    }

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
        if(word_explain == null || word_explain.equals("")){
            this.word_explain = NO_CONTENT;
        }
        else {
            this.word_explain = word_explain;
        }
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

    @Override
    public String toString() {
        return word_target;
    }
}
