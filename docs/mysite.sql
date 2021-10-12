-- scheme user
desc user;

INSERT 
  INTO user
VALUES (null,
		'김진영',
        'jin0kim326@gmail.com',
        '1234',
        'male',
        now());
        
SELECT no,
	   name
  FROM user
 WHERE email = ''
   AND password = '' 
        ;
        
Select * FROM user;        
	 