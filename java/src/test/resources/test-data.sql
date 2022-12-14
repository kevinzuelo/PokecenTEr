BEGIN TRANSACTION;

DROP TABLE IF EXISTS trades, pokemon, collections, users CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email varchar(100),
	continent varchar(50),
	icon varchar(200),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE collections (
    collection_id SERIAL,
    collection_name varchar(100) NOT NULL,
    user_id int,
    is_private boolean,
    game varchar(100),
    CONSTRAINT PK_collections PRIMARY KEY (collection_id),
    CONSTRAINT FK_collections_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE pokemon (
    pokemon_id SERIAL,
    pokemon_name varchar(50),
    species varchar(50),
    type varchar(200),
    collection_id int,
    pokemon_level int,
    is_shiny boolean,
    notes varchar(1000),
    image_url varchar(200),
    image_sprite varchar(200),
    CONSTRAINT PK_pokemon PRIMARY KEY (pokemon_id),
    CONSTRAINT FK_pokemon_collection_id FOREIGN KEY (collection_id) REFERENCES collections (collection_id)
);

CREATE TABLE trades (
    trade_id SERIAL,
    from_pokemon int,
    to_pokemon int,
    trade_status varchar(50),
    CONSTRAINT PK_trades PRIMARY KEY (trade_id),
    CONSTRAINT FK_trades_from_pokemon FOREIGN KEY (from_pokemon) REFERENCES pokemon (pokemon_id),
    CONSTRAINT FK_trades_to_pokemon FOREIGN KEY (to_pokemon) REFERENCES pokemon (pokemon_id)
);

INSERT INTO users (username,password_hash,role,email,continent,icon) VALUES ('user1','user1','ROLE_USER','user1@google.com','North America','Squirtle');
INSERT INTO users (username,password_hash,role,email,continent,icon) VALUES ('user2','user2','ROLE_USER','user2@google.com','South America','Charmander'));
INSERT INTO users (username,password_hash,role,email,continent,icon) VALUES ('user3','user3','ROLE_USER','user3@google.com','Asia','Bulbasaur'));

--Give user1 one collection, user2 two collections, and user 3 zero collections
INSERT INTO collections (collection_name, user_id, is_private, game) VALUES ('user1 first collection',)
--Add some pokemon to user1's collection
COMMIT TRANSACTION;
