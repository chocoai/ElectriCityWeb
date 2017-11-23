list
===
select * from dg_order_apply

findList
===
select 
    a.id,
    a.create_id dgUserId,
    u.name userName,
    a.work_order_id,
	a.status,
    d.name statusName,
    a.type,
    g.name typeName,
    DATE_FORMAT(a.create_time,'%Y-%m-%d') create_time,
	a.mark,
	a.audit_desc
from
    dg_order_apply a
        left join
    dg_user u ON a.create_id = u.id
        left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '202' and t.num > - 1) as d ON a.status = d.num
        left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '201' and t.num > - 1) as g ON a.type = g.num

findByWorkOrder
===
select 
    a.id,
    a.create_id dgUserId,
    u.name userName,
    a.work_order_id,
	a.status,
    d.name statusName,
    a.type,
    g.name typeName,
    a.create_time,
	a.mark,
	a.audit_desc
from
    dg_order_apply a
        left join
    dg_user u ON a.create_id = u.id
        left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '202' and t.num > - 1) as d ON a.status = d.num
        left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '201' and t.num > - 1) as g ON a.type = g.num
