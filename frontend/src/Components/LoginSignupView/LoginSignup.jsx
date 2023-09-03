import React, { useState } from 'react'
import './LoginSignup.css'

import FormLogin from './FormLogin'
import FormRegister from './FormRegister';

const LoginSignup = () => {

  const [action,setAction] = useState("Login");

  return (
    <>
      <div className='container'>
        <div className='headers-container'>
          <div className='header'>
            <div className='text' onClick={()=>(setAction("Login"))}>Login</div>
            <div className={action==="Login"?"underline":null}></div>
          </div>

          <div className='header'>
            <div className='text' onClick={()=>(setAction("Register"))}>Register</div>
            <div className={action==="Register"?"underline":null}></div>
          </div>
        </div>

        <div className='inputs-container'>
          {
            action==="Login"?<FormLogin></FormLogin>:<FormRegister></FormRegister>
          }
        </div>

        <div className='submit-container'>
          <div className='submit-button'>Submit</div> 
        </div>

      </div>
    </>
  )
}

export default LoginSignup
