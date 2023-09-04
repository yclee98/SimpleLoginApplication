import React, { useState } from 'react'

const FormLogin = ({onFormSubmit}) => {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  return (    
    <><div className='input'>
        <div className='text'>Username</div>
        <input type='text'
          value={username} 
          onChange={(e) => setUsername(e.target.value)}/>
    </div>
    
    <div className='input'>
        <div className='text'>Password</div>
        <input type='password' 
          value={password}
          onChange={(e) => setPassword(e.target.value)}/>
    </div> 

    <div className='submit-container'>
      <div className='submit-button' onClick={()=>onFormSubmit(username, password)}>Submit</div> 
    </div> 

    </>  
  )
}

export default FormLogin
