-- default passwords are "root" without quotes
insert into users(first_name, middle_name, last_name, username, password, password_never_expire, last_pw_change_dt,
                  active, add_dtm, add_by, chg_dtm, chg_by)
values ('test', 'user', null, 'test', '$2a$08$tRspuGll09uEXey.do5hI.uqHSBnsBN93P6x3z35yOHgnRCdAeY.6', false, CURRENT_TIMESTAMP(), true, CURRENT_TIMESTAMP(), 'H2',
        CURRENT_TIMESTAMP(), 'H2');

insert into users(first_name, middle_name, last_name, username, password, password_never_expire, last_pw_change_dt,
                  active, add_dtm, add_by, chg_dtm, chg_by)
values ('dev', 'user', null, 'dev', '$2a$08$tRspuGll09uEXey.do5hI.uqHSBnsBN93P6x3z35yOHgnRCdAeY.6', false, CURRENT_TIMESTAMP(), true, CURRENT_TIMESTAMP(), 'H2',
        CURRENT_TIMESTAMP(), 'H2');

insert into users(first_name, middle_name, last_name, username, password, password_never_expire, last_pw_change_dt,
                  active, add_dtm, add_by, chg_dtm, chg_by)
values ('Muhammad', 'Usama', null, 'usama28232', '$2a$08$tRspuGll09uEXey.do5hI.uqHSBnsBN93P6x3z35yOHgnRCdAeY.6', true, CURRENT_TIMESTAMP(), true, CURRENT_TIMESTAMP(), 'H2',
        CURRENT_TIMESTAMP(), 'H2');
