import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day5_1 {
    public static void main(String[] args) {
        final Stack<Character> first = new Stack<>();
        first.push('Z');
        first.push('N');
        final Stack<Character> second = new Stack<>();
        second.push('M');
        second.push('C');
        second.push('D');
        final Stack<Character> third = new Stack<>();
               third.push('P');

        Map<Integer, Stack<Character>> stackMap = new HashMap<>();
stackMap.put(1, first);
stackMap.put(2, second);
stackMap.put(3, third);

        try {
            File crates = new File("src/cratesPlacements.txt");
            Scanner myReader = new Scanner(crates);
            while (myReader.hasNextLine()) {
                String movement = myReader.nextLine();
                final String[] movements = movement.split(" ");
                final int moveFrom = Integer.parseInt(movements[3]);
                final int numberOfPops = Integer.parseInt(movements[1]);
                final int moveTo = Integer.parseInt(movements[5]);

                Stack<Character> from = stackMap.get(moveFrom);
                Stack<Character> to = stackMap.get(moveTo);
                for(int i = numberOfPops; i > 0; i--) {
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
            } catch(FileNotFoundException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }