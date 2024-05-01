CREATE TABLE Policy_Types
(
		Id INTEGER,
        Type VARCHAR(10),
        CONSTRAINT PK_PolicyTypes PRIMARY KEY (Id)
);

CREATE TABLE Policies 
(	
		Policy_Id INTEGER,
        Policy_Name VARCHAR(30),
        Policy_Description VARCHAR(200),
        Tenure INTEGER,
        Monthly_Premium INTEGER,
        Maturity_Amount FLOAT(10),
        Policy_Type_Id INTEGER,
        CONSTRAINT PK_Policies PRIMARY KEY (Policy_Id),
        CONSTRAINT FK_PolicyTypes_Policies FOREIGN KEY (Policy_Type_Id) REFERENCES Policy_Types(Id)
);


CREATE TABLE Subscriptions
(
		Subscription_Id INTEGER,
		Policy_Id INTEGER,
        Subscription_Date date,
        Policy_Holder_Name VARCHAR(50),
        Username VARCHAR(6),
        Subscription_Status VARCHAR(15),
        Policies_Policy_Id INTEGER,
        Medical_Certificate_Doc_URL VARCHAR(200),
        Relation_To_Policy_Holder VARCHAR(15),
        Policy_Holder_Id_Proof_Type VARCHAR(10),
        Policy_Holder_Id_Proof_No VARCHAR(12),
        CONSTRAINT PK_Subscriptions PRIMARY KEY (Subscription_Id),
        CONSTRAINT FK_Policies_Subscriptions FOREIGN KEY (Policies_Policy_Id) REFERENCES Policies(Policy_Id)
);

CREATE TABLE Users(
        User_Name varchar(40) not null,
        Password varchar(40) not null,
        Role varchar(40) not null,
        Is_Account_Locked boolean
);