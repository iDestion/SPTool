import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        File file = new File("test.txt");

        ArrayList<String> in = new ArrayList<String>(List.of(args));

        try {
            ArrayList<SpeakerTurn> turns = Utils.splitTurns(file);
            HashMap<SpeakerTurn, Double> scores = Searcher.multiLongTermFrequency(turns, in, false);
            for(SpeakerTurn turn : scores.keySet()){
                System.out.println("Speakerturn text: " + turn.getText() + "\n Score: " + scores.get(turn));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
