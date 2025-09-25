import FormDialog from './FormDialog';
import Status from './status/status';
import Transaction from './transaction/transaction';
import { useState } from 'react';

export default function Revenue() {

    const testing = [
  {
    "id": "1",
    "name": "Mua sách",
    "category": "Thanh toán tại nhà sách Fahasa",
    "amount": 150000,
    "type": "expense",
    "createdate": "2025-09-01"
  },
  {
    "id": "2",
    "name": "Lương tháng 9",
    "category": "Chuyển khoản từ công ty ABC",
    "amount": 12000000,
    "type": "income",
    "createdate": "2025-09-05"
  },
  {
    "id": "3",
    "name": "Đi ăn",
    "category": "Ăn tối tại nhà hàng Kichi Kichi",
    "amount": 500000,
    "type": "expense",
    "createdate": "2025-09-08"
  },
  {
    "id": "4",
    "name": "Bán laptop cũ",
    "category": "Giao dịch qua chợ tốt",
    "amount": 7000000,
    "type": "income",
    "createdate": "2025-09-10"
  },
  {
    "id": "5",
    "name": "Mua vé máy bay",
    "category": "Vé đi Đà Nẵng khứ hồi",
    "amount": 2500000,
    "type": "expense",
    "createdate": "2025-09-15"
  }
  ]

    const [open, setOpen] = useState(false);
    const [data, setData] = useState(testing);

    const handleOpen = () => {
      setOpen(true);
    };
    
    const handleClose = () => {
      setOpen(false);
    }

    const handleSubmit = (newData) => {
      const latestData = [...data, newData];
      setData(latestData);
      handleClose();
    }

    const handleDelete = (id) => {
      const latestData = data.filter(item => item.id !== id);
      setData(latestData);
    }

    const handleUpdate = (id, updatedItem) => {
      const latestData = data.map(item => item.id === id ? { ...updatedItem, id }: item);
      setData(latestData);
    }

    return (
        <div style={{flex : 7, padding: 20, display: "flex", flexDirection: "column", gap: 20}}>
            {/* 
            status
            transaction
             */}
            <Status transactions={data}/>

            <button onClick={handleOpen}>Add Transaction</button>


            <FormDialog open={open} handleClose={handleClose} handleSubmit={handleSubmit} defaultData={null}/>


            <Transaction transactions={data} handleDelete={handleDelete} handleUpdate={handleUpdate}/>

        </div>
    );
}
