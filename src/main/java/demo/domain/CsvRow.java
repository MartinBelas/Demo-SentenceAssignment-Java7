package demo.domain;

public class CsvRow implements Convertible {

    /**
     * Used in SentenceToCsvConverter to create the CSV file header.
     * The sentence's length is variable and we need to know the longest one before conversion starts to create the header.
     * It can has very bad performance impact to try to find out the value dynamically for large files or huge streams.
     */
    public final static int DEFAULT_MAX_COUNT_OF_ITEMS = 100;

    private final String csvRow;

    public CsvRow(String csvRow) {
        this.csvRow = csvRow.trim();
    }

    public String toString() {
        return this.csvRow;
    }
}
