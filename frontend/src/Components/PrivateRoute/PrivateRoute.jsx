import React, { useState } from 'react'
import { useLocalStorage } from '../../Utility/LocalStorageHelper';
import {Navigate} from 'react-router-dom';
import {API_AUTHENTICATION_URL} from '../../config' 
import { validateRole, validateToken } from '../../Controller/AuthenticationController';

// only authenticated users are allowed to be inside private rote
const PrivateRoute = (props) => {
  console.log("private route")
  const { role, fallbackpath, children } = props;

  const [jwt, setJwt] = useLocalStorage(null,"jwt");
  const [isLoading, setIsLoading] = useState(false);
  const [isValid, setIsValid] = useState(null);
  const [isValidRole, setIsValidRole] = useState(null);
  console.log(isLoading + " " + isValid + " " + isValidRole);

  //set isValid for valid token and isValidRole for manager role 
  if(isValid === null && isValidRole === null && isLoading !== true && jwt){
    setIsLoading(true);
    validateToken(jwt).then(()=>{
      setIsValid(true);
      setIsLoading(false);
    }).catch((error)=>{
      setIsValid(false);
      setIsLoading(false);
      setJwt(null);
    })

    validateRole(jwt).then(()=>{
      setIsValidRole(true);
      setIsLoading(false);
    }).catch((error)=>{
      setIsValidRole(false);
      setIsLoading(false);
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
