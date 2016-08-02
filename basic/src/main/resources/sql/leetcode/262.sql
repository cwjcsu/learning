set names 'utf8';
INSERT INTO `LeetCode`.`Trips`
(`Id`,
`Client_Id`,
`Driver_Id`,
`City_Id`,
`Status`,
`Request_at`)
VALUES
(1,1,10,1,'completed',STR_TO_DATE('2013-10-01','%Y-%m-%d')),
(2,2,11,1,'cancelled_by_driver',STR_TO_DATE('2013-10-01','%Y-%m-%d')),
(3,3,12,6,'completed',STR_TO_DATE('2013-10-01','%Y-%m-%d')),
(4,4,13,6,'cancelled_by_client',STR_TO_DATE('2013-10-01','%Y-%m-%d')),
(5,1,10,1,'completed',STR_TO_DATE('2013-10-02','%Y-%m-%d')),
(6,2,11,6,'completed',STR_TO_DATE('2013-10-02','%Y-%m-%d')),
(7,3,12,6,'completed',STR_TO_DATE('2013-10-02','%Y-%m-%d')),
(8,2,12,12,'completed',STR_TO_DATE('2013-10-03','%Y-%m-%d')),
(9,3,10,12,'completed',STR_TO_DATE('2013-10-03','%Y-%m-%d')),
(10,4,13,12,'cancelled_by_driver',STR_TO_DATE('2013-10-03','%Y-%m-%d'));


INSERT INTO `LeetCode`.`Users`
(`Users_Id`,
`Banned`,
`Role`)
VALUES
(1,'No','client'),
(2,'Yes','client'),
(3,'No','client'),
(4,'No','client'),
(10,'No','driver'),
(11,'No','driver'),
(12,'No','driver'),
(13,'No','driver');


--- 

select count(Id) as Requests from Trips t1 
where t1.Request_at>=STR_TO_DATE('2013-10-01','%Y-%m-%d') and t1.Request_at<=STR_TO_DATE('2013-10-03','%Y-%m-%d')
group by date(Request_at) ;

select * from Trips t1 where t1.Client_Id in (select Users_Id from Users where Banned='No') or t1.Driver_Id in (select Users_Id from Users where Banned='No');


-- left join Users t2 on (t1.Client_Id=t2.Users_Id and t2.Banned='No') or (t1.Driver_Id=t2.Users_id and t2.Banned='No');

-- where t1.Request_at>=STR_TO_DATE('2013-10-01','%Y-%m-%d') and t1.Request_at<=STR_TO_DATE('2013-10-03','%Y-%m-%d');


select date(Request_at) as Day,count(Id) as Requests from Trips t1
inner join Users t2 on t1.Client_Id=t2.Users_Id or t1.Driver_Id=t2.Users_id and t2.Banned='No' 
where t1.Request_at>=STR_TO_DATE('2013-10-01','%Y-%m-%d') and t1.Request_at<=STR_TO_DATE('2013-10-03','%Y-%m-%d')
group by date(Request_at) 


