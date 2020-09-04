package demo.converters.strategy;

import demo.domain.Sentence;
import demo.providers.FileProvider;
import demo.providers.SentenceFileProvider;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class SentenceToXmlConverterStrategyIT {

    private static Logger logger = LoggerFactory.getLogger(SentenceToXmlConverterStrategyIT.class);

    SentenceToXmlConverterStrategy strategy;

	File integrationTestResources = new File("src/integrationTest/resources");
	String resourcePath = integrationTestResources.getAbsolutePath();
	

    @Before
    public void setUp() {
    	strategy = new SentenceToXmlConverterStrategy();
    }

    @Test(expected = Test.None.class /* no exception expected */)
    public void testConvertFromSmallTextFile() throws Exception {
    	
    	final String smallInputFileName = resourcePath + "\\small.in";
        final String smallOutputFileName = resourcePath + "\\small.xml";
        
    	logger.info("Convert from small file: {}", smallInputFileName);
    	logger.info("Convert to small xml file: {}", smallOutputFileName);
    	
    	FileProvider<Sentence> inputFileProvider = new SentenceFileProvider();

        strategy.convert(inputFileProvider.getFile(smallInputFileName), smallOutputFileName);
        
        logger.info("Finished stream conversion in testConvertFromSmallTextFile.");
        
        File expectedXmlFile = new File(smallOutputFileName);
        assertTrue("Output XML file must exist.", expectedXmlFile.isFile());
        
        
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        SAXParser saxLevel1 = spf.newSAXParser();
        XMLReader parser = saxLevel1.getXMLReader();
        parser.parse(smallOutputFileName);
        logger.info("Test result: " + smallOutputFileName + " parsed without errors");
    }

    @Test
    public void testConvertFromLargeTextFile() throws Exception {
    	
    	final String largeInputFileName = resourcePath + "\\large.in";
        final String largeOutputFileName = resourcePath + "\\large.xml";
        
    	logger.info("Convert from large file: {}", largeInputFileName);
    	logger.info("Convert to large xml file: {}", largeOutputFileName);
    	
    	FileProvider<Sentence> inputFileProvider = new SentenceFileProvider();

        strategy.convert(inputFileProvider.getFile(largeInputFileName), largeOutputFileName);
        
        logger.info("Finished stream conversion in testConvertFromSmallTextFile.");
        
        File expectedXmlFile = new File(largeOutputFileName);
        assertTrue("Output XML file must exist.", expectedXmlFile.isFile());
        
        
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        SAXParser saxLevel1 = spf.newSAXParser();
        XMLReader parser = saxLevel1.getXMLReader();
        parser.parse(largeOutputFileName);
        logger.info("Test result: " + largeOutputFileName + " parsed without errors");
    }

}