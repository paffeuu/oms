package exception;

import java.io.IOException;

public class IncorrectLineException extends IOException {
    public IncorrectLineException(String line) {
        super(line);
    }
}
