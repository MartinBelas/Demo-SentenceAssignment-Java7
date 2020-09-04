package demo.converters;

import demo.domain.Sentence;
import demo.domain.XmlElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Converts text file to XML file.
 * The text is parsed, broken into sentences and words and the words are sorted.
 * The sorted words of the sentences are places into a XML file.
 */
public class SentenceToXmlConverter implements Converter<Sentence, XmlElement> {

	@Override
	public XmlElement convertItem(Sentence sentence) {
        
		if (sentence == null) {
            return null;
        }
		
		return new XmlElement(new ArrayList<>(sentence.getWords()));
	}

	@Override
	public File convertFile(File inputFile, String outputFileName) {

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
            		pw.println(convertItem(sentence).toString());
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
}


