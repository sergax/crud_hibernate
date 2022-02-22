CREATE TABLE IF NOT EXISTS tag (
  tag_id int NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (tag_id)
);

CREATE TABLE IF NOT EXISTS post (
  post_id int NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE,
  content varchar(255) DEFAULT NULL,
  status varchar(255) NOT NULL,
  PRIMARY KEY (post_id)
);

CREATE TABLE IF NOT EXISTS tag_post (
  tag_id int NOT NULL,
  post_id int NOT NULL,
  PRIMARY KEY (tag_id, post_id),
  CONSTRAINT fk_post_tag_post FOREIGN KEY (post_id) REFERENCES post (post_id) ON DELETE CASCADE,
  CONSTRAINT fk_tag_tag_post FOREIGN KEY (tag_id) REFERENCES tag (tag_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS writer (
  writer_id int NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE,
  name varchar(255) DEFAULT NULL,
  post_id int DEFAULT NULL,
  PRIMARY KEY (writer_id),
  CONSTRAINT FK_writer_post FOREIGN KEY (post_id) REFERENCES post (post_id) ON DELETE CASCADE
);