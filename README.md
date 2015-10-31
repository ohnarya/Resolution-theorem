### Abstract

>This assignment is to implement automated reasoning with a resolution theorem prover that can be used to make inferences from a propositional knowledge base (KB), using proof by negation. Given a set of sentences KB, determine whether a query q, “the middle box must contain white tennis balls” is entailed, KB|=q? 

### Description of Sammy’s Sport Shop

>3 boxes of ball are delivered. However, the boxes have labeled incorrectly (Box1: white, Box2: yellow, Box3: both). The manufacturer tells that one box of each has been delivered. One ball is drawn from each box and observed like Box1: yellow, Box2: white, Box3: yellow. 

### Implementation

>The program has 3 part: initialization, resolution, and tracking the tree. Main part of the implementation is resolution part.  The program checks if clauses are resolvable. Then add the pairs to ‘candidates’ and iterate over candidates until it gets empty or empty clauses is generated. Once an empty clause is generated, it means that, the query which was negated and added to candidates, is interred. 


### Propositional logics

With given conditions, I defined propositional logics in “Sammy.kb” and “ss.kb”.
`Sammy.kb`
```
C1Y C2Y C3Y
C1W C2W C3W
C1B C2B C3B
C1Y C1W C1B
C2Y C2W C2B
C3Y C3W C3B

#L1W->-C1W
-L1W -C1W
-L1Y -C1Y
-L1B -C1B
-L2W -C2W
-L2Y -C2Y
-L2B -C3W
-L3Y -C3Y
-L3B -C3B

#C1W->-C2W -C3W
-C1W -C2W
-C1W -C3W
-C1Y -C2Y
-C1Y -C3Y
-C1B -C2B
-C1B -C3B

-C2W -C1W
-C2W -C3W
-C2Y -C1Y
-C2Y -C3Y
-C2B -C1B
-C2B -C3B

-C3W -C2W
-C3W -C1W
-C3Y -C2Y
-C3Y -C1Y
-C3B -C2B
-C3B -C1B

#L1W->-C1W
-L1W -C1W
-L2Y -C2Y
-L3B -C3B

#O1Y->C1Y C1B
-O1Y C1Y C1B
-O2W C2W C2B
-O3Y C3Y C3B

#add negated query
-C2W
```

### Result

The result of propositional KB, `Sammy.kb`

