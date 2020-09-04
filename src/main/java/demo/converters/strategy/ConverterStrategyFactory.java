package demo.converters.strategy;

import demo.providers.FileType;

public class ConverterStrategyFactory {

    private enum ConverterStrategyType {
        SENTENCE_TO_CSV,
        SENTENCE_TO_XML,
        NONE;
    }

    //public ConverterStrategy<?, ?> getStrategy(FileType inputFileType, FileType outputFileType) {
    public ConverterStrategy getStrategy(FileType inputFileType, FileType outputFileType) {

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

