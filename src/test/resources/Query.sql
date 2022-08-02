select count(*) from books;



select id,name,author,isbn,description,year from books
where name ='java'
order by id desc;

select * from books where name ='java' order by id desc;