import "./status.css";
import Box from "./box.js";
export default function Status({ transactions }) {

    const arr = JSON.parse(JSON.stringify(transactions));

    function calculateTotalIncome(transactions) {
        let income = 0;
        arr.forEach(t => {
            if (t.type === "income") {
                income += t.amount;
            }
        });
        return income;
    }

    function calculateTotalExpenses(transactions) {
        let expense = 0;
        arr.forEach(t => {
            if (t.type === "expense") {
                expense += t.amount;
            }
        });
        return expense;
    }

    function calculateTotalTransactions(transactions) {
        return arr.length;
    }

    
    return (
        <div className="status">
            <Box type="totalBalance" value={calculateTotalIncome(transactions) - calculateTotalExpenses(transactions)}/>
            <Box type="totalIncome" value={calculateTotalIncome(transactions)}/>
            <Box type="totalExpenses" value={calculateTotalExpenses(transactions)}/>
            <Box type="totalTransactions" value={calculateTotalTransactions(transactions)}/>
        </div>
    );
}