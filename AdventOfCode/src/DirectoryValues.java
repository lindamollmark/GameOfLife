import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectoryValues {
    private String directory;
    List<String> subDir = new ArrayList<>();
    Integer fileSize = 0;
    private int subDirTotal = 0;


    public DirectoryValues(String directory) {
        this.directory = directory;
    }

    public String directory() {
        return directory;
    }

    public void addSubDir(String subDir) {
        this.subDir.add(subDir);
    }

    public void addFileSize(int fileSize) {
        this.fileSize = this.fileSize + fileSize;
    }

    public int totalFileSize() {
        return fileSize + subDirTotal;
    }

    public void addSubDirTotal(int subDirTotal) {
        this.subDirTotal = this.subDirTotal + subDirTotal;
    }
}
