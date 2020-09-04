package demo.converters;

import demo.domain.Convertible;

import java.io.File;

public interface Converter<T extends Convertible, R extends Convertible> {

    R convertItem(T inputItem);

	File convertFile(File inputFile, String outputFileName);
}
