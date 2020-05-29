package com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayStream;

/**
 * Using this class to Read Files
 *
 * @author rmb19196 (Alessandro Spano)
 */
public class MyFile extends FileManagement {

    private final String path = "src/com/RenfrewshireSoftFruitsCooperative_Project/resources/Console";
    private final String extention = ".txt";

    /**
     * This method reads a specified File saved in the resources folder.
     *
     * I use this method for getting the menus and information displayed on the console.
     *
     * @param fileLocation
     * @return String of values taken for File.
     */
    @Override
    public Stream<String> read(String fileLocation) {
        Path filePath = Paths.get(path, fileLocation + extention );
        try (Stream<String> lines = Files.lines( filePath )) {

            displayStream(lines);
            return lines;

            } catch (NullPointerException | IOException e) {
                System.out.println("File not Found! \nTry restarting Application.");
            }

            return null;
        }

    /**
     * I don't have a need for creating a File as I am using them only for menus display
     * @param fileLocation
     * @return null
     */
    @Override
    public boolean write(String fileLocation, Data data) throws IOException {

        Files.write(Paths.get(path + "/" + fileLocation + extention), "content".getBytes());

        return false;
    }

    @Override
    public boolean createNewFile(Object obj, Data data) {
        return false;
    }

}