```
0: C1Y C2Y C3Y
1: C1W C2W C3W
2: C1B C2B C3B
3: C1Y C1W C1B
4: C2Y C2W C2B
5: C3Y C3W C3B
6: -L1W -C1W
7: -L1Y -C1Y
8: -L1B -C1B
9: -L2W -C2W
10: -L2Y -C2Y
11: -L2B -C3W
12: -L3Y -C3Y
13: -L3B -C3B
14: -C1W -C2W
15: -C1W -C3W
16: -C1Y -C2Y
17: -C1Y -C3Y
18: -C1B -C2B
19: -C1B -C3B
20: -C2W -C1W
21: -C2W -C3W
22: -C2Y -C1Y
23: -C2Y -C3Y
24: -C2B -C1B
25: -C2B -C3B
26: -C3W -C2W
27: -C3W -C1W
28: -C3Y -C2Y
29: -C3Y -C1Y
30: -C3B -C2B
31: -C3B -C1B
32: -L1W -C1W
33: -L2Y -C2Y
34: -L3B -C3B
35: -O1Y C1Y C1B
36: -O2W C2W C2B
37: -O3Y C3Y C3B
38: -C2W
iteration=39, queue size=109 , resolution on 1 and 38
resolving (C1W C2W C3W) and (-C2W)
39: C1W C3W generated
iteration=40, queue size=117 , resolution on 4 and 38
resolving (C2Y C2W C2B) and (-C2W)
40: C2B C2Y generated
iteration=41, queue size=126 , resolution on 36 and 38
resolving (-O2W C2W C2B) and (-C2W)
41: -O2W C2B generated
iteration=42, queue size=129 , resolution on 6 and 39
resolving (-L1W -C1W) and (C1W C3W)
42: C3W -L1W generated
iteration=43, queue size=133 , resolution on 25 and 41
resolving (-C2B -C3B) and (-O2W C2B)
43: -O2W -C3B generated
iteration=44, queue size=135 , resolution on 30 and 41
resolving (-C3B -C2B) and (-O2W C2B)
44: -O2W -C3B generated
iteration=45, queue size=134 , resolution on 11 and 42
resolving (-L2B -C3W) and (C3W -L1W)
45: -L2B -L1W generated
iteration=46, queue size=133 , resolution on 15 and 42
resolving (-C1W -C3W) and (C3W -L1W)
46: -C1W -L1W generated
iteration=47, queue size=135 , resolution on 21 and 42
resolving (-C2W -C3W) and (C3W -L1W)
47: -C2W -L1W generated
iteration=48, queue size=137 , resolution on 26 and 42
resolving (-C3W -C2W) and (C3W -L1W)
48: -C2W -L1W generated
iteration=49, queue size=136 , resolution on 27 and 42
resolving (-C3W -C1W) and (C3W -L1W)
49: -C1W -L1W generated
iteration=50, queue size=135 , resolution on 39 and 45
resolving (C1W C3W) and (-C1W -L1W)
50: C3W -L1W generated
iteration=51, queue size=134 , resolution on 11 and 39
resolving (-L2B -C3W) and (C1W C3W)
51: C1W -L2B generated
iteration=52, queue size=140 , resolution on 14 and 39
resolving (-C1W -C2W) and (C1W C3W)
52: -C2W C3W generated
iteration=53, queue size=147 , resolution on 6 and 47
resolving (-L1W -C1W) and (C1W -L2B)
53: -L2B -L1W generated
iteration=54, queue size=146 , resolution on 14 and 47
resolving (-C1W -C2W) and (C1W -L2B)
54: -C2W -L2B generated
iteration=55, queue size=148 , resolution on 15 and 47
resolving (-C1W -C3W) and (C1W -L2B)
55: -L2B -C3W generated
iteration=56, queue size=147 , resolution on 20 and 47
resolving (-C2W -C1W) and (C1W -L2B)
56: -C2W -L2B generated
iteration=57, queue size=146 , resolution on 27 and 47
resolving (-C3W -C1W) and (C1W -L2B)
57: -L2B -C3W generated
iteration=58, queue size=145 , resolution on 32 and 47
resolving (-L1W -C1W) and (C1W -L2B)
58: -L2B -L1W generated
iteration=59, queue size=144 , resolution on 45 and 47
resolving (-C1W -L1W) and (C1W -L2B)
59: -L2B -L1W generated
iteration=60, queue size=143 , resolution on 11 and 48
resolving (-L2B -C3W) and (-C2W C3W)
60: -L2B -C2W generated
iteration=61, queue size=145 , resolution on 15 and 48
resolving (-C1W -C3W) and (-C2W C3W)
61: -C1W -C2W generated
iteration=62, queue size=144 , resolution on 21 and 48
resolving (-C2W -C3W) and (-C2W C3W)
62: -C2W generated
iteration=63, queue size=143 , resolution on 26 and 48
resolving (-C3W -C2W) and (-C2W C3W)
63: -C2W generated
iteration=64, queue size=142 , resolution on 27 and 48
resolving (-C3W -C1W) and (-C2W C3W)
64: -C1W -C2W generated
iteration=65, queue size=141 , resolution on 15 and 39
resolving (-C1W -C3W) and (C1W C3W)
65: Empty clause generated
Success!! empty clause found
65: Empty [15,39]
 15: -C1W -C3W [input]
 39: C1W C3W [1,38]
  1: C1W C2W C3W [input]
38: -C2W [input]
```

