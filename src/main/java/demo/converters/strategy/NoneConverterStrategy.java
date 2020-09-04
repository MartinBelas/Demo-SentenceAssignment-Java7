package demo.converters.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class NoneConverterStrategy implements ConverterStrategy {

    private static Logger logger = LoggerFactory.getLogger(NoneConverterStrategy.class);

	@Override
	public File convert(File inputFile, String outputFileName) {
        logger.info("Convert with NoneConversionStrategy.");
		return null;
	}
}
