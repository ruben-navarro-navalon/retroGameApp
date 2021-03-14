DROP SCHEMA IF EXISTS usercatalog;
CREATE SCHEMA usercatalog;
USE usercatalog;

CREATE TABLE game (
	id BIGINT NOT NULL AUTO_INCREMENT,
    api_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE user (
	id BIGINT NOT NULL,
    username VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE collection (
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE game_in_collection (
	collection_id BIGINT,
    game_id BIGINT,
    FOREIGN KEY (collection_id) REFERENCES collection(id),
    FOREIGN KEY (game_id) REFERENCES game(id)
);

CREATE TABLE wanted (
	id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE game_in_wanted (
	wanted_id BIGINT,
    game_id BIGINT,
    FOREIGN KEY (wanted_id) REFERENCES wanted(id),
    FOREIGN KEY (game_id) REFERENCES game(id)
);

CREATE TABLE sell (
	id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE game_in_sell (
	sell_id BIGINT,
    game_id BIGINT,
    FOREIGN KEY (sell_id) REFERENCES sell(id),
    FOREIGN KEY (game_id) REFERENCES game(id)
);

CREATE TABLE platform_data (
	id INT NOT NULL AUTO_INCREMENT,
	api_id INT,
    name VARCHAR(255),
    short_name VARCHAR(255),
    games_count BIGINT,
    description VARCHAR(2047),
    image VARCHAR(255),
    released DATE,
    portable BOOLEAN,
    PRIMARY KEY (id)
);
INSERT INTO platform_data (api_id, name, short_name, games_count, description, image, released, portable) VALUES
	(49, "Nintendo Entertainment System", "nes", 908, "Nintendo Entertainment System (usually referred to as NES) is Nintendo’s first major home video game console. It was initially released in 1983 in Japan as Famicom. Even though both consoles shared similar hardware, the games were region-blocked and had different game cartridge design. The consoles’ design was also drastically different with Famicom having two controllers wired inside the system. NES was the first successful video game console in North America since the video game crash of 1983 and was one of the main reasons of the industry revitalization. Throughout its lifespan, many classic game series originated on NES: Super Mario, Metroid, Castlevania, Final Fantasy, The Legend of Zelda, Dragon Quest, Mega Man, and Ghosts'n'Goblins among others. The NES gamepad consisted of four-directional D-pad, Start, Select, A and B buttons. It was the first video game console controller that included a D-pad which became the industry standard and was featured in almost every game console since. The console had a considerably long lifespan and was only discontinued in 1995 in North America and 2003 in Japan.", "nes.png", "1983-07-15", false),
	(79, "Super Nintendo Entertainment System", "snes", 838, "Super Nintendo Entertainment System or SNES (also known as Super Famicom in Japan) is Nintendo’s second major video game home console and a successor to the company's NES. The console competed with Sega Genesis, NEC’s TurboGrafx-16, SNK’s Neo Geo and sold more than 40 million units worldwide becoming the best-selling console of the 16-bit era. The SNES controller was notably different from its predecessor and included more minimalistic and ergonomic design as well as four more buttons. The system launched with 5 games in North America: Pilotwings, F-Zero, SimCity, Gradius III and Super Mario World later of which became the best-selling title for the system. The system did not have any notable revisions during its lifespan. However numerous games used enhancement chips that increased the performance of the console. The most notable of them is SuperFX chip that was used in such games as Star Fox and Dirt Racer. Many SNES games gained the cult following. They include Final Fantasy VII, Earthbound, Super Metroid The Legend of Zelda: A link to the Past among others.", "snes.png", "1990-11-21", false),
	(83, "Nintendo 64", "n64", 356, "Nintendo 64 is the third major video game home console made by Nintendo. It was released in 1996 and competed with Sony PlayStation, Sega Saturn and later Dreamcast. Unlike its competitors, Nintendo 64 used game cartridges as the primary storage medium instead of optical disks. The console introduced a unique gamepad with a D-pad, six face buttons, three shoulder buttons, a trigger and an analog stick, which later became a standard in the industry and has been used in every major video game console since. The game cartridges on Nintendo 64 contained significantly less storage than CDs on other consoles. That resulted in N64 games having much less cinematic cutscenes, music tracks, and other multimedia data. Compared to the SNES, Nintendo 64 gained far less support from third-party developers. The most notable third-party developer for the system was Rare which made some of the most well-received games for the consoles such as Perfect Dark, GoldenEye 007 and Banjo-Kazooie. The most popular first-party games on the system include Super Mario 64 The Legend of Zelda: Ocarina of Time, Mario Kart 64 and Mario Party 2.", "n64.png", "1996-06-23", false),
    (105, "GameCube", "gcn", 637, "GameCube is Nintendo’s fourth major home video game console and a successor to the Nintendo 64. It was the first Nintendo console to use optical disks as the primary storage medium. Unlike its competitors, Sony PlayStation 2 and Microsoft Xbox, it used mini-DVD format instead of regular DVDs which resulted in console lacking DVD-player feature. The GameCube controller was drastically different from previous Nintendo controllers and used an asymmetrical design of its face buttons. The wireless version of the controller called WaveBird was released in 2002. The GameCube became Nintendo’s worst selling home console at the time with about 22 million copies sold worldwide. However, it was praised for its game library with many games gaining widespread acclaim from both critics and players. Some of the most notable of them include: Paper Mario: A Thousand-Year Door, Eternal Darkness: Sanity's Requiem, Metroid Prime, The Legend of Zelda: The Wind Waker, Pikmin, and Super Monkey Ball among others. The best-selling game on the console is Super Smash Bros. Melee. The game sold 7 million copies and had an active tournament community up to this day.", "gcn.png", "2001-09-14", false),
    (11, "Wii", "wii", 2353, "Wii is Nintendo’s fifth major home video game console. It became a huge financial success for Nintendo with 101 million consoles sold worldwide. Unlike its competitors, Xbox 360 and PlayStation 3, Wii could not display HD graphics and targeted broader demographic of casual players. The main feature of the console was its motion-sensing central controller – Wii Remote that also had the functionality of a pointing device. Most of its lifespan, the console packages included Wii Sports game, which also was one of the Wii’s selling point. The game was marketed as easy to learn entertainment for the whole family. Most of Nintendo’s main franchises got their installments on the Wii most notable of which are Super Mario Galaxy, Metroid Prime 3, The Legend of Zelda: Skyward Sword, Donkey Kong Country Returns, Super Smash Bros. Brawl, and Mario Kart Wii. The first model of Wii also was backward compatible with the majority of GameCube games and had GameCube Controller ports.", "wii.png", "2006-11-19", false),

	(26, "Game Boy", "gb", 585, "Game Boy is the first major portable video game console made by Nintendo. It sold more than 118 million units worldwide (including different revisions and Game Boy Color) and became the first successful portable game console in the world. The console featured a monochrome display and used a control scheme similar to that of NES with a D-pad, A, B, Select and Start buttons. The Game Link cable allowed players to connect two Game Boys to play multiplayer or transfer data in certain games. The system launched in 1989 in North America with Tetris a packed-in game. The game became a huge hit and a reliable promotional tool for the console. The Game Boy was an origin console for Nintendo’s hit series Kirby and Pokémon. Aside from them, various game series that became popular on other console got their Game Boy releases. They include Metroid, Super Mario, The Legend of Zelda, Contra and Donkey Kong among others. The system got two main revision throughout its lifespan: a more lightweight Game Boy Pocket in 1996, that also required two batteries instead of four, and Game Boy Light in 1998 that had backlit display and was exclusive for Japan.", "gb.png", "1989-04-21", true),
	(43, "Game Boy Color", "gbc", 385, "Game Boy Color is the second major portable video game console made by Nintendo. The console was a successor to the original Game Boy and shared many similarities with it. Game Boy Color was backward compatible with all original Game Boy titles. The main innovation in the system, compared to its predecessor was its color screen that could display up to 56 different colors. The control elements were the same as in original Game Boy: a D-pad, A, B, Select and Start buttons. The games developed specifically for Game Boy Color could not be played on Game Boy, while the Game Boy titles when played on Game Boy Color were colored using built-in color palettes. The player could choose from 12 different pallets. Additionally, certain Game Boy games had their specific pallets. The most successful exclusive games on the system were titles from Pokémon, The Legend of Zelda and Super Mario series with Pokémon Gold and Silver selling more than 23 million copies worldwide.", "gbc.png", "1998-10-22", true),
	(24, "Game Boy Advance", "gba", 920, "Game Boy Advance is Nintendo’s third major portable game console and a successor to the company’s famous Game Boy and Game Boy Color consoles. It has much more powerful hardware than its predecessors, different design and two more buttons on the side of the device. The GBA hardware is comparable to that of SNES and majority of the games on the system featured 2D sprite-based graphics reminiscent to that of 16-bit consoles. The console had two revisions: Game Boy Advance SP in 2003 and Game Boy Micro in 2005. SP is a more lightweight version of the system that had improved backlit display and clamshell design. The Game Boy micro is a much smaller version of GBA with the size comparable to that of the NES controller. All of the Game Boy Advance revision except for Game Boy Mini were backward compatible with most of the Game Boy and Game Boy Color games. The most successful games on the system were the titles in the Pokémon series. Some other well-known game series that had successful titles on the system include Metroid, Kingdom Hearts, Super Mario, PAC-Man and Kirby among others.", "gba.png", "2001-03-21", true),

    (74, "Sega Master System", "sms", 203, "SEGA Master System (also known as Mark III in Japan) is the second home video game console made by Sega. In North America, it was released a year after NES and directly competed with it. However, the sales of the system were significantly lower than of NES. It was not successful in Japan and North America but gained some popularity in PAL regions. In Brazil, Master System was a huge success and has not been discontinued as for 2018 making it the console with the most significant lifespan. \nThe Master System gamepad included a D-pad and two face buttons. The console used two main formats of data storage: game cartridges and more affordable Sega Cards (or Mark III My Card in Japan). Additionally, the system could play most of the games from previous Sega console – SG-1000. The console’s success in PAL regions led to Sega porting some of their games from their next console, Mega Drive (Genesis in North America) onto the Master System.", "sms.png", "1985-10-20", false),
    (167, "Sega Mega Drive", "smd", 812, "Sega Genesis (known as Sega Mega Drive outside North America) is Sega’s third home video game console, a successor to Sega Master System. The console was a success for Sega and became the company’s best-selling console of all-time with more than 30 million units sold worldwide. It competed with NES, TurboGrafx-16 and, later, SNES. The console is notable for being the origin platform for Sega’s flagship franchise – Sonic The Hedgehog. The original trilogy of games was actively used in the Genesis promotion and played an essential role in the console’s success. Throughout its lifespan, Genesis received several revisions. The most notable of them is 1993 Genesis (without Sega prefix) or Mega Drive 2 outside North America. This model brought the 6-button controller instead of the original 3-button controller. Throughout its lifespan, two significant add-ons were released for the system: Sega CD and Sega 32X. The first one let Genesis read data from CD and the second one drastically increased the hardware power of the console.", "smd.png", "1988-10-29", false),
    (107, "Sega Saturn", "ss", 298, "SEGA Saturn is the fourth home video game console made by Sega and a successor to Sega Genesis/Mega Drive. Initially, the system’s focus was on recreating the arcade experience at home. This decision was partially made because of the success of Sega’s arcade division. Saturn got several well-received ports of such arcade hits as Virtua Fighter and Daytona USA. The Saturn’s library is also notable for several unique critically acclaimed projects such as Shining Force 3, Nights into Dreams, Guardian Heroes and Panzer Dragoon series among others. The gamepad of Sega Saturn is very reminiscent to that of Genesis but has slightly different design and two additional shoulder buttons. The console was a commercial failure for the company due to its high price tag, lack of third-party support, technical specifications that made programming for the system intense and challenging competition from Sony PlayStation and Nintendo 64.", "ss.png", "1994-11-21", false),
    (106, "Sega Dreamcast", "sd", 343, "Dreamcast is the fifth and the last home video game console made by Sega. It was released only four years after the launch of the previous Sega console, Saturn. The system was more affordable than its predecessor, had a unique gamepad that drastically differed from other consoles’ controllers and offered numerous features that were considered to be ahead of its time. It became the first console with a pre-build modem and supported online multiplayer in certain titles such as Phantasy Star Online. The Dreamcast controller included an analog stick, a D-pad, four face buttons, and two analog triggers. Its most notable feature was the Visual Memory Unit that could be inserted in the gamepad and served as a small additional monochrome screen and a memory card. Additionally, the VMU had its control elements and could pay some mini-games depending on which game files it had. The game library of Dreamcast is frequently praised for numerous critically-acclaimed installments in Sega’s classic game series, as well as its unique and innovative titles from both Sega and third-party developers.", "sd.png", "1998-11-27", false),

    (77, "Sega Game Gear", "sgg", 164, "Game Gear is the first portable video game console made by Sega. It competed with Atari Lynx, Nintendo Game Boy, and NEC TurboExpress and became the second best-selling portable console of its era. However, it still was not a successful product with only 10 million units sold. The console was much more technically advanced, then its main competitor, Game Boy, but was criticized for its short battery life, large size and lack of Sega’s support, as well as third-party developers support. The system’s hardware was very reminiscent of the Sega Master System and using Master Gear Converter could play games from that console. It used a similar control layout with the addition of separate START button. The game library included various Master System ports and exclusive titles. Some of the most acclaimed Game Gear games include Land of Illusion Starring Mickey Mouse, Gunstar Heroes, Shinobi and Sonic the Hedgehog series ports.", "sgg.png", "1990-10-6", true)
;

INSERT INTO user (id, username) VALUES
	(1, "sarahK"),
    (2, "pepito"),
    (3, "epicRetro")
;
INSERT INTO collection (id, user_id) VALUES
	(1,1),
    (2,2),
    (3,3)
;
INSERT INTO wanted (id, user_id) VALUES
	(1,1),
    (2,2),
    (3,3)
;
INSERT INTO sell (id, user_id) VALUES
	(1,1),
    (2,2),
    (3,3)
;

INSERT INTO game (id, api_id) VALUES
	(1, 54036),
	(2, 442851),
	(3, 53900),
	(4, 57758),
	(5, 53432),
	(6, 27571),
	(7, 27022),
	(8, 55194),
	(9, 55323),
	(10, 56941),
    (11, 54036),
	(12, 53900),
	(13, 53432),
	(14, 57758),
	(15, 53274),
	(16, 53207),
	(17, 14975),
	(18, 55323),
    (19, 57758),
	(20, 58346),
	(21, 53432),
	(22, 32359),
	(23, 27571),
	(24, 25097),
	(25, 54667),
	(26, 53274),
	(27, 53207)
;
INSERT INTO game_in_collection(collection_id, game_id) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 7),
	(1, 9),
	(1, 10),
	(2, 12),
	(2, 13),
	(2, 14),
	(2, 16),
	(2, 17),
	(3, 21),
	(3, 22),
	(3, 23)
;
INSERT INTO game_in_wanted(wanted_id, game_id) VALUES
	(1, 5),
	(1, 6),
	(1, 8),
	(2, 11),
	(2, 15),
	(2, 18),
	(3, 19),
	(3, 20),
	(3, 24),
	(3, 25),
	(3, 26),
	(3, 27)
;
INSERT INTO game_in_sell(sell_id, game_id) VALUES
	(1, 9),
	(1, 10),
	(1, 4),
	(2, 13),
	(2, 14),
	(2, 12),
	(3, 23)
;