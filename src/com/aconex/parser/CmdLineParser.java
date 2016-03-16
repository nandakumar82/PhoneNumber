package com.aconex.parser;


import com.aconex.constants.DictionaryEnum;
import com.aconex.dictionary.Dictionary;
import com.aconex.main.PhoneNumberParser;

import java.util.List;
import java.util.Scanner;

/**
 * This is the class that will analyze inputs from the command line.
 */
public class CmdLineParser extends NumberParser {

    /**
     * The constructor of the NumberParser class.
     *
     * @param oDictionary The dictionary to be used to analyze the numbers.
     */
    public CmdLineParser(Dictionary oDictionary) {
        super(oDictionary);
    }

    /**
     * Starts up the STDIN to receive input.
     */
    public void analyzeNumbers() {
        Scanner oScanner = new Scanner(System.in);
        List<String> oResult;
        String sLine;

        System.out.println("PHONE NUMBER ANALYZER");
        System.out.println("\"Looks for words in your phone number!\"");
        System.out.println("(Type \"" + DictionaryEnum.STR_EXIT.getValue() + "\" if you want to end.)\n");

        while (true) {
            System.out.print("Input number: ");
            sLine = oScanner.nextLine().trim();

            if (DictionaryEnum.STR_EXIT.getValue().startsWith(sLine)) {
                // End the application.
                System.out.println("Exiting Phone Number Parser.");
                break;
            } else {
                // Analyze the line.
                oResult = analyzeNumber(sLine);

                // Display the results.
                PhoneNumberParser.displayResults(sLine, oResult);
            }
        }
    }
}
