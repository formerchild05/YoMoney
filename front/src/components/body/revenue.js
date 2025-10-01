import FormDialog from './FormDialog';
import Status from './status/status';
import Transaction from './transaction/transaction';
import { useState, useEffect } from 'react';

export default function Revenue({transactions}) {

    const [open, setOpen] = useState(false);
    const [data, setData] = useState([]);

    // Update data when transactions prop changes - use backend format directly
    useEffect(() => {
        if (transactions && transactions.length > 0) {
            // Use the backend response format as-is
            setData(transactions);
        }
    }, [transactions]);

    const handleOpen = () => {
      setOpen(true);
    };
    
    const handleClose = () => {
      setOpen(false);
    }

    const handleSubmit = (newData) => {
      // const latestData = [...data, newData];
      // setData(latestData);
      handleClose();

      const apiRequestBody = {
        categoryName: newData.name,
        description: newData.description,
        amount: newData.amount,
        type: newData.type,
        createdAt: newData.createdAt
      };

      fetch(`${process.env.REACT_APP_BACKEND_URL}/api/transaction/create`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        body: JSON.stringify(apiRequestBody)
      })
      .then(response => response.json())
      .then(responseData => {  
        if (responseData.data) {
          console.log('message:', responseData.message);
          // Update local state with the new transaction including its ID from backend
          const localStateFormat = {
            transId: responseData.data.transactionId, // Use 'transId' to match Transaction component
            transactionId: responseData.transactionId, // Already a string from backend
            categoryName: responseData.data.categoryName,
            description: responseData.data.description,
            amount: responseData.data.amount,
            type: responseData.data.type,
            createdAt: responseData.data.createdAt
          };
          const latestData = [...data, localStateFormat];  // ✅ Now 'data' refers to state array
          setData(latestData);
        }
      })
      .catch(error => {
        console.error('Error creating transaction:', error);
      });
    }



    // send to backend transactionId as String, Spring will auto-convert it
    const handleDelete = (id) => {
      const latestData = data.filter(item => item.transId !== id);  // Direct string comparison
      setData(latestData);

      fetch(`${process.env.REACT_APP_BACKEND_URL}/api/transaction/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      }).then(response => response.json())
        .then(responseData => {
            if (responseData.message) {
              console.log(responseData.message);
            }
        })
      .catch(error => {
        console.error('Error deleting transaction:', error);
      });
    }

    const handleUpdate = (id, updatedItem) => {
      // Transform form data back to backend format
      console.log("Updating ID:", id, "with data:", updatedItem);
      


      // For local state update (includes transactionId for array management)
      const localStateFormat = {
        transactionId: id, // Already a string, no conversion needed
        categoryName: updatedItem.name,      
        description: updatedItem.description,
        amount: updatedItem.amount,
        type: updatedItem.type,
        createdAt: updatedItem.createdAt
      };

      // For API request (no transactionId in body, it's in the URL)
      const apiRequestBody = {
        categoryName: updatedItem.name,       
        description: updatedItem.description,
        amount: updatedItem.amount,
        type: updatedItem.type,
        createdAt: updatedItem.createdAt
      };


      
      const latestData = data.map(item => 
        item.transactionId === id ? localStateFormat : item  // Direct string comparison
      );
      setData(latestData);



      fetch(`${process.env.REACT_APP_BACKEND_URL}/api/transaction/update/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        body: JSON.stringify(apiRequestBody)  // Send only the fields to update
      });
    }


    return (
        <div style={{flex : 7, padding: 20, display: "flex", flexDirection: "column", gap: 20}}>

            <Status transactions={data}/>

            <button onClick={handleOpen}>Add Transaction</button>


            <FormDialog open={open} handleClose={handleClose} handleSubmit={handleSubmit} defaultData={null}/>


            <Transaction transactions={data} handleDelete={handleDelete} handleUpdate={handleUpdate}/>

        </div>
    );
}
