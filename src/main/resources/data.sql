INSERT INTO Policy_Types
VALUES(100,'Health'),(101,'Life');

INSERT INTO Policies
VALUES (1,'LIC','Health insurance policy with tenure of 5 years',5,2500,13500.00,100),
       (2, 'Life Secure', 'Life insurance policy with tenure of 10 years', 10, 5000, 55000.00, 101),
       (3, 'Health First', 'Health insurance policy with tenure of 5 years', 5, 3000, 16200.00, 100),
       (4, 'Life Plus', 'Life insurance policy with tenure of 15 years', 15, 8000, 132000.00, 101),
       (5, 'Health Pro', 'Health insurance policy with tenure of 10 years', 15, 4000, 65400.00, 100),
       (6, 'Life Ultra', 'Life insurance policy with tenure of 20 years', 20, 10000, 220000.00, 101);


INSERT INTO Subscriptions
VALUES (1000,1,'2024-02-28','Ramesh','Ramesh','New',1,'www.googledocs.32dsjdhrtejfsdmnr','Parent','Aadhar','434647465434');

INSERT INTO Users
VALUES ('Manager','manager@123','Manager',false),
       ('User','user@123','User',false);
