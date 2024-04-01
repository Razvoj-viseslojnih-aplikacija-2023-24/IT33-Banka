
INSERT INTO "korisnik"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('KORISNIK_SEQ'), 'Neda', 'Arsenijevic', '1208002726819');
INSERT INTO "korisnik"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('KORISNIK_SEQ'), 'Natasa', 'Martic', '2205002678445');
INSERT INTO "korisnik"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('KORISNIK_SEQ'), 'Nadja', 'Mileusnic', '3009002666489');
INSERT INTO "korisnik"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('KORISNIK_SEQ'), 'Nemanja', 'Lakicevic', '1503001721889');
INSERT INTO "korisnik"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('KORISNIK_SEQ'), 'Nevena', 'Saponja', '1006002099323');
INSERT INTO "korisnik"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('KORISNIK_SEQ'), 'Stefan', 'Arsenijevic', '1810991721123');
INSERT INTO "korisnik"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('KORISNIK_SEQ'), 'Aleksandra', 'Dobricic', '0306005726545');
INSERT INTO "korisnik"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('KORISNIK_SEQ'), 'Stefan', 'Vidic', '2308002890013');
INSERT INTO "korisnik"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('KORISNIK_SEQ'), 'Aleksandar', 'Lucic', '9012345678901');


INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('BANKA_SEQ'), 'HalkBank', '+38111223319', 951753824);
INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('BANKA_SEQ'), 'UniCredit Bank Srbija', '+38111223344', 123456789);
INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('BANKA_SEQ'), 'Raiffeisen Bank Srbija', '+38111223355', 987654321);
INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('BANKA_SEQ'), 'OTP Banka Srbija', '+38111223388', 369258147);
INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('BANKA_SEQ'), 'Intesa Sanpaolo Banka Srbija', '+38111223310', 147369852);
INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('BANKA_SEQ'), 'AIK Banka', '+38111223312', 321654987);
INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('BANKA_SEQ'), 'Credit Agricole Bank Srbija', '+38111223318', 147258369);


INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES(nextval('FILIJALA_SEQ'), 'Trg Slobode 5, Novi Sad', 7, true, 5);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES(nextval('FILIJALA_SEQ'), 'Bulevar Mihajla Pupina 25, Novi Sad', 6, true, 2);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES(nextval('FILIJALA_SEQ'), 'Miletićeva 16, Novi Sad', 4, false, 6);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES(nextval('FILIJALA_SEQ'), 'Braće Ribnikar 12, Novi Sad', 3, true, 7);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES(nextval('FILIJALA_SEQ'), 'Ilije Ognjanovića 33, Novi Sad', 8, false, 1);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES(nextval('FILIJALA_SEQ'), 'Futoški put 74, Novi Sad', 4, true, 4);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES(nextval('FILIJALA_SEQ'), 'Jevrejska 17, Novi Sad', 5, false, 3);


INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Elektronsko bankarstvo', 'Podnošenje zahteva, kao i aktivacija naloga za korišćenje mobilnog bankarstva', '2023-12-15', 5.0, 1, 7);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Izrada kartice', 'Izrada i personalizacija kartice', '2023-12-15', 10.0, 2, 1);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Prelazak u veci paket', 'Korisnik sa trenutnog paketa želi da pređe na neki paket na višem nivou. Prilikom prelaska na veći paket, korisniku su omogućeni razni benefiti, ali uz to je i povećana mesečna pretplata za 10%.', '2023-12-15', 8.0, 3, 2);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Isplata gotovine', 'Isplata gotovine korisniku na šalteru banke uz priložen lični dokument.', '2023-12-15', 3.5, 4, 6);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Podnošenje zahteva za kredit', 'Korisnik podnosi zahtev za kredit. Banka isti taj zahtev može ili da odobri ili odbije.', '2023-12-15', 12.0, 5, 4);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Tekući račun', 'Otvaranje i vođenje tekućeg računa', '2023-12-15', 6.5, 6, 3);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Štednja', 'Štednja u domaćoj valuti', '2023-12-15', 4.0, 7, 5);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Devizna štednja', 'Štednja u stranoj valuti', '2023-12-15', 5.5, 8, 3);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Devizni transferi', 'Međunarodni novčani transferi', '2023-12-15', 10.0, 9, 3);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "korisnik", "filijala") VALUES(nextval('USLUGA_SEQ'), 'Investicioni fondovi', 'Ulaganje u investicione fondove', '2023-12-15', 7.0, 8, 3);
