package demo.converters.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.domain.Sentence;
import demo.domain.XmlElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SentenceToXmlConverterStrategy implements ConverterStrategy {

    private static Logger logger = LoggerFactory.getLogger(SentenceToXmlConverterStrategy.class);

    private final static String OUTPUT_FILE_SUFFIX = "xml";

    @Override
    public File convert(File inputFile) {
        
        logger.info("CONVERT String Sentence to XML ...");
        
        String outputFileName = getOutputFileName(inputFile.getAbsolutePath());

        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(inputFile);
            sc = new Scanner(inputStream, "UTF-8");
            sc.useDelimiter(Sentence.SENTENCE_DELIMITERS);

            PrintWriter pw = new PrintWriter(outputFileName, "UTF-8");

            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>");
            pw.println("<text>");

            while (sc.hasNext()) {
                Sentence sentence = new Sentence(sc.next());
                if (!sentence.getWords().isEmpty()) {
                    pw.println(new XmlElement(sentence.getWords()).getConvertedToString());
                }
            }

            pw.println("</text>");
            pw.close();

            return new File(outputFileName);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
        return null;
    }

    private static String getOutputFileName(String inputFileName) {

        int i = inputFileName.lastIndexOf('.');
        if (i > 0) {
            inputFileName = inputFileName.substring(0, i);
        }

        return inputFileName + "." + OUTPUT_FILE_SUFFIX;
    }
}
