--窗口函数
--排名函数
partition by  分组   如去除则按全表排序
order by      排序字段

select * ,
       row_number() over (partition by mgr_id order by salary desc) as r1, --顺序排名  1 2 3
       rank() over (partition by mgr_id order by salary desc) as r1,       --并列排名  1 1 3
       dense_rank() over (partition by mgr_id order by salary desc) as r1  --并列顺序排名 1 1 2
from employee;
