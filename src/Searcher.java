import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Searcher {

    private final static ArrayList<String> canditerms = new ArrayList<>();

    //Returns a map with speakerturns and their respective scores according to the amount of occurrences of the terms.
    public static HashMap<SpeakerTurn, Double> multiTermFrequency(ArrayList<SpeakerTurn> input, ArrayList<String> terms,
                                                                  boolean compensateLength){
        HashMap<SpeakerTurn, Double> result = new HashMap<>();

        //For each speaker turn, look at the simple words that are in the turn, count the frequency of the searchterms,
        // this is the score for the specific turn. Possibility for accounting for longer terms.
        for(SpeakerTurn turn : input){
            double score = 0;
            for(String term : terms){
                score += Collections.frequency(turn.getWords(), term);
            }
            if(compensateLength){
                result.put(turn, score/turn.getWordCount());
            } else {
                result.put(turn, score);
            }
        }

        return result;
    }

    public static HashMap<SpeakerTurn, Double> cValueNonNested(ArrayList<SpeakerTurn> input, ArrayList<String> terms){
        HashMap<SpeakerTurn, Double> result = new HashMap<>();
        //Calculates c-value for non-nested terms and adds it to a map with the corresponding speakerturn.
        for(SpeakerTurn turn : input) {
            for (String term : terms) {
                double cvalue = (Math.log(Utils.wordCount(term)) / Math.log(2)) * Utils.frequency(turn.getText(), term);
                result.put(turn, cvalue);
            }
        }
        return result;
    }

    public static HashMap<SpeakerTurn, Double> cValueNested(ArrayList<SpeakerTurn> input, ArrayList<String> terms) {
        HashMap<SpeakerTurn, Double> result = new HashMap<>();

        //Calculates c-value for nested terms and adds it to a map with the corresponding speakerturn.
        for(SpeakerTurn turn : input) {
            for (String term : terms) {
                //Canditerms contain all candidate terms containing the term.
                double cvalue = (Math.log(Utils.wordCount(term)) / Math.log(2)) * (Utils.frequency(turn.getText(), term) -
                        1/(double)canditerms.size() * Utils.frequency(turn.getText(), canditerms));
                result.put(turn, cvalue);
            }
        }
        return result;
    }
}
