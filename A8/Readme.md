
# Compile
```
javac -cp "/Users/shivrajchaudar/apache-tomcat-9.0.104/lib/servlet-api.jar:WebContent/WEB-INF/lib/*" \
-d WebContent/WEB-INF/classes \
src/com/login/*.java
```
# Make WAR File
```
cd WebContent
jar -cvf StudentLoginApp.war *
```
# Move WAR to Tomcat
```
mv StudentLoginApp.war /Users/shivrajchaudar/apache-tomcat-9.0.104/webapps/
```
# Start Tomcat
```
cd /Users/shivrajchaudar/apache-tomcat-9.0.104/bin
./startup.sh
```
# Open in Browser
http://localhost:8080/StudentLoginApp