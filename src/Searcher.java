import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Searcher {

    //Returns a map with speakerturns and their respective scores according to the amount of occurrences of the terms.
    public static HashMap<SpeakerTurn, Integer> multiTermFrequency(ArrayList<SpeakerTurn> input, ArrayList<String> terms){
        HashMap<SpeakerTurn, Integer> result = new HashMap<>();

        //For each speaker turn, look at the simple words that are in the turn, count the frequency of the searchterms, this is the score for the specific turn. Possibility for accounting for longer terms.
        for(SpeakerTurn turn : input){
            int score = 0;
            for(String term : terms){
                score += Collections.frequency(turn.getWords(), term);
            }
            result.put(turn, score);
        }

        return result;
    }

}
