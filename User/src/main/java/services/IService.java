package services;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_LIGHTENPeer;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
      void ajouter(T t) throws SQLException;
     void modifier(T t) throws SQLException;
      void supprimer(int id) throws SQLException;

    List<T> afficher() throws SQLException;



}
