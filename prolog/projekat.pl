%ime, pol, godine
pacijent(petar).
pacijent(milica).
pacijent(milan).
pacijent(ana).
pacijent(luka).

pol(petar, m).
pol(milica, z).
pol(milan, m).
pol(ana, z).
pol(luka, m).

godine(petar, 36).
godine(milica, 23).
godine(milan, 37).
godine(ana, 50).
godine(luka, 40).

pusac(petar,ne).
pusac(milica,ne).
pusac(milan,da).
pusac(ana, da).
pusac(luka, ne).

tezina(petar, povecanaTezina).
tezina(milica, smanjenaTezina).
tezina(milan, normalnaTezina).
tezina(ana, povecanaTezina).
tezina(luka, normalnaTezina).

dijabeticar(petar,da).
dijabeticar(milica,da).
dijabeticar(milan,ne).
dijabeticar(ana, ne).
dijabeticar(luka, ne).

asmaticar(petar, da).
asmaticar(milica, ne).
asmaticar(milan, ne).
asmaticar(ana, ne).
asmaticar(luka, da).

fizickaAktivnost(petar, ne).
fizickaAktivnost(milan, ne).
fizickaAktivnost(milica, ne).
fizickaAktivnost(ana, da).
fizickaAktivnost(luka, ne).

trudnoca(milica, da).
trudnoca(ana, ne).

alergican(petar, ne).
alergican(milica, da).
alergican(milan, da).
alergican(ana, ne).
alergican(luka, da).

porodicneBolesti(petar, [trombofilija]).
porodicneBolesti(milica, [dijabetes]).
porodicneBolesti(milan, [artritis]).
porodicneBolesti(ana, [infarktMiokarda]).
porodicneBolesti(luka, [infarktMiokarda]).

simptom(bolUGrudima).
simptom(mucnina).
simptom(hladnoZnojenje).
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
auskultacija(petar, poremecajRitma).
auskultacija(milica, postojiSum).
auskultacija(milan, uredna).
auskultacija(ana, poremecajRitma).
auskultacija(luka, uredna).

%racunanje pritiska
pritisak(petar,130,100).
pritisak(milica,120,70).
pritisak(milan,90,50).
pritisak(ana, 85, 60).
pritisak(luka, 120, 90).

rezPritiska(X,povisen) :- pritisak(X,A,B), A>120, B>80, !.
rezPritiska(X,nizak) :- pritisak(X,A,B), A<100, B<60, !.
rezPritiska(X,normalan):- pritisak(X,A,B), A>=100, A=<120, B>=60,B=<80.

%OPCIONI PREGLEDI u zavisnosti od anamneze ---------------------------------------------------------------------------------------------

listaSimptoma(pacijent, simptomi).

sadrzi(S,[]).
sadrzi(S,[H|T]) :- member(H,S), sadrzi(S, T).

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
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   ((potrebniSimptomi(analizaKrvi, S2), sadrzi(S2, S), pacijent(X) ) -> Y = analizaKrvi);
   ((rezPritiska(X, P), (P = povisen; P = nizak), pacijent(X)) -> Y = analizaKrvi).

% ehokardiografija
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(ehokardiografija, S2), sadrzi(S2, S), pacijent(X)) -> Y = ehokardiografija;
   (auskultacija(X, A), A = postojiSum, pacijent(X)) -> Y = ehokardiografija.

% ekg
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(ekg, S2), sadrzi(S2, S), pacijent(X)) -> Y = ekg;
   (auskultacija(X, A), A = poremecajRitma, pacijent(X)) -> Y = ekg.

% ergometrija
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(ergometrija, S2), sadrzi(S2, S), pacijent(X), rezPritiska(X, P), (P = povisen; P = smanjen)) -> Y = ergometrija.

% koronarnaAngiografija
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(koronarnaAngiografija, S2), sadrzi(S2, S), pacijent(X), rezPritiska(X, P), (P = povisen; P = smanjen), rezErgometrija(X, niskaOpterecenja)) -> Y = koronarnaAngiografija.

% rendgen
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   potrebniSimptomi(rendgen, S2), sadrzi(S2, S), pacijent(X) -> Y = rendgen.

% holter24
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(holter24, S2), sadrzi(S2, S), pacijent(X), rezPritiska(X, P), (P = povisen ; P = nizak), rezEkg(X, K, T), K=nijeUredan, (T = ubrzan; T = usporen)) -> Y = holter24;
   (rezPritiska(X, P), (P = povisen; P = nizak), auskultacija(X, A), (A = postojiSum; A = poremecajRitma), rezEkg(X, K, T), K=nijeUredan, (T = ubrzan; T = usporen)) -> Y = holter24.

