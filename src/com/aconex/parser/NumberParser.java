package com.aconex.parser;


import com.aconex.constants.DictionaryEnum;
import com.aconex.dictionary.Dictionary;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class parses number sequences and determine words based on the
 * dictionary provided.
 */
public class NumberParser {
    /**
     * The dictionary instance for this parser.
     */
    private Dictionary coDictionary;

    /**
     * The constructor of the NumberParser class.
     *
     * @param oDictionary The dictionary to be used to analyze the numbers.
     */
    public NumberParser(Dictionary oDictionary) {
        coDictionary = oDictionary;
    }

    /**
     * This will analyze the string sequence if it is a valid number.
     *
     * @param sLine The line to analyze.
     * @return The resulting analysis of the number.
     */
    public List<String> analyzeNumber(String sLine) {
        String sCleanNumber = cleanNumber(sLine);
        List<String> oResult = new ArrayList<>();
        try {
            // Attempt to parse the number.
            new BigInteger(sCleanNumber);
        } catch (NumberFormatException e) {
            // Unable to parse! Invalid number. Clear the string to avoid processing.
            sCleanNumber = DictionaryEnum.STR_EMPTY.getValue();
        }

        doIterateString(sCleanNumber, oResult);

        return oResult;
    }

    private void doIterateString(String sCleanNumber, List<String> oResult) {
        String sAppend;
        for (int nBegin = 0; nBegin < sCleanNumber.length() - 1; nBegin++) {
            if (nBegin == 1) {
                sAppend = String.valueOf(sCleanNumber.charAt(0)) + DictionaryEnum.STR_HYPHEN.getValue();
            } else if (nBegin > 1) {
                // No two consecutive digits should remain unchanged.
                break;
            } else {
                sAppend = DictionaryEnum.STR_EMPTY.getValue();
            }


            for (int nEnd = nBegin + 1; nEnd <= sCleanNumber.length(); nEnd++)
                doCheckPresenceInDictionary(sCleanNumber, oResult, sAppend, nBegin, nEnd);
        }
    }

    private void doCheckPresenceInDictionary(String sCleanNumber, List<String> oResult, String sAppend, int nBegin, int nEnd) {
        String sTemp;
        List<String> oWordList;
        sTemp = sCleanNumber.substring(nBegin, nEnd);
        oWordList = coDictionary.get(sTemp);

        if (oWordList.isEmpty()) return;
        else {
            if (nEnd != sCleanNumber.length())
                doAnalyzeRestOfNumberSequence(sCleanNumber, oResult, sAppend, nEnd, oWordList);
            else {
                // Reached the end of the number sequence.
                // Loop through the word list.
                oResult.addAll(oWordList.stream().map(sWord -> sAppend + sWord).collect(Collectors.toList()));
            }
        }
    }

    private void doAnalyzeRestOfNumberSequence(String sCleanNumber, List<String> oResult, String sAppend, int nEnd, List<String> oWordList) {
        String sRemaining;
        List<String> oChildResult;
        String sResult;// Not yet at its end.
        // Analyze the rest of the numbers.
        sRemaining = sCleanNumber.substring(nEnd);
        oChildResult = analyzeNumber(sRemaining);

        if (!oChildResult.isEmpty()) {
            for (String sWord : oWordList) {
                // Loop through the word list result.
                for (String sChild : oChildResult) {
                    // Loop through the child results.
                    sResult = sAppend + sWord + DictionaryEnum.STR_HYPHEN.getValue() + sChild;
                    oResult.add(sResult);
                }
            }
        } else if (sRemaining.length() == 1) {
            for (String sWord : oWordList) {
                // Loop through the word list and append the
                // single number.
                sResult = sAppend + sWord + DictionaryEnum.STR_HYPHEN.getValue() + sRemaining;

                oResult.add(sResult);
            }
        }
    }

    /**
     * Cleans the number of any invalid characters: punctuation and white
     * spaces.
     */
    protected String cleanNumber(String sNumber) {
        // Remove all white spaces.
        sNumber = sNumber.replaceAll("\\p{Z}", DictionaryEnum.STR_EMPTY.getValue());

        // Remove all punctuation.
        sNumber = sNumber.replaceAll("\\p{P}", DictionaryEnum.STR_EMPTY.getValue());

        return sNumber;
    }
}
