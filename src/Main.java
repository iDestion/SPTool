import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {

    public static void main(String[] args) {

        Boolean json = true;

        File file = new File("test.txt");

        ArrayList<String> in = new ArrayList<>(List.of(args));

        try {
            ArrayList<SpeakerTurn> turns = Utils.splitTurns(file);
            HashMap<SpeakerTurn, Double> scores = Searcher.multiLongTermFrequency(turns, in, false);
            File output;
            if(json){
                output = new File("out.json");
            } else {
                output = new File("out.txt");
            }
            PrintWriter out = new PrintWriter(output);
            JSONArray array = new JSONArray();
            for (SpeakerTurn turn : scores.keySet()) {
                if(json){
                    JSONObject obj = new JSONObject();
                    obj.put("turnNumber", turn.getTurnNumber());
                    obj.put("speaker", turn.getSpeaker());
                    obj.put("text" , turn.getText());
                    obj.put("wordcount", turn.getWordCount());
                    obj.put("score", scores.get(turn));
                    array.add(obj);
                } else {
                    out.write(turn.getText() + " : " + scores.get(turn) + "\n");
                }
            }
            if(json){out.write(array.toJSONString());}

            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
