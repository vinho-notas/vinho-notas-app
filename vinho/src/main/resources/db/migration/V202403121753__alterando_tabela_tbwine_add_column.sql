alter table vinho.tbwine
add column dthreg timestamp default CURRENT_TIMESTAMP not null,
add column userreg varchar(30) null,
add column dthalt timestamp null,
add column useralt varchar(30) null;