import "./trans.css";
import { useState } from "react";
import FormDialog from "../FormDialog";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

export default function Transaction({ transactions, handleDelete, handleUpdate }) {
    const [openUpdate, setOpenUpdate] = useState(false);
    const [selectedTransactionId, setSelectedTransactionId] = useState(null);
    
    // Ensure transactions is an array and provide fallback
    const transactionList = transactions || [];

    const handleOpenUpdate = (id) => {
        setOpenUpdate(true);
        setSelectedTransactionId(id);
    };

    const handleCloseUpdate = () => {
        setOpenUpdate(false);
        setSelectedTransactionId(null);
    };

    const onUpdate= (id, updatedItem) => {
        handleUpdate(id, updatedItem);
        handleCloseUpdate();
    }

    function getDefaultData(Id) {
        return transactionList.find(item => item.transId === Id) || null;
    }
    return (
        <div className="mid">

        
            <ul className="container" style={{ listStyleType: "none"}}>
                <p>Your History</p>
                {transactionList.map((t, index) => (
                    <li className="item" key={index}>   

                        {/* <p>{index}</p> */}


                        <div style={{ display: "flex", flexDirection: "row", alignItems: "center", justifyContent: "space-between", gap: "10px" }}>
                                
                                <div style={{ display: "flex", flexDirection: "column", gap: "5px" }}>
                                    <h3 style={{ margin: 0 }}>{t.categoryName}</h3>
                                    <p style={{ margin: 0 }}>Date : {t.createdAt}</p>                            
                                </div>     

                                <p>{t.type}</p>        
                        </div>

{/*                     <p>{t.description}</p> */}

                        <div style={{ display: "flex", flexDirection: "row", alignItems: "center", justifyContent: "space-between", gap: "5px" }}>
                            
                            {
                                t.type === "INCOME" ?
                                <p style={{ color: "green", margin: 0 }}>+ {Number(t.amount).toLocaleString("vi-VN")} <u>đ</u></p> :
                                <p style={{ color: "red", margin: 0 }}>- {Number(t.amount).toLocaleString("vi-VN")} <u>đ</u></p>
                            }


                            <button className="itemButton" onClick={() => handleOpenUpdate(t.transId)}>
                                <EditIcon sx={{ fontSize: "5" }} />
                                </button>

                            <button className="itemButton" onClick={() => handleDelete(t.transId)}>
                                <DeleteIcon sx={{ fontSize: "5" }} />
                            </button>
                        </div>

                    </li>

                ))}


            </ul>
                <FormDialog open={openUpdate} handleClose={handleCloseUpdate} 
                handleSubmit={(updatedItem) => onUpdate(selectedTransactionId, updatedItem)} 
                defaultData={getDefaultData(selectedTransactionId)}/>
        </div>


    );
}