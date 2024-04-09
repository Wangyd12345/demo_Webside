// 获取小圆点
var dotList = document.getElementsByClassName("dot");
// 获取图片
var picList = document.getElementsByClassName("pic");
// 获取模糊背景
var bg = document.getElementsByClassName("banner");
// 获取左按钮
var btnLeft = document.getElementsByClassName("left-btn");
// 获取右按钮
var btnRight = document.getElementsByClassName("right-btn");

// 全局变量，索引
index = 0;

console.log(dotList.length);

// 清除active、up样式
// var clearUp = function () {
//   for (var i = 0; i < dotList.length; i++) {
//     dotList[i].className = "dot";
//     picList[i].className = "pic";
//   }
// };

var clearUp = function () {
  for (var i = 0; i < dotList.length; i++) {
    dotList[i].className = "dot";
    picList[i].className = "pic";
    console.log(picList[i].className);
  }
};

// 点击小圆点后相应图片和小圆点的样式改变，同时模糊的背景也改变
var goDot = function () {
  dotList[index].className = "dot active";
  picList[index].className = "pic up";
  bg[0].style.background = "url(../images/轮播图" + index + ".jpg)";
};

// 点击小圆点触发跳转事件;
for (var i = 0; i < dotList.length; i++) {
  dotList[i].addEventListener("click", function () {
    var dotIndex = this.getAttribute("data");
    index = dotIndex;
    clearUp();
    goDot();
    time = 0;
  });
}

// 点击左按钮跳转上一张图片
var goLast = function () {
  if (index > 0) {
    index--;
  } else {
    index = 9;
  }
  clearUp();
  dotList[index].className = "dot active";
  picList[index].className = "pic up";
  bg[0].style.background = "url(../images/轮播图" + index + ".jpg)";
};

// 点击右按钮跳转下一张图片
var goNext = function () {
  if (index < 9) {
    index++;
  } else {
    index = 0;
  }
  clearUp();
  dotList[index].className = "dot active";
  picList[index].className = "pic up";
  bg[0].style.background = "url(../images/轮播图" + index + ".jpg)";
};

// 点击左按钮触发跳转事件;
btnLeft[0].addEventListener("click", function () {
  goLast();
  time = 0;
});

// 点击右按钮触发跳转事件;
btnRight[0].addEventListener("click", function () {
  goNext();
  time = 0;
});

// 自动轮播，每5秒跳转到下一张图片
var time = 0;
setInterval(function () {
  // console.log(time);
  time++;
  if (time == 25) {
    goNext();
    time = 0;
  }
}, 200);
