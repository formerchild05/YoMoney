import "./trans.css";
import { useState } from "react";
import FormDialog from "../FormDialog";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

export default function Transaction({ transactions, handleDelete, handleUpdate }) {
    const [openUpdate, setOpenUpdate] = useState(false);
    const [selectedTransactionId, setSelectedTransactionId] = useState(null);

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
        return transactions.find(item => item.id === Id) || null;
    }
    return (
        <div className="mid">

        
            <ul className="container" style={{ listStyleType: "none"}}>
                <p>Your History</p>
                {transactions.map((t, index) => (
                    <li className="item" key={index}>   

                        {/* <p>{index}</p> */}


                        <div style={{ display: "flex", flexDirection: "row", alignItems: "center", justifyContent: "space-between", gap: "10px" }}>
                                
                                <div style={{ display: "flex", flexDirection: "column", gap: "5px" }}>
                                    <h3 style={{ margin: 0 }}>{t.name}</h3>
                                    <p style={{ margin: 0 }}>Date : {t.createdate}</p>                            
                                </div>     

                                <p>{t.type}</p>        
                        </div>

{/*                     <p>{t.description}</p> */}

                        <div style={{ display: "flex", flexDirection: "row", alignItems: "center", justifyContent: "space-between", gap: "5px" }}>
                            
                            {
                                t.type === "income" ?
                                <p style={{ color: "green", margin: 0 }}>+ {Number(t.amount).toLocaleString("vi-VN")} <u>đ</u></p> :
                                <p style={{ color: "red", margin: 0 }}>- {Number(t.amount).toLocaleString("vi-VN")} <u>đ</u></p>
                            }


                            <button className="itemButton" onClick={() => handleOpenUpdate(t.id)}>
                                <EditIcon sx={{ fontSize: "5" }} />
                                </button>


                            <button className="itemButton" onClick={() => handleDelete(t.id)}>
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