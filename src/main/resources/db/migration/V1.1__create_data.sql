insert into account_types
       (code, description)
values ('SAVING', 'Savings'),
       ('CURRENT', 'Current'),
       ('FIXED_DEPOSIT', 'Fixed Deposit'),
       ('RECURRING_DEPOSIT', 'Recurring Deposit');

insert into account_role_types
       (code, description)
values ('OWNER', 'Owner');

insert into account_status_types
       (code, description)
values ('ACTIVE', 'Active'),
       ('INACTIVE', 'Inactive'),
       ('CLOSED', 'Closed');