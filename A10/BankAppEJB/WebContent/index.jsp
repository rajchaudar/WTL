<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bank App</title>
    <style>
        body {
            font-family: Arial;
            background: #f0f2f5;
            text-align: center;
            padding-top: 60px;
        }
        .box {
            background: white;
            display: inline-block;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px #ccc;
        }
        input, select {
            padding: 8px;
            margin: 10px;
            width: 200px;
        }
        .btn {
            background: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }
        .btn:hover {
            background: #218838;
        }
    </style>
</head>
<body>
    <div class="box">
        <h2>🏦 Welcome to Simple Bank</h2>
        <form action="result.jsp" method="post">
            <input type="text" name="accNo" placeholder="Account No" required /><br/>
            <input type="number" step="0.01" name="amount" placeholder="Amount" required /><br/>
            <select name="action" required>
                <option value="deposit">Deposit</option>
                <option value="withdraw">Withdraw</option>
            </select><br/>
            <input type="submit" value="Submit" class="btn" />
        </form>
    </div>
</body>
</html>