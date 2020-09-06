package demo.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//import demo.domain.Sentence.SortIgnoreCase;

/**
 * This immutable class consumes a String sentence when created (in constructor)
 * and provides case insensitive sorted List of words in the sentence.
 *
 * The List of words contains all words from the sentence,
 * so it can contain some duplicities if there are more occurrences of a word.
 */
public class Sentence implements Convertible {
	
	public final static String SENTENCE_DELIMITERS = "[.!?]";

	private final String sentence;
    private List<String> words;
    private boolean isWordsPrepared = false;

    public Sentence(String sentence) {
        if (sentence != null && !sentence.isEmpty()) {
            sentence.trim();
        }
        this.sentence = sentence;
    }
    
    @Override
    public String getConvertedToString() {
        return this.sentence;
    }

    /**
     * Prepares (lazy executes) and provides sorted List of all words in the sentence.
     *
     * @return deep copy of the List of all words of the sentence
     */
    public List<String> getWords() {
        if (!this.isWordsPrepared) {
            prepareWords();
        }
        List<String> clonedWords = new ArrayList<>(this.words);
        return clonedWords;
    }

    private void prepareWords() {

        String withoutCommas = this.sentence.replaceAll(",","").replace("\n", "").replace("\r", "");
        this.words= Arrays.asList(withoutCommas.split("\\s+"));
    	if (this.words == null) {
    		this.words = Collections.emptyList();
    	}
    	
    	Collections.sort(this.words, String.CASE_INSENSITIVE_ORDER); 
    	
    	List<String> nonBlank = new ArrayList<String>();
    	for (String w: this.words) {
    	    if (!w.trim().isEmpty()) {
    	        nonBlank.add(w);
    	    }
    	}
    	this.words = nonBlank;

        this.isWordsPrepared = true;
    }
}
