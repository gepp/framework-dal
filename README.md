1.正常的crud srpingJdbcTemplate <br/>
2.分库分表 自带mod,hash   支持自定义分库分表路由算法<br/>
3.采用 1pc 实现分布式事务<br/>
4.动态切换datasource(读写分离、主备切换、故障转移) AbstractRoutingDataSource<br/>
5.sql执行监控 druid<br/>
6.支持ehcache、redis 缓存<br/>
7.新增了常用工具类 <br/>
8.新增了基础service，service直接继承，即可自带crud<br/>
9.新增了基础controller baseController<br/>
10.全局异常处理HandlerExceptionResolver,使用spring的SimpleMappingExceptionResolver<br/>
11.XSSFilter 特殊字符过滤<br/>
12.新增IdWorker，采用twitter 的snowflake 全局ID生成策略 <br/>

1.0.8更新 实体类解析使用lombok 简化实体类操作<br/>
