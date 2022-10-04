# 本文档仅供大数据国赛班内阅，禁止传阅！
 
@Author Matthew

@Timestamp Sat Jul 9 21:27



| node   | ip     | jps/server                                              |
|--------|--------|---------------------------------------------------------|
| master | cell 2 | NameNode<br/>SecondrayNameNode<br/>ResourceManager<br/> |
| slave1 | cell 4 | DataNode<br/>                                           |
| slave2 | cell 4 |                                                         |


## Hadoop 完全分布式安装配置

本环节需要使用root用户完成相关配置，安装Hadoop需要配置前置环境。
命令中要求使用绝对路径，具体要求如下:

1、将Master节点JDK安装包解压并移动到/usr/java路径(若路径不存在，则需新建)，
将命令复制并粘贴至对应报告中;

```shell
[root@master ~]# ls /opt/soft/
flink-1.10.2-bin-scala_2.11.tgz          scala-2.11.11.tgz
hadoop-2.7.7.tar.gz                      spark-2.1.1-bin-hadoop2.7.tgz
jdk-8u211-linux-x64.tar.gz               zookeeper-3.4.9.tar.gz
kafka_2.11-2.0.0.tgz                     

[root@master ~]# ls -lh /opt/soft/
total 2.2G
-rw-r--r--. 1 hadoop hadoop 277M May 13 15:16 flink-1.10.2-bin-scala_2.11.tgz
-rw-r--r--. 1 hadoop hadoop 209M May 13 15:16 hadoop-2.7.7.tar.gz
-rw-r--r--. 1 hadoop hadoop 186M May 13 15:15 jdk-8u211-linux-x64.tar.gz
-rw-r--r--. 1 hadoop hadoop  54M May 13 15:15 kafka_2.11-2.0.0.tgz
-rw-r--r--. 1 hadoop hadoop  28M May 13 15:15 scala-2.11.11.tgz
-rw-r--r--. 1 hadoop hadoop  22M May 13 15:15 zookeeper-3.4.9.tar.gz
-rw-r--r--. 1 hadoop hadoop 192M May 13 15:15 spark-2.1.1-bin-hadoop2.7.tgz

[root@master ~]# chmod 755 /opt/soft/*

[root@master ~]# ls -lh /opt/soft/
total 2.2G
-rwxr-xr-x. 1 hadoop hadoop 277M May 13 15:16 flink-1.10.2-bin-scala_2.11.tgz
-rwxr-xr-x. 1 hadoop hadoop 209M May 13 15:16 hadoop-2.7.7.tar.gz
-rwxr-xr-x. 1 hadoop hadoop 186M May 13 15:15 jdk-8u211-linux-x64.tar.gz
-rwxr-xr-x. 1 hadoop hadoop  54M May 13 15:15 kafka_2.11-2.0.0.tgz
-rwxr-xr-x. 1 hadoop hadoop  28M May 13 15:15 scala-2.11.11.tgz
-rwxr-xr-x. 1 hadoop hadoop 192M May 13 15:15 spark-2.1.1-bin-hadoop2.7.tgz
-rwxr-xr-x. 1 hadoop hadoop  22M May 13 15:15 zookeeper-3.4.9.tar.gz

[root@master ~]# mkdir /usr/java

[root@master ~]# tar -zxvf /opt/soft/jdk-8u211-linux-x64.tar.gz -C /usr/java/

[root@master ~]# ls /usr/java/
jdk1.8.0_211

```

2、修改/root/profile文件，设置JDK环境变量，
配置完毕后在Master节点分别执行“java”和“javac”命令，
将命令行执行结果分别截图并粘贴至对应报告中;

```shell
[root@master ~]# vim /etc/profile
...

export JAVA_HOME=/usr/java/jdk1.8.0_211/
export PATH=$PATH:$JAVA_HOME/bin

...

[root@master ~]# source /etc/profile

[root@master ~]# javac
Usage: javac <options> <source files>
where possible options include:
  -g                         Generate all debugging info
  -g:none                    Generate no debugging info
  -g:{lines,vars,source}     Generate only some debugging info
  -nowarn                    Generate no warnings

...

[root@master ~]# java
Usage: java [-options] class [args...]
           (to execute a class)
   or  java [-options] -jar jarfile [args...]
           (to execute a jar file)
where options include:
    -d32	  use a 32-bit data model if available
    -d64	  use a 64-bit data model if available
    -server	  to select the "server" VM
                  The default VM is server,
                  because you are running on a server-class machine.
...

[root@master ~]# java -version
java version "1.8.0_211"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)

```

3、请完成host相关配置，将三个节点分别命名为master、slave1、slave2，并做免密登录，
使用绝对路径从Master节点复制JDK解压后的安装文件到Slave1、Slave2节点，
并配置相关环境变量，将全部复制命令复制并粘贴至对应报告中;

