| 格式          | 场景                |
| ----------- | ----------------- |
| CSV         | 表格数据（默认）          |
| JSON        | 半结构化API数据         |
| AVRO        | 大数据序列化（你的Spark经验） |
| ORC/PARQUET | 列式存储，压缩高效         |
| XML         | 老系统导出             |

SKIP_HEADER=1          （跳过标题行）
FIELD_DELIMITER='|'    （分隔符改竖线）
NULL_IF=('NULL','')    （认空值）
COMPRESSION='GZIP'     （解压）
ERROR_ON_COLUMN_COUNT_MISMATCH=FALSE  （容错）

-- 标准CSV（你的场景）
CREATE OR REPLACE FILE FORMAT my_csv
TYPE = 'CSV'
FIELD_DELIMITER = ','
SKIP_HEADER = 1;

-- JSON文件
CREATE OR REPLACE FILE FORMAT my_json
TYPE = 'JSON'
COMPRESSION = 'GZIP';

-- Parquet（大数据压缩好）
CREATE OR REPLACE FILE FORMAT my_parquet
TYPE = 'PARQUET'
SNAPPY_COMPRESSION = TRUE;

-- AVRO（你的Spark经验）
CREATE OR REPLACE FILE FORMAT my_avro
TYPE = 'AVRO';


-- 修改现有格式
ALTER FILE FORMAT my_csv
SET ERROR_ON_COLUMN_COUNT_MISMATCH = FALSE,
    NULL_IF = ('NULL', '');

-- 查看格式详情
DESC FILE FORMAT my_csv;

-- 列出所有格式
SHOW FILE FORMATS;

-- 之前长写法
COPY INTO ORDERS FROM @stage
    file_format=(type=csv field_delimiter=',' skip_header=1);

-- 现在简写
COPY INTO ORDERS FROM @stage
    file_format = my_csv
    ON_ERROR = 'CONTINUE';


-- 1. 创建格式
CREATE FILE FORMAT my_etl_csv
TYPE = 'CSV' FIELD_DELIMITER=',' SKIP_HEADER=1
ERROR_ON_COLUMN_COUNT_MISMATCH=FALSE NULL_IF=('NULL','');

-- 2. 加载数据
COPY INTO ORDERS FROM @stage
    file_format = my_etl_csv
    files = ('OrderDetails.csv')
    ON_ERROR = 'CONTINUE';

-- 3. 检查结果
SELECT * FROM TABLE(INFORMATION_SCHEMA.COPY_HISTORY(
        TABLE_NAME=>'ORDERS', START_TIME=>DATEADD(hours,-1,CURRENT_TIMESTAMP())
                    ));

