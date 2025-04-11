package com.bank;

public class BankBean implements BankInterface {
    private double balance;

    public BankBean(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String deposit(double amount) {
        if (amount <= 0) {
            return "⚠️ Deposit amount must be greater than ₹0.";
        }
        balance += amount;
        return "Deposited ₹" + String.format("%.2f", amount) + ". New Balance: ₹" + String.format("%.2f", balance);
    }

    @Override
    public String withdraw(double amount) {
        if (amount <= 0) {
            return "⚠️ Withdrawal amount must be greater than ₹0.";
        }
        if (amount > balance) {
            return "❌ Withdrawal Failed. Insufficient Balance: ₹" + String.format("%.2f", balance);
        }
        balance -= amount;
        return "Withdrawn ₹" + String.format("%.2f", amount) + ". Remaining Balance: ₹" + String.format("%.2f", balance);
    }
}