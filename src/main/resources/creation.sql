-- Create options table
CREATE TABLE options
(
    id                          INT PRIMARY KEY AUTO_INCREMENT,
    filter_enabled              BOOLEAN,
    filter_debug                BOOLEAN,
    filter_word_manager         BOOLEAN,
    safe_mode_enabled           BOOLEAN,
    safe_mode_allow_my_websites BOOLEAN
);

-- Insert data into options table
INSERT INTO options (filter_enabled, filter_debug, filter_word_manager, safe_mode_enabled, safe_mode_allow_my_websites)
VALUES (true,
        false,
        false,
        true,
        true);

-- Create forbidden_words table
CREATE TABLE forbidden_words
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    word VARCHAR(255)
);

-- Insert data into forbidden_words table
INSERT INTO forbidden_words (word)
VALUES ('Vulgarity1'),
       ('Vulgarity2'),
       ('Vulgarity3'),
       ('and_so_on');

-- Create allowed_websites table
CREATE TABLE allowed_websites
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    website VARCHAR(255)
);

-- Insert data into allowed_websites table
INSERT INTO allowed_websites (website)
VALUES ('itsmyunderscore.me');

-- Create lang_phrases table
CREATE TABLE lang_phrases
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    message_sent     VARCHAR(255),
    no_permission    VARCHAR(255),
    reload_completed VARCHAR(255)
);

-- Insert data into lang_phrases table
INSERT INTO lang_phrases (message_sent, no_permission, reload_completed)
VALUES ('&4&l[CF] &1%PLAYER% &f just tried to say &4%WORD%',
        '&4You do not have permission to use this command!',
        '&4&l[CF] &aReload completed!');

-- Create lang_warnings table
CREATE TABLE lang_warnings
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    swearing VARCHAR(255),
    improper VARCHAR(255)
);

-- Insert data into lang_warnings table
INSERT INTO lang_warnings (swearing, improper)
VALUES ('&4Do not swear in chat!',
        '&4Do not send links or IP addresses!');
