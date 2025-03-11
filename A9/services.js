app.service("AuthService", function() {
    var users = [];

    this.getUsers = function() {
        return users;
    };

    this.register = function(user) {
        let exists = users.some(u => u.username === user.username);
        if (exists) {
            return "Username already exists!";
        } else {
            users.push(user);
            return "Registration successful!";
        }
    };

    this.login = function(user) {
        let foundUser = users.find(u => u.username === user.username && u.password === user.password);
        if (foundUser) {
            return { success: true, message: "Login successful! Welcome, " + foundUser.firstName };
        } else {
            return { success: false, message: "Invalid username or password." };
        }
    };
});