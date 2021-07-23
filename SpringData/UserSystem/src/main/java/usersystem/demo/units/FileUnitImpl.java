package usersystem.demo.units;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;
@Component
public class FileUnitImpl implements FileUnit  {
    @Override
    public String[] readFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file)
        );
        Set<String> result = new LinkedHashSet<>();
        String line;
        while ((line = bufferedReader.readLine())!= null){
            if(!"".equals(line)){
                result.add(line);
            }
        }
        return result.toArray(String[]::new);
    }
}
