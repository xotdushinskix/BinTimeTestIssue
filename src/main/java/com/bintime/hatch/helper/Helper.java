package com.bintime.hatch.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * Created by FromxSoul on 07.10.2016.
 */
@Component
public class Helper {

    public List<File> multiFileConverter(MultipartFile[] files) throws IOException {
        List<File> multipartFiles = new ArrayList<>();
        FileOutputStream fos = null;
        for (MultipartFile file : files) {
            File convertFile = new File(file.getOriginalFilename());
            convertFile.createNewFile();
            fos = new FileOutputStream(convertFile);
            fos.write(file.getBytes());
            multipartFiles.add(convertFile);
        }
        fos.close();
        return multipartFiles;
    }



    public Map stringCounter(File filePath) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String line;
        Map<String, Integer> valueCount = new HashMap<>();
        while((line = in.readLine()) != null)
        {
            String text = line;
            List<String> list = Arrays.asList(text.split(", "));

            Set<String> uniqueWords = new HashSet(list);
            for (String word : uniqueWords) {
                String shaWord = word;
                int shaCount = Collections.frequency(list, word);
                valueCount.put(shaWord, shaCount);
            }
        }
        in.close();
        return valueCount;
    }

}
