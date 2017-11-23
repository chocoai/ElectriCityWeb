list
===
select * from dg_worktype_check

findByPage
===
select 
    t.id,
    t.dg_user_id,
    t.user_worktype_id userWorktypeId,
    u.name dgUserName,
    u.code idCardCode,
    t.work_id,
    b.name workTypeName,
    t.status,
    t.mark,
    t.update_tm updateTm,
    t.create_tm createTm,
    d.name statusName,
    CONCAT(#{headUrl}, t.idcard_up) idCardUp,
    CONCAT(#{headUrl}, t.idcard_down) idCardDown,
    CONCAT(#{headUrl}, t.qualif_cert) qualifCert
from
    dg_worktype_check t
        left join
    dg_user u ON t.dg_user_id = u.id
        left join
    dg_worktype b ON t.work_id = b.id
        left join
    (select 
        k.name, k.num
    from
        tfw_dict k
    where
        k.code = '209' and k.num > - 1) d ON t.status = d.num

findForBackground
===
select 
    a.id,
    a.status,
    a.create_tm,
    u.name userName,
    b.name workTypeName,
	admin.NAME adminName,
	a.mark
from
    dg_worktype_check a
        left join
    dg_user u ON a.dg_user_id = u.id
        left join
    dg_worktype b ON a.work_id = b.id
        left join
    tfw_user admin ON a.admin_id = admin.ID

getById
===
select 
    a.id,
    a.status,
    a.create_tm,
    u.name userName,
    b.name workTypeName,
	admin.NAME adminName,
	d.name statusName,
	a.update_tm updateTm,
    a.create_tm createTm,
	a.idcard_up,
	a.idcard_down,
	a.qualif_cert,
	a.mark
from
    dg_worktype_check a
        left join
    dg_user u ON a.dg_user_id = u.id
        left join
    dg_worktype b ON a.work_id = b.id
        left join
    tfw_user admin ON a.admin_id = admin.ID
    left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '209' and t.num > - 1) as d ON a.status = d.num
where a.id = #{id}