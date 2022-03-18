INSERT INTO users (id, name, surname, email) VALUES
(hibernate_sequence.nextval, 'Anna', 'Nowak', 'anna.nowak@gmail.com'),
(hibernate_sequence.nextval, 'Jan', 'Kowalski', 'jan.kowalski@gmail.com'),
(hibernate_sequence.nextval, 'Grzegorz', 'Perk', 'grzegorz.perk@gmail.com');

INSERT INTO tasks (id, title, description, status, deadline) VALUES
(hibernate_sequence.nextval, 'Java Learning', 'SpringBoot application', 'IN_PROGRESS', '2022-03-18 12:00:00'),
(hibernate_sequence.nextval, 'Javascript Learning', 'React JS course', 'CANCEL', '2022-03-30 15:00:00'),
(hibernate_sequence.nextval, 'Active Break', 'Joga exercises', 'DONE','2022-02-18 09:00:00');