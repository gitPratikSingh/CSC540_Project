

INSERT INTO distributors
Values('D0001', 'D1');
INSERT INTO distributors
Values('D0002', 'D2');

INSERT INTO USERS 
VALUES('ethanhunt@gmail.com','Tom Cruise','203 Aviation Way, Raleigh, NC 27601','213-988-2011', '123456');

INSERT INTO CUSTOMER
VALUES(1001, 'ethanhunt@gmail.com', 'Tom Cruise', 'Address 1', '213-988-2011');

INSERT INTO CAR
VALUES('XYZ-5643', 'Honda', '2009', 'Civic', CAST(TO_DATE('2018-09-10', 'yyyy-mm-dd') AS TIMESTAMP), 'C', CAST(TO_DATE('2009-12-24', 'yyyy-mm-dd') AS TIMESTAMP),90452);

INSERT INTO CAR
VALUES('AHS-3132', 'Toyota', '2007', 'Prius', CAST(TO_DATE('2018-08-06', 'yyyy-mm-dd') AS TIMESTAMP), 'C', CAST(TO_DATE('2011-01-02', 'yyyy-mm-dd') AS TIMESTAMP),65452);

INSERT INTO HAS_CARS 
VALUES('XYZ-5643', 1001);
INSERT INTO HAS_CARS 
VALUES('AHS-3132', 1001);

INSERT INTO USERS 
VALUES('jarvis@gmail.com','Robert Downey Jr.','12-A, High St, Raleigh, NC - 27605','9989877791', '123456');

INSERT INTO CUSTOMER
VALUES(1002, 'jarvis@gmail.com', 'Robert Downey Jr.', '12-A, High St, Raleigh, NC - 27605', '213-988-2011');

INSERT INTO CAR
VALUES('IRM-1212', 'Nissan', '2001', 'Altima', 
CAST(TO_DATE('2018-02-11', 'yyyy-mm-dd') AS TIMESTAMP), 'A', CAST(TO_DATE('2002-09-07', 'yyyy-mm-dd') AS TIMESTAMP),210452);

INSERT INTO HAS_CARS 
VALUES('IRM-1212', 1002);

INSERT INTO USERS 
VALUES('lovestory@gmail.com','Taylor Swift','2A, 3rd Ave, Charlotte, NC - 28134','8179827199', '123456');

INSERT INTO CUSTOMER
VALUES(1003, 'lovestory@gmail.com', 'Taylor Swift','2A, 3rd Ave, Charlotte, NC - 28134','8179827199');

INSERT INTO CAR
VALUES('TSW-3462', 'Honda', '2015', 'Accord', 
null, null, CAST(TO_DATE('2015-12-09', 'yyyy-mm-dd') AS TIMESTAMP),
null);

INSERT INTO CAR
VALUES('DEL-8888', 'Nissan', '2014', 'Rogue', 
CAST(TO_DATE('2018-02-11', 'yyyy-mm-dd') AS TIMESTAMP), 'A', CAST(TO_DATE('2016-05-11', 'yyyy-mm-dd') AS TIMESTAMP),
31209);

INSERT INTO HAS_CARS 
VALUES('TSW-3462', 1003);
INSERT INTO HAS_CARS 
VALUES('DEL-8888', 1003);



INSERT INTO USERS 
VALUES('venus@gmail.com','Serena Williams','90, Gorman St, Charlotte, NC - 28201','8714791879', '123456');

INSERT INTO CUSTOMER
VALUES(1004, 'venus@gmail.com','Serena Williams','90, Gorman St, Charlotte, NC - 28201','8714791879');

INSERT INTO CAR
VALUES('P11-212A', 'Honda', '2009', 'Accord', 
CAST(TO_DATE('2017-09-01', 'yyyy-mm-dd') AS TIMESTAMP), 'B', CAST(TO_DATE('2015-04-14', 'yyyy-mm-dd') AS TIMESTAMP),
60452);

