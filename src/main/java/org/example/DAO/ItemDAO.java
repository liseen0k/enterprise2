package org.example.DAO;

import org.example.Models.Items;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {
    public static String getInsertQuery(Items item){
        return "Insert into items(playerId, resourceId, count, level) VALUES("+
                item.getPlayerId() + ", " +
                item.getResourceId() + ", " +
                item.getCount() + ", " +
                item.getLevel() + ")";
    }

    public static String getUpdateQuery(Items items){

        return "UPDATE items SET playerId = " + items.getPlayerId() +
                ", count = " + items.getCount() +
                ", level = " + items.getLevel() +
                ", resourceId = " + items.getResourceId() +
                " WHERE id = " + items.getId();
    }

    public static Items parseFromQuery(ResultSet rs) throws SQLException {
        return new Items(rs.getInt("id"),
                rs.getInt("playerId"),
                rs.getInt("resourceId"),
                rs.getInt("count"),
                rs.getInt("level"));
    }
}
