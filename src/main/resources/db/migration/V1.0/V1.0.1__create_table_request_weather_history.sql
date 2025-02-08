create table if not exists request_weather_history (
    id bigserial not null unique,
    description varchar(255),
    lat real,
    lon real,
    time_request time,

    primary key (id)
);