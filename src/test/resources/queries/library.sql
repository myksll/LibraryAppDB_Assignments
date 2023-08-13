SELECT count(Id) FROM users;   #actual data

SELECT count(distinct Id) FROM users; #expected

select * from users;

select
    count(*)from book_borrow where is_returned =0;

select name from book_categories;

select * from books
where name="End of the Course";


select BC.name, count(*) from book_borrow BB
join books B On BB.book_id = B.id
join book_categories BC ON B.book_category_id = BC.id
group by BC.name
order by 2 desc;

select name from book_categories
where id=(select book_category_id from books
where id=(select book_id from book_borrow group by book_id order by count(*) desc limit 1));

select id, name , author from books
where name= 'Clean Code' and author='Robert C.Martin'
order by id desc;
          select name from books b
                join book_borrow bb on b.id = bb.book_id
                join users u on bb.user_id = u.id
                where  full_name = 'Test Student 5';

