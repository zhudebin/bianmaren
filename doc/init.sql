/**
 * 初始化语句
 * ---------------------------------------------------------------------------------------------------------------------------------
 */


/** 超级管理员 */
INSERT INTO `t_admin` (`id`, `create_date`, `modify_date`, `department`, `email`, `is_enabled`, `is_locked`, `locked_date`, `login_date`, `login_failure_count`, `login_ip`, `name`, `password`, `username`,`mobile`) 
VALUES ('1', '2015-03-29 12:56:03', '2015-11-07 09:05:32', NULL, 'admin@diange168.com', '1', '0', NULL, '2015-11-07 09:05:32', '0', '0:0:0:0:0:0:0:1', '超级管理员', '96e79218965eb72c92a549dd5a330112', 'admin','18770090755');

/** 本地存储插件 */
INSERT INTO `t_plugin_config` (`id`, `create_date`, `modify_date`, `orders`, `is_enabled`, `plugin_id`) 
VALUES ('1', '2015-11-07 00:59:07', '2015-11-07 00:59:07', '100', '1', 'filePlugin');

/** 角色数据 */
INSERT INTO `t_role` (`id`, `create_date`, `modify_date`, `description`, `is_system`, `name`) 
VALUES ('1', '2015-11-20 00:02:44', '2015-11-20 00:02:44', '拥有管理后台最高权限', '1', '超级管理员');

/** 超级管理员角色*/
insert  into `t_admin_role`(`admins`,`roles`) values (1,1);
insert  into `t_role_authority`(`role`,`authorities`) values
(1,'admin:cache');


/** 系统设置*/
INSERT INTO `t_sys_setting` (`create_date`, `modify_date`, `setting_type`,`is_system`, `name`,  `value`,`remark`) VALUES
(now(), now(),'0', '1', 'siteName','DGCMS',"网站名称"),
(now(), now(),'0', '1', 'siteUrl','http://localhost:8080/DGCMS/',"网站链接"),
(now(), now(),'0', '1', 'systemDescription','DGCMS',"系统描述"),
(now(), now(),'0', '1', 'isSiteEnabled','1',"是否开启网站"),
(now(), now(),'0', '1', 'isRegisterEnabled','1',"是否开发注册"),
(now(), now(),'0', '1', 'imageUploadPath','/upload/image/${.now?string(''yyyyMM'')}/',"图片上传路径"),
(now(), now(),'0', '1', 'flashUploadPath','/upload/flash/${.now?string(''yyyyMM'')}/',"flash上传路径"),
(now(), now(),'0', '1', 'mediaUploadPath','/upload/media/${.now?string(''yyyyMM'')}/',"媒体文件上传路径"),
(now(), now(),'0', '1', 'fileUploadPath','/upload/file/${.now?string(''yyyyMM'')}/',"flash上传路径"),
(now(), now(),'0', '1', 'uploadMaxSize','10',"文件上传最大大小，单位M"),
(now(), now(),'0', '1', 'uploadImageExtension','jpg,jpeg,bmp,gif,png',"允许图片上传的文件后缀"),
(now(), now(),'0', '1', 'uploadFlashExtension','swf,flv',"允许上传的flash文件后缀"),
(now(), now(),'0', '1', 'uploadMediaExtension','swf,flv,mp3,wav,avi,rm,rmvb',"允许上传的媒体文件后缀"),
(now(), now(),'0', '1', 'uploadFileExtension','zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx',"允许上传的文件后缀"),
(now(), now(),'1', '1', '添加管理员','/admin/admin/save.html',null),
(now(), now(),'2', '1', '后台登录页面','/admin/login.ftl','');

