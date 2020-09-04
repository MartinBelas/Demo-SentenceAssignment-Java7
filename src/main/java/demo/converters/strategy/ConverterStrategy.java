package demo.converters.strategy;

import java.io.File;

public interface ConverterStrategy {

    File convert(File inputFile, String outputFileName);
}
