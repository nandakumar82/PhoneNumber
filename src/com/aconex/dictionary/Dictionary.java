package com.aconex.dictionary;

import com.aconex.constants.DictionaryEnum;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Nanda on 13/03/2016.
 */
public class Dictionary extends LinkedHashMap<String, List<String>> {
    /**
     * Returns the value to which the specified key is mapped in this identity
     * hash map, or <i>an empty list</i> if the map contains no mapping for this
     * key.
     *
     * @param key the key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or <i>an
     * empty list</i> if the map contains no mapping for this key.
     */
    public List<String> get(Object key) {
        List<String> oValue = super.get(key);

        if (oValue == null) {
            oValue = DictionaryEnum.EMPTY_LIST;
        }

        return oValue;
    }
}
