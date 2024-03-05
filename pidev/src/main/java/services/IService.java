package services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public void ajouter(T t) throws SQLException;
    public void modifier(String nom, T t) throws SQLException;
    public void supprimer(String nom) throws SQLException;
    public List<T> afficher() throws SQLException;
}


