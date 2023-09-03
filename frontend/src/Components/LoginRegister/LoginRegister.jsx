import React, { useEffect, useState } from 'react'
import './LoginRegister.css'

import FormLogin from './FormLogin'
import FormRegister from './FormRegister';
import { useLocalStorage } from '../../Utility/uselocalStorage';
import { useNavigate  } from 'react-router-dom';

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
  }, [jwt, navigate]);

  function redirectHomePage(){
    console.log("to home2 "+jwt)
    navigate('/home');
    // window.location.href = 'home';
  }

  function sendAuthenticationRequest(endpoint, requestBody){
    console.log(`sending request jwt: ${jwt}`);    

    fetch("http://localhost:8080/api/authentication/"+endpoint,{
      headers:{
        "Content-Type":"application/json",
      },
      method:"post",
      body: JSON.stringify(requestBody)
    }).then(response=>{
      if (response.status === 200)
        return Promise.all([response.json()]);
      else
        return response.json().then((data)=>{
          return Promise.reject("Error: "+data['message']);
      })
    }).then(([data]) =>{
      console.log("setting jwt"+data["token"])
      setJwt(data["token"]);
    }).catch((message)=>{
      alert(message);
    });
  }

  function handleLoginSubmitClick(username, password){
    console.log("login " + username + " "  + password);
    const requestBody = {
      username: username,
      password: password,
    };
    
    sendAuthenticationRequest("login", requestBody);
    console.log("hoho")
  };

  function handleRegisterSubmitClick(username, password, name, role){
    console.log("register "+ username + name + password + role);
    const requestBody ={
      username:username,
      password:password,
      name:name,
      role:role
    }
    sendAuthenticationRequest("register", requestBody);
    console.log("done register")
  }

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


