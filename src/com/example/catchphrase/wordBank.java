package com.example.catchphrase;

import java.util.ArrayList;
import java.util.Collections;

public class wordBank {

	private ArrayList<String> words = new ArrayList<String>();
	
	public wordBank() {
		String[] temp_words = {"dog", "cat", "ticky tack", "boat", "Cleveland"};
		for(String word: temp_words) {
			words.add(word);
		}
		Collections.shuffle(words);
	}
	
	public String getPhrase() {
		int last = words.size();
		if(last != 0) {
			String phrase = words.get(last-1);
			words.remove(phrase);
			return phrase;
		}
		else {
			return "no more phrases";
		}
	}
	
}
