package demo.converters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import demo.domain.Sentence;
import demo.domain.XmlElement;

public class SentenceToXmlConverterTest {
    
	SentenceToXmlConverter converter;
    Sentence sentence;

    @Before
    public void setUp() {
        converter = new SentenceToXmlConverter();
        sentence = new Sentence("Some nonsense    in some  sentence");
    }

    @Test
    public void testConvertItem() throws Exception {

        XmlElement csvExpected = new XmlElement(new ArrayList<>(Arrays.asList("in", "nonsense", "sentence", "Some", "some"))) ;

        XmlElement result = converter.convertItem(sentence);
        assertEquals(csvExpected.toString(), result.toString());
    }

    @Test
    public void testConvertEmptySentence() throws Exception {

        String testSentence = "";
        Sentence convertible = new Sentence(testSentence);
        XmlElement csvExpected = new XmlElement();

        XmlElement result = converter.convertItem(convertible);
        assertEquals(csvExpected.toString(), result.toString());
    }

    @Test
    public void testConvertNullSentence() throws Exception {

        Sentence convertible = null;
        XmlElement csvExpected = null;

        XmlElement result = converter.convertItem(convertible);
        assertEquals(csvExpected, result);
    }
}