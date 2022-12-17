import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day6_2 {
    public static void main(String[] args) {
        int numberOfUniqInARow = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int highest = 0;
        try {
            File numbers = new File("src/communication2.txt");
            Scanner myReader = new Scanner(numbers);
            while (myReader.hasNextLine()) {
                String communication = myReader.nextLine();
                final char[] chars = communication.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    if (charMap.size() < 14) {
                        if (charMap.containsKey(chars[i])) {
                           i = charMap.get(chars[i]);
                           charMap.clear();
                        } else {
                            charMap.put(chars[i], i);
                        }
                    } else if (charMap.size() == 14) {
                        System.out.println("Svaret är: " + (i));
                        break;

                    } else {
                        charMap.put(chars[i], i);
                    }
//                    numberOfUniqInARow = numberOfUniqInARow + 1;
//                    if (numberOfUniqInARow == 14) {
//                        System.out.println("Svaret är: " + (i + 1));
//                        break;
//                    }
//                    for (int x = i + 1; (x <= chars.length-1 && x <= (i + 13 - numberOfUniqInARow)); x++) {
//                        if (chars[i] == chars[x]) {
//                            numberOfUniqInARow = 0;
//                            break;
//                        }
//
//                    }
                }
                }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}