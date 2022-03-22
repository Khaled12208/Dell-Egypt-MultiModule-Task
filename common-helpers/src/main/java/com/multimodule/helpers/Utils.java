package com.multimodule.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

@SuppressWarnings("unchecked")
public class Utils {


    public Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    public static String getRandomAlphaNumericString(int n) {
        {

            // lower limit for LowerCase Letters
            int lowerLimit = 97;

            // lower limit for LowerCase Letters
            int upperLimit = 122;

            Random random = new Random();

            // Create a StringBuffer to store the result
            StringBuffer r = new StringBuffer(n);

            for (int i = 0; i < n; i++) {

                // take a random value between 97 and 122
                int nextRandomChar = lowerLimit
                        + (int) (random.nextFloat()
                        * (upperLimit - lowerLimit + 1));

                // append a character at the end of bs
                r.append((char) nextRandomChar);
            }

            // return the resultant string
            return r.toString();
        }
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    private static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    static boolean isAlphabaticOrder(String s)
    {
        // length of the string
        int n = s.length();

        // create a character array
        // of the length of the string
        char c[] = new char [n];

        // assign the string
        // to character array
        for (int i = 0; i < n; i++) {
            c[i] = s.charAt(i);
        }

        // sort the character array
        Arrays.sort(c);

        // check if the character array
        // is equal to the string or not
        for (int i = 0; i < n; i++)
            if (c[i] != s.charAt(i))
                return false;

        return true;
    }


}
