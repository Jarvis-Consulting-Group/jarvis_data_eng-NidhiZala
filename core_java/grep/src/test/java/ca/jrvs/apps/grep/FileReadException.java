package ca.jrvs.apps.grep;

import java.io.IOException;

public class FileReadException extends IOException {
    public FileReadException(String message, Throwable cause){
        super(message, cause);
    }

}
