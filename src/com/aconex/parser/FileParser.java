package com.aconex.parser;

import com.aconex.constants.DictionaryEnum;
import com.aconex.dictionary.Dictionary;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the class that will analyze the numbers found in a file.
 */
public class FileParser extends NumberParser {
    /**
     * Constructor for FileParser.
     *
     * @param dictionary The dictionary to user for parsing.
     */
    public FileParser(Dictionary dictionary) {
        super(dictionary);
    }

    /**
     * Analyzes the numbers found in the filename.
     *
     * @param sFileName The filename of the input file.
     */
    public Map<String, List<String>> analyzeFile(String sFileName) throws FileNotFoundException {
        File file = new File(DictionaryEnum.STR_INPUT_DIRECTORY.getValue() + sFileName);


        BufferedReader bufferedReader;
        FileReader fileReader;
        String sLine;
        Map<String, List<String>> resultMap = new HashMap<>();

        try {
            // Check if the file is readable.
            fileReader = new FileReader(file);

            // Wrap FileReader into a BufferedReader for easier line reading.
            bufferedReader = new BufferedReader(fileReader);

            while ((sLine = bufferedReader.readLine()) != null) {
                if (!sLine.equals(DictionaryEnum.STR_EMPTY.getValue())) {

                    List<String> stringList = analyzeNumber(sLine);
                    if (stringList.size() != 0)
                        resultMap.put(sLine, stringList);
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file: " + sFileName);
            e.printStackTrace();
        }

        return resultMap;
    }
}
