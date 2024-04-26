package br.ed.senaisp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.edu.senaisp.dao.SaborDAO;
import br.edu.senaisp.model.Sabor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns = "/API")
public class API extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("GETOU GOSTOSO");
		
		SaborDAO dao = new SaborDAO();
		
		List<Sabor> sabores = dao.lista();
		String json = "";
		try {
			Gson gson = new Gson();
			json = gson.toJson(sabores);
		}catch (Exception e) {
			e.printStackTrace();
		}
		resp.getWriter().append(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("POSTOU GOSTOSO");
		
		BufferedReader br = req.getReader();
		
		String json = "";
		String linha = "";
		
		while ((linha = br.readLine()) != null) {
			json += linha;
		}
		Gson gson = new Gson();
		Sabor sabor = gson.fromJson(json, Sabor.class);
		SaborDAO dao = new SaborDAO();
		dao.novo(sabor);
		resp.getWriter().append("\nEIIIIITA COMO POSTA");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("PUTOU GOSTOSO");
		
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("DELETOU GOSTOSO");
		
		BufferedReader br = req.getReader();
		
		String json = "";
		String linha = "";
		
		while ((linha = br.readLine()) != null) {
			json += linha;
		}
		Gson gson = new Gson();
		Sabor sabor = gson.fromJson(json, Sabor.class);
		SaborDAO dao = new SaborDAO();
		int id = sabor.getId();
		dao.remove(id);
		resp.getWriter().append("\nEIIIIITA COMO DELETA");
	}
}
