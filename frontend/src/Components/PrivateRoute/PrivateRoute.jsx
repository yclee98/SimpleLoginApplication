import React, { useEffect, useState } from 'react'
import { useLocalStorage } from '../../Service/LocalStorageService';
import {Navigate} from 'react-router-dom';
import { validateRole, validateToken } from '../../Service/AuthenticationService';
import LoadingSpinner from '../LoadingSpinner/LoadingSpinner';

// only authenticated users are allowed to be inside private route
const PrivateRoute = (props) => {
  const { role, fallbackpath, children } = props;
  const [jwt, setJwt] = useLocalStorage(null,"jwt");
  const [isValid, setIsValid] = useState(null);
  const [isValidRole, setIsValidRole] = useState(null);

  useEffect(()=>{
    console.log("came")
    validateToken(jwt).then(()=>{
      setIsValid(true);
    }).catch((error)=>{
      setIsValid(false);
      setJwt(null);
    })

    validateRole(jwt).then(()=>{
      setIsValidRole(true);
    }).catch((error)=>{
      setIsValidRole(false);
    })
  }, []);

  if (isValid === null || isValidRole === null)
    return <LoadingSpinner/>
  if (role==="false" && isValid === true)
    return children
  if (role==="true" && isValidRole === true)
    return children
  return <Navigate to={fallbackpath}/>;

}
export default PrivateRoute
