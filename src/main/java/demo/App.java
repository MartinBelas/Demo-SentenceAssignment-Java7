package demo;

import demo.domain.Sentence;
import demo.providers.FileProvider;
import demo.providers.FileType;
import demo.providers.SentenceFileProvider;
import demo.converters.strategy.ConverterStrategy;
import demo.converters.strategy.ConverterStrategyFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

    final static String DEFAULT_FILE_PATH = "data.in";
    private static final String DEFAULT_INPUT_FILE_NAME_SUFFIX = ".in";

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        // 1. ask for input file resource (default "data.in" file in the App folder)
    	FileProvider<Sentence> inputFileProvider = new SentenceFileProvider();
    	String inputFileName = askForInputFileName();
    	File inputFile = null;
    	try {
			inputFile = inputFileProvider.getFile(inputFileName);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			System.exit(-1);
		}
    	logger.info("Input File Type: {}", inputFileProvider.getFileType());

        // 2. ask for output file type, for example xml or csv (from enum)
        FileType outputFileType = null;
        while (outputFileType == null) {
        	outputFileType = getOutputFileType();
        }
        logger.info("Output Stream Type: {}", outputFileType.name());

        // 3. select the conversion strategy
        ConverterStrategy strategy = new ConverterStrategyFactory().getStrategy(inputFileProvider.getFileType(), outputFileType);

        // 4. create output file name with the right extension
        String outputFileName = getOutputFileName(
        		inputFile.getName(),
        		outputFileType.name().toLowerCase()
        );
        logger.info("outputFileName: {}", outputFileName);

        // 5. start the conversion and export to output file
        File outputFile = strategy.convert(inputFile, outputFileName);

        // 6. write the output file location
        System.out.println("Finished");
        if (outputFile != null) {
        	System.out.println("Output file: " + outputFile.getAbsolutePath());
        }
    }

    
    /*
     * Asks for input file name.
     * If nothing entered then returns default file name data.in
     */
    private static String askForInputFileName() {

    	String inputFileName;
    	
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println(String.format("Please enter input file name (or press ENTER to use default %s) : ", DEFAULT_FILE_PATH));
		inputFileName = in.nextLine();

        if (!inputFileName.isEmpty()) {
            inputFileName = inputFileName.trim();
           
        } else {
            System.out.println(String.format("Input file not provided, we will use %s", DEFAULT_FILE_PATH));
            inputFileName = DEFAULT_FILE_PATH;
        }
        

        logger.info("Input file name: {}", inputFileName);

        return inputFileName;
    }
    
	private static FileType getOutputFileType() {
	
		FileType result = null;
	
	    while (result == null) {
	
	        System.out.println("Please select output file type: ");
	        
	        for ( FileType t : FileType.values()) {
	        	System.out.println(String.format("%d - %s", t.getCode(), t.name()));
	        }
	
	        System.out.println("Please enter the number: ");
	         
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
	
	        FileType found = null;
	        if (s.hasNextInt()) {
	            int input = s.nextInt();
	            for (FileType t : FileType.values()) {
	                if (t.getCode() == input) {
	                    found = t;
	                    break;
	                }
	            }
	        }
	        result = found;
	    }
	
	    return result;
	}
	
	  private static String getOutputFileName(String resourceName, String outputSuffix) {
	
	      if (resourceName != null && resourceName.endsWith(DEFAULT_INPUT_FILE_NAME_SUFFIX)) {
	          resourceName = resourceName.substring(0, resourceName.length() - DEFAULT_INPUT_FILE_NAME_SUFFIX.length());
	      }
	
	      return resourceName +"."+ outputSuffix;
	  }

}
