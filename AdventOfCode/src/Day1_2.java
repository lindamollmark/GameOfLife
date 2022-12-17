import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Day1_2 {
    public static void main(String[] args) {
        int elfTotal = 0;
        List<Integer> elfTotals = new ArrayList<>();
        try {
            File numbers = new File("src/numbers.txt");
            Scanner myReader = new Scanner(numbers);
            while (myReader.hasNextLine()) {
                String calories = myReader.nextLine();
                if (calories == "") {
                    elfTotals.add(elfTotal);
                    elfTotal = 0;
                } else {
                    elfTotal += Integer.parseInt(calories);
                }
            }
            elfTotals.add(elfTotal);
            elfTotal = 0;
            elfTotals = elfTotals.stream().sorted(Comparator.reverseOrder()).toList();
            elfTotal += elfTotals.get(0);
            elfTotal += elfTotals.get(1);
            elfTotal += elfTotals.get(2);
            System.out.println("Tre högsta antal kalorier är: " + elfTotal);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}