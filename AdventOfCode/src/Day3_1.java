import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day3_1 {
    public static void main(String[] args) {
        List<Character> dublicatedChars = new ArrayList<>();
        try {
            File numbers = new File("src/rucksacks.txt");
            Scanner myReader = new Scanner(numbers);
            while (myReader.hasNextLine()) {
                String item = myReader.nextLine();
                final String first = item.substring(0, (item.length()/2));
                final String second = item.substring((item.length()/2));

                boolean foundDuplicate = false;
                for (final char letter : first.toCharArray()) {
                    if (foundDuplicate) {
                        break;
                    }
                    for (final char secondLetter : second.toCharArray()) {
                        if (secondLetter == letter) {
                            dublicatedChars.add(letter);
                            foundDuplicate = true;
                            break;
                        }
                    }
                }

            }
            final int charsTotal = dublicatedChars.stream().map(Day3_1::countSignValue).reduce(0, Integer::sum);
            System.out.println("Min total är : " + charsTotal);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int countSignValue(char c) {
        if (Character.isUpperCase(c)) {
            final int letterAsciiValue = Character.toLowerCase(c);
            return letterAsciiValue-70;
        }
         final int letterAsciiValue = c;
        return letterAsciiValue-96;
    }
}