package demo.providers;

import demo.domain.Convertible;

import java.io.File;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface FileProvider<T extends Convertible> {

	Logger logger = LoggerFactory.getLogger(FileProvider.class);
	
	FileType getFileType();
	
	File getFile(String fileName) throws FileNotFoundException;
}
