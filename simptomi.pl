import('pacijenti.pl').

simptom(bolUGrudima).
simptom(mucnina).
simptom(hladnoZnojenje).
simptom(pritiskanje).
simptom(vrtoglavica).
simptom(gubitakSvesti).
simptom(otezanoDisanje).

sadrzi(S,[]).
sadrzi(S,[H|T]) :- member(H,S), sadrzi(S,T).