```shell
# ------------------------------------------------------------------------
# OPT 以下步骤若有
[root@master ~]# vim /etc/hostname
master

[root@master ~]# reboot

# 其余节点重复以上配置

# ------------------------------------------------------------------------



# ------------------------------------------------------------------------

[root@master ~]# sudo vim /etc/hosts

[root@master ~]# vim /etc/hosts
127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4
::1         localhost localhost.localdomain localhost6 localhost6.localdomain6

# 集群环境使用公网IP时启动hadoop出现端口占用异常，更换节点使用内网IP
# 192.168.66.58 master
# 192.168.66.225 slave1
# 192.168.66.241 slave2

192.168.100.20 master
192.168.100.110 slave1
192.168.100.108 slave2

# 其余节点重复以上配置并重复检查

# ------------------------------------------------------------------------



# ------------------------------------------------------------------------

[root@master ~]# ls -al
total 80
dr-xr-x---.  3 root root   186 Jul  9 21:11 .
dr-xr-xr-x. 17 root root   242 Jun 30 17:18 ..
-rw-------.  1 root root  6376 Jul  8 23:37 .bash_history
-rw-r--r--.  1 root root    18 Dec 29  2013 .bash_logout
-rw-r--r--.  1 root root   176 Dec 29  2013 .bash_profile
-rw-r--r--.  1 root root   176 Dec 29  2013 .bashrc
drwxr-xr-x.  3 root root    17 Jun 18 11:28 .cache
-rw-r--r--.  1 root root   100 Dec 29  2013 .cshrc
-rw-r--r--.  1 root root   129 Dec 29  2013 .tcshrc
-rw-------.  1 root root  4703 Jul  9 21:09 .viminfo
-rw-------.  1 root root  1324 May 10 19:00 anaconda-ks.cfg
-rw-r--r--.  1 root root 39540 May 18 10:24 zookeeper.out

[root@master ~]# ssh-keygen -t rsa
Generating public/private rsa key pair.
Enter file in which to save the key (/root/.ssh/id_rsa): 
Created directory '/root/.ssh'.
Enter passphrase (empty for no passphrase): 
Enter same passphrase again: 
Your identification has been saved in /root/.ssh/id_rsa.
Your public key has been saved in /root/.ssh/id_rsa.pub.
The key fingerprint is:
SHA256:XJS9OZdIfH/ULiYuu20HjUxIh21aVuMblRqKvsCN21E root@master
The key's randomart image is:
+---[RSA 2048]----+
|          .* .o +|
|         .+ Xo.+o|
|         .oO.=== |
|       . ooEB.=o+|
|      . S .+ B...|
|       + +. = .  |
|        + oo .   |
|       . o... .  |
|          .o..   |
+----[SHA256]-----+

[root@master ~]# ls -lh .ssh/
total 8.0K
-rw-------. 1 root root 1.7K Jul  9 21:55 id_rsa
-rw-r--r--. 1 root root  393 Jul  9 21:55 id_rsa.pub

[root@master ~]# cd .ssh

[root@master .ssh]# ls
id_rsa  id_rsa.pub

[root@master .ssh]# ls -lh
total 12K
-rw-r--r--. 1 root root  393 Jul  9 22:00 authorized_keys
-rw-------. 1 root root 1.7K Jul  9 21:55 id_rsa
-rw-r--r--. 1 root root  393 Jul  9 21:55 id_rsa.pub

[root@master .ssh]# chmod 600 authorized_keys 

[root@master .ssh]# ls -lh
total 12K
-rw-------. 1 root root  393 Jul  9 22:00 authorized_keys
-rw-------. 1 root root 1.7K Jul  9 21:55 id_rsa
-rw-r--r--. 1 root root  393 Jul  9 21:55 id_rsa.pub

[root@master .ssh]# tail authorized_keys 
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC/m5XdbGq7kVgDCP4tMUAr0iYxuDiY+GYQCtgSzAjOVzWoCQmV0qtXidNVjLItNUP/jgVad8/KD2SMmGf1irzy+LqvJ2QTla1OjV8XJhubWZ9LPUQYeV3dQfyC9To9/FaC39Coqg49E9NmMnTNxm1vqYrE+eEoufRqVzksRicPqgDnkqcAj/lGInUoBJDh/Mt2U8NNIXkXSHrkLO9PzVVYn+AEPALloL9AJzn/ob50HpMgK6rTFUMUnZ6+Z3R7Y+G3NuUg6e7kJh/EDfu0b2e6n0txmBV8IZT7gqskUoGajlVTRbI6Taf8jbGZJa2CokrS6SwlYGbwcNlXglUnduBz root@master

[root@master ~]# ssh-copy-id -i ~/.ssh/id_rsa.pub root@slave1
/usr/bin/ssh-copy-id: INFO: Source of key(s) to be installed: "/root/.ssh/id_rsa.pub"
/usr/bin/ssh-copy-id: INFO: attempting to log in with the new key(s), to filter out any that are already installed
/usr/bin/ssh-copy-id: INFO: 1 key(s) remain to be installed -- if you are prompted now it is to install the new keys
root@slave1's password: 

Number of key(s) added: 1

Now try logging into the machine, with:   "ssh 'root@slave1'"
and check to make sure that only the key(s) you wanted were added.

# 当前节点master也需要免密登陆自身
[root@master ~]# ssh-copy-id -i ~/.ssh/id_rsa.pub root@master
...

# 测试ssh免密登陆所有节点
[root@master ~]# ssh root@slave1
Last login: Wed Jun 22 03:34:48 2022 from master

# 其余节点重复以上配置

# 节点分发/usr/java，注意使用绝对路径
[root@master ~]# scp -r /usr/java/ root@slave1:/usr/
[root@master ~]# scp -r /usr/java/ root@slave2:/usr/
...

# ------------------------------------------------------------------------



# ------------------------------------------------------------------------

[root@master ~]# cat /etc/profile
...
!!! 注意 export 位置

export JAVA_HOME=/usr/java/jdk1.8.0_211/
export PATH=$PATH:$JAVA_HOME/bin
...

# 分发java
[root@master opt]# scp -r /usr/java/ root@slave1:/usr/
[root@master opt]# scp -r /usr/java/ root@slave2:/usr/

# 使用 scp 方式传送配置
[root@master ~]# scp /etc/profile root@slave1:/etc/
profile                                  100% 1889     1.4MB/s   00:00    

[root@slave1 ~]# source /etc/profile

# 其余节点补充配置java环境变量，测试java配置生效 

```