INSERT INTO CAR
VALUES('WIM-BLE5', 'Toyota', '2014', 'Prius', 
CAST(TO_DATE('2016-11-11', 'yyyy-mm-dd') AS TIMESTAMP), 'A', CAST(TO_DATE('2014-03-01', 'yyyy-mm-dd') AS TIMESTAMP),
19876);


INSERT INTO HAS_CARS 
VALUES('P11-212A', 1004);
INSERT INTO HAS_CARS 
VALUES('WIM-BLE5', 1004);


INSERT INTO BASIC_SERVICES
VALUES(1, 'Air filter change', 'Air filter', 50, 15, 'Toyota', 'Corolla', 1, 'B', 25000);
INSERT INTO BASIC_SERVICES
VALUES(2, 'Air filter change', 'Air filter', 50, 15, 'Toyota', 'Prius', 1, 'B', 28000);
INSERT INTO BASIC_SERVICES
VALUES(3, 'Air filter change', 'Air filter', 50, 15, 'Honda', 'Civic', 1, 'B', 29000);
INSERT INTO BASIC_SERVICES
VALUES(4, 'Air filter change', 'Air filter', 50, 15, 'Honda', 'Accord', 2, 'A', 15000);
INSERT INTO BASIC_SERVICES
VALUES(5, 'Air filter change', 'Air filter', 50, 15, 'Nissan', 'Altima', 1, 'A', 10000);
INSERT INTO BASIC_SERVICES
VALUES(6, 'Air filter change', 'Air filter', 50, 15, 'Nissan', 'Rogue', 3, 'A', 10000);

