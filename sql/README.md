# Introduction

The objective of this project was to gain extensive knowledge about RDBMS (Relational Database management systems). With the help of DDL and DML sql commands data is manipulated and updated information is displayed. To execute this project, Dbeaver and PostgreSQL were utilized as the database management system and Integrated Development Environment for retriving the results. The queries employed in the project encompassed tasks such as CURD operations namely data creation, updation, deletion, and modification, as well as writing join queries  on tables and manipuling rows and columns to fetch specific outputs.

# SQL Queries

###### Table Setup (DDL) : Here we setup the tables providing all the contraints and fields required to setup our database:

We have created a database schema using clubdata.sql file wherein we create database 'exercises' under schema cd. The below queries from the script help achive the followinf :
```sql
CREATE DATABASE exercises;
\c exercises
CREATE SCHEMA cd;
```
We set proper details for our database :

```sql
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET search_path = cd, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;
```
We write the queries to create the tables according to the following diagram :

```sql
CREATE TABLE members (
    memid integer NOT NULL,
    surname character varying(200) NOT NULL,
    firstname character varying(200) NOT NULL,
    address character varying(300) NOT NULL,
    zipcode integer NOT NULL,
    telephone character varying(20) NOT NULL,
    recommendedby integer,
    joindate timestamp without time zone NOT NULL
);


CREATE TABLE bookings (
    bookid integer NOT NULL,
    facid integer NOT NULL,
    memid integer NOT NULL,
    starttime timestamp without time zone NOT NULL,
    slots integer NOT NULL
);

CREATE TABLE facilities (
    facid integer NOT NULL,
    name character varying(100) NOT NULL,
    membercost numeric NOT NULL,
    guestcost numeric NOT NULL,
    initialoutlay numeric NOT NULL,
    monthlymaintenance numeric NOT NULL
);
```

The sql script adds contraints to the field like setting primary key, foreign key and more.

```sql

ALTER TABLE ONLY bookings
    ADD CONSTRAINT bookings_pk PRIMARY KEY (bookid);

ALTER TABLE ONLY facilities
    ADD CONSTRAINT facilities_pk PRIMARY KEY (facid);
    
ALTER TABLE ONLY members
    ADD CONSTRAINT members_pk PRIMARY KEY (memid);

ALTER TABLE ONLY bookings
    ADD CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES facilities(facid);

ALTER TABLE ONLY bookings
    ADD CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES members(memid);

ALTER TABLE ONLY members
    ADD CONSTRAINT fk_members_recommendedby FOREIGN KEY (recommendedby) REFERENCES members(memid) ON DELETE SET NULL;

```

After this step a dummy data is inserted using the sql insert query for all the tables.

#### Question 1:The club is adding a new facility - a spa. We need to add it into the facilities table. Use the following values: facid: 9, Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.

```sql
insert into facilities 
(facid,name,membercost,guestcost,initialoutlay,monthlymaintenance)
values(9,'spa',20,30,1000000,800);
```

#### Question 2 : Let's try adding the spa to the facilities table again. This time, though, we want to automatically generate the value for the next facid, rather than specifying it as a constant. Use the following values for everything else: Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.

```sql
insert into facilities 
(facid,name,membercost,guestcost,initialoutlay,monthlymaintenance)
values((select max(facid) from facilities)+1 ,'spa',20,30,1000000,800);
```

#### Question 3 : We made a mistake when entering the data for the second tennis court. The initial outlay was 10000 rather than 8000: you need to alter the data to fix the error.

```sql
Update facilities set "initialoutlay" = 100000 where facid = 1;
```

#### Question 4 : We want to alter the price of the second tennis court so that it costs 10% more than the first one. Try to do this without using constant values for the prices, so that we can reuse the statement if we want to.

```sql
UPDATE facilities set
membercost = membercost +(
(
	select membercost from facilities
	where facid = 0
)* 0.1),
guestcost = guestcost +(
(
  select guestcost from facilities 
	where facid = 0
)* 0.1)
where facid = 1;
```

#### Question 5 : As part of a clearout of our database, we want to delete all bookings from the cd.bookings table. How can we accomplish this?

```sql
delete from bookings
```

#### Question 6 : We want to remove member 37, who has never made a booking, from our database. How can we achieve that? 

```sql
delete from
    members
	where memid = 37;
```

#### Question  7 :How can you produce a list of facilities that charge a fee to members, and that fee is less than 1/50th of the monthly maintenance cost? Return the facid, facility name, member cost, and monthly maintenance of the facilities in question.

```sql
select facid, name, membercost, monthlymaintenance from facilities 
	where 
		membercost > 0 
		and membercost < (monthlymaintenance * 0.02)
```

#### Question 8 : How can you produce a list of all facilities with the word 'Tennis' in their name?"

```sql
select * from facilities where name like '%Tennis%'
```

#### Question 9: How can you retrieve the details of facilities with ID 1 and 5? Try to do it without using the OR operator. 

```sql
select * from cd.facilities where facid in (1,5)
```

#### Question 10 : How can you produce a list of members who joined after the start of September 2012? Return the memid, surname, firstname, and joindate of the members in question."

```sql
select memid, surname, firstname, joindate from members where joindate >= ('2012-09-01')
```


#### Question 11 : You, for some reason, want a combined list of all surnames and all facility names. Yes, this is a contrived example :-). Produce that list!