4、在Master节点将Hadoop解压到/opt目录下，并将解压包分发至Slave1、Slave2节点中，
配置好相关环境，初始化Hadoop环境namenode，将初始化命令及初始化结果复制粘贴至对应报告中；

```shell
[root@master ~]# tar -zxvf /opt/soft/hadoop-2.7.7.tar.gz -C /opt/

# 准备hadoop hdfs数据存储目录，并授权最高，必须授权否则启动异常
# 将新建好的目录对应填入hdfs-site.xml配置文件
[root@master ~]# mkdir /opt/hdfs
[root@master ~]# mkdir /opt/hdfs/namenode
[root@master ~]# mkdir /opt/hdfs/datanode
[root@master ~]# mkdir /opt/hdfs/tmp
[root@master ~]# chmod 777 -R /opt/hdfs/

[root@master ~]# ls /opt/hadoop-2.7.7/
LICENSE.txt  README.txt  etc      lib      sbin
NOTICE.txt   bin         include  libexec  share

[root@master ~]# cd /opt/hadoop-2.7.7/etc/hadoop/

[root@master hadoop]# ls
capacity-scheduler.xml      kms-env.sh
configuration.xsl           kms-log4j.properties
container-executor.cfg      kms-site.xml
core-site.xml               log4j.properties
hadoop-env.cmd              mapred-env.cmd
hadoop-env.sh               mapred-env.sh
hadoop-metrics.properties   mapred-queues.xml.template
hadoop-metrics2.properties  mapred-site.xml.template
hadoop-policy.xml           slaves
hdfs-site.xml               ssl-client.xml.example
httpfs-env.sh               ssl-server.xml.example
httpfs-log4j.properties     yarn-env.cmd
httpfs-signature.secret     yarn-env.sh
httpfs-site.xml             yarn-site.xml
kms-acls.xml

[root@master hadoop]# vim hadoop-env.sh
...
# 使用绝对路径修改环境变量参数
...
# The java implementation to use.
export JAVA_HOME=/usr/java/jdk1.8.0_211/
...

# core-site.xml 需要手写，最小配置项（不一定需要配置）
        <property>
            <name>fs.default.name</name>
            <value>hdfs://master:8020</value>
        </property>
        <property>
            <name>hadoop.tmp.dir</name>
            <value>/opt/hdfs/tmp</value>
        </property>
  
# hdfs-site.xml 需要手写，最小配置项
!!! 注意dir前置路径
        <property>
          <name>dfs.namenode.secondary.http-address</name>
          <value>master:50090</value>
        </property>
        <property>
          <name>dfs.namenode.http-address</name>
          <value>master:50070</value>
        </property>
        <property>
          <name>dfs.namenode.name.dir</name>
          <value>file:///opt/hdfs/namenode</value>
        </property>
        <property>
          <name>dfs.datanode.data.dir</name>
          <value>file:///opt/hdfs/datanode</value>
        </property>       
	    <property>
          <name>dfs.replication</name>
          <value>1</value>
        </property>
        <property>
          <name>dfs.permissions</name>
          <value>true</value>
        </property>

# yarn-site.xml 需要手写，最小配置项
        <property>
            <name>yarn.resourcemanager.hostname</name>
            <value>master</value>
        </property>  
  
[root@master hadoop]# cp mapred-site.xml.template mapred-site.xml
	
[root@master hadoop]# vim slaves
slave1
slave2

[root@master ~]# vim /etc/profile
...

export SCALA_HOME=/usr/scala/scala-2.11.11
export JAVA_HOME=/usr/java/jdk1.8.0_211
export HADOOP_HOME=/opt/hadoop-2.7.7
export export PATH=$JAVA_HOME/bin:$SCALA_HOME/bin:$HADOOP_HOME/sbin:$HADOOP_HOME:/bin:$PATH

...

[root@master ~]# source /etc/profile

# 节点分发hadoop
[root@master opt]# scp -r /opt/hadoop-2.7.7/ root@slave1:/opt/
[root@master opt]# scp -r /opt/hadoop-2.7.7/ root@slave2:/opt/

# 节点分发profile
[root@master ~]# scp /etc/profile root@slave1:/etc/ 
profile                          100% 1959     1.9KB/s   00:00    
[root@master ~]# scp /etc/profile root@slave2:/etc/ 
profile                          100% 1959     1.9KB/s   00:00

```

