1.正常的crud srpingJdbcTemplate <br/>
2.分库分表 自带mod,hash   支持自定义分库分表路由算法<br/>
3.采用 1pc 实现分布式事务<br/>
4.动态切换datasource(读写分离、主备切换、故障转移) AbstractRoutingDataSource<br/>
5.sql执行监控 druid<br/>
6.支持ehcache、redis 缓存<br/>
7.新增了常用工具类 <br/>
8.新增了基础service，service直接继承，即可自带crud<br/>
9.新增了基础controller，controller继承，即可实现类似jfinal的一些常用封装方法<br/>