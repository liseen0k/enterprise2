package org.example.Services;

import org.example.DAO.CurrenciesDAO;
import org.example.DAO.ItemDAO;
import org.example.DAO.PlayerDAO;
import org.example.DAO.ProgressDAO;
import org.example.Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBServices {

    private static DBServices instance;

    private final Connection conn;

    private DBServices() {
        conn = Connector.getConnectionInstance();
    }

    public static DBServices getInstance(){
        if(instance == null){
            instance = new DBServices();
        }
        return instance;
    }

    public void insert(String tableName, Model model) throws SQLException {
        String query = null;
        switch (tableName) {
            case "player":
                Player player = (Player) model;
                player.setCurrencies(getCurrenciesForPlayer(player.getPlayerId()));
                player.setProgresses(getProgressesForPlayer(player.getPlayerId()));
                player.setItems(getItemsForPlayer(player.getPlayerId()));
                query = PlayerDAO.getInsertQuery(player);
                break;
            case "currencies":
                query = CurrenciesDAO.getInsertQuery((Currencies) model);
                break;
            case "progresses":
                query = ProgressDAO.getInsertQuery((Progresses) model);
                break;
            case "items":
                query = ItemDAO.getInsertQuery((Items) model);
                break;
            default:
                System.err.println("код не должен сюда заходить");
                System.exit(111);
                break;
        }
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();
        closePrepareStatement(ps);

    }

    public void update(String tableName, Model model) throws SQLException {
        String query = null;
        switch (tableName) {
            case "player":
                query = PlayerDAO.getUpdateQuery((Player) model);
                break;
            case "currencies":
                query = CurrenciesDAO.getUpdateQuery((Currencies) model);
                break;
            case "progresses":
                query = ProgressDAO.getUpdateQuery((Progresses) model);
                break;
            case "items":
                query = ItemDAO.getUpdateQuery((Items) model);
                break;
            default:
                System.err.println("код не должен сюда заходить");
                System.exit(111);
                break;
        }
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();
        closePrepareStatement(ps);

    }

    public void delete(String tableName, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tableName + " WHERE id = " + id + ";");
        ps.executeUpdate();
        closePrepareStatement(ps);
    }

    public Model find(String tableName, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE id = " + id + ";");
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException();
        }

        switch (tableName) {
            case "player":
                Player player = PlayerDAO.parseFromQuery(rs);
                player.setCurrencies(getCurrenciesForPlayer(player.getPlayerId()));
                player.setProgresses(getProgressesForPlayer(player.getPlayerId()));
                player.setItems(getItemsForPlayer(player.getPlayerId()));
                closePrepareStatement(ps);
                return player;

            case "currencies":
                closePrepareStatement(ps);
                return CurrenciesDAO.parseFromQuery(rs);

            case "progresses":
                closePrepareStatement(ps);
                return ProgressDAO.parseFromQuery(rs);

            case "items":
                closePrepareStatement(ps);
                return ItemDAO.parseFromQuery(rs);
            default:
                return null;
        }
    }

    private List<Currencies> getCurrenciesForPlayer(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM currencies WHERE id = " + id + ";");
        ResultSet rs = ps.executeQuery();
        List<Currencies> result = new ArrayList<>();

        while (rs.next()) {
            result.add(CurrenciesDAO.parseFromQuery(rs));
        }
        closePrepareStatement(ps);
        return result;
    }

    private List<Items> getItemsForPlayer(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM items WHERE id = " + id + ";");
        ResultSet rs = ps.executeQuery();
        List<Items> result = new ArrayList<>();

        while (rs.next()) {
            result.add(ItemDAO.parseFromQuery(rs));
        }
        closePrepareStatement(ps);
        return result;
    }

    private List<Progresses> getProgressesForPlayer(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM progresses WHERE id = " + id + ";");
        ResultSet rs = ps.executeQuery();
        List<Progresses> result = new ArrayList<>();
        while (rs.next()) {
            result.add(ProgressDAO.parseFromQuery(rs));
        }
        closePrepareStatement(ps);
        return result;
    }


    public List<Model> getAll(String tableName) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName + ";");
        List<Model> result = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        switch (tableName) {
            case "player":
                while (rs.next()) {
                    Player player = PlayerDAO.parseFromQuery(rs);
                    player.setCurrencies(getCurrenciesForPlayer(player.getPlayerId()));
                    player.setProgresses(getProgressesForPlayer(player.getPlayerId()));
                    player.setItems(getItemsForPlayer(player.getPlayerId()));
                    result.add(player);
                }
                break;
            case "currencies":
                while (rs.next()) {
                    result.add(CurrenciesDAO.parseFromQuery(rs));
                }
                break;
            case "progresses":
                while (rs.next()) {
                    result.add(ProgressDAO.parseFromQuery(rs));
                }
                break;
            case "items":
                while (rs.next()) {
                    result.add(ItemDAO.parseFromQuery(rs));
                }
                break;
            default:
                return null;
        }
        closePrepareStatement(ps);
        return result;
    }


