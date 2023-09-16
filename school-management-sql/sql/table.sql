# drop database if exists school_management;
create database school_management;

-- 院系(院系号, 院系名)
create table departments (
    department_id char(16) primary key,
    department_name char(24) not null
);

-- 专业(专业号, 专业名, 院系号)
create table majors (
    major_id char(16) primary key ,
    major_name char(24) not null,
    department_id char(16) references departments(department_id)
);

-- 班级(班级号, 班级名, 班级人数, 专业号)
create table classes (
    class_id char(16) primary key ,
    class_name char(24) not null ,
    size smallint default 0 not null,
    major_id char(16) references majors(major_id)
);

-- 学生(学号, 学生姓名, 性别, 入学年, 出生日期, 民族, 家庭住址, 班级号)
create table students (
    student_id char(16) primary key ,
    student_name char(24) not null ,
    gender smallint check(gender = 1 or gender = 0) not null,
    birthday date not null,
    enroll year not null,
    nation char(16) not null ,
    address char(24) not null ,
    class_id char(16) references classes(class_id)
);

-- 课程(课程号, 课程名, 学分)
create table courses (
    course_id char(16) primary key ,
    course_name char(24) not null ,
    credit smallint not null
);

-- 成绩(学号, 课程号, 成绩)
create table grades (
    course_id char(16) references courses(course_id),
    student_id char(16) references students(student_id),
    grade smallint check(grade >= 0 and grade <= 100 or grade is null) ,
    primary key (course_id, student_id)
);

-- 用户(用户名, 密码, 权限)
create table users (
    username char(32) primary key,
    password char(32) not null,
    access smallint
);

-- 异动申请(学号, 待转专业)
create table applications (
    student_id char(16) primary key references students(student_id),
    major_id char(16) references majors(major_id)
)

