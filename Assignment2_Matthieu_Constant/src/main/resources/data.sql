insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('employee', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into sec_role (roleName)
values ('ROLE_OWNER');
 
insert into sec_role (roleName)
values ('ROLE_EMPLOYEE');

insert into user_role (userId, roleId)
values (1, 1);

insert into user_role (userId, roleId)
values (2, 2);

 
INSERT INTO employee (name, employeeId, sunday, monday, tuesday, wednesday, thursday, friday, saturday, wage, totalHours, pay) VALUES
('John Doe', '156', 8, 6, 8, 8, 8, 8, 8, 12, 46, 732),
('Jane Dove', '134', 9, 6, 8, 8, 8, 8, 8, 16, 46, 732),
('Emily Eve', '112', 3, 6, 8, 8, 8, 8, 8, 18, 46, 732),
('Bruce Jones', '523', 4, 6, 8, 8, 8, 8, 8, 14, 46, 732),
('Bobby Robinson', '673', 6, 6, 8, 8, 8, 8, 8, 12, 46, 732),
('Grace Parker', '783', 5, 6, 8, 8, 8, 8, 8, 12, 46, 732),
('Grant Carter', '783', 4, 6, 8, 8, 8, 8, 8, 12, 46, 732),
('Stacy Hendley', '783', 9, 6, 8, 8, 8, 8, 8, 12, 46, 732),
('Ponathan Jenava', '783', 10, 6, 8, 8, 8, 8, 8, 12, 46, 732),
('Kevin Smith', '982', 6, 6, 8, 8, 8, 8, 8, 17, 46, 732);





