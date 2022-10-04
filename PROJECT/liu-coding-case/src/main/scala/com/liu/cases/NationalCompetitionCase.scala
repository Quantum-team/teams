package com.liu.cases

import com.liu.util.LogFactory
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

import java.util.Properties

/**
 * @author 老刘Matthew
 * @timestamp
 * 本项目源码仅供大数据国赛班教学内阅，禁止传阅！
 */
object NationalCompetitionCase extends LogFactory {

  // TODO Init spark-shell CMD
  //  spark-shell --master local[1] --jars /Users/sasaki/.m2/repository/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar
  //  :paste
    import org.apache.spark.SparkContext
    import org.apache.spark.sql.{DataFrame, SparkSession}
    import java.util.Properties
    import org.apache.spark.sql.SaveMode
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[1]")
      .appName("SparkByExample")
      .config("spark.sql.warehouse.dir", "hdfs://localhost:9000/user/hive/warehouse/")
      .enableHiveSupport()
      .getOrCreate()
    val sc: SparkContext = spark.sparkContext
    import spark.implicits._
    import spark.sql
    spark.sparkContext.setLogLevel("INFO")
    val URL         = "jdbc:mysql://localhost:3306/shtd_store"
    val USER        = "root"
    val PASSWORD    = "12345678"
    val DRIVER      = "com.mysql.jdbc.Driver"
    val CUSTOMER    = "CUSTOMER"
    val NATION      = "NATION"
    val PART        = "PART"
    val PARTSUPP    = "PARTSUPP"
    val REGION      = "REGION"
    val SUPPLIER    = "SUPPLIER"
    val connectionProperties = new Properties()
    connectionProperties.put("user", USER)
    connectionProperties.put("password", PASSWORD)
    connectionProperties.put("driver", DRIVER)


  // TODO Theme of Course
  //  --------------------------------------------------------------------------------------------------------
  //  Hive/HQL 重难点核心操作 & Spark/SparkSQL 前置知识导入
  //  -
  //  DDL Create/Drop/Alter/Use Database
  //  -
  //  DML
  //  # Loading files into tables
  //  # *Inserting data into Hive Tables from queries
  //  # *Dynamic Partition Inserts
  //  # Inserting values into tables from SQL（选讲）
  //  # Managed vs. External Tables
  //  # *Partitioned Tables
  //  --------------------------------------------------------------------------------------------------------


  // TODO
  //  https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL#LanguageManualDDL-CreateTableCreate/Drop/TruncateTable
  //  --------------------------------------------------------------------------------------------------------
  //  CREATE [REMOTE] (DATABASE|SCHEMA) [IF NOT EXISTS] database_name
  //  [COMMENT database_comment]
  //  [LOCATION hdfs_path]
  //  [MANAGEDLOCATION hdfs_path]
  //  [WITH DBPROPERTIES (property_name=property_value, ...)];
  //  /
  //  DROP (DATABASE|SCHEMA) [IF EXISTS] database_name [RESTRICT|CASCADE];
  //  /
  //  ALTER (DATABASE|SCHEMA) database_name SET DBPROPERTIES (property_name=property_value, ...);   -- (Note: SCHEMA added in Hive 0.14.0)
  //  ALTER (DATABASE|SCHEMA) database_name SET OWNER [USER|ROLE] user_or_role;   -- (Note: Hive 0.13.0 and later; SCHEMA added in Hive 0.14.0)
  //  ALTER (DATABASE|SCHEMA) database_name SET LOCATION hdfs_path; -- (Note: Hive 2.2.1, 2.4.0 and later)
  //  ALTER (DATABASE|SCHEMA) database_name SET MANAGEDLOCATION hdfs_path; -- (Note: Hive 4.0.0 and later)
  //  /
  //  USE database_name;
  //  USE DEFAULT;
  //  /
  //  CREATE [TEMPORARY] [EXTERNAL] TABLE [IF NOT EXISTS] [db_name.]table_name    -- (Note: TEMPORARY available in Hive 0.14.0 and later)
  //  [(col_name data_type [column_constraint_specification] [COMMENT col_comment], ... [constraint_specification])]
  //  [COMMENT table_comment]
  //  [PARTITIONED BY (col_name data_type [COMMENT col_comment], ...)]
  //  [LOCATION hdfs_path]
  //  [AS select_statement];   -- (Note: Available in Hive 0.5.0 and later; not supported for external tables)
  //  /
  //  CREATE [TEMPORARY] [EXTERNAL] TABLE [IF NOT EXISTS] [db_name.]table_name
  //  LIKE existing_table_or_view_name
  //  [LOCATION hdfs_path];
  //  /
  //  /
  //  /
  //  # Managed vs. External Tables
  //  Hive fundamentally knows two different types of tables:
  //  - Managed (Internal)
  //    A managed table is stored under the hive.metastore.warehouse.dir path property,
  //    by default in a folder path similar to /user/hive/warehouse/databasename.db/tablename/.
  //    The default location can be overridden by the location property during table creation.
  //    If a managed table or partition is dropped, the data and metadata associated with
  //    that table or partition are deleted. If the PURGE option is not specified, the data
  //    is moved to a trash folder for a defined duration.
  //    Use managed tables when Hive should manage the lifecycle of the table,
  //    or when generating temporary tables.
  //  - External
  //    An external table describes the metadata / schema on external files.
  //    External table files can be accessed and managed by processes outside of Hive.
  //    External tables can access data stored in sources such as Azure Storage Volumes (ASV)
  //    or remote HDFS locations. If the structure or partitioning of an external table is changed,
  //    an MSCK REPAIR TABLE table_name statement can be used to refresh metadata information.
  //    Use external tables when files are already present or in remote locations, and the files
  //    should remain even if the table is dropped.
  //  -
  //  For external tables Hive assumes that it does not manage the data.
  //  Managed or external tables can be identified using the DESCRIBE FORMATTED table_name command,
  //  which will display either MANAGED_TABLE or EXTERNAL_TABLE depending on table type.
  //  Statistics can be managed on internal and external tables and partitions for query optimization.
  //  /
  //  /
  //  /
  //  # Partitioned Tables
  //    Partitioned tables can be created using the PARTITIONED BY clause.
  //    A table can have one or more partition columns and a separate data directory is created
  //    for each distinct value combination in the partition columns.
  //  /
  //  create table table_name (
  //    id                int,
  //    dtDontQuery       string,
  //    name              string
  //  )
  //  partitioned by (date string);
  //  /
  //  CREATE TABLE page_view(
  //    viewTime        INT,
  //    userid          BIGINT,
  //    page_url        STRING,
  //    referrer_url    STRING,
  //    ip STRING COMMENT 'IP Address of the User'
  //    )
  //  COMMENT 'This is the page view table'
  //  PARTITIONED BY(dt STRING, country STRING)
  //  STORED AS SEQUENCEFILE;
  //  --------------------------------------------------------------------------------------------------------