```sql
select surname from members union select name from facilities
```

#### Question 12 : How can you produce a list of the start times for bookings by members named 'David Farrell'?*/

```sql
select starttime from bookings 
	INNER JOIN members ON bookings.memid = members.memid
	where 
		members.firstname = 'David'
  		and members.surname = 'Farrell';
```

#### Question 13 : How can you produce a list of the start times for bookings for tennis courts, for the date '2012-09-21'? Return a list of start time and facility name pairings, ordered by the time.

```sql
select
    bookings.starttime as start,
    facilities.name
from
    bookings
        INNER JOIN facilities ON bookings.facid = facilities.facid
where
        bookings.starttime >= '2012-09-21'
  and bookings.starttime < '2012-09-22'
  and facilities.name in(
                            'Tennis Court 1', 'Tennis Court 2'
    )
order by
bookings.starttime;
```

#### Question 14: How can you output a list of all members, including the individual who recommended them (if any)? Ensure that results are ordered by (surname, firstname).

```sql
select 
 mem.firstname as memfname, 
 mem.surname as memsname,
 ref.firstname as recfname,
 ref.surname as recsname
from 
	members mem
left outer Join members ref ON ref.memid = mem.recommendedby
order by memsname, memfname;
```

#### Question 15 : How can you output a list of all members who have recommended another member? Ensure that there are no duplicates in the list, and that results are ordered by (surname, firstname).

```sql
select
    DISTINCT ref.firstname,
             ref.surname
from
    members mem
        inner join members ref ON ref.memid = mem.recommendedby
order by ref.surname, ref.firstname;
```

#### Question 16 : How can you output a list of all members, including the individual who recommended them (if any), without using any joins? Ensure that there are no duplicates in the list, and that each firstname + surname pairing is formatted as a column and ordered. 

```sql
select
    distinct mem.firstname || ' ' || mem.surname as member,
             (
                 select
                         ref.firstname || ' ' || ref.surname as recommender
                 from
                     cd.members ref
                 where
                         ref.memid = mem.recommendedby
             )
from members mem
order by member;
```

#### Question 17 : Produce a count of the number of recommendations each member has made. Order by member ID. 

```sql
select
    recommendedby,
    COUNT(*)
from
    members
where
    recommendedby is not null
group by
    (recommendedby)
order by
recommendedby;
```

#### Question 18 : Produce a list of the total number of slots booked per facility. For now, just produce an output table consisting of facility id and slots, sorted by facility id.

```sql
select facid, sum(slots) from bookings where facid is not null group by (facid) order by (facid);
```

#### Question 19 : Produce a list of the total number of slots booked per facility in the month of September 2012. Produce an output table consisting of facility id and slots, sorted by the number of slots.

```sql
select facid, sum(slots) as "Total Slots"
  from bookings
  where starttime >= '2012-09-01' and starttime <= '2012-10-01'
  group by (facid)
  order by sum(slots);
```

#### Question 20 :Produce a list of the total number of slots booked per facility per month in the year of 2012. Produce an output table consisting of facility id and slots, sorted by the id and month. 

```sql
select
    facid,EXTRACT( Month from starttime) as month, sum(slots) as "Total slots"
from
    bookings
where
    EXTRACT(Year from starttime)= 2012
group by facid, month
order by facid, month;
```

#### Question 21 : Find the total number of members (including guests) who have made at least one booking.*/

```sql
select count(distinct memid) from bookings;
```

#### Question 22 : Produce a list of each member name, id, and their first booking after September 1st 2012. Order by member ID.

```sql
select mem.surname,
    mem.firstname,
    mem.memid,
    min(book.starttime) as starttime
from bookings book inner join members mem ON book.memid = mem.memid
where
        starttime >= '2012-09-01'
group by
    mem.surname,
    mem.firstname,
    mem.memid
order by
memid;
```

#### Question 23 : Produce a list of member names, with each row containing the total member count. Order by join date, and include guest members.

```sql
select 
	(select count(*) from members), firstname, surname from members 
	order by joindate
```	
#### Question 24 : Produce a monotonically increasing numbered list of members (including guests), ordered by their date of joining. Remember that member IDs are not guaranteed to be sequential.

```sql
select row_number() over(), firstname, surname
from members
order by joindate
```

#### Question 25 : Output the facility id that has the highest number of slots booked. Ensure that in the event of a tie, all tieing results get output. 

```sql
select facid, total from (
                             select facid, sum(slots) total, rank() over (order by sum(slots) desc) rank
                             from bookings
                             group by facid
                         ) as ranked
where rank = 1
```

#### Question 26 : Output the names of all members, formatted as 'Surname, Firstname' */

```sql
select surname || ',' || ' ' || firstname as name from members
```

#### Question 27 : You've noticed that the club's member table has telephone numbers with very inconsistent formatting. You'd like to find all the telephone numbers that contain parentheses, returning the member ID and telephone number sorted by member ID.

```sql
select memid, telephone from members where telephone ~ '[()]' order by memid
```

#### Question 28 : You'd like to produce a count of how many members you have whose surname starts with each letter of the alphabet. Sort by the letter, and don't worry about printing out a letter if the count is 0.

```sql
select substring(surname, 1, 1) as letter, count(*) from members
group by (substring(surname, 1, 1))
order by letter;
```
