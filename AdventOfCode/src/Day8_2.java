import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Day8_2 {
    public static void main(String[] args) {
        final List<String> lines = readFile();

        final AtomicInteger numberOfVisible = new AtomicInteger();
        final int size = lines.size();
        final int rowLength = lines.get(0).split("").length;
        Integer[][] numbers = new Integer[size][rowLength];
//        Integer[][] result = new Integer[size][rowLength];
        List<Integer> result = new ArrayList<>();


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
        for (int x = 1; x <= lines.size() - 2; x++) {
            for (int y = 1; y <= lines.size() - 2; y++) {
                final int visibleOnTheLeft = noOfVisibleOnTheLeft(numbers[x][y], numbers, x, y);
                final int higherOnRight = anyHigherOnRight(numbers[x][y], numbers, x, y);
                final int higherAbove = anyHigherAbove(numbers[x][y], numbers, x, y);
                final int higherBelow = anyHigherBelow(numbers[x][y], numbers, x, y);

                result.add(higherAbove * higherBelow * visibleOnTheLeft * higherOnRight);
                    }
            }


//        for (int i = 0; i <= lines.size() - 1; i++) {
//            for (int y = 0; y <= lines.size() - 1; y++) {
//
//                if (result[i][y] != null && result[i][y].equals("V")) {
//                    numberOfVisible.getAndIncrement();
//                }
//            }
//            }

        final int max = result.stream()
                .mapToInt(v -> v)
                .max().orElse(0);
        System.out.println(max);
    }
    private static int noOfVisibleOnTheLeft(Integer number, Integer[][] numbers, int x, int y) {
        int noOfVisible = 0;
        for (int i = x-1; i >= 0; i--) {
            if (number > numbers[i][y]){
                noOfVisible = noOfVisible + 1;
            } else if (number <= numbers[i][y]) {
                return noOfVisible + 1;
            }
        }
        return noOfVisible;
    }

    private static int anyHigherOnRight(Integer number, Integer[][] numbers, int x, int y) {
        int noOfVisible = 0;
        for (int i = x+1; i <= numbers.length -1; i++) {
            if (numbers[i][y] < number) {
                noOfVisible = noOfVisible + 1;
            } else if (numbers[i][y] >= number) {
                return noOfVisible + 1;
            }
        }
        return noOfVisible;
    }

 private static int anyHigherAbove(Integer number, Integer[][] numbers, int x, int y) {
     int noOfVisible = 0;
     for (int i = y+1; i <= numbers.length -1; i++) {
            if (numbers[x][i] < number) {
                noOfVisible = noOfVisible + 1;
            }else if (numbers[x][i] >= number) {
                return noOfVisible + 1;
            }
        }
        return noOfVisible;
    }

    private static int anyHigherBelow(Integer number, Integer[][] numbers, int x, int y) {
        int noOfVisible = 0;
        for (int i = y-1; i >= 0; i--) {
            if (numbers[x][i] < number) {
                noOfVisible = noOfVisible + 1;
            } if (numbers[x][i] >= number) {
                return noOfVisible + 1;
            }
        }
        return noOfVisible;
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