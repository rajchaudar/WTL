# Compilation
```
javac -cp "/Users/shivrajchaudar/apache-tomcat-9.0.104/lib/servlet-api.jar:/Users/Shivrajchaudar/Desktop/WTL/A5/EbookShop/WebContent/WEB-INF/lib/mysql-connector-j-9.2.0.jar" \
-d /Users/Shivrajchaudar/Desktop/WTL/A5/EbookShop/WebContent/WEB-INF/classes \
/Users/Shivrajchaudar/Desktop/WTL/A5/EbookShop/src/com/ebooks/*.java
```


#.War File Creation
```
cd /Users/Shivrajchaudar/Desktop/WTL/A5/EbookShop/WebContent
jar -cvf EbookShop.war *
```

#Move to The Tomcat Webapps
```
mv EbookShop.war /Users/shivrajchaudar/apache-tomcat-9.0.104/webapps/
```

#Run Tomcat
```
cd /Users/shivrajchaudar/apache-tomcat-9.0.104/bin
./startup.sh
```

#Go To Browser
http://localhost:8080/EbookShop/