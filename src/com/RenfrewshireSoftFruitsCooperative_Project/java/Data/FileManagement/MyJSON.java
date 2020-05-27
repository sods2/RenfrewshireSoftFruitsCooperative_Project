package com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;

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
     * @param fileName
     * @param data
     * @return True if operation is successful False if not
     */
    @Override
    public boolean write(Enum fileName, Data data) {

//        JSONObject object = new JSONObject();


//        try{
//            JsonObject object = null;
//
//            //Preparing data for Iteration
//            Map<String, String> jsonData = data.getData();
//
//            //Inserting data into Json Object
//            for (Map.Entry<String, String> value : jsonData.entrySet()) {
//                object = (JsonObject) Json.createObjectBuilder().add(value.getKey(), value.getValue());
//            }
//
//            if (null!=object){
//                FileUtils.write(new File(resourcesPath + "/" + PathFile.JSON + "/" + "jsonTest" + extention),
//                        object.toString(),
//                        "UTF-8");
//            }
//
//            return true;
//        } catch (Exception e) {
//            System.out.println("ERROR: Could not write File! \nPlease Try Again!");
//        }
        return false;
    }
}
