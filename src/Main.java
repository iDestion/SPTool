import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        File file = new File("test.txt");

        try {
            ArrayList<SpeakerTurn> turns = Utils.splitTurns(file);
            HashMap<SpeakerTurn, Integer> scores = Searcher.dumbSearch(turns, new ArrayList<String>(List.of("dan", "overzicht")));
            for(SpeakerTurn turn : scores.keySet()){
                System.out.println("Speakerturn text: " + turn.getText() + "\n Score: " + scores.get(turn));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
