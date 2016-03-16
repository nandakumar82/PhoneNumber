package com.aconex.dictionary;

import com.aconex.constants.DictionaryEnum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.aconex.constants.DictionaryEnum.STR_DICTIONARY_FILENAME;

/**
 * This class reads the dictionary and determines every words' number sequences.
 */
public class DictionaryReader {
    /**
     * This will hold the number sequences and their corresponding words based
     * on the dictionary.
     */
    private Dictionary dictionary;

    /**
     * Initializes the DictionaryReader class with the default dictionary.
     */
    public DictionaryReader() {
        this(null);
    }

    /**
     * Initializes the DictionaryReader class based on the filename provided.
     * The dictionary file should be located inside the <strong>dict</strong>
     * folder.
     *
     * @param sDictionaryName The dictionary's filename.
     */
    public DictionaryReader(String sDictionaryName) {
        if (sDictionaryName == null || sDictionaryName.equals(DictionaryEnum.STR_EMPTY.getValue())) {
            sDictionaryName = STR_DICTIONARY_FILENAME.getValue();
        }

        File oFile = new File(DictionaryEnum.STR_DICTIONARY_DIRECTORY.getValue() + sDictionaryName).getAbsoluteFile();

        BufferedReader oReader;
        FileReader oFileReader;
        try {
            // Check if the file is readable.
            oFileReader = new FileReader(oFile);

            // Wrap FileReader into a BufferedReader for easier line reading.
            oReader = new BufferedReader(oFileReader);
            analyzeDictionary(oReader);
        } catch (FileNotFoundException e) {
            System.err.println("Error occurred while accessing the file: " + sDictionaryName);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file: " + sDictionaryName);
            e.printStackTrace();
        }

    }

    /**
     * This will analyze the dictionary and populate the dictionary map.
     *
     * @param oReader The BufferedReader reading the dictionary.
     */
    public void analyzeDictionary(BufferedReader oReader) throws IOException {
        // Initialize the dictionary to be populated.
        dictionary = new Dictionary();

        String sWord;

        if (oReader != null) while ((sWord = oReader.readLine()) != null) {
            // Trim the line of leading and trailing white spaces.
            sWord = sWord.trim();

            if (doEliminateWordsStartingWithHash(sWord)) continue;

            if (!sWord.equals(DictionaryEnum.STR_EMPTY.getValue())) {
                convertDictionaryWordToInteger(sWord);
            }
        }
    }

    protected boolean doEliminateWordsStartingWithHash(String sWord) {
        return sWord.startsWith("#");
    }

    /**
     * Retrieves the dictionary generated from initialization.
     *
     * @return The dictionary generated.
     */
    public Dictionary getDictionary() {
        return dictionary;
    }

    /**
     * Adds the word into the dictionary with its number sequence as its value.
     *
     * @param sWord The word to be added.
     */
    void convertDictionaryWordToInteger(String sWord) {
        String sCurrentChar;
        String integerValue;

        // Initialize the integer string buffer.
        StringBuilder integerBuilder = new StringBuilder();
        sWord = sWord.toUpperCase();

        // Traverse through the word
        for (int i = 0; i < sWord.length(); i++) {
            sCurrentChar = String.valueOf(sWord.charAt(i));



            doAppendInteger(sCurrentChar, integerBuilder);
        }

        // Convert the number sequence into an integer and add them
        // into the dictionary.
        integerValue = integerBuilder.toString();
        checkWordPresentAndStore(sWord, integerValue);


    }

    private void doAppendInteger(String sCurrentChar, StringBuilder integerBuilder) {
        if (DictionaryEnum.STR_TWO.getValue().contains(sCurrentChar)) {
            integerBuilder.append(2);
        } else if (DictionaryEnum.STR_THREE.getValue().contains(sCurrentChar)) {
            integerBuilder.append(3);
        } else if (DictionaryEnum.STR_FOUR.getValue().contains(sCurrentChar)) {
            integerBuilder.append(4);
        } else if (DictionaryEnum.STR_FIVE.getValue().contains(sCurrentChar)) {
            integerBuilder.append(5);
        } else if (DictionaryEnum.STR_SIX.getValue().contains(sCurrentChar)) {
            integerBuilder.append(6);
        } else if (DictionaryEnum.STR_SEVEN.getValue().contains(sCurrentChar)) {
            integerBuilder.append(7);
        } else if (DictionaryEnum.STR_EIGHT.getValue().contains(sCurrentChar)) {
            integerBuilder.append(8);
        } else if (DictionaryEnum.STR_NINE.getValue().contains(sCurrentChar)) {
            integerBuilder.append(9);
        }
    }

    private void checkWordPresentAndStore(String sWord, String integerValue) {
        // Check if the number has already been encountered.
        if (dictionary.containsKey(integerValue)) {
            List<String> oWordList = dictionary.get(integerValue);

            if (!oWordList.contains(sWord)) {
                // The word is new. Add the word into the list.
                oWordList.add(sWord);
            }
        } else {
            // Add the number and its word to the map.
            List<String> oWordList = new ArrayList<>();
            oWordList.add(sWord);
            dictionary.put(integerValue, oWordList);
        }
    }
}
