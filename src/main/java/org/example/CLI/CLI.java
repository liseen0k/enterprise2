package org.example.CLI;

import org.example.Models.*;
import org.example.Services.DBServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private final Scanner scanner = new Scanner(System.in);
    private final DBServices dbServices = DBServices.getInstance();

    public void start(){
        System.out.println("Что хотите сделать? " +
                "\n 1 - Вывести список " +
                "\n 2 - Добавить сущность " +
                "\n 3 - Обновить значение у сущности " +
                "\n 4 - Удалить сущность/сущности");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> list();
            case 2 -> create();
            case 4 -> delete();
        }
    }


    public void list(){
        System.out.println("Список чего вы хотите вывести? " +
                "\n 1 - Currencies " +
                "\n 2 - Items" +
                "\n 3 - Player" +
                "\n 4 - Progresses");
        int choiceWhichEntityList = scanner.nextInt();
        String entityName = "";
        switch (choiceWhichEntityList) {
            case 1 -> entityName = "currencies";
            case 2 -> entityName = "items";
            case 3 -> entityName = "player";
            case 4 -> entityName = "progresses";
        }
        List<Model> entityList = null;
        try {
            entityList = dbServices.getAll(entityName);
        } catch (SQLException e) {
            System.out.println("smth went wrong. try again\n");
        }
        System.out.println(entityList);
    }

    public void create(){
        System.out.println("Какую сущность хотите добавить? " +
                "\n 1 - Currencies " +
                "\n 2 - Items" +
                "\n 3 - Player" +
                "\n 4 - Progresses");
        int choiceWhichEntityList = scanner.nextInt();
        String className = "";
        switch (choiceWhichEntityList) {case 1:
            System.out.println("name, count, resourceId, playerId \n");
            scanner.nextLine();
            String name = scanner.nextLine();
            scanner.next();
            int count = scanner.nextInt();
            scanner.next();
            int resourceId = scanner.nextInt();
            int playerId = scanner.nextInt();

            Currencies currencies = new Currencies(0, playerId, resourceId, name, count);
            try {
                dbServices.insert("currencies",currencies);
            } catch (SQLException e) {
                System.out.println("wrong args");
            }
            break;
            case 2:
                System.out.println("playerid, resourceid, count, level\n");

                playerId = scanner.nextInt();
                resourceId = scanner.nextInt();
                count = scanner.nextInt();
                int level = scanner.nextInt();
                Items items = new Items();
                items.setCount(count);
                items.setLevel(level);
                items.setPlayerId(playerId);
                items.setResourceId(resourceId);
                try {
                    dbServices.insert("items", items);
                } catch (SQLException e) {
                    System.out.println("wrong args");
                }
                break;
            case 3:
                System.out.println("nickname");
                String nickname = scanner.nextLine();
                Player player = new Player();
                player.setNickname(nickname);
                try {
                    dbServices.insert("player", player);
                } catch (SQLException e) {
                    System.out.println("wrong args");
                }
                break;
            case 4:
                System.out.println("playerid, resourceId, score, maxscore");
                playerId = scanner.nextInt();
                resourceId = scanner.nextInt();
                int score = scanner.nextInt();
                int maxScore = scanner.nextInt();
                Progresses progresses = new Progresses();
                progresses.setScore(score);
                progresses.setMaxScore(maxScore);
                progresses.setPlayerId(playerId);
                progresses.setResourceId(resourceId);

                try {
                    dbServices.insert("progresses", progresses);
                } catch (SQLException e) {
                    System.out.println("wrong args");
                }
                break;
        }
    }


    public void delete(){
        System.out.println("Что хотите удалить? " +
                "\n 1 - Currencies " +
                "\n 2 - Items" +
                "\n 3 - Player" +
                "\n 4 - Progresses");
        int choiceWhichEntityList = scanner.nextInt();
        String className = "";
        switch (choiceWhichEntityList) {
            case 1 -> className = "currencies";
            case 2 -> className = "items";
            case 3 -> className = "player";
            case 4 -> className = "progresses";
        }
        try {
            dbServices.delete(className, scanner.nextInt());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
