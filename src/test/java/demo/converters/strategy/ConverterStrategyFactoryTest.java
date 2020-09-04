package demo.converters.strategy;

import demo.providers.FileType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterStrategyFactoryTest {

    ConverterStrategyFactory factory;

    @Before
    public void setUp() {
        factory = new ConverterStrategyFactory();
    }

    @Test
    public void test_getStrategy_returns_SentenceToCsvConverterStrategy() {

    	FileType inputFileType = FileType.SENTENCE;
    	FileType outputFileType = FileType.CSV;
        
        Class<? extends ConverterStrategy> expectedStrategy = SentenceToCsvConverterStrategy.class;

		ConverterStrategy strategy = factory.getStrategy(inputFileType, outputFileType);
        assertEquals(expectedStrategy.getName(), strategy.getClass().getName());
    }

    @Test
    public void test_getStrategy_returns_SentenceToXmlConverterStrategy() {

        FileType inputFileType = FileType.SENTENCE;
        FileType outputFileType = FileType.XML;

        Class<? extends ConverterStrategy> expectedStrategy = SentenceToXmlConverterStrategy.class;

        ConverterStrategy strategy = factory.getStrategy(inputFileType, outputFileType);
        assertEquals(expectedStrategy.getName(), strategy.getClass().getName());
    }

    @Test
    public void test_GetStrategy_Returns_NoneConverterStrategy() {

        FileType inputFileType = FileType.SENTENCE;
        FileType outputFileType = FileType.SENTENCE;

        Class<? extends ConverterStrategy> expectedStrategy = NoneConverterStrategy.class;

        ConverterStrategy strategy = factory.getStrategy(inputFileType, outputFileType);
        assertEquals(String.format("When converting from SENTENCE to SENTENCE then must return %s.", expectedStrategy.getName()),
        		expectedStrategy.getName(), strategy.getClass().getName());
    }
}