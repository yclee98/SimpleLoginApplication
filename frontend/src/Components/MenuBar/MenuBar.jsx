import React from 'react'

import { clearLocalStorage, useLocalStorage } from '../../Service/LocalStorageService';
import { useNavigate } from 'react-router-dom';
import './MenuBar.css'

const MenuBar = () => {
    const [jwt, ] = useLocalStorage("", "jwt");
    const navigate = useNavigate();

    function handleLogoutClick(){
        clearLocalStorage();    
        navigate("/");
    }

    return (
        <div>
            <ul>
                <li><div className='text'>EN/CN</div></li>
                {jwt?<li><div className='text' onClick={handleLogoutClick}>Logout</div></li>:<></>}
                
            </ul>
      </div>   
    )
}

export default MenuBar
