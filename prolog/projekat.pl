%ime, pol, godine
pacijent(petar).
pacijent(milica).
pacijent(milan).
pacijent(ana).
pacijent(luka).
pacijent(zdravko).
pacijent(slavica).
pacijent(zeljko).
%
pol(petar, m).
pol(milica, z).
pol(milan, m).
pol(ana, z).
pol(luka, m).
pol(zdravko, m).
pol(slavica, z).
pol(zeljko, m).
%
godine(petar, 36).
godine(milica, 27).
godine(milan, 37).
godine(ana, 50).
godine(luka, 40).
godine(zdravko, 42).
godine(slavica, 48).
godine(zeljko, 41).
%
pusac(petar,ne).
pusac(milica,ne).
pusac(milan,da).
pusac(ana, da).
pusac(luka, ne).
pusac(zdravko, ne).
pusac(slavica, da).
pusac(zeljko, da).
%
tezina(petar, povecanaTezina).
tezina(milica, smanjenaTezina).
tezina(milan, normalnaTezina).
tezina(ana, povecanaTezina).
tezina(luka, normalnaTezina).
tezina(zdravko, normalnaTezina).
tezina(slavica, povecanaTezina).
tezina(zeljko, povecanaTezina).
%
dijabeticar(petar,da).
dijabeticar(milica,da).
dijabeticar(milan,ne).
dijabeticar(ana,ne).
dijabeticar(luka,ne).
dijabeticar(zdravko,ne).
dijabeticar(slavica,ne).
dijabeticar(zeljko,ne).
%
asmaticar(petar, da).
asmaticar(milica, ne).
asmaticar(milan, ne).
asmaticar(ana, ne).
asmaticar(luka, da).
asmaticar(zdravko, da).
asmaticar(slavica, ne).
asmaticar(zeljko, da).
%
fizickaAktivnost(petar, ne).
fizickaAktivnost(milan, ne).
fizickaAktivnost(milica, ne).
fizickaAktivnost(ana, da).
fizickaAktivnost(luka, ne).
fizickaAktivnost(zdravko, ne).
fizickaAktivnost(slavica, da).
fizickaAktivnost(zeljko, da).
%
trudnoca(petar, ne).
trudnoca(milica, da).
trudnoca(milan, ne).
trudnoca(ana, ne).
trudnoca(luka, ne).
trudnoca(zdravko, ne).
trudnoca(slavica, ne).
trudnoca(zeljko, ne).
%
alergican(petar, ne).
alergican(milica, ne).
alergican(milan, da).
alergican(ana, ne).
alergican(luka, da).
alergican(zdravko, ne).
alergican(slavica, ne).
alergican(zeljko, ne).
%
porodicneBolesti(petar,[trombofilija, dijabetes, artritis, infarktMiokarda, tahikardija]).
porodicneBolesti(milica,[dijabetes, trombofilija, artritis, infarktMiokarda, tahikardija]).
porodicneBolesti(milan,[artritis, trombofilija, dijabetes]).
porodicneBolesti(ana,[infarktMiokarda, trombofilija]).
porodicneBolesti(luka, [infarktMiokarda]).
porodicneBolesti(zdravko, []).
porodicneBolesti(slavica, [infarktMiokarda]).
porodicneBolesti(zeljko, [infarktMiokarda]).
%
simptom(bolUGrudima).
simptom(mucnina).
simptom(pritiskanje).
simptom(vrtoglavica).
simptom(gubitakSvesti).
simptom(otezanoDisanje).
simptom(malaksalost).
simptom(zamucenjeVida).
simptom(znojenje).
simptom(umor).
simptom(gusenje).
simptom(povracanje).
simptom(kratakDah).
simptom(povisenaTemperatura).
%PREGLED -> rutinske stvari i rezultati racunanja osnovnih stvari ---------------------------------------------------------------------------
auskultacija(petar,poremecajRitma).
auskultacija(milica,postojiSum).
auskultacija(milan,poremecajRitma).
auskultacija(ana,poremecajRitma).
auskultacija(luka, uredna).
auskultacija(zdravko, uredna).
auskultacija(slavica, poremecajRitma).
auskultacija(zeljko, poremecajRitma).
%racunanje pritiska
pritisak(petar,130,100).
pritisak(milica,124,100).
pritisak(milan,123,243).
pritisak(ana,130,100).
pritisak(luka, 121, 90).
pritisak(zdravko, 123, 94).
pritisak(slavica, 86, 57).
pritisak(zeljko,130,100).
%
rezPritiska(X,povisen) :- pritisak(X,A,B), A>120, B>80, !.
rezPritiska(X,nizak) :- pritisak(X,A,B), A<100, B<60, !.
rezPritiska(X,normalan):- pritisak(X,A,B), A>=100, A=<120, B>=60,B=<80.
%OPCIONI PREGLEDI u zavisnosti od anamneze ---------------------------------------------------------------------------------------------
listaSimptoma(pacijent, simptomi).
%
sadrzi(S,[]).
sadrzi(S,[H|T]) :- member(H,S), sadrzi(S, T).
%
potrebniSimptomi(analizaKrvi, [mucnina, pritiskanje, malaksalost, zamucenjeVida, umor, znojenje, vrtoglavica, gubitakSvesti]).
potrebniSimptomi(ekg, [bolUGrudima, gubitakSvesti, pritiskanje, malaksalost, zamucenjeVida, umor, znojenje, vrtoglavica, povracanje, gusenje, kratakDah]).
potrebniSimptomi(ehokardiografija, [bolUGrudima]).
potrebniSimptomi(ergometrija, [pritiskanje, kratakDah, otezanoDisanje, gubitakSvesti, vrtoglavica, umor, malaksalost, bolUGrudima]).
potrebniSimptomi(koronarnaAngiografija, [pritiskanje, bolUGrudima, malaksalost, znojenje, gusenje, povracanje]).
potrebniSimptomi(rendgen, [otezanoDisanje, bolUGrudima]).
potrebniSimptomi(holter24, [gubitakSvesti, vrtoglavica, malaksalost, zamucenjeVida, umor, znojenje]).
potrebniSimptomi(ct, [bolUGrudima]).
%DODATNA ISPITIVANJA -------------------------------------------------------------------------------------------------------------------
% analizaKrvi
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-((potrebniSimptomi(analizaKrvi, S2), sadrzi(S2, S), pacijent(X) ) -> Y = analizaKrvi);((rezPritiska(X, P), (P = povisen; P = nizak), pacijent(X)) -> Y = analizaKrvi).
% ehokardiografija
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-(potrebniSimptomi(ehokardiografija, S2), sadrzi(S2, S), pacijent(X)) -> Y = ehokardiografija;(auskultacija(X, A), A = postojiSum, pacijent(X)) -> Y = ehokardiografija.
% ekg
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-(potrebniSimptomi(ekg, S2), sadrzi(S2, S), pacijent(X)) -> Y = ekg;(auskultacija(X, A), A = poremecajRitma, pacijent(X)) -> Y = ekg.
% ergometrija
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-(potrebniSimptomi(ergometrija, S2), sadrzi(S2, S), pacijent(X), rezPritiska(X, P), (P = povisen; P = smanjen)) -> Y = ergometrija.
% koronarnaAngiografija
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-(potrebniSimptomi(koronarnaAngiografija, S2), sadrzi(S2, S), pacijent(X), rezPritiska(X, P), (P = povisen; P = smanjen), rezErgometrija(X, niskaOpterecenja)) -> Y = koronarnaAngiografija.
% rendgen
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-potrebniSimptomi(rendgen, S2), sadrzi(S2, S), pacijent(X) -> Y = rendgen.
% holter24
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-(potrebniSimptomi(holter24, S2), sadrzi(S2, S), pacijent(X), rezPritiska(X, P), (P = povisen ; P = nizak), rezEkg(X, K, T), K=nijeUredan, (T = ubrzan; T = usporen)) -> Y = holter24;(rezPritiska(X, P), (P = povisen; P = nizak), auskultacija(X, A), (A = postojiSum; A = poremecajRitma), rezEkg(X, K, T), K=nijeUredan, (T = ubrzan; T = usporen)) -> Y = holter24.
%ct
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-(potrebniSimptomi(ct, S2), sadrzi(S2, S), pacijent(X), rezErgometrija(X, E), E = neodredjeno, rezEkg(X, K, T), K = uredan, T=normalan) -> Y = ct;(rezRendgen(X, R), R = nijeUredan) -> Y = ct.
%racunanje secera
secernaBolest(X, R) :- (rezAnalizeKrvi(X, Y, H, T), Y>11) -> R = povisen ;dijabeticar(X, da) -> R = povisen ;(rezAnalizeKrvi(X, Y, H, T),Y>=4, Y=<7) -> R = nema;dijabeticar(X, ne) -> R = nema;R = smanjen.
%racunanje holesterola
rezHolesterola(X, R) :- (rezAnalizeKrvi(X, D, Y, T), Y >= 3.1, Y =< 5.5) -> R = normalan ;(rezAnalizeKrvi(X, D, Y, T), Y < 3.1) -> R = nizak;(rezAnalizeKrvi(X, D, Y, T), Y > 5.5) -> R = povisen .
%racunanje triglicerida
rezTriglicerida(X, normalan) :- (rezAnalizeKrvi(X, D, H, Y), Y >= 0.46, Y =< 2.28)-> R = normalan ;(rezAnalizeKrvi(X, D, H, Y), Y < 0.46) -> R = nizak;(rezAnalizeKrvi(X, D, H, Y), Y > 2.28) -> R = povisen.
%REZULTATI DODATNIH ISPITIVANJA ------------------------------------------------------------------------------------------------------
%rezAnalizeKrvi:  pacijent, nivoSeceraUKrvi, nivoHolesterola, nivoTriglecirida
rezAnalizeKrvi(petar,16,5.8,0.88).
rezAnalizeKrvi(milica,10,2,3).
rezAnalizeKrvi(milan, 10, 2.1, 3).
rezAnalizeKrvi(ana,16,5.8,0.88).
rezAnalizeKrvi(luka, 11, 2.5, 3.5).
rezAnalizeKrvi(zdravko, 11, 2.5, 3.5).
rezAnalizeKrvi(slavica, 10, 2.2, 3).
rezAnalizeKrvi(zeljko, 16, 5.8, 0.88).
%rezEkg: pacijent, nalaz (uredan, nijeUredan, neodredjen), puls(ubrzan, normalan, usporen)
rezEkg(petar,nijeUredan,ubrzan).
rezEkg(milan, nijeUredan, usporen).
rezEkg(milica,nijeUredan,normalan).
rezEkg(ana,nijeUredan,ubrzan).
rezEkg(luka, neodredjen, ubrzan).
rezEkg(zdravko, nijeUredan, ubrzan).
rezEkg(slavica, nijeUredan, usporen).
rezEkg(zeljko, nijeUredan, ubrzan).
%ergometrija, rezultati
rezErgometrija(petar, niskaOpterecenja).
rezErgometrija(ana, niskaOpterecenja).
rezErgometrija(slavica, niskaOpterecenja).
rezErgometrija(zdravko, niskaOpterecenja).
%ehokardiografija rezultati(uredan, nijeUredan)
rezEhokardiografije(milica,nijeUredan).
%koronarnaAngiografija -> potrebni rezultati ako je rutinsk/// pozitivno/negativno
rezKA(petar, pozitivno).
rezKA(ana, pozitivno).
%rezultati rendgena(uredan, nijeUredan)
rezRendgen(milan, nijeUredan).
rezRendgen(milica, nijeUredan).
%rezultati holtera 24
% srcanaFrekvencija, poremecajRitma, ST segment
rezHolter24(milan, povisen, prisutno, normalan).
rezHolter24(ana,povisen,prisutno,normalan).
rezHolter24(milica,povisen,nijePrisutno,normalan).
rezHolter24(luka, povisen, prisutno, normalan).
rezHolter24(zdravko, povisen, prisutno, normalan).
rezHolter24(slavica, snizen, prisutno, normalan).
%rezultati CT(uredan, nijeUredan)
rezCT(milica,nijeUredan).
rezCT(milan, nijeUredan).
%DIJAGNOZE ----------------------------------------------------------------------------------------------------------------------
% hipertenzija
dijagnoza(X, Y) :-(rezHolter24(X, povisen, prisutno, normalan), pacijent(X), rezPritiska(X, povisen), godine(X, G), G>25) -> Y = hipertenzija.
% hipotenzija
dijagnoza(X, Y) :-(rezHolter24(X, povisen, prisutno, normalan), pacijent(X), rezPritiska(X, nizak), godine(X, G), G>25, secernaBolest(X, R), R = nema) -> Y = hipotenzija.
% anginaPektoris
dijagnoza(X, Y) :-(rezKA(X, pozitivno), pacijent(X)) -> Y = anginaPektoris;(rezErgometrija(X, niskaOpterecenja), pacijent(X)) -> Y = anginaPektoris;(rezEkg(X, nijeUredan, ubrzan), pacijent(X)) -> Y = anginaPektoris.
% infarktMiokarda
dijagnoza(X, Y) :-(rezPritiska(X, povisen), secernaBolest(X, povisen), (rezHolesterola(X, povisen); pusac(X, da); tezina(X, povecanaTezina); fizickaAktivnost(X, da)), pacijent(X), rezEkg(X, nijeUredan, ubrzan)) -> Y = infarktMiokarda.
% bradikardija
dijagnoza(X, Y) :-(rezEkg(X, nijeUredan, usporen), rezPritiska(X, nizak), pacijent(X), (rezHolter24(X, snizen, prisutno, normalan); rezErgometrija(X, niskaOpterecenja)))  -> Y = bradikardija.
%tahikardija
dijagnoza(X, Y) :-(rezEkg(X, nijeUredan, ubrzan), rezPritiska(X, R), (R = normalan; R = povisen), pacijent(X), rezHolter24(X, povisen, prisutno, normalan))  -> Y = tahikardija.
%TERAPIJE ------------------------------------------------------------------------------------------------------------------------------
listaSvihLekova(X, T, L) :-  pacijent(X),
 (
    ( alergican(X, da) -> (delete(L, lizinopril, M1), delete(M1, kaptopril, M2), delete(M2, nitroglicerin, M3), delete(M3, aspirin, M4),
    delete(M4, atenolol, M5), delete(M5, propranolol, M6), delete(M6, rosuvastatin, M7), delete(M7, amlodipin, M8) , delete(M8, micardis, M9),
    delete(M9, promerol, M10), delete(M10, izopamil, M11), delete(M11, valsartan, M12), delete(M12, telmipres, M13), delete(M13, bisprolol, M14),
    delete(M14, metoprolol, M15), delete(M15, amiodaron, M16), delete(M16, propafen, M17), delete(M17, verapamil, M18), delete(M18, diltiazem, L1));
    append(L, [], L1) ),
    ( asmaticar(X, da) -> ( delete(L1, aspirin, M1), delete(M1, propranolol, M2), delete(M2, bisprolol, L2)  );
     append(L1, [], L2) ),
    ( dijabeticar(X,da) -> ( delete(L2, lizinopril, M1), delete(M1, kaptopril, M2), delete(M2, valsartan, L3));
    append(L2, [], L3)),
    ( trudnoca(X, da) -> (delete(L3, lizinopril, M1) ,delete(M1, kaptopril, M2), delete(M2, aspirin, M3), delete(M3, propranolol, M4),
    delete(M4, rosuvastatin, M5), delete(M5, micardis, M6), delete(M6, izopamil, M7), delete(M7, valsartan, M8), delete(M8, telmipres, M9),
    delete(M9, bisprolol, M10), delete(M10, metoprolol, M11), delete(M11, amiodaron, M12), delete(M12, verapamil, M13), delete(M13, diltiazem, L4));
    append(L3, [], L4) ),
    ( dijagnoza(X, bradikardija) -> (delete(L4, atenolol, M1), delete(M1, propranolol, M2), delete(M2, promerol, M3),
    delete(M3, bisprolol, M4), delete(M4, metoprolol, M5), delete(M5, amiodaron, M6),  delete(M6, propafen, M7), delete(M7, diltiazem, L5));
    append(L4, [], L5) ),
    ( dijagnoza(X, hipotenzija) -> (delete(L5, atenolol, M1) , delete(M1, propranolol, M2),  delete(M2, amlodipin, M3),
    delete(M3, promerol, M4), delete(M4, izopamil, M5),  delete(M5, bisprolol, M6), delete(M6, metoprolol, M7),
    delete(M7, propafen, M8), delete(M8, verapamil, L6)) ;
    append(L5, [], L6) ),
    ( godine(X, G), G =< 16 -> ( delete(L6, aspirin, M1), delete(M1, bisprolol, M2), delete(M2, amiodaron, M3), delete(M3, diltiazem, L7));
    append(L6, [], L7) ),
    ( godine(X, G), G =< 6 -> ( delete(L7, lizinopril, M1) , delete(M1, amlodipin, M2) ,delete(M2, metoprolol, L8));
    append(L7, [], L8) ),
    ( dijagnoza(X, anginaPektoris) ->  delete(L8, propranolol, L9) ;  append(L8, [], L9) ),
    ( dijagnoza(X, infarktMiokarda) -> ( delete(L9, amlodipin, M1), delete(M1, metoprolol, M2), delete(M2, propafen, M3),
    delete(M3, verapamil, L10));
    append(L9, [], L10) )
 ) -> append(L10, [], T).
