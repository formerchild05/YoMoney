import "./status.css";
import Box from "./box.js";
export default function Status({ transactions }) {

    // Ensure transactions is an array and provide fallback
    const transactionList = transactions || [];

    function calculateTotalIncome() {
        let income = 0;
        transactionList.forEach(t => {
            if (t.type === "INCOME") {  // Backend uses uppercase "INCOME"
                income += t.amount;
            }
        });
        return income;
    }

    function calculateTotalExpenses() {
        let expense = 0;
        transactionList.forEach(t => {
            if (t.type === "EXPENSE") {  // Backend uses uppercase "EXPENSE"
                expense += t.amount;
            }
        });
        return expense;
    }

    function calculateTotalTransactions() {
        return transactionList.length;
    }

    
    return (
        <div className="status">
            <Box type="totalBalance" value={calculateTotalIncome() - calculateTotalExpenses()}/>
            <Box type="totalIncome" value={calculateTotalIncome()}/>
            <Box type="totalExpenses" value={calculateTotalExpenses()}/>
            <Box type="totalTransactions" value={calculateTotalTransactions()}/>
        </div>
    );
}