  // TODO DML
  //  https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DML
  //  --------------------------------------------------------------------------------------------------------
  //  # Loading files into tables
  //    Hive does not do any transformation while loading data into tables.
  //    Load operations are currently pure copy/move operations that
  //    move datafiles into locations corresponding to Hive tables.
  //  /
  //  CREATE TABLE tab1 (col1 int, col2 int) PARTITIONED BY (col3 int) STORED AS ORC;
  //  LOAD DATA [LOCAL] INPATH 'filepath' [OVERWRITE] INTO TABLE tablename [PARTITION (partcol1=val1, partcol2=val2 ...)]
  //  /
  //  - filepath can be:
  //    -- a relative path, such as project/data1
  //    -- an absolute path, such as /user/hive/project/data1
  //    -- a full URI with scheme and (optionally) an authority, such as hdfs://namenode:9000/user/hive/project/data1
  //  - The target being loaded to can be a table or a partition. If the table is partitioned, then one must specify
  //    a specific partition of the table by specifying values for all of the partitioning columns.
  //  - filepath can refer to a file (in which case Hive will move the file into the table) or it can be a directory
  //    (in which case Hive will move all the files within that directory into the table). In either case,
  //    filepath addresses a set of files.
  //  /
  //  - If the keyword LOCAL is specified, then:
  //    -- the load command will look for filepath in the local file system. If a relative path is specified,
  //      it will be interpreted relative to the user's current working directory. The user can specify
  //      a full URI for local files as well - for example: file:///user/hive/project/data1
  //    -- the load command will try to copy all the files addressed by filepath to the target filesystem.
  //      The target file system is inferred by looking at the location attribute of the table. The copied
  //      data files will then be moved to the table.
  //    -- Note: If you run this command against a HiveServer2 instance then the local path refers to a path
  //      on the HiveServer2 instance. HiveServer2 must have the proper permissions to access that file.
  //  /
  //  - If the keyword LOCAL is not specified, then Hive will either use the full URI of filepath, if one is specified,
  //    or will apply the following rules:
  //    -- If scheme or authority are not specified, Hive will use the scheme and authority from the hadoop configuration variable
  //       fs.default.name that specifies the Namenode URI.
  //    -- If the path is not absolute, then Hive will interpret it relative to /user/<username>
  //    -- Hive will move the files addressed by filepath into the table (or partition)
  //  /
  //  - If the OVERWRITE keyword is used then the contents of the target table (or partition) will be deleted
  //    and replaced by the files referred to by filepath; otherwise the files referred by filepath
  //    will be added to the table.
  //  --------------------------------------------------------------------------------------------------------
  //  /
  //  --------------------------------------------------------------------------------------------------------
  //  # Inserting data into Hive Tables from queries
  //  Query Results can be inserted into tables by using the insert clause.
  //  Standard syntax:
  //  INSERT INTO TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...)]
  //    select_statement1 FROM from_statement;
  //  /
  //  Hive extension (multiple inserts):
  //  FROM from_statement
  //  INSERT OVERWRITE TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...)
  //    [IF NOT EXISTS]] select_statement1
  //    [INSERT OVERWRITE TABLE tablename2 [PARTITION ... [IF NOT EXISTS]] select_statement2]
  //    [INSERT INTO TABLE tablename2 [PARTITION ...] select_statement2] ...;
  //  FROM from_statement
  //  INSERT INTO TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...)] select_statement1
  //    [INSERT INTO TABLE tablename2 [PARTITION ...] select_statement2]
  //    [INSERT OVERWRITE TABLE tablename2 [PARTITION ... [IF NOT EXISTS]] select_statement2] ...;
  //  /
  //  Hive extension (dynamic partition inserts):
  //  INSERT OVERWRITE TABLE tablename PARTITION (partcol1[=val1], partcol2[=val2] ...)
  //    select_statement FROM from_statement;
  //  INSERT INTO TABLE tablename PARTITION (partcol1[=val1], partcol2[=val2] ...)
  //    select_statement FROM from_statement;
  //  /
  //  Synopsis
  //  - INSERT OVERWRITE will overwrite any existing data in the table or partition.
  //  - INSERT INTO will append to the table or partition, keeping the existing data intact.
  //  - Inserts can be done to a table or a partition. If the table is partitioned,
  //    then one must specify a specific partition of the table by specifying values
  //    for all of the partitioning columns.
  //  - Multiple insert clauses (also known as Multi Table Insert) can be specified in the same query.
  //  - The output of each of the select statements is written to the chosen table (or partition).
  //    Currently the OVERWRITE keyword is mandatory and implies that the contents of the chosen table
  //    or partition are replaced with the output of corresponding select statement.
  //  --------------------------------------------------------------------------------------------------------
  //  /
  //  --------------------------------------------------------------------------------------------------------
  //  # Dynamic Partition Inserts
  //  In the dynamic partition inserts, users can give partial partition specifications,
  //  which means just specifying the list of partition column names in the PARTITION clause.
  //  The column values are optional.
  //  If a partition column value is given, we call this a static partition, otherwise it is a dynamic partition.
  //  Each dynamic partition column has a corresponding input column from the select statement.
  //  This means that the dynamic partition creation is determined by the value of the input column.
  //  The dynamic partition columns must be specified last among the columns in the SELECT statement
  //  and in the same order in which they appear in the PARTITION() clause.
  //  -
  //  hive.exec.dynamic.partition=true
  //  - Needs to be set to true to enable dynamic partition inserts
  //  hive.exec.dynamic.partition.mode=strict
  //  - In strict mode, the user must specify at least one static partition in case
  //    the user accidentally overwrites all partitions, in nonstrict mode all partitions
  //    are allowed to be dynamic
  //  /
  //  FROM page_view_stg pvs
  //    INSERT OVERWRITE TABLE page_view PARTITION(dt='2008-06-08', country)
  //    SELECT pvs.viewTime, pvs.userid, pvs.page_url, pvs.referrer_url, null, null, pvs.ip, pvs.cnt
  //  /
  //  Here the country partition will be dynamically created by the last column
  //  from the SELECT clause (i.e. pvs.cnt). Note that the name is not used.
  //  In nonstrict mode the dt partition could also be dynamically created.
  //  /
  //  Example
  //  https://cwiki.apache.org/confluence/display/Hive/Tutorial#Tutorial-Dynamic-PartitionInsert
  //  --------------------------------------------------------------------------------------------------------
  //  -
  //  --------------------------------------------------------------------------------------------------------
  //  （选讲）
  //  # Inserting values into tables from SQL
  //    The INSERT...VALUES statement can be used to insert data into tables directly from SQL.
  //    Standard Syntax:
  //    INSERT INTO TABLE tablename [PARTITION (partcol1[=val1], partcol2[=val2] ...)] VALUES values_row [, values_row ...]
  //  /
  //  Where values_row is:
  //    ( value [, value ...] )
  //  where a value is either null or any valid SQL literal
  //  /
  //  Synopsis
  //  - Each row listed in the VALUES clause is inserted into table tablename.
  //  - Values must be provided for every column in the table. The standard SQL syntax that allows the user to
  //    insert values into only some columns is not yet supported. To mimic the standard SQL, nulls can be provided
  //    for columns the user does not wish to assign a value to.
  //  - Dynamic partitioning is supported in the same way as for INSERT...SELECT.
  //  - If the table being inserted into supports ACID and a transaction manager that supports ACID is in use,
  //    this operation will be auto-committed upon successful completion.
  //  - Hive does not support literals for complex types (array, map, struct, union), so it is not possible
  //    to use them in INSERT INTO...VALUES clauses. This means that the user cannot insert data into a complex
  //    datatype column using the INSERT INTO...VALUES clause.
  //  /
  //  Examples
  //  CREATE TABLE students (name VARCHAR(64), age INT, gpa DECIMAL(3, 2))
  //  CLUSTERED BY (age) INTO 2 BUCKETS STORED AS ORC;
  //  /
  //  INSERT INTO TABLE students VALUES ('fred flintstone', 35, 1.28), ('barney rubble', 32, 2.32);
  //  /
  //  CREATE TABLE pageviews (userid VARCHAR(64), link STRING, came_from STRING)
  //  PARTITIONED BY (datestamp STRING) CLUSTERED BY (userid) INTO 256 BUCKETS STORED AS ORC;
  //  /
  //  INSERT INTO TABLE pageviews PARTITION (datestamp = '2014-09-23')
  //  VALUES ('jsmith', 'mail.com', 'sports.com'), ('jdoe', 'mail.com', null);
  //  /
  //  INSERT INTO TABLE pageviews PARTITION (datestamp)
  //  VALUES ('tjohnson', 'sports.com', 'finance.com', '2014-09-23'), ('tlee', 'finance.com', null, '2014-09-21');
  //  /
  //  INSERT INTO TABLE pageviews VALUES ('tjohnson', 'sports.com', 'finance.com', '2014-09-23'), ('tlee', 'finance.com', null, '2014-09-21');