%ct
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(ct, S2), sadrzi(S2, S), pacijent(X), rezErgometrija(X, E), E = neodredjeno, rezEkg(X, K, T), K = uredan, T=normalan) -> Y = ct;
   (rezRendgen(X, R), R = nijeUredan) -> Y = ct.


%racunanje secera
secernaBolest(X, R) :- (rezAnalizeKrvi(X, Y, H, T), Y>11) -> R = povisen ;
                        dijabeticar(X, da) -> R = povisen ;
                       (rezAnalizeKrvi(X, Y, H, T),Y>=4, Y=<7) -> R = nema;
                       dijabeticar(X, ne) -> R = nema;
                       R = smanjen.

%racunanje holesterola
rezHolesterola(X, R) :- (rezAnalizeKrvi(X, D, Y, T), Y >= 3.1, Y =< 5.5) -> R = normalan ;
                        (rezAnalizeKrvi(X, D, Y, T), Y < 3.1) -> R = povisen ;
                        (rezAnalizeKrvi(X, D, Y, T), Y > 5.5) -> R = povisen .

%racunanje triglicerida
rezTriglicerida(X, normalan) :- (rezAnalizeKrvi(X, D, H, Y), Y >= 0.46, Y =< 2.28)-> R = normalan ;
                                (rezAnalizeKrvi(X, D, H, Y), Y < 0.46) -> R = nizak;
                                (rezAnalizeKrvi(X, D, H, Y), Y > 2.28) -> R = povisen.

%REZULTATI DODATNIH ISPITIVANJA ------------------------------------------------------------------------------------------------------
%rezAnalizeKrvi:  pacijent, nivoSeceraUKrvi, nivoHolesterola, nivoTriglecirida
rezAnalizeKrvi(petar, 16, 5.8, 0.88).
rezAnalizeKrvi(milica, 15, 3.6, 0.25).
rezAnalizeKrvi(milan, 10, 2.1, 3).
rezAnalizeKrvi(ana,  10, 2.1, 3).
rezAnalizeKrvi(luka,  11, 2.5, 3.5).

%rezEkg: pacijent, nalaz (uredan, nijeUredan, neodredjen), puls(ubrzan, normalan, usporen)
rezEkg(petar, nijeUredan, ubrzan).
rezEkg(milan, nijeUredan, usporen).
rezEkg(milica, nijeUredan, ubrzan).
rezEkg(ana, nijeUredan, usporen).
rezEkg(luka, neodredjen, ubrzan).

%ergometrija, rezultati
rezErgometrija(petar, niskaOpterecenja).
rezErgometrija(ana, niskaOpterecenja).

%ehokardiografija rezultati(uredna, nijeUredna)
rezEhokardiografije(milica, uredna).

%koronarnaAngiografija -> potrebni rezultati ako je rutinski
rezKA(petar, pozitivno).
rezKA(ana, pozitivno).

%rezultati rendgena(uredan, nijeUredan)
rezRendgen(milan, nijeUredan).
rezRendgen(milica, nijeUredan).

%rezultati holtera 24
% srcanaFrekvencija, poremecajRitma, ST segment
rezHolter24(milan, povisen, prisutno, normalan).
rezHolter24(ana, snizen, prisutno, normalan).
rezHolter24(luka, povisen, prisutno, normalan).

%rezultati CT(uredan, nijeUredan)
rezCT(milica, uredan).

%DIJAGNOZE ----------------------------------------------------------------------------------------------------------------------
% hipertenzija
dijagnoza(X, Y) :-
        (rezHolter24(X, povisen, prisutno, normalan), pacijent(X), rezPritiska(X, povisen), godine(X, G), G>25) -> Y = hipertenzija.

% hipotenzija
dijagnoza(X, Y) :-
        (rezHolter24(X, povisen, prisutno, normalan), pacijent(X), rezPritiska(X, nizak), godine(X, G), G>25, secernaBolest(X, R), R = nema) -> Y = hipotenzija.

% anginaPektoris
dijagnoza(X, Y) :-
        (rezKA(X, pozitivno), pacijent(X)) -> Y = anginaPektoris;
        (rezErgometrija(X, niskaOpterecenja), pacijent(X)) -> Y = anginaPektoris;
        (rezEkg(X, nijeUredan, ubrzan), pacijent(X)) -> Y = anginaPektoris.

