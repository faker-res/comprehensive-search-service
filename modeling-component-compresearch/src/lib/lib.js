import {
    notification
} from 'antd';

// 通用的error事件 组要是处理 200后的success为 false事件
export function generalErrorHandle(res, dontShow) {
    let {
        success
    } = res;
    if (success) {
        return true
    } else {
        if (dontShow) return;
        
        notification.error({
            message: '错误',
            description: res.message,
        });
    }
}
// 获取search 的 参数
export function getQueryString(search, name) {
    let reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    let r = search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return undefined;
}

// 生成guid  
export function guid() {
    return 'xxxxxxxx_xxxx_4xxx_yxxx_xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        let r = Math.random() * 16 | 0,
            v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}
// 获取多久前 时间戳
export function howLongAgo(dateTimeStamp){
    let result = '';
    let minute = 1000 * 60;
    let hour = minute * 60;
    let day = hour * 24;
    let month = day * 30;
    let now = new Date().getTime();
    let diffValue = now - dateTimeStamp;
    if (diffValue < 0){return;}
    let monthC = diffValue / month;
    let weekC = diffValue / (7 * day);
    let dayC = diffValue / day;
    let hourC = diffValue / hour;
    let minC = diffValue / minute;
    if (monthC >= 1){
        result = "" + parseInt(monthC) + "月前";
    }
    else if (weekC >= 1){
        result = "" + parseInt(weekC) + "周前";
    }
    else if (dayC >= 1){
        result = "" + parseInt(dayC) + "天前";
    }
    else if (hourC >= 1){
        result = "" + parseInt(hourC) + "小时前";
    }
    else if (minC >= 1){
        result = "" + parseInt(minC) + "分钟前";
    } else {result = "刚刚";}
    return result;
}
// 拷贝对象
export function cloneJSON(object) {
    return JSON.parse(JSON.stringify(object));
}

export function setFile(arr) {
    let temp = []
    if (arr.includes('pdf')) {
        temp.push('pdf')
    }
    if (arr.includes('xls')) {
        temp.push('xls,xlsx')
    }
    if (arr.includes('doc')) {
        temp.push('doc,docx')
    }
    if (arr.includes('ppt')) {
        temp.push('ppt,pptx')
    }
    if (arr.includes('jpg')) {
        temp.push('jpg,jpeg,png')
    }
    return temp
}