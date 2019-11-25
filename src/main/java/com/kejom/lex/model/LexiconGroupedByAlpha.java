package com.kejom.lex.model;

import java.util.ArrayList;
import java.util.List;

public class LexiconGroupedByAlpha {

	private List<Lexicon> listOfLexicon;
	private String alphabet;
	
	public LexiconGroupedByAlpha(String alphabet) {
		listOfLexicon = new ArrayList<>();
		this.alphabet = alphabet;
	}
	
	public List<Lexicon> getListOfLexicon() {
		return listOfLexicon;
	}
	public void setListOfLexicon(List<Lexicon> listOfLexicon) {
		this.listOfLexicon = listOfLexicon;
	}
	public String getAlphabet() {
		return alphabet;
	}
		
}
