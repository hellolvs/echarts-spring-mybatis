/**
 * @author shuai.lv
 * @date 2017/06/28.
 */
$(function () {
    resizeContainer();                                //容器大小初始化
    echart = echarts.init(container);                 //创建echarts实例
    buildDatepicker();                                //创建日期选择器
    bulidTags();                                      //创建标签插件
    showLine();                                       //绘制echarts图像，默认日期2017-06-01

    $('#addLine').on('click', showLine);              //监听添加按钮，添加echarts折线
    $('#clearCharts').on('click', clearCharts);       //监听清空按钮，清除echarts数据
    resize();                                         //监听窗口大小，自动调整chart
});

var container = document.getElementById('main');
var tags;
var echart;
var option = {
    grid: {
        top: '10%',
        left: '5%'
    },
    title: {
        text: '机票每分钟订单量'
    },
    tooltip: {
        trigger: 'axis'
    },
    toolbox: { //可视化的工具箱
        show: true,
        feature: {
            dataView: { //数据视图
                show: true
            },
            restore: { //重置
                show: true
            },
            dataZoom: { //数据缩放视图
                show: true
            },
            saveAsImage: {//保存图片
                show: true
            },
            magicType: {//动态类型切换
                type: ['bar', 'line']
            }
        }
    },
    legend: {
        data: []
        // orient: 'vertical'
    },
    dataZoom: [
        {
            type: 'slider',
            xAxisIndex: 0,
            start: 0,
            end: 100
        },
        {
            type: 'inside',
            xAxisIndex: 0,
            start: 0,
            end: 100
        },
        {
            type: 'slider',
            yAxisIndex: 0,
            start: 0,
            end: 100
        },
        {
            type: 'inside',
            yAxisIndex: 0,
            start: 0,
            end: 100
        }
    ],
    xAxis: {
        data: getDateArray(),
        axisPointer: {
            show: true
        }
    },
    yAxis: {},
    series: []
};

//获取24小时内每隔一分钟的时间数组（默认）
function getDateArray(endDate, splitTime, count) {
    if (!endDate) {
        endDate = new Date('2000-01-01 23:59:00');
    }
    if (!splitTime) {
        splitTime = 60 * 1000;
    }
    if (!count) {
        count = 1440;
    }
    var endTime = endDate.getTime();
    var mod = endTime % splitTime;
    if (mod > 0) {
        endTime -= mod;
    }
    var dateArray = [];
    while (count-- > 0) {
        var d = new Date();
        d.setTime(endTime - count * splitTime);
        dateArray.push(checkTime(d.getHours()) + ':' + checkTime(d.getMinutes()));
    }
    return dateArray;
}

//小时、分钟小于10时补上前面的0
function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

//响应用户请求，展示某天数据
function showLine() {
    var someday = $('#dateInput').val();
    if (!someday) {
        someday = '2017-06-01'
    }
    if (option.legend.data.length > 4) {
        BootstrapDialog.show({
            size: 'size-wide',
            title: '提示',
            message: $('<div class="imporMsg">不能多于5条数据</div>')
        });
        return;
    }
    if ($.inArray(someday, option.legend.data) >= 0) {
        return;
    }

    tags.addTag(someday);
    $.ajax({
        type: 'GET',
        url: '/flight_minute_orders/date/' + someday
    }).done(function (res) {
        if (res && res.data) {
            var tempSeries = {};
            tempSeries.name = someday;
            tempSeries.type = 'line';
            tempSeries.data = [];
            res.data.forEach(function (ele, index) {
                tempSeries.data.push(ele['orderNum']);
            });
            option.legend.data.push(someday);
            option.series.push(tempSeries);
            echart.setOption(option);
        }
    });
}

//清空图表中的数据
function clearCharts() {
    var length = tags.tagData.length;
    for (var i = 0; i < length; i++) {
        tags.removeLastTag();
    }
}

//使chart容器自适应window大小
function resizeContainer() {
    container.style.width = (window.innerWidth - 300) + 'px';
    container.style.height = (window.innerHeight - 100) + 'px';
}

//window大小变化时，chart自动调整
function resize() {
    //用于使chart自适应高度和宽度
    window.onresize = function () {
        //重置容器高宽
        resizeContainer(container);
        echart.resize();
    };
}

function buildDatepicker() {
    $("#datetimepicker").datepicker({
        format: "yyyy-mm-dd",
        startDate: '2017-06-01',
        endDate: '2017-06-21',
        autoclose: true,
        language: 'zh-CN'
    });
}

function bulidTags() {
    tags = $('#my-tag-list').tags({
        tagData: ["2017-06-01"],
        promptText: "显示在图表中的日期列表",
        tagSize: 'lg',
        afterDeletingTag: function (tag) {
            if ($.inArray(tag, option.legend.data) >= 0) {
                option.legend.data = option.legend.data.filter(function (item) {
                    return tag !== item;
                });
                option.series = option.series.filter(function (item) {
                    return tag !== item.name;
                });
                echart.setOption(option, true);
            }
        }
    });
}