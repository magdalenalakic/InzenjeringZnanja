import('pacijenti.pl').

test(ehokardiografija, [bolUGrudima]).
dodatnoIspitivanje(listaSimptoma(X, S), ehokardiografija) :-
   test(ehokardiografija, S2), sadrzi(S2, S),  pacijent(X).


%rezEkg: pacijent, ST, T, otkucajSrca
rezEkg(petar, ispodKonture, menjaPrikaz).
rezErgometrija(petar, niskaOpterecenja).
rezKA(petar, pozitivno).




