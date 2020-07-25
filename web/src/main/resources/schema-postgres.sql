CREATE TABLE IF NOT EXISTS todo (
    todo_id varchar(36) primary key,
    todo_title varchar(30),
    finished boolean,
    created_at timestamp
);

CREATE TABLE IF NOT EXISTS account (
    username varchar(128),
    password varchar(88),
    first_name varchar(128),
    last_name varchar(128),
    constraint pk_tbl_account primary key (username)
);
