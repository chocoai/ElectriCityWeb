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
    
findByUid
===
select 
    name
from
    dg_worktype
where
    id in (select 
            a.worktype_id
        from
            dg_user_worktype a
        where
            a.dg_userid = #{uid})