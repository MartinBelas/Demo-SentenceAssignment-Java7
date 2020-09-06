package demo.domain;

import java.util.ArrayList;
import java.util.List;

public class XmlElement implements Convertible {

    private List<String> words;

    public XmlElement(List<String> words) {
        List<String> clonedWords = new ArrayList<>(words);
        this.words = clonedWords;
	}

    public String getConvertedToString() {
    	
    	String openingTag = new StringBuilder()
    		.append("<sentence> \r\n")
    		.toString();
    	
    	String body = "";
    	for (String w : this.words) {
    		body += "    <word>" + w + "</word> \r\n";
    	}
    	
    	String closingTag = new StringBuilder()
    			.append("</sentence>")
    			.toString();
    	
    	return new StringBuilder()
    			.append(openingTag)
    			.append(body)
    			.append(closingTag)
    			.toString();
    }
}
