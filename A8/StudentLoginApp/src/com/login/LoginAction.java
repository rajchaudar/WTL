package com.login;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import java.sql.*;

public class LoginAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        LoginForm loginForm = (LoginForm) form;
        String user = loginForm.getUsername();
        String pass = loginForm.getPassword();

        if (isValidUser(user, pass)) {
            return mapping.findForward("success");
        } else {
            return mapping.findForward("failure");
        }
    }

    // ✅ Method moved outside 'execute' method
    public boolean isValidUser(String username, String password) {
        boolean valid = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb", "root", "raj@1415"
            );
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            valid = rs.next();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }
}