CREATE TABLE FEED_METADATA(
    tidspunkt_siste_lesing TIMESTAMP(6)
);

INSERT INTO FEED_METADATA (tidspunkt_siste_lesing) VALUES (TO_TIMESTAMP('01/01/2000', 'DD/MM/YYYY'));