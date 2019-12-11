import React, { Component } from "react";

export const toFixed = (num, keep) => {
    const n = new Number(num);
    return n.toFixed(keep);
};

export const getPriceColor = (differ) => {
    if (differ > 0) {
        return {
            color: "#D9554E"
        };
    } else if (differ < 0) {
        return {
            color: "#4DA04C"
        };
    } else {
        return {
            color: "#9B9B9B"
        };
    }
};



export const stockDealTypes = {
    1: '买入',
    2: '卖出',
    3: '补回',
    4: '卖空',
    5: '合股',
    6: '拆股',
    7: '除权除息',
}

export const currencyTypes = {
    CN: '人民币',
    US: '美元',
    HK: '港币',
}

export const marketTypes = [{
    1: {
        1004001001: 'A股',
    }
},
{
    2: {
        1004001004: '美股',
    }
}
]

/**
* 获得股票的Icon
* market 市场类型，沪深、美股、港股、基金
* marginSatus 用于判断 沪深股票是否有融资融券0：没有，1：有
*/
export const getStockIcon = (market, marginSatus = 0) => {
    if (!market) {
        return '';
    }
    /**
     * 沪深 是否有融资融券
     */
    const hasFinancing = () => {
        return marginSatus === 0 ? false : true;
    }
    let result = '';
    switch (market) {
        //美股
        case 1004018:
            result = <svg className="icon stockIcon" aria-hidden="true">
                <use xlinkHref="#icon-usd"></use>
            </svg>
            break;
        //港股
        case 1004017:
            result = <svg className="icon stockIcon" aria-hidden="true">
                <use xlinkHref="#icon-hkd"></use>
            </svg>
            break;
        //基金
        case 1004003:
            result = <svg className="icon stockIcon" aria-hidden="true">
                <use xlinkHref="#icon-fund"></use>
            </svg>
            break;
        //沪深
        case 1004001:
            if (hasFinancing()) {
                result = <svg className="icon stockIcon" aria-hidden="true">
                    <use xlinkHref="#icon-financing"></use>
                </svg>
            } else {
                result = '';
            }
            break;
        default:
            break;
    }

    return result;
}

export const getHost = () => {
    let serverUrl = '';
    if (window.abc_sso_env === 'dev') {
        serverUrl = 'https://api.researchreport.cn';
    } else if (window.abc_sso_env === 'pre') {
        serverUrl = 'https://api-dev.analyst.ai';
    } else if (window.abc_sso_env === 'prod') {
        serverUrl = 'https://api.analyst.ai';
    }
    return serverUrl;
}

export const toDecimal = (x, length) => {
    let f = toFixed(x, length);
    if (isNaN(f)) {
        return false;
    }
    if (length == 3){
        f = Math.round(x * 1000) / 1000;
    } else if (length == 4){
        f = Math.round(x * 10000) / 10000;
    } else {
        f = Math.round(x * 100) / 100;
    }
    let s = f.toString();
    let rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + length) {
        s += '0';
    }
    return s;
}

/**
 * 小数后面保留几位，不四舍五入
 */
export const toUnrounded = (x, length) => {
    try {
        if (!length) {
            return x;
        }
        let v = x + '';
        let r = v;
        if (v.lastIndexOf('.') > -1) {
            if (v.substring(v.lastIndexOf('.') + 1).length < length) {
                return parseFloat(v.substring(0, v.lastIndexOf('.') + (length + 1))).toFixed(length);
            } else {
                return v.substring(0, v.lastIndexOf('.') + (length + 1));
            }
        } else {
            return parseFloat(r).toFixed(length);
        }
    } catch (error) {
        console.error(error + 'toUnrounded 转换失败');
    }
}

export const getUnroundedUnit = (num, length = 0) => {
    const key = Math.abs(parseInt(num)) + "";
    const yi = 100000000;
    const wan = 10000;
    let money = 0.00;
    let allValue = 0.00;
    let unit = "";
    if (key.length >= 9) {
        money = toUnrounded(num / yi, 2);
        allValue = toUnrounded(num / yi, 9);
        unit = "亿";
    } else if (key.length >= 5) {
        money = toUnrounded(num / wan, 2);
        allValue = toUnrounded(num / wan, 5);
        unit = "万";
    } else {
        money = toUnrounded(num, length);
        allValue = toUnrounded(num, length);
    }
    return {
        value: money,
        allValue: allValue,
        unit: unit
    };
};

export const getUnit = num => {
    const key = Math.abs(parseInt(num)) + "";
    const yi = 100000000;
    const wan = 10000;
    let money = 0.0;
    let unit = "";
    if (key.length >= 9) {
        money = toFixed(num / yi, 2);
        unit = "亿";
    } else if (key.length >= 5) {
        money = toFixed(num / wan, 2);
        unit = "万";
    } else {
        money = num;
    }
    return {
        value: money,
        unit: unit
    };
};