5、启动Hadoop集群，查看Master节点jps进程，将查看结果复制粘贴至对应报告中。

```shell
# 格式化namenode，仅master节点
[root@master ~]# hdfs namenode -format
22/07/10 10:41:48 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = master/192.168.66.58
STARTUP_MSG:   args = [format]
STARTUP_MSG:   version = 2.7.7
...

# 格式化datanode，仅master节点
[root@master ~]# hdfs datanode -format
22/07/10 10:42:58 INFO datanode.DataNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting DataNode
STARTUP_MSG:   host = master/192.168.66.58
STARTUP_MSG:   args = [format]
STARTUP_MSG:   version = 2.7.7
...

# 启动hadoop，仅master节点
[root@master opt]# cd hadoop-2.7.7/sbin/
[root@master sbin]# ls
distribute-exclude.sh    start-all.cmd        stop-balancer.sh
hadoop-daemon.sh         start-all.sh         stop-dfs.cmd
hadoop-daemons.sh        start-balancer.sh    stop-dfs.sh
hdfs-config.cmd          start-dfs.cmd        stop-secure-dns.sh
hdfs-config.sh           start-dfs.sh         stop-yarn.cmd
httpfs.sh                start-secure-dns.sh  stop-yarn.sh
kms.sh                   start-yarn.cmd       yarn-daemon.sh
mr-jobhistory-daemon.sh  start-yarn.sh        yarn-daemons.sh
refresh-namenodes.sh     stop-all.cmd
slaves.sh                stop-all.sh


[root@master sbin]# ./start-dfs.sh
Starting namenodes on [master]
master: starting namenode, logging to /opt/hadoop-2.7.7/logs/hadoop-root-namenode-master.out
slave2: starting datanode, logging to /opt/hadoop-2.7.7/logs/hadoop-root-datanode-slave2.out
slave1: starting datanode, logging to /opt/hadoop-2.7.7/logs/hadoop-root-datanode-slave1.out
Starting secondary namenodes [master]
master: starting secondarynamenode, logging to /opt/hadoop-2.7.7/logs/hadoop-root-secondarynamenode-master.out

# 查看所有节点启动项服务
[root@master sbin]# jps
11155 NameNode
11480 Jps
11356 SecondaryNameNode

[root@master sbin]# ssh root@slave1
Last login: Sun Jul 10 11:59:33 2022 from master
[root@slave1 ~]# jps
6534 Jps
6409 DataNode

# 注意确保hdfs启动完成无误后再启动yarn

[root@master sbin]# ./start-yarn.sh
starting yarn daemons
starting resourcemanager, logging to /opt/hadoop-2.7.7/logs/yarn-root-resourcemanager-master.out
slave1: starting nodemanager, logging to /opt/hadoop-2.7.7/logs/yarn-root-nodemanager-slave1.out
slave2: starting nodemanager, logging to /opt/hadoop-2.7.7/logs/yarn-root-nodemanager-slave2.out

# 再次查看所有节点启动项服务，最终服务项贴入报告
[root@master sbin]# jps
11155 NameNode
11543 ResourceManager
11356 SecondaryNameNode
11839 Jps

[root@slave1 ~]# jps
6409 DataNode
6585 NodeManager
6729 Jps
```

## Hive 安装配置
本环节需要使用root用户完成相关配置，已安装Hadoop及需要配置前置环境，具体部署要求如下：

1、将Master节点Hive安装包解压到/opt目录下，将命令复制并粘贴至粘贴至对应报告中；
```shell
[root@master opt]# tar -zxvf /opt/app/apache-hive-2.3.4-bin.tar.gz -C /opt/
```

2、设置Hive环境变量，并使环境变量生效，并将环境变量配置内容复制并粘贴至粘贴至对应报告中；
```shell
[root@master ~]# vim /etc/profile
...
export SCALA_HOME=/usr/scala/scala-2.11.11
export JAVA_HOME=/usr/java/jdk1.8.0_211
export HADOOP_HOME=/opt/hadoop-2.7.7
export SPARK_HOME=/opt/spark-2.1.1-bin-hadoop2.7
export FLINK_HOME=/opt/flink-1.10.2
export HIVE_HOME=/opt/hive-2.3.4
export PATH=$JAVA_HOME/bin:$SCALA_HOME/bin:$HADOOP_HOME/sbin:$HADOOP_HOME/bin:$SPARK_HOME/sbin:$SPARK_HOME/bin:$FLINK_HOME/bin:$HIVE_HOME/bin:$PATH
...

# 节点分发profile
[root@master ~]# scp /etc/profile root@slave1:/etc/ 
profile                          100% 1959     1.9KB/s   00:00    
[root@master ~]# scp /etc/profile root@slave2:/etc/ 
profile                          100% 1959     1.9KB/s   00:00

# 所有节点检查更新环境变量
[root@master ~]# source /etc/profile

[root@master ~]# echo $HIVE_HOME
/opt/hive-2.3.4
```

