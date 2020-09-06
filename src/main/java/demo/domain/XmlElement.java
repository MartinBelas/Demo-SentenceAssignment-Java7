package demo.domain;

import java.util.ArrayList;
import java.util.List;

public class XmlElement implements Convertible {

    private List<String> words;

    public XmlElement(List<String> words) {
		this.words =  new ArrayList<>(words);
	}

	/**
     * Prepares (lazy executes) and provides sorted List of all words in the sentence.
     *
     * @return deep copy of the List of all words of the sentence
     */
    public List<String> getWords() {
        return new ArrayList<>(this.words);
    }
    
    public String toString() {
    	
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
