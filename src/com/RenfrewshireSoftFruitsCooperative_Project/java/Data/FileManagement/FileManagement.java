package com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement;


import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;

import java.io.FileNotFoundException;
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
    public abstract Object read(Enum fileName);

    /**
     * Write File
     * @param fileName
     * @return File object
     */
    public abstract boolean write(Enum fileName , Data data) throws IOException;

}
