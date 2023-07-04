create view majors_view as
    select m.major_id, m.major_name, d.*
    from majors m inner join departments d
    on m.department_id = d.department_id;

create view classes_view as
    select c.class_id, c.class_name, m.*
    from classes c inner join majors_view m
    on c.major_id = m.major_id;

create view students_view as
    select s.student_id, s.student_name, s.gender, s.address, s.birthday, s.enroll, s.nation, c.*
    from students s left join classes_view c
    on s.class_id = c.class_id;

create view grades_view as
    select s.student_id, s.student_name, c.*, g.grade
    from (students s inner join grades g on s.student_id = g.student_id)
        inner join courses c on g.course_id = c.course_id;