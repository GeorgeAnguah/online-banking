#! /bin/bash
./wait-for-it.sh mysql-db:3306 -t 60
java -Djava.security.egd=file:/dev/./urandom -cp .:lib/* com.onlinebanking.OnlineBankingApplication