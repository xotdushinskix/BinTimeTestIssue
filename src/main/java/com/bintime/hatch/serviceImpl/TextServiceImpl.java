package com.bintime.hatch.serviceImpl;

import com.bintime.hatch.dao.TextFileDao;
import com.bintime.hatch.model.TextFile;
import com.bintime.hatch.service.TextFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by FromxSoul on 08.10.2016.
 */
@Service
public class TextServiceImpl implements TextFileService {

    @Autowired
    private TextFileDao textFileDao;

    @Override
    @Transactional
    public List<TextFile> getAllTextFile() throws SQLException {
        return textFileDao.getAllTextFile();
    }

    @Override
    @Transactional
    public void addTextFile(TextFile textFile) throws SQLException {
        textFileDao.addTextFile(textFile);
    }

    @Override
    @Transactional
    public void editTextFile(TextFile textFile) throws SQLException {
        textFileDao.editTextFile(textFile);
    }

    @Override
    @Transactional
    public TextFile getTextFileByValue(String value) throws SQLException {
        return textFileDao.getTextFileByValue(value);
    }

}
