list
===
select * from dg_user_worktype

findByPage
===
SELECT 
    a.id,
    a.dg_userid,
    u.name dgUser,
    a.worktype_id,
    b.name workTypeName,
    b.order_flag, 
    b.salary, 
    b.over_money,
    a.status,
    d.name statusName,
    a.create_time
FROM
    dg_user_worktype a
        left join
    dg_worktype b ON a.worktype_id = b.id
        left join
    dg_user u ON a.dg_userid = u.id
        left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '212' and t.num > - 1) d ON a.status = d.num
