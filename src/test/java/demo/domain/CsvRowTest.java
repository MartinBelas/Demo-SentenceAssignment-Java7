package demo.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class CsvRowTest {

    @Test
    public void testConvertSentence() throws Exception {

        String someSentence = "Some nonsense    in some  sentence";
        Sentence testSentence = new Sentence(someSentence);
        
        String csvStringExpected = "in, nonsense, sentence, Some, some";

        CsvRow csvRow = new CsvRow(testSentence.getWords());
        String csvStringConverted = csvRow.getConvertedToString();
        assertEquals(csvStringExpected, csvStringConverted);
    }

    @Test
    public void testConvertEmptySentence() throws Exception {

        String someSentence = "";
        Sentence testSentence = new Sentence(someSentence);
        
        String csvStringExpected = "";

        CsvRow csvRow = new CsvRow(testSentence.getWords());
        String csvStringConverted = csvRow.getConvertedToString();
        assertEquals(csvStringExpected, csvStringConverted);
    }
}