3、完成相关配置并添加所依赖包，将MySQL数据库作为Hive元数据库。
初始化Hive元数据，并通过schematool相关命令执行初始化，将初始化结果复制粘贴至粘贴至对应报告中。
```shell
[root@master conf]# cp hive-env.sh.template hive-env.sh

# 准备mysql作为hive metadata数据库的连接配置
jdbc:mysql://localhost:3306/hive
root
12345678
com.mysql.jdbc.Driver

[root@master conf]# cp hive-default.xml.template hive-default.xml

[root@master conf]# vim hive-default.xml
  <property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://localhost:3306/hive</value>
    <description>
      JDBC connect string for a JDBC metastore.
      To use SSL to encrypt/authenticate the connection, provide database-specific SSL flag in the connection URL.
      For example, jdbc:postgresql://myhost/db?ssl=true for postgres database.
    </description>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionUserName</name>
    <value>root</value>
    <description>Username to use against metastore database</description>
  </property> 
  <property>
    <name>javax.jdo.option.ConnectionPassword</name>
    <value>12345678</value>
    <description>password to use against metastore database</description>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.jdbc.Driver</value>
    <description>Driver class name for a JDBC metastore</description>
  </property>

```

## Spark on Yarn安装配置
本环节需要使用root用户完成相关配置，已安装Hadoop及需要配置前置环境，具体要求如下：

1、将scala包解压到/usr/路径，配置环境变量使其生效，将完整命令复制粘贴至对应报告中（若已安装，则可跳过）；

```shell
export SCALA_HOME=/usr/java/scala-2.11.11/

[root@master opt]# tar -zxvf app/scala-2.11.11.tgz -C /usr/scala/

[root@master opt]# ls /usr/scala/
scala-2.11.11

# 分发scala
[root@master opt]# scp -r /usr/scala/ root@slave1:/usr/
[root@master opt]# scp -r /usr/scala/ root@slave2:/usr/

# 所有节点测试scala环境
[root@master ~]# scala -version
Scala code runner version 2.11.11 -- Copyright 2002-2017, LAMP/EPFL
[root@master ~]# scala
Welcome to Scala 2.11.11 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_211).
Type in expressions for evaluation. Or try :help.

scala> println(1 to 10)
Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

scala> :q

```

2、配置/ect/profile文件，设置Spark环境变量，并使环境变量生效将环境变量配置内容复制粘贴至对应报告中;

```shell
[root@master ~]# tar -zxvf /opt/app/spark-2.1.1-bin-hadoop2.7.tgz -C /opt/

[root@master spark-2.1.1-bin-hadoop2.7]# vim /etc/profile
...
export SCALA_HOME=/usr/scala/scala-2.11.11
export JAVA_HOME=/usr/java/jdk1.8.0_211
export HADOOP_HOME=/opt/hadoop-2.7.7
export SPARK_HOME=/opt/spark-2.1.1-bin-hadoop2.7
export PATH=$JAVA_HOME/bin:$SCALA_HOME/bin:$HADOOP_HOME/sbin:$HADOOP_HOME/bin:$SPARK_HOME/sbin:$SPARK_HOME/bin:$PATH
...

[root@master spark-2.1.1-bin-hadoop2.7]# source /etc/profile
```


