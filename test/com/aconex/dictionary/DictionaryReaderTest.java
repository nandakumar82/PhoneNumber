package com.aconex.dictionary;

import com.aconex.constants.DictionaryEnum;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nanda on 13/03/2016.
 */
public class DictionaryReaderTest {
    private static final String DICTIONARY_FILE = "CodingChallengeDictionary.txt";
    DictionaryReader dictionaryReader;


    @Test
    public void test_Null_File() throws Exception {
        dictionaryReader = new DictionaryReader(null);
        assertNotNull(dictionaryReader);
    }

    @Test
    public void test_Empty_File() throws Exception {
        dictionaryReader = new DictionaryReader("");
        assertNotNull(dictionaryReader);
    }

    @Test
    public void test_Valid_File() throws Exception {
        dictionaryReader = new DictionaryReader(DICTIONARY_FILE);
        assertNotNull(dictionaryReader);
    }

    @Test
    public void testAnalyzeDictionary() {
        DictionaryReader oReader = new DictionaryReader(DictionaryEnum.STR_DICTIONARY_FILENAME.getValue());
        assertNotNull(oReader.getDictionary());
    }

    @Test
    public void test_Eliminate_Dictionary_Value_With_Hash() throws Exception {
        DictionaryReader oReader = new DictionaryReader(DictionaryEnum.STR_DICTIONARY_FILENAME.getValue());
        boolean b = oReader.doEliminateWordsStartingWithHash("#TEST");
        assertTrue("The value contains # and will not be considered",b);

    }


}