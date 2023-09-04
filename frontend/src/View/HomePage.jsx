import React from 'react'
import { Button, Box } from '@mui/material';
import MenuBar from '../Components/MenuBar/MenuBar';
import { useNavigate } from 'react-router-dom';
const HomePage = () => {

  const navigate = useNavigate();

  function handleRestrictedClick(){
    navigate("/restricted");
  }

  return (
    <div>
      <MenuBar></MenuBar>
      <h1>Welcome</h1>
      <Box display="flex" justifyContent="center" alignItems="center">
        <Button variant="contained" color="primary" 
          onClick={handleRestrictedClick}>
          Restriced page
        </Button>
    </Box>
    </div>
  );   
}

export default HomePage;
