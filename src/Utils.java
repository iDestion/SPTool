import java.io.File;
import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.*;

public class Utils {

    public static ArrayList<SpeakerTurn> splitTurns(File file) throws FileNotFoundException {
        ArrayList<SpeakerTurn> result = new ArrayList<>();
        Scanner reader = new Scanner(file);
        int currentTurn = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String text = line.split(" ", 2)[1];
            String speaker = line.split(" ")[0].replaceAll(":", "");
            SpeakerTurn turn = new SpeakerTurn(currentTurn, text, speaker);
            result.add(turn);
            currentTurn++;
        }
        return result;
    }

    //Using Breakiterator, the sentenize method turn the text of SpeakerTurns into sentences.
    public static ArrayList<String> sentenize(String input) {
        ArrayList<String> result = new ArrayList<>();
        Locale locale = new Locale("nl", "NL");
        BreakIterator iterator = BreakIterator.getSentenceInstance(locale);
        iterator.setText(input);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            result.add(input.substring(start, end));
        }
        return result;
    }

    public static ArrayList<String> tokenize(String input) {
        ArrayList<String> result = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(input);
        while (tokenizer.hasMoreTokens()) {
            result.add(tokenizer.nextToken());
        }

        return result;
    }


    //Removes special characters from tokens
    public static ArrayList<String> wordize(ArrayList<String> input) {
        ArrayList<String> result = new ArrayList<>();
        for (String token : input) {
            result.add(token.replaceAll("[^a-zA-Z0-9_-]", "").toLowerCase());
        }
        return result;
    }

    public static double frequency(String text, String term) {
        return 0;
    }

    public static double wordCount(String input) {
        return 0;
    }

    public static double frequency(String text, ArrayList<String> terms) {
        return 0;
    }

}
