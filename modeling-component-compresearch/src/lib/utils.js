import moment from 'moment';
import queryString from 'query-string'
const pathUrl = "http://abc-crawler.oss-cn-hangzhou.aliyuncs.com/";
const url = pathUrl;
export function fixedZero(val) {
  return Number(val) < 10 ? `0${val}` : val;
}

export function getTimeDistance(type) {
  const now = new Date();
  const oneDay = 1000 * 60 * 60 * 24;

  if (type === 'today') {
    now.setHours(0);
    now.setMinutes(0);
    now.setSeconds(0);
    return [moment(now), moment(now.getTime() + (oneDay - 1000))];
  }

  if (type === 'week') {
    let day = now.getDay();
    now.setHours(0);
    now.setMinutes(0);
    now.setSeconds(0);

    if (day === 0) {
      day = 6;
    } else {
      day -= 1;
    }

    const beginTime = now.getTime() - (day * oneDay);

    return [moment(beginTime), moment(beginTime + ((7 * oneDay) - 1000))];
  }

  if (type === 'month') {
    const year = now.getFullYear();
    const month = now.getMonth();
    const nextDate = moment(now).add(1, 'months');
    const nextYear = nextDate.year();
    const nextMonth = nextDate.month();

    return [moment(`${year}-${fixedZero(month + 1)}-01 00:00:00`), moment(moment(`${nextYear}-${fixedZero(nextMonth + 1)}-01 00:00:00`).valueOf() - 1000)];
  }

  if (type === 'year') {
    const year = now.getFullYear();

    return [moment(`${year}-01-01 00:00:00`), moment(`${year}-12-31 23:59:59`)];
  }
}

export function getPlainNode(nodeList, parentPath = '') {
  const arr = [];
  nodeList.forEach((node) => {
    const item = node;
    item.path = `${parentPath}/${item.path || ''}`.replace(/\/+/g, '/');
    item.exact = true;
    if (item.children && !item.component) {
      arr.push(...getPlainNode(item.children, item.path));
    } else {
      if (item.children && item.component) {
        item.exact = false;
      }
      arr.push(item);
    }
  });
  return arr;
}

export function digitUppercase(n) {
  const fraction = ['角', '分'];
  const digit = ['零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'];
  const unit = [
    ['元', '万', '亿'],
    ['', '拾', '佰', '仟'],
  ];
  let num = Math.abs(n);
  let s = '';
  fraction.forEach((item, index) => {
    s += (digit[Math.floor(num * 10 * (10 ** index)) % 10] + item).replace(/零./, '');
  });
  s = s || '整';
  num = Math.floor(num);
  for (let i = 0; i < unit[0].length && num > 0; i += 1) {
    let p = '';
    for (let j = 0; j < unit[1].length && num > 0; j += 1) {
      p = digit[num % 10] + unit[1][j] + p;
      num = Math.floor(num / 10);
    }
    s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
  }

  return s.replace(/(零.)*零元/, '元').replace(/(零.)+/g, '零').replace(/^整$/, '零元整');
}


function getRelation(str1, str2) {
  if (str1 === str2) {
    console.warn('Two path are equal!'); // eslint-disable-line
  }
  const arr1 = str1.split('/');
  const arr2 = str2.split('/');
  if (arr2.every((item, index) => item === arr1[index])) {
    return 1;
  } else if (arr1.every((item, index) => item === arr2[index])) {
    return 2;
  }
  return 3;
}

function getRenderArr(routes) {
  let renderArr = [];
  renderArr.push(routes[0]);
  for (let i = 1; i < routes.length; i += 1) {
    let isAdd = false;
    // 是否包含
    isAdd = renderArr.every(item => getRelation(item, routes[i]) === 3);
    // 去重
    renderArr = renderArr.filter(item => getRelation(item, routes[i]) !== 1);
    if (isAdd) {
      renderArr.push(routes[i]);
    }
  }
  return renderArr;
}

/**
 * Get router routing configuration
 * { path:{name,...param}}=>Array<{name,path ...param}>
 * @param {string} path
 * @param {routerData} routerData
 */
export function getRoutes(path, routerData) {
  let routes = Object.keys(routerData).filter(routePath =>
    routePath.indexOf(path) === 0 && routePath !== path);
  // Replace path to '' eg. path='user' /user/name => name
  routes = routes.map(item => item.replace(path, ''));
  // Get the route to be rendered to remove the deep rendering
  const renderArr = getRenderArr(routes);
  // Conversion and stitching parameters
  const renderRoutes = renderArr.map((item) => {
    const exact = !routes.some(route => route !== item && getRelation(route, item) === 1);
    return {
      ...routerData[`${path}${item}`],
      key: `${path}${item}`,
      path: `${path}${item}`,
      exact,
    };
  });
  return renderRoutes;
}


