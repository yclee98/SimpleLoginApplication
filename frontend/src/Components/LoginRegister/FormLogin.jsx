import React, { useState } from 'react'
import { useTranslation } from 'react-i18next';

const FormLogin = ({onFormSubmit}) => {
  const {t} = useTranslation();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  return (    
    <><div className='input'>
        <div className='text'>{t("username")}</div>
        <input type='text'
          value={username} 
          onChange={(e) => setUsername(e.target.value)}/>
    </div>
    
    <div className='input'>
        <div className='text'>{t("password")}</div>
        <input type='password' 
          value={password}
          onChange={(e) => setPassword(e.target.value)}/>
    </div> 

    <div className='submit-container'>
      <div className='submit-button' onClick={()=>onFormSubmit(username, password)}>{t("submit")}</div> 
    </div> 

    </>  
  )
}

export default FormLogin
