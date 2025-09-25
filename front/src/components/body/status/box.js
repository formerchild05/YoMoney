import { CircleDollarSign, TrendingDown, Sheet, TrendingUp } from 'lucide-react';


export default function Box({ type, value   }) {
    function checkType(type) {
        let a = {
            name : undefined,
            icon : undefined,
        };
        if(type === "totalBalance") {
            a.name = "Total Balance";
            a.icon = <CircleDollarSign/>;

        } else if(type === "totalExpenses") {
            a.name = "Total Expenses";
            a.icon = <TrendingDown/>;    
        } else if(type === "totalIncome") {
            a.name = "Total Income";
            a.icon = <TrendingUp/>;
        } else if(type === "totalTransactions") {
            a.name = "Total Transactions";
            a.icon = <Sheet/>;

        }
        return a;
    }

    const T = checkType(type); 

    return (
        <div className="box" style={{flex : 1}}>
            <div className="top-box">
                <p style={{margin: 0, fontSize: 21}}>{T.name}</p>
                {T.icon}
            </div> 
            
            <div>
            {
            type === "totalBalance" ?
                <p style={{margin: 0}}> {Number(value).toLocaleString("vn-VN")} <u>đ</u></p> :   
            type === "totalIncome" ? 
                <p style={{color: "green", margin: 0}}>+  {Number(value).toLocaleString("vn-VN")} <u>đ</u></p> :
                type === "totalExpenses" ?
                <p style={{color: "red", margin: 0}}> -  {Number(value).toLocaleString("vn-VN")} <u>đ</u></p> :
                <p style={{margin: 0}}>{value}</p>
            }
            </div>
        </div>
    );
}