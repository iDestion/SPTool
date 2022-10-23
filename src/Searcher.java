import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Searcher {

    //Returns a map with speakerturns and their respective scores according to the amount of occurrences of the terms.
    public static HashMap<SpeakerTurn, Double> multiTermFrequency(ArrayList<SpeakerTurn> input, ArrayList<String> terms, boolean compensateLength){
        HashMap<SpeakerTurn, Double> result = new HashMap<>();

        //For each speaker turn, look at the simple words that are in the turn, count the frequency of the searchterms, this is the score for the specific turn. Possibility for accounting for longer terms.
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

}
