package com.bintime.hatch.dao;

import com.bintime.hatch.model.TextFile;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by FromxSoul on 08.10.2016.
 */
public interface TextFileDao {

    List<TextFile> getAllTextFile() throws SQLException;
    void addTextFile(TextFile textFile) throws SQLException;
    void editTextFile(TextFile textFile) throws SQLException;
    TextFile getTextFileByValue(String value) throws SQLException;

}
