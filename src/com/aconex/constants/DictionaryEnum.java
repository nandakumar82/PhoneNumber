package com.aconex.constants;

import java.util.ArrayList;

/**
 * Created by Nanda on 13/03/2016.
 */
public enum DictionaryEnum {

    STR_DICTIONARY_FILENAME("CodingChallengeDictionary.txt"), STR_EMPTY(""), STR_TWO("ABC"), STR_THREE("DEF"), STR_FOUR("GHI"), STR_FIVE("JKL"), STR_SIX("MNO"), STR_SEVEN("PQRS"), STR_EIGHT("TUV"), STR_NINE("WXYZ"), STR_HYPHEN("-"), STR_EXIT("EXIT"), STR_DICTIONARY_DIRECTORY("dictionary/"), STR_INPUT_DIRECTORY("input/"), STR_DICTIONARY_OPTION("-d");

    public static ArrayList<String> EMPTY_LIST = new ArrayList<>();
    private String value;

    DictionaryEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
