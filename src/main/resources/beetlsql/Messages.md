list
===
select * from dg_message

findBackground
===
select 
    a.id,
    a.dg_id,
    u.name dgUser,
    c.name statusName,
    d.name typeName,
    m.name msgTemp,
    m.content tmpContent,
    a.create_time,
    a.read_time,
    a.content
from
    dg_message a
        left join
    dg_user u ON a.dg_id = u.id
        left join
    dg_msg_template m ON a.tempid = m.id
        left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '206' and t.num > - 1) as c ON a.status = c.num
        left join
    (select 
        t.name, t.num
    from
        tfw_dict t
    where
        t.code = '207' and t.num > - 1) as d ON a.type = d.num

findPage
===
SELECT 
    a.id,
    a.status,
    s.NAME statusName,
    a.type,
    d.NAME typeName,
    DATE_FORMAT(a.create_time, '%Y-%m-%d %H:%i') createTime,
    a.dg_id dgId,
    m.content
FROM
    dg_message a
        LEFT JOIN
    dg_msg_template m ON a.tempid = m.id
        LEFT JOIN
    (SELECT 
        t.name, t.num
    FROM
        tfw_dict t
    WHERE
        t.code = '207' AND t.num > - 1) AS d ON a.type = d.num
        LEFT JOIN
    (SELECT 
        t.name, t.num
    FROM
        tfw_dict t
    WHERE
        t.code = '206' AND t.num > - 1) AS s ON a.status = s.num


