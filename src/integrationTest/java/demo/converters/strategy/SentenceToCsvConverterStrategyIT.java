package demo.converters.strategy;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class SentenceToCsvConverterStrategyIT {

    private static Logger logger = LoggerFactory.getLogger(SentenceToCsvConverterStrategyIT.class);

    SentenceToCsvConverterStrategy strategy;

	File integrationTestResources = new File("src/integrationTest/resources");
	String resourcePath = integrationTestResources.getAbsolutePath();
	

    @Before
    public void setUp() {
    	strategy = new SentenceToCsvConverterStrategy();
    }

    @Test
    public void testConvertFromSmallTextFile() throws Exception {
    	
    	final String smallInputFileName = resourcePath + "\\small.in";
        final String smallOutputFileName = resourcePath + "\\small.csv";
        
    	logger.info("Convert from small file: {}", smallInputFileName);
    	logger.info("Convert to small csv file: {}", smallOutputFileName);
    	
    	File inputFile = new File(smallInputFileName);

        File output = strategy.convert(inputFile);
        
        logger.info("Finished stream conversion in testConvertFromSmallTextFile.");
        
        File expectedCsvFile = new File(smallOutputFileName);
        assertTrue("Output CSV file must exist.", expectedCsvFile.isFile());
        
        BufferedReader brTest = new BufferedReader(new FileReader(expectedCsvFile));
        String header = brTest.readLine().substring(0, 22);
        String csvRow1 = brTest.readLine().substring(0, 24);
        brTest.close();
        
        assertEquals("Output file must be same with right suffix.", output.getAbsolutePath(), smallOutputFileName);
        assertEquals("Output CSV file must contain header.", header, ",Word 0,Word 1,Word 2,");
        assertEquals("Output CSV file must contain row with CSV data.", csvRow1, "Sentence 1, he, shocking");
    }

    @Test
    public void testConvertFromLargeTextFile() throws Exception {
    	
    	final String largeInputFileName = resourcePath + "\\large.in";
        final String largeOutputFileName = resourcePath + "\\large.csv";

    	logger.info("Convert large file: {}", largeInputFileName);
    	logger.info("Convert to large csv file: {}", largeOutputFileName);
    	
        
    	logger.info("Convert from large file: {}", largeInputFileName);
    	logger.info("Convert to large csv file: {}", largeOutputFileName);
    	
    	File inputFile = new File(largeInputFileName);

    	File output = strategy.convert(inputFile);
        
        logger.info("Finished stream conversion in testConvertFromSmallTextFile.");
        
        File expectedCsvFile = new File(largeOutputFileName);
        assertTrue("Output CSV file must exist.", expectedCsvFile.isFile());
        
        BufferedReader brTest = new BufferedReader(new FileReader(expectedCsvFile));
        String header = brTest.readLine().substring(0, 22);
        String csvRow1 = brTest.readLine().substring(0, 48);
        brTest.close();
        
        assertEquals("Output file must be same with right suffix.", output.getAbsolutePath(), largeOutputFileName);
        assertEquals("Output CSV file must contain header..", header, ",Word 0,Word 1,Word 2,");
        assertEquals("Output CSV file must contain row with CSV data.", csvRow1, "Sentence 1, adipiscing, amet, consectetur, dolor");
    }

}