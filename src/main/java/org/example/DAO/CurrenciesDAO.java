package org.example.DAO;

import org.example.Models.Currencies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CurrenciesDAO {
    public static String getInsertQuery(Currencies currencies){
        return "Insert into currencies(name, count, resourceId, playerId) VALUES('"+
                currencies.getName() + "', " +
                currencies.getCount() + ", " +
                currencies.getResourceId() + ", " +
                currencies.getPlayerId() + ")";
    }

    public static String getUpdateQuery(Currencies currencies){

        return "UPDATE currencies SET name = '" + currencies.getName() +
                "', count = " + currencies.getCount() +
                ", resourceId = " + currencies.getResourceId() +
                ", playerId = " + currencies.getPlayerId() +
                " WHERE id = " + currencies.getId();
    }

    public static Currencies parseFromQuery(ResultSet rs) throws SQLException {
        return new Currencies(rs.getInt("id"),
                rs.getInt("playerId"),
                rs.getInt("resourceid"),
                rs.getString("name"),
                rs.getInt("count"));
    }
}
