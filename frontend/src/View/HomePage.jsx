import React from 'react'
import MenuBar from '../Components/MenuBar/MenuBar';
import { useNavigate } from 'react-router-dom';
import { useLocalStorage } from '../Service/LocalStorageService';
import { useTranslation } from 'react-i18next';

const HomePage = () => {
  const {t} = useTranslation();
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
      <h1>{t("welcome_text")} {username}</h1>
      <p>{t("name")}: {name}</p>
      <p>{t("role")}: {role}</p>

      {
        role==="manager" ? 
        <div className='submit-container'>
        <div className='submit-button' onClick={handleRestrictedClick}>{t("restricted")}</div> </div> 
        : <></>
      }
      
    </div>
  );   
}

export default HomePage;
