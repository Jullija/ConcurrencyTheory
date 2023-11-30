Rozważmy zbiór zmiennych („bazę danych”) {x, y, z} i następujący zbiór akcji („transakcji”) modyfikujących wartości tych zmiennych:

· (a) x := x + y

· (b) y := y + 2z

· (c) x := 3x + z

· (d) z := y – z.

Akcje możemy wykonywać współbieżnie z następującym zastrzeżeniem: akcja zmieniająca wartość zmiennej nie może być wykonana współbieżnie z akcją odczytującą lub modyfikującą stan tej samej zmiennej. W języku teorii śladów: dwie akcje są zależne jeśli obie operują na tej samej zmiennej, a przynajmniej jedna z nich modyfikuje wartość tej zmiennej.




## Zadanie 1a 
### W alfabecie A = { a, b, c, d} określ relacje zależności i niezależności.

I (niezależne): {(a, d), (d, a), (b, c), (c, b)}

D (zależne): uzupełnienie




## Zadanie 1b
### Wyznacz ślad wyznaczony przez słowo w = b a a d c b względem powyższej relacji niezależności.

Ślad: wszystkie słowa, które dadzą ten sam wynik. 

Ślad: [w] = {b, aaad, cb }
             b  ada   cb
             b  daa   cb
             b        bc
             b        bc
             b        bc


B jest zależne od a, więc nie możemy zamienić ich kolejnością.
D jest niezależne od a, więc możemy je zamienić kolejnością.




### Zadanie 1c 
### Wyznacz postać normalną Foaty śladu [w] (algorytm z pracy Volker Diekert, Yves Métivier : Partial Commutation and Traces str 10)


Algorytm: robimy kubełki na każdą literę. Bierzemy słowo, do którego mamy robić postać normalną i robimy to OD TYŁU. Jeśli jest b, wpisujemy do kubełka i sprawdzamy z czym jest zależne - przy nich piszemy gwiazdkę. Następnie sięgamy rzeczy (jeśli coś jest na wierzchu, ściągamy wszystko na raz i odpowiadające mu gwiazdki)

```
 *    |   b    |       |
 a    |   *    |   *   |   *
 a    |   *    |   *   |   d 
 *    |   *    |   *   |   *
 *    |   b    |   c   |   *
--------------------------
 a.   |   b.   |   c.  |    d

```




**Pierwszy krok: (b)**
```
     |        |       |
a    |   *    |   *   |    
a    |   *    |   *   |   d 
*    |   *    |   *   |   *
*    |   b    |   c   |   *
--------------------------
a.   |   b.   |   c.  |    d
```







**Drugi krok: (b) (a d)**
```
     |        |       |
     |        |       |    
a    |   *    |   *   |   d 
*    |   *    |   *   |   *
*    |   b    |   c   |   *
--------------------------
a.   |   b.   |   c.  |    d


     |        |       |
     |        |       |    
a    |        |       |     
*    |   *    |   *   |   *
*    |   b    |   c   |   *
--------------------------
a.   |   b.   |   c.  |    d
```





**Trzeci krok: (b) (a d) (a)**
```
     |        |       |
     |        |       |    
     |        |       |     
*    |        |       |   *
*    |   b    |   c   |   *
--------------------------
a.   |   b.   |   c.  |    d
```






**Czwarty krok: (b) (a d) (a) (b c)**
```
     |        |       |
     |        |       |    
     |        |       |     
     |        |       |    
*    |        |   c   |   *
--------------------------
a.   |   b.   |   c.  |    d


     |        |       |
     |        |       |    
     |        |       |     
     |        |       |    
     |        |       |    
--------------------------
a.   |   b.   |   c.  |    d

```



## Zadanie 1d 
### Narysuj graf zależności Diekerta (w postaci zminimalizowanej - bez krawędzi "przechodnich") dla słowa w.
w = b a a d cb


Patrzymy na postać normalną i robimy strzałki do wszystkich zależnych do przodu. Potem usuwamy te nadmiarowe.





