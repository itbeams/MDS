# MDS
Multiple Data source Droid Persistence Framework

This framework is built using SQLite and SQLCipher - zetetic @see https://www.zetetic.net/sqlcipher/

Features:
- Supports secure and none secure data storage @entity level (based on cipherEntity=true/false)
- Annotation based persistence framework
- Supports auto sequesnce reset
- Supports object structure caching to avoid reflection (supports entity level so that develeper can define identified most frequently used entities as cached structure.
- Supports connection pooling

Sample Run:
1. mvn clean
2. mvn install

Download lates SQLCipher from https://www.zetetic.net/sqlcipher/ if you want to use secure storage otherwise no third party API needed