The result of different propositional KB, `ss.kb`
```
0: C1Y C2Y C3Y
1: C1W C2W C3W
2: C1B C2B C3B
3: -L1W -C1W
4: -L1Y -C1Y
5: -L1B -C1B
6: -L2W -C2W
7: -L2Y -C2Y
8: -L2B -C2B
9: -L3W -C3W
10: -L3Y -C3Y
11: -L3B -C3B
12: -O1Y C1Y C1B
13: -O2W C2W C2B
14: -O3Y C3Y C3B
15: -O1W C1W C1B
16: -O3W C3W C3B
17: -O2Y C2Y C2B
18: -C1Y -C2Y
19: -C1Y -C3Y
20: -C2Y -C3Y
21: -C1B -C2B
22: -C1B -C3B
23: -C2B -C3B
24: -C1W -C2W
25: -C1W -C3W
26: -C2W -C3W
27: O1Y
28: O2W
29: O3Y
30: L1W
31: L2Y
32: L3B
33: -C2W
iteration=34, queue size=62 , resolution on 3 and 30
resolving (-L1W -C1W) and (L1W)
34: -C1W generated
iteration=35, queue size=63 , resolution on 11 and 32
resolving (-L3B -C3B) and (L3B)
35: -C3B generated
iteration=36, queue size=65 , resolution on 7 and 31
resolving (-L2Y -C2Y) and (L2Y)
36: -C2Y generated
iteration=37, queue size=66 , resolution on 1 and 33
resolving (C1W C2W C3W) and (-C2W)
37: C1W C3W generated
iteration=38, queue size=71 , resolution on 34 and 37
resolving (-C1W) and (C1W C3W)
38: C3W generated
iteration=39, queue size=73 , resolution on 9 and 38
resolving (-L3W -C3W) and (C3W)
39: -L3W generated
iteration=40, queue size=72 , resolution on 25 and 38
resolving (-C1W -C3W) and (C3W)
40: -C1W generated
iteration=41, queue size=71 , resolution on 26 and 38
resolving (-C2W -C3W) and (C3W)
41: -C2W generated
iteration=42, queue size=70 , resolution on 24 and 37
resolving (-C1W -C2W) and (C1W C3W)
42: -C2W C3W generated
iteration=43, queue size=74 , resolution on 14 and 35
resolving (-O3Y C3Y C3B) and (-C3B)
43: C3Y -O3Y generated
iteration=44, queue size=77 , resolution on 29 and 41
resolving (O3Y) and (C3Y -O3Y)
44: C3Y generated
iteration=45, queue size=79 , resolution on 10 and 42
resolving (-L3Y -C3Y) and (C3Y)
45: -L3Y generated
iteration=46, queue size=78 , resolution on 19 and 42
resolving (-C1Y -C3Y) and (C3Y)
46: -C1Y generated
iteration=47, queue size=79 , resolution on 20 and 42
resolving (-C2Y -C3Y) and (C3Y)
47: -C2Y generated
iteration=48, queue size=78 , resolution on 12 and 44
resolving (-O1Y C1Y C1B) and (-C1Y)
48: C1B -O1Y generated
iteration=49, queue size=81 , resolution on 27 and 45
resolving (O1Y) and (C1B -O1Y)
49: C1B generated
iteration=50, queue size=83 , resolution on 5 and 46
resolving (-L1B -C1B) and (C1B)
50: -L1B generated
iteration=51, queue size=82 , resolution on 21 and 46
resolving (-C1B -C2B) and (C1B)
51: -C2B generated
iteration=52, queue size=84 , resolution on 22 and 46
resolving (-C1B -C3B) and (C1B)
52: -C3B generated
iteration=53, queue size=83 , resolution on 0 and 36
resolving (C1Y C2Y C3Y) and (-C2Y)
53: C3Y C1Y generated
iteration=54, queue size=88 , resolution on 44 and 49
resolving (-C1Y) and (C3Y C1Y)
54: C3Y generated
iteration=55, queue size=87 , resolution on 13 and 48
resolving (-O2W C2W C2B) and (-C2B)
55: C2W -O2W generated
iteration=56, queue size=92 , resolution on 28 and 50
resolving (O2W) and (C2W -O2W)
56: C2W generated
iteration=57, queue size=96 , resolution on 33 and 51
resolving (-C2W) and (C2W)
57: Empty clause generated
Success!! empty clause found
57: Empty [33,51]
 33: -C2W [input]
 56: C2W [28,50]
  28: O2W [input]
  55: C2W -O2W [13,48]
   13: -O2W C2W C2B [input]
   51: -C2B [21,46]
    21: -C1B -C2B [input]
    49: C1B [27,45]
     27: O1Y [input]
     48: C1B -O1Y [12,44]
      12: -O1Y C1Y C1B [input]
      46: -C1Y [19,42]
       19: -C1Y -C3Y [input]
       44: C3Y [29,41]
        29: O3Y [input]
        43: C3Y -O3Y [14,35]
         14: -O3Y C3Y C3B [input]
         35: -C3B [11,32]
          11: -L3B -C3B [input]
          32: L3B [input]
```
