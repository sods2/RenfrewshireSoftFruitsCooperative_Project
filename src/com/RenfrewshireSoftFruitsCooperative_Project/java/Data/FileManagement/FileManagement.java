package com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement;


import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;

import java.io.IOException;

/**
 * Abstract Class For File Management
 */
public abstract class FileManagement {

    /**
     * Read File
     * @param fileName
     * @return File object
     */
    public abstract Object read(String fileName);

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

}
