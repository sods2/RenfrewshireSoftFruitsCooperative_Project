package com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.omg.CORBA.Object;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Common.Constants.JSON_EXERTION;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Common.Constants.RESOURCE_PATH;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * Using this class to Read & write JSON files
 *
 * @author rmb19196 (Alessandro Spano)
 */
public class MyJSON extends FileManagement {

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
        //instantiating Data
        Data data;

        try{

            data = getDataFormFile(filename + JSON_EXERTION);

            //returning data obj
            return data;

        }
        catch (FileNotFoundException e) {
            displayString("Could not read File! \nTry restarting Application.");
        } catch (Exception e) {
            displayString("File not Found! \nTry restarting Application.");
        }
        return null;
    }

    @Override
    public Data readAll(String folder, List<String> fileNames) {

        //instantiating Data
        Data data = new Data();

        try{

            //looping through files
            for (String filename : fileNames){
                //Getting data from file
                data = getDataFormFile(folder + "/" + filename);
            }

            //returning data obj
            return data;

        }
        catch (FileNotFoundException e) {
            displayString("Could not read File! \nTry restarting Application.");
        } catch (Exception e) {
            displayString("File not Found! \nTry restarting Application.");
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
                displayString("No data found while attempting to create the file.");
                return false;
            }

            if (!gson.toString().isEmpty()) {
                //creating File in path
                FileWriter fWriter = new FileWriter(RESOURCE_PATH + "/" + PathFile.JSON + "/"+ filename + JSON_EXERTION);
                BufferedWriter out = new BufferedWriter(fWriter);
                out.write(json);
                //closing buffer
                out.close();

                displayString("");
                displayString("    File Created Successfully!");
                return true;
            }
            return true;
        } catch (Exception e) {
            displayString("ERROR: Could not write File! \nPlease Try Again!");
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
            displayString("ERROR: Could not write File! \nPlease Try Again!");
        }

        return false;
    }

    private Data getDataFormFile(String filename) throws FileNotFoundException {

        //instantiating Data & Gson
        Data data = new Data();
        Gson gson = new Gson();

        //Getting the specified file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(RESOURCE_PATH + "/" + PathFile.JSON.toString() + "/" + filename));

        //Getting Obj from file data
        Object json = gson.fromJson(bufferedReader, Object.class);

        //Creating desired type for json deserialization
        Type type = new TypeToken<HashMap<String, Object>>() {}.getType();

        //getting json String
        String s = gson.toJson(json, type);

        //populating HashMap with File's data
        HashMap<String, Object> stringData = gson.fromJson(s, type);

        //getting value to the Data obj
        data.getData().putAll(stringData);

        return data;
    }

}
