<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "EmployeeDB";
$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if (isset($_POST['insert'])) {
    $first_name = $_POST['first_name'];
    $last_name = $_POST['last_name'];
    $email = $_POST['email'];
    $position = $_POST['position'];
    $sql = "INSERT INTO Employees (first_name, last_name, email, position) VALUES ('$first_name', '$last_name', '$email', '$position')";
    $conn->query($sql);
}

if (isset($_POST['update'])) {
    $id = $_POST['id'];
    $first_name = $_POST['first_name'];
    $last_name = $_POST['last_name'];
    $email = $_POST['email'];
    $position = $_POST['position'];

    $sql = "UPDATE Employees SET first_name='$first_name', last_name='$last_name', email='$email', position='$position' WHERE id=$id";
    $conn->query($sql);
}

if (isset($_GET['delete'])) {
    $id = $_GET['delete'];
    $sql = "DELETE FROM Employees WHERE id=$id";
    $conn->query($sql);
}

$result = $conn->query("SELECT * FROM Employees");
?>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Management</title>
</head>
<body>
    <h2>Employee Management</h2>
    <form method="post">
        <input type="text" name="first_name" placeholder="First Name" required>
        <input type="text" name="last_name" placeholder="Last Name" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="text" name="position" placeholder="Position" required>
        <button type="submit" name="insert">Add Employee</button>
    </form>

    <h3>Employee List</h3>
    <table border="1">
    <tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Position</th><th>Actions</th></tr>
    <?php while ($row = $result->fetch_assoc()): ?>
        <tr>
            <td><?= $row['id'] ?></td>
            <td><?= $row['first_name'] ?></td>
            <td><?= $row['last_name'] ?></td>
            <td><?= $row['email'] ?></td>
            <td><?= $row['position'] ?></td>
            <td>
                <button onclick="openUpdatePopup(<?= $row['id'] ?>, '<?= $row['first_name'] ?>', '<?= $row['last_name'] ?>', '<?= $row['email'] ?>', '<?= $row['position'] ?>')">Edit</button>
                <a href="?delete=<?= $row['id'] ?>">Delete</a>
            </td>
        </tr>
    <?php endwhile; ?>
    </table>
    <div id="updateModal" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background: white; padding: 20px; border: 1px solid black;">
    <h3>Update Employee Details</h3>
    <form method="post">
        <input type="hidden" id="update_id" name="id">
        <label>First Name:</label>
        <input type="text" id="update_first_name" name="first_name" required><br>

        <label>Last Name:</label>
        <input type="text" id="update_last_name" name="last_name" required><br>

        <label>Email:</label>
        <input type="email" id="update_email" name="email" required><br>

        <label>Position:</label>
        <input type="text" id="update_position" name="position" required><br>

        <button type="submit" name="update">Update</button>
        <button type="button" onclick="closeUpdatePopup()">Cancel</button>
    </form>
    </div>          


</body>
<script>
    function openUpdatePopup(id, firstName, lastName, email, position) {
        document.getElementById('update_id').value = id;
        document.getElementById('update_first_name').value = firstName;
        document.getElementById('update_last_name').value = lastName;
        document.getElementById('update_email').value = email;
        document.getElementById('update_position').value = position;
        document.getElementById('updateModal').style.display = 'block';
    }

    function closeUpdatePopup() {
        document.getElementById('updateModal').style.display = 'none';
    }
</script>
</html>