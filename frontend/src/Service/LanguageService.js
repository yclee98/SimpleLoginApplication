import i18next from "i18next";
import { initReactI18next } from "react-i18next";

import enTranslation from '../Resources/Locales/en.json'
import zhTranslation from '../Resources/Locales/zh.json'

const resources = {
    en: {
        translation: enTranslation
    },
    zh: {
        translation: zhTranslation
    }
}

i18next.use(initReactI18next).init({
    resources,
    lng: "en",
    fallbackLng: "en",
    interpolation:{
        escapeValue: false,
    }
})

export default i18next