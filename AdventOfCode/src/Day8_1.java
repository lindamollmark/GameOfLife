import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day8_1 {
    public static void main(String[] args) {
        final List<String> lines = readFile();

        final AtomicInteger numberOfVisible = new AtomicInteger();
        final int size = lines.size();
        final int rowLength = lines.get(0).split("").length;
        Integer[][] numbers = new Integer[size][rowLength];
        String[][] result = new String[size][rowLength];


//        for (String line : lines) {
//            Arrays.stream(line.split(""))
//                    .map(l -> Integer.parseInt(l))
//                    .reduce((a, b) -> a < b ? 0 : 1)
//                    .ifPresent(v -> numberOfVisible.set(numberOfVisible.get() + v));
//        }
        for (int i = 0; i <= lines.size() - 1; i++) {
            final String[] line = lines.get(i).split("");
            for (int y = 0; y <= lines.size() - 1; y++) {
                numbers[i][y] = Integer.parseInt(line[y]);
            }
        }
         Integer number;
        for (int i = 1; i <= lines.size() - 2; i++) {
            for (int y = 1; y <= lines.size() - 2; y++) {
                number = numbers[i][y];
                final boolean higherOnLeft = anyHigherOnLeft(number, numbers, i, y);
                final boolean higherOnRight = anyHigherOnRight(number, numbers, i, y);
                final boolean higherAbove = anyHigherAbove(number, numbers, i, y);
                final boolean higherBelow = anyHigherBelow(number, numbers, i, y);

                        if (higherAbove && higherBelow && higherOnLeft && higherOnRight) {
                            result[i][y] = "X";
                        } else {
                            result[i][y] = "V";
                        }
                    }
            }


        for (int i = 0; i <= lines.size() - 1; i++) {
            for (int y = 0; y <= lines.size() - 1; y++) {
                if (i == 0 || i == lines.size()-1 || y == 0 || y == lines.size() -1) {
                    result[i][y] = "V";
                }
                if (result[i][y] != null && result[i][y].equals("V")) {
                    numberOfVisible.getAndIncrement();
                }
            }
            }

        System.out.println(numberOfVisible.get());
    }
    private static boolean anyHigherOnLeft(Integer number, Integer[][] numbers, int x, int y) {
        for (int i = x-1; i >= 0; i--) {
            if (numbers[i][y] >= number){
                return true;
            }
        }
        return false;
    }

    private static boolean anyHigherOnRight(Integer number, Integer[][] numbers, int x, int y) {
        for (int i = x+1; i <= numbers.length -1; i++) {
            if (numbers[i][y] >= number) {
                return true;
            }
        }
        return false;
    }

 private static boolean anyHigherAbove(Integer number, Integer[][] numbers, int x, int y) {
        for (int i = y+1; i <= numbers.length -1; i++) {
            if (numbers[x][i] >= number) {
                return true;
            }
        }
        return false;
    }

    private static boolean anyHigherBelow(Integer number, Integer[][] numbers, int x, int y) {
        for (int i = y-1; i >= 0; i--) {
            if (numbers[x][i] >= number) {
                return true;
            }
        }
        return false;
    }

    private static List<String> readFile() {
        List<String> lines = new ArrayList<>();

        try {
            File numbers = new File("src/treePlacement.txt");
            Scanner myReader = new Scanner(numbers);
            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
            myReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lines;
    }
}