import java.lang.reflect.Array;
import java.util.*;

public class Searcher {

    private final static ArrayList<String> canditerms = new ArrayList<>();

    //Returns a map with speakerturns and their respective scores according to the amount of occurrences of the terms. Splits input terms into single words to score turns.
    public static HashMap<SpeakerTurn, Double> multiTermFrequency(ArrayList<SpeakerTurn> input, ArrayList<String> terms,
                                                                  boolean compensateLength){

        //Remove duplicate terms
        Set<String> termset = new HashSet<>(terms);
        terms.clear();
        terms.addAll(termset);

        HashMap<SpeakerTurn, Double> result = new HashMap<>();

        //Split terms into individual terms
        ArrayList<String> termsSplit = new ArrayList<>();
        for (String term : terms){
            ArrayList<String> split = Utils.tokenize(term);
            termsSplit.addAll(split);
        }

        termsSplit = Utils.wordize(termsSplit);

        //Remove duplicate word from split
        Set<String> wordset = new HashSet<>(termsSplit);
        termsSplit.clear();
        termsSplit.addAll(wordset);

        //For each speaker turn, look at the simple words that are in the turn, count the frequency of the searchterms,
        // this is the score for the specific turn. Possibility for accounting for longer terms.
        for(SpeakerTurn turn : input){
            double score = 0;
            for(String term : termsSplit){
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

    //Returns a map with speakerturns and their respective scores according to the amount of occurrences of the terms. Scores terms as individual words as well as longer strings (in the case they are longer than 1 word). TODO check if score needs to be adjusted for longer terms.
    public static HashMap<SpeakerTurn, Double> multiLongTermFrequency(ArrayList<SpeakerTurn> input, ArrayList<String> terms,
                                                                  boolean compensateLength){
        HashMap<SpeakerTurn, Double> result = new HashMap<>();

        //Split terms into individual terms
        ArrayList<String> termsSplit = new ArrayList<>();
        for (String term : terms){
            ArrayList<String> split = Utils.tokenize(term);
            for (String spl : split){
                termsSplit.add(spl);
            }
        }

        termsSplit = Utils.wordize(termsSplit);

        //For each speaker turn, look at the simple words that are in the turn, count the frequency of the searchterms,
        // this is the score for the specific turn. Possibility for accounting for longer terms.
        for(SpeakerTurn turn : input){
            double score = 0;
            for(String term : termsSplit){
                score += Collections.frequency(turn.getWords(), term);
            }
            if(compensateLength){
                result.put(turn, score/turn.getWordCount());
            } else {
                result.put(turn, score);
            }
        }

        //Check for strings longer than 1 word. Score according to the length of the String.
        for(SpeakerTurn turn : input){
            double score = result.get(turn);
            for(String term : terms){
                if(term.split(" ").length > 1){
                    if(turn.getText().contains(term)) {
                        score += term.split(" ").length;
                    }
                }
            }
            result.put(turn, score);
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
