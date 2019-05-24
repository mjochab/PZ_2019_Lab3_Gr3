package serwisPaczek.utils;

import org.springframework.stereotype.Service;

@Service
public class TextFieldUtils {

    // to test
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
