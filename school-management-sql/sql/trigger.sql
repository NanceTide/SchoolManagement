create trigger students_insert
    after insert on students
    for each row begin
    update classes
    set size = size + 1
    where class_id = NEW.class_id;
end;

create trigger students_delete
    after delete on students
    for each row begin
    update classes
    set size = size - 1
    where class_id = OLD.class_id;
end;

create trigger students_update
    after update on students
    for each row begin
    update classes
    set size = size + 1
    where class_id = NEW.class_id;
    update classes
    set size = size - 1
    where class_id = OLD.class_id;
end;