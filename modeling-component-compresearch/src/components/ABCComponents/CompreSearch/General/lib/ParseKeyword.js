/**
 * @description 提取keyword中的 $key
 * Created by shwu.abcft on 2018/6/2.
 */

import queryString from 'query-string';

window.ParseKeyword = (props)=>{
  let  queryURL = queryString.parse(props.location.search)
  let UrlKeyword = queryURL.keyword || '';
  let newKeyword;
  if (UrlKeyword.indexOf('$key') !== -1 && UrlKeyword.indexOf('analyst') !== -1){
    let converAyy = UrlKeyword.split("AND");
    for (let i in converAyy){
      if (converAyy[i].indexOf('$key') !== -1){
        newKeyword = converAyy[i]
      }
    }
    newKeyword = newKeyword.slice(newKeyword.indexOf(":") + 1)
  } else {
    newKeyword = UrlKeyword;
  }
  return newKeyword;
}