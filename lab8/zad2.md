# Zadanie 2

Dany jest zbiór akcji:

· (a) x ← y + z

· (b) y ← x + w + y

· (c) x ← x + y + v

· (d) w ← v + z

· (e) v ← x + v + w

· (f) z ← y + z + v.



## Zadanie 2a
### W alfabecie A = { a, b, c, d, e, f} określ relacje zależności i niezależności.


I: (niezależne): {(a, d), (d, a), (b, e), (e, b), (c, d), (d, c), (c, f), (f, c)}.   (Np.: a zalezy od f, bo, z <- y+z+v = x + v)

D: (zależność): uzupełnienie





## Zadanie 2b 
### Wyznacz postać normalną Foaty śladu [u] dla u=a c d c f b b e

Zdejmujemy śnieżynki wierzchnie zależne od zdejmowanej litery.

```

 a   |   *    |       |        |      |    
 *   |   *    |   *   |        |  *   |   *
 *   |   *    |   c   |    d   |  *   |   *
 *   |   *    |   c   |    *   |  *   |   f
 *   |   *    |   *   |    *   |  *   |   *
 *   |   b    |   *   |    *   |  *   |   *
 *   |   b    |   *   |    *   |  e   |   *
--------------------------------------------
a.   |   b.   |   c.  |    d   |   e  |   f
```




**Pierwszy krok: (a d)**
```
     |        |       |        |      |    
 *   |   *    |       |        |      |    
 *   |   *    |   c   |    d   |  *   |   *
 *   |   *    |   c   |    *   |  *   |   f
 *   |   *    |   *   |    *   |  *   |   *
 *   |   b    |   *   |    *   |  *   |   *
 *   |   b    |   *   |    *   |  e   |   *
--------------------------------------------
a.   |   b.   |   c.  |    d   |   e  |   f



     |        |       |        |      |    
 *   |        |       |        |      |    
 *   |   *    |   c   |        |      |    
 *   |   *    |   c   |    *   |  *   |   f
 *   |   *    |   *   |    *   |  *   |   *
 *   |   b    |   *   |    *   |  *   |   *
 *   |   b    |   *   |    *   |  e   |   *
--------------------------------------------
a.   |   b.   |   c.  |    d   |   e  |   f
```





**Drugi krok: (a d) (c f)**


```
     |        |       |        |      |    
     |        |       |        |      |    
 *   |        |       |        |      |    
 *   |   *    |   c   |    *   |      |   f
 *   |   *    |   *   |    *   |  *   |   *
 *   |   b    |   *   |    *   |  *   |   *
 *   |   b    |   *   |    *   |  e   |   *
--------------------------------------------
a.   |   b.   |   c.  |    d   |   e  |   f




     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
 *   |        |   c   |        |      |    
 *   |   *    |   *   |    *   |      |   *
 *   |   b    |   *   |    *   |  *   |   *
 *   |   b    |   *   |    *   |  e   |   *
--------------------------------------------
a.   |   b.   |   c.  |    d   |   e  |   f
```



**Trzeci krok: (a d) (c f) (c)**

```
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
 *   |        |   *   |    *   |      |   *
 *   |   b    |   *   |    *   |      |   *
 *   |   b    |   *   |    *   |  e   |   *
--------------------------------------------
a.   |   b.   |   c.  |    d   |   e  |   f
```


**Czwarty krok: (a d) (c f) (c) (b e)**

```

     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
 *   |        |   *   |    *   |      |   *
 *   |   b    |   *   |    *   |  e   |   *
--------------------------------------------
a.   |   b.   |   c.  |    d   |   e  |   f



     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
 *   |   b    |   *   |    *   |      |   *
--------------------------------------------
a.   |   b.   |   c.  |    d   |   e  |   f
```


**Piąty krok: (a d) (c f) (c) (b e) (b)**

```
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
     |        |       |        |      |    
--------------------------------------------
a.   |   b.   |   c.  |    d   |   e  |   f

```



## Zadanie 2c 
### Narysuj graf zależności Diekerta (w postaci zminimalizowanej - bez krawędzi "przechodnich") dla słowa u.




