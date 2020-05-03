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

%PREGLED
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

listaSimptoma(pacijent, simptomi).

sadrzi(S,[]).
sadrzi(S,[H|T]) :- member(H,S), sadrzi(S, T).

potrebniSimptomi(analizaKrvi, [mucnina, pritiskanje]).
potrebniSimptomi(ehokardiografija, [bolUGrudima]).

dodatnoIspitivanje(listaSimptoma(X, S), analizaKrvi) :-
   (potrebniSimptomi(analizaKrvi, S2), sadrzi(S2, S), pacijent(X));
   (rezPritiska(X, P), (P = povisen; P = nizak), pacijent(X)).

dodatnoIspitivanje(listaSimptoma(X, S), ehokardiografija) :-
   (potrebniSimptomi(ehokardiografija, S2), sadrzi(S2, S), pacijent(X));
   (auskultacija(X, A), A = postojiSum, pacijent(X)).



