# 1、抽取shtd_store库中ORDERS的增量数据进入Hive的ods库中表orders，
# 要求只取XXXX年XX月XX日及之后的数据，根据ORDERS表中ORDERKEY作为增量字段，
# 只将新增的数据抽入，字段类型不变，同时添加动态分区，分区字段类型为String，
# 且值为ORDERDATE字段的内容。并在hive cli执行select count(distinct(dealdate)) from  ods.orders命令，
# 将结果截图复制粘贴至对应报告中；


# 1、
# 请根据dwd层表计算出XXXX年每个地区的平均消费额,
# 存入MySQL数据库shtd_store的nationavgcmp表（表结构如下）中，
# 然后在Linux的MySQL命令行中根据人均消费额逆序排序，查询出前5条，
# 将SQL语句与执行结果截图粘贴至对应报告中;
# reginkey	            int	    地区表主键
# reginname	            text	地区名称
# reginavgconsumption	double	人均消费额	该地区已购买产品的人均消费额

-- Case 1 Method 1
-- 先按nation求平均，再连nation表得region，再按region求平均

select
    r.regionkey regionkey,
    r.`name` reginname,
    o_.reginavgconsumption reginavgconsumption
from (
         select
             n.regionkey,
             avg(o.avg_acctbal_nation) reginavgconsumption
         from (
                  select
                      c.nationkey nationkey,
                      avg(c.acctbal) avg_acctbal_nation
                  from customer c
                  group by c.nationkey
              ) o, nation n
         where o.nationkey = n.nationkey
         group by regionkey
     ) o_, region r
where o_.regionkey = r.regionkey
order by o_.reginavgconsumption desc
;

-- 4	MIDDLE EAST	4525.204523689163
-- 2	ASIA	4514.93014371641
-- 3	EUROPE	4470.180080832821
-- 0	AFRICA	4403.903998713098
-- 1	AMERICA	4372.6865434981455


-- Case 1 Method 2

select
    o.regionkey regionkey,
    r.`name` reginname,
    o.reginavgconsumption reginavgconsumption
from (
         select
             r.regionkey regionkey,
             avg(c.acctbal) reginavgconsumption
         from customer c, nation n, region r
         where c.nationkey = n.nationkey
           and n.regionkey = r.regionkey
         group by r.regionkey
     ) o, region r
where o.regionkey = r.regionkey
order by reginavgconsumption desc
;

-- 4	MIDDLE EAST	4516.957591801874
-- 2	ASIA	4515.454721529515
-- 3	EUROPE	4465.021347150257
-- 0	AFRICA	4403.026123417723
-- 1	AMERICA	4373.3478447564

# 2、
# 请根据dwd层表计算出余货大于50的所有供应商以及余货总和，
# MySQL数据库shtd_store的supplyacc表（表结构如下）中，
# 然后在Linux的MySQL命令行中根据人均消费额逆序排序，查询出前5条，
# 将SQL语句与执行结果截图粘贴至对应报告中。
# suppkey	    int	    供应商序号
# name	        text	供应商名称
# acctal	    double	余货	        该供应商余货
# allacctal	    double	总余货	    所有供应商余货总和

-- 因需求表描述不准确，假定以partsupp.supplysost为关联计算allacctal的基准数据
select s.suppkey, s.name, s.acctnal, o.allacctal allacctal
from (
         select s.suppkey, sum(supplysost) allacctal
         from supplier s, partsupp p
         where s.suppkey = p.suppkey
         group by s.suppkey
     ) o, supplier s
where o.suppkey = s.suppkey
;

