/**
 * @description 基础API
 * @author wxiong
 * date: 2018/5/26
 */

export function upOrDown(numStr, showGray, fix) {
  let num = +numStr;
    if (!isNaN(num)) {
      num = +num.toFixed(2);
      if (num > 0) {
        return ' up';
      }
      else if (num === 0) {
        return ' flat'
      } else {
        return ' down';
      }
    } else {
      if (showGray) {
        return ' flat'
      } else {
        return ' ';
      }
    }
  }
export function getPrefixNum(numStr, isPercent, fix, sufix) {
    let num = +numStr;
    let unit = '';
    if (!isNaN(num) && numStr !== null) {
      if (Math.abs(num) > 100000000) {
        num = num / 100000000;
        unit = '亿';
      } else if (Math.abs(num) > 10000) {
        num = num / 10000;
        unit = '万';
      }
      if (fix) {
        numStr = num.toFixed(2);
      }
      if (isPercent) {
        return numStr + '%';
      } else {
        numStr += unit;
        if (sufix) {
          numStr += sufix;
        }
      }
      return numStr;
    } else {
      return '--';
    }
  }

  export function getNum(numStr, fix) {
    if (numStr === null) {
      return '--';
    }
    let num = +numStr;
    if (!isNaN(num) && numStr !== null) {
      if (Math.abs(num) > 100000000) {
        num = num / 100000000;
      } else if (Math.abs(num) > 10000) {
        num = num / 10000;
      }
    }
    if (fix) {
      num = num.toFixed(2);
    }
    return num
  }

  export function getPrefix(numStr) {
    let num = +numStr;
    let unit = '';
    if (!isNaN(num) && numStr !== null) {
      if (Math.abs(num) > 100000000) {
        unit = '亿';
      } else if (Math.abs(num) > 10000) {
        unit = '万';
      }
    }
    return unit;
  }