3、完成on yarn相关配置，使用spark on yarn 的模式提交$SPARK_HOME/examples/jars/spark-examples_2.11-2.1.1.jar 
运行的主类为org.apache.spark.examples.SparkPi，将运行结果粘贴至对应报告中。
```shell
[root@master spark-2.1.1-bin-hadoop2.7]# cd conf/


[root@master conf]# ls
docker.properties.template   slaves.template
fairscheduler.xml.template   spark-defaults.conf.template
log4j.properties.template    spark-env.sh.template
metrics.properties.template

[root@master conf]# cp slaves.template slaves

[root@master conf]# vim slaves
slave1
slave2

[root@master conf]# cp spark-env.sh.template spark-env.sh

# 复制hadoop conf路径
[root@master conf]# ls /opt/hadoop-2.7.7/etc/hadoop/

[root@master conf]# vim spark-env.sh
export HADOOP_CONF_DIR=/opt/hadoop-2.7.7/etc/hadoop

# 节点分发spark
[root@master opt]# scp -r /opt/spark-2.1.1-bin-hadoop2.7/ root@slave1:/opt/
[root@master opt]# scp -r /opt/spark-2.1.1-bin-hadoop2.7/ root@slave2:/opt/

./bin/spark-submit --class org.apache.spark.examples.SparkPi \
    --master yarn \
    --deploy-mode cluster \
    --driver-memory 2g \
    --executor-memory 1g \
    --executor-cores 1 \
    examples/jars/spark-examples_2.11-2.1.1.jar \
    10

[root@master spark-2.1.1-bin-hadoop2.7]# ./bin/spark-submit --class org.apache.spark.examples.SparkPi \
>     --master yarn \
>     --deploy-mode cluster \
>     --driver-memory 2g \
>     --executor-memory 1g \
>     --executor-cores 1 \
>     examples/jars/spark-examples_2.11-2.1.1.jar \
>     10

# 执行任务结果
22/07/10 15:14:11 INFO yarn.Client: Application report for application_1657437032066_0002 (state: FINISHED)
22/07/10 15:14:11 INFO yarn.Client: 
	 client token: N/A
	 diagnostics: N/A
	 ApplicationMaster host: 192.168.100.110
	 ApplicationMaster RPC port: 0
	 queue: default
	 start time: 1657437237120
	 final status: SUCCEEDED
	 tracking URL: http://master:8088/proxy/application_1657437032066_0002/
	 user: root
22/07/10 15:14:11 INFO util.ShutdownHookManager: Shutdown hook called
22/07/10 15:14:11 INFO util.ShutdownHookManager: Deleting directory /tmp/spark-df4e9e87-b003-4572-ac23-104712c4eb48
   
# 在slave1节点上查看job执行结果日志
[root@slave1 container_1657437032066_0002_01_000001]# pwd
/opt/hadoop-2.7.7/logs/userlogs/application_1657437032066_0002/container_1657437032066_0002_01_000001
[root@slave1 container_1657437032066_0002_01_000001]# tail stdout 
Pi is roughly 3.141175141175141
```

## 任务三：Flink on Yarn安装配置

本环节需要使用root用户完成相关配置，已安装Hadoop及需要配置前置环境，具体要求如下：


1、将Flink包解压到路径/opt目录下，将完整命令复制粘贴至对应报告中;
```shell
[root@master opt]# tar -zxvf /opt/app/flink-1.10.2-bin-scala_2.11.tgz -C /opt/

```

2、修改/root/profile文件，设置Flink环境变量，并使环境变量生效将环境变量配置内容复制粘贴至对应报告中;
```shell
[root@master conf]# pwd
/opt/flink-1.10.2/conf

[root@master conf]# vim slaves
slave1
slave2

[root@master conf]# vim masters
master:8081

[root@master conf]# vim flink-conf.yaml
...
jobmanager.rpc.address: master
...

[root@master flink-1.10.2]# vim /etc/profile
...
export SCALA_HOME=/usr/scala/scala-2.11.11
export JAVA_HOME=/usr/java/jdk1.8.0_211
export HADOOP_HOME=/opt/hadoop-2.7.7
export SPARK_HOME=/opt/spark-2.1.1-bin-hadoop2.7
export FLINK_HOME=/opt/flink-1.10.2
export PATH=$JAVA_HOME/bin:$SCALA_HOME/bin:$HADOOP_HOME/sbin:$HADOOP_HOME/bin:$SPARK_HOME/sbin:$SPARK_HOME/bin:$FLINK_HOME/bin:$PATH
...

# 节点分发flink
[root@master opt]# scp -r /opt/flink-1.10.2/ root@slave1:/opt/
[root@master opt]# scp -r /opt/flink-1.10.2/ root@slave2:/opt/

# 所有节点测试配置生效
[root@master opt]# source /etc/profile

[root@master opt]# flink -v
Version: 1.10.2, Commit ID: 68bb8b6

```

