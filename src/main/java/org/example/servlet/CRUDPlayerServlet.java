package org.example.servlet;


import org.example.Models.Model;
import org.example.Models.Player;
import org.example.Services.DBServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet()
public class CRUDPlayerServlet extends HttpServlet {
    DBServices dbServices = DBServices.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            List<Player> players = new ArrayList<>();
            if(req.getParameter("action").equals("find")){
                players.add((Player) dbServices.find("player", Integer.parseInt(req.getParameter("find_id"))));
            }else{
                List<Model> models = dbServices.getAll("player");
                for (Model model: models){
                    players.add((Player) model);
                }
            }


            req.setAttribute("players", players);
            req.getRequestDispatcher("/getPlayers.jsp").forward(req, resp);
        } catch (SQLException e) {

            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action").equals("delete")){
            int id = Integer.parseInt(req.getParameter("delete_id"));

            try {
                dbServices.delete("player", id);

                req.getRequestDispatcher("/success.jsp").forward(req, resp);
            } catch (SQLException e) {
                req.setAttribute("error_message", "student not found");
                e.printStackTrace();
            }
        } else if(req.getParameter("action").equals("create")){
            int id = Integer.parseInt(req.getParameter("id"));
            String nickname = req.getParameter("nickname");

            Player player = new Player(id, nickname);
        }
    }
}
