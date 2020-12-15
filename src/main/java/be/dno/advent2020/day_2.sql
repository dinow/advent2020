SELECT COUNT(*) 
  FROM (SELECT  d02_password
               ,d02_letter
               ,d02_min
               ,d02_max
               ,REGEXP_COUNT(d02_password, d02_letter) AS actual_count 
          FROM  day_02)
 WHERE actual_count >= d02_min 
   AND actual_count <= d02_max;


select count(*)
from ( select d02_id
      ,case when substr(d02_password, d02_min, 1) = d02_letter then 1 else 0 end as min_ok
      ,case when substr(d02_password, d02_max, 1) = d02_letter then 1 else 0 end as max_ok
       from day_02
    )
    where max_ok + min_ok = 1;