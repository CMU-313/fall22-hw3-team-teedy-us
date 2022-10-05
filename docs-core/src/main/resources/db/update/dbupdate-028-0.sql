create cached table T_DOCUMENT_REVIEWER ( DOR_ID_C varchar(36) not null, DOR_IDDOCUMENT_C varchar(36) not null, DOR_IDUSER_C varchar(36) not null, DOR_SCORE int, primary key (DOR_ID_C) );
alter table T_DOCUMENT_REVIEWER add constraint FK_DOR_IDDOCUMENT_C foreign key (DOR_IDDOCUMENT_C) references T_DOCUMENT (DOC_ID_C) on delete restrict on update restrict;
alter table T_DOCUMENT_REVIEWER add constraint FK_DOR_IDUSER_C foreign key (DOR_IDUSER_C) references T_USER (USE_ID_C) on delete restrict on update restrict;
update T_CONFIG set CFG_VALUE_C = '28' where CFG_ID_C = 'DB_VERSION';
