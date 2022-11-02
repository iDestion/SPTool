import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TurnCounter {

    public static void main(String[] args) {
        File file = new File("in.txt");
        File output = new File("numbered.txt");

        Scanner scan = null;
        PrintWriter writer = null;
        try {
            scan = new Scanner(file);
            writer = new PrintWriter(output);
            int current = 0;

            while(scan.hasNext()){
                String line = scan.nextLine();
                writer.write(current + " : "  + line + "\n");
                current++;
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
