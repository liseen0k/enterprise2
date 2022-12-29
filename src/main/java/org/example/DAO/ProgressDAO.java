package org.example.DAO;

import org.example.Models.Currencies;
import org.example.Models.Progresses;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressDAO {

    public static String getInsertQuery(Progresses progresses){
        return "Insert into progresses(playerId, resourceId, score, maxScore) VALUES("+
                progresses.getPlayerId() + ", " +
                progresses.getResourceId() + ", " +
                progresses.getScore() + ", " +
                progresses.getMaxScore() + ")";
    }

    public static String getUpdateQuery(Progresses progresses){

        return "UPDATE progresses SET playerId = " + progresses.getPlayerId() +
                ", score = " + progresses.getScore() +
                ", maxScore = " + progresses.getMaxScore() +
                ", resourceId = " + progresses.getResourceId() +
                " WHERE id = " + progresses.getId();
    }

    public static Progresses parseFromQuery(ResultSet rs) throws SQLException {
        return new Progresses(rs.getInt("id"),
                rs.getInt("playerId"),
                rs.getInt("resourceId"),
                rs.getInt("score"),
                rs.getInt("maxScore"));
    }



}
