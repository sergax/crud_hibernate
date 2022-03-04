CREATE TABLE tag (
  tag_id serial not null primary key,
  name varchar(255) DEFAULT NULL
);

CREATE TABLE post (
  post_id serial not null primary key,
  content varchar(255) DEFAULT NULL,
  status varchar(255) NOT NULL
);

CREATE TABLE tag_post (
  tag_id serial not null primary key,
  post_id serial not null,
  CONSTRAINT fk_post_tag_post FOREIGN KEY (post_id) REFERENCES post (post_id),
  CONSTRAINT fk_tag_tag_post FOREIGN KEY (tag_id) REFERENCES tag (tag_id)
);

CREATE TABLE writer (
  id serial not null primary key,
  name varchar(255) DEFAULT NULL,
  post_writer_id serial not null,
  CONSTRAINT fk_post_writer FOREIGN KEY (post_writer_id) REFERENCES post(post_id)
);
