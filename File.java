public class File {
    private String filename;
    private byte[] content;
    private long size;

    public File(String filename, byte[] content) {
        this.filename = filename;
        this.content = content;
        this.size = content.length;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String toString() {
        return "File{" +
                "filename='" + filename + '\'' +
                ", size=" + size +
                '}';
    }
}