import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day3_2 {
    public static void main(String[] args) {
        List<String> itemsList = new ArrayList<>();
        final List<Integer> total = new ArrayList<>();
        try {
            File numbers = new File("src/rucksacks.txt");
            Scanner myReader = new Scanner(numbers);

            while (myReader.hasNextLine()) {
                itemsList.add(myReader.nextLine());
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        final String[] items = itemsList.toArray(new String[0]);
        for (int i = 0; i <= items.length-1; i = i+3) {
            final Set<Character> first = items[i].chars().mapToObj(l -> (char) l).collect(Collectors.toSet());
            final Set<Character> second = items[1 + i].chars().mapToObj(l -> (char) l).collect(Collectors.toSet());
            final Set<Character> third = items[2 + i].chars().mapToObj(l -> (char) l).collect(Collectors.toSet());

            first.stream()
                    .filter(f -> second.contains(f) && third.contains(f))
                    .map(c -> total.add(countSignValue(c)))
                    .collect(Collectors.toSet());

        }
        final Integer sum = total.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private static int countSignValue(char c) {
        if (Character.isUpperCase(c)) {
            final int letterAsciiValue = Character.toLowerCase(c);
            return letterAsciiValue - 70;
        }
        final int letterAsciiValue = c;
        return letterAsciiValue - 96;
    }
}