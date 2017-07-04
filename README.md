# echarts-spring-mybatis
使用echarts做数据统计分析，支持折线图的添加、删除、编辑更新至数据库，后端使用spring+mybatis。

JDBC配置已删除，请自己建表配置，表信息如下   


```
CREATE TABLE `flight_minute` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  `order_num` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_datetime` (`datetime`)
) ENGINE=InnoDB AUTO_INCREMENT=30241 DEFAULT CHARSET=utf8mb4 COMMENT='机票每分钟订单量';
```
数据示例：   

```
'1', '2017-06-01 00:00:00', '129'
'2', '2017-06-01 00:01:00', '135'
'3', '2017-06-01 00:02:00', '170'
'4', '2017-06-01 00:03:00', '149'
'5', '2017-06-01 00:04:00', '163'
'6', '2017-06-01 00:05:00', '163'
'7', '2017-06-01 00:06:00', '170'
```
   
![image](https://github.com/hellolvs/echarts-spring-mybatis/blob/master/readme-img/chart.png)
