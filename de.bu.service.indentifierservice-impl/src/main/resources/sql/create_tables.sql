CREATE table identifier_block(
	id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	owner VARCHAR(50) NOT NULL, 
	blockstart BIGINT NOT NULL,
	blockend BIGINT NOT NULL,
	PRIMARY KEY (id)
);