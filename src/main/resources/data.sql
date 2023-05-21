-- INSERT DEVICE BRANDS
INSERT INTO brands (id, name) VALUES (1, 'Samsung');
INSERT INTO brands (id, name) VALUES (2, 'Apple');
INSERT INTO brands (id, name) VALUES (3, 'Motorola');
INSERT INTO brands (id, name) VALUES (4, 'OnePlus');
INSERT INTO brands (id, name) VALUES (5, 'Nokia');

-- INSERT DEVICES
INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (1, 'Samsung Galaxy S9', 1, 'GSM / CDMA / HSPA / EVDO / LTE',
    'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM model only), CDMA 800 / 1900 - USA',
    'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100 - Global, USA, CDMA2000 1xEV-DO - USA',
    '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 32, 38, 39, 40, 41, 66 - Global, 1, 2, 3, 4, 5, 7, 8, 12, 13, 14, 17, 18, 19, 20, 25, 26, 28, 29, 30, 38, 39, 40, 41, 46, 66, 71 - USA');

INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (2, 'Samsung Galaxy S8', 1, 'GSM / HSPA / LTE',
    'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM model only)',
    'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100',
    '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 32, 66, 38, 39, 40, 41');

INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (3, 'Samsung Galaxy S8', 1, 'GSM / HSPA / LTE',
    'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM model only)',
    'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100',
    '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 32, 66, 38, 39, 40, 41');

INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (4, 'Motorola Nexus 6', 3, 'GSM / CDMA / HSPA / LTE',
    'GSM 850 / 900 / 1800 / 1900 - all models, CDMA 800 / 1900 - XT1103',
    'HSDPA 800 / 850 / 900 / 1700 / 1800 / 1900 / 2100 - XT1100, HSDPA 850 / 900 / 1700 / 1900 / 2100 - XT1103, HSDPA 850 / 900 / 1900 / 2100 - Verizon',
    '1, 3, 5, 7, 8, 9, 19, 20, 28, 41 - XT1100, 2, 3, 4, 5, 7, 12, 13, 17, 25, 26, 29, 41 - XT1103, 4, 13 - Verizon');

INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (5, 'Oneplus 9', 4, 'GSM / CDMA / HSPA / LTE / 5G',
    'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2, CDMA 800 / 1900',
    'HSDPA 800 / 850 / 900 / 1700(AWS) / 1800 / 1900 / 2100',
    '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 32, 38, 39, 40, 41, 66 - EU, 1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 30, 32, 38, 39, 40, 41, 46, 48, 66, 71 - NA, 1, 2, 3, 4, 5, 7, 8, 12, 17, 18, 19, 20, 26, 34, 38, 39, 40, 41, 46 - IN, 1, 2, 3, 4, 5, 7, 8, 12, 17, 18, 19, 20, 26, 34, 38, 39, 40, 41 - CN');


INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (6, 'Apple iPhone 13', 2,
    'GSM / CDMA / HSPA / EVDO / LTE / 5G',
     'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM)',
    'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100', '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 30, 32, 34, 38, 39, 40, 41, 42, 46, 48, 66 - A2633, A2634, A2635');

INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (7, 'Apple iPhone 12', 2, 'GSM / CDMA / HSPA / EVDO / LTE / 5G',
    'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM) - for China, CDMA 800 / 1900',
    'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100, CDMA2000 1xEV-DO',
    '1, 2, 3, 4, 5, 7, 8, 12, 13, 14, 17, 18, 19, 20, 25, 26, 28, 29, 30, 32, 34, 38, 39, 40, 41, 42, 46, 48, 66, 71 - A2172');

INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (8, 'Apple iPhone 11', 2, 'GSM / CDMA / HSPA / EVDO / LTE',
    'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM) - for China, CDMA 800 / 1900',
    'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100, CDMA2000 1xEV-DO',
    '1, 2, 3, 4, 5, 7, 8, 11, 12, 13, 17, 18, 19, 20, 21, 25, 26, 28, 29, 30, 32, 34, 38, 39, 40, 41, 42, 46, 48, 66 - A2221');

INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (9, 'iPhone X', 2, '	GSM / HSPA / LTE',
    'GSM 850 / 900 / 1800 / 1900',
    'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100',
    '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 29, 30, 34, 38, 39, 40, 41, 66');

INSERT INTO devices (id, name, brand_id, technology, twoGBands, threeGBands, fourGBands)
    VALUES (10, 'Nokia 3310', 5, 'GSM',
    'GSM 900 / 1800 - SIM 1 & SIM 2 (dual-SIM model only)',
    '', '');

