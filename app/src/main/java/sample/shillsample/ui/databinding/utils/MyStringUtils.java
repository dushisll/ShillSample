package sample.shillsample.ui.databinding.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by we on 2016/12/19.
 */

public class MyStringUtils {
    private static Pattern sWordPattern = Pattern.compile("\\w+");

    public static String capitalizeSentence(final String sentence) {
        String result = sentence;
        Matcher matcher = sWordPattern.matcher(result);
        while (matcher.find()) {
            String word = matcher.group();
            result = result.replace(matcher.group(), capitalize(word));
        }
        return result;
    }

    public static String capitalize(final String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }
}
