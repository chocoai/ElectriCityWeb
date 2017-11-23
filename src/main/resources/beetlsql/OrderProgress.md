list
===
select * from dg_order_progress

findList
===
SELECT 
    a.id,
    a.create_id,
    u.name userName,
    a.status,
    a.work_order_id workOrderId,
    a.longitude,
    a.latitude,
    a.location,
    DATE_FORMAT(a.create_time,'%Y-%m-%d') create_time,
    a.mark
FROM
    dg_order_progress a
        LEFT JOIN
    dg_user u ON a.create_id = u.id
    
findByWorkOrder
===
SELECT 
    a.id,
    a.create_id,
    u.name userName,
    a.status,
    d.name statusName,
    a.work_order_id workOrderId,
    a.longitude,
    a.latitude,
    a.location,
    a.create_time,
    a.mark
FROM
    dg_order_progress a
        LEFT JOIN
    dg_user u ON a.create_id = u.id
     left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '204' and t.num > - 1) as d ON a.status = d.num