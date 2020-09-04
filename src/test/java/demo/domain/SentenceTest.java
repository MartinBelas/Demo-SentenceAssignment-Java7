package demo.domain;

import demo.domain.Sentence;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class SentenceTest {

    Sentence sentence;
    final String someSentence = "Nordea Markets is the leading international capital markets operator and investment banking partner in the Nordic and Baltic Sea regions.";
    String[] arr = new String[] { "and", "and", "Baltic", "banking", "capital" };
    List<String>firstWordsOfTheSentence = Arrays.asList(arr);

    @Before
    public void setUp() {
        sentence = new Sentence(someSentence);
    }

    @Test
    public void testGetWords() {

        final Collection<String> firstWordsOfTestResult = sentence.getWords().subList(0, 5);

        assertEquals("Words count in the sentence must be as expected.", someSentence.split("\\s+").length, sentence.getWords().size());
        assertEquals("The first sorted words of the sentence must be as expected.", firstWordsOfTheSentence, firstWordsOfTestResult);
    }
}