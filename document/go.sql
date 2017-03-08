/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/3/8 11:13:53                            */
/*==============================================================*/


drop table if exists t_admin;

drop table if exists t_news;

drop table if exists t_tournament;

drop table if exists t_tournament_detail;

drop table if exists t_training_address;

drop table if exists t_user;

/*==============================================================*/
/* Table: t_admin                                               */
/*==============================================================*/
create table t_admin
(
   id                   int not null auto_increment,
   name                 varchar(30),
   password             varchar(256),
   phone                varchar(11),
   address              varchar(50),
   type                 int comment '0-最高权限管理员
            1-二级管理员
            2-三级管理员',
   create_datetime      datetime default CURRENT_TIMESTAMP(),
   update_datetime      datetime default CURRENT_TIMESTAMP(),
   primary key (id)
);

alter table t_admin comment '管理员表';

/*==============================================================*/
/* Table: t_news                                                */
/*==============================================================*/
create table t_news
(
   id                   int not null auto_increment,
   title                varchar(255),
   description          longtext,
   type                 int,
   admin_id             int,
   create_datetime      datetime default CURRENT_TIMESTAMP(),
   update_datetime      datetime default CURRENT_TIMESTAMP(),
   primary key (id)
);

alter table t_news comment '新闻表';

/*==============================================================*/
/* Table: t_tournament                                          */
/*==============================================================*/
create table t_tournament
(
   id                   int not null auto_increment,
   name                 varchar(50),
   description          varchar(1024),
   dan_grading          int,
   fee                  int,
   admin_id             int,
   status               int comment '0-可以进行报名
            1-赛事关闭',
   start_datetime       datetime,
   edn_datetime         datetime,
   create_datetime      datetime default CURRENT_TIMESTAMP(),
   update_datetime      datetime default CURRENT_TIMESTAMP(),
   primary key (id)
);

alter table t_tournament comment '赛事表';

/*==============================================================*/
/* Table: t_tournament_detail                                   */
/*==============================================================*/
create table t_tournament_detail
(
   id                   int not null auto_increment,
   name                 int,
   user_id              int,
   status               int,
   pay_type             int comment '0-支付宝
            1-微信
            2-现金',
   pay_account          varchar(50),
   pay_id               int,
   create_datetime      datetime default CURRENT_TIMESTAMP(),
   update_datetime      datetime default CURRENT_TIMESTAMP(),
   primary key (id)
);

alter table t_tournament_detail comment '赛事详情表';

/*==============================================================*/
/* Table: t_training_address                                    */
/*==============================================================*/
create table t_training_address
(
   id                   int not null auto_increment,
   training_name        varchar(50),
   address              varchar(255),
   user_id              int,
   admin_id             int,
   create_datetime      datetime default CURRENT_TIMESTAMP(),
   update_datetime      datetime default CURRENT_TIMESTAMP(),
   primary key (id)
);

alter table t_training_address comment '培训地点表';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   int not null auto_increment,
   user_name            varchar(50),
   passward             varchar(256),
   phone                varchar(11),
   card                 varchar(20),
   wechat               varchar(50),
   qq                   varchar(50),
   dan_grading          int,
   type                 int comment '0-学员
            1-老师
            2-总教练',
   admin_id             int,
   unit                 varchar(50),
   certificate_number   varchar(50),
   certificate_datetime datetime,
   create_datetime      datetime default CURRENT_TIMESTAMP(),
   update_datetime      datetime default CURRENT_TIMESTAMP(),
   primary key (id)
);

alter table t_user comment '用户表（包含学员，总教练，老师）';

