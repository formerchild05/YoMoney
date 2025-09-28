import { useState } from 'react';
import { TextField, Button, Paper, Typography, Box } from '@mui/material';

export default function Login() {
    const [serverError, setServerError] = useState('');
    const [isSignUp, setIsSignUp] = useState(false);
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        password: '',
        confirmPassword: ''
    });
    const [errors, setErrors] = useState({});

    const handleSubmit = (e) => {
        e.preventDefault();
        
        if (isSignUp) {
            // Sign up validation
            const newErrors = {};
            
            if (formData.password !== formData.confirmPassword) {
                newErrors.confirmPassword = "Passwords don't match";
            }
            
            if (formData.password.length < 6) {
                newErrors.password = "Password must be at least 6 characters";
            }

            if (Object.keys(newErrors).length > 0) {
                setErrors(newErrors);
                return;
            }

            setErrors({});
            console.log('Sign up attempt:', formData);
            // Add your sign up logic here

            const fullname = formData.firstName + " " + formData.lastName;
            fetch(`${process.env.REACT_APP_BACKEND_URL}/api/auth/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    fullName: fullname,
                    email: formData.email,
                    username: formData.username,
                    password: formData.password
                })
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                if (data.error) {
                    setServerError(data.error);
                }
            })
            .catch(error => {

                console.error('Error:', error);
            });

        } else {
            console.log('Login attempt:', formData);
            // Add your login logic here

            fetch(`${process.env.REACT_APP_BACKEND_URL}/api/auth/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: formData.username,
                    password: formData.password
                })
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                if (data.error) {
                    setServerError(data.error);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
    };

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
        
        // Clear error when user starts typing
        if (errors[e.target.name]) {
            setErrors({
                ...errors,
                [e.target.name]: ''
            });
            setServerError('');
        }
    };

    const toggleMode = () => {
        setIsSignUp(!isSignUp);
        setFormData({
            firstName: '',
            lastName: '',
            username: '',
            email: '',
            password: '',
            confirmPassword: ''
        });
        setErrors({});
        setServerError(''); 
    };

    return (
        <Box 
            display="flex" 
            justifyContent="center" 
            alignItems="center" 
            minHeight="100vh"
            bgcolor="#f5f5f5"
        >
            <Paper 
                elevation={3} 
                sx={{ 
                    padding: 4, 
                    width: '100%', 
                    maxWidth: isSignUp ? 450 : 400,
                    borderRadius: 2
                }}
            >
                <Typography variant="h4" align="center" gutterBottom>
                    {isSignUp ? 'Sign Up' : 'Login'}
                </Typography>
                
                <form onSubmit={handleSubmit}>
                    {isSignUp && (
                        <Box display="flex" gap={2}>
                            <TextField
                                fullWidth
                                label="First Name"
                                name="firstName"
                                value={formData.firstName}
                                onChange={handleChange}
                                margin="normal"
                                required
                                variant="outlined"
                            />
                            
                            <TextField
                                fullWidth
                                label="Last Name"
                                name="lastName"
                                value={formData.lastName}
                                onChange={handleChange}
                                margin="normal"
                                required
                                variant="outlined"
                            />
                        </Box>
                    )}
                    
                    {isSignUp && (
                        <TextField
                        fullWidth
                        label="Email"
                        name="email"
                        type="email"
                        value={formData.email}
                        onChange={handleChange}
                        margin="normal"
                        required
                        variant="outlined"
                        />
                    )}
                    
                    <TextField
                        fullWidth
                        label="Username"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        margin="normal"
                        required
                        variant="outlined"
                    />
                    
                    <TextField
                        fullWidth
                        label="Password"
                        name="password"
                        type="password"
                        value={formData.password}
                        onChange={handleChange}
                        margin="normal"
                        required
                        variant="outlined"
                        error={!!errors.password}
                        helperText={errors.password}
                    />
                    
                    {isSignUp && (
                        <TextField
                            fullWidth
                            label="Confirm Password"
                            name="confirmPassword"
                            type="password"
                            value={formData.confirmPassword}
                            onChange={handleChange}
                            margin="normal"
                            required
                            variant="outlined"
                            error={!!errors.confirmPassword}
                            helperText={errors.confirmPassword}
                        />
                    )}
                    
                    {serverError && (
                        <Typography variant="body2" color="error" align="center" sx={{ mt: 1 }}>
                            {serverError}
                        </Typography>
                    )}


                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2, py: 1.5 }}
                        size="large"
                    >
                        {isSignUp ? 'Create Account' : 'Sign In'}
                    </Button>
                </form>
                
                <Typography variant="body2" align="center" sx={{ mt: 2 }}>
                    {isSignUp ? 'Already have an account?' : "Don't have an account?"} 
                    <Button variant="text" size="small" onClick={toggleMode}>
                        {isSignUp ? 'Sign In' : 'Sign Up'}
                    </Button>
                </Typography>
            </Paper>
        </Box>
    );
}