3、开启Hadoop集群，在yarn上以per job模式（即Job分离模式，不采用Session模式）运行 $FLINK_HOME/examples/batch/WordCount.jar，将运行结果最后10行复制粘贴至对应报告中。
示例 ：
flink run -m yarn-cluster -p 2 -yjm 2G -ytm 2G $FLINK_HOME/examples/batch/WordCount.jar
```shell
# 加载所有hadoop库，该步骤解决执行flink job时未加载HADOOP_CLASSPATH异常
!!!重要
export HADOOP_CLASSPATH=`hadoop classpath`

[root@master flink-1.10.2]# echo $HADOOP_CLASSPATH
/opt/hadoop-2.7.7/etc/hadoop:/opt/hadoop-2.7.7/share/hadoop/common/lib/*:/opt/hadoop-2.7.7/share/hadoop/common/*:/opt/hadoop-2.7.7/share/hadoop/hdfs:/opt/hadoop-2.7.7/share/hadoop/hdfs/lib/*:/opt/hadoop-2.7.7/share/hadoop/hdfs/*:/opt/hadoop-2.7.7/share/hadoop/yarn/lib/*:/opt/hadoop-2.7.7/share/hadoop/yarn/*:/opt/hadoop-2.7.7/share/hadoop/mapreduce/lib/*:/opt/hadoop-2.7.7/share/hadoop/mapreduce/*:/opt/hadoop-2.7.7/etc/hadoop:/opt/hadoop-2.7.7/share/hadoop/common/lib/*:/opt/hadoop-2.7.7/share/hadoop/common/*:/opt/hadoop-2.7.7/share/hadoop/hdfs:/opt/hadoop-2.7.7/share/hadoop/hdfs/lib/*:/opt/hadoop-2.7.7/share/hadoop/hdfs/*:/opt/hadoop-2.7.7/share/hadoop/yarn/lib/*:/opt/hadoop-2.7.7/share/hadoop/yarn/*:/opt/hadoop-2.7.7/share/hadoop/mapreduce/lib/*:/opt/hadoop-2.7.7/share/hadoop/mapreduce/*:/opt/hadoop-2.7.7/lib:/opt/hadoop-2.7.7/contrib/capacity-scheduler/*.jar:/opt/hadoop-2.7.7/contrib/capacity-scheduler/*.jar

flink run \
-m yarn-cluster \
-p 2 \
-yjm 2G \
-ytm 2G \
$FLINK_HOME/examples/batch/WordCount.jar

[root@master flink-1.10.2]# flink run \
> -m yarn-cluster \
> -p 2 \
> -yjm 2G \
> -ytm 2G \
> $FLINK_HOME/examples/batch/WordCount.jar

(weary,1)
(when,2)
(whips,1)
(who,2)
(whose,1)
(will,1)
(wish,1)
(would,2)
(wrong,1)
(you,1)
```



启动不成功时查看日志
[root@master logs]# cat hadoop-root-namenode-master.log

!!! 启动时端口占用解决：更换外网IP与内网IP解决，与比赛无关


netstat -npl|grep 50070

2022-07-10 11:13:42,389 INFO org.apache.hadoop.http.HttpServer2: HttpServer.start() threw a non Bind IOException
java.net.BindException: Port in use: master:50070

重启hadoop时目录文件不同步冲突异常，重启一定要格式化namenode和datanode
每次格式化前一定要运行停止所有服务命令
./stop-yarn.sh
./stop-dfs.sh

2022-07-10 11:32:36,702 ERROR org.apache.hadoop.hdfs.server.namenode.NameNode: Failed to start namenode.
org.apache.hadoop.hdfs.server.common.InconsistentFSStateException: Directory /opt/hdfs/namenode is in an inconsistent state: storage directory does not exist or is not accessible.

清空自定义的/opt/hdfs/namenode目录
授权777权限至/opt/hdfs/目录

# namenode未格式化异常
2022-07-10 11:50:55,292 ERROR org.apache.hadoop.hdfs.server.namenode.NameNode: Failed to start namenode.
java.io.IOException: NameNode is not formatted.

# 注意项

每一小步必须重复检查，基础步骤必须重复检查
分发操作或所有节点配置时必须重复检查

出问题时注意排查最新状态log

注意启动hadoop两个服务顺序


spark提交yarn时报错排查
```shell
22/07/10 14:45:27 INFO yarn.Client: Deleted staging directory hdfs://master:8020/user/root/.sparkStaging/application_1657425866513_0001
Exception in thread "main" org.apache.hadoop.yarn.exceptions.YarnException: Failed to submit application_1657425866513_0001 to YARN : Application application_1657425866513_0001 submitted by user root to unknown queue: thequeue
	at org.apache.hadoop.yarn.client.api.impl.YarnClientImpl.submitApplication(YarnClientImpl.java:271)
	at org.apache.spark.deploy.yarn.Client.submitApplication(Client.scala:176)
	at org.apache.spark.deploy.yarn.Client.run(Client.scala:1167)
	at org.apache.spark.deploy.yarn.Client$.main(Client.scala:1226)
	at org.apache.spark.deploy.yarn.Client.main(Client.scala)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.spark.deploy.SparkSubmit$.org$apache$spark$deploy$SparkSubmit$$runMain(SparkSubmit.scala:743)
	at org.apache.spark.deploy.SparkSubmit$.doRunMain$1(SparkSubmit.scala:187)
	at org.apache.spark.deploy.SparkSubmit$.submit(SparkSubmit.scala:212)
	at org.apache.spark.deploy.SparkSubmit$.main(SparkSubmit.scala:126)
	at org.apache.spark.deploy.SparkSubmit.main(SparkSubmit.scala)

# yarn查看日志，注意找到applicationId
yarn logs -applicationId application_1657425866513_0001

[root@master spark-2.1.1-bin-hadoop2.7]# yarn logs -applicationId application_1657425866513_0001
22/07/10 14:50:04 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
/tmp/logs/root/logs/application_1657425866513_0001 does not exist.
Log aggregation has not completed or is not enabled.

# yarn一直是Retrying状态，排查是yarn-site.xml未配置master，添加配置后解决
2022-07-10 15:04:04,609 INFO org.apache.hadoop.ipc.Client: Retrying connect to server: 0.0.0.0/0.0.0.0:8031. Already tried 3 time(s); retry policy is RetryUpToMaximumCountWithFixedSleep(maxRetries=10, sleepTime=1000 MILLISECONDS)
2022-07-10 15:04:05,610 INFO org.apache.hadoop.ipc.Client: Retrying connect to server: 0.0.0.0/0.0.0.0:8031. Already tried 4 time(s); retry policy is RetryUpToMaximumCountWithFixedSleep(maxRetries=10, sleepTime=1000 MILLISECONDS)


```

