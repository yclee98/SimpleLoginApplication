import React from 'react'
import { useLocalStorage } from '../../Utility/uselocalStorage';
import {Navigate} from 'react-router-dom';

const PrivateRoute = ({children}) => {
    const [jwt, setJwt] = useLocalStorage(null,"jwt");
  
    return jwt ? children : <Navigate to="/"/>;
    
  }
export default PrivateRoute
