import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyTreeNode<T>{
        private T data = null;
        private List<MyTreeNode> children = new ArrayList<>();
        private MyTreeNode parent = null;

        private int fileSize = 0;

        private int subSize = 0;

        public MyTreeNode(T data) {
            this.data = data;
        }

        public void addChild(MyTreeNode child) {
            child.setParent(this);
            this.children.add(child);
        }

        public void addChild(T data) {
            MyTreeNode<T> newChild = new MyTreeNode<>(data);
            this.addChild(newChild);
        }

        public void addChildren(List<MyTreeNode> children) {
            for(MyTreeNode t : children) {
                t.setParent(this);
            }
            this.children.addAll(children);
        }

        public List<MyTreeNode> getChildren() {
            return children;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        private void setParent(MyTreeNode parent) {
            this.parent = parent;
        }

        public MyTreeNode getParent() {
            return parent;
        }

        public void addFileSize(int fileSize) {
            this.fileSize = this.fileSize + fileSize;
        }

        public int fileSize() {
            return fileSize;
        }
        public int fileSizeIncludingChildSize() {
//            final int parentSize = fileSize < 100000 ? fileSize : 0;
            final int childFileSize = findChildFileSize();
            final int totalDirSize = fileSize + childFileSize;

            final int dirSizeToInclude = totalDirSize < 100000 ? totalDirSize : 0;
            return totalDirSize + childFileSize;
        }

        public void findChildFileSize() {

             for (MyTreeNode child : children) {
                 subSize = subSize + child.fileSize();
                 final List<MyTreeNode> children1 = child.getChildren();
                 children1.stream().forEach(c -> c.findChildFileSize());

             }
        }
}

