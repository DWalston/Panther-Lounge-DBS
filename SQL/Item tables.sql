CREATE TABLE IF NOT EXISTS items (
	id INT NOT NULL,
    itemName VARCHAR(64),
    itemType VARCHAR(16),
    shelf VARCHAR(8),
    club VARCHAR(8),
    series VARCHAR(64),
    itemStatus INT,
    rating INT,
    description VARCHAR(1024),
    CONSTRAINT PRIMARY KEY (id)
);
    
CREATE TABLE IF NOT EXISTS media (
	id INT NOT NULL,
    creator VARCHAR(32),
    length INT,
    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (id) REFERENCES items(id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS game (
	id INT NOT NULL,
    gameSystem VARCHAR(64),
    age INT,
    maxPlayer INT,
    playTime INT,
    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (id) REFERENCES items(id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);
        
CREATE TABLE IF NOT EXISTS hardware (
	id INT NOT NULL,
    age INT,
    condit VARCHAR(64),
    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (id) REFERENCES items(id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);
    
CREATE TABLE IF NOT EXISTS member (
	id INT NOT NULL,
    memberName VARCHAR(64),
    email VARCHAR(32),
    discord VARCHAR(32),
    phone VARCHAR(10),
    club VARCHAR(8),
    joined DATE,
    memberUntil DATE,
    flagged BOOL,
    password VARCHAR(64),
    CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `admin` (
	id INT NOT NULL,
    pos VARCHAR(16),
    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (id) REFERENCES member(id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS checkOut (
	memberId INT NOT NULL,
    itemId INT NOT NULL,
    coDate DATE,
    returned BOOL,
    returnDate DATE,
    CONSTRAINT PRIMARY KEY (memberId, itemId),
    CONSTRAINT FOREIGN KEY (memberId) REFERENCES member(id)
		ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (itemId) REFERENCES items(id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);
