import React from 'react'
import { clearLocalStorage } from '../../Utility/LocalStorageHelper';
import { useNavigate } from 'react-router-dom';
import { AppBar, Stack, Toolbar, Button } from '@mui/material';

const MenuBar = () => {
    const navigate = useNavigate();

    function handleLogoutClick(){
        console.log("loggout click")
        clearLocalStorage();    
        navigate("/");
    }

    return (
        <div>
            <AppBar position="static">
                <Toolbar style={{justifyContent:'flex-end'}}>
                    <Stack direction='row'>
                        <Button color='inherit' onClick={handleLogoutClick}>Logout</Button>
                    </Stack>
                </Toolbar>
            </AppBar>
      </div>   
    )
}

export default MenuBar
