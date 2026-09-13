-- Q1. List the name of every student along with their department name, the courses they are enrolled in, 
--     7and the grade obtained — for Fall2023 only. Sort by department, then student name.

SELECT 
	s.name, d.dept_name, c.course_name, e.grade
FROM 
	Student s, Department d, Course c, Enrollment e 
WHERE 
	s.dept_id = d.dept_id AND s.student_id = e.student_id AND e.course_id = c.course_id AND e.semester = 'Fall2023';


-- Q2. Find the names of all instructors, their department, and the total number of distinct students who have ever taken any course they teach.
--     Instructors with zero students should still appear, with a count of 0.

select
	i.name,dd.dept_name, temp.numOfStudents
FROM
	Instructor i, Department dd,
	(SELECT 
		d.dept_id, count(s.student_id) AS numOfStudents 
	FROM 
		Department d, Student s
	WHERE 
		d.dept_id = s.dept_id
	GROUP BY 
		d.dept_id) AS temp
WHERE 
dd.dept_id = temp.dept_id;

-- Q3. List every course along with its instructor's name, the room and day it is scheduled, 
-- and the total number of students enrolled in it (any semester).

select 
	cc.course_name, i.name, cs.room_no, cs.day_of_week, temp.numOfStudents
FROM
	Course cc, Instructor i, Course_Schedule cs,
    (SELECT 
		c.course_id, count(e.student_id) as numOfStudents
	FROM
		Course c, Enrollment e
	where 
		c.course_id = e.course_id
	GROUP BY
		c.course_id) as temp
where cc.instructor_id = i.instructor_id AND 
	  cc.course_id = cs.course_id;


-- Q4. List the names of students who are enrolled in at least one course offered by a different department than their own home department.
--     Show student name, home department, course name, and the course's department.

select 
	s.name, s.dept_id, c.course_name, c.dept_id
FROM 
	Student s, Course c, Enrollment e
WHERE 
	e.student_id = s.student_id AND
    e.course_id = c.course_id AND
    c.dept_id <> s.dept_id;


