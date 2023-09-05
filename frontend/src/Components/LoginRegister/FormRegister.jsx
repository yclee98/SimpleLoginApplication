import React, { useState } from 'react'
import { useTranslation } from 'react-i18next';

const FormRegister = ({onFormSubmit}) => {  
  const {t} = useTranslation();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [role, setRole] = useState('User');

  return (
    <><div className='input'>
        <div className='text'>{t("name")}</div>
        <input type='text' 
        value={name}
        onChange={(e) => setName(e.target.value)}/>
    </div>
    
    <div className='input'>
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

    <div className='input radio'>
      <div className='radio-option'>
        <input type='radio' name='role' id='radio_user' value='User' defaultChecked
        onChange={(e) => setRole(e.target.value)}/>
        <label htmlFor='radio_user'>{t("role_user")}</label>
      </div>

      <div className='radio-option'>
        <input type='radio' name='role' id='radio_manager' value='Manager'
        onChange={(e) => setRole(e.target.value)}/>
        <label htmlFor='radio_manager'>{t("role_manager")}</label>
      </div>
    </div>

    <div className='submit-container'>
      <div className='submit-button' onClick={()=>onFormSubmit(username, password, name, role)}>{t("submit")}</div> 
    </div> 

    </>  
  )
}

export default FormRegister
