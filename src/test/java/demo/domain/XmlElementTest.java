package demo.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class XmlElementTest {

    @Test
    public void testConvertSentence() throws Exception {

        String someSentence = "Some nonsense    in some  sentence";
        Sentence testSentence = new Sentence(someSentence);
        
        String xmlStringExpected = "in, nonsense, sentence, Some, some";

        CsvRow xmlRow = new CsvRow(testSentence.getWords());
        String xmlStringConverted = xmlRow.getConvertedToString();
        assertEquals(xmlStringExpected, xmlStringConverted);
    }

    @Test
    public void testConvertEmptySentence() throws Exception {

        String someSentence = "";
        Sentence testSentence = new Sentence(someSentence);
        
        String xmlStringExpected = "";

        CsvRow xmlRow = new CsvRow(testSentence.getWords());
        String xmlStringConverted = xmlRow.getConvertedToString();
        assertEquals(xmlStringExpected, xmlStringConverted);
    }
    
}
