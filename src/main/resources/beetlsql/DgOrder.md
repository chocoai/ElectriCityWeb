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
    a.create_id,
    a.overflag,
    d.name flagName
from
    dg_order a
        left join
    dg_user u ON a.create_id = u.id
    	left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '213' and t.num > - 1) as d ON a.overflag = d.num

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
    a.create_id,
    a.overflag,
    d.name flagName
from
    dg_order a
        left join
    dg_user u ON a.create_id = u.id
    left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '213' and t.num > - 1) as d ON a.overflag = d.num