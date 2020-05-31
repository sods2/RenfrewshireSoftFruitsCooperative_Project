package com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Common.Constants.JSON_EXERTION;

/**
 * Using this class to Read & write JSON files
 *
 * @author rmb19196 (Alessandro Spano)
 */
public class MyJSON extends FileManagement {

    private final String resourcePath = "src/com/RenfrewshireSoftFruitsCooperative_Project/resources";

    /**
     * This method reads & writes specified JSON File saved in the resources folder.
     *
     * Documentation can be found on Google gson github repository at https://github.com/google/gson (visited may-2020)
     *
     * @param filename
     * @return String of values taken for JSON File.
     */
    @Override
    public Data read(String filename) {
        try{

            //instantiating Data & Gson
            Data data = new Data();
            Gson gson = new Gson();

            //Getting the specified file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(resourcePath + "/" + PathFile.JSON + "/" + filename + JSON_EXERTION));

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
     * @param filename
     * @param data
     * @return True if operation is successful False if not
     */
    @Override
    public boolean write(String filename, Data data) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try{
            String json;

            if (null!=data) {
                json = gson.toJson(data.getData());
            } else {
                System.out.println("No data found while attempting to create the file.");
                return false;
            }

            if (!"".equals(gson.toString())) {
                Files.write(Paths.get(resourcePath + "/" + PathFile.JSON + "/"+ filename + JSON_EXERTION), json.getBytes());
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: Could not write File! \nPlease Try Again!");
        }
        return false;
    }

    /**
     * Create a new File
     * @param filename
     * @param data
     * @return true if successful
     */
    @Override
    public boolean createNewFile(String filename, Data data) {

        try{

            return write(filename, data);


        } catch (Exception e) {
            System.out.println("ERROR: Could not write File! \nPlease Try Again!");
        }

        return false;
    }
}