-- Q5. Find pairs of students who are enrolled in the same course in the same semester but received different grades.
--  Show both student names, the course name, the semester, and both grades. Avoid showing the same pair twice (e.g., don't show both "A–B" and "B–A").

select 
	s1.name AS student1,
	s2.name AS student2,
    c.course_name,
    e1.semester,
    e1.grade AS student1_grade,
    e2.grade AS student1_grade
FROM 
	Student s1, Student s2,
    Course c, Enrollment e1,
    Enrollment e2
WHERE 
	s1.student_id = e1.student_id AND
    s2.student_id = e2.student_id AND
	e1.course_id = e2.course_id AND
    s1.student_id < s2.student_id AND
    e1.semester = e2.semester AND
    e1.grade <> e2.grade AND
    e1.course_id = c.course_id;

-- Q6. List the titles of books whose author has written books catalogued under more than one department. (Hint: this needs Book joined to itself,
--  or a GROUP BY author HAVING COUNT(DISTINCT dept_id) > 1, then joined back to fetch qualifying titles.)
SELECT
	title
FROM 
	Book
WHERE
	author in 
	(SELECT author FROM book GROUP BY author HAVING count(dept_id) > 1);

-- Q7. Find the names of instructors who teach more courses than the average number of courses taught per instructor
--  (computed across all instructors who teach at least one course).

SELECT i.name
FROM Instructor i,
     ( SELECT instructor_id, COUNT(*) AS course_count
         FROM Course
         GROUP BY instructor_id
     ) temp
WHERE i.instructor_id = temp.instructor_id
  AND temp.course_count > (
      SELECT AVG(avg_temp.course_count)
      FROM (
          SELECT instructor_id, COUNT(*) AS course_count
          FROM Course
          GROUP BY instructor_id
      ) avg_temp
  );

-- Q8. For each course, compute the "average grade point" of students enrolled in it, using this mapping: A = 4, B = 3, C = 2. 
-- Then list only the courses whose average grade point is higher than the overall average grade point across all enrollments in the university.
SELECT
    c.course_name,
    AVG(
        CASE
            WHEN e.grade = 'A' THEN 4
            WHEN e.grade = 'B' THEN 3
            WHEN e.grade = 'C' THEN 2
        END
    ) AS avg_grade_point
FROM
    Course c, Enrollment e
WHERE
    c.course_id = e.course_id
GROUP BY
    c.course_id, c.course_name
HAVING
    AVG(
        CASE
            WHEN e.grade = 'A' THEN 4
            WHEN e.grade = 'B' THEN 3
            WHEN e.grade = 'C' THEN 2
        END
    ) > (
        SELECT AVG(
            CASE
                WHEN grade = 'A' THEN 4
                WHEN grade = 'B' THEN 3
                WHEN grade = 'C' THEN 2
            END
        )
        FROM Enrollment
    );

-- Q9. Find the department(s) where the total fees collected (summing Fee_Payment.amount from students who belong to that department) 
-- is more than 20% of the department's budget. Show department name, total collected, budget, and the percentage.
SELECT 
    d1.dept_name
FROM
    Department d1,
    Fee_Payment fp,
    Student s
WHERE s.student_id = fp.student_id
      AND s.dept_id = d1.dept_id
GROUP BY
    d1.dept_id
HAVING
    SUM(fp.amount) > (SELECT 20 * budget / 100 AS budget
					  FROM Department d2
                      WHERE d2.dept_id = d1.dept_id);

-- Q10. Find students who have issued a book but never made a Spring2024 fee payment. Show student name, department, and how many books they've issued.

SELECT 
	s.name, d.dept_name, count(issue.issue_id) AS issue_count
FROM 
	Student s, Department d, Book_issue issue
WHERE s.dept_id = d.dept_id
	AND s.student_id = issue.student_id
    AND s.student_id IN ( 
    SELECT 
		DISTINCT bs.student_id
	FROM	 
		Book_Issue bs, Fee_payment fp
	WHERE
		bs.student_id = fp.student_id
    AND  fp.semester <> 'Spring2024')
GROUP BY 
	s.name, d.dept_name;

-- Q11. (Relational division) Find the student(s) who have enrolled in every single course offered by their own home department.
--  (Hint: for each student, there should be no course in their department that they have not taken — this is a classic double-NOT EXISTS pattern.)

SELECT s.name
FROM Student s
WHERE
(
    SELECT COUNT(DISTINCT e.course_id)
    FROM Course c, Enrollment e
    WHERE s.student_id = e.student_id
      AND e.course_id = c.course_id
      AND c.dept_id = s.dept_id
)
=
(
    SELECT COUNT(*)
    FROM Course c2
    WHERE c2.dept_id = s.dept_id
);

-- Q12. Find instructor(s) for whom every student who has ever taken one of their courses received a grade of A or B only — i.e.,
--  no student of theirs has ever gotten a C or lower. Show instructor name and department.

SELECT i.name, d.dept_name
FROM Instructor i, Department d
WHERE i.dept_id = d.dept_id
  AND NOT EXISTS (
      SELECT 1
      FROM Course c, Enrollment e
      WHERE c.instructor_id = i.instructor_id
        AND e.course_id = c.course_id
        AND e.grade NOT IN ('A', 'B')
  );

-- Q13. Find students who currently have an outstanding (unreturned) book — return_date IS NULL — and are also enrolled in a course scheduled
--  in a room that some other course also uses (a room shared by 2+ courses). Show student name, book title, and the shared room number.

SELECT
    s.name,
    b.title,
    cs.room_no
FROM
    Student s,
    Book_Issue bi,
    Book b,
    Enrollment e,
    Course c,
    Course_Schedule cs
WHERE
    s.student_id = bi.student_id
    AND bi.book_id = b.book_id
    AND bi.return_date IS NULL
    AND s.student_id = e.student_id
    AND e.course_id = c.course_id
    AND c.course_id = cs.course_id
    AND cs.room_no IN (
        SELECT room_no
        FROM Course_Schedule
        GROUP BY room_no
        HAVING COUNT(DISTINCT course_id) >= 2
    );

-- Q14. Using ALL, find the instructor(s) whose salary is greater than the salary of every instructor in the CS department (dept_id = 1),
--  excluding CS instructors themselves from the result.

SELECT i.name, i.salary
FROM Instructor i
WHERE i.dept_id <> 1
  AND i.salary > ALL (
      SELECT salary
      FROM Instructor
      WHERE dept_id = 1
  );

-- Q15. Using a window function (RANK() or DENSE_RANK()), find the top 2 students per department ranked by the number of distinct courses
--  they've enrolled in. Show department name, student name, distinct course count, and rank. Handle ties sensibly.

SELECT
    dept_name,
    name,
    course_count,
    student_rank
FROM (
    SELECT
        d.dept_name,
        s.name,
        COUNT(DISTINCT e.course_id) AS course_count,
        RANK() OVER (
            PARTITION BY s.dept_id
            ORDER BY COUNT(DISTINCT e.course_id) DESC
        ) AS student_rank
    FROM
        Student s, Department d, Enrollment e
    WHERE
        s.dept_id = d.dept_id
        AND s.student_id = e.student_id
    GROUP BY
        s.student_id, s.name, s.dept_id, d.dept_name
) temp
WHERE student_rank <= 2;

-- Q16. Find the second-highest paid instructor in each department (not the overall second-highest — per department), without using LIMIT.
--  Show department name, instructor name, and salary.

SELECT
    dept_name,
    name,
    salary
FROM (
    SELECT
        d.dept_name,
        i.name,
        i.salary,
        DENSE_RANK() OVER (
            PARTITION BY i.dept_id
            ORDER BY i.salary DESC
        ) AS rnk
    FROM
        Instructor i, Department d
    WHERE
        i.dept_id = d.dept_id
) temp
WHERE rnk = 2;

-- Q17. Using UNION, produce a single combined list of all people (students and instructors) affiliated with the CS department,
--  with columns person_name, role ('Student' or 'Instructor'), and dept_name.

SELECT
    s.name AS person_name,
    'Student' AS role,
    d.dept_name
FROM
    Student s, Department d
WHERE
    s.dept_id = d.dept_id
    AND s.dept_id = 1

UNION

SELECT
    i.name AS person_name,
    'Instructor' AS role,
    d.dept_name
FROM
    Instructor i, Department d
WHERE
    i.dept_id = d.dept_id
    AND i.dept_id = 1;

-- Q18. Produce a single summary report, one row per department, with the following columns:

-- Department name
-- Number of students in that department
-- Number of instructors in that department
-- Total number of enrollments generated by students of that department (across any course, any department)
-- Total fees collected from students of that department (all semesters combined)

-- Be careful: naively joining Department → Student → Enrollment and Department → Student → Fee_Payment in the same query will multiply rows and inflate your fee totals (a classic "fan-out" bug). Your query must produce correct, non-inflated totals for both counts.

SELECT
    d.dept_name,
    COALESCE(s.student_count, 0) AS student_count,
    COALESCE(i.instructor_count, 0) AS instructor_count,
    COALESCE(e.enrollment_count, 0) AS enrollment_count,
    COALESCE(f.total_fees, 0) AS total_fees
FROM Department d

LEFT JOIN (
    SELECT
        dept_id,
        COUNT(*) AS student_count
    FROM Student
    GROUP BY dept_id
) s
    ON d.dept_id = s.dept_id

LEFT JOIN (
    SELECT
        dept_id,
        COUNT(*) AS instructor_count
    FROM Instructor
    GROUP BY dept_id
) i
    ON d.dept_id = i.dept_id

LEFT JOIN (
    SELECT
        s.dept_id,
        COUNT(e.enroll_id) AS enrollment_count
    FROM Student s, Enrollment e
    WHERE s.student_id = e.student_id
    GROUP BY s.dept_id
) e
    ON d.dept_id = e.dept_id

LEFT JOIN (
    SELECT
        s.dept_id,
        SUM(fp.amount) AS total_fees
    FROM Student s, Fee_Payment fp
    WHERE s.student_id = fp.student_id
    GROUP BY s.dept_id
) f
    ON d.dept_id = f.dept_id;