% infarktMiokarda
dijagnoza(X, Y) :-
         (rezPritiska(X, povisen), secernaBolest(X, povisen), (rezHolesterola(X, povisen); pusac(X, da); tezina(X, povecanaTezina); fizickaAktivnost(X, da)), pacijent(X), rezEkg(X, nijeUredan, ubrzan)) -> Y = infarktMiokarda.

% bradikardija
dijagnoza(X, Y) :-
         (rezEkg(X, nijeUredan, usporen), rezPritiska(X, nizak), pacijent(X), (rezHolter24(X, snizen, prisutno, normalan); rezErgometrija(X, niskaOpterecenja)))  -> Y = bradikardija.

%tahikardija
dijagnoza(X, Y) :-
         (rezEkg(X, nijeUredan, ubrzan), rezPritiska(X, R), (R = normalan; R = povisen), pacijent(X), rezHolter24(X, povisen, prisutno, normalan))  -> Y = tahikardija.

dijagnoza(X, Y) :-
         (rezPritiska(X, povisen), secernaBolest(X, povisen), (rezHolesterola(X, povisen); pusac(X, da); tezina(X, povecanaTezina); fizickaAktivnost(X, da)), pacijent(X), rezEkg(X, nijeUredan, ubrzan)) -> Y = infarktMiokarda.

%TERAPIJE ------------------------------------------------------------------------------------------------------------------------------
%TODO: ispitati pol, godine, trudnoca, pusac...
%lekovi za Anginu Pectoris
terapija(X, anginaPektoris, T) :- dijagnoza(X, anginaPektoris), pacijent(X), trudnoca(X, ne), asmaticar(X, ne), (rezPritiska(X,povisen);rezPritiska(X,normalan)), append([],[nitroglicerin, aspirin, atenolol, propranolol, rosuvastatin],T), !.
terapija(X, anginaPektoris, T) :- dijagnoza(X, anginaPektoris), pacijent(X), trudnoca(X, ne), asmaticar(X, da), (rezPritiska(X,povisen);rezPritiska(X,normalan)), append([],[nitroglicerin, atenolol, rosuvastatin],T), !.
terapija(X, anginaPektoris, T) :- dijagnoza(X, anginaPektoris), pacijent(X), trudnoca(X, ne), asmaticar(X, ne), rezPritiska(X,nizak), append([],[nitroglicerin, aspirin, rosuvastatin],T), !.
terapija(X, anginaPektoris, T) :- dijagnoza(X, anginaPektoris), pacijent(X), trudnoca(X, ne), asmaticar(X, da), rezPritiska(X,nizak), append([],[nitroglicerin, rosuvastatin],T).

%lekovi za Hipertenziju
terapija(X, hipertenzija, T) :- dijagnoza(X, hipertenzija), pacijent(X), trudnoca(X, ne),(secernaBolest(X, nema);secernaBolest(X, smanjen)) , (rezPritiska(X,povisen);rezPritiska(X,normalan)), append([], [lizinopril, kaptopril, atenolol, amlopin], T), !.
terapija(X, hipertenzija, T) :- dijagnoza(X, hipertenzija), pacijent(X), trudnoca(X, ne),secernaBolest(X, povisen), (rezPritiska(X,povisen);rezPritiska(X,normalan)), append([], [lizinopril, atenolol, amlopin], T), !.
terapija(X, hipertenzija, T) :- dijagnoza(X, hipertenzija), pacijent(X), trudnoca(X, ne),(secernaBolest(X, nema);secernaBolest(X, smanjen)), rezPritiska(X,nizak),  append([], [lizinopril, kaptopril], T), !.
terapija(X, hipertenzija, T) :- dijagnoza(X, hipertenzija), pacijent(X), trudnoca(X, ne),secernaBolest(X, povisen), rezPritiska(X,nizak), append([], [lizinopril], T).

%lijekovi za Hipotenziju: Mogu?i terapijski režim uklju??uje dihidroergotamin, etilefrin, amezinium, njihovu kombinaciju ili postupno dodavanje mineralokortikoida.
terapija(X, hipotenzija, T) :- dijagnoza(X, hipotenzija), pacijent(X), trudnoca(X, ne), godine(X, G), G>=16, secernaBolest(X, nema), rezPritiska(X, nizak), append([], [dihidroergotamin, etilefrin, amezinium, mineralokortikoida], T).

%lekovi za infarkt miokarda
terapija(X, infarktMiokarda, T) :- dijagnoza(X, infarktMiokarda), trudnoca(X, ne), godine(X, G), G>=16, pacijent(X), alergican(X, ne), asmaticar(X, ne),  append([], [aspirin, nitroglicerin, propranolol], T).

terapije(L) :- findall(X, terapija(X,D,T), L).

