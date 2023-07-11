-- Delete the init change log in the table 'flyway_schema_history' if this file is modified;
-- Otherwise, the app will not start (because of the checksum)
# --- !Ups

create table task (
                    id uuid not null,
                    title varchar(255) not null,
                    description varchar(255) not null,
                    location point not null,
                    created_at timestamp not null,
                    updated_at timestamp not null,
                    primary key (id)
);

create table tasker (
                      id uuid not null,
                      name varchar(255) not null,
                      email varchar(255) not null,
                      phone_number varchar(255) not null,
                      location point not null,
                      created_at timestamp not null,
                      updated_at timestamp not null,
                      primary key (id)
);

create table bid (
                   id uuid not null,
                   task_id uuid not null,
                   tasker_id uuid not null,
                   amount decimal(19,2) not null,
                   created_at timestamp not null,
                   updated_at timestamp not null,
                   primary key (id)
);

alter table bid add constraint fk_bid_task_id foreign key (task_id) references task (id);
alter table bid add constraint fk_bid_tasker_id foreign key (tasker_id) references tasker (id);
