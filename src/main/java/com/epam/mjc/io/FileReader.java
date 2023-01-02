package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        String word = "";
        try (FileInputStream fileInput = new FileInputStream(file)) {
            int ch;
            while ((ch = fileInput.read()) != -1) {
                word += (char) ch;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arr = word.split("\n");
        String name = getValueByKey(arr[0], "Name");
        profile.setName(name);
        String email = getValueByKey(arr[2], "Email");
        profile.setEmail(email);

        try {
            String age = getValueByKey(arr[1], "Age");
            profile.setAge(Integer.parseInt(age));
            Long phone = (long) Integer.parseInt(getValueByKey(arr[3], "Phone"));
            profile.setPhone(phone);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return profile;
    }

    public String getValueByKey(String pair, String key) {
        String[] arr = pair.split(": ");
        if (arr[0].equals(key)) return arr[1].trim();
        return "";
    }
}
