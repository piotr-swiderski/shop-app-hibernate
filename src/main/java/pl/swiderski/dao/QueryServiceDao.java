package pl.swiderski.dao;

import org.hibernate.Session;
import pl.swiderski.DataBaseConnectorFactory;

import java.util.List;

public interface QueryServiceDao<T> {

    List<T> findAll();
    T findById(int id);


    default Session openSession(){
        return DataBaseConnectorFactory.getFactory().openSession();
    }




}
