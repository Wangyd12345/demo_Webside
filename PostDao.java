import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PostDao {
    private Connection getConnection() {
        {
            try {
                Connection getConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/traval?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Hongkong",
                        "root",
                        "epiphany1133X");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        // 返回数据库连接
        return null;
    }
    //获取点赞数
    private int getLikes(int postId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int likes = 0;
        try {
            // 获取数据库连接
            connection = getConnection();

            // 准备 SQL 查询语句
            String query = "SELECT COUNT(*) AS likes FROM likes WHERE post_id = ?";
            statement = connection.prepareStatement(query);

            // 设置参数
            statement.setInt(1, postId);

            // 执行查询
            resultSet = statement.executeQuery();

            // 处理结果集
            if (resultSet.next()) {
                likes = resultSet.getInt("likes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接和资源
            closeResources(connection, statement, resultSet);
        }

        return likes;
    }
    //获取评论数
    private Object getComments(int postId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int comments = 0;

        try {
            // 获取数据库连接
            connection = getConnection();

            // 准备 SQL 查询语句
            String query = "SELECT COUNT(*) AS comments FROM comments WHERE post_id = ?";
            statement = connection.prepareStatement(query);

            // 设置参数
            statement.setInt(1, postId);

            // 执行查询
            resultSet = statement.executeQuery();

            // 处理结果集
            if (resultSet.next()) {
                comments = resultSet.getInt("comments");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接和资源
            closeResources(connection, statement, resultSet);
        }

        return comments;
    }
    //更新点赞评论
    public void updateLikesAndComments(int postId, int likes, int comments) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 获取数据库连接
            connection = getConnection();

            // 创建 SQL 更新语句
            String sql = "UPDATE posts SET likes = ?, comments = ? WHERE id = ?";

            // 创建 PreparedStatement
            statement = connection.prepareStatement(sql);

            // 设置参数
            statement.setInt(1, likes);
            statement.setInt(2, comments);
            statement.setInt(3, postId);

            // 执行更新
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和声明
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    //更新点赞数
    public void likePost(int postId) {
        // 获取当前点赞数
        int currentLikes = getLikes(postId);

        // 增加点赞数
        int newLikes = currentLikes + 1;

        // 更新点赞数和评论数
        updateLikesAndComments(postId, newLikes, getComments(postId));
    }

    //更新评论
    public void addComment(int postId, String comment) {
        // 获取当前评论数
        int currentComments = (int) getComments(postId);

        // 增加评论数
        int newComments = currentComments + 1;

        // 更新点赞数和评论数
        updateLikesAndComments(postId, getLikes(postId), newComments);

        // 更新评论内容
        updateComments(postId, );
    }
}


