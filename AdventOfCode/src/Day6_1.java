import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6_1 {
    public static void main(String[] args) {
        int numberOfUniqInARow = 0;
        int highest = 0;
        try {
            File numbers = new File("src/communication.txt");
            Scanner myReader = new Scanner(numbers);
            while (myReader.hasNextLine()) {
                String communication = myReader.nextLine();
                final char[] chars = communication.toCharArray();

                for (int i = 0; i <= chars.length; i++) {
                    numberOfUniqInARow = numberOfUniqInARow+1;
                    if (numberOfUniqInARow == 4) {
                        System.out.println("Svaret är: " + (i + 1));
                        break;
                    }
                    for (int x = i + 1; x <= i + 3; x++) {
                        if (chars[i] == chars[x]) {
                            numberOfUniqInARow = 0;
                            break;
                        }

                    }

                }
//                boolean foundDuplicate = false;
//                for (final char letter :  communication.toCharArray()) {
//                    if (foundDuplicate) {
//                        break;
//                    }
//                    for (final char secondLetter : second.toCharArray()) {
//                        if (secondLetter == letter) {
//                            dublicatedChars.add(letter);
//                            foundDuplicate = true;
//                            break;
//                        }
//                    }
//                }

            }
//            final int charsTotal = dublicatedChars.stream().map(Day3_1::countSignValue).reduce(0, Integer::sum);
//            System.out.println("Min total är : " + charsTotal);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}