INSERT INTO BASIC_SERVICES
VALUES(7, 'Battery replacement', 'Battery', 50, 15, 'Toyota', 'Corolla', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(8, 'Battery replacement', 'Battery', 50, 15, 'Toyota', 'Prius', 1, 'B', 28000);
INSERT INTO BASIC_SERVICES
VALUES(9, 'Battery replacement', 'Battery', 50, 15, 'Honda', 'Civic', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(10, 'Battery replacement', 'Battery', 50, 15, 'Honda', 'Accord', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(11, 'Battery replacement', 'Battery', 50, 15, 'Nissan', 'Altima', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(12, 'Battery replacement', 'Battery', 50, 15, 'Nissan', 'Rogue', 2, 'C', 50000);


INSERT INTO BASIC_SERVICES
VALUES(13, 'Brake check', 'Brake fluid', 50, 15, 'Toyota', 'Corolla', 1, 'B', 25000);
INSERT INTO BASIC_SERVICES
VALUES(14, 'Brake check', 'Brake fluid', 50, 15, 'Toyota', 'Prius', 1, 'B', 28000);
INSERT INTO BASIC_SERVICES
VALUES(15, 'Brake check', 'Brake fluid', 50, 15, 'Honda', 'Civic', 1, 'A', 14000);
INSERT INTO BASIC_SERVICES
VALUES(16, 'Brake check', 'Brake fluid', 50, 15, 'Honda', 'Accord', 1, 'B', 37000);
INSERT INTO BASIC_SERVICES
VALUES(17, 'Brake check', 'Brake fluid', 50, 15, 'Nissan', 'Altima', 1, 'B', 25000);
INSERT INTO BASIC_SERVICES
VALUES(18, 'Brake check', 'Brake fluid', 50, 15, 'Nissan', 'Rogue', 2, 'A', 10000);


INSERT INTO BASIC_SERVICES
VALUES(19, 'Brake repair', 'Brake shoes', 50, 30, 'Toyota', 'Corolla', 4, 'C', 45000);
INSERT INTO BASIC_SERVICES
VALUES(20, 'Brake repair', 'Brake shoes', 50, 30, 'Toyota', 'Prius', 4, 'C', 58000);
INSERT INTO BASIC_SERVICES
VALUES(21, 'Brake repair', 'Brake shoes', 50, 30, 'Honda', 'Civic', 4, 'B', 29000);
INSERT INTO BASIC_SERVICES
VALUES(22, 'Brake repair', 'Brake shoes', 50, 30, 'Honda', 'Accord', 4, 'C', 67000);
INSERT INTO BASIC_SERVICES
VALUES(23, 'Brake repair', 'Brake shoes', 50, 30, 'Nissan', 'Altima', 4, 'C', 50000);
INSERT INTO BASIC_SERVICES
VALUES(24, 'Brake repair', 'Brake shoes', 50, 30, 'Nissan', 'Rogue', 4, 'C', 50000);

INSERT INTO BASIC_SERVICES
VALUES(25, 'Camshaft replacement', 'Camshaft', 65, 60, 'Toyota', 'Corolla', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(26, 'Camshaft replacement', 'Camshaft', 65, 60, 'Toyota', 'Prius', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(27, 'Camshaft replacement', 'Camshaft', 65, 60, 'Honda', 'Civic', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(28, 'Camshaft replacement', 'Camshaft', 65, 60, 'Honda', 'Accord', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(29, 'Camshaft replacement', 'Camshaft', 65, 60, 'Nissan', 'Altima', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(30, 'Camshaft replacement', 'Camshaft', 65, 60, 'Nissan', 'Rogue', 2, null, null);



INSERT INTO BASIC_SERVICES
VALUES(31, 'Catalytic convertor replacement', 'Catalytic convertor', 65, 60, 'Toyota', 'Corolla', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(32, 'Catalytic convertor replacement', 'Catalytic convertor', 65, 60, 'Toyota', 'Prius', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(33, 'Catalytic convertor replacement', 'Catalytic convertor', 65, 60, 'Honda', 'Civic', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(34, 'Catalytic convertor replacement', 'Catalytic convertor', 65, 60, 'Honda', 'Accord', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(35, 'Catalytic convertor replacement', 'Catalytic convertor', 65, 60, 'Nissan', 'Altima', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(36, 'Catalytic convertor replacement', 'Catalytic convertor', 65, 60, 'Nissan', 'Rogue', 1, null, null);

INSERT INTO BASIC_SERVICES
VALUES(37, 'Coolant recycle', 'Coolant', 50, 15, 'Toyota', 'Corolla', 1, 'A', 5000);
INSERT INTO BASIC_SERVICES
VALUES(38, 'Coolant recycle', 'Coolant', 50, 15, 'Toyota', 'Prius', 1, 'A', 10000);
INSERT INTO BASIC_SERVICES
VALUES(39, 'Coolant recycle', 'Coolant', 50, 15, 'Honda', 'Civic', 1, 'A', 14000);
INSERT INTO BASIC_SERVICES
VALUES(40, 'Coolant recycle', 'Coolant', 50, 15, 'Honda', 'Accord', 1, 'A', 15000);
INSERT INTO BASIC_SERVICES
VALUES(41, 'Coolant recycle', 'Coolant', 50, 15, 'Nissan', 'Altima', 2, 'A', 10000);
INSERT INTO BASIC_SERVICES
VALUES(42, 'Coolant recycle', 'Coolant', 50, 15, 'Nissan', 'Rogue', 2, 'A', 10000);


INSERT INTO BASIC_SERVICES
VALUES(43, 'Drive belt replacement', 'Drive belt', 65, 60, 'Toyota', 'Corolla', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(44, 'Drive belt replacement', 'Drive belt', 65, 60, 'Toyota', 'Prius', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(45, 'Drive belt replacement', 'Drive belt', 65, 60, 'Honda', 'Civic', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(46, 'Drive belt replacement', 'Drive belt', 65, 60, 'Honda', 'Accord', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(46, 'Drive belt replacement', 'Drive belt', 65, 60, 'Nissan', 'Altima', 1, null, null);
INSERT INTO BASIC_SERVICES
VALUES(48, 'Drive belt replacement', 'Drive belt', 65, 60, 'Nissan', 'Rogue', 1, null, null);

INSERT INTO BASIC_SERVICES
VALUES(49, 'Engine oil change', 'Engine oil', 50, 15, 'Toyota', 'Corolla', 1, 'A', 5000);
INSERT INTO BASIC_SERVICES
VALUES(50, 'Engine oil change', 'Engine oil', 50, 15, 'Toyota', 'Prius', 1, 'A', 10000);
INSERT INTO BASIC_SERVICES
VALUES(51, 'Engine oil change', 'Engine oil', 50, 15, 'Honda', 'Civic', 1, 'A', 14000);
INSERT INTO BASIC_SERVICES
VALUES(52, 'Engine oil change', 'Engine oil', 50, 15, 'Honda', 'Accord', 1, 'A', 15000);
INSERT INTO BASIC_SERVICES
VALUES(53, 'Engine oil change', 'Engine oil', 50, 15, 'Nissan', 'Altima', 2, 'A', 10000);
INSERT INTO BASIC_SERVICES
VALUES(54, 'Engine oil change', 'Engine oil', 50, 15, 'Nissan', 'Rogue', 3, 'A', 10000);


INSERT INTO BASIC_SERVICES
VALUES(55, 'Gearbox repair', 'Gears', 65, 60, 'Toyota', 'Corolla', 6, null, null);
INSERT INTO BASIC_SERVICES
VALUES(56, 'Gearbox repair', 'Gears', 65, 60, 'Toyota', 'Prius', 6, null, null);
INSERT INTO BASIC_SERVICES
VALUES(57, 'Gearbox repair', 'Gears', 65, 60, 'Honda', 'Civic', 12, null, null);
INSERT INTO BASIC_SERVICES
VALUES(58, 'Gearbox repair', 'Gears', 65, 60, 'Honda', 'Accord', 7, null, null);
INSERT INTO BASIC_SERVICES
VALUES(59, 'Gearbox repair', 'Gears', 65, 60, 'Nissan', 'Altima', 7, null, null);
INSERT INTO BASIC_SERVICES
VALUES(60, 'Gearbox repair', 'Gears', 65, 60, 'Nissan', 'Rogue', 9, null, null);


INSERT INTO BASIC_SERVICES
VALUES(61, 'Headlights replacement', 'Light assembly', 50, 30, 'Toyota', 'Corolla', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(62, 'Headlights replacement', 'Light assembly', 50, 30, 'Toyota', 'Prius', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(63, 'Headlights replacement', 'Light assembly', 50, 30, 'Honda', 'Civic', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(64, 'Headlights replacement', 'Light assembly', 50, 30, 'Honda', 'Accord', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(65, 'Headlights replacement', 'Light assembly', 50, 30, 'Nissan', 'Altima', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(66, 'Headlights replacement', 'Light assembly', 50, 30, 'Nissan', 'Rogue', 2, null, null);


INSERT INTO BASIC_SERVICES
VALUES(67, 'Oil filter change', 'Light assembly', 50, 15, 'Toyota', 'Corolla', 1, 'B', 25000);
INSERT INTO BASIC_SERVICES
VALUES(68, 'Oil filter change', 'Light assembly', 50, 15, 'Toyota', 'Prius', 1, 'A', 10000);
INSERT INTO BASIC_SERVICES
VALUES(69, 'Oil filter change', 'Light assembly', 50, 15, 'Honda', 'Civic', 1, 'B', 29000);
INSERT INTO BASIC_SERVICES
VALUES(70, 'Oil filter change', 'Light assembly', 50, 15, 'Honda', 'Accord', 1, 'A', 15000);
INSERT INTO BASIC_SERVICES
VALUES(71, 'Oil filter change', 'Light assembly', 50, 15, 'Nissan', 'Altima', 1, 'A', 10000);
INSERT INTO BASIC_SERVICES
VALUES(72, 'Oil filter change', 'Light assembly', 50, 15, 'Nissan', 'Rogue', 2, 'A', 10000);

INSERT INTO BASIC_SERVICES
VALUES(73, 'Piston replacement', 'Piston', 65, 60, 'Toyota', 'Corolla', 4, null, null);
INSERT INTO BASIC_SERVICES
VALUES(74, 'Piston replacement', 'Piston', 65, 60, 'Toyota', 'Prius', 4, null, null);
INSERT INTO BASIC_SERVICES
VALUES(75, 'Piston replacement', 'Piston', 65, 60, 'Honda', 'Civic', 4, null, null);
INSERT INTO BASIC_SERVICES
VALUES(76, 'Piston replacement', 'Piston', 65, 60, 'Honda', 'Accord', 4, null, null);
INSERT INTO BASIC_SERVICES
VALUES(77, 'Piston replacement', 'Piston', 65, 60, 'Nissan', 'Altima', 6, null, null);
INSERT INTO BASIC_SERVICES
VALUES(78, 'Piston replacement', 'Piston', 65, 60, 'Nissan', 'Rogue', 8, null, null);


INSERT INTO BASIC_SERVICES
VALUES(79, 'Power steering check', 'Power steering fluid', 50, 15, 'Toyota', 'Corolla', 1, 'C', 45000);
INSERT INTO BASIC_SERVICES
VALUES(80, 'Power steering check', 'Power steering fluid', 50, 15, 'Toyota', 'Prius', 1, 'C', 58000);
INSERT INTO BASIC_SERVICES
VALUES(81, 'Power steering check', 'Power steering fluid', 50, 15, 'Honda', 'Civic', 1, 'C', 44000);
INSERT INTO BASIC_SERVICES
VALUES(82, 'Power steering check', 'Power steering fluid', 50, 15, 'Honda', 'Accord', 1, 'C', 67000);
INSERT INTO BASIC_SERVICES
VALUES(83, 'Power steering check', 'Power steering fluid', 50, 15, 'Nissan', 'Altima', 1, 'C', 50000);
INSERT INTO BASIC_SERVICES
VALUES(84, 'Power steering check', 'Power steering fluid', 50, 15, 'Nissan', 'Rogue', 1, 'A', 10000);


INSERT INTO BASIC_SERVICES
VALUES(85, 'Spark plugs replacement', 'Spark plugs', 50, 15, 'Toyota', 'Corolla', 4, 'B', 25000);
INSERT INTO BASIC_SERVICES
VALUES(86, 'Spark plugs replacement', 'Spark plugs', 50, 15, 'Toyota', 'Prius', 4, 'B', 28000);
INSERT INTO BASIC_SERVICES
VALUES(87, 'Spark plugs replacement', 'Spark plugs', 50, 15, 'Honda', 'Civic', 4, 'B', 29000);
INSERT INTO BASIC_SERVICES
VALUES(88, 'Spark plugs replacement', 'Spark plugs', 50, 15, 'Honda', 'Accord', 4, 'B', 37000);
INSERT INTO BASIC_SERVICES
VALUES(89, 'Spark plugs replacement', 'Spark plugs', 50, 15, 'Nissan', 'Altima', 6, 'C', 50000);
INSERT INTO BASIC_SERVICES
VALUES(90, 'Spark plugs replacement', 'Spark plugs', 50, 15, 'Nissan', 'Rogue', 8, 'B', 37000);



INSERT INTO BASIC_SERVICES
VALUES(91, 'Suspension check', 'Suspension fluid', 50, 15, 'Toyota', 'Corolla', 1, 'C', 45000);
INSERT INTO BASIC_SERVICES
VALUES(92, 'Suspension check', 'Suspension fluid', 50, 15, 'Toyota', 'Prius', 1, 'C', 58000);
INSERT INTO BASIC_SERVICES
VALUES(93, 'Suspension check', 'Suspension fluid', 50, 15, 'Honda', 'Civic', 1, 'C', 44000);
INSERT INTO BASIC_SERVICES
VALUES(94, 'Suspension check', 'Suspension fluid', 50, 15, 'Honda', 'Accord', 1, 'C', 67000);
INSERT INTO BASIC_SERVICES
VALUES(95, 'Suspension check', 'Suspension fluid', 50, 15, 'Nissan', 'Altima', 1, 'C', 50000);
INSERT INTO BASIC_SERVICES
VALUES(96, 'Suspension check', 'Suspension fluid', 50, 15, 'Nissan', 'Rogue', 1, 'B', 37000);


INSERT INTO BASIC_SERVICES
VALUES(97, 'Tail lights replacement', 'Light assembly', 50, 30, 'Toyota', 'Corolla', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(98, 'Tail lights replacement', 'Light assembly', 50, 30, 'Toyota', 'Prius', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(99, 'Tail lights replacement', 'Light assembly', 50, 30, 'Honda', 'Civic', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(100, 'Tail lights replacement', 'Light assembly', 50, 30, 'Honda', 'Accord', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(101, 'Tail lights replacement', 'Light assembly', 50, 30, 'Nissan', 'Altima', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(102, 'Tail lights replacement', 'Light assembly', 50, 30, 'Nissan', 'Rogue', 2, null, null);


INSERT INTO BASIC_SERVICES
VALUES(103, 'Turn lights replacement', 'Light assembly', 50, 30, 'Toyota', 'Corolla', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(104, 'Turn lights replacement', 'Light assembly', 50, 30, 'Toyota', 'Prius', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(105, 'Turn lights replacement', 'Light assembly', 50, 30, 'Honda', 'Civic', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(106, 'Turn lights replacement', 'Light assembly', 50, 30, 'Honda', 'Accord', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(107, 'Turn lights replacement', 'Light assembly', 50, 30, 'Nissan', 'Altima', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(108, 'Turn lights replacement', 'Light assembly', 50, 30, 'Nissan', 'Rogue', 2, null, null);


INSERT INTO BASIC_SERVICES
VALUES(109, 'Valve replacement', 'Valves', 65, 60, 'Toyota', 'Corolla', 4, null, null);
INSERT INTO BASIC_SERVICES
VALUES(110, 'Valve replacement', 'Valves', 65, 60, 'Toyota', 'Prius', 4, null, null);
INSERT INTO BASIC_SERVICES
VALUES(111, 'Valve replacement', 'Valves', 65, 60, 'Honda', 'Civic', 4, null, null);
INSERT INTO BASIC_SERVICES
VALUES(112, 'Valve replacement', 'Valves', 65, 60, 'Honda', 'Accord', 4, null, null);
INSERT INTO BASIC_SERVICES
VALUES(113, 'Valve replacement', 'Valves', 65, 60, 'Nissan', 'Altima', 6, null, null);
INSERT INTO BASIC_SERVICES
VALUES(114, 'Valve replacement', 'Valves', 65, 60, 'Nissan', 'Rogue', 8, null, null);

INSERT INTO BASIC_SERVICES
VALUES(115, 'Wheel alignment', 'Axel rod', 65, 60, 'Toyota', 'Corolla', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(116, 'Wheel alignment', 'Axel rod', 65, 60, 'Toyota', 'Prius', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(117, 'Wheel alignment', 'Axel rod', 65, 60, 'Honda', 'Civic', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(118, 'Wheel alignment', 'Axel rod', 65, 60, 'Honda', 'Accord', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(119, 'Wheel alignment', 'Axel rod', 65, 60, 'Nissan', 'Altima', 2, null, null);
INSERT INTO BASIC_SERVICES
VALUES(120, 'Wheel alignment', 'Axel rod', 65, 60, 'Nissan', 'Rogue', 2, null, null);


INSERT INTO BASIC_SERVICES
VALUES(121, 'Wiper check', 'Wiper fluid', 50, 15, 'Toyota', 'Corolla', 1, 'B', 25000);
INSERT INTO BASIC_SERVICES
VALUES(122, 'Wiper check', 'Wiper fluid', 50, 15, 'Toyota', 'Prius', 1, 'B', 28000);
INSERT INTO BASIC_SERVICES
VALUES(123, 'Wiper check', 'Wiper fluid', 50, 15, 'Honda', 'Civic', 1, 'B', 29000);
INSERT INTO BASIC_SERVICES
VALUES(124, 'Wiper check', 'Wiper fluid', 50, 15, 'Honda', 'Accord', 1, 'B', 37000);
INSERT INTO BASIC_SERVICES
VALUES(125, 'Wiper check', 'Wiper fluid', 50, 15, 'Nissan', 'Altima', 1, 'B', 25000);
INSERT INTO BASIC_SERVICES
VALUES(126, 'Wiper check', 'Wiper fluid', 50, 15, 'Nissan', 'Rogue', 1, 'B', 37000);
