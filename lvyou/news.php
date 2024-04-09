<?php
// 连接数据库
$servername = "localhost"; // 数据库地址
$username = "root"; // 数据库用户名
$password = "wangyudian"; // 数据库密码
$dbname = "db1"; // 数据库名称

// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);

// 检查连接
if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
}

// 查询新闻数据
$sql = "SELECT * FROM news";
$result = $conn->query($sql);

// 动态生成新闻内容
if ($result->num_rows > 0) {
    // 输出数据
    while($row = $result->fetch_assoc()) {
        echo "<p class='news-content'>" . $row["content"] . "</p>";

    }
} else {
    echo "0 结果";
}
$conn->close();
?>
