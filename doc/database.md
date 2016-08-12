表结构说明
===================================

**管理员表** t_admin

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
username        | String        | 登录名
mobile          | String        | 手机
password        | String        | 密码
email           | String        | 邮箱
name            | String        | 真实姓名
department      | String        | 部门
isEnabled       | Boolean       | 是否启用
isLocked        | Boolean       | 是否锁定
loginFailureCount| Integer      | 连续登录失败次数
lockedDate      | Date          | 锁定日期
loginDate       | Date          | 最后登录日期
loginIp         | String        | 最后登录IP
headPortrait    | String        | 头像
introduction    | String        | 介绍
weiXinScanCode  | String        | 微信捐赠扫码图片
zhiFuBaoScanCode| String        | 支付宝捐赠扫码图片


**角色表** t_role

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
name            | String        | 名称
isSystem        | Boolean       | 是否内置
description     | String        | 描述


**角色权限表** t_role_authority

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
role            | Long          | 角色ID
authorities     | String        | 权限


**文章分类表** t_article_category

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
name            | String        | 名称
seoTitle        | String        | 页面标题
seoKeywords     | String        | 页面关键字
seoDescription  | String        | 页面描述
grade           | String        | 层级
parent          | Long          | 上级分类


**文章标签表** t_tag

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
name            | String        | 标签名字
type            | Integer       | 标签类型
icon            | String        | 标签图标
memo            | String        | 标签备注


**文章表** t_article

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
staticPath      | String        | 静态路径
headImg         | String        | 首图
title           | String        | 标题
author          | String        | 作者
content         | String        | 内容
seoTitle        | String        | 页面标题
seoKeywords     | String        | 页面关键字
seoDescription  | String        | 页面描述
isPublication   | Boolean       | 是否发布
isTop           | Boolean       | 是否置顶
hits            | Long          | 点击数
admin           | Long          | 发布者
reprintedUrl    | String        | 转载地址


**轮播图表** t_carousel_images

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
title           | String        | 标题
url             | String        | 跳转链接
pic             | String        | 图片
is_enable       | Boolean       | 是否启用


**名言警句表** t_famous_aphorism

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
content         | String        | 内容
mrname          | String        | 作者

**友情链接表** t_friend_link

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
name            | String        | 名称
type            | Integer       | 类型
logo            | String        | logo
url             | String        | 链接地址


**系统日志表** t_log

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
operation       | String        | 操作
operator        | String        | 操作员 
content         | String        | 内容
parameter       | String        | 请求参数
ip              | String        | IP 



**导航表** t_navigation

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
name            | String        | 名称
url             | String        | 链接地址
is_enable       | Boolean        | 是否启用

**二级导航表** t_navigation_child

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
name            | String        | 名称
url             | String        | 链接地址
is_enable       | Boolean       | 是否启用
parentNavigation| Long          | 父导航


**系统设置表** t_sys_setting

字段名称        | 字段类型      | 字段说明
--------------|--------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
name            | String        | 设置名称
value           | String        | 设置值
remark          | String        | 备注
is_system       | String        | 是否为系统内置