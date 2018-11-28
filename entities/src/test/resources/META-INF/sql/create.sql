CREATE TABLE GAME_GENRE (game_genre_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name VARCHAR(100) NOT NULL)
CREATE TABLE SONG (last_fm_id VARCHAR(100) NOT NULL PRIMARY KEY, name VARCHAR(100) NOT NULL, image VARCHAR(255), description VARCHAR(1000), url VARCHAR(255))
CREATE TABLE ALBUM (last_fm_id VARCHAR(100) NOT NULL PRIMARY KEY, name VARCHAR(100) NOT NULL, image VARCHAR(255), description VARCHAR(1000), url VARCHAR(255))
CREATE TABLE ARTIST (last_fm_id VARCHAR(100) NOT NULL PRIMARY KEY, name VARCHAR(100) NOT NULL, image VARCHAR(255), description VARCHAR(1000), url VARCHAR(255))
CREATE TABLE GAME_GENRE_TO_MUSIC_RELEASE (game_genre_to_music_release_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), game_genre_id INT NOT NULL, FOREIGN KEY (game_genre_id) REFERENCES GAME_GENRE (game_genre_id) ON DELETE CASCADE, song_id VARCHAR(100), FOREIGN KEY (song_id) REFERENCES SONG (last_fm_id) ON DELETE CASCADE, album_id VARCHAR(100), FOREIGN KEY (album_id) REFERENCES ALBUM (last_fm_id) ON DELETE CASCADE, artist_id VARCHAR(100), FOREIGN KEY (artist_id) REFERENCES ARTIST (last_fm_id) ON DELETE CASCADE)
CREATE TABLE STEAM_FM_USER (steam_fm_user_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), login VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, is_admin BOOLEAN, steam_user VARCHAR(100), steam_id VARCHAR(100))