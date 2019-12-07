package pl.swiderski.dao;

import org.hibernate.Session;
import pl.swiderski.DataBaseConnectorFactory;

public interface CommandServiceDao<T> {

    void createNew(T t);

    void edit(T t);

    void delete(int ID);

 //   default Session openSession(){
 //       return DataBaseConnectorFactory.getFactory().openSession();
 //   }

}
