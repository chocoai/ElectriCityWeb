list
===
select 
    a.id,
    a.title,
    a.content,
    a.address,
    DATE_FORMAT(a.createtm,'%Y-%m-%d') createtm,
    a.total_money,
    u.name dgUser,
    a.create_id
from
    dg_order a
        left join
    dg_user u ON a.create_id = u.id

findByBackgroud
===
select 
    a.id,
    a.title,
    a.content,
    a.address,
    a.createtm,
    a.total_money,
    u.name dgUser,
    a.create_id
from
    dg_order a
        left join
    dg_user u ON a.create_id = u.id

getById    
===
select 
    a.id,
    a.title,
    a.content,
    a.createtm,
    a.total_money,
    u.name dgUser,
    a.create_id
from
    dg_order a
        left join
    dg_user u ON a.create_id = u.id