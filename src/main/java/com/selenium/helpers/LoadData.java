package com.selenium.helpers;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public class LoadData {

    public static Collection ofJson(String path, Type typeOfT) throws IOException {
        File usersFile = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            return gson.fromJson(json.toString(), typeOfT);
        }
    }
}
