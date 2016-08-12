项目安装说明
===================================

###### 第一步 安装j2ee环境

安装 **java sdk** , **maven** 。
本项目采用 **maven**进行包管理，后期用 **maven插件**进行运行 `mvn tomcat7:run` 或者 `mvn jetty:run`

###### 第二步 安装数据库

本系统采用 **JPA**作为持久化框架 , 我们目前在 **mysql**上测试没有问题，所以请安装一个 **mysql数据库**

###### 第三步 执行初始化语句

创建数据库，并执行数据初始化语句
数据库配置: /src/main/java/config/jdbc.properties 
初始化语句文件 : /doc/install.md