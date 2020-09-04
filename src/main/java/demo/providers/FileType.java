package demo.providers;

public enum FileType {
    SENTENCE(1),
    CSV(2),
    XML(3);

    private int code;

    private FileType(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
