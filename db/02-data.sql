delete from queue;

do
$do$
declare
	i int;
begin
	for i in 1..100
	loop
		insert into queue (content) values (md5(random()::text));
	end loop;
end;
$do$;


/*
delete from queue
where id in (
	select id
	from queue
	order by id
	for update skip locked
	limit 10
)
returning *;
*/