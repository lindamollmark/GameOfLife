import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2_2 {
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
                if(my == 'X') { //förlorar
                    myTotalPoints += 0;
                    if (opp == 'A') { //Sten
                        myChars.add('C');
                    }
                    else if (opp == 'B') { // papper
                        myChars.add('A');
                    }
                   else if (opp == 'C') { // sax
                        myChars.add('B');
                    }
                }
                else if(my == 'Y') { //lika
                    myTotalPoints += 3;
                   myChars.add(opp);
                }
                 else if(my == 'Z') { // vinner
                    myTotalPoints += 6;
                    if (opp == 'A') { //Sten
                        myChars.add('B');
                    }
                    else if (opp == 'B') { // papper
                        myChars.add('C');
                    }
                    else if (opp == 'C') { // sax
                        myChars.add('A');
                    }
                }

            }
            final int charsTotal = myChars.stream().map(Day2_2::countSignValue).reduce(0, Integer::sum);
            System.out.println("Min total är : " + (myTotalPoints + charsTotal));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int countSignValue(Character c) {
        if(c == 'A') {
            return 1;
        } else if (c == 'B') {
            return 2;
        }
        return 3;
    }
}