package demo.converters;

import demo.domain.CsvRow;
import demo.domain.Sentence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Converts text file to CSV file.
 * The text is parsed, broken into sentences and words and the words are sorted.
 * The sorted words of the sentences are places into a CSV file.
 */
public class SentenceToCsvConverter implements Converter<Sentence, CsvRow> {
	
	int rowIndex = 1;

    @Override
    public CsvRow convertItem(Sentence sentence) {

        if (sentence == null) {
            return new CsvRow("");
        }
        
        String csvRow = "";
        for (String word : sentence.getWords()) {
        	csvRow += (" "+word+","); 
        }

        if (csvRow.endsWith(",")) {
        	csvRow = csvRow.substring(0, csvRow.length() - 1);
        }

        return new CsvRow(csvRow);
    }

    @Override
    public File convertFile(File inputFile, String outputFileName) {

        String header = ",";
        for (int i = 0; i < CsvRow.DEFAULT_MAX_COUNT_OF_ITEMS; i++) {
            header += String.format("Word %d,", i);
        }
        header = header.substring(0, header.length() - 1);

        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(inputFile);
            sc = new Scanner(inputStream, "UTF-8");
            sc.useDelimiter(Sentence.SENTENCE_DELIMITERS);
            
            PrintWriter pw = new PrintWriter(outputFileName, "UTF-8");
        	pw.println(header);
        
            while (sc.hasNext()) {
            	Sentence sentence = new Sentence(sc.next());
            	if (!sentence.getWords().isEmpty()) {
            		pw.println(newCsvRow(convertItem(sentence)));
            	}
            }
            pw.close();
            return new File(outputFileName);
        
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
		return null;        
    }

    private CsvRow newCsvRow(CsvRow csvRow) {
        return new CsvRow("Sentence " + this.rowIndex++ + ", " + csvRow);
    }
}

