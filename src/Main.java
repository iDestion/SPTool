import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        File file = new File("test.txt");

        ArrayList<String> in = new ArrayList<>(List.of(args));

        try {
            ArrayList<SpeakerTurn> turns = Utils.splitTurns(file);
            HashMap<SpeakerTurn, Double> scores = Searcher.multiLongTermFrequency(turns, in, false);
            File output = new File("out.txt");
            PrintWriter out = new PrintWriter(output);
            for (SpeakerTurn turn : scores.keySet()) {
                out.write(turn.getText() + " : " + scores.get(turn) + "\n");
            }
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
