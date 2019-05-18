package serwisPaczek.utils;

import org.springframework.stereotype.Service;

@Service
public class TextFieldUtils {

    public static boolean isCorrect(String tf) {
        if (tf == null || tf.length() == 0) {
            return false;
        }

        for (int i = 0; i < tf.length(); i++) {
            if (Character.isWhitespace(tf.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isCorrectAndNoDigit(String tf) {

            if (tf == null || tf.length() == 0) {
                return false;
            }

            for (int i = 0; i < tf.length(); i++) {
                if (Character.isWhitespace(tf.charAt(i))) {
                    return false;
                }
            }
        for(char c : tf.toCharArray()){
            if(Character.isDigit(c)) return false;
        }
        return true;
    }

    public static boolean isCorrectAndOnlyDigit(String tf) {

        if (tf == null || tf.length() == 0) {
            return false;
        }

        for (int i = 0; i < tf.length(); i++) {
            if (Character.isWhitespace(tf.charAt(i))) {
                return false;
            }
        }

        for(char c : tf.toCharArray()){
            if(!(Character.isDigit(c))) return false;
        }
        return true;
    }

}
