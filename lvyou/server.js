// 后端服务器代码（使用 Express 框架）
const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const multer  = require('multer');
const upload = multer({ dest: 'uploads/' });

// 解析 application/x-www-form-urlencoded 格式的请求体
app.use(bodyParser.urlencoded({ extended: false }));

// 解析 application/json 格式的请求体
app.use(bodyParser.json());

// 处理文章提交的接口
app.post('/submit_essay', upload.single('image'), (req, res) => {
    const content = req.body.content;
    const image = req.file;

    // 将文章信息存入数据库，这里假设使用了一个名为 Essay 的模型来操作数据库
    const newEssay = new Essay({
        content: content,
        imagePath: image.path
    });
    newEssay.save((err, essay) => {
        if (err) {
            res.status(500).json({ message: '文章保存失败' });
        } else {
            res.json(essay);
        }
    });
});

app.listen(3000, () => {
    console.log('后端服务器已启动，监听端口3000');
});