[root@master ~]# curl -sSLO https://dev.mysql.com/get/mysql80-community-release-el7-5.noarch.rpm

[root@master ~]# ls -lh
total 48K
-rw-r--r--  1 root root  26K Apr 25  2019 mysql80-community-release-el7-5.noarch.rpm

[root@master ~]# rpm -Uvh mysql80-community-release-el7-5.noarch.rpm

[root@master ~]# yum install mysql-server

# 执行该步骤后再启动服务，否则无法授权初始密码
[root@master ~]# mysqld --user=mysql --skip-grant-tables --skip-networking&
[1] 6012

[root@master ~]# systemctl start mysqld
[root@master ~]# systemctl stop mysqld

[root@master ~]# systemctl status mysqld
● mysqld.service - MySQL Server
Loaded: loaded (/usr/lib/systemd/system/mysqld.service; enabled; vendor preset: disabled)
Active: active (running) since Mon 2022-07-11 11:35:27 CST; 20s ago
Docs: man:mysqld(8)
http://dev.mysql.com/doc/refman/en/using-systemd.html
Process: 333 ExecStartPre=/usr/bin/mysqld_pre_systemd (code=exited, status=0/SUCCESS)
Main PID: 415 (mysqld)
Status: "Server is operational"
Tasks: 38
CGroup: /system.slice/mysqld.service
└─415 /usr/sbin/mysqld

Jul 11 11:35:08 master systemd[1]: Starting MySQL Server...
Jul 11 11:35:27 master systemd[1]: Started MySQL Server.

# 初始密码为空
[root@master ~]# mysql -u root -p

mysql> use mysql;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed

mysql> UPDATE user SET password='123456' where USER='root';
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '('123456') where USER='root'' at line 1

mysql> flush privileges;
Query OK, 0 rows affected (0.01 sec)

退出 Hadoop 安全模式
rm: Cannot delete /tmp/239514206-1-19.mp4. Name node is in safe mode
hdfs dfsadmin -safemode leave
Safe mode is OFF


https://kafka.apache.org/20/documentation.html#quickstart
```shell
# 启动Kafka
cd /Applications/kafka_2.11-2.0.0/

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ ls
LICENSE		bin		libs
NOTICE		config		site-docs

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ bin/zookeeper-server-start.sh config/zookeeper.properties &

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ jps
18176
30242 Jps
28674 SecondaryNameNode
28486 NameNode
28906 NodeManager
28571 DataNode
28815 ResourceManager
29983 QuorumPeerMainerties &

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ bin/kafka-server-start.sh config/server.properties &

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ jps
18176
28674 SecondaryNameNode
30515 Jps
28486 NameNode
30250 Kafka
28906 NodeManager
28571 DataNode
28815 ResourceManager
29983 QuorumPeerMain

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic t1

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ bin/kafka-topics.sh --list --zookeeper localhost:2181
[2022-08-01 20:48:23,533] INFO Accepted socket connection from /fe80:0:0:0:0:0:0:1%1:51410 (org.apache.zookeeper.server.NIOServerCnxnFactory)
[2022-08-01 20:48:23,543] INFO Client attempting to establish new session at /fe80:0:0:0:0:0:0:1%1:51410 (org.apache.zookeeper.server.ZooKeeperServer)
[2022-08-01 20:48:23,560] INFO Established session 0x100060a702b0002 with negotiated timeout 30000 for client /fe80:0:0:0:0:0:0:1%1:51410 (org.apache.zookeeper.server.ZooKeeperServer)
t1
[2022-08-01 20:48:23,617] INFO Processed session termination for sessionid: 0x100060a702b0002 (org.apache.zookeeper.server.PrepRequestProcessor)
[2022-08-01 20:48:23,618] INFO Closed socket connection for client /fe80:0:0:0:0:0:0:1%1:51410 which had sessionid 0x100060a702b0002 (org.apache.zookeeper.server.NIOServerCnxn)

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ bin/kafka-console-producer.sh --broker-list localhost:9092 --topic t1
{"schema":{"type":"string","optional":false},"payload":"foo"}
{"schema":{"type":"string","optional":false},"payload":"bar"}

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t1
{"schema":{"type":"string","optional":false},"payload":"foo"}
{"schema":{"type":"string","optional":false},"payload":"bar"}

_Nicholas_:kafka_2.11-2.0.0 Sasaki$ bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic t1
Topic:t1        PartitionCount:1        ReplicationFactor:1     Configs:
Topic: t1       Partition: 0    Leader: 0       Replicas: 0     Isr: 0
        
```

