package demo.converters;

import demo.domain.CsvRow;
import demo.domain.Sentence;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SentenceToCsvConverterTest {

    SentenceToCsvConverter converter;
    Sentence sentence;

    @Before
    public void setUp() {
        converter = new SentenceToCsvConverter();
        sentence = new Sentence("Some nonsense    in some  sentence");
    }

    @Test
    public void testConvertItem() throws Exception {

        CsvRow csvExpected = new CsvRow("in, nonsense, sentence, Some, some");

        CsvRow result = converter.convertItem(sentence);
        assertEquals(csvExpected.toString(), result.toString());
    }

    @Test
    public void testConvertEmptySentence() throws Exception {

        String testSentence = "";
        Sentence convertible = new Sentence(testSentence);
        CsvRow csvExpected = new CsvRow("");

        CsvRow result = converter.convertItem(convertible);
        assertEquals(csvExpected.toString(), result.toString());
    }

    @Test
    public void testConvertNullSentence() throws Exception {

        Sentence convertible = null;
        CsvRow csvExpected = new CsvRow("");

        CsvRow result = converter.convertItem(convertible);
        assertEquals(csvExpected.toString(), result.toString());
    }
}