/* eslint no-useless-escape:0 */
const reg = /(((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$/g;

export function isUrl(path) {
  return reg.test(path);
}

// --------------------------新的--------------------------------------
export function formatMoney(moneyT, decimal) {
  const regNew = /(\d+)(\d{3})/;
  let money = moneyT;
  if (decimal) {
    money = parseFloat(money).toFixed(decimal);
  } else {
    money = String(money);
  }
  while (regNew.test(money)) {
    money = money.replace(regNew, '$1,$2');
  }
  return money;
}

export function getSearchParams(object) {
  let paramsStr = '';
  for (const key in object) {
    if (Object.prototype.hasOwnProperty.call(object, key)) {
      const value = object[key];
      paramsStr = `${paramsStr}&${key}=${value}`;
    }
  }
  return paramsStr;
}

export function getApiPath(url) {
  let path = url;
  if (pathUrl.length > 0) {
    path = pathUrl + url;
  }
  console.log(path);
  return path;
}
// 拷贝对象
export function cloneJSON(object) {
  return JSON.parse(JSON.stringify(object));
}
// 拷贝Map
export function cloneMap(map) {
  const newMap = new Map();
  for (const [key, value] of map) {
    newMap.set(key, value);
  }
  return newMap;
}
// 多个数组合并去重（限基础数据类型）
export function concatArraysWithSingle(arrays) {
  let arr = [];
  // 合并
  for (const array of arrays) {
    arr = [...arr, ...array];
  }
  // 去重
  arr = Array.from(new Set(arr));
  return arr;
}
// 通过一个属性值，获取数组中匹配的对象的索引，仅限对象
export function getArrayIndex(list, value, property) {
  let index = -1;
  for (let i = 0; i < list.length; i++) {
    const item = list[i];
    if (item[property] === value) {
      index = i;
    }
  }
  return index;
}
// 判断图片等url地址是否包含http头部
export function getFullUrl(urlTemp) {
  return urlTemp.indexOf('http') === -1 ? `${url}${urlTemp}` : urlTemp;
}
// 获取据当前时间前几天的日期
export function getDay(num, str) {
  let today = new Date();
  let nowTime = today.getTime();
  let ms = 24 * 3600 * 1000 * num;
  today.setTime(parseInt(nowTime + ms));
  let oYear = today.getFullYear();
  let oMoth = (today.getMonth() + 1).toString();
  if (oMoth.length <= 1) oMoth = '0' + oMoth;
  let oDay = today.getDate().toString();
  if (oDay.length <= 1) oDay = '0' + oDay;
  return oYear + str + oMoth + str + oDay;
}

//去掉html标签
export function delHtmlTag(str) {
  return str ? str.replace(/<[^<>]+>/g, "") : '';
}
export function getNoUserInfoLocationHref() {
  const local = window.location;
  const baseUrl = local.protocol + '//' + local.host + local.pathname;
  const oSearch = queryString.parse(local.search);
  if (!oSearch) return baseUrl;
  oSearch.userId && delete oSearch.userId;
  oSearch.ticket && delete oSearch.ticket;
  oSearch.skipAuth && delete oSearch.skipAuth;
  const search = queryString.stringify(oSearch) ? '?' + queryString.stringify(oSearch) : '';
  return baseUrl + search;
}
export function GetUrlParam(paraName){
  var url = document.location.toString();
  var arrObj = url.split("?");

  if (arrObj.length > 1) {
    var arrPara = arrObj[1].split("&");
    var arr;

    for (var i = 0; i < arrPara.length; i++) {
      arr = arrPara[i].split("=");

      if (arr != null && arr[0] == paraName) {
        return arr[1];
      }
    }
    return "";
  } else {
    return "";
  }
}
export function formateTimebyTimebefore(timebefore) {
  var newDate1 = new Date(Math.floor(new Date().getTime()) - timebefore * 1000);
  var newDate = new Date(newDate1.getTime());
  var date = {
    Y: newDate.getFullYear(),
    M: newDate.getMonth() + 1,
    d: newDate.getDate(),
    h: newDate.getHours(),
    m: newDate.getMinutes(),
    s: newDate.getSeconds(),
    q: Math.floor((newDate.getMonth() + 3) / 3),
    S: newDate.getMilliseconds()
  };
  return `${date.M}月${date.d}日`;
}