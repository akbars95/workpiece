package com.mtsmda;

import com.mtsmda.helper.LocalDateTimeHelper;
import com.mtsmda.helper.ObjectHelper;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MTSMDA on 23.10.2016.
 */
public class FileSearcher {

    private Map<String, Integer> result = new HashMap<>();
    private Integer countDirectories = 0;
    private Integer countFile = 0;

    private Map<String, Integer> searchExtensions(String fileName) {
        return searchExtensions(new File(fileName));
    }

    private Map<String, Integer> searchExtensions(File file) {
        LocalDateTime localDateTimeStart = LocalDateTime.now();
        System.out.println(file.getAbsoluteFile() + "\nstart - " + LocalDateTimeHelper.localDateTime(localDateTimeStart, LocalDateTimeHelper.NORMAL_DATE_TIME_FORMAT));
        if (ObjectHelper.objectIsNull(file)) {
            throw new RuntimeException("input file name is null");
        }

        if (!file.exists()) {
            throw new RuntimeException("File - " + file.getAbsolutePath() + " is not exists!");
        }

        recursiveSearchFiles(file);
        LocalDateTime localDateTimeEnd = LocalDateTime.now();
        System.out.println("end - " + LocalDateTimeHelper.localDateTime(localDateTimeEnd, LocalDateTimeHelper.NORMAL_DATE_TIME_FORMAT));
        System.out.println(Duration.between(localDateTimeStart, localDateTimeEnd).getSeconds() + " seconds");
        return result;
    }

    private void recursiveSearchFiles(File file) {
        if (ObjectHelper.objectIsNotNull(file) && file.isDirectory()) {
            countDirectories++;
            File[] files = file.listFiles();
            System.out.println("" + files.length);
            for (File current : files) {
                /*if("C://Cookies".equals(file.getAbsolutePath())){
                    System.out.println(file.getAbsoluteFile() + " _ " + file.isDirectory());
                }*/
                recursiveSearchFiles(current);
            }
        } else {
            countFile++;
            String nameExtension = null;
            try {
                nameExtension = file.getName().substring(file.getName().lastIndexOf("."));
            } catch (RuntimeException e) {
                System.out.println(file.getAbsoluteFile());
                nameExtension = ".file";
            }
            if (result.containsKey(nameExtension)) {
                result.put(nameExtension, result.get(nameExtension) + 1);
            } else {
                result.put(nameExtension, 0);
            }
        }
    }

    public Map<String, Integer> getResult() {
        return result;
    }

    public void setResult(Map<String, Integer> result) {
        this.result = result;
    }

    public Integer getCountDirectories() {
        return countDirectories;
    }

    public void setCountDirectories(Integer countDirectories) {
        this.countDirectories = countDirectories;
    }

    public Integer getCountFile() {
        return countFile;
    }

    public void setCountFile(Integer countFile) {
        this.countFile = countFile;
    }

    public static void main(String[] args) {
        FileSearcher fileSearcher = new FileSearcher();
        for(File rootCurrent : File.listRoots()){
            System.out.println(rootCurrent.getAbsoluteFile());
            Map<String, Integer> extensions = fileSearcher.searchExtensions(rootCurrent.getAbsoluteFile());
            extensions.forEach((key, value) -> {
                System.out.println(key + " - " + value);
            });
            System.out.println("Count files - " + fileSearcher.countFile);
            System.out.println("Count directories - " + fileSearcher.countDirectories);
        }
        File file = new File("C:\\Cookies");
        System.out.println(file.exists());
        System.out.println(file.isFile());
    }

}