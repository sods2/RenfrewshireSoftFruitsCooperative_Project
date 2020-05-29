package com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Using this class to Read & write JSON files
 *
 * @author rmb19196 (Alessandro Spano)
 */
public class MyJSON extends FileManagement {

    private final String path = "src/com/RenfrewshireSoftFruitsCooperative_Project/resources/JSON";

    private final String extention = ".json";

    /**
     * This method reads & writes specified JSON File saved in the resources folder.
     *
     * Documentation can be found on Google gson github repository at https://github.com/google/gson (visited may-2020)
     *
     * @return String of values taken for JSON File.
     */
    @Override
    public Object read(Enum fileName) {
        try{ //TODO: change filename

            //instantiating Data & Gson
            Data data = new Data();
            Gson gson = new Gson();

            //Getting the specified file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "/" + "Test_JSON2" + extention));

            //Getting Obj from file data
            Object json = gson.fromJson(bufferedReader, Object.class);

            //Creating desired type for json deserialization
            Type type = new TypeToken<HashMap<String, String>>() {}.getType();

            //getting json String
            String s = gson.toJson(json, type);

            //populating HashMap with File's data
            HashMap<String, String> stringData = gson.fromJson(s, type);

            //getting value to the Data obj
            data.getData().putAll(stringData);

            //returning data obj
            return data;

        } catch (FileNotFoundException e) {
            System.out.println("Could not read File! \nTry restarting Application.");
        } catch (NullPointerException e) {
            System.out.println("File not Found! \nTry restarting Application.");
        }

        return null;

    }

    /**
     * Writing Json File into resources Folder
     *
     * Documentation can be found on Google gson github repository at https://github.com/google/gson (visited may-2020)
     *
     * @param fileName
     * @param data
     * @return True if operation is successful False if not
     */
    @Override
    public boolean write(Enum fileName, Data data) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try{ //TODO: add creation of file & change filename
            String json = gson.toJson(data.getData());
//            System.out.println(json);

            if (!"".equals(gson.toString())) {
//                gson.toJson(json, new FileWriter(path + "/" + "Test_JSON" + extention));
                Files.write(Paths.get(path + "/" + "Test_JSON2" + extention), json.getBytes());
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: Could not write File! \nPlease Try Again!");
        }
        return false;
    }
}
