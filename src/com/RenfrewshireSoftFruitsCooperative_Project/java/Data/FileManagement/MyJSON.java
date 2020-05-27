package com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;

/**
 * Using this class to Read & write JSON files
 *
 * @author rmb19196 (Alessandro Spano)
 */
public class MyJSON extends FileManagement {

    private final String resourcesPath = "src/main/resources";

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
//        try{
//
//            //Getting JSON File From input stream
//            JsonReader jsonReader = Json.createReader(new FileInputStream(classLoader.getResource(PathFile.JSON + "/" + fileName + extention).getFile()));
//
//            //Reading the JSON object
//            JsonObject object = jsonReader.readObject();
//
//            //Closing Reader
//            jsonReader.close();
//
//            //Returning data acquired from the Reader
//            return object;
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Could not read File! \nTry restarting Application.");
//        } catch (NullPointerException e) {
//            System.out.println("File not Found! \nTry restarting Application.");
//        }

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

        try{ //TODO: add creation of file

            String json = gson.toJson(data);

            System.out.println(json);
            if (!"".equals(gson.toString())) {
                return true;
            }
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: Could not write File! \nPlease Try Again!");
        }
        return false;
    }
}
