package com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement;


import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Common.Constants.RESOURCE_PATH;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * Abstract Class For File Management
 *
 * @author Alessandro Spano (Student N. rmb19196)
 */
public abstract class FileManagement {

    /**
     * Read File
     * @param fileName
     * @return File object
     */
    public abstract Object read(String fileName);

    /**
     * Read all Files in folder
     * @param folder
     * @param fileNames
     * @return File object
     */
    public abstract Data readAll(String folder, List<String> fileNames);

    /**
     * Write File
     *
     * I am using Data for the file generation instead of the actual object because it will be easier to extend it in case of multiple files
     *
     * @param fileName - name of the document
     * @param data - data to write into the document
     * @return true if file has benn created successfully
     */
    public abstract boolean write(String fileName , Data data) throws IOException;

    /**
     * Creating new JSON File
     *
     * @return true when successful | false if not
     */
    public abstract boolean createNewFile(String filename, Data data);

    /**
     * Get a list of all files in a specified folder
     *
     * @return List of file names
     */
    public List<String> getFileList(String folderPath) {
        List<String> fileNames = new ArrayList<>();

        //trying to get all file names in Path
        try (Stream<Path> paths = Files.walk(Paths.get(RESOURCE_PATH + "/" + PathFile.JSON.toString() + "/" + folderPath))) {

            //getting all file names and inserting them into a list
            paths.filter(Files::isRegularFile).forEach(p -> fileNames.add(p.getFileName().toString()));

            //return list
            return fileNames;

        } catch (Exception e) {
            displayString("No files found on Path: " + folderPath);
        }
        return null;
    }


}
