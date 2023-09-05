import React from 'react'
import { useTranslation } from 'react-i18next'

const LangaugeSwitcher = () => {
    const {i18n} = useTranslation();

    const handleLanguageChange = () => {
        if (i18n.language === "en"){
            i18n.changeLanguage("zh");
        }
        else{
            i18n.changeLanguage("en");
        }
    }

    return (
        <div className='text' onClick={handleLanguageChange}>EN/ZH</div>
    )
}

export default LangaugeSwitcher
