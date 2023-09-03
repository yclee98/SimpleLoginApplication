import React from 'react'

const FormRegister = () => {
  return (
    <><div className='input'>
        <div className='text'>Name</div>
        <input type='text' />
    </div>
    
    <div className='input'>
        <div className='text'>Username</div>
        <input type='text' />
    </div>

    <div className='input'>
        <div className='text'>Password</div>
        <input type='password' />
    </div>

    <div className='input radio'>
      <div className='radio-option'>
        <input type='radio' name='role' id='radio_user' value='User' defaultChecked/>
        <label for='radio_user'>User</label>
      </div>
      <div className='radio-option'>
        <input type='radio' name='role' id='radio_manager' value='Manager'/>
        <label for='radio_manager'>Manager</label>
      </div>
    </div></>   
  )
}

export default FormRegister
