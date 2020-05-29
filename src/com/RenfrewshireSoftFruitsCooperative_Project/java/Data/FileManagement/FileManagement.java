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
     * @param fileName
     * @return File object
     */
    public abstract boolean write(String fileName , Data data) throws IOException;

    /**
     * Creating new JSON File
     *
     * @return true when successful | false if not
     */
    public abstract boolean createNewFile(Object obj, Data data);

}
