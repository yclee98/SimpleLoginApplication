import React, { useState } from 'react'
import { useLocalStorage } from '../../Utility/LocalStorageHelper';
import {Navigate} from 'react-router-dom';
import {API_AUTHENTICATION_URL} from '../../config' 

// only authenticated users are allowed to be inside private rote
const PrivateRoute = (props) => {
  console.log("private rote1111");
  const { role, fallbackpath, children } = props;
  console.log(props)

  const [jwt, setJwt] = useLocalStorage(null,"jwt");
  const [isLoading, setIsLoading] = useState(false);
  const [isValid, setIsValid] = useState(null);
  const [isValidRole, setIsValidRole] = useState(null);
  console.log(isLoading + " " + isValid + " " + isValidRole);

  if(isValid === null && isValidRole === null && isLoading !== true && jwt){
    setIsLoading(true);
    fetch(API_AUTHENTICATION_URL+"validatetoken",{
      headers:{
        "Content-Type":"application/json",
        "Authorization":"Bearer "+jwt,
      },
      method:"get",
    }).then((response)=>{
      if (response.status === 200){
        console.log("valid for token");
          setIsValid(true);        
      }
      else{
          setIsValid(false);      
      }
      setIsLoading(false);
    }).catch((error)=>{
      setIsLoading(false);
      setIsValid(false);
      setJwt(null);
    })

    fetch(API_AUTHENTICATION_URL+"validaterole",{
      headers:{
        "Content-Type":"application/json",
        "Authorization":"Bearer "+jwt,
      },
      method:"get",
    }).then((response)=>{
      if (response.status === 200){
        console.log("valid for role");
          setIsValidRole(true);        
      }
      else{
        setIsValidRole(false);      
      }
      setIsLoading(false);
    }).catch((error)=>{
      setIsLoading(false);
      setIsValidRole(false);
    })    
  }

  if(isLoading)
    return (<div>Loading...</div>)
  else{
    if(role === "false"){
        if(isValid === true)
          return children
    }else{
        if(isValidRole === true)
          return children
        else
          alert("No access");
    }
    return <Navigate to={fallbackpath}/>;
  }
}
export default PrivateRoute
