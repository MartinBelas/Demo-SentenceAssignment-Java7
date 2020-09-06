package demo.domain;

import java.util.ArrayList;
import java.util.List;

public class CsvRow implements Convertible {

    private List<String> words;
    
    public CsvRow(List<String> words) {
        List<String> clonedWords = new ArrayList<>(words);
        this.words = clonedWords;
    }

    @Override
    public String getConvertedToString() {

        String csvRow = "";
        for (String word : this.words) {
            csvRow += (" "+word+","); 
        }
        
        if (csvRow.endsWith(",")) {
            csvRow = csvRow.trim().substring(0, csvRow.length() - 2);
        }
        
        return csvRow;
    }
}
