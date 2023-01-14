package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.SQLException;

public interface TerrenoDAO {
    public void create(TerrenoBean t) throws SQLException;
    public TerrenoBean retrieve(String id_terreno) throws SQLException;


}
