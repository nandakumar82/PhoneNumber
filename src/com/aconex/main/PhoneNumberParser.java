package com.aconex.main;


import com.aconex.constants.DictionaryEnum;
import com.aconex.dictionary.Dictionary;
import com.aconex.dictionary.DictionaryReader;
import com.aconex.parser.CmdLineParser;
import com.aconex.parser.FileParser;
import com.aconex.parser.NumberParser;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class will hold the main class of the PhoneNumberParser.
 */
public class PhoneNumberParser {

    /**
     * @param args
     */
    public static void main(String[] args) {
        DictionaryReader oDictionaryReader;
        Dictionary oDict;
        NumberParser oNumParser;
        FileParser oFileParser;

        Map<String, List<String>> oResults;
        List<String> oValues;

        String sDictionaryName = null;
        String sCurrent;

        int nArgsLength;
        int i = 0;

        if (args.length > 0) {
            if (args[i].equals(DictionaryEnum.STR_DICTIONARY_OPTION.getValue())) {
                i++;
                sDictionaryName = args[i++];
            }
            nArgsLength = args.length - i;
            oDictionaryReader = new DictionaryReader(sDictionaryName);
            oDict = oDictionaryReader.getDictionary();

            if (nArgsLength > 0) {
                // Remaining arguments could either be a series of numbers, a
                // series of filenames, or both.
                oNumParser = new NumberParser(oDict);
                oFileParser = new FileParser(oDict);

                for (; i < args.length; i++) {
                    sCurrent = args[i];

                    try {
                        oResults = oFileParser.analyzeFile(sCurrent);

                        displayResults(oResults);
                    } catch (FileNotFoundException e) {
                        // It's not a filename. It might be a number sequence.
                        oValues = oNumParser.analyzeNumber(sCurrent);

                        displayResults(sCurrent, oValues);
                    }
                }
            } else {
                // Invoke command line to gather inputs from the user.
                doFetchFromCommandLine(oDict);
            }
        } else {
            // Invoke command line to gather inputs from the user.
            doFetchFromCommandLine(new DictionaryReader().getDictionary());
        }
    }


    /**
     * @param oDict
     */
    protected static void doFetchFromCommandLine(Dictionary oDict) {
        CmdLineParser oCmdLine = new CmdLineParser(oDict);

        oCmdLine.analyzeNumbers();
    }

    /**
     * Displays the results.
     *
     * @param sSequence The number sequence in string format.
     * @param oValues   The resulting words of the number sequence.
     */
    public static void displayResults(String sSequence, List<String> oValues) {
        Map<String, List<String>> oResults = new HashMap<>();
        oResults.put(sSequence, oValues);

        displayResults(oResults);
    }

    /**
     * Displays the results. Traverses through the map and displays all
     * information.
     *
     * @param oResults The map of the results.
     */
    public static void displayResults(Map<String, List<String>> oResults) {
        Set<String> oKeys = oResults.keySet();
        List<String> oValues;
        int i;

        for (String sKey : oKeys) {
            i = 1;

            oValues = oResults.get(sKey);
            if (oValues.size() > 1) {
                System.out.println("Results for " + sKey + " are: ");
                for (String sValue : oValues) {
                    System.out.println((i++) + ". " + sValue);
                }
            } else if (oValues.size() == 1) {
                System.out.println("The result for " + sKey + " is: " + oValues.get(0));
            } else {
                System.out.println("No words were found in the sequence: " + sKey);
            }

            // Insert a line for separation and readability.
            System.out.println();
        }
    }
}
