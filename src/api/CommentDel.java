package api;

import dao.CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/comment/del")
public class CommentDel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getParameter("body");
        try {
            new CommentDAO().deleteComment(body);
            resp.getWriter().print(body);
        } catch (Exception exception) {
            exception.printStackTrace();
            resp.getWriter().print("-1");
        }
    }
}