  def main(args: Array[String]): Unit = {

    // :paste
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[1]")
      .appName("SparkByExample")
      .config("spark.sql.warehouse.dir", "hdfs://localhost:9000/user/hive/warehouse/")
      .enableHiveSupport()
      .getOrCreate()

    val sc: SparkContext = spark.sparkContext

    import spark.implicits._
    import spark.sql

    spark.sparkContext.setLogLevel("INFO")
    spark.sparkContext.setLogLevel("ERROR")
    //    spark.sqlContext.setConf("hive.exec.dynamic.partition", "false")
    //    spark.sqlContext.setConf("hive.exec.dynamic.partition.mode", "nonstrict")


    // TODO
    //  --------------------------------------------------------------------------------------------------------
    //  /
    //                               use DDL/DML in spark-shell
    //  /
    //  --------------------------------------------------------------------------------------------------------

    sql("show databases").show
    // TODO
    //    +------------+
    //    |databaseName|
    //    +------------+
    //    |     default|
    //    +------------+

    sql("create database if not exists ods")
    sql("create database if not exists dwd")
    sql("create database if not exists temp")
    sql("create database if not exists temp_")
    // TODO
    //   22 +------------+
    //    |databaseName|
    //    +------------+
    //    |     default|
    //    |         dwd|
    //    |         ods|
    //    |        temp|
    //    +------------+
    //  -
    //  - 注意：必须保证Spark conf目录下有 hive-site.xml 原文件，否则SparkSQL Hive无数据不同步
    //  -
    //    hive> show databases;
    //    OK
    //    default
    //    dwd
    //    ods
    //    temp
    //    Time taken: 15.374 seconds, Fetched: 4 row(s)

    sql("drop database if exists temp")
    // TODO
    //    +------------+
    //    |databaseName|
    //    +------------+
    //    |     default|
    //    |         dwd|
    //    |         ods|
    //    +------------+

    // TODO 查看仓库目录
    //    _Nicholas_:~ Sasaki$ hdfs dfs -ls /user/hive/warehouse/
    //    drwxrwxrwx   - Sasaki supergroup          0 2022-07-30 10:56 /user/hive/warehouse/dwd.db
    //    drwxrwxrwx   - Sasaki supergroup          0 2022-07-30 10:55 /user/hive/warehouse/ods.db

    // TODO
    //  --------------------------------------------------------------------------------------------------------
    //  /
    //    模块B：离线数据抽取 （15分）
    //  /
    //    任务一：全量数据抽取
    //     编写Scala工程代码，将MySQL的shtd_store库中表
    //     CUSTOMER、NATION、PART、PARTSUPP、REGION、SUPPLIER
    //     的数据全量抽取到Hive的ods库中对应表customer，nation，part，partsupp，region，supplier中。
    //  /
    //  --------------------------------------------------------------------------------------------------------

    val URL = "jdbc:mysql://localhost:3306/shtd_store"
    val USER = "root"
    val PASSWORD = "12345678"
    val DRIVER = "com.mysql.jdbc.Driver"

    // 全量
    val CUSTOMER    = "CUSTOMER"
    val NATION      = "NATION"
    val PART        = "PART"
    val PARTSUPP    = "PARTSUPP"
    val REGION      = "REGION"
    val SUPPLIER    = "SUPPLIER"

    val connectionProperties = new Properties()
    connectionProperties.put("user", USER)
    connectionProperties.put("password", PASSWORD)
    connectionProperties.put("driver", DRIVER)

    val df_customer: DataFrame = spark.read.jdbc(URL, CUSTOMER, connectionProperties)

    df_customer.printSchema()
    // TODO
    //    root
    //    |-- CUSTKEY: integer (nullable = true)
    //    |-- NAME: string (nullable = true)
    //    |-- ADDRESS: string (nullable = true)
    //    |-- NATIONKEY: string (nullable = true)
    //    |-- PHONE: string (nullable = true)
    //    |-- ACCTBAL: string (nullable = true)
    //    |-- MKTSEGMENT: string (nullable = true)
    //    |-- COMMENT: string (nullable = true)

    df_customer.createTempView("customer")

//    df_customer.count

    sql("select count(0) from customer").show
    // TODO
    //  +--------+
    //  |count(0)|
    //  +--------+
    //  |    6007|
    //  +--------+

    sql("select * from customer limit 10").show

    df_customer.show(10)
    // TODO
    //    +-------+------------------+--------------------+---------+---------------+-------+----------+--------------------+
    //    |CUSTKEY|              NAME|             ADDRESS|NATIONKEY|          PHONE|ACCTBAL|MKTSEGMENT|             COMMENT|
    //    +-------+------------------+--------------------+---------+---------------+-------+----------+--------------------+
    //    |      1|Customer#000000001|   IVhzIApeRb ot,c,E|       15|25-989-741-2988| 711.56|  BUILDING|to the even, regu...|
    //    |      2|Customer#000000002|XSTf4,NCwDVaWNe6t...|       13|23-768-687-3665| 121.65|AUTOMOBILE|l accounts. blith...|
    //    |      3|Customer#000000003|        MG9kdTD2WBHm|        1|11-719-748-3364|7498.12|AUTOMOBILE| deposits eat sly...|
    //    |      4|Customer#000000004|         XxVSJsLAGtn|        4|14-128-190-5944|2866.83| MACHINERY| requests. final,...|
    //    |      5|Customer#000000005|KvpyuHCplrB84WgAi...|        3|13-750-942-6364| 794.47| HOUSEHOLD|n accounts will h...|
    //    |      6|Customer#000000006|sKZz0CsnMD7mp4Xd0...|       20|30-114-968-4951|7638.57|AUTOMOBILE|tions. even depos...|
    //    |      7|Customer#000000007|TcGe5gaZNgVePxU5k...|       18|28-190-982-9759|9561.95|AUTOMOBILE|ainst the ironic,...|
    //    |      8|Customer#000000008|I0B10bB0AymmC, 0P...|       17|27-147-574-9335|6819.74|  BUILDING|among the slyly r...|
    //    |      9|Customer#000000009|xKiAFTjUsCuxfeleN...|        8|18-338-906-3675|8324.07| FURNITURE|r theodolites acc...|
    //    |     10|Customer#000000010|6LrEaV6KR6PLVcgl2...|        5|15-741-346-9870|2753.54| HOUSEHOLD|es regular deposi...|
    //    +-------+------------------+--------------------+---------+---------------+-------+----------+--------------------+
    //    only showing top 10 rows


    // TODO
    //  --------------------------------------------------------------------------------------------------------
    //  /
    //     1.
    //     抽取shtd_store库中CUSTOMER的全量数据进入Hive的ods库中表customer。
    //     字段排序，类型不变，同时添加静态分区，分区字段类型为String，且值为某日期（分区字段格式为yyyyMMdd）。
    //     并在hive cli执行show partitions ods.customer命令，将结果截图复制粘贴至对应报告中；
    //  /
    //  --------------------------------------------------------------------------------------------------------

    sql(
      """
        |create table ods.customer (
        | custkey     int,
        | name        string,
        | address     string,
        | nationkey   string,
        | phone       string,
        | acctbal     string,
        | mktsegment  string,
        | comment     string
        |)
        |partitioned by(date string)
        |""".stripMargin)

    sql("desc ods.customer").show
    // TODO
    //    +--------------------+---------+-------+
    //    |            col_name|data_type|comment|
    //    +--------------------+---------+-------+
    //    |             custkey|      int|   null|
    //    |                name|   string|   null|
    //    |             address|   string|   null|
    //    |           nationkey|   string|   null|
    //    |               phone|   string|   null|
    //    |             acctbal|   string|   null|
    //    |          mktsegment|   string|   null|
    //    |             comment|   string|   null|
    //    |                date|   string|   null|
    //    |# Partition Infor...|         |       |
    //    |          # col_name|data_type|comment|
    //    |                date|   string|   null|
    //    +--------------------+---------+-------+

    sql(
      """
        |insert into table ods.customer
        |partition (date="20220728")
        |select
        | custkey,
        | name,
        | address,
        | nationkey,
        | phone,
        | acctbal,
        | mktsegment,
        | comment
        |from customer
        |""".stripMargin)

    sql("select * from ods.customer limit 5").show
    // TODO
    //    +-------+------------------+--------------------+---------+---------------+-------+----------+--------------------+--------+
    //    |custkey|              name|             address|nationkey|          phone|acctbal|mktsegment|             comment|    date|
    //    +-------+------------------+--------------------+---------+---------------+-------+----------+--------------------+--------+
    //    |      1|Customer#000000001|   IVhzIApeRb ot,c,E|       15|25-989-741-2988| 711.56|  BUILDING|to the even, regu...|20220728|
    //    |      2|Customer#000000002|XSTf4,NCwDVaWNe6t...|       13|23-768-687-3665| 121.65|AUTOMOBILE|l accounts. blith...|20220728|
    //    |      3|Customer#000000003|        MG9kdTD2WBHm|        1|11-719-748-3364|7498.12|AUTOMOBILE| deposits eat sly...|20220728|
    //    |      4|Customer#000000004|         XxVSJsLAGtn|        4|14-128-190-5944|2866.83| MACHINERY| requests. final,...|20220728|
    //    |      5|Customer#000000005|KvpyuHCplrB84WgAi...|        3|13-750-942-6364| 794.47| HOUSEHOLD|n accounts will h...|20220728|
    //    +-------+------------------+--------------------+---------+---------------+-------+----------+--------------------+--------+

    sql("select count(0) from ods.customer").show
    // TODO
    //    +--------+
    //    |count(0)|
    //    +--------+
    //    |    6007|
    //    +--------+

    // TODO 以上查询换Hive测试
    //  - 测试Hive简单查询，无MapReduce
    //    hive> select * from ods.customer limit 5;
    //    OK
    //    1	Customer#000000001	IVhzIApeRb ot,c,E	15	25-989-741-2988	711.56	BUILDING	to the even, regular platelets. regular, ironic epitaphs nag e	20220728
    //    2	Customer#000000002	XSTf4,NCwDVaWNe6tEgvwfmRchLXa13	23-768-687-3665	121.65	AUTOMOBILE	l accounts. blithely ironic theodolites integrate boldly: caref	20220728
    //    3	Customer#000000003	MG9kdTD2WBHm	1	11-719-748-3364	7498.12	AUTOMOBILE	 deposits eat slyly ironic, even instructions. express foxes detect slyly. blithely even accounts abov	20220728
    //    4	Customer#000000004	XxVSJsLAGtn	4	14-128-190-5944	2866.83	MACHINERY	 requests. final, regular ideas sleep final accou	20220728
    //    5	Customer#000000005	KvpyuHCplrB84WgAiGV6sYpZq7Tj	13-750-942-6364	794.47	HOUSEHOLD	n accounts will have to unwind. foxes cajole accor	20220728
    //    Time taken: 6.98 seconds, Fetched: 5 row(s)
    //  -
    //  - 测试Hive"复杂"查询，自动生成并执行MapReduce任务
    //  -
    //    hive> select count(0) from ods.customer;
    //  内存不够，结果出不来，MapReduce消耗大量磁盘、内存交换空间

    sql("show partitions ods.customer").show
    // TODO
    //    +-------------+
    //    |    partition|
    //    +-------------+
    //    |date=20220728|
    //    +-------------+

    // TODO 查看仓库目录数据
    //    _Nicholas_:~ Sasaki$ hdfs dfs -ls /user/hive/warehouse/ods.db/customer
    //    Found 1 items
    //    drwxrwxrwx   - Sasaki supergroup          0 2022-07-30 12:06 /user/hive/warehouse/ods.db/customer/date=20220728
    //  /
    //    _Nicholas_:~ Sasaki$ hdfs dfs -ls /user/hive/warehouse/ods.db/customer/date=20220728
    //    Found 1 items
    //    -rwxrwxrwx   3 Sasaki supergroup     962662 2022-07-30 12:06 /user/hive/warehouse/ods.db/customer/date=20220728/part-00000
    //  /
    //    _Nicholas_:~ Sasaki$ hdfs dfs -tail /user/hive/warehouse/ods.db/customer/date=20220728/part-00000
    //    6002Customer#000006002ZnHuM0Y9nONIKR5TFrHGuJnmxq9GLULVLitL82030-330-985-91614400.25HOUSEHOLDfinal instructions. quickly regular epitaphs are according to the sl
    //    6003Customer#000006003TD5JS9ULaDBUHIy5J7FfT1626-402-596-3552-30.04MACHINERYyly furiously regular instructions. requests sleep about the carefully special accounts.
    //    6004Customer#000006004Oo0mrAFH,KrRuF0eCxbklLZC414-523-907-2485-987.86FURNITUREthe regular theodolites. carefully silent deposits hagg

    // TODO func方式如何实现partition value?
    //    dfTableCustomer
    //      .write
    //      .partitionBy("partition_date")
    //      .saveAsTable("customer")

    val df_nation = spark.read.jdbc(URL, NATION, connectionProperties)
    df_nation.createTempView("nation")
    df_nation.printSchema()
    sql(
      """
        |create table ods.nation(
        | nationkey int,
        | name      string,
        | regionkey string,
        | comment   string
        |)
        |partitioned by(date string)
        |""".stripMargin)
    sql("desc ods.nation").show
    sql(
      """
        |insert into table ods.nation
        |partition (date="20220728")
        |select
        | nationkey,
        | name,
        | regionkey,
        | comment
        |from nation
        |""".stripMargin)
    sql("select count(0) from ods.nation").show

    val df_region = spark.read.jdbc(URL, REGION, connectionProperties)
    df_region.createTempView("region")
    df_region.printSchema()
    sql(
      """
        |create table ods.region(
        | regionkey int,
        | name      string,
        | comment   string
        |)
        |partitioned by(date string)
        |""".stripMargin)
    sql("desc ods.region").show
    sql(
      """
        |insert into table ods.region
        |partition (date="20220728")
        |select
        | regionkey,
        | name,
        | comment
        |from region
        |""".stripMargin)
    sql("select count(0) from ods.region").show


    // TODO
    //  --------------------------------------------------------------------------------------------------------
    //  /
    //    任务二：增量数据抽取
    //     编写Scala工程代码，将MySQL的shtd_store库中表ORDERS、
    //     LINEITEM增量抽取到Hive的ods库中对应表ORDERS，LINEITEM中。
    //  /
    //     1、抽取shtd_store库中ORDERS的增量数据进入Hive的ods库中表orders，要求只取XXXX年XX月XX日及之后的数据，
    //      根据ORDERS表中ORDERKEY作为增量字段，只将新增的数据抽入，字段类型不变，同时添加动态分区，分区字段类型为String，
    //      且值为ORDERDATE字段的内容。并在hive cli执行select count(distinct(dealdate)) from ods.orders命令，
    //      将结果截图复制粘贴至对应报告中；
    //  /
    //  --------------------------------------------------------------------------------------------------------

    // 增量
    val ORDERS      = "ORDERS"
    val LINEITEM    = "LINEITEM"

    val df_orders = spark.read.jdbc(URL, ORDERS, connectionProperties)

    df_orders.createTempView("orders")

    // TODO Case SparkSQL write mysql by jdbc
    // :paste
    df_orders
      .write
      .mode(SaveMode.Append)
      .format("jdbc")
      .option("url", URL)
      .option("dbtable", "shtd_store.ORDERS_")
      .option("user", USER)
      .option("driver", DRIVER)
      .option("password", PASSWORD)
      .save()

    // :paste
    df_orders
      .write
        .mode(SaveMode.Overwrite)
//      .mode(SaveMode.Append)
      .jdbc(URL, "shtd_store.ORDERS_", connectionProperties)

    // Specifying create table column data types on write
    //    jdbcDF.write
    //      .option("createTableColumnTypes", "name CHAR(64), comments VARCHAR(1024)")
    //      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)


    sql("select * from orders limit 10").show
    // TODO
    //    +--------+-------+-----------+----------+----------+-------------+---------------+------------+--------------------+
    //    |ORDERKEY|CUSTKEY|ORDERSTATUS|TOTALPRICE| ORDERDATE|ORDERPRIORITY|          CLERK|SHIPPRIORITY|             COMMENT|
    //    +--------+-------+-----------+----------+----------+-------------+---------------+------------+--------------------+
    //    | 5700035|   4985|          O|  69954.71|1997-12-17|        5-LOW|Clerk#000000173|           0|e platelets. blit...|
    //    | 5700160|   3940|          F| 237098.37|1993-04-29|       2-HIGH|Clerk#000000969|           0|requests use slyl...|
    //    | 5700547|   2845|          O| 155968.54|1997-02-05|     3-MEDIUM|Clerk#000000364|           0| cajole alongside...|
    //    | 5700674|    968|          O| 172107.13|1997-03-01|     3-MEDIUM|Clerk#000000887|           0|c pinto beans wak...|
    //    | 5700742|    929|          O| 224985.28|1996-12-22|     3-MEDIUM|Clerk#000000544|           0|t packages cajole...|
    //    | 5700805|   2344|          O| 241645.54|1997-12-10|       2-HIGH|Clerk#000000940|           0|unts hang quickly...|
    //    | 5700865|   1609|          O|   6452.57|1997-11-14|     3-MEDIUM|Clerk#000000506|           0|regular dolphins ...|
    //    | 5700867|   4559|          O| 243729.82|1998-02-05|        5-LOW|Clerk#000000928|           0|ggedly pending es...|
    //    | 5700870|   1837|          O| 241198.72|1995-10-24|        5-LOW|Clerk#000000761|           0|ithely quickly ex...|
    //    | 5700929|   4039|          F| 283204.97|1993-08-21|       2-HIGH|Clerk#000000830|           0|platelets above t...|
    //    +--------+-------+-----------+----------+----------+-------------+---------------+------------+--------------------+

    sql("select count(0) from orders").show
    // TODO
    //    +--------+
    //    |count(0)|
    //    +--------+
    //    |    2980|
    //    +--------+

    sql("desc orders").show
    // TODO
    //    +-------------+---------+-------+
    //    |     col_name|data_type|comment|
    //    +-------------+---------+-------+
    //    |     ORDERKEY|   string|   null|
    //    |      CUSTKEY|   string|   null|
    //    |  ORDERSTATUS|   string|   null|
    //    |   TOTALPRICE|   string|   null|
    //    |    ORDERDATE|   string|   null|
    //    |ORDERPRIORITY|   string|   null|
    //    |        CLERK|   string|   null|
    //    | SHIPPRIORITY|   string|   null|
    //    |      COMMENT|   string|   null|
    //    +-------------+---------+-------+

    sql(
      """
        |select *
        |from orders
        |where orderdate>="1998-02-01"
        |limit 10
        |""".stripMargin).show

    sql(
      """
        |select count(0)
        |from orders
        |where orderdate>="1998-02-01"
        |limit 10
        |""".stripMargin).show

    sql(
      """
        |create table if not exists ods.orders(
        | orderkey     int,
        | custkey      string,
        | orderstatus  string,
        | totalprice   string,
        | orderpriority   string,
        | clerk        string,
        | shippriority string,
        | comment      string
        |)
        |partitioned by(orderdate    string)
        |""".stripMargin)

    sql("drop table if exists ods.orders")

    sql("desc ods.orders").show
    // TODO
    //    +--------------------+---------+-------+
    //    |            col_name|data_type|comment|
    //    +--------------------+---------+-------+
    //    |            orderkey|      int|   null|
    //    |             custkey|   string|   null|
    //    |         orderstatus|   string|   null|
    //    |          totalprice|   string|   null|
    //    |       orderpriority|   string|   null|
    //    |               clerk|   string|   null|
    //    |        shippriority|   string|   null|
    //    |             comment|   string|   null|
    //    |           orderdate|   string|   null|
    //    |# Partition Infor...|         |       |
    //    |          # col_name|data_type|comment|
    //    |           orderdate|   string|   null|
    //    +--------------------+---------+-------+

    sql("truncate table ods.orders")

    sql(
      """
        |insert overwrite table ods.orders
        |partition (orderdate)
        |select
        | orderkey,
        | custkey,
        | orderstatus,
        | totalprice,
        | orderpriority,
        | clerk,
        | shippriority,
        | comment,
        | orderdate
        |from orders
        |where orderdate>="1992-02-01"
        |""".stripMargin)

    // TODO
    //  - 动态分区异常1
    //  动态分区是在静态分区基础之上的，开启严格校验时，
    //  使用条件orderdate>="1992-02-01"，分区结果大于1000异常
    //  动态分区至少需要已有一个静态分区，保障增量数据可动态分区，否则需主动关闭严格较验
    //  为了比赛连续性，可直接设置非严格校验nonstrict
    //  Dynamic partition strict mode requires at least one static partition column.
    //  To turn this off set hive.exec.dynamic.partition.mode=nonstrict
    //  /
    //  - 动态分区异常2
    //  动态分区的实际分区数大于默认值1000
    //  根据提示加大默认值或根据赛题允许减少查询区间的实际分区数
    //  在spark-shell中设置该参数均不生效，推测需要在启动时设置，暂时减少查询区间数
    //  org.apache.spark.sql.AnalysisException: org.apache.hadoop.hive.ql.metadata.HiveException:
    //  Number of dynamic partitions created is 1662, which is more than 1000.
    //  To solve this try to set hive.exec.max.dynamic.partitions to at least 1662.;
    //  /
    //  以下两种设置参数方式解决以上Case
    sql("set hive.exec.dynamic.partition.mode=nonstrict")
    sql("set hive.exec.max.dynamic.partitions=3000")

    spark.sqlContext.setConf("hive.exec.dynamic.partition.mode", "nonstrict")
    spark.sqlContext.setConf("hive.exec.max.dynamic.partitions", "3000")

    // TODO
    //  发生中断错误情况时，必须保证表数据被清空后再次尝试，必要时在HDFS中删除仓库相应目录
    //  hdfs dfs -rm -r /user/hive/warehouse...
    sql("truncate table ods.orders")
    sql("select count(0) from ods.orders").show
    sql("select * from ods.orders").show

    // 赛题给出查询dealdate有误，是orderdate
    sql("select count(distinct(orderdate)) from ods.orders").show
    // TODO Result & Done!
    //    +-------------------------+
    //    |count(DISTINCT orderdate)|
    //    +-------------------------+
    //    |                      129|
    //    +-------------------------+
    //  /
    //  - Hive中查看动态分区结果
    //  hive> select count(distinct(orderdate)) from ods.orders;
    //  内存不足
    //  /
    //  - HDFS中查看动态分区结果
    //  _Nicholas_:~ Sasaki$ hdfs dfs -ls /user/hive/warehouse/ods.db/orders/
    //  22/07/31 15:56:33 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
    //  Found 133 items
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-01
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-04
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-05
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-06
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-08
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-10
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-11
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-12
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-14
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-15
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-16
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-17
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-18
    //  drwxrwxrwx   - Sasaki supergroup          0 2022-07-31 15:44 /user/hive/warehouse/ods.db/orders/orderdate=1998-02-19
    //  ...

    // TODO
    //  --------------------------------------------------------------------------------------------------------
    //  /
    //     2、抽取shtd_store库中LINEITEM的增量数据进入Hive的ods库中表lineitem，根据LINEITEM表中orderkey作为增量字段，
    //      只将新增的数据抽入，字段类型不变，同时添加静态分区，分区字段类型为String，且值为某日期（分区字段格式为yyyyMMdd）。
    //      并在hive cli执行show partitions ods.lineitem命令，将结果截图复制粘贴至对应报告中。
    //  /
    //  --------------------------------------------------------------------------------------------------------

    val df_lineitem = spark.read.jdbc(URL, LINEITEM, connectionProperties)

    df_lineitem.createTempView("lineitem")

    sql("select * from lineitem limit 10").show
    // TODO
    //    +--------+-------+-------+----------+--------+-------------+--------+----+----------+----------+----------+-----------+-----------+-----------------+--------+--------------------+
    //    |orderkey|partkey|suppkey|linenumber|quantity|extendedprice|discount| tax|returnflag|linestatus|  shipdate|commentdate|receiptdate|       shipstruct|shipmode|             comment|
    //    +--------+-------+-------+----------+--------+-------------+--------+----+----------+----------+----------+-----------+-----------+-----------------+--------+--------------------+
    //    | 5990016|  90909|   3419|         3|      12|     22798.80|    0.04|0.00|         R|         F|1994-05-19| 1994-04-27| 1994-05-20|             NONE|    MAIL|endencies: final,...|
    //    | 5990016| 105054|   5055|         4|      25|     26476.25|    0.07|0.03|         A|         F|1994-04-23| 1994-03-14| 1994-05-18|DELIVER IN PERSON|    RAIL|ets cajole. boldl...|
    //    | 5990017|  77499|   5021|         1|      19|     28053.31|    0.10|0.05|         A|         F|1992-09-28| 1992-09-18| 1992-10-28|             NONE|     FOB|        final warhor|
    //    | 5990017| 113674|   8697|         2|       4|      6750.68|    0.03|0.03|         R|         F|1992-09-19| 1992-09-26| 1992-10-17| TAKE BACK RETURN|     AIR|y final requests ...|
    //    | 5990017| 111929|   4441|         3|      42|     81518.64|    0.07|0.04|         R|         F|1992-08-22| 1992-08-18| 1992-09-11|DELIVER IN PERSON|    RAIL|s. slyly express ...|
    //    | 5990017| 179865|   2383|         4|      35|     68070.10|    0.08|0.03|         A|         F|1992-10-12| 1992-09-13| 1992-11-11| TAKE BACK RETURN|     FOB|r deposits dazzle...|
    //    | 5990018| 183271|    826|         1|      22|     29793.94|    0.06|0.00|         A|         F|1992-08-17| 1992-08-13| 1992-09-10| TAKE BACK RETURN|     AIR|y ironic excuses....|
    //    | 5990018|  21624|   4127|         2|      32|     49459.84|    0.01|0.03|         A|         F|1992-08-04| 1992-07-11| 1992-08-29| TAKE BACK RETURN|    RAIL|gular packages. f...|
    //    | 5990019|  17534|   7535|         1|       3|      4354.59|    0.09|0.01|         N|         O|1998-08-25| 1998-09-04| 1998-09-07| TAKE BACK RETURN|   TRUCK|s. regular, unusu...|
    //    | 5990019|  80334|   5351|         2|      45|     59144.85|    0.02|0.06|         N|         O|1998-08-31| 1998-08-03| 1998-09-21|      COLLECT COD|     FOB|ironic dependenci...|
    //    +--------+-------+-------+----------+--------+-------------+--------+----+----------+----------+----------+-----------+-----------+-----------------+--------+--------------------+


    // TODO
    //  --------------------------------------------------------------------------------------------------------
    //  /
    //    任务二：指标计算
    //     1、
    //     请根据dwd层表计算出XXXX年每个地区的平均消费额，
    //     存入MySQL数据库shtd_store的nationavgcmp表（表结构如下）中，
    //     然后在Linux的MySQL命令行中根据人均消费额逆序排序，查询出前5条，
    //     将SQL语句与执行结果截图粘贴至对应报告中;
    //  /
    //  --------------------------------------------------------------------------------------------------------

    // TODO -- Case 1 Method 1
    sql(
      """
        |select
        | r.regionkey regionkey,
        | r.`name` reginname,
        | o_.reginavgconsumption reginavgconsumption
        |from (
        | select
        |   n.regionkey,
        |   avg(o.avg_acctbal_nation) reginavgconsumption
        | from (
        |   select
        |     c.nationkey nationkey,
        |     avg(c.acctbal) avg_acctbal_nation
        |   from ods.customer c
        |   group by c.nationkey
        | ) o, ods.nation n
        | where o.nationkey = n.nationkey
        | group by regionkey
        |) o_, ods.region r
        |where o_.regionkey = r.regionkey
        |order by o_.reginavgconsumption desc
        |""".stripMargin).show

    // TODO
    //    +---------+-----------+-------------------+
    //    |regionkey|  reginname|reginavgconsumption|
    //    +---------+-----------+-------------------+
    //    |        4|MIDDLE EAST|  4525.204523689163|
    //    |        2|       ASIA| 4514.9301437164095|
    //    |        3|     EUROPE|  4470.180080832821|
    //    |        0|     AFRICA|  4403.903998713098|
    //    |        1|    AMERICA| 4372.6865434981455|
    //    +---------+-----------+-------------------+

    // TODO -- Case 1 Method 2
    sql("""
          |select
          | o.regionkey regionkey,
          |	r.`name` reginname,
          |	o.reginavgconsumption reginavgconsumption
          |from (
          |	select
          |		r.regionkey regionkey,
          |		avg(c.acctbal) reginavgconsumption
          |	from ods.customer c, ods.nation n, ods.region r
          |	where c.nationkey = n.nationkey
          |	and n.regionkey = r.regionkey
          |	group by r.regionkey
          |) o, region r
          |where o.regionkey = r.regionkey
          |order by reginavgconsumption desc
          |""".stripMargin).show

    // TODO
    //    +---------+-----------+-------------------+
    //    |regionkey|  reginname|reginavgconsumption|
    //    +---------+-----------+-------------------+
    //    |        4|MIDDLE EAST|  4516.957591801879|
    //    |        2|       ASIA|  4515.454721529518|
    //    |        3|     EUROPE|  4465.021347150254|
    //    |        0|     AFRICA|  4403.026123417729|
    //    |        1|    AMERICA|  4373.347844756401|
    //    +---------+-----------+-------------------+

    /**
     * 2、
     * 请根据dwd层表计算出余货大于50的所有供应商以及余货总和，
     * MySQL数据库shtd_store的supplyacc表（表结构如下）中，
     * 然后在Linux的MySQL命令行中根据人均消费额逆序排序，查询出前5条，
     * 将SQL语句与执行结果截图粘贴至对应报告中。
     */

    sql(
      """create table ods.tb_borrow (
        |	id int,
        |	book_id int,
        |	username string,
        |	score double,
        |	borrow_date date,
        |	level string
        |)
        |""".stripMargin)

    sql(
      """
        |insert into ods.tb_borrow
        |select
        |	o_.id,
        |	o_.book_id,
        |	o_.username,
        |	o_.score,
        |	o_.borrow_date,
        |	o_.`level`
        |from (
        |	select
        |		book_id,
        |		book_name,
        |		book_typeid,
        |		book_author,
        |		book_publisher,
        |		book_price,
        |		book_publisher_date
        |	from tb_book
        |	where book_price<0
        |) o,
        |	(
        |	select
        |		id,
        |		book_id,
        |		username,
        |		if(score<0,score*-1,score) score,
        |		borrow_date,
        |		if(
        |			score>=4.5, "好评",
        |			if(
        |				score>=3.0 and score<4.5, "中评", "差评"
        |			)
        |		)
        |		`level`
        |	from
        |		tb_borrow
        |) o_
        |where o.book_id=o_.book_id
        |""".stripMargin).show


    spark close
  }
}

