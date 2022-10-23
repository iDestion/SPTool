import java.util.ArrayList;

public class SpeakerTurn {

    //Turn number order turns. The first turn in the file will get number 0.
    private int turnNumber;
    private String text;
    private String speaker;
    private int wordCount;

    //Sentences contain the specific sentences in the speakerturn. Tokens are words with special characters attached sometimes. Words contain lowercase tokens, without any special characters attached.
    private ArrayList<String> sentences;
    private ArrayList<String> tokens;
    private ArrayList<String> words;

    public SpeakerTurn(int turnNumber, String text, String speaker){
        this.turnNumber = turnNumber;
        this.text = text;
        this.speaker = speaker;
        this.sentences = Utils.sentenize(this.text);
        this.tokens = Utils.tokenize(this.text);
        this.words = Utils.wordize(this.tokens);

        //get word number from text
        String trim = text.trim();
        if(trim.isEmpty()){
            this.wordCount = 0;
        } else {
            this.wordCount = trim.split("\\s+").length;
        }
    }

    public int getTurnNumber(){
        return this.turnNumber;
    }
    public String getText(){
        return this.text;
    }
    public String getSpeaker(){
        return this.speaker;
    }
    public int getWordCount(){
        return this.wordCount;
    }
    public ArrayList<String> getSentences(){
        return this.sentences;
    }
    public ArrayList<String> getTokens(){
        return this.tokens;
    }
    public ArrayList<String> getWords(){
        return this.words;
    }

    public String toString(){
        return this.turnNumber + " : \nText: " + this.text + "\n" + "Speaker: " + this.speaker + "\n" + "WordCount: " + this.wordCount;
    }

}
