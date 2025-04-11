#Compile
```
javac -cp "/Users/shivrajchaudar/apache-tomcat-9.0.104/lib/servlet-api.jar:/Users/shivrajchaudar/Desktop/WTL/A6/StudentApp/lib/mysql-connector-j-9.2.0.jar" \
-d /Users/shivrajchaudar/Desktop/WTL/A6/StudentApp/WebContent/WEB-INF/classes \
/Users/shivrajchaudar/Desktop/WTL/A6/StudentApp/src/com/student/*.java
```

#Make Jar File 
```
cd /Users/shivrajchaudar/Desktop/WTL/A6/StudentApp/WebContent
jar -cvf StudentApp.war *
```

#Move to Tomcat
```
mv StudentApp.war /Users/shivrajchaudar/apache-tomcat-9.0.104/webapps/
```

#Start Tomcat
```
cd /Users/shivrajchaudar/apache-tomcat-9.0.104/bin
./startup.sh
```

#Browser Url 
http://localhost:8080/StudentApp/