#Compilation
```
javac -cp "/Users/shivrajchaudar/apache-tomcat-9.0.104/lib/servlet-api.jar:WebContent/WEB-INF/lib/*" \
-d WebContent/WEB-INF/classes \
src/com/bank/*.java
```

#Making Jar File
```
cd WebContent
jar -cvf SimpleBankApp.war *
```

#Moving Jar File To Wildify
```
mv SimpleBankApp.war /Users/Shivrajchaudar/wildfly-36.0.0.Final/standalone/deployments
```