%
terapija(X, hipertenzija, Z) :- dijagnoza(X, hipertenzija) ,listaSvihLekova(X, T, [lizinopril, kaptopril, atenolol, amlodipin, propranolol,micardis, izopamil, valsartan, telmipres, metoprolol, verapamil, diltiazem ]) -> Z = T.
%
terapija(X, hipotenzija, Z) :- dijagnoza(X, hipotenzija) ,listaSvihLekova(X, T, [zenSenKapsule, dihidroergotamin, etilefrin, amezinium, mineralokortikoida]) -> Z = T.
%
terapija(X, anginaPektoris, Z) :- dijagnoza(X, anginaPektoris) ,listaSvihLekova(X, T, [nitroglicerin, aspirin, atenolol, propranolol, rosuvastatin,amlodipin, izopamil, metoprolol, verapamil, diltiazem]) -> Z = T.
%
terapija(X, tahikardija, Z) :- dijagnoza(X, tahikardija),listaSvihLekova(X, T, [atenolol, propranolol, bisprolol, metoprolol, amiodaron, propafen, verapamil,diltiazem, promerol,izopamil]) -> Z = T.
%
terapija(X, bradikardija, Z) :- dijagnoza(X, bradikardija),listaSvihLekova(X, T, [zenSenKapsule]) -> Z = T.
%
terapija(X, infarktMiokarda, Z) :- dijagnoza(X, infarktMiokarda),listaSvihLekova(X, T, [lizinopril, kaptopril, nitroglicerin, aspirin, atenolol, propranolol, rosuvastatin,promerol, valsartan ]) -> Z = T.
rezHolter24(petar,povisen,prisutno,normalan).
rezEkg(aa,nijeUredan,usporen).
rezEhokardiografije(aa,uredan).
rezCT(aa,uredan).
rezHolter24(aa,snizen,nijePrisutno,nijeNormalan).
rezAnalizeKrvi(aa,1,1,1).
rezAnalizeKrvi(pavle,7,8,9).
rezEkg(pavle,nijeUredan,usporen).
rezCT(pavle,uredan).
rezHolter24(pavle,povisen,prisutno,normalan).
rezAnalizeKrvi(djsadaskd,3,6,2).
rezHolter24(djsadaskd,povisen,prisutno,normalan).
rezEhokardiografije(aaa,uredan).
rezCT(aaa,uredan).
rezEkg(kakkasdska,nijeUredan,ubrzan).
rezHolter24(kakkasdska,snizen,prisutno,normalan).
rezErgometrija(adasdas,niskaOpterecenja).
rezHolter24(adasdas,povisen,prisutno,normalan).
rezErgometrija(aa,niskaOpterecenja).
rezEkg(asasa,nijeUredan,usporen).
rezRendgen(asasa,nijeUredan).
rezEkg(dds,nijeUredan,usporen).
rezEkg(ivana,neodredjen,normalan).
rezEhokardiografije(ivana,uredan).
rezRendgen(ivana,uredan).
rezCT(ivana,uredan).
rezEkg(jj,nijeUredan,usporen).
rezErgometrija(jj,visokaOpterecenja).
rezEkg(YRTYRT,nijeUredan,usporen).
rezErgometrija(YRTYRT,visokaOpterecenja).
rezHolter24(dad,povisen,prisutno,normalan).
rezEkg(sasasdas,neodredjen,usporen).
rezEkg(yujkl,nijeUredan,usporen).
rezErgometrija(yujkl,visokaOpterecenja).
rezEkg(ujjk,nijeUredan,ubrzan).
rezEkg(dsds,nijeUredan,usporen).
rezEkg(dadasa,nijeUredan,usporen).
rezEkg(dasdas,neodredjen,usporen).
rezEkg(edede,uredan,ubrzan).
rezErgometrija(edede,niskaOpterecenja).
rezEkg(nh,nijeUredan,usporen).
rezEhokardiografije(nh,uredan).