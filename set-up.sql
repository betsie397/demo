CREATE TABLE users(
    user_id INT PRIMARY KEY,
    user_name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE instruments(
    instrument_id INT PRIMARY KEY,
    instrument_name VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE user_instruments(
    user_id INT NOT NULL,
    instrument_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (instrument_id) REFERENCES instruments(instrument_id)
    CONSTRAINT entry_id PRIMARY KEY (user_id, instrument_id)
);

CREATE TABLE practice_log(
    log_id INT PRIMARY KEY,
    user_id INT NOT NULL,
    practice_date DATE NOT NULL,
    duration VARCHAR(10) NOT NULL,
    instrument_id INT NOT NULL,
    scales TINYINT DEFAULT 0,
    exercises TINYINT DEFAULT 0,
    pieces TINYINT DEFAULT 0,
    description TEXT(50000) ,
    date_entered DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (instrument_id) REFERENCES instruments(instrument_id)
);

