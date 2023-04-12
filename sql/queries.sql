
/* Question 1:The club is adding a new facility - a spa. We need to add it into the facilities table. Use the following values:

facid: 9, Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.*/

insert into facilities 
(facid,name,membercost,guestcost,initialoutlay,monthlymaintenance)
values(9,'spa',20,30,1000000,800);

/* Question 2 : Let's try adding the spa to the facilities table again. This time, though, we want to automatically generate the value for the next facid, rather than specifying it as a constant. Use the following values for everything else:

Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.*/

insert into facilities 
(facid,name,membercost,guestcost,initialoutlay,monthlymaintenance)
values((select max(facid) from facilities)+1 ,'spa',20,30,1000000,800);

/* Question 3 : We made a mistake when entering the data for the second tennis court. The initial outlay was 10000 rather than 8000: you need to alter the data to fix the error.*/

Update facilities set "initialoutlay" = 100000 where facid = 1;

/* Question 4 : We want to alter the price of the second tennis court so that it costs 10% more than the first one. Try to do this without using constant values for the prices, so that we can reuse the statement if we want to.*/

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

/* Question 5 : As part of a clearout of our database, we want to delete all bookings from the cd.bookings table. How can we accomplish this?*/

delete from bookings

/* Question 6 : We want to remove member 37, who has never made a booking, from our database. How can we achieve that? */

delete from
    members
	where memid = 37;
/* Question  7 :How can you produce a list of facilities that charge a fee to members, and that fee is less than 1/50th of the monthly maintenance cost? Return the facid, facility name, member cost, and monthly maintenance of the facilities in question.*/

select facid, name, membercost, monthlymaintenance from facilities 
	where 
		membercost > 0 
		and membercost < (monthlymaintenance * 0.02)

/* Question 8 : How can you produce a list of all facilities with the word 'Tennis' in their name?"*/

select * from facilities where name like '%Tennis%'

/* Question 9: How can you retrieve the details of facilities with ID 1 and 5? Try to do it without using the OR operator. */

select * from cd.facilities where facid in (1,5)

/* Question 10 : How can you produce a list of members who joined after the start of September 2012? Return the memid, surname, firstname, and joindate of the members in question."*/

select memid, surname, firstname, joindate from members where joindate >= ('2012-09-01')

/* Question 11 : You, for some reason, want a combined list of all surnames and all facility names. Yes, this is a contrived example :-). Produce that list!*/

select surname from members union select name from facilities


/* Question 12 : How can you produce a list of the start times for bookings by members named 'David Farrell'?*/

select starttime from bookings 
	INNER JOIN members ON bookings.memid = members.memid
	where 
		members.firstname = 'David'
  		and members.surname = 'Farrell';
/* Question 13 : How can you produce a list of the start times for bookings for tennis courts, for the date '2012-09-21'? Return a list of start time and facility name pairings, ordered by the time.*/

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

/* Question 14: How can you output a list of all members, including the individual who recommended them (if any)? Ensure that results are ordered by (surname, firstname).*/ 

select 
 mem.firstname as memfname, 
 mem.surname as memsname,
 ref.firstname as recfname,
 ref.surname as recsname

from 
	members mem
left outer Join members ref ON ref.memid = mem.recommendedby
order by
    memsname,
	memfname;

/* Question 15 : How can you output a list of all members who have recommended another member? Ensure that there are no duplicates in the list, and that results are ordered by (surname, firstname).*/

select
    DISTINCT ref.firstname,
             ref.surname
from
    members mem
        inner join members ref ON ref.memid = mem.recommendedby
order by
    ref.surname,
	ref.firstname;
/* Question 16 : How can you output a list of all members, including the individual who recommended them (if any), without using any joins? Ensure that there are no duplicates in the list, and that each firstname + surname pairing is formatted as a column and ordered. */
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
from
    members mem
order by
    member;

/* Question 17 : Produce a count of the number of recommendations each member has made. Order by member ID. */

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

/* Question 18 : Produce a list of the total number of slots booked per facility. For now, just produce an output table consisting of facility id and slots, sorted by facility id.*/
select facid, sum(slots) from bookings where facid is not null group by (facid) order by (facid);

/* Question 19 : Produce a list of the total number of slots booked per facility in the month of September 2012. Produce an output table consisting of facility id and slots, sorted by the number of slots.*/

select
    facid,
    sum(slots) as "Total Slots"
from
    bookings
where
        starttime >= '2012-09-01'
  and starttime <= '2012-10-01'
group by
    (facid)
order by
sum(slots);

/*Question 20 :Produce a list of the total number of slots booked per facility per month in the year of 2012. Produce an output table consisting of facility id and slots, sorted by the id and month. */

select
    facid,EXTRACT( Month from starttime) as month, sum(slots) as "Total slots"
from
    bookings
where
    EXTRACT(Year from starttime)= 2012
group by facid, month
order by facid, month;

/*Question 21 : Find the total number of members (including guests) who have made at least one booking.*/

select count(distinct memid) from bookings;

/* Question 22 : Produce a list of each member name, id, and their first booking after September 1st 2012. Order by member ID.*/

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

/* Question 23 : Produce a list of member names, with each row containing the total member count. Order by join date, and include guest members.*/

select 
	(select count(*) from members), firstname, surname from members 
	order by joindate
	
/*Question 24 : Produce a monotonically increasing numbered list of members (including guests), ordered by their date of joining. Remember that member IDs are not guaranteed to be sequential.*/

select row_number() over(), firstname, surname
from members
order by joindate

/* Question 25 : Output the facility id that has the highest number of slots booked. Ensure that in the event of a tie, all tieing results get output. */ 

select facid, total from (
                             select facid, sum(slots) total, rank() over (order by sum(slots) desc) rank
                             from bookings
                             group by facid
                         ) as ranked
where rank = 1

/* Question 26 : Output the names of all members, formatted as 'Surname, Firstname' */

select surname || ',' || ' ' || firstname as name from members


/* Question 27 : You've noticed that the club's member table has telephone numbers with very inconsistent formatting. You'd like to find all the telephone numbers that contain parentheses, returning the member ID and telephone number sorted by member ID.
*/

select memid, telephone from members where telephone ~ '[()]' order by memid

/* Question 28 : You'd like to produce a count of how many members you have whose surname starts with each letter of the alphabet. Sort by the letter, and don't worry about printing out a letter if the count is 0.
Schema reminder ? */

select substring(surname, 1, 1) as letter, count(*) from members
group by (substring(surname, 1, 1))
order by letter;







