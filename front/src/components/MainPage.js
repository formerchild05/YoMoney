import Header from "./header/Header";
import Revenue from "./body/revenue";
import { useEffect } from "react";
import { useState } from "react";
import { Drawer, IconButton, Divider, Button, Link } from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import { useNavigate } from 'react-router-dom';
import LogoutIcon from '@mui/icons-material/Logout';
import SButton from "./But/SButton";
import GitHubIcon from '@mui/icons-material/GitHub';
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import FacebookIcon from '@mui/icons-material/Facebook';
import LoButton from "./But/LoButton";

export default function MainPage() {
    const navigate = useNavigate();
    const [userData, setUserData] = useState(null);
    const [transactions, setTransactions] = useState([]);
    const [drawerOpen, setDrawerOpen] = useState(false);

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/');
    };

    useEffect(() => {
        // This code runs ONCE when component mounts
        fetch(`${process.env.REACT_APP_BACKEND_URL}/api/auth/getUserdetails`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log('User profile data:', data);
            setUserData(data);
        })
        .catch(error => {
            console.error('Error fetching user profile:', error);
        });


        fetch(`${process.env.REACT_APP_BACKEND_URL}/api/transaction`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log('Transaction data:', data);
            setTransactions(data);
        })
        .catch(error => {
            console.error('Error fetching transaction data:', error);
        });


    }, []); // ← Empty array means "only run on mount"
    
    return (
        <div>
            <section>
                <IconButton size="large" onClick={() => setDrawerOpen(true)}>
                    <MenuIcon />
                </IconButton>
            </section>

            <Header user={userData} />
            <Revenue transactions={transactions} />


            <Drawer
                anchor="left"
                open={drawerOpen}
                onClose={() => setDrawerOpen(false)}
                sx={{
                    '& .MuiDrawer-paper': {
                        width: 300  // Set width to 300px (default is usually 240px)
                    }
                }}>

                {
                <div style ={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
                    <div style={{ flex: 4, alignItems: 'center', justifyContent: 'center', display: 'flex' }}>

                        <LoButton Logout={handleLogout} />
                    
                    </div>

                    <div style={{ flex: 4, alignItems: 'flex-end', justifyContent: 'center', display: 'flex' }}>
                        <SButton />
                    </div>

                    <div style={{ flex: 1, display: 'flex', justifyContent: 'center', alignItems: 'center', gap: '15px' }}>
                        <Link href="https://github.com/formerchild05" target="_blank" rel="noopener noreferrer">
                            <IconButton >
                                <GitHubIcon />
                            </IconButton>
                        </Link>
                        <Link href="https://www.linkedin.com/in/nam-bui-2843a5321/" target="_blank" rel="noopener noreferrer">
                            <IconButton>
                                <LinkedInIcon />
                            </IconButton>
                        </Link>
                        <Link href="https://facebook.com/manh.nam.681993/" target="_blank" rel="noopener noreferrer">
                            <IconButton>
                                <FacebookIcon />
                            </IconButton>
                        </Link>
                    </div>
                
                </div>
                }
            
            </Drawer>
        </div>
    );
}