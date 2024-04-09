<?php
// 获取表单中的账号和密码
if (isset($_POST['account'])) {
    $account = $_POST['account'];
} else {
    $account = "";
}
if (isset($_POST['password'])) {
    $userPassword = $_POST['password'];
} else {
    $userPassword = "";
}

// 数据库连接信息
$servername = "localhost";
$username = "root";
$dbpassword = "wangyudian";
$dbname = "db1";

// 连接到数据库
$conn = new mysqli($servername, $username, $dbpassword, $dbname);

// 检查连接是否成功
if ($conn->connect_error) {
    die("连接数据库失败: " . $conn->connect_error);
}

/// 将账号和密码插入数据库表
$sql = "INSERT INTO touristuser (account, password) VALUES ('" . $conn->real_escape_string($account) . "', '" . $conn->real_escape_string($userPassword) . "')";

if ($conn->query($sql) === TRUE) {
    // 数据插入成功
    // 将账号插入 userlikes 表
    $sql_likes = "INSERT INTO users_likes (account, star_num) VALUES ('" . $conn->real_escape_string($account) . "', 0)";
    if ($conn->query($sql_likes) === TRUE) {
        // 点赞数据插入成功
        header("Location: last.html?message=success");  // 重定向到主页面
        exit;  // 结束脚本，确保后续代码不会执行
    } else {
        echo "Error: " . $sql_likes . "<br>" . $conn->error;
        echo "详细错误信息：" . $conn->error;
    }
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
    echo "详细错误信息：" . $conn->error;
}

$conn->close();
?>