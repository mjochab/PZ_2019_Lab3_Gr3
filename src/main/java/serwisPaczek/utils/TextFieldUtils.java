package serwisPaczek.utils;

import org.springframework.stereotype.Service;

@Service
public class TextFieldUtils {

    /** Method is checking correctness of string
     * @param tf - text to check
     * @return true if string is not null, length is not 0 and dont have white spaces
     */
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

}
