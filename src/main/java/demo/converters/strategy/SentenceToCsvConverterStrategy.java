package demo.converters.strategy;

import demo.domain.CsvRow;
import demo.domain.Sentence;
import demo.converters.Converter;
import demo.converters.SentenceToCsvConverter;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SentenceToCsvConverterStrategy implements ConverterStrategy {

    private static Logger logger = LoggerFactory.getLogger(SentenceToCsvConverterStrategy.class);

    Converter<Sentence, CsvRow> converter = new SentenceToCsvConverter();

	@Override
	public File convert(File inputFile, String outputFileName) {
		logger.info("CONVERT Sentences to CSV ...");
        return converter.convertFile(inputFile, outputFileName);
	}
}
