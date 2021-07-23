package usersystem.demo.units;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileUnit {
    String[] readFile(String path) throws IOException;
}
