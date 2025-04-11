<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
<body>
    <h2>Login Page</h2>
    <html:form action="/login">
        Username: <html:text property="username"/><br/>
        Password: <html:password property="password"/><br/>
        <html:submit value="Login"/>
    </html:form>
</body>
</html>