create table tb_game
(
    id                bigserial not null,
    genre             varchar(255),
    img_url           varchar(255),
    long_description  TEXT,
    platforms         varchar(255),
    score             float(53),
    short_description TEXT,
    title             varchar(255),
    game_year         integer,
    primary key (id)
);
