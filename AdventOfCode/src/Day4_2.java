import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day4_2 {
    public static void main(String[] args) {
        List<Character> dublicatedChars = new ArrayList<>();
        int overlap = 0;
        try {
            File numbers = new File("src/sections.txt");
            Scanner myReader = new Scanner(numbers);
            while (myReader.hasNextLine()) {
                String item = myReader.nextLine();
                final String[] splitedItems = item.split(",");
                final List<Integer> first = Arrays.stream(splitedItems[0].split("-")).filter(n -> !n.equals("-"))
                        .map(Integer::parseInt).toList();
                final List<Integer> second = Arrays.stream(splitedItems[1].split("-")).filter(n -> !n.equals("-"))
                        .map(Integer::parseInt).toList();

                if ((first.get(0) <= second.get(1)) && (first.get(1) >= second.get(0))) {
                    overlap = overlap+1;
                }

            }
            System.out.println("Min total är : " + overlap);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}