一、openstack

​		01创建两台虚拟机   **controller****compute**

​				（1  向导设置>典型 > 稍后安装 > Linux CentOS 7 64位 > 虚拟机名称 安装位置 > 磁盘大小 将磁盘拆分成多个文件 > 自定义配件

​				（2  自定义配件 ：内存：4G   处理器：2+2 虚拟化

​												CD：选中CentOS-7-x86_64-DVD-1804的iso文件

​												网络适配器：一个仅主机模式  新建一个NAT模式（网络配置必须这样***）

controller节点

​	 NAT模式                                                                            主机模式             

```
DEVICE=ens33                                DEVICE=ens34
YPE=Ethernet                               TYPE=Etheret  
ONBOOT=yes                                  ONBOOT=yes
NM_CONTROLLED=no                            NM_CONTROLLED=no  
BOOTPROTO=static                            BOOTPROTO=static  
IPADDR=192.168.200.10                       IPADDR=192.168.100.10
PREFIX=24                                   PREFIX=24  
GATEWAY=192.168.200.2                                
```

compute 节点

​	 NAT模式                                                                            主机模式             

```
DEVICE=ens33                                DEVICE=ens34
TYPE=Ethernet                               TYPE=Ethernet  
ONBOOT=yes                                  ONBOOT=yes
NM_CONTROLLED=no                            NM_CONTROLLED=no  
BOOTPROTO=static                            BOOTPROTO=static  
IPADDR=192.168.200.20                       IPADDR=192.168.100.20   
PREFIX=24                                   PREFIX=24  
GATEWAY=192.168.200.2                                
```

​	02修改主机名

```
hostnamectl set-hostname controller
hostnamectl set-hostname compute
```

​	03主机映射

```
vi /etc/hosts
192.168.200.10 controller
192.168.200.20 compute
```

​	04配置yum源

```
```

