package demo.converters.strategy;

import java.io.File;

import demo.domain.FileType;

public class ConverterStrategyFactory {

    private enum ConverterStrategyType {
        SENTENCE_TO_CSV,
        SENTENCE_TO_XML,
        NONE;
    }

    public ConverterStrategy getStrategy(File inputFile, FileType outputFileType) {
    	
    	FileType inputFileType = getFileType(inputFile);

        ConverterStrategyType strategyType = getStrategyType(inputFileType, outputFileType);

        switch (strategyType) {

            case SENTENCE_TO_CSV:
                return new SentenceToCsvConverterStrategy();

            case SENTENCE_TO_XML:
                return new SentenceToXmlConverterStrategy();

            default:
                return new NoneConverterStrategy();
        }
    }

    private FileType getFileType(File inputFile) {
    	
    	String extension = "";

    	int i = inputFile.getName().lastIndexOf('.');
    	if (i > 0) {
    	    extension = inputFile.getName().substring(i+1).toUpperCase();
    	}
    	
        switch (extension) {

	        case "CSV":
	            return FileType.CSV;
	
	        case "XML":
	            return FileType.XML;
	
	        default:
	        	return FileType.SENTENCE;
	    }
	}

	private static ConverterStrategyType getStrategyType(FileType inputFileType, FileType outputFileType) {

        // Sentences to CSV or XML
        if (inputFileType.equals(FileType.SENTENCE)) {
            if (outputFileType.equals(FileType.CSV)) {
                return ConverterStrategyType.SENTENCE_TO_CSV;
            }

            if (outputFileType.equals(FileType.XML)) {
                return ConverterStrategyType.SENTENCE_TO_XML;
            }
        }

        return ConverterStrategyType.NONE;
    }
}

