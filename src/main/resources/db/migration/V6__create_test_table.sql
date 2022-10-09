DROP TABLE IF EXISTS suburbs CASCADE;
CREATE TABLE suburbs
(
    ssc_code INTEGER     NOT NULL PRIMARY KEY,
    suburb   VARCHAR(37) NOT NULL,
    postcode INTEGER     NOT NULL,
    state    VARCHAR(3)  NOT NULL
);
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (11344, 'East Albury', 2640, 'NSW');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (12287, 'Lavington', 2641, 'NSW');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (11678, 'Glenroy', 2640, 'NSW');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (10027, 'Albury', 2640, 'NSW');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (12977, 'North Albury', 2640, 'NSW');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (13722, 'Table Top', 2640, 'NSW');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (12029, 'Jincumbilly', 2631, 'NSW');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (11644, 'Glen Allen', 2631, 'NSW');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (11111, 'Creewah', 2631, 'NSW');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (31297, 'Haliday Bay', 4740, 'QLD');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (30127, 'Ball Bay', 4741, 'QLD');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (32825, 'The Leap', 4740, 'QLD');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (31304, 'Hampden', 4741, 'QLD');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (31293, 'Habana', 4740, 'QLD');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (41367, 'Steinfeld', 5356, 'SA');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (40377, 'Dutton', 5356, 'SA');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (40458, 'Frankton', 5374, 'SA');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (40171, 'Brownlow', 5374, 'SA');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (60763, 'Yambacoona', 7256, 'TAS');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (60747, 'Wickham', 7256, 'TAS');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (60378, 'Margate', 7054, 'TAS');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (60051, 'Blackmans Bay', 7052, 'TAS');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (20662, 'Cranbourne West', 3977, 'VIC');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (20659, 'Cranbourne East', 3977, 'VIC');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (21268, 'Junction Village', 3977, 'VIC');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (20305, 'Botanic Ridge', 3977, 'VIC');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (21117, 'Hallam', 3803, 'VIC');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (20865, 'Endeavour Hills', 3802, 'VIC');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (20883, 'Eumemmerring', 3177, 'VIC');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (20777, 'Doveton', 3177, 'VIC');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (21549, 'Lysterfield South', 3156, 'VIC');
INSERT INTO suburbs(ssc_code, suburb, postcode, state)
VALUES (20661, 'Cranbourne South', 3977, 'VIC');