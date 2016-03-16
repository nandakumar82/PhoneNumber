package com.aconex.parser;

import com.aconex.constants.DictionaryEnum;
import com.aconex.dictionary.DictionaryReader;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Nanda on 15/03/2016.
 */

public class FileParserTest {

    @Test
    public void testAnalyzeNumbers1() {
        DictionaryReader oReader = new DictionaryReader(DictionaryEnum.STR_DICTIONARY_FILENAME.getValue());
        FileParser oFileParser = new FileParser(oReader.getDictionary());
        Map<String, List<String>> oResult = null;
        Map<String, List<String>> oExpected = new HashMap<>();

        List<String> oTempList = new ArrayList<>();

        oTempList.add("CHAIR-NO-3");
        oTempList.add("CHAIR-ON-3");
        oTempList.add("CHAIR-6-ME");
        oTempList.add("2-GAG-7-NO-3");
        oTempList.add("2-GAG-7-ON-3");
        oTempList.add("2-HAIR-NO-3");
        oTempList.add("2-HAIR-ON-3");
        oTempList.add("2-HAIR-6-ME");
        oExpected.put("24247.663", oTempList);

        oTempList = new ArrayList<>();
        oTempList.add("GAG-7-AN-6");
        oTempList.add("HAIR-AN-6");
        oTempList.add("HAIR-2-NO");
        oTempList.add("HAIR-2-ON");
        oTempList.add("4-AIR-AN-6");
        oTempList.add("4-AIR-2-NO");
        oTempList.add("4-AIR-2-ON");
        oExpected.put("42472.66", oTempList);

        oTempList = new ArrayList<>();
        oTempList.add("CHAIR-NO");
        oTempList.add("CHAIR-ON");
        oTempList.add("2-GAG-7-NO");
        oTempList.add("2-GAG-7-ON");
        oTempList.add("2-HAIR-NO");
        oTempList.add("2-HAIR-ON");
        oExpected.put("24247.66", oTempList);

        oTempList = new ArrayList<>();
        oTempList.add("CODING-CHALLENGE");
        oExpected.put("263-464-242-5.53-643", oTempList);

        oTempList = new ArrayList<>();
        oTempList.add("TWO-ME");
        oTempList.add("8-ZONE");
        oExpected.put("89-663", oTempList);

        oTempList = new ArrayList<>();
        oTempList.add("PRESENTATION-NO-3");
        oTempList.add("PRESENTATION-ON-3");
        oTempList.add("PRESENTATION-6-ME");
        oExpected.put("77/3!7#36%82%84&6*6(6)6_3", oTempList);

        oTempList = DictionaryEnum.EMPTY_LIST;
        oExpected.put("a5483728873", oTempList);
        oExpected.put("54837a28873", oTempList);
        oExpected.put("548$3728873", oTempList);

        try {
            oResult = oFileParser.analyzeFile("Numbers.txt");
        } catch (FileNotFoundException e) {
            fail("No exception should have occurred!");
        }

        assertNotNull(oResult);

        Set<String> oKeys = oResult.keySet();

        for (String sKey : oKeys) {
            assertTrue(oExpected.containsKey(sKey));

            assertEquals(oExpected.get(sKey), oResult.get(sKey));
        }
    }

}