delimiter $$

drop procedure if exists p3 $$
create procedure p3()
begin

select name,sub1marks,sub2marks,sub3marks,(sub1marks+sub2marks+sub3marks) as total from student order by total desc limit 3;

end $$
delimiter ;