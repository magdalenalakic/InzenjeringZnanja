import('pacijenti.pl').

%PREGLED
pritisak(petar,130,100).
pritisak(milica,120,70).
pritisak(milan,90,50).

askultacija(petar, poremecajRitma).
askultacija(milica, postojiSum ).
askultacija(milan, uredna).

%racunanje pritiska
rezPritiska(X,povisen) :- pritisak(X,A,B), A>120, B>80, !.
rezPritiska(X,nizak) :- pritisak(X,A,B), A<100, B<60, !.
rezPritiska(X,normalan):- pritisak(X,A,B), A>=100, A=<120, B>=60,B=<80.


