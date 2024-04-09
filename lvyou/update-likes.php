<?php
// 获取账号
if (isset($_POST['account'])) {
    $account = $_POST['account'];
} else {
    $account = "";
}

// 连接到数据库
$servername = "localhost";
$username = "root";
$dbpassword = "wangyudian";
$dbname = "db1";

// 创建一个新的MySQL连接
$conn = new mysqli($servername, $username, $dbpassword, $dbname);

// 检查连接是否成功
if ($conn->connect_error) {
    die("连接数据库失败: " . $conn->connect_error);
}

// 查询文章表，更新点赞数
$query = "SELECT likes FROM articles WHERE account = '$account'";
$result = mysqli_query($conn, $query);
if (mysqli_num_rows($result) > 0) {
    // 如果存在记录，则更新点赞次数
    $row = mysqli_fetch_assoc($result);
    $likes = $row['likes'];

    if ($likes == 0) {
        // 如果点赞次数为0，则更新为1
        $likes = 1;
    } else {
        // 否则递增点赞次数
        $likes++;
    }
    // 更新点赞次数
    $updateQuery = "UPDATE articles SET likes = $likes WHERE account = '$account'";
    mysqli_query($conn, $updateQuery);
} else {
    // 如果不存在记录，则插入新的点赞次数记录
    $likes = 1;
    $insertQuery = "INSERT INTO articles (account, likes) VALUES ('$account', $likes)";
    mysqli_query($conn, $insertQuery);
}

// 更新用户的总点赞数
$query = "SELECT star_num FROM users_likes WHERE account = '$account'";
$result = mysqli_query($conn, $query);
if (mysqli_num_rows($result) > 0) {
    $row = mysqli_fetch_assoc($result);
    $starNum = $row['star_num'];
} else {
    // 如果用户不存在于表中，插入新记录并更新总点赞数
    $starNum = 1;
    $insertQuery = "INSERT INTO users_likes (account, star_num) VALUES ('$account', $starNum)";
    mysqli_query($conn, $insertQuery);
}
$newStarNum = $starNum + 1;
$updateUserLikesQuery = "UPDATE users_likes SET star_num = $newStarNum WHERE account = '$account'";
mysqli_query($conn, $updateUserLikesQuery);

// 返回更新后的点赞次数和总点赞数给前端，这里仍然使用原来的变量进行输出。
echo $likes . ',' . $newStarNum;
$conn->close();
?>