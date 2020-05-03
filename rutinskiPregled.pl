
%racunanje pritiska
rezPritiska(X,povisen) :- pritisak(X,A,B), A>120, B>80, !.
rezPritiska(X,nizak) :- pritisak(X,A,B), A<100, B<60, !.
rezPritiska(X,normalan):- pritisak(X,A,B), A>=100, A=<120, B>=60,B=<80.

auskultacija(postojiSum).
auskultacija(poremecajRitma).
auskultacija(uredna).


%racunanje idealne tezine
indeks(X,Y) :- pacijent(X, P, G, V, T), Y is T/((V/100)^2).
bmi(X,gojazan) :- pacijent(X, P, G, V, T), indeks(X,Y), ((G>=18, G=<24, Y>24); (G>=25, G=<34, Y>25); (G>=35,G=<44, Y>26);
(G>=45, G=<54, Y>27); (G>=55, G=<64, Y>28); (G>=65, Y>29)), !.
bmi(X,normalnaTezina) :- pacijent(X, P, G, V, T), indeks(X,B), ((G>=18, G=<24, B>=19, B=<24); (G>=25, G=<34, B>=20, B=<25);
(G>=35, G=<44, B>=21, B=<26); (G>=45, G=<54, B>=22, B=<27); (G>=55, G=<64, B>=23, B=<28); (G>=65, B>=24, B=<29)), !.
bmi(X,smanjenaTezina) :- pacijent(X, P, G, V, T).

% rezAnalizeKrvi: pacijent, nivoSeceraUKrvi, nivoHolesterola,
% nivoTriglicirida
rezAnalizeKrvi(petar, 16, 5.8, 0.88).
rezAnalizeKrvi(milica, 15, 3.6, 0.25).
rezAnalizeKrvi(milan, 15, 2.1, 3).

