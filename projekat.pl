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

pusac(petar,da).
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

potrebniSimptomi(analizaKrvi, [mucnina, pritiskanje]).
potrebniSimptomi(ehokardiografija, [bolUGrudima]).
potrebniSimptomi(ekg, [bolUGrudima, gubitakSvesti, pritiskanje]).
potrebniSimptomi(ergometrija, [pritiskanje]).
potrebniSimptomi(koronarnaAngiografija, [pritiskanje]).
potrebniSimptomi(rendgen, [otezanoDisanje, bolUGrudima]).
potrebniSimptomi(holter24, [gubitakSvesti, vrtoglavica]).
potrebniSimptomi(ct, [bolUGrudima]).

dodatnoIspitivanje(listaSimptoma(X, S), analizaKrvi) :-
    ( potrebniSimptomi(analizaKrvi, S2), sadrzi(S2, S), pacijent(X)) ;  
   (rezPritiska(X, P), (P = povisen; P = nizak), pacijent(X)).

%dodatnoIspitivanje(listaSimptoma(X, S), Y) :-
%    (( potrebniSimptomi(analizaKrvi, S2), sadrzi(S2, S), pacijent(X) ) -> Y = analizaKrvi);  
%   ((rezPritiska(X, P), (P = povisen; P = nizak), pacijent(X)) -> Y = analizaKrvi).

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
   (potrebniSimptomi(holter24, S2), sadrzi(S2, S), pacijent(X), rezPritiska(X, P), P = povisen) -> Y = holter24;
   (rezPritiska(X, P), P = povisen, auskultacija(X, A), (A = postojiSum; A = poremecajRitma)) -> Y = holter24.

%moze tek posle ekg-a i posle ergometrije ili rendgena
dodatnoIspitivanje(listaSimptoma(X, S), Y) :- 
   (potrebniSimptomi(ct, S2), sadrzi(S2, S), pacijent(X), rezErgometrija(X, E), E = neodredjeno, rezEkg(X, K, T), K = uredno, T=uredno) -> Y = ct;
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
%rezEkg: pacijent, ST, T, otkucajSrca
rezEkg(petar, ispodKonture, menjaPrikaz).

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
        (rezHolter24(X, povisen, prisutno, normalan), pacijent(X)) -> Y = hipertenzija;
        (rezPritiska(X, povisen), pacijent(X)) -> Y = hipertenzija.

dijagnoza(X, Y) :-
        (rezKA(X, pozitivno), pacijent(X)) -> Y = anginaPektoris;
        (rezErgometrija(X, niskaOpterecenja), pacijent(X)) -> Y = anginaPektoris;
        (rezEkg(X, ispodKonture, menjaPrikaz), pacijent(X)) -> Y = anginaPektoris.


%TERAPIJE
%lekovi za Anginu Pectoris
terapija(X, anginaPektoris, T) :- dijagnoza(X, anginaPektoris), pacijent(X),  asmaticar(X, ne), (rezPritiska(X,povisen);rezPritiska(X,normalan)), append([],[nitroglicerin, aspirin, atenolol, propranolol, rosuvastatin],T), !.
terapija(X, anginaPektoris, T) :- dijagnoza(X, anginaPektoris), pacijent(X), asmaticar(X, da), (rezPritiska(X,povisen);rezPritiska(X,normalan)), append([],[nitroglicerin, atenolol, rosuvastatin],T), !.
terapija(X, anginaPektoris, T) :- dijagnoza(X, anginaPektoris), pacijent(X), asmaticar(X, ne), rezPritiska(X,nizak), append([],[nitroglicerin, aspirin, rosuvastatin],T), !.
terapija(X, anginaPektoris, T) :- dijagnoza(X, anginaPektoris), pacijent(X), asmaticar(X, da), rezPritiska(X,nizak), append([],[nitroglicerin, rosuvastatin],T).

%lekovi za Hipertenziju
terapija(X, hipertenzija, T) :- dijagnoza(X, hipertenzija), pacijent(X), (secernaBolest(X, nema);secernaBolest(X, smanjen)) , (rezPritiska(X,povisen);rezPritiska(X,normalan)), append([], [lizinopril, kaptopril, atenolol, amlopin], T), !.
terapija(X, hipertenzija, T) :- dijagnoza(X, hipertenzija), pacijent(X), secernaBolest(X, povisen), (rezPritiska(X,povisen);rezPritiska(X,normalan)), append([], [lizinopril, atenolol, amlopin], T), !.
terapija(X, hipertenzija, T) :- dijagnoza(X, hipertenzija), pacijent(X), (secernaBolest(X, nema);secernaBolest(X, smanjen)), rezPritiska(X,nizak),  append([], [lizinopril, kaptopril], T), !.
terapija(X, hipertenzija, T) :- dijagnoza(X, hipertenzija), pacijent(X), secernaBolest(X, povisen), rezPritiska(X,nizak), append([], [lizinopril], T).


terapije(L) :- findall(X, terapija(X,D,T), L).



