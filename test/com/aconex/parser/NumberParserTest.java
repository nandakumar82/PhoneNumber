package com.aconex.parser;

import com.aconex.constants.DictionaryEnum;
import com.aconex.dictionary.Dictionary;
import com.aconex.dictionary.DictionaryReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nanda on 15/03/2016.
 */
public class NumberParserTest {

    private DictionaryReader oReader;


    @Before
    public void setUp() throws Exception {
        oReader = getDictionaryReader();

    }

    @Test
    public void test_Clean_Number() throws Exception {
        NumberParser numberParser = new NumberParser(new Dictionary());
        String s = numberParser.cleanNumber("77/3!7#36%82%84&6*6(6)6_3");
        assertEquals("773736828466663", s);
    }

    @Test
    public void test_Analyze_Number1() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("24247.66");//24247.66
        List<String> oExpected = new ArrayList<>();

        oExpected.add("CHAIR-NO");
        oExpected.add("CHAIR-ON");
        oExpected.add("2-GAG-7-NO");
        oExpected.add("2-GAG-7-ON");
        oExpected.add("2-HAIR-NO");
        oExpected.add("2-HAIR-ON");

        assertEquals(oExpected.size(), oResult.size());

        // Remove the item from the result list.
        oExpected.stream().filter(sExpected -> oResult.contains(sExpected)).forEach(oResult::remove);

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number2() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("42472.66");
        List<String> oExpected = new ArrayList<>();

        oExpected.add("HAIR-2-ON");
        oExpected.add("HAIR-2-NO");
        oExpected.add("4-AIR-2-ON");
        oExpected.add("4-AIR-2-NO");
        oExpected.add("HAIR-AN-6");
        oExpected.add("GAG-7-AN-6");
        oExpected.add("4-AIR-AN-6");

        assertEquals(oExpected.size(), oResult.size());

        oExpected.stream().filter(sExpected -> oResult.contains(sExpected)).forEach(oResult::remove);

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number3() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("24247.663");
        List<String> oExpected = new ArrayList<>();

        oExpected.add("CHAIR-NO-3");
        oExpected.add("CHAIR-ON-3");
        oExpected.add("CHAIR-6-ME");
        oExpected.add("2-GAG-7-NO-3");
        oExpected.add("2-GAG-7-ON-3");
        oExpected.add("2-HAIR-NO-3");
        oExpected.add("2-HAIR-ON-3");
        oExpected.add("2-HAIR-6-ME");

        assertEquals(oExpected.size(), oResult.size());

        // Remove the item from the result list.
        oExpected.stream().filter(sExpected -> oResult.contains(sExpected)).forEach(oResult::remove);

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number4() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("263-464-242-5.53-643");
        List<String> oExpected = new ArrayList<>();

        oExpected.add("CODING-CHALLENGE");

        assertEquals(oExpected.size(), oResult.size());

        // Remove the item from the result list.
        oExpected.stream().filter(sExpected -> oResult.contains(sExpected)).forEach(oResult::remove);

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number5() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("89-663");
        List<String> oExpected = new ArrayList<>();

        oExpected.add("TWO-ME");
        oExpected.add("8-ZONE");

        assertEquals(oExpected.size(), oResult.size());

        // Remove the item from the result list.
        oExpected.stream().filter(sExpected -> oResult.contains(sExpected)).forEach(oResult::remove);

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number6() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("77/3!7#36%82%84&6*6(6)6_3");
        List<String> oExpected = new ArrayList<>();

        oExpected.add("PRESENTATION-NO-3");
        oExpected.add("PRESENTATION-ON-3");
        oExpected.add("PRESENTATION-6-ME");

        assertEquals(oExpected.size(), oResult.size());

        // Remove the item from the result list.
        oExpected.stream().filter(sExpected -> oResult.contains(sExpected)).forEach(oResult::remove);

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number_Invalid1() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("a5483728873");

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number_Invalid2() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("54837a28873");

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number_Invalid3() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("548$3728873");

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number_Invalid4() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("5483728873+");

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number_Invalid5() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("54831728128373");

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number_Invalid6() {
        DictionaryReader oReader = getDictionaryReader();
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("^5483728873");

        assertEquals(0, oResult.size());
    }


    @Test
    public void test_Analyze_Number_Invalid7() {
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("^5483728873");

        assertEquals(0, oResult.size());
    }

    @Test
    public void test_Analyze_Number_Invalid8() {
        NumberParser oNumParse = new NumberParser(oReader.getDictionary());
        List<String> oResult = oNumParse.analyzeNumber("TEST");

        assertEquals(0, oResult.size());
    }

    protected DictionaryReader getDictionaryReader() {
        return new DictionaryReader(DictionaryEnum.STR_DICTIONARY_FILENAME.getValue());
    }


}