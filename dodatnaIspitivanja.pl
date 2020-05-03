import('pacijenti.pl') as pacijenti.
import('rutinskiPregled.pl') as rutinskiPregledi.
import('simptomi.pl').

sadrzi(S,[]).
sadrzi(S,[H|T]) :- member(H,S), sadrzi(S,T).

listaSimptoma(pacijent, simptomi).

test(analizaKrvi, [mucnina, pritiskanje]).
dodatnoIspitivanje(listaSimptoma(X, S), analizaKrvi) :-
   test(analizaKrvi, S2), sadrzi(S2, S), pacijenti.pacijent(X),
   rutinskiPregledi.rezPritiska(X, P), (P = povisen; P = nizak).


% rezAnalizeKrvi: pacijent, nivoSeceraUKrvi, nivoHolesterola,
% nivoTriglicirida
%rezAnalizeKrvi(petar, 16, 5.8, 0.88).
%rezAnalizeKrvi(milica, 15, 3.6, 0.25).
%rezAnalizeKrvi(milan, 15, 2.1, 3).


test(ehokardiografija, [bolUGrudima]).
dodatnoIspitivanje(listaSimptoma(X, S), ehokardiografija) :-
   test(ehokardiografija, S2), sadrzi(S2, S), pacijenti.pacijent(X),
   rutinskiPregledi.askultacija(X, A), A = postojiSum.


%rezEkg: pacijent, ST, T, otkucajSrca
%rezEkg(petar, ispodKonture, menjaPrikaz).
%rezErgometrija(petar, niskaOpterecenja).
%rezKA(petar, pozitivno).



%holter
%ergometrija
%ekg
%analiza krvi
%koronarnaAngiografija
%


