import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        File file = new File("test.txt");

        try {
            ArrayList<SpeakerTurn> turns = Utils.splitTurns(file);
            for(SpeakerTurn turn : turns) {
                System.out.println(turn.getWords());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
