/*
Navicat MySQL Data Transfer

Source Server         : 10.10.130.149_3306
Source Server Version : 50723
Source Host           : 10.10.130.149:3306
Source Database       : saas

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-04-29 16:38:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_account_verification
-- ----------------------------
DROP TABLE IF EXISTS `t_account_verification`;
CREATE TABLE `t_account_verification` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(32) DEFAULT NULL COMMENT '认证码',
  `account_id` varchar(32) DEFAULT NULL COMMENT '账号id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户绑定表';

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `p_id` varchar(32) DEFAULT NULL COMMENT '父id',
  `area_name` varchar(30) DEFAULT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `area_type` int(11) DEFAULT NULL COMMENT '地区类型1 国家 2 省 3市',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区表';

-- ----------------------------
-- Table structure for t_login_history
-- ----------------------------
DROP TABLE IF EXISTS `t_login_history`;
CREATE TABLE `t_login_history` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `operate_id` varchar(32) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业操作员登录历史表';

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `type` int(11) DEFAULT NULL COMMENT '类型1 公司 2 部门',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `pid` varchar(32) DEFAULT NULL COMMENT '上级id',
  `create_time` datetime DEFAULT NULL COMMENT '新建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司机构表';

-- ----------------------------
-- Table structure for t_organization_user
-- ----------------------------
DROP TABLE IF EXISTS `t_organization_user`;
CREATE TABLE `t_organization_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(32) DEFAULT NULL COMMENT '工号',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `org_id` varchar(32) DEFAULT NULL COMMENT '机构id',
  `create_time` datetime DEFAULT NULL COMMENT '新建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构用户表';

-- ----------------------------
-- Table structure for t_platform_option
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_option`;
CREATE TABLE `t_platform_option` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `opt_name` varchar(32) DEFAULT NULL COMMENT '操作名称',
  `opt_type` int(11) DEFAULT NULL COMMENT '操作类型1添加 2修改3 删除 4查看 5导出 6重置密码',
  `opt_interceptor` varchar(300) DEFAULT NULL COMMENT '操作拦截参数',
  `opt_interceptor_type` int(11) DEFAULT NULL COMMENT '操作拦截类型1 url 2 切面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台操作表';

-- ----------------------------
-- Table structure for t_platform_option_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_option_logs`;
CREATE TABLE `t_platform_option_logs` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `platform_user_id` varchar(32) DEFAULT NULL COMMENT '平台用户id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `user_code` varchar(32) DEFAULT NULL COMMENT '工号',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `opt_type` int(11) DEFAULT NULL COMMENT '操作类型1添加 2修改3 删除 4查看 5导出 6重置密码',
  `logs` varchar(32) DEFAULT NULL COMMENT '日志详情',
  `is_success` int(11) DEFAULT NULL COMMENT '操作结果',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台操作日志';

-- ----------------------------
-- Table structure for t_platform_user
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_user`;
CREATE TABLE `t_platform_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `org_user_id` varchar(32) DEFAULT NULL COMMENT '机构用户id',
  `code` varchar(32) DEFAULT NULL COMMENT '工号冗余字段',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名冗余字段',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '新建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台用户表';

-- ----------------------------
-- Table structure for t_platform_user_system_role
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_user_system_role`;
CREATE TABLE `t_platform_user_system_role` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `system_role_id` varchar(32) DEFAULT NULL COMMENT '系统角色id',
  `platform_user_id` varchar(32) DEFAULT NULL COMMENT '平台用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台用户系统角色表';

-- ----------------------------
-- Table structure for t_profession
-- ----------------------------
DROP TABLE IF EXISTS `t_profession`;
CREATE TABLE `t_profession` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称名称',
  `type` int(11) DEFAULT NULL COMMENT '类别类别：0专业1行业',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专业行业管理表';

-- ----------------------------
-- Table structure for t_school
-- ----------------------------
DROP TABLE IF EXISTS `t_school`;
CREATE TABLE `t_school` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '名称名称',
  `country_id` varchar(32) DEFAULT NULL COMMENT '国家id国家id',
  `province_id` varchar(32) DEFAULT NULL COMMENT '省份id省份id',
  `city_id` varchar(32) DEFAULT NULL COMMENT '城市id城市id',
  `level` int(11) DEFAULT NULL COMMENT '层次层次：0小学，1初中，2高中，3中职，4高职，5本科，6研究生',
  `type` int(11) DEFAULT NULL COMMENT '类型类型：0综合类、1理工类、2师范类、3农林类、4政法类、5医药类、6财经类、7民族类、8语言类、9艺术类、10体育类、11军事类、12旅游类院校,13N/A',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校公司信息表';

-- ----------------------------
-- Table structure for t_system
-- ----------------------------
DROP TABLE IF EXISTS `t_system`;
CREATE TABLE `t_system` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(50) DEFAULT NULL COMMENT '系统在平台的编码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `realm_name` varchar(200) DEFAULT NULL COMMENT '域名',
  `login_url` varchar(200) DEFAULT NULL COMMENT '登录地址',
  `permission_type` int(11) DEFAULT NULL COMMENT '权限类型适配不同平台的权限类型',
  `create_time` datetime DEFAULT NULL COMMENT '新建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统表';

-- ----------------------------
-- Table structure for t_system_account
-- ----------------------------
DROP TABLE IF EXISTS `t_system_account`;
CREATE TABLE `t_system_account` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `account_id` varchar(32) DEFAULT NULL COMMENT '账号id',
  `instruction_profession` varchar(10) DEFAULT NULL COMMENT '授课专业授课专业',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码手机号码',
  `research_direction` varchar(10) DEFAULT NULL COMMENT '研究方向研究方向',
  `mail` varchar(30) DEFAULT NULL COMMENT '邮箱邮箱',
  `profession` varchar(20) DEFAULT NULL COMMENT '专业专业',
  `job` int(11) DEFAULT NULL COMMENT '职务/身份职务/身份,0:xxx1:xx...',
  `grade` varchar(10) DEFAULT NULL COMMENT '年级年级',
  `class_name` varchar(10) DEFAULT NULL COMMENT '班级班级',
  `sno` varchar(10) DEFAULT NULL COMMENT '学号学号',
  `wx_account` varchar(50) DEFAULT NULL COMMENT '微信号微信号',
  `account_status` int(11) DEFAULT NULL COMMENT '账号状态账号状态',
  `identification_code` varchar(30) DEFAULT NULL COMMENT '系统内部识别码系统内部识别码',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注备注',
  `not_accreditation` int(11) DEFAULT NULL COMMENT '是否未认证是否未认证:0否，1是',
  `sec_binding` varchar(20) DEFAULT NULL COMMENT '二级绑定单位二级绑定单位',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户中心详情表';

-- ----------------------------
-- Table structure for t_system_base_account
-- ----------------------------
DROP TABLE IF EXISTS `t_system_base_account`;
CREATE TABLE `t_system_base_account` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `account` varchar(20) DEFAULT NULL COMMENT '账号账号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名姓名',
  `system_id` varchar(32) DEFAULT NULL COMMENT '所属系统id所属系统',
  `t_s_id` varchar(32) DEFAULT NULL COMMENT '学校公_id',
  `sys_create_time` datetime DEFAULT NULL COMMENT '账号创建时间账号创建时间',
  `account_source` int(11) DEFAULT NULL COMMENT '账号来源账号来源:0用户自己注册，1平台管理员开的，2学校管理员开的',
  `account_role` int(11) DEFAULT NULL COMMENT '账号角色账号角色:0老师；1学生；2管理员',
  `enrollment_year` int(11) DEFAULT NULL COMMENT '入学年份入学年份',
  `account_type` int(11) DEFAULT NULL COMMENT '账号类型账号类型:0正式，1试用,2内部使用',
  `expiration_time` datetime DEFAULT NULL COMMENT '账号权限到期时间账号权限到期时间',
  `login_times` int(11) DEFAULT NULL COMMENT '登录次数登录次数',
  `country_id` varchar(32) DEFAULT NULL COMMENT '学校/公司所在国家id学校/公司所在国家',
  `province_id` varchar(32) DEFAULT NULL COMMENT '学校/公司所在省份id学校/公司所在省份',
  `city_id` char(10) DEFAULT NULL COMMENT '学校/公司所在城市id',
  `sch_com_name` varchar(32) DEFAULT NULL COMMENT '学校/公司名称学校/公司名称',
  `customer_type` int(11) DEFAULT NULL COMMENT '客户类型账号类型:0普通用户；1VIP；2VVIP;3一级管理员;4二级管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户中心基础表';

-- ----------------------------
-- Table structure for t_system_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_system_permission`;
CREATE TABLE `t_system_permission` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `system_id` varchar(32) DEFAULT NULL COMMENT '系统id',
  `resource_id` varchar(32) DEFAULT NULL COMMENT '系统资源id',
  `permission` varchar(1000) DEFAULT NULL COMMENT '权限内容',
  `permission_type` varchar(32) DEFAULT NULL COMMENT '内容类型(role,shiro,url)',
  `create_time` datetime DEFAULT NULL COMMENT '新建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Table structure for t_system_permission_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_system_permission_operation`;
CREATE TABLE `t_system_permission_operation` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `system_id` varchar(32) DEFAULT NULL COMMENT '系统id',
  `resource_id` varchar(32) DEFAULT NULL COMMENT '系统资源id',
  `permission_id` varchar(32) DEFAULT NULL COMMENT '权限内容id',
  `operation` varchar(100) DEFAULT NULL COMMENT '操作',
  `operation_name` varchar(100) DEFAULT NULL COMMENT '操作名称',
  `option_script` varchar(1000) DEFAULT NULL COMMENT '操作脚本(后期)',
  `create_time` datetime DEFAULT NULL COMMENT '新建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限操作表';

-- ----------------------------
-- Table structure for t_system_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_system_resource`;
CREATE TABLE `t_system_resource` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `system_id` varchar(32) DEFAULT NULL COMMENT '系统id',
  `resource_name` varchar(100) DEFAULT NULL COMMENT '系统资源名称',
  `pid` varchar(32) DEFAULT NULL COMMENT '上级资源id',
  `is_permission` int(11) DEFAULT NULL COMMENT '是否是权限节点',
  `create_time` datetime DEFAULT NULL COMMENT '新建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源';

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT NULL COMMENT '新建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` int(11) DEFAULT NULL COMMENT '删除标识0 未删除 1已删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '新建人id',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Table structure for t_system_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_resource`;
CREATE TABLE `t_system_role_resource` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `system_role_id` varchar(32) DEFAULT NULL COMMENT '系统角色id',
  `system_resource_id` varchar(32) DEFAULT NULL COMMENT '系统资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色资源表';

-- ----------------------------
-- Table structure for t_user_system
-- ----------------------------
DROP TABLE IF EXISTS `t_user_system`;
CREATE TABLE `t_user_system` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `system_id` varchar(32) DEFAULT NULL COMMENT '系统id',
  `is_collect` int(11) DEFAULT NULL COMMENT '是否收藏：0否，1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_verification_code
-- ----------------------------
DROP TABLE IF EXISTS `t_verification_code`;
CREATE TABLE `t_verification_code` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `code` varchar(32) DEFAULT NULL COMMENT '认证码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户绑定码';
