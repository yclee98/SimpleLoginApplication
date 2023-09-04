import React from 'react'
import MenuBar from '../Components/MenuBar/MenuBar';
import { useNavigate } from 'react-router-dom';
import { useLocalStorage } from '../Service/LocalStorageService';
const HomePage = () => {
  const navigate = useNavigate();
  const [username, ] = useLocalStorage("", "username");
  const [name, ] = useLocalStorage("", "name");
  const [role, ] = useLocalStorage("", "role");

  function handleRestrictedClick(){
    navigate("/restricted");
  }

  return (
    <div>
      <MenuBar></MenuBar>
      <h1>Welcome {username}</h1>
      <p>Name: {name}</p>
      <p>Role: {role}</p>

      {
        role==="manager" ? 
        <div className='submit-container'>
        <div className='submit-button' onClick={handleRestrictedClick}>Restricted page</div> </div> 
        : <></>
      }
      
    </div>
  );   
}

export default HomePage;
