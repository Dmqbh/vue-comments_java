package api;

import bean.Comment;
import com.google.gson.Gson;
import dao.CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/comment/add")
public class CommentAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String author = req.getParameter("author");
        String body = req.getParameter("body");
        try {
            // 所有信息入库
            CommentDAO commentDAO = new CommentDAO();
            Comment comment = commentDAO.addComment(new Comment(author,body));
            resp.getWriter().print(new Gson().toJson(comment));
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().print("-1");
        }
    }
}
