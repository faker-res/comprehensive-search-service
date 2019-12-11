import Cookies from "js-cookie";

export function checkSession(history, toUrl){
  if(Cookies.get('userId') && Cookies.get('token')){
    history.push(toUrl || '/home')
  }
}