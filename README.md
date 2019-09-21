# regexVsArray
benchmark

Test RUN example>

------------------------------- Testovani pro 100 prvku: 
0002 ms - Davida řešení s mapou na cachovaní..<br/>
0000 ms - Davida řešení bez mapy..<br/>
0001 ms - Regex s připraveným patternem..<br/>
0000 ms - Další řešení pomocí substring..<br/>

------------------------------- Testovani pro 1000 prvku: 
0002 ms - Davida řešení s mapou na cachovaní..<br/>
0001 ms - Davida řešení bez mapy..<br/>
0006 ms - Regex s připraveným patternem..<br/>
0000 ms - Další řešení pomocí substring..<br/>

------------------------------- Testovani pro 10000 prvku: 
0009 ms - Davida řešení s mapou na cachovaní..<br/>
0004 ms - Davida řešení bez mapy..<br/>
0012 ms - Regex s připraveným patternem..<br/>
0002 ms - Další řešení pomocí substring..<br/>

------------------------------- Testovani pro 100000 prvku: 
0096 ms - Davida řešení s mapou na cachovaní..<br/>
0015 ms - Davida řešení bez mapy..<br/>
0066 ms - Regex s připraveným patternem..<br/>
0013 ms - Další řešení pomocí substring..<br/>

------------------------------- Testovani pro 1000000 prvku: 
0525 ms - Davida řešení s mapou na cachovaní..<br/>
0160 ms - Davida řešení bez mapy..<br/>
0269 ms - Regex s připraveným patternem..<br/>
0053 ms - Další řešení pomocí substring..<br/>

------------------------------- Testovani pro 10000000 prvku: 
7325 ms - Davida řešení s mapou na cachovaní..<br/>
1609 ms - Davida řešení bez mapy..<br/>
3096 ms - Regex s připraveným patternem..<br/>
1037 ms - Další řešení pomocí substring..<br/>
