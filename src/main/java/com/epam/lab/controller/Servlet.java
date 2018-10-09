package com.epam.lab.controller;

import com.epam.lab.model.Entity;
import com.epam.lab.service.EntityService;
import com.epam.lab.service.impl.EntityServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class Servlet extends HttpServlet {

    private static long startTime = System.currentTimeMillis();

    private EntityService service = new EntityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long currentTimeMillis = System.currentTimeMillis();
        req.setAttribute("runtimeMs", currentTimeMillis - startTime);
        req.setAttribute("serverDate", new java.util.Date());

        if (req.getRequestURI().equals("/") && req.getParameterMap().isEmpty()) {
            req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        }

        if (req.getParameter("getAll").equals("getAll")) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            List<Entity> entities = service.getAll();
            String json = new Gson().toJson(entities);
            resp.getWriter().write(json);
            resp.getWriter().flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String name = req.getParameter("name");
        Entity reqEntity = new Entity();
        reqEntity.setName(name);
        Entity respEntity = service.add(reqEntity);
        String json = new Gson().toJson(respEntity);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String idValue = req.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(idValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        String value = req.getParameter("value");
        Entity entity = service.get(id);
        entity.setName(value);
        Entity respEntity = service.update(entity);
        String json = new Gson().toJson(respEntity);
        resp.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String idValue = req.getParameter("idValue");
        int id = 0;
        try {
            id = Integer.parseInt(idValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Entity respEntity = service.delete(id);
        if (respEntity == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String json = new Gson().toJson(respEntity);
        resp.getWriter().write(json);
    }
}


