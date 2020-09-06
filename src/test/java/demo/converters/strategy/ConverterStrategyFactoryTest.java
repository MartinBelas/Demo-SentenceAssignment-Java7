package demo.converters.strategy;

import org.junit.Before;
import org.junit.Test;

import demo.domain.FileType;

import static org.junit.Assert.assertEquals;

import java.io.File;

public class ConverterStrategyFactoryTest {

    ConverterStrategyFactory factory;

    @Before
    public void setUp() {
        factory = new ConverterStrategyFactory();
    }

    @Test
    public void test_getStrategy_returns_SentenceToCsvConverterStrategy() {

    	File inputFile = new File("SomeFile.txt");
    	FileType outputFileType = FileType.CSV;
        
        Class<? extends ConverterStrategy> expectedStrategy = SentenceToCsvConverterStrategy.class;

		ConverterStrategy strategy = factory.getStrategy(inputFile, outputFileType);
        assertEquals(expectedStrategy.getName(), strategy.getClass().getName());
    }

    @Test
    public void test_getStrategy_returns_SentenceToXmlConverterStrategy() {

    	File inputFile = new File("SomeFile.txt");
        FileType outputFileType = FileType.XML;

        Class<? extends ConverterStrategy> expectedStrategy = SentenceToXmlConverterStrategy.class;

        ConverterStrategy strategy = factory.getStrategy(inputFile, outputFileType);
        assertEquals(expectedStrategy.getName(), strategy.getClass().getName());
    }

    @Test
    public void test_GetStrategy_Returns_NoneConverterStrategy() {

    	File inputFile = new File("SomeFile.txt");
        FileType outputFileType = FileType.SENTENCE;

        Class<? extends ConverterStrategy> expectedStrategy = NoneConverterStrategy.class;

        ConverterStrategy strategy = factory.getStrategy(inputFile, outputFileType);
        assertEquals(String.format("When converting from SENTENCE to SENTENCE then must return %s.", expectedStrategy.getName()),
        		expectedStrategy.getName(), strategy.getClass().getName());
    }
}