import React from 'react'
import MenuBar from '../Components/MenuBar/MenuBar';
import { useTranslation } from 'react-i18next';

const RestrictedPage = () => {
  const {t} = useTranslation();

  return (
    <div>
      <MenuBar></MenuBar>
      {t("restricted")}
    </div>
  )
}

export default RestrictedPage
