import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2_1 {
    public static void main(String[] args) {
        List<Character> myChars = new ArrayList<>();
        int myTotalPoints = 0;
        try {
            File numbers = new File("src/gameStrategy.txt");
            Scanner myReader = new Scanner(numbers);
            while (myReader.hasNextLine()) {
                String game = myReader.nextLine();
                final char opp = game.charAt(0);
                final char my = game.charAt(2);
                myChars.add(my);
                if(my == 'X' && opp == 'C') {
                    myTotalPoints += 6;
                }
                else if(my == 'X' && opp == 'A') {
                    myTotalPoints += 3;
                } else if(my == 'Y' && opp == 'A') {
                    myTotalPoints += 6;
                }
                else if(my == 'Y' && opp == 'B') {
                    myTotalPoints += 3;
                } else if(my == 'Z' && opp == 'B') {
                    myTotalPoints += 6;
                }
                else if(my == 'Z' && opp == 'C') {
                    myTotalPoints += 3;
                }
            }
            final int charsTotal = myChars.stream().map(Day2_1::countSignValue).reduce(0, Integer::sum);
            System.out.println("Min total är : " + (myTotalPoints + charsTotal));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int countSignValue(Character c) {
        if(c == 'X') {
            return 1;
        } else if (c == 'Y') {
            return 2;
        }
        return 3;
    }
}