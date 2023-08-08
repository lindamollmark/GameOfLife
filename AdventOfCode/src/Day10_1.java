import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day10_1 {
    public static void main(String[] args) {

        final List<String> lines = readFile();
        final AtomicInteger x = new AtomicInteger(1);
        List<Integer> cycleValues = new ArrayList<>();
        Map<Integer, Integer> roundTotal = new HashMap<>();
        final AtomicInteger round = new AtomicInteger(0);

        lines.stream()
                .forEach(l -> {
                    if (l.equals("noop")) {
                        cycleValues.add(x.get());
                        roundTotal.put(round.incrementAndGet(), x.get());

                    } else {
                        cycleValues.add(x.get());
                        roundTotal.put(round.incrementAndGet(), x.get());

                        final String[] split = l.split(" ");
                        final String xValue = split[1];
                        if (xValue.contains("-")) {
                            cycleValues.add(x.get());
                            x.set(x.get() - Integer.parseInt(xValue.substring(1)));
                            roundTotal.put(round.incrementAndGet(), x.get());

                        } else {
                            cycleValues.add(x.get());
                            x.set(x.get() + Integer.parseInt(xValue));
                            roundTotal.put(round.incrementAndGet(), x.get());
                        }
                    }
                });

        int total = 0;
        int i;
        for (i = 19; i < 220; ) {
            total = total + ((i+1) * cycleValues.get(i));
            i = i + 40;
        }
        System.out.println(total);

    }
    private static List<String> readFile() {
        List<String> lines = new ArrayList<>();

        try {
            File numbers = new File("src/cycleInput.txt");
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