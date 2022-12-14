import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {

    public static void main(String[] args) {

        //final String JSON = "json";
        final String JSON = "txt";
        //final String JSON = "console";

        //Configure the threshold used in identifying advice. Scores over threshold are included in output. Make threshold 0 to include all scored turns.
        final int THRESHOLD = 20;

        File file = new File("in.txt");

        ArrayList<String> in = new ArrayList<>();

        try {

            //Scanner to read the terms file.
            Scanner scan = new Scanner(new File("terms.txt"));
            while (scan.hasNext()){
                in.add(scan.next());
            }
            scan.close();

            ArrayList<SpeakerTurn> turns = Utils.splitTurns(file);
            HashMap<SpeakerTurn, Double> scores = Searcher.multiTermFrequency(turns, in, false);

            File output;
            if(JSON.equals("json")){
                output = new File("out.json");
            } else if (JSON.equals("txt")) {
                output = new File("out.txt");
            } else {
                output = null;
            }
            PrintWriter out;
            if(JSON.equals("json") || JSON.equals("txt")) {
                out = new PrintWriter(output);
            } else {
                out = new PrintWriter(System.out);
            }
            JSONArray array = new JSONArray();
            for (SpeakerTurn turn : scores.keySet()) {
                //Only add turns to file or console when score is higher than 0
                if(scores.get(turn) > THRESHOLD) {
                    if (JSON.equals("json")) {
                        JSONObject obj = new JSONObject();
                        obj.put("turnNumber", turn.getTurnNumber());
                        obj.put("speaker", turn.getSpeaker());
                        obj.put("text", turn.getText());
                        obj.put("wordcount", turn.getWordCount());
                        obj.put("score", scores.get(turn));
                        array.add(obj);
                    } else {
                        out.write("Turn number: " + turn.getTurnNumber() + ", speaker: " + turn.getSpeaker() +  ", score: " + scores.get(turn) + "\n");
                    }
                }
            }
            if(JSON.equals("json")){out.write(array.toJSONString());}
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
