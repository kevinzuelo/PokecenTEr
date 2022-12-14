BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, collections, pokemon, trades, pokedex, friends;

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
    link_key varchar(50),
    CONSTRAINT PK_collections PRIMARY KEY (collection_id),
    CONSTRAINT FK_collections_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE pokemon (
    pokemon_id SERIAL,
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
    requested_pokemon int,
    offered_pokemon int,
    trade_status varchar(50),
    requested_pokemon_owner int,
    offered_pokemon_owner int,
    
    CONSTRAINT PK_trades PRIMARY KEY (trade_id),
    CONSTRAINT FK_trades_requested_pokemon FOREIGN KEY (requested_pokemon) REFERENCES pokemon (pokemon_id),
    CONSTRAINT FK_trades_offered_pokemon FOREIGN KEY (offered_pokemon) REFERENCES pokemon (pokemon_id),
    CONSTRAINT FK_trades_requested_pokemon_owner FOREIGN KEY (requested_pokemon_owner) REFERENCES users (user_id),
    CONSTRAINT FK_trades_offered_pokemon_owner FOREIGN KEY (offered_pokemon_owner) REFERENCES users (user_id)
    
);


CREATE TABLE friends (
    owner_id int NOT NULL,
    friend_id int NOT NULL,
	CONSTRAINT PK_friends PRIMARY KEY(owner_id, friend_id),
    CONSTRAINT FK_friends_owner FOREIGN KEY(owner_id) REFERENCES users(user_id),
    CONSTRAINT FK_friends_friend FOREIGN KEY(friend_id) REFERENCES users(user_id)
);

CREATE TABLE pokedex (
    id SERIAL,
    name varchar(50),
    CONSTRAINT PK_pokedex PRIMARY KEY (id)
);

