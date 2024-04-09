$(document).ready(function() {
    var keyword = decodeURIComponent(window.location.search.substring(1).split('=')[1]);
    loadAndDisplayData(keyword);
});

function loadAndDisplayData(keyword) {
    // 在这里根据关键词加载数据并展示
    // 可以使用AJAX请求后端接口或者直接在前端处理数据
    function loadAndDisplayData(keyword) {
        $.ajax({
            url: "http://localhost:8080/brand_case_war_exploded/search",
            type: "GET",
            data: { keyword: keyword },
            success: function(response) {
                // 将搜索结果显示在页面上
                displaySearchResults(response);
            },
            error: function() {
                alert("搜索失败，请稍后再试。");
            }
        });
    }

}
function displaySearchResults(results) {
    var container = $("#searchResultsContainer");
    container.empty(); // 清空容器

    if (results && results.length > 0) {
        for (var i = 0; i < results.length; i++) {
            var result = results[i];
            var html = "<p>" + result + "</p>";
            container.append(html);
        }
    } else {
        container.append("<p>没有找到相关结果。</p>");
    }
}


