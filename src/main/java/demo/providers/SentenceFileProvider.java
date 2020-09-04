package demo.providers;

import java.io.File;
import java.io.FileNotFoundException;

import demo.domain.Sentence;

public class SentenceFileProvider implements FileProvider<Sentence>{

	@Override
	public FileType getFileType() {
		return FileType.SENTENCE;
	}

	@Override
	public File getFile(String fileName) throws FileNotFoundException {
        
    	File f = new File(fileName);
        
        if (!f.exists()) {
            logger.error(String.format("File %s does not exist.", f.getAbsolutePath()));
            throw new FileNotFoundException(f.getAbsolutePath());
        }
        
        return f;
    }
}
