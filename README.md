# regexVsArray
benchmark

Test RUN example>

------------------------------- Testovani pro 100 prvku:<br/> 
0002 ms - Davida řešení s mapou na cachovaní.<br/>
0000 ms - Davida řešení bez mapy.<br/>
0001 ms - Regex s připraveným patternem.<br/>
0000 ms - Další řešení pomocí substring.<br/>

------------------------------- Testovani pro 1000 prvku:<br/> 
0001 ms - Davida řešení s mapou na cachovaní.<br/>
0001 ms - Davida řešení bez mapy.<br/>
0004 ms - Regex s připraveným patternem.<br/>
0000 ms - Další řešení pomocí substring.<br/>

------------------------------- Testovani pro 10000 prvku:<br/> 
0010 ms - Davida řešení s mapou na cachovaní.<br/>
0006 ms - Davida řešení bez mapy.<br/>
0013 ms - Regex s připraveným patternem.<br/>
0002 ms - Další řešení pomocí substring.<br/>

------------------------------- Testovani pro 100000 prvku:<br/> 
0104 ms - Davida řešení s mapou na cachovaní.<br/>
0015 ms - Davida řešení bez mapy.<br/>
0059 ms - Regex s připraveným patternem.<br/>
0009 ms - Další řešení pomocí substring.<br/>

------------------------------- Testovani pro 1000000 prvku:<br/> 
0667 ms - Davida řešení s mapou na cachovaní.<br/>
0169 ms - Davida řešení bez mapy.<br/>
0325 ms - Regex s připraveným patternem.<br/>
0081 ms - Další řešení pomocí substring.<br/>

------------------------------- Testovani pro 10000000 prvku:<br/> 
6740 ms - Davida řešení s mapou na cachovaní.<br/>
1651 ms - Davida řešení bez mapy.<br/>
3174 ms - Regex s připraveným patternem.<br/>
1064 ms - Další řešení pomocí substring.<br/>
