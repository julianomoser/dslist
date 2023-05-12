create table tb_belonging
(
    position integer,
    list_id  bigint not null,
    game_id  bigint not null,
    primary key (game_id, list_id)
);

alter table tb_belonging
    add constraint fk_list_id
        foreign key (list_id) references tb_game_list (id);

alter table tb_belonging
    add constraint fk_game_id
        foreign key (game_id) references tb_game (id);
