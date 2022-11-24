## 一、openstack安装

#### 前置准备：

#### 虚拟机

| 节点     | 主机名     | 内存 | 作用       | cpu        | 磁盘空间 |
| -------- | ---------- | ---- | ---------- | ---------- | -------- |
| 控制节点 | controller | 2g   | 管理       | 开启虚拟化 | 60       |
| 计算节点 | compute    | 4g   | 运行虚拟机 | 开启虚拟化 | 60       |

#### 主机名|hosts|防火墙

```
修改主机名     
hostnamectl set-hostname controller
hostnamectl set-hostname compute

关闭防火墙|关闭自动开启防火墙：
sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config; systemctl stop firewalld; systemctl disable firewalld;setenforce 0

hosts解析：
vim /etc/hosts
ip   controller
ip   compute

```

#### 配置时间服务器

```
步骤一：安装 chrony
	controller和compute执行
	yum -y install chrony

步骤二：1、在controller配置
	vi /etc/chrony.conf 
	只留这一段
	server ntp3.aliyun.com iburst 
	allow all
 	local stratum 10

	2、设置开机自启
	systemctl restart chronyd;systemctl enable chronyd
	
	compute
	vi /etc/chrony.conf 
	server controller iburst

步骤三：验证
	1、在控制器节点上运行此命令：
	chronyc sources
	2、在所有其他节点上运行相同的命令：
	chronyc sources

步骤四：controller和compute
	yum install centos-release-openstack-train -y
	需要依赖什么安装的源
	yum install python-openstackclient openstack-selinux -y	
	
```



###### 数据库（controller）

```
1、安装：yum install mariadb mariadb-server python2-PyMySQL -y

2、配置数据库：
vim /etc/my.cnf.d/openstack.cnf 
[mysqld]
bind-address = controller的IP
default-storage-engine = innodb
innodb_file_per_table = on
max_connections = 4096
collation-server = utf8_general_ci
character-set-server = utf8

3、设置开机自启
systemctl enable mariadb.service; systemctl start mariadb.service

4、初始化数据库
mysql_secure_installation

[root@controller ~]# mysql_secure_installation

NOTE: RUNNING ALL PARTS OF THIS SCRIPT IS RECOMMENDED FOR ALL MariaDB
      SERVERS IN PRODUCTION USE!  PLEASE READ EACH STEP CAREFULLY!

In order to log into MariaDB to secure it, we'll need the current
password for the root user.  If you've just installed MariaDB, and
you haven't set the root password yet, the password will be blank,
so you should just press enter here.

Enter current password for root (enter for none): 回车
OK, successfully used password, moving on...

Setting the root password ensures that nobody can log into the MariaDB
root user without the proper authorisation.

Set root password? [Y/n] y  # 设置密码123，确认123
New password: 
Re-enter new password: 
Password updated successfully!
Reloading privilege tables..
 ... Success!


By default, a MariaDB installation has an anonymous user, allowing anyone
to log into MariaDB without having to have a user account created for
them.  This is intended only for testing, and to make the installation
go a bit smoother.  You should remove them before moving into a
production environment.

Remove anonymous users? [Y/n] y
 ... Success!

Normally, root should only be allowed to connect from 'localhost'.  This
ensures that someone cannot guess at the root password from the network.

Disallow root login remotely? [Y/n] n
 ... Success!

By default, MariaDB comes with a database named 'test' that anyone can
access.  This is also intended only for testing, and should be removed
before moving into a production environment.

Remove test database and access to it? [Y/n] y
 - Dropping test database...
 ... Success!
 - Removing privileges on test database...
 ... Success!

Reloading the privilege tables will ensure that all changes made so far
will take effect immediately.

Reload privilege tables now? [Y/n] y
 ... Success!

Cleaning up...

All done!  If you've completed all of the above steps, your MariaDB
installation should now be secure.

Thanks for using MariaDB!
到这里MariaDB的数据库就安装完成了
```

###### 消息队列

```

```



