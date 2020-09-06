package demo.converters.strategy;

import demo.domain.CsvRow;
import demo.domain.Sentence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentenceToCsvConverterStrategy implements ConverterStrategy {

    /**
     * Used to create the CSV file header. The sentence's length is variable and we
     * need to know the longest one before conversion starts to create the header.
     * It can has very bad performance impact to try to find out the value
     * dynamically for large files.
     */
    public final static int DEFAULT_MAX_COUNT_OF_ITEMS = 100;
    
    private final static String OUTPUT_FILE_SUFFIX = "csv";

    private static Logger logger = LoggerFactory.getLogger(SentenceToCsvConverterStrategy.class);

    @Override
    public File convert(File inputFile) {

        logger.info("CONVERT Sentences to CSV ...");
        
        String outputFileName = getOutputFileName(inputFile.getAbsolutePath());

        String header = ",";
        for (int i = 0; i < DEFAULT_MAX_COUNT_OF_ITEMS; i++) {
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

            int rowIndex = 1;
            while (sc.hasNext()) {
                Sentence sentence = new Sentence(sc.next());
                if (!sentence.getWords().isEmpty()) {
                    pw.println("Sentence " + rowIndex++ + ", " + 
                            new CsvRow(sentence.getWords()).getConvertedToString());
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
    
    private static String getOutputFileName(String inputFileName) {
        
        int i = inputFileName.lastIndexOf('.');
        if (i > 0) {
            inputFileName = inputFileName.substring(0, i);
        }

        return inputFileName + "." + OUTPUT_FILE_SUFFIX;
    }
}
