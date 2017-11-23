list
===
select * from dg_worktype

findList
===
select 
    a.id, a.name, a.check, a.salary, 
    a.over_money, a.order_flag
from
    dg_worktype a 