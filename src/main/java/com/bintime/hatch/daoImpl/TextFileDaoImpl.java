package com.bintime.hatch.daoImpl;

import com.bintime.hatch.dao.TextFileDao;
import com.bintime.hatch.model.TextFile;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by FromxSoul on 08.10.2016.
 */
@Repository
public class TextFileDaoImpl implements TextFileDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TextFile> getAllTextFile() throws SQLException {
        List<TextFile> textFiles = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT count, value from testrest.text_file";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            textFiles = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return textFiles;
    }



    @Override
    @Transactional
    public void addTextFile(TextFile textFile) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(textFile);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null)) {
                session.close();
            }
        }
    }



    @Override
    @Transactional
    public void editTextFile(TextFile textFile) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(textFile);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null)) {
                session.close();
            }
        }
    }



    @Override
    public TextFile getTextFileByValue(String value) throws SQLException {
        Session session = null;
        TextFile textFile = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(TextFile.class);
            textFile = (TextFile) criteria.add(Restrictions.like("value", value)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return textFile;
    }


}
