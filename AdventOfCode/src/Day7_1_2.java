import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7_1_2 {
    public static void main(String[] args) {
//String currentDir = "/";
        MyTreeNode<String> root = new MyTreeNode<>("/");

        MyTreeNode<String> currentDir = root;

        final List<String> lines = readFile();
        for (String line : lines) {
            if (line.startsWith("$") && line.substring(2, 4).equals("cd")) {
                final String newDir = line.substring(4).trim();
                if (!newDir.equals("..") && !newDir.equals("/")) {
                    final Optional<MyTreeNode> children = currentDir.getChildren().stream()
                            .filter(c -> c.getData().equals(newDir))
                            .findAny();
                    ;
                    if (children.isPresent()) {
                        currentDir = children.get();
                    } else {
                        MyTreeNode<String> child = new MyTreeNode<>(newDir);
                        root.addChild(child);
                        final Optional<MyTreeNode> addedChild = root.getChildren().stream()
                                .filter(c -> c.getData().equals(newDir))
                                .findAny();
                        currentDir = addedChild.isPresent() ? addedChild.get() : child;
                    }
                }
                if (newDir.equals("..")) {
                    currentDir = currentDir.getParent();
                }
            } else if (!line.startsWith("$")) {
                if (line.substring(0, 3).trim().equals("dir")) {
                    final String childDir = line.substring(3).trim();
                    currentDir.addChild(childDir);

                } else {
                    final String[] splitLines = line.split(" ");
                    currentDir.addFileSize(Integer.parseInt(splitLines[0]));
                }
//                final MyTreeNode parent = currentDir.getParent();
//                final Object parentData = parent.getData();
            }
        }


        final int rootSize = root.fileSize() > 100000 ? 0 : root.fileSize();

        root.getChildren().stream()
                .forEach(c -> c.findChildFileSize());

        root.

//        root.getChildren().stream()
//                .flatMap(c -> c.getChildren().stream())
////                .filter(c -> c.fileSize() < 100000)
//                .map(c -> c.fileSize())
//                .reduce(rootSize, Integer::sum);

                System.out.println(rootChildSize);
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