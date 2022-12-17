import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day5_1_2 {
    public static void main(String[] args) {
        final Stack<Character> first = new Stack<>();
        first.push('R');
        first.push('N');
        first.push('P');
        first.push('G');
        final Stack<Character> second = new Stack<>();
        second.push('T');
        second.push('J');
        second.push('B');
        second.push('L');
        second.push('C');
        second.push('S');
        second.push('V');
        second.push('H');
        final Stack<Character> third = new Stack<>();
        third.push('T');
        third.push('D');
        third.push('B');
        third.push('M');
        third.push('N');
        third.push('L');
        final Stack<Character> fourth = new Stack<>();
        fourth.push('R');
        fourth.push('V');
        fourth.push('P');
        fourth.push('S');
        fourth.push('B');
        final Stack<Character> fifth = new Stack<>();
        fifth.push('G');
        fifth.push('C');
        fifth.push('Q');
        fifth.push('S');
        fifth.push('W');
        fifth.push('M');
        fifth.push('V');
        fifth.push('H');
        final Stack<Character> sixth = new Stack<>();
        sixth.push('W');
        sixth.push('Q');
        sixth.push('S');
        sixth.push('C');
        sixth.push('D');
        sixth.push('B');
        sixth.push('J');
        final Stack<Character> seventh = new Stack<>();
        seventh.push('F');
        seventh.push('Q');
        seventh.push('L');
        final Stack<Character> eigth = new Stack<>();
        eigth.push('W');
        eigth.push('M');
        eigth.push('H');
        eigth.push('T');
        eigth.push('D');
        eigth.push('L');
        eigth.push('F');
        eigth.push('V');
        final Stack<Character> ninth = new Stack<>();
        ninth.push('L');
        ninth.push('P');
        ninth.push('B');
        ninth.push('V');
        ninth.push('M');
        ninth.push('J');
        ninth.push('F');

        Map<Integer, Stack<Character>> stackMap = new HashMap<>();
        stackMap.put(1, first);
        stackMap.put(2, second);
        stackMap.put(3, third);
        stackMap.put(4, fourth);
        stackMap.put(5, fifth);
        stackMap.put(6, sixth);
        stackMap.put(7, seventh);
        stackMap.put(8, eigth);
        stackMap.put(9, ninth);

        try {
            File crates = new File("src/cratesPlacements2.txt");
            Scanner myReader = new Scanner(crates);
            while (myReader.hasNextLine()) {
                String movement = myReader.nextLine();
                final String[] movements = movement.split(" ");
                final int moveFrom = Integer.parseInt(movements[3]);
                final int numberOfPops = Integer.parseInt(movements[1]);
                final int moveTo = Integer.parseInt(movements[5]);

                Stack<Character> from = stackMap.get(moveFrom);
                Stack<Character> to = stackMap.get(moveTo);
                for (int i = numberOfPops; i > 0; i--) {
                    final Character pop = from.pop();
                    to.push(pop);
                    stackMap.put(moveFrom, from);
                    stackMap.put(moveTo, to);
                }

            }
            final String result = stackMap.values().stream().map(v -> v.pop().toString())
                    .collect(Collectors.joining());
            System.out.println("Min rad är : " + result);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}