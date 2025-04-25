import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sender {
public ArrayList<Package> vector;

    public Sender(String filePath) {
        vector = new ArrayList<Package>();
        try {
            File fisier = new File(filePath);
            Scanner scanner = new Scanner(fisier);
            String x = scanner.nextLine();
            for (int i = 0; i < x.length(); i++) {
                vector.add(new Package(x.charAt(i)));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
        public void display()
        {
            for (int i = 0; i < vector.size(); i++) {
                vector.get(i).display();
            }
        }

}
