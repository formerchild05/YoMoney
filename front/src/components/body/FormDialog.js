import { Dialog, DialogActions, DialogContent, DialogTitle, Button, TextField } from '@mui/material';
import { ToggleButton, ToggleButtonGroup } from '@mui/material';
import { Box, Input } from '@mui/material';
import { useState, useEffect } from 'react';

export default function FormDialog({ open, handleClose, handleSubmit, defaultData}) {
    const today = new Date().toISOString().split("T")[0]; 

    const initData = defaultData === null ?
            {
                name: '',
                category: '',
                amount: '',
                type: '',
                createdate: today
            } 
            : 
            {
                name : defaultData.name,
                category : defaultData.category,
                amount : defaultData.amount,
                type : defaultData.type,
                createdate : defaultData.createdate
            }
    
        if(defaultData !== null) {
            {console.log("default ", defaultData)}
            {console.log("init ", initData)}
        }
    const [formData, setFormData] = useState(initData);


    useEffect(() => {
        if (defaultData && open) {
            setFormData({
                name : defaultData.name,
                category : defaultData.category,
                amount : defaultData.amount,
                type : defaultData.type,
                createdate : defaultData.createdate
            });
        } else if (open && !defaultData) {
            setFormData(initData);
        }
    }, [defaultData, open]);

    function onSubmit(e) {
        e.preventDefault();
        
        // parse amount to number
        const parse = {...formData, amount: parseFloat(formData.amount) || 0};

        handleSubmit(parse);
    }

    return (
        
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle style={{ textAlign: "center" }}>add new</DialogTitle>
            <DialogContent>
                <form onSubmit={onSubmit}>
                    <TextField              
                        autoFocus
                        required
                        margin="dense"
                        label="Transaction Name"
                        value={formData.name}
                        fullWidth
                        variant="outlined"
                        onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                    />
                    <TextField
                        autoFocus
                        required
                        margin="dense"
                        label="Transaction Category"
                        value={formData.category}
                        fullWidth
                        variant="outlined"
                        onChange={(e) => setFormData({ ...formData, category: e.target.value })}
                    />

                    <TextField
                        autoFocus
                        required
                        margin="dense"
                        label="Transaction Amount"
                        value={formData.amount}
                        fullWidth
                        variant="outlined"
                        onChange={(e) => {
                            const value = e.target.value;
                            // Only allow numbers and decimal point
                            if (/^[0-9]*\.?[0-9]*$/.test(value) || value === '') {
                                setFormData({ ...formData, amount: value });
                            }
                        }}
                    />
                    <ToggleButtonGroup style={{ display: "flex", justifyContent: "center", gap: "150px"}}
                        value={formData.type}
                        exclusive
                        onChange={(e, newType) => setFormData({ ...formData, type: newType })}
                        aria-label='Transaction Type'
                    >
                        <ToggleButton size='small' value="income">Income</ToggleButton>
                        <ToggleButton size='small' value="expense">Expense</ToggleButton>
                    </ToggleButtonGroup>

                    <Box display="flex" justifyContent="center" mt={2}>
                    <Input
                        type="date"
                        value={formData.createdate || today}
                        onChange={(e) => setFormData({ ...formData, createdate: e.target.value })}
                    />
                    </Box>
                    <DialogActions>
                        <Button onClick={handleClose}>Cancel</Button>
                        <Button type='submit'>Submit</Button>
                    </DialogActions>
                
                </form>

            </DialogContent>
        </Dialog>
    );
};