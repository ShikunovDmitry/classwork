PRAGMA foreign_keys = ON;

create TABLE IF NOT EXISTS users(
	userId int(10) PRIMARY KEY,
	name varchar(50),
	address varchar(255)
);
create TABLE IF NOT EXISTS Accounts(
	accountId int(10),
	userId int(10) NOT NULL,
	currency varchar(10) NOT NULL,
	balance decimal(15,3) CHECK (balance<=2000000000),
	PRIMARY KEY (userId, currency)
	FOREIGN KEY(userId) REFERENCES Users(userId)
);
create TABLE IF NOT EXISTS Transactions(
	transactionId int(10) PRIMARY KEY,
	accountId int(10) NOT NULL,
	amount decimal(15,3) NOT NULL CHECK (amount<=1000000),
	FOREIGN KEY(accountId) REFERENCES Accounts(accountId)
);