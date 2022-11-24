## 一、openstack安装

#### 前置准备：

#### 虚拟机

| 节点     | 主机名     | 内存 | 作用       | cpu        | 磁盘空间 |
| -------- | ---------- | ---- | ---------- | ---------- | -------- |
| 控制节点 | controller | 4g   | 管理       | 开启虚拟化 | 50       |
| 计算节点 | compute    | 2g   | 运行虚拟机 | 开启虚拟化 | 50       |

#### 主机名|hosts|防火墙

```
修改主机名     
hostnamectl set-hostname controller
hostnamectl set-hostname compute

主机映射：
vim /etc/hosts
ip   controller
ip   compute

关闭防火墙|关闭自动开启防火墙：
systemctl stop firewalld
systemctl disable firewalld
SELINUX=disabled

```

#### 配置yum源(关键 --->所有节点执行)

```
步骤一：挂载本地镜像
    mount /dev/cdrom /mnt/
    注：如果提示找不到媒体：手动挂载光盘像既可

步骤二：配置本地yum 源  < 所有节点 >
1.vim /etc/yum.repos.d
      [local]
      name=local
      gpgcheck=0
      baseurl=file:///mnt

2.清理缓存
    yum clean all

3.查看repo
    yum repolist;
    注：源名称只有：local 才是正确的 ：如果存在多个源需要把多余的源删除
    [root@controller ~]# yum repolist 
    Loaded plugins: fastestmirror
    Loading mirror speeds from cached hostfile
    repo id               repo name                           status
    local                    local                             3,894

4.在本地yum源配置openstack
    vim /etc/yum.repos.d/local.repo
    [local]
    name=local
    gpgcheck=0
    baseurl=file:///mnt

    [openstack]
    name=openstack
    gpgcheck=0
    baseurl=file:///opt/repo

4.1清理缓存：
	yum clean all

查看：repolist
    [root@controller ~]# yum repolist 
    Loaded plugins: fastestmirror
    Loading mirror speeds from cached hostfile
    repo id               repo name                           status
    local                    local                             3,894
    openstack               openstack                           598
```

###### 配置openstack 包 ----->所有节点配置

```
解压软件包到 /opt 目录     ---->目录可自己指定
tar -zxvf openstack_rpm.tar.gz -C /opt/ 
```



###### 设置时间服务 ----->所有节点配置

```
控制节点
-----
vim /etc/chrony.conf 
-----
server ntp6.aliyun.com iburst   // 都在文件里面自行查找修改
-----
allow ip地址           // 设置同步的网段，  也可以设置所有:    all
-----

- 重启服务
systemctl restart chronyd
systemctl enable chronyd

 计算节点
vim /etc/chrony.conf        // 都在文件里面自行查找修改
……
server IP地址 iburst

- 重启服务
systemctl restart chronyd
systemctl enable chronyd
```



###### 安装openstack 客户端  ----->所有节点配置

```
安装：openstack客户端：
yum -y install python-openstackclient   //如果获取不到 说明yum源配置错误

-----------------------------------------------------------------------

安装：openstack-selinux   //软件包可自动管理 openstack的安全策略
 yum -y install openstack-selinux  
```



