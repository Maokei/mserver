CREATE TABLE users (
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    roles VARCHAR(255)[],
    enabled BOOLEAN NOT NULL
);

CREATE TABLE userRoles (
    user_id UUID NOT NULL,
    role_ud UUID NOT NULL
);

CREATE TABLE roles (
    role_id INT NOT NULL,
    role VARCHAR(15) NOT NULL,
    PRIMARY KEY(role_id)
);

CREATE TABLE comments (
    comment_id UUID NOT NULL,
    user_id UUID REFERENCES users(user_id) ON DELETE CASCADE NOT NULL,
    comment TEXT NOT NULL,
    created TIMESTAMP DEFAULT now(),
    updated TIMESTAMP DEFAULT now(),
    PRIMARY KEY(comment_id)
);

CREATE TABLE artists (
    artist_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    bio TEXT,
    profile_image_url TEXT,
    is_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE Table albums (
    album_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    release_date DATE,
    cover_art_url TEXT,
    label VARCHAR(100),
    artist_id UUID REFERENCES artists(artist_id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE medias (
    media_id UUID NOT NULL,
    created TIMESTAMP DEFAULT now(),
    updated TIMESTAMP DEFAULT now(),
    foreign_id VARCHAR(40) NOT NULL,
    title VARCHAR(255) NOT NULL,
    views BIGINT DEFAULT 0,
    url TEXT,
    user_id UUID NOT NULL,
    type TEXT NOT NULL,
    metadata JSON,
    filename VARCHAR(60) NOT NULL,
    size INT NOT NULL,
    hash BYTEA NULL,
    content bytea NULL,
    location TEXT NULL,
    PRIMARY KEY (media_id)
);

CREATE TABLE playlists (
    playlist_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    owner_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    is_public BOOLEAN DEFAULT FALSE,
    is_collaborative BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_library (
    user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
    media_id UUID REFERENCES medias(media_id) ON DELETE CASCADE,
    added_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, media_id)
);

CREATE TABLE listen_history (
    history_id BIGSERIAL PRIMARY KEY,
    user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
    media_id UUID REFERENCES medias(media_id) ON DELETE SET NULL,
    consumed_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    device_type VARCHAR(50), -- 'mobile', 'web', 'smart_speaker'
    completion_percent INT CHECK (completion_percent BETWEEN 0 AND 100)
);

-- Indexes
CREATE INDEX idx_media_title ON medias(title);
CREATE INDEX idx_artist_name ON artists(name);
CREATE INDEX idx_user_history ON listen_history(user_id, consumed_at DESC);