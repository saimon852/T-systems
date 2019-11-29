select * from obec;
1-----------------------------------
select nazov,count(*)
from obec o
group by nazov
having count(*)>1;

2------------------------------------
select count(*)
from kraj k
full outer join okres o on k.id=o.id_kraj
where id_kraj = 8;
3-------------------------------------
select count(*)
from kraj k
inner join okres ok on k.id =ok.id_kraj
inner join obec o on ok.id = o.id_okres
where id_kraj = 8;
4------------------------------------------
select nazov,rok, (muzi + zeny) as total
from obec o
inner join populacia p on o.id= p.id_obec
where rok = 2012
order by total desc
limit 1;
5-------------------------------------------
select sum(muzi+zeny) as total
from populacia p
inner join obec o on p.id_obec = o.id and p.rok= 2012
inner join okres ok on o.id_okres= ok.id and ok.nazov='Sabinov';
6------------------------------------------
select rok, sum(muzi+zeny) as total
from populacia
group by rok
order by rok desc;
7-----------------------------------------
select o.nazov,sum(muzi+zeny) as total
from populacia p
inner join obec o on p.id_obec = o.id and p.rok= 2011
inner join okres ok on o.id_okres= ok.id and ok.nazov='Tvrdosin'
group by o.nazov
order by total asc
limit 2;
8--------------------------------------------
select o.nazov,sum(muzi+zeny) as total
from populacia p
inner join obec o on p.id_obec = o.id and p.rok= 2010 and (muzi+zeny)< 5000
group by nazov
order by total desc;
9---------------------------------------------
select o.nazov,sum(muzi+zeny) as total, sum(case when muzi = muzi then 1 else 0 end)/count(*) - sum(case when zeny = zeny then 1 else 0 end)/count(*) as ratio
from populacia p
inner join obec o on p.id_obec = o.id and p.rok= 2012 and (muzi+zeny) > 20000
group by nazov,round(zeny-muzi,4)
limit 10;

10---------------------------------------------------
select distinct k.nazov, count(id_okres) as okres, count(id_obec) as obec, sum(muzi+zeny) as total
from populacia p
inner join obec o on p.id_obec = o.id and rok = 2012
inner join okres ok on o.id_okres= ok.id
inner join kraj k on ok.id_kraj = k.id
group by k.nazov;

11 ---------------------------------------------------
SELECT  o.nazov, sum(muzi + zeny) AS total
FROM populacia p
INNER JOIN obec o on p.id_obec = o.id
GROUP BY rok,  o.nazov
ORDER BY  o.nazov, total desc;



12--------------------------------------------------
select nazov
from populacia p
inner join obec o on p.id_obec = o.id and rok = 2012
where id_okres in (select id_okres
                     from obec
                    (select avg(




