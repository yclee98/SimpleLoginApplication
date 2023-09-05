import React from 'react'

import { clearLocalStorage, useLocalStorage } from '../../Service/LocalStorageService';
import { useNavigate } from 'react-router-dom';
import './MenuBar.css'
import LangaugeSwitcher from '../LanguageSwitcher/LangaugeSwitcher';
import { useTranslation } from 'react-i18next';

const MenuBar = () => {
    const {t} = useTranslation();
    const [jwt, ] = useLocalStorage("", "jwt");
    const navigate = useNavigate();

    function handleLogoutClick(){
        clearLocalStorage();    
        navigate("/");
    }

    return (
        <div>
            <ul>
                <li><LangaugeSwitcher/></li>
                {jwt?<li><div className='text' onClick={handleLogoutClick}>{t("logout")}</div></li>:<></>}
                
            </ul>
      </div>   
    )
}

export default MenuBar
