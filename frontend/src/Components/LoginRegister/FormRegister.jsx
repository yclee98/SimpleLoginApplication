import React, { useState } from 'react'

const FormRegister = ({onFormSubmit}) => {  

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [role, setRole] = useState('User');

  return (
    <><div className='input'>
        <div className='text'>Name</div>
        <input type='text' 
        value={name}
        onChange={(e) => setName(e.target.value)}/>
    </div>
    
    <div className='input'>
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

    <div className='input radio'>
      <div className='radio-option'>
        <input type='radio' name='role' id='radio_user' value='User' defaultChecked
        onChange={(e) => setRole(e.target.value)}/>
        <label htmlFor='radio_user'>User</label>
      </div>

      <div className='radio-option'>
        <input type='radio' name='role' id='radio_manager' value='Manager'
        onChange={(e) => setRole(e.target.value)}/>
        <label htmlFor='radio_manager'>Manager</label>
      </div>
    </div>

    <div className='submit-container'>
      <div className='submit-button' onClick={()=>onFormSubmit(username, password, name, role)}>Submit</div> 
    </div> 

    </>   
  )
}

export default FormRegister
