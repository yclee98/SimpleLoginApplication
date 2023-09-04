import React, { useEffect, useState } from 'react'
import './LoginRegister.css'

import FormLogin from './FormLogin'
import FormRegister from './FormRegister';
import { useLocalStorage } from '../../Utility/LocalStorageHelper';
import { useNavigate  } from 'react-router-dom';

import { LoginAuthenticationRequest, RegisterAuthenticaionRequest } from '../../Controller/AuthenticationController';

const LoginRegister = () => {
  console.log("login page");

  const [action,setAction] = useState("Login");
  const [jwt, setJwt] = useLocalStorage("", "jwt");
  const navigate = useNavigate();

  useEffect(() => {
    console.log('JWT has changed:', jwt);
    if (jwt) {
      redirectHomePage();
    }
  }, [jwt]);

  function redirectHomePage(){
    console.log("to home2 "+jwt)
    navigate('/home');
    // window.location.href = 'home';
  };

  function handleLoginSubmitClick(username, password){
    console.log("login " + username + " "  + password);
    LoginAuthenticationRequest(username, password).then(([data])=>{
    if(!jwt)
        setJwt(data["token"]);
    }).catch((message)=>{
      alert("Error: " + message);
    })
  };

  function handleRegisterSubmitClick(username, password, name, role){
    console.log("register "+ username + name + password + role);
    RegisterAuthenticaionRequest(username, password, name, role).then(([data])=>{
    if(!jwt)
        setJwt(data["token"]);
    }).catch((message)=>{
      alert("Error: " + message);
    })
  };

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
          action==="Login"?<FormLogin onFormSubmit={handleLoginSubmitClick}></FormLogin>:
          <FormRegister onFormSubmit={handleRegisterSubmitClick}></FormRegister>
        }
        </div>

        
      </div>
    </>
  )
}

export default LoginRegister


