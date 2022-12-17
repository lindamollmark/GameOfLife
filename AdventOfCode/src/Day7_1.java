import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7_1 {
    public static void main(String[] args) {
        String currentDir = "";
        Map<String, DirectoryValues> directorys = new HashMap<>();

        final List<String> lines = readFile();
        for (String line: lines) {
            if (line.startsWith("$") && line.substring(2,4).equals("cd")) {
                if (!line.substring(5).equals("..")) {
                    currentDir = line.substring(5);
                    final DirectoryValues directoryValues = new DirectoryValues(line.substring(5));
                    directorys.put(currentDir, directoryValues);
                }
            } else if (!line.startsWith("$")) {
                final DirectoryValues directoryValues = directorys.get(currentDir);
                if (line.substring(0,3).equals("dir")) {
                    directoryValues.addSubDir(line.substring(3).trim());
                } else {
                    final String[] splitLines = line.split(" ");
                    directoryValues.addFileSize(Integer.parseInt(splitLines[0]));
                }
            }
        }

        directorys.values().stream()
                .filter(d -> !d.subDir.isEmpty() || d.subDir != null)
                .forEach(d -> d.subDir.forEach(s -> {
                    final DirectoryValues subDir = directorys.get(s);
                    d.addSubDirTotal(subDir.totalFileSize());
                }));

        final Integer totalLowSizeDir = directorys.values().stream()
                .filter(e -> e.totalFileSize() <= 100000)
                .map(d -> d.totalFileSize())
                .reduce(0, (a, b) -> Integer.sum(a, b));

        System.out.println(totalLowSizeDir);
    }


    private static List<String> readFile() {
        List<String> lines = new ArrayList<>();

        try {
            File numbers = new File("src/fileSystem.txt");
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