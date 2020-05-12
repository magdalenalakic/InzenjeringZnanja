%ime, pol, godine
pacijent(petar).
pacijent(milica).
pacijent(milan).

pol(petar, m).
pol(milica, z).
pol(milan, m).

godine(petar, 36).
godine(milica, 23).
godine(milan, 37).

pusac(petar,ne).
pusac(milica,ne).
pusac(milan,da).

tezina(petar, povecanaTezina).
tezina(milica, smanjenaTezina).
tezina(milan, normalnaTezina).

dijabeticar(petar,da).
dijabeticar(milica,da).
dijabeticar(milan,ne).

asmaticar(petar, da).
asmaticar(milica, ne).
asmaticar(milan, ne).

fizickaAktivnost(petar, ne).
fizickaAktivnost(milan, ne).
fizickaAktivnost(milica, ne).

trudnoca(milica, da).

alergican(petar, ne).
alergican(milica, da).
alergican(milan, da).

porodicneBolesti(petar, [trombofilija]).
porodicneBolesti(milica, [dijabetes]).
porodicneBolesti(milan, [artritis]).

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

%PREGLED -> rutinske stvari i rezultati racunanja osnovnih stvari
auskultacija(petar, poremecajRitma).
auskultacija(milica, postojiSum ).
auskultacija(milan, uredna).

%racunanje pritiska
pritisak(petar,130,100).
pritisak(milica,120,70).
pritisak(milan,90,50).

rezPritiska(X,povisen) :- pritisak(X,A,B), A>120, B>80, !.
rezPritiska(X,nizak) :- pritisak(X,A,B), A<100, B<60, !.
rezPritiska(X,normalan):- pritisak(X,A,B), A>=100, A=<120, B>=60,B=<80.

%OPCIONI PREGLEDI u zavisnosti od anamneze

listaSimptoma(pacijent, simptomi).

sadrzi(S,[]).
sadrzi(S,[H|T]) :- member(H,S), sadrzi(S, T).

potrebniSimptomi(analizaKrvi, [mucnina, pritiskanje, malaksalost, zamucenjeVida, umor, znojenje, vrtoglavica, gubitakSvesti]).
potrebniSimptomi(ekg, [bolUGrudima, gubitakSvesti, pritiskanje, malaksalost, zamucenjeVida, umor, znojenje, vrtoglavica, povracanje, gusenje]).
potrebniSimptomi(ehokardiografija, [bolUGrudima]).
potrebniSimptomi(ergometrija, [pritiskanje]).
potrebniSimptomi(koronarnaAngiografija, [pritiskanje, bolUGrudima, malaksalost, znojenje, gusenje, povracanje]).
potrebniSimptomi(rendgen, [otezanoDisanje, bolUGrudima]).
potrebniSimptomi(holter24, [gubitakSvesti, vrtoglavica, malaksalost, zamucenjeVida, umor, znojenje]).
potrebniSimptomi(ct, [bolUGrudima]).

dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   ((potrebniSimptomi(analizaKrvi, S2), sadrzi(S2, S), pacijent(X) ) -> Y = analizaKrvi);
   ((rezPritiska(X, P), (P = povisen; P = nizak), pacijent(X)) -> Y = analizaKrvi).

dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(ehokardiografija, S2), sadrzi(S2, S), pacijent(X)) -> Y = ehokardiografija;
   (auskultacija(X, A), A = postojiSum, pacijent(X)) -> Y = ehokardiografija.

dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(ekg, S2), sadrzi(S2, S), pacijent(X)) -> Y = ekg;
   (auskultacija(X, A), A = poremecajRitma, pacijent(X)) -> Y = ekg.

dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(ergometrija, S2), sadrzi(S2, S), pacijent(X)) -> Y = ergometrija;
   (rezPritiska(X, P), P = povisen, pacijent(X)) -> Y = ergometrija.

dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(koronarnaAngiografija, S2), sadrzi(S2, S), pacijent(X)) -> Y = koronarnaAngiografija;
   (rezPritiska(X, P), P = povisen, pacijent(X)) -> Y = koronarnaAngiografija.

dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   potrebniSimptomi(rendgen, S2), sadrzi(S2, S), pacijent(X) -> Y = rendgen.

dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(holter24, S2), sadrzi(S2, S), pacijent(X), rezPritiska(X, P), (P = povisen ; P = nizak), rezEkg(X, K, T), K=nijeUredan, (T = ubrzan; T = usporen)) -> Y = holter24;
   (rezPritiska(X, P), (P = povisen; P = nizak), auskultacija(X, A), (A = postojiSum; A = poremecajRitma), rezEkg(X, K, T), K=nijeUredan, (T = ubrzan; T = usporen)) -> Y = holter24.

%moze tek posle ekg-a i posle ergometrije ili rendgena
dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
   (potrebniSimptomi(ct, S2), sadrzi(S2, S), pacijent(X), rezErgometrija(X, E), E = neodredjeno, rezEkg(X, K, T), K = uredan, T=normalan) -> Y = ct;
   (rezRendgen(X, R), R = abnormalnePojave) -> Y = ct.


%AKO JE POTREBNA ANALIZA KRVI
%rezultati analize krvi
%rezAnalizeKrvi:  pacijent, nivoSeceraUKrvi, nivoHolesterola, nivoTriglecirida
rezAnalizeKrvi(petar, 16, 5.8, 0.88).
rezAnalizeKrvi(milica, 15, 3.6, 0.25).
rezAnalizeKrvi(milan, 10, 2.1, 3).

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


%AKO JE ODRADJEN EKG
%rezEkg: pacijent, nalaz (uredan, nijeUredan), puls(ubrzan, normalan, usporen)
rezEkg(petar, nijeUredan, ubrzan).
rezEkg(milan, nijeUredan, usporen).

%ergometrija, rezultati
rezErgometrija(petar, niskaOpterecenja).

%koronarnaAngiografija -> potrebni rezultati ako je rutinski
rezKA(petar, pozitivno).

%rezultati rendgena
rezRendgen(milan, abnormalnePojave).

%rezultati holtera 24
% srcanaFrekvencija, poremecajRitma, ST segment
rezHolter24(milan, povisen, prisutno, normalan).

%DIJAGNOZE

dijagnoza(X, Y) :-
        (rezHolter24(X, povisen, prisutno, normalan), pacijent(X), rezPritiska(X, povisen), godine(X, G), G>25) -> Y = hipertenzija.

dijagnoza(X, Y) :-
        (rezHolter24(X, povisen, prisutno, normalan), pacijent(X), rezPritiska(X, nizak), godine(X, G), G>25, secernaBolest(X, R), R = nema) -> Y = hipotenzija.


dijagnoza(X, Y) :-
        (rezKA(X, pozitivno), pacijent(X)) -> Y = anginaPektoris;
        (rezErgometrija(X, niskaOpterecenja), pacijent(X)) -> Y = anginaPektoris;
        (rezEkg(X, nijeUredan, ubrzan), pacijent(X)) -> Y = anginaPektoris.

dijagnoza(X, Y) :-
         (rezPritiska(X, povisen), secernaBolest(X, povisen), (rezHolesterola(X, povisen); pusac(X, da); tezina(X, povecanaTezina); fizickaAktivnost(X, da)), pacijent(X), rezEkg(X, nijeUredan, ubrzan)) -> Y = infarktMiokarda.

%TERAPIJE
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

%lijekovi za Hipotenziju: Mogući terapijski režim uključuje dihidroergotamin, etilefrin, amezinium, njihovu kombinaciju ili postupno dodavanje mineralokortikoida.
terapija(X, hipotenzija, T) :- dijagnoza(X, hipotenzija), pacijent(X), trudnoca(X, ne), godine(X, G), G>=16, secernaBolest(X, nema), rezPritiska(X, nizak), append([], [dihidroergotamin, etilefrin, amezinium, mineralokortikoida], T).

%lekovi za infarkt miokarda
terapija(X, infarktMiokarda, T) :- dijagnoza(X, infarktMiokarda), trudnoca(X, ne), godine(X, G), G>=16, pacijent(X), alergican(X, ne), asmaticar(X, ne),  append([], [aspirin, nitroglicerin, propranolol], T).

terapije(L) :- findall(X, terapija(X,D,T), L).



