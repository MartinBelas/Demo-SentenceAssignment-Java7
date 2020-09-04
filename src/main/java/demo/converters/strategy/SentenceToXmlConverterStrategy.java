package demo.converters.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.converters.Converter;
import demo.converters.SentenceToXmlConverter;
import demo.domain.Sentence;
import demo.domain.XmlElement;

import java.io.File;

public class SentenceToXmlConverterStrategy implements ConverterStrategy {

    private static Logger logger = LoggerFactory.getLogger(SentenceToXmlConverterStrategy.class);
    
    Converter<Sentence, XmlElement> converter = new SentenceToXmlConverter();

	@Override
	public File convert(File inputFile, String outputFileName) {
        logger.info("CONVERT String Sentence to XML ...");
        return converter.convertFile(inputFile, outputFileName);
	}
}
