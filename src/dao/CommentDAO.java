package dao;

import bean.Comment;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBHelper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class CommentDAO {
    public List<Comment> getAllComments() throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "select id, author, body from comment ";
        try {
            return new QueryRunner().query(
                    conn, sql, new BeanListHandler<Comment>(Comment.class));
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    public Comment addComment(Comment comment) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "insert into comment (author, body) values (?, ?)";
        Object[] params = {
                comment.getAuthor(),comment.getBody()
        };
        try {
            QueryRunner run = new QueryRunner();
            BigDecimal res = run.insert(conn, sql, new ScalarHandler<BigDecimal>(), params);
            comment.setId((int) res.longValue());
            return comment;
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    public void deleteComment(String body) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "delete from comment where body = ?";
        try {
            new QueryRunner().update(conn, sql, body);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }
}