INSERT INTO pokedex (name) VALUES ('bulbasaur');
INSERT INTO pokedex (name) VALUES ('ivysaur');
INSERT INTO pokedex (name) VALUES ('venusaur');
INSERT INTO pokedex (name) VALUES ('charmander');
INSERT INTO pokedex (name) VALUES ('charmeleon');
INSERT INTO pokedex (name) VALUES ('charizard');
INSERT INTO pokedex (name) VALUES ('squirtle');
INSERT INTO pokedex (name) VALUES ('wartortle');
INSERT INTO pokedex (name) VALUES ('blastoise');
INSERT INTO pokedex (name) VALUES ('caterpie');
INSERT INTO pokedex (name) VALUES ('metapod');
INSERT INTO pokedex (name) VALUES ('butterfree');
INSERT INTO pokedex (name) VALUES ('weedle');
INSERT INTO pokedex (name) VALUES ('kakuna');
INSERT INTO pokedex (name) VALUES ('beedrill');
INSERT INTO pokedex (name) VALUES ('pidgey');
INSERT INTO pokedex (name) VALUES ('pidgeotto');
INSERT INTO pokedex (name) VALUES ('pidgeot');
INSERT INTO pokedex (name) VALUES ('rattata');
INSERT INTO pokedex (name) VALUES ('raticate');
INSERT INTO pokedex (name) VALUES ('spearow');
INSERT INTO pokedex (name) VALUES ('fearow');
INSERT INTO pokedex (name) VALUES ('ekans');
INSERT INTO pokedex (name) VALUES ('arbok');
INSERT INTO pokedex (name) VALUES ('pikachu');
INSERT INTO pokedex (name) VALUES ('raichu');
INSERT INTO pokedex (name) VALUES ('sandshrew');
INSERT INTO pokedex (name) VALUES ('sandslash');
INSERT INTO pokedex (name) VALUES ('nidoran-f');
INSERT INTO pokedex (name) VALUES ('nidorina');
INSERT INTO pokedex (name) VALUES ('nidoqueen');
INSERT INTO pokedex (name) VALUES ('nidoran-m');
INSERT INTO pokedex (name) VALUES ('nidorino');
INSERT INTO pokedex (name) VALUES ('nidoking');
INSERT INTO pokedex (name) VALUES ('clefairy');
INSERT INTO pokedex (name) VALUES ('clefable');
INSERT INTO pokedex (name) VALUES ('vulpix');
INSERT INTO pokedex (name) VALUES ('ninetales');
INSERT INTO pokedex (name) VALUES ('jigglypuff');
INSERT INTO pokedex (name) VALUES ('wigglytuff');
INSERT INTO pokedex (name) VALUES ('zubat');
INSERT INTO pokedex (name) VALUES ('golbat');
INSERT INTO pokedex (name) VALUES ('oddish');
INSERT INTO pokedex (name) VALUES ('gloom');
INSERT INTO pokedex (name) VALUES ('vileplume');
INSERT INTO pokedex (name) VALUES ('paras');
INSERT INTO pokedex (name) VALUES ('parasect');
INSERT INTO pokedex (name) VALUES ('venonat');
INSERT INTO pokedex (name) VALUES ('venomoth');
INSERT INTO pokedex (name) VALUES ('diglett');
INSERT INTO pokedex (name) VALUES ('dugtrio');
INSERT INTO pokedex (name) VALUES ('meowth');
INSERT INTO pokedex (name) VALUES ('persian');
INSERT INTO pokedex (name) VALUES ('psyduck');
INSERT INTO pokedex (name) VALUES ('golduck');
INSERT INTO pokedex (name) VALUES ('mankey');
INSERT INTO pokedex (name) VALUES ('primeape');
INSERT INTO pokedex (name) VALUES ('growlithe');
INSERT INTO pokedex (name) VALUES ('arcanine');
INSERT INTO pokedex (name) VALUES ('poliwag');
INSERT INTO pokedex (name) VALUES ('poliwhirl');
INSERT INTO pokedex (name) VALUES ('poliwrath');
INSERT INTO pokedex (name) VALUES ('abra');
INSERT INTO pokedex (name) VALUES ('kadabra');
INSERT INTO pokedex (name) VALUES ('alakazam');
INSERT INTO pokedex (name) VALUES ('machop');
INSERT INTO pokedex (name) VALUES ('machoke');
INSERT INTO pokedex (name) VALUES ('machamp');
INSERT INTO pokedex (name) VALUES ('bellsprout');
INSERT INTO pokedex (name) VALUES ('weepinbell');
INSERT INTO pokedex (name) VALUES ('victreebel');
INSERT INTO pokedex (name) VALUES ('tentacool');
INSERT INTO pokedex (name) VALUES ('tentacruel');
INSERT INTO pokedex (name) VALUES ('geodude');
INSERT INTO pokedex (name) VALUES ('graveler');
INSERT INTO pokedex (name) VALUES ('golem');
INSERT INTO pokedex (name) VALUES ('ponyta');
INSERT INTO pokedex (name) VALUES ('rapidash');
INSERT INTO pokedex (name) VALUES ('slowpoke');
INSERT INTO pokedex (name) VALUES ('slowbro');
INSERT INTO pokedex (name) VALUES ('magnemite');
INSERT INTO pokedex (name) VALUES ('magneton');
INSERT INTO pokedex (name) VALUES ('farfetchd');
INSERT INTO pokedex (name) VALUES ('doduo');
INSERT INTO pokedex (name) VALUES ('dodrio');
INSERT INTO pokedex (name) VALUES ('seel');
INSERT INTO pokedex (name) VALUES ('dewgong');
INSERT INTO pokedex (name) VALUES ('grimer');
INSERT INTO pokedex (name) VALUES ('muk');
INSERT INTO pokedex (name) VALUES ('shellder');
INSERT INTO pokedex (name) VALUES ('cloyster');
INSERT INTO pokedex (name) VALUES ('gastly');
INSERT INTO pokedex (name) VALUES ('haunter');
INSERT INTO pokedex (name) VALUES ('gengar');
INSERT INTO pokedex (name) VALUES ('onix');
INSERT INTO pokedex (name) VALUES ('drowzee');
INSERT INTO pokedex (name) VALUES ('hypno');
INSERT INTO pokedex (name) VALUES ('krabby');
INSERT INTO pokedex (name) VALUES ('kingler');
INSERT INTO pokedex (name) VALUES ('voltorb');
INSERT INTO pokedex (name) VALUES ('electrode');
INSERT INTO pokedex (name) VALUES ('exeggcute');
INSERT INTO pokedex (name) VALUES ('exeggutor');
INSERT INTO pokedex (name) VALUES ('cubone');
INSERT INTO pokedex (name) VALUES ('marowak');
INSERT INTO pokedex (name) VALUES ('hitmonlee');
INSERT INTO pokedex (name) VALUES ('hitmonchan');
INSERT INTO pokedex (name) VALUES ('lickitung');
INSERT INTO pokedex (name) VALUES ('koffing');
INSERT INTO pokedex (name) VALUES ('weezing');
INSERT INTO pokedex (name) VALUES ('rhyhorn');
INSERT INTO pokedex (name) VALUES ('rhydon');
INSERT INTO pokedex (name) VALUES ('chansey');
INSERT INTO pokedex (name) VALUES ('tangela');
INSERT INTO pokedex (name) VALUES ('kangaskhan');
INSERT INTO pokedex (name) VALUES ('horsea');
INSERT INTO pokedex (name) VALUES ('seadra');
INSERT INTO pokedex (name) VALUES ('goldeen');
INSERT INTO pokedex (name) VALUES ('seaking');
INSERT INTO pokedex (name) VALUES ('staryu');
INSERT INTO pokedex (name) VALUES ('starmie');
INSERT INTO pokedex (name) VALUES ('mr. mime');
INSERT INTO pokedex (name) VALUES ('scyther');
INSERT INTO pokedex (name) VALUES ('jynx');
INSERT INTO pokedex (name) VALUES ('electabuzz');
INSERT INTO pokedex (name) VALUES ('magmar');
INSERT INTO pokedex (name) VALUES ('pinsir');
INSERT INTO pokedex (name) VALUES ('tauros');
INSERT INTO pokedex (name) VALUES ('magikarp');
INSERT INTO pokedex (name) VALUES ('gyarados');
INSERT INTO pokedex (name) VALUES ('lapras');
INSERT INTO pokedex (name) VALUES ('ditto');
INSERT INTO pokedex (name) VALUES ('eevee');
INSERT INTO pokedex (name) VALUES ('vaporeon');
INSERT INTO pokedex (name) VALUES ('jolteon');
INSERT INTO pokedex (name) VALUES ('flareon');
INSERT INTO pokedex (name) VALUES ('porygon');
INSERT INTO pokedex (name) VALUES ('omanyte');
INSERT INTO pokedex (name) VALUES ('omastar');
INSERT INTO pokedex (name) VALUES ('kabuto');
INSERT INTO pokedex (name) VALUES ('kabutops');
INSERT INTO pokedex (name) VALUES ('aerodactyl');
INSERT INTO pokedex (name) VALUES ('snorlax');
INSERT INTO pokedex (name) VALUES ('articuno');
INSERT INTO pokedex (name) VALUES ('zapdos');
INSERT INTO pokedex (name) VALUES ('moltres');
INSERT INTO pokedex (name) VALUES ('dratini');
INSERT INTO pokedex (name) VALUES ('dragonair');
INSERT INTO pokedex (name) VALUES ('dragonite');
INSERT INTO pokedex (name) VALUES ('mewtwo');
INSERT INTO pokedex (name) VALUES ('mew');
INSERT INTO pokedex (name) VALUES ('chikorita');
INSERT INTO pokedex (name) VALUES ('bayleef');
INSERT INTO pokedex (name) VALUES ('meganium');
INSERT INTO pokedex (name) VALUES ('cyndaquil');
INSERT INTO pokedex (name) VALUES ('quilava');
INSERT INTO pokedex (name) VALUES ('typhlosion');
INSERT INTO pokedex (name) VALUES ('totodile');
INSERT INTO pokedex (name) VALUES ('croconaw');
INSERT INTO pokedex (name) VALUES ('feraligatr');
INSERT INTO pokedex (name) VALUES ('sentret');
INSERT INTO pokedex (name) VALUES ('furret');
INSERT INTO pokedex (name) VALUES ('hoothoot');
INSERT INTO pokedex (name) VALUES ('noctowl');
INSERT INTO pokedex (name) VALUES ('ledyba');
INSERT INTO pokedex (name) VALUES ('ledian');
INSERT INTO pokedex (name) VALUES ('spinarak');
INSERT INTO pokedex (name) VALUES ('ariados');
INSERT INTO pokedex (name) VALUES ('crobat');
INSERT INTO pokedex (name) VALUES ('chinchou');
INSERT INTO pokedex (name) VALUES ('lanturn');
INSERT INTO pokedex (name) VALUES ('pichu');
INSERT INTO pokedex (name) VALUES ('cleffa');
INSERT INTO pokedex (name) VALUES ('igglybuff');
INSERT INTO pokedex (name) VALUES ('togepi');
INSERT INTO pokedex (name) VALUES ('togetic');
INSERT INTO pokedex (name) VALUES ('natu');
INSERT INTO pokedex (name) VALUES ('xatu');
INSERT INTO pokedex (name) VALUES ('mareep');
INSERT INTO pokedex (name) VALUES ('flaaffy');
INSERT INTO pokedex (name) VALUES ('ampharos');
INSERT INTO pokedex (name) VALUES ('bellossom');
INSERT INTO pokedex (name) VALUES ('marill');
INSERT INTO pokedex (name) VALUES ('azumarill');
INSERT INTO pokedex (name) VALUES ('sudowoodo');
INSERT INTO pokedex (name) VALUES ('politoed');
INSERT INTO pokedex (name) VALUES ('hoppip');
INSERT INTO pokedex (name) VALUES ('skiploom');
INSERT INTO pokedex (name) VALUES ('jumpluff');
INSERT INTO pokedex (name) VALUES ('aipom');
INSERT INTO pokedex (name) VALUES ('sunkern');
INSERT INTO pokedex (name) VALUES ('sunflora');
INSERT INTO pokedex (name) VALUES ('yanma');
INSERT INTO pokedex (name) VALUES ('wooper');
INSERT INTO pokedex (name) VALUES ('quagsire');
INSERT INTO pokedex (name) VALUES ('espeon');
INSERT INTO pokedex (name) VALUES ('umbreon');
INSERT INTO pokedex (name) VALUES ('murkrow');
INSERT INTO pokedex (name) VALUES ('slowking');
INSERT INTO pokedex (name) VALUES ('misdreavus');
INSERT INTO pokedex (name) VALUES ('unown');
INSERT INTO pokedex (name) VALUES ('wobbuffet');
INSERT INTO pokedex (name) VALUES ('girafarig');
INSERT INTO pokedex (name) VALUES ('pineco');
INSERT INTO pokedex (name) VALUES ('forretress');
INSERT INTO pokedex (name) VALUES ('dunsparce');
INSERT INTO pokedex (name) VALUES ('gligar');
INSERT INTO pokedex (name) VALUES ('steelix');
INSERT INTO pokedex (name) VALUES ('snubbull');
INSERT INTO pokedex (name) VALUES ('granbull');
INSERT INTO pokedex (name) VALUES ('qwilfish');
INSERT INTO pokedex (name) VALUES ('scizor');
INSERT INTO pokedex (name) VALUES ('shuckle');
INSERT INTO pokedex (name) VALUES ('heracross');
INSERT INTO pokedex (name) VALUES ('sneasel');
INSERT INTO pokedex (name) VALUES ('teddiursa');
INSERT INTO pokedex (name) VALUES ('ursaring');
INSERT INTO pokedex (name) VALUES ('slugma');
INSERT INTO pokedex (name) VALUES ('magcargo');
INSERT INTO pokedex (name) VALUES ('swinub');
INSERT INTO pokedex (name) VALUES ('piloswine');
INSERT INTO pokedex (name) VALUES ('corsola');
INSERT INTO pokedex (name) VALUES ('remoraid');
INSERT INTO pokedex (name) VALUES ('octillery');
INSERT INTO pokedex (name) VALUES ('delibird');
INSERT INTO pokedex (name) VALUES ('mantine');
INSERT INTO pokedex (name) VALUES ('skarmory');
INSERT INTO pokedex (name) VALUES ('houndour');
INSERT INTO pokedex (name) VALUES ('houndoom');
INSERT INTO pokedex (name) VALUES ('kingdra');
INSERT INTO pokedex (name) VALUES ('phanpy');
INSERT INTO pokedex (name) VALUES ('donphan');
INSERT INTO pokedex (name) VALUES ('porygon2');
INSERT INTO pokedex (name) VALUES ('stantler');
INSERT INTO pokedex (name) VALUES ('smeargle');
INSERT INTO pokedex (name) VALUES ('tyrogue');
INSERT INTO pokedex (name) VALUES ('hitmontop');
INSERT INTO pokedex (name) VALUES ('smoochum');
INSERT INTO pokedex (name) VALUES ('elekid');
INSERT INTO pokedex (name) VALUES ('magby');
INSERT INTO pokedex (name) VALUES ('miltank');
INSERT INTO pokedex (name) VALUES ('blissey');
INSERT INTO pokedex (name) VALUES ('raikou');
INSERT INTO pokedex (name) VALUES ('entei');
INSERT INTO pokedex (name) VALUES ('suicune');
INSERT INTO pokedex (name) VALUES ('larvitar');
INSERT INTO pokedex (name) VALUES ('pupitar');
INSERT INTO pokedex (name) VALUES ('tyranitar');
INSERT INTO pokedex (name) VALUES ('lugia');
INSERT INTO pokedex (name) VALUES ('ho-oh');
INSERT INTO pokedex (name) VALUES ('celebi');
INSERT INTO pokedex (name) VALUES ('treecko');
INSERT INTO pokedex (name) VALUES ('grovyle');
INSERT INTO pokedex (name) VALUES ('sceptile');
INSERT INTO pokedex (name) VALUES ('torchic');
INSERT INTO pokedex (name) VALUES ('combusken');
INSERT INTO pokedex (name) VALUES ('blaziken');
INSERT INTO pokedex (name) VALUES ('mudkip');
INSERT INTO pokedex (name) VALUES ('marshtomp');
INSERT INTO pokedex (name) VALUES ('swampert');
INSERT INTO pokedex (name) VALUES ('poochyena');
INSERT INTO pokedex (name) VALUES ('mightyena');
INSERT INTO pokedex (name) VALUES ('zigzagoon');
INSERT INTO pokedex (name) VALUES ('linoone');
INSERT INTO pokedex (name) VALUES ('wurmple');
INSERT INTO pokedex (name) VALUES ('silcoon');
INSERT INTO pokedex (name) VALUES ('beautifly');
INSERT INTO pokedex (name) VALUES ('cascoon');
INSERT INTO pokedex (name) VALUES ('dustox');
INSERT INTO pokedex (name) VALUES ('lotad');
INSERT INTO pokedex (name) VALUES ('lombre');
INSERT INTO pokedex (name) VALUES ('ludicolo');
INSERT INTO pokedex (name) VALUES ('seedot');
INSERT INTO pokedex (name) VALUES ('nuzleaf');
INSERT INTO pokedex (name) VALUES ('shiftry');
INSERT INTO pokedex (name) VALUES ('taillow');
INSERT INTO pokedex (name) VALUES ('swellow');
INSERT INTO pokedex (name) VALUES ('wingull');
INSERT INTO pokedex (name) VALUES ('pelipper');
INSERT INTO pokedex (name) VALUES ('ralts');
INSERT INTO pokedex (name) VALUES ('kirlia');
INSERT INTO pokedex (name) VALUES ('gardevoir');
INSERT INTO pokedex (name) VALUES ('surskit');
INSERT INTO pokedex (name) VALUES ('masquerain');
INSERT INTO pokedex (name) VALUES ('shroomish');
INSERT INTO pokedex (name) VALUES ('breloom');
INSERT INTO pokedex (name) VALUES ('slakoth');
INSERT INTO pokedex (name) VALUES ('vigoroth');
INSERT INTO pokedex (name) VALUES ('slaking');
INSERT INTO pokedex (name) VALUES ('nincada');
INSERT INTO pokedex (name) VALUES ('ninjask');
INSERT INTO pokedex (name) VALUES ('shedinja');
INSERT INTO pokedex (name) VALUES ('whismur');
INSERT INTO pokedex (name) VALUES ('loudred');
INSERT INTO pokedex (name) VALUES ('exploud');
INSERT INTO pokedex (name) VALUES ('makuhita');
INSERT INTO pokedex (name) VALUES ('hariyama');
INSERT INTO pokedex (name) VALUES ('azurill');
INSERT INTO pokedex (name) VALUES ('nosepass');
INSERT INTO pokedex (name) VALUES ('skitty');
INSERT INTO pokedex (name) VALUES ('delcatty');
INSERT INTO pokedex (name) VALUES ('sableye');
INSERT INTO pokedex (name) VALUES ('mawile');
INSERT INTO pokedex (name) VALUES ('aron');
INSERT INTO pokedex (name) VALUES ('lairon');
INSERT INTO pokedex (name) VALUES ('aggron');
INSERT INTO pokedex (name) VALUES ('meditite');
INSERT INTO pokedex (name) VALUES ('medicham');
INSERT INTO pokedex (name) VALUES ('electrike');
INSERT INTO pokedex (name) VALUES ('manectric');
INSERT INTO pokedex (name) VALUES ('plusle');
INSERT INTO pokedex (name) VALUES ('minun');
INSERT INTO pokedex (name) VALUES ('volbeat');
INSERT INTO pokedex (name) VALUES ('illumise');
INSERT INTO pokedex (name) VALUES ('roselia');
INSERT INTO pokedex (name) VALUES ('gulpin');
INSERT INTO pokedex (name) VALUES ('swalot');
INSERT INTO pokedex (name) VALUES ('carvanha');
INSERT INTO pokedex (name) VALUES ('sharpedo');
INSERT INTO pokedex (name) VALUES ('wailmer');
INSERT INTO pokedex (name) VALUES ('wailord');
INSERT INTO pokedex (name) VALUES ('numel');
INSERT INTO pokedex (name) VALUES ('camerupt');
INSERT INTO pokedex (name) VALUES ('torkoal');
INSERT INTO pokedex (name) VALUES ('spoink');
INSERT INTO pokedex (name) VALUES ('grumpig');
INSERT INTO pokedex (name) VALUES ('spinda');
INSERT INTO pokedex (name) VALUES ('trapinch');
INSERT INTO pokedex (name) VALUES ('vibrava');
INSERT INTO pokedex (name) VALUES ('flygon');
INSERT INTO pokedex (name) VALUES ('cacnea');
INSERT INTO pokedex (name) VALUES ('cacturne');
INSERT INTO pokedex (name) VALUES ('swablu');
INSERT INTO pokedex (name) VALUES ('altaria');
INSERT INTO pokedex (name) VALUES ('zangoose');
INSERT INTO pokedex (name) VALUES ('seviper');
INSERT INTO pokedex (name) VALUES ('lunatone');
INSERT INTO pokedex (name) VALUES ('solrock');
INSERT INTO pokedex (name) VALUES ('barboach');
INSERT INTO pokedex (name) VALUES ('whiscash');
INSERT INTO pokedex (name) VALUES ('corphish');
INSERT INTO pokedex (name) VALUES ('crawdaunt');
INSERT INTO pokedex (name) VALUES ('baltoy');
INSERT INTO pokedex (name) VALUES ('claydol');
INSERT INTO pokedex (name) VALUES ('lileep');
INSERT INTO pokedex (name) VALUES ('cradily');
INSERT INTO pokedex (name) VALUES ('anorith');
INSERT INTO pokedex (name) VALUES ('armaldo');
INSERT INTO pokedex (name) VALUES ('feebas');
INSERT INTO pokedex (name) VALUES ('milotic');
INSERT INTO pokedex (name) VALUES ('castform');
INSERT INTO pokedex (name) VALUES ('kecleon');
INSERT INTO pokedex (name) VALUES ('shuppet');
INSERT INTO pokedex (name) VALUES ('banette');
INSERT INTO pokedex (name) VALUES ('duskull');
INSERT INTO pokedex (name) VALUES ('dusclops');
INSERT INTO pokedex (name) VALUES ('tropius');
INSERT INTO pokedex (name) VALUES ('chimecho');
INSERT INTO pokedex (name) VALUES ('absol');
INSERT INTO pokedex (name) VALUES ('wynaut');
INSERT INTO pokedex (name) VALUES ('snorunt');
INSERT INTO pokedex (name) VALUES ('glalie');
INSERT INTO pokedex (name) VALUES ('spheal');
INSERT INTO pokedex (name) VALUES ('sealeo');
INSERT INTO pokedex (name) VALUES ('walrein');
INSERT INTO pokedex (name) VALUES ('clamperl');
INSERT INTO pokedex (name) VALUES ('huntail');
INSERT INTO pokedex (name) VALUES ('gorebyss');
INSERT INTO pokedex (name) VALUES ('relicanth');
INSERT INTO pokedex (name) VALUES ('luvdisc');
INSERT INTO pokedex (name) VALUES ('bagon');
INSERT INTO pokedex (name) VALUES ('shelgon');
INSERT INTO pokedex (name) VALUES ('salamence');
INSERT INTO pokedex (name) VALUES ('beldum');
INSERT INTO pokedex (name) VALUES ('metang');
INSERT INTO pokedex (name) VALUES ('metagross');
INSERT INTO pokedex (name) VALUES ('regirock');
INSERT INTO pokedex (name) VALUES ('regice');
INSERT INTO pokedex (name) VALUES ('registeel');
INSERT INTO pokedex (name) VALUES ('latias');
INSERT INTO pokedex (name) VALUES ('latios');
INSERT INTO pokedex (name) VALUES ('kyogre');
INSERT INTO pokedex (name) VALUES ('groudon');
INSERT INTO pokedex (name) VALUES ('rayquaza');
INSERT INTO pokedex (name) VALUES ('jirachi');
INSERT INTO pokedex (name) VALUES ('deoxys');
INSERT INTO pokedex (name) VALUES ('turtwig');
INSERT INTO pokedex (name) VALUES ('grotle');
INSERT INTO pokedex (name) VALUES ('torterra');
INSERT INTO pokedex (name) VALUES ('chimchar');
INSERT INTO pokedex (name) VALUES ('monferno');
INSERT INTO pokedex (name) VALUES ('infernape');
INSERT INTO pokedex (name) VALUES ('piplup');
INSERT INTO pokedex (name) VALUES ('prinplup');
INSERT INTO pokedex (name) VALUES ('empoleon');
INSERT INTO pokedex (name) VALUES ('starly');
INSERT INTO pokedex (name) VALUES ('staravia');
INSERT INTO pokedex (name) VALUES ('staraptor');
INSERT INTO pokedex (name) VALUES ('bidoof');
INSERT INTO pokedex (name) VALUES ('bibarel');
INSERT INTO pokedex (name) VALUES ('kricketot');
INSERT INTO pokedex (name) VALUES ('kricketune');
INSERT INTO pokedex (name) VALUES ('shinx');
INSERT INTO pokedex (name) VALUES ('luxio');
INSERT INTO pokedex (name) VALUES ('luxray');
INSERT INTO pokedex (name) VALUES ('budew');
INSERT INTO pokedex (name) VALUES ('roserade');
INSERT INTO pokedex (name) VALUES ('cranidos');
INSERT INTO pokedex (name) VALUES ('rampardos');
INSERT INTO pokedex (name) VALUES ('shieldon');
INSERT INTO pokedex (name) VALUES ('bastiodon');
INSERT INTO pokedex (name) VALUES ('burmy');
INSERT INTO pokedex (name) VALUES ('wormadam');
INSERT INTO pokedex (name) VALUES ('mothim');
INSERT INTO pokedex (name) VALUES ('combee');
INSERT INTO pokedex (name) VALUES ('vespiquen');
INSERT INTO pokedex (name) VALUES ('pachirisu');
INSERT INTO pokedex (name) VALUES ('buizel');
INSERT INTO pokedex (name) VALUES ('floatzel');
INSERT INTO pokedex (name) VALUES ('cherubi');
INSERT INTO pokedex (name) VALUES ('cherrim');
INSERT INTO pokedex (name) VALUES ('shellos');
INSERT INTO pokedex (name) VALUES ('gastrodon');
INSERT INTO pokedex (name) VALUES ('ambipom');
INSERT INTO pokedex (name) VALUES ('drifloon');
INSERT INTO pokedex (name) VALUES ('drifblim');
INSERT INTO pokedex (name) VALUES ('buneary');
INSERT INTO pokedex (name) VALUES ('lopunny');
INSERT INTO pokedex (name) VALUES ('mismagius');
INSERT INTO pokedex (name) VALUES ('honchkrow');
INSERT INTO pokedex (name) VALUES ('glameow');
INSERT INTO pokedex (name) VALUES ('purugly');
INSERT INTO pokedex (name) VALUES ('chingling');
INSERT INTO pokedex (name) VALUES ('stunky');
INSERT INTO pokedex (name) VALUES ('skuntank');
INSERT INTO pokedex (name) VALUES ('bronzor');
INSERT INTO pokedex (name) VALUES ('bronzong');
INSERT INTO pokedex (name) VALUES ('bonsly');
INSERT INTO pokedex (name) VALUES ('mime jr.');
INSERT INTO pokedex (name) VALUES ('happiny');
INSERT INTO pokedex (name) VALUES ('chatot');
INSERT INTO pokedex (name) VALUES ('spiritomb');
INSERT INTO pokedex (name) VALUES ('gible');
INSERT INTO pokedex (name) VALUES ('gabite');
INSERT INTO pokedex (name) VALUES ('garchomp');
INSERT INTO pokedex (name) VALUES ('munchlax');
INSERT INTO pokedex (name) VALUES ('riolu');
INSERT INTO pokedex (name) VALUES ('lucario');
INSERT INTO pokedex (name) VALUES ('hippopotas');
INSERT INTO pokedex (name) VALUES ('hippowdon');
INSERT INTO pokedex (name) VALUES ('skorupi');
INSERT INTO pokedex (name) VALUES ('drapion');
INSERT INTO pokedex (name) VALUES ('croagunk');
INSERT INTO pokedex (name) VALUES ('toxicroak');
INSERT INTO pokedex (name) VALUES ('carnivine');
INSERT INTO pokedex (name) VALUES ('finneon');
INSERT INTO pokedex (name) VALUES ('lumineon');
INSERT INTO pokedex (name) VALUES ('mantyke');
INSERT INTO pokedex (name) VALUES ('snover');
INSERT INTO pokedex (name) VALUES ('abomasnow');
INSERT INTO pokedex (name) VALUES ('weavile');
INSERT INTO pokedex (name) VALUES ('magnezone');
INSERT INTO pokedex (name) VALUES ('lickilicky');
INSERT INTO pokedex (name) VALUES ('rhyperior');
INSERT INTO pokedex (name) VALUES ('tangrowth');
INSERT INTO pokedex (name) VALUES ('electivire');
INSERT INTO pokedex (name) VALUES ('magmortar');
INSERT INTO pokedex (name) VALUES ('togekiss');
INSERT INTO pokedex (name) VALUES ('yanmega');
INSERT INTO pokedex (name) VALUES ('leafeon');
INSERT INTO pokedex (name) VALUES ('glaceon');
INSERT INTO pokedex (name) VALUES ('gliscor');
INSERT INTO pokedex (name) VALUES ('mamoswine');
INSERT INTO pokedex (name) VALUES ('porygon-z');
INSERT INTO pokedex (name) VALUES ('gallade');
INSERT INTO pokedex (name) VALUES ('probopass');
INSERT INTO pokedex (name) VALUES ('dusknoir');
INSERT INTO pokedex (name) VALUES ('froslass');
INSERT INTO pokedex (name) VALUES ('rotom');
INSERT INTO pokedex (name) VALUES ('rotom');
INSERT INTO pokedex (name) VALUES ('uxie');
INSERT INTO pokedex (name) VALUES ('mesprit');
INSERT INTO pokedex (name) VALUES ('azelf');
INSERT INTO pokedex (name) VALUES ('dialga');
INSERT INTO pokedex (name) VALUES ('palkia');
INSERT INTO pokedex (name) VALUES ('heatran');
INSERT INTO pokedex (name) VALUES ('regigigas');
INSERT INTO pokedex (name) VALUES ('giratina');
INSERT INTO pokedex (name) VALUES ('cresselia');
INSERT INTO pokedex (name) VALUES ('phione');
INSERT INTO pokedex (name) VALUES ('manaphy');
INSERT INTO pokedex (name) VALUES ('darkrai');
INSERT INTO pokedex (name) VALUES ('shaymin');
INSERT INTO pokedex (name) VALUES ('arceus');
INSERT INTO pokedex (name) VALUES ('victini');
INSERT INTO pokedex (name) VALUES ('snivy');
INSERT INTO pokedex (name) VALUES ('servine');
INSERT INTO pokedex (name) VALUES ('serperior');
INSERT INTO pokedex (name) VALUES ('tepig');
INSERT INTO pokedex (name) VALUES ('pignite');
INSERT INTO pokedex (name) VALUES ('emboar');
INSERT INTO pokedex (name) VALUES ('oshawott');
INSERT INTO pokedex (name) VALUES ('dewott');
INSERT INTO pokedex (name) VALUES ('samurott');
INSERT INTO pokedex (name) VALUES ('patrat');
INSERT INTO pokedex (name) VALUES ('watchog');
INSERT INTO pokedex (name) VALUES ('lillipup');
INSERT INTO pokedex (name) VALUES ('herdier');
INSERT INTO pokedex (name) VALUES ('stoutland');
INSERT INTO pokedex (name) VALUES ('purrloin');
INSERT INTO pokedex (name) VALUES ('liepard');
INSERT INTO pokedex (name) VALUES ('pansage');
INSERT INTO pokedex (name) VALUES ('simisage');
INSERT INTO pokedex (name) VALUES ('pansear');
INSERT INTO pokedex (name) VALUES ('simisear');
INSERT INTO pokedex (name) VALUES ('panpour');
INSERT INTO pokedex (name) VALUES ('simipour');
INSERT INTO pokedex (name) VALUES ('munna');
INSERT INTO pokedex (name) VALUES ('musharna');
INSERT INTO pokedex (name) VALUES ('pidove');
INSERT INTO pokedex (name) VALUES ('tranquill');
INSERT INTO pokedex (name) VALUES ('unfezant');
INSERT INTO pokedex (name) VALUES ('blitzle');
INSERT INTO pokedex (name) VALUES ('zebstrika');
INSERT INTO pokedex (name) VALUES ('roggenrola');
INSERT INTO pokedex (name) VALUES ('boldore');
INSERT INTO pokedex (name) VALUES ('gigalith');
INSERT INTO pokedex (name) VALUES ('woobat');
INSERT INTO pokedex (name) VALUES ('swoobat');
INSERT INTO pokedex (name) VALUES ('drilbur');
INSERT INTO pokedex (name) VALUES ('excadrill');
INSERT INTO pokedex (name) VALUES ('audino');
INSERT INTO pokedex (name) VALUES ('timburr');
INSERT INTO pokedex (name) VALUES ('gurdurr');
INSERT INTO pokedex (name) VALUES ('conkeldurr');
INSERT INTO pokedex (name) VALUES ('tympole');
INSERT INTO pokedex (name) VALUES ('palpitoad');
INSERT INTO pokedex (name) VALUES ('seismitoad');
INSERT INTO pokedex (name) VALUES ('throh');
INSERT INTO pokedex (name) VALUES ('sawk');
INSERT INTO pokedex (name) VALUES ('sewaddle');
INSERT INTO pokedex (name) VALUES ('swadloon');
INSERT INTO pokedex (name) VALUES ('leavanny');
INSERT INTO pokedex (name) VALUES ('venipede');
INSERT INTO pokedex (name) VALUES ('whirlipede');
INSERT INTO pokedex (name) VALUES ('scolipede');
INSERT INTO pokedex (name) VALUES ('cottonee');
INSERT INTO pokedex (name) VALUES ('whimsicott');
INSERT INTO pokedex (name) VALUES ('petilil');
INSERT INTO pokedex (name) VALUES ('lilligant');
INSERT INTO pokedex (name) VALUES ('basculin');
INSERT INTO pokedex (name) VALUES ('sandile');
INSERT INTO pokedex (name) VALUES ('krokorok');
INSERT INTO pokedex (name) VALUES ('krookodile');
INSERT INTO pokedex (name) VALUES ('darumaka');
INSERT INTO pokedex (name) VALUES ('darmanitan');
INSERT INTO pokedex (name) VALUES ('maractus');
INSERT INTO pokedex (name) VALUES ('dwebble');
INSERT INTO pokedex (name) VALUES ('crustle');
INSERT INTO pokedex (name) VALUES ('scraggy');
INSERT INTO pokedex (name) VALUES ('scrafty');
INSERT INTO pokedex (name) VALUES ('sigilyph');
INSERT INTO pokedex (name) VALUES ('yamask');
INSERT INTO pokedex (name) VALUES ('cofagrigus');
INSERT INTO pokedex (name) VALUES ('tirtouga');
INSERT INTO pokedex (name) VALUES ('carracosta');
INSERT INTO pokedex (name) VALUES ('archen');
INSERT INTO pokedex (name) VALUES ('archeops');
INSERT INTO pokedex (name) VALUES ('trubbish');
INSERT INTO pokedex (name) VALUES ('garbodor');
INSERT INTO pokedex (name) VALUES ('zorua');
INSERT INTO pokedex (name) VALUES ('zoroark');
INSERT INTO pokedex (name) VALUES ('minccino');
INSERT INTO pokedex (name) VALUES ('cinccino');
INSERT INTO pokedex (name) VALUES ('gothita');
INSERT INTO pokedex (name) VALUES ('gothorita');
INSERT INTO pokedex (name) VALUES ('gothitelle');
INSERT INTO pokedex (name) VALUES ('solosis');
INSERT INTO pokedex (name) VALUES ('duosion');
INSERT INTO pokedex (name) VALUES ('reuniclus');
INSERT INTO pokedex (name) VALUES ('ducklett');
INSERT INTO pokedex (name) VALUES ('swanna');
INSERT INTO pokedex (name) VALUES ('vanillite');
INSERT INTO pokedex (name) VALUES ('vanillish');
INSERT INTO pokedex (name) VALUES ('vanilluxe');
INSERT INTO pokedex (name) VALUES ('deerling');
INSERT INTO pokedex (name) VALUES ('sawsbuck');
INSERT INTO pokedex (name) VALUES ('emolga');
INSERT INTO pokedex (name) VALUES ('karrablast');
INSERT INTO pokedex (name) VALUES ('escavalier');
INSERT INTO pokedex (name) VALUES ('foongus');
INSERT INTO pokedex (name) VALUES ('amoonguss');
INSERT INTO pokedex (name) VALUES ('frillish');
INSERT INTO pokedex (name) VALUES ('jellicent');
INSERT INTO pokedex (name) VALUES ('alomomola');
INSERT INTO pokedex (name) VALUES ('joltik');
INSERT INTO pokedex (name) VALUES ('galvantula');
INSERT INTO pokedex (name) VALUES ('ferroseed');
INSERT INTO pokedex (name) VALUES ('ferrothorn');
INSERT INTO pokedex (name) VALUES ('klink');
INSERT INTO pokedex (name) VALUES ('klang');
INSERT INTO pokedex (name) VALUES ('klinklang');
INSERT INTO pokedex (name) VALUES ('tynamo');
INSERT INTO pokedex (name) VALUES ('eelektrik');
INSERT INTO pokedex (name) VALUES ('eelektross');
INSERT INTO pokedex (name) VALUES ('elgyem');
INSERT INTO pokedex (name) VALUES ('beheeyem');
INSERT INTO pokedex (name) VALUES ('litwick');
INSERT INTO pokedex (name) VALUES ('lampent');
INSERT INTO pokedex (name) VALUES ('chandelure');
INSERT INTO pokedex (name) VALUES ('axew');
INSERT INTO pokedex (name) VALUES ('fraxure');
INSERT INTO pokedex (name) VALUES ('haxorus');
INSERT INTO pokedex (name) VALUES ('cubchoo');
INSERT INTO pokedex (name) VALUES ('beartic');
INSERT INTO pokedex (name) VALUES ('cryogonal');
INSERT INTO pokedex (name) VALUES ('shelmet');
INSERT INTO pokedex (name) VALUES ('accelgor');
INSERT INTO pokedex (name) VALUES ('stunfisk');
INSERT INTO pokedex (name) VALUES ('mienfoo');
INSERT INTO pokedex (name) VALUES ('mienshao');
INSERT INTO pokedex (name) VALUES ('druddigon');
INSERT INTO pokedex (name) VALUES ('golett');
INSERT INTO pokedex (name) VALUES ('golurk');
INSERT INTO pokedex (name) VALUES ('pawniard');
INSERT INTO pokedex (name) VALUES ('bisharp');
INSERT INTO pokedex (name) VALUES ('bouffalant');
INSERT INTO pokedex (name) VALUES ('rufflet');
INSERT INTO pokedex (name) VALUES ('braviary');
INSERT INTO pokedex (name) VALUES ('vullaby');
INSERT INTO pokedex (name) VALUES ('mandibuzz');
INSERT INTO pokedex (name) VALUES ('heatmor');
INSERT INTO pokedex (name) VALUES ('durant');
INSERT INTO pokedex (name) VALUES ('deino');
INSERT INTO pokedex (name) VALUES ('zweilous');
INSERT INTO pokedex (name) VALUES ('hydreigon');
INSERT INTO pokedex (name) VALUES ('larvesta');
INSERT INTO pokedex (name) VALUES ('volcarona');
INSERT INTO pokedex (name) VALUES ('cobalion');
INSERT INTO pokedex (name) VALUES ('terrakion');
INSERT INTO pokedex (name) VALUES ('virizion');
INSERT INTO pokedex (name) VALUES ('tornadus');
INSERT INTO pokedex (name) VALUES ('thundurus');
INSERT INTO pokedex (name) VALUES ('reshiram');
INSERT INTO pokedex (name) VALUES ('zekrom');
INSERT INTO pokedex (name) VALUES ('landorus');
INSERT INTO pokedex (name) VALUES ('kyurem');
INSERT INTO pokedex (name) VALUES ('keldeo');
INSERT INTO pokedex (name) VALUES ('meloetta');
INSERT INTO pokedex (name) VALUES ('genesect');
INSERT INTO pokedex (name) VALUES ('chespin');
INSERT INTO pokedex (name) VALUES ('quilladin');
INSERT INTO pokedex (name) VALUES ('chesnaught');
INSERT INTO pokedex (name) VALUES ('fennekin');
INSERT INTO pokedex (name) VALUES ('braixen');
INSERT INTO pokedex (name) VALUES ('delphox');
INSERT INTO pokedex (name) VALUES ('froakie');
INSERT INTO pokedex (name) VALUES ('frogadier');
INSERT INTO pokedex (name) VALUES ('greninja');
INSERT INTO pokedex (name) VALUES ('bunnelby');
INSERT INTO pokedex (name) VALUES ('diggersby');
INSERT INTO pokedex (name) VALUES ('fletchling');
INSERT INTO pokedex (name) VALUES ('fletchinder');
INSERT INTO pokedex (name) VALUES ('talonflame');
INSERT INTO pokedex (name) VALUES ('scatterbug');
INSERT INTO pokedex (name) VALUES ('spewpa');
INSERT INTO pokedex (name) VALUES ('vivillon');
INSERT INTO pokedex (name) VALUES ('litleo');
INSERT INTO pokedex (name) VALUES ('pyroar');
INSERT INTO pokedex (name) VALUES ('flab??b??');
INSERT INTO pokedex (name) VALUES ('floette');
INSERT INTO pokedex (name) VALUES ('florges');
INSERT INTO pokedex (name) VALUES ('skiddo');
INSERT INTO pokedex (name) VALUES ('gogoat');
INSERT INTO pokedex (name) VALUES ('pancham');
INSERT INTO pokedex (name) VALUES ('pangoro');
INSERT INTO pokedex (name) VALUES ('furfrou');
INSERT INTO pokedex (name) VALUES ('espurr');
INSERT INTO pokedex (name) VALUES ('meowstic-male');
INSERT INTO pokedex (name) VALUES ('meowstic-female');
INSERT INTO pokedex (name) VALUES ('honedge');
INSERT INTO pokedex (name) VALUES ('doublade');
INSERT INTO pokedex (name) VALUES ('aegislash');
INSERT INTO pokedex (name) VALUES ('spritzee');
INSERT INTO pokedex (name) VALUES ('aromatisse');
INSERT INTO pokedex (name) VALUES ('swirlix');
INSERT INTO pokedex (name) VALUES ('slurpuff');
INSERT INTO pokedex (name) VALUES ('inkay');
INSERT INTO pokedex (name) VALUES ('malamar');
INSERT INTO pokedex (name) VALUES ('binacle');
INSERT INTO pokedex (name) VALUES ('barbaracle');
INSERT INTO pokedex (name) VALUES ('skrelp');
INSERT INTO pokedex (name) VALUES ('dragalge');
INSERT INTO pokedex (name) VALUES ('clauncher');
INSERT INTO pokedex (name) VALUES ('clawitzer');
INSERT INTO pokedex (name) VALUES ('helioptile');
INSERT INTO pokedex (name) VALUES ('heliolisk');
INSERT INTO pokedex (name) VALUES ('tyrunt');
INSERT INTO pokedex (name) VALUES ('tyrantrum');
INSERT INTO pokedex (name) VALUES ('amaura');
INSERT INTO pokedex (name) VALUES ('aurorus');
INSERT INTO pokedex (name) VALUES ('sylveon');
INSERT INTO pokedex (name) VALUES ('hawlucha');
INSERT INTO pokedex (name) VALUES ('dedenne');
INSERT INTO pokedex (name) VALUES ('carbink');
INSERT INTO pokedex (name) VALUES ('goomy');
INSERT INTO pokedex (name) VALUES ('sliggoo');
INSERT INTO pokedex (name) VALUES ('goodra');
INSERT INTO pokedex (name) VALUES ('klefki');
INSERT INTO pokedex (name) VALUES ('phantump');
INSERT INTO pokedex (name) VALUES ('trevenant');
INSERT INTO pokedex (name) VALUES ('pumpkaboo');
INSERT INTO pokedex (name) VALUES ('gourgeist');
INSERT INTO pokedex (name) VALUES ('bergmite');
INSERT INTO pokedex (name) VALUES ('avalugg');
INSERT INTO pokedex (name) VALUES ('noibat');
INSERT INTO pokedex (name) VALUES ('noivern');
INSERT INTO pokedex (name) VALUES ('xerneas');
INSERT INTO pokedex (name) VALUES ('yveltal');
INSERT INTO pokedex (name) VALUES ('zygarde');
INSERT INTO pokedex (name) VALUES ('diancie');
INSERT INTO pokedex (name) VALUES ('hoopahoopa');
INSERT INTO pokedex (name) VALUES ('volcanion');

COMMIT TRANSACTION;
