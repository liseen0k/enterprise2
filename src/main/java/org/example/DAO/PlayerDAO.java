package org.example.DAO;

import org.example.Models.Currencies;
import org.example.Models.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {
    public static String getInsertQuery(Player player){
        return "Insert into Player(nickname) VALUES('"+
                player.getNickname() + "')";
    }

    public static String getUpdateQuery(Player player){

        return "UPDATE Player SET nickname = '" + player.getNickname() +
                "' WHERE playerId = " + player.getPlayerId();
    }

    public static Player parseFromQuery(ResultSet rs) throws SQLException {
        return new Player(rs.getInt("playerId"),
                rs.getString("nickname"));
    }
}
