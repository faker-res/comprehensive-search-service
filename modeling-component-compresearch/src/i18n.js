/**
 * @description 国际化插件i18next实例配置文件
 * @author jhqu
 * date: 2018-05-18
 */

import i18n from 'i18next';
import XHR from 'i18next-xhr-backend';
import LanguageDetector from 'i18next-browser-languagedetector';
import i18nConfig from './i18n-config.json';
const supportedLanguages = Object.keys(i18nConfig.supportedLanguages);

i18n
  .use(XHR)
  .use(LanguageDetector)
  .init({
    lowerCaseLng: true,
    fallbackLng: i18nConfig.defaultLanguage,
    ns: i18nConfig.NSList,
    defaultNS: i18nConfig.defaultNS,
    load: 'currentOnly',
    whitelist: supportedLanguages,
    preload: supportedLanguages,
    debug: process.env.NODE_ENV === 'development',

    backend: {
      loadPath: process.env.PUBLIC_URL + '/locales/{{lng}}/{{ns}}.json'
    },

    detection: {
      lookupQuerystring: 'lang',
      order: ['querystring', 'path', 'subdomain', 'cookie', 'localStorage', 'navigator', 'htmlTag'],
    },

    interpolation: {
      escapeValue: false,
    },

    react: {
      wait: true,
      bindI18n: 'languageChanged loaded',
      bindStore: 'added removed',
      nsMode: 'default'
    }
  });

export default i18n;
