list
===
select * from dg_work_order

findList
===
select 
    a.id,
    a.order_id,
    a.order_date orderDays,
	a.money,
	a.total_money,
    a.subsidy,
	a.subsidy_mark,
	b.id orderId,
	b.title,
	b.total_money allMoney,
	b.content orderContent,
	b.address,
	DATE_FORMAT(b.createtm,'%Y-%m-%d') orderDate,
	c.name acceptUser,
	a.accept_user_id,
	a.reject_times,
    u.name dgUser,
    u.id dgUserId,
    w.name workTypeName,
    w.id worktypeId,
    d.name statusName,
    d.num status,
    DATE_FORMAT(a.create_time,'%Y-%m-%d') create_time,
    DATE_FORMAT(a.accpet_time,'%Y-%m-%d') accpet_time,
    DATE_FORMAT(a.finish_time,'%Y-%m-%d') finish_time,
    a.mark
from
    dg_work_order a
        left join
    dg_order b ON a.order_id = b.id
        left join
    dg_user u ON a.create_id = u.id
        left join
	dg_user c on a.accept_user_id = c.id 
	left join
    dg_worktype w ON a.worktype_id = w.id
        left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '203' and t.num > - 1) as d ON a.status = d.num
        
findByBackground
===
select 
    a.id,
    a.order_id,
    a.order_date orderDays,
	a.money,
	a.total_money,
    a.subsidy,
	a.subsidy_mark,
	b.id orderId,
	b.title,
	b.total_money allMoney,
	b.content orderContent,
	b.address,
	b.createtm orderDate,
	c.name acceptUser,
	a.accept_user_id,
	a.reject_times,
    u.name dgUser,
    u.id dgUserId,
    w.name workTypeName,
    w.id worktypeId,
    d.name statusName,
    d.num status,
    a.create_time,
    a.accpet_time,
    a.finish_time,
    a.mark
from
    dg_work_order a
        left join
    dg_order b ON a.order_id = b.id
        left join
    dg_user u ON a.create_id = u.id
        left join
	dg_user c on a.accept_user_id = c.id 
	left join
    dg_worktype w ON a.worktype_id = w.id
        left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '203' and t.num > - 1) as d ON a.status = d.num