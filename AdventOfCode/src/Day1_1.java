import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1_1 {
    public static void main(String[] args) {
        int total = 0;
        int highest = 0;
        try {
            File numbers = new File("src/numbers.txt");
            Scanner myReader = new Scanner(numbers);
            while (myReader.hasNextLine()) {
                String calories = myReader.nextLine();
                if (calories == "") {
                    if (total > highest) {
                        highest = total;
                        total = 0;
                    } else {
                        total = 0;
                    }
                } else {
                    total += Integer.parseInt(calories);
                }
                System.out.println(calories);
            }
            System.out.println("Högst antal kalorier är: " + highest);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}