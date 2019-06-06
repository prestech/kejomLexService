package com.kejom.lex.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kejom.lex.model.Lexicon;

/******************************************************************************
 * This class is an implementation of the SQLITE database resources through
 * which other parts of this activity will use to access the database
 *
 * This class is a Singleton class, which means it is implemented to ensure that
 * only one instance of it can be instantiated at any time in the life cycle of
 * the application.
 *
 * Created by asohm on 4/23/2017. 47439
 */
public class DataSource {

	/******************************************************************************
	 * This function extracts the components of a lexicon and create a lexicon
	 * object from it. it returns the lexicon object
	 * 
	 * @param lineOfData
	 * @return
	 */
	private static Lexicon extractLexiconTokens(String lineOfData) {

		int index = 0;
		char charAtIndex = lineOfData.charAt(index);
		String variantPluralPartOfSpeech[] = new String[6];

		variantPluralPartOfSpeech[0] = "";
		while (charAtIndex != '[') {
			variantPluralPartOfSpeech[0] = variantPluralPartOfSpeech[0] + charAtIndex;
			index++;
			charAtIndex = lineOfData.charAt(index);
		} // while Ends
		index++;
		charAtIndex = lineOfData.charAt(index);

		// System.out.println(charAtIndex+ " kejom:"+ variantPluralPartOfSpeech[0]);

		variantPluralPartOfSpeech[1] = "";
		while (charAtIndex != ']') {
			variantPluralPartOfSpeech[1] = variantPluralPartOfSpeech[1] + charAtIndex;
			index++;
			charAtIndex = lineOfData.charAt(index);
		} // while Ends
		index++;
		charAtIndex = lineOfData.charAt(index);

		// System.out.println(charAtIndex + " Phonetics:"+
		// variantPluralPartOfSpeech[1]);
		String lexiconData = "";
		boolean hasPlural = false;
		boolean hasVariant = false;

		while (charAtIndex != ':' && charAtIndex != '.' && charAtIndex != '?') {

			lexiconData = lexiconData + charAtIndex;
			index++;
			charAtIndex = lineOfData.charAt(index);

		} // while Ends
		index++;
		charAtIndex = lineOfData.charAt(index);

		/********************************************************
		 *
		 */
		if (lexiconData.equalsIgnoreCase("variant:") || lexiconData.toLowerCase().contains("variant")) {
			hasPlural = true;
			variantPluralPartOfSpeech[2] = "";
			while (charAtIndex != '.') {
				variantPluralPartOfSpeech[2] = variantPluralPartOfSpeech[2] + charAtIndex;
				index++;
				charAtIndex = lineOfData.charAt(index);
			} // while Ends
			index++;
			charAtIndex = lineOfData.charAt(index);
			// System.out.println(charAtIndex + " Variant:"+ variantPluralPartOfSpeech[2]);

		} // if Ends

		else if (lexiconData.equalsIgnoreCase("pl:") || lexiconData.toLowerCase().contains("pl")) {
			hasVariant = true;
			variantPluralPartOfSpeech[3] = "";
			while (charAtIndex != '.') {
				variantPluralPartOfSpeech[3] = variantPluralPartOfSpeech[3] + charAtIndex;
				index++;
				charAtIndex = lineOfData.charAt(index);
			} // while Ends
			index++;
			charAtIndex = lineOfData.charAt(index);
			// System.out.println(charAtIndex + " Plural:"+ variantPluralPartOfSpeech[3]);
		} // else if Ends

		if (hasVariant == true || hasPlural == true) {
			variantPluralPartOfSpeech[4] = "";
			while (charAtIndex != '.' && charAtIndex != '?') {
				variantPluralPartOfSpeech[4] = variantPluralPartOfSpeech[4] + charAtIndex;
				index++;
				charAtIndex = lineOfData.charAt(index);
			} // while Ends
			index++;

		} // if Ends
		else {
			variantPluralPartOfSpeech[4] = lexiconData;
		} // else ends
			// System.out.println(charAtIndex + " Part of Speech:"+
			// variantPluralPartOfSpeech[4]);

		variantPluralPartOfSpeech[5] = "";
		while (index < lineOfData.length() - 1) {
			variantPluralPartOfSpeech[5] = variantPluralPartOfSpeech[5] + charAtIndex;
			index++;
			charAtIndex = lineOfData.charAt(index);
		} // while Ends

		// System.out.println(""+variantPluralPartOfSpeech[5]);
		if (variantPluralPartOfSpeech[5].length() < 1) {
			System.out.println(charAtIndex + " Part of Speech:" + variantPluralPartOfSpeech[4]);
			System.err.println("Exception: No english word Captured for " + variantPluralPartOfSpeech[0]);
			System.out.println("******************************");
		}
		index = 0;

		Lexicon lexicon = new Lexicon(variantPluralPartOfSpeech);

		return lexicon;
	}// extractLexiconTokens() Ends

	/************************************************************
	 * This function reads the lexicon data from the text file, located in the raw
	 * folder, and rights it to the SQLITE data base.
	 *
	 * It calls the extractLexiconTokens() function to created Lexicon objects from
	 * the data
	 * @throws JsonProcessingException 
	 */
	public static String pkgLexToJsonListObj() throws JsonProcessingException {

		HashMap<String,Lexicon> listOfLex = new HashMap<>();
		
		InputStream inputStream = new DataSource().getClass().getClassLoader().getResourceAsStream("kejom_english.txt");
		
		Scanner scanner  = new Scanner(inputStream);
		int lexId = 0; 
		while (scanner.hasNextLine()) {

			Lexicon lexicon = extractLexiconTokens(scanner.nextLine());

			lexicon.setLexiconId(lexId);
			
			if (lexicon.getEnglishWord().charAt(0) == '.') {
				lexicon.setEnglishWord(lexicon.getEnglishWord().substring(1));
			} // if Ends

			listOfLex.put(lexicon.getLexiconId()+"", lexicon);
			
			lexId++;
			
		} // while() Ends
		
		//System.out.print(listOfLex);
		
		String value = new ObjectMapper().writeValueAsString(listOfLex);
		
		return value;

	}// readData(String Filename) Ends

}// Database Class