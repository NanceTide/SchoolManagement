create trigger students_after_insert
    after insert on students
    for each row
    begin
    update classes
        set size = size + 1
        where class_id = NEW.class_id;
    insert into users(username, password, access) values
        (NEW.student_id, '0000', 1);
    end;

create trigger students_before_insert
    before insert on students
    for each row
    begin
        if NEW.student_id in (
            select username
            from users
            where access = 0
        ) then
            signal sqlstate '45000'
                set message_text = 'student_id is in users with access = 0';
        end if;
    end;

create trigger students_delete
    after delete on students
    for each row
    begin
    update classes
        set size = size - 1
        where class_id = OLD.class_id;
    delete from users
        where OLD.student_id = username;
    end;

create trigger students_update
    after update on students
    for each row
    begin
    update classes
        set size = size + 1
        where class_id = NEW.class_id;
    update classes
        set size = size - 1
        where class_id = OLD.class_id;
    end;
