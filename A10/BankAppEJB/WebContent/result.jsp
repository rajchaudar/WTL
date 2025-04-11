<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.bank.BankBean" %>
<%@ page import="java.util.*, java.text.*" %>

<%
    DecimalFormat df = new DecimalFormat("0.00");
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    HttpSession sessionObj = request.getSession();
    String result = "";
    int accNo = 0;
    double amount = 0.0;
    String action = request.getParameter("action");

    try {
        accNo = Integer.parseInt(request.getParameter("accNo"));
        amount = Double.parseDouble(request.getParameter("amount"));

        Map<Integer, BankBean> accountMap = (Map<Integer, BankBean>) sessionObj.getAttribute("accountMap");
        if (accountMap == null) accountMap = new HashMap<>();

        BankBean bank = accountMap.get(accNo);
        if (bank == null) {
            bank = new BankBean(1000); // Default initial balance
            accountMap.put(accNo, bank);
        }

        // Transaction logic with validation
        if (amount <= 0) {
            result = "❌ Amount must be greater than ₹0.";
        } else if ("deposit".equalsIgnoreCase(action)) {
            result = bank.deposit(amount);
        } else if ("withdraw".equalsIgnoreCase(action)) {
            result = bank.withdraw(amount);
        } else {
            result = "❌ Invalid transaction type.";
        }

        sessionObj.setAttribute("accountMap", accountMap);

        // Transaction history tracking
        Map<Integer, List<String>> historyMap = (Map<Integer, List<String>>) sessionObj.getAttribute("historyMap");
        if (historyMap == null) historyMap = new HashMap<>();
        
        List<String> history = historyMap.getOrDefault(accNo, new ArrayList<>());
        
        // ✅ Add entry ONLY if successful
        if (result.startsWith("Deposited") || result.startsWith("Withdrawn")) {
            history.add(sdf.format(new Date()) + " | " + action.toUpperCase() + " ₹" + df.format(amount));
        }
        
        historyMap.put(accNo, history);
        sessionObj.setAttribute("historyMap", historyMap);

    } catch (NumberFormatException e) {
        result = "❌ Invalid input format. Please enter numeric values for Account Number and Amount.";
    } catch (Exception e) {
        result = "❌ Error processing transaction: " + e.getMessage();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            text-align: center;
            padding-top: 50px;
        }
        .card {
            background: #fff;
            display: inline-block;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
        }
        .btn {
            margin-top: 20px;
            display: inline-block;
            padding: 10px 20px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        ul {
            text-align: left;
            margin-top: 20px;
            padding: 0;
            list-style: none;
        }
        li {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <div class="card">
        <h2>✅ Transaction Status</h2>
        <p><strong><%= result %></strong></p>

        <% if (result.startsWith("Deposited") || result.startsWith("Withdrawn")) { %>
            <p>Account No: <%= accNo %></p>
            <p>Current Balance: ₹<%= df.format(((Map<Integer, BankBean>) sessionObj.getAttribute("accountMap")).get(accNo).getBalance()) %></p>

            <h4>🧾 Transaction History</h4>
            <ul>
                <% for (String entry : ((Map<Integer, List<String>>) sessionObj.getAttribute("historyMap")).get(accNo)) { %>
                    <li><%= entry %></li>
                <% } %>
            </ul>
        <% } %>

        <a class="btn" href="index.jsp">Make Another Transaction</a>
    </div>
</body>
</html>