//    public void toDataBase(List<Player> playerList) throws SQLException {
//        try {
//            System.out.println("Устанавливается соединение с базой данных...");
//            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("Соединение установлено");
//            Statement statement = conn.createStatement();
//            System.out.println("Идет загрузка данных в базу...");
//            for (Player pl : playerList) {
//                insert(statement, "Player", pl);
//
//                for (Progresses prog : pl.getProgresses()) {
//                    insert(statement, "Progresses", prog);
//                }
//                for (Currencies cur : pl.getCurrencies()) {
//                    insert(statement, "Currencies", cur);
//                }
//                for (Items item : pl.getItems()) {
//                    insert(statement, "Items", item);
//                }
//            }
//            System.out.println("Данные загружены");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        finally {
//            System.out.println("Разрыв соединения с базой...");
//            conn.close();
//            System.out.println("Соединение разорвано");
//        }
//    }
//
//    public List<Player> fromDataBase() throws SQLException {
//        List<Player> playerList = new ArrayList<>();
//        try {
//            System.out.println("Устанавливается соединение с базой данных...");
//            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("Соединение установлено");
//            Statement statement = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_INSENSITIVE,
//                    ResultSet.CONCUR_READ_ONLY,
//                    ResultSet.HOLD_CURSORS_OVER_COMMIT
//            );
//            System.out.println("Идет выгрузка данных из базы...");
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Player\"");
//
//            while(resultSet.next()) {
//                Player player = new Player();
//                int playerId = resultSet.getInt("playerId");
//                player.setPlayerId(playerId);
//                player.setNickname(resultSet.getString("nickname"));
//
//                Statement st2 = conn.createStatement(
//                        ResultSet.TYPE_SCROLL_INSENSITIVE,
//                        ResultSet.CONCUR_READ_ONLY
//                );
//                ResultSet progressResult = st2.executeQuery(
//                        String.format("SELECT * FROM public.\"Progresses\" WHERE \"Progresses\".\"playerId\"=%d", playerId));
//                while (progressResult.next()) {
//                    player.addProgress(new Progresses(progressResult.getInt("id"),
//                            progressResult.getInt("playerId"),
//                            progressResult.getInt("resourceId"),
//                            progressResult.getInt("score"),
//                            progressResult.getInt("maxScore")));
//                }
//
//                Statement st3 = conn.createStatement(
//                        ResultSet.TYPE_SCROLL_INSENSITIVE,
//                        ResultSet.CONCUR_READ_ONLY
//                );
//                ResultSet currencyResult = st3.executeQuery(
//                        String.format("SELECT * FROM public.\"Currencies\" WHERE \"Currencies\".\"playerId\"=%d", playerId));
//                while (currencyResult.next()) {
//                    player.addCurrency(new Currencies(currencyResult.getInt("id"),
//                            currencyResult.getInt("playerId"),
//                            currencyResult.getInt("resourceId"),
//                            currencyResult.getString("name"),
//                            currencyResult.getInt("count")));
//                }
//
//                Statement st4 = conn.createStatement(
//                        ResultSet.TYPE_SCROLL_INSENSITIVE,
//                        ResultSet.CONCUR_READ_ONLY
//                );
//                ResultSet itemResult = st4.executeQuery(
//                        String.format("SELECT * FROM public.\"Items\" WHERE \"Items\".\"playerId\"=%d", playerId));
//                while (itemResult.next()) {
//                    player.addItem(new Items(itemResult.getInt("id"),
//                            itemResult.getInt("playerId"),
//                            itemResult.getInt("resourceId"),
//                            itemResult.getInt("count"),
//                            itemResult.getInt("level")));
//                }
//
//                playerList.add(player);
//            }
//            System.out.println("Выгрузка данных из базы завершена");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        finally {
//            System.out.println("Разрыв соединения с базой...");
//            conn.close();
//            System.out.println("Соединение разорвано");
//        }
//        return playerList;
//    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
