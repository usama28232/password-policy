create table users
(
    id                    INT AUTO_INCREMENT PRIMARY KEY,
    first_name            varchar(100) not null,
    middle_name           varchar(100),
    last_name             varchar(100),
    username              varchar(100) not null,
    password              varchar(255) not null,
    password_never_expire bit          not null,
    last_pw_change_dt     TIMESTAMP    not null,
    active                bit          not null,
    add_dtm               timestamp    not null,
    add_by                varchar(100) default 'H2',
    chg_dtm               timestamp    not null,
    chg_by                varchar(100) default 'H2'
);