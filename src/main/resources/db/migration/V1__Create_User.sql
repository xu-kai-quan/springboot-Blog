create table user(
    id                      bigint primary key auto_increment,
    username                varchar(100)  UNIQUE,
    encrypted_password      varchar(100),
    create_at               datetime,
    updated_at              datetime
)