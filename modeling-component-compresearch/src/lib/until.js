import {SSL_OP_DONT_INSERT_EMPTY_FRAGMENTS, ECONNREFUSED} from 'constants';
import {
    notification
} from 'antd';
import twemoji from 'twemoji';

/**
 * @description 方法集合
 * @author dhhuang1
 * @date 2018-06-22
 */

const numeral = require('numeral');
const moment = require('moment');
const Cookies = require('js-cookie');
const emojiRegex = require('emoji-regex');
const axios = require('axios');


//  设定list 长度
export const setListLength = (value) => {
    if (!value || value.length <= 0) return '100%';
    return `${value.length * 30 + 100}%`;
};

// 去掉html标签
export const replaceValue = (str) => {
    if (!str) return '';
    return str.replace(/<.*?>/gi, '');
};


// 设定input 宽度

export const setInputWidth = (number) => {
    if (number >= 1) {
        return 280;
    } else {
        return '100%';
    }
};
const setObjectToArray = (obj, list) => {
    let arr = [];
    Object.keys(obj).slice().map((item) => {
        if (item === 'history') {
            arr.push({
                title: 'history',
                list: obj[item]
            });
        }
    });
    if (list && list.length > 0) {
        arr.push({
            title: 'hotList',
            list: list
        });
    }
    Object.keys(obj).slice().map((item) => {
        if (item !== 'history') {
            arr.push({
                title: item,
                list: obj[item]
            });
        }
    });
    return arr;
};
// 设定index
export const setIndexNumber = (list, hostlist, len) => {
    if (!list) return {};
    list = setObjectToArray(list, hostlist);
    let num = 0;
    list.forEach(item => {
        item.list = item.list.slice(0, len.search).map((items) => {
            if (typeof items === 'string') {
                num += 1;
                return {
                    selectIndex: num,
                    title: items,
                    type: item.title === 'history' ? 'source' : 'in'
                };
            }
        });
    });
    return {list, threshold: num};
};

// 历史记录操作
export const historyFun = (type, data) => {
    switch (type) {
        case 'get':
            return localStorage.getItem('historyValue');
        case 'set':
            return localStorage.setItem('historyValue', JSON.stringify(data));
        case 'remove':
            localStorage.removeItem('historyValue');
    }

};

export const setValue = (value) => {
    if (!value) return {value: [], inputValue: ''};
    if (value.length <= 0) return {value: [], inputValue: ''};
    let inputValue = '';
    for (let i, len = value.length; i < len; i++) {
        if (value[i].select === 'keyword') {
            inputValue += `${value[i].value} `;
            value.slice(i, 1);
            i -= 1;

        }
    }
    return {
        value,
        inputValue
    };

};

export const utils = {
    // private property
    _keyStr: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=',
    // public method for encoding
    base64Encode: function (input) {
        let output = '';
        let chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        let i = 0;
        input = this._utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
                this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
        }
        return output;
    },
    // public method for decoding
    base64Decode: function (input) {
        let output = '';
        let chr1, chr2, chr3;
        let enc1, enc2, enc3, enc4;
        let i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, '');
        while (i < input.length) {
            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = this._utf8_decode(output);
        return output;
    },
    // private method for UTF-8 encoding
    _utf8_encode: function (string) {
        string = string.replace(/\r\n/g, '\n');
        let utftext = '';
        for (let n = 0; n < string.length; n++) {
            let c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }
        return utftext;
    },
    // private method for UTF-8 decoding
    _utf8_decode: function (utftext) {
        let string = '';
        let i = 0;
        let c = 0,
            c1 = 0,
            c2 = 0,
            c3 = 0;
        while (i < utftext.length) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    },
    /**
    * 邮箱格式校验
    * @param {*} email 
    */
    isValidEmail: function (email) {
        const reg = /^[A-Za-z0-9\-\.\_\!\#\$\%\*\/\?\^\`\{\|\}\~]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        return reg.test(email);
    },
    /**
     * 密码格式校验
     * @param {*} password 
     */
    isValidPassword: function (password) {
        // const reg = /^([a-zA-Z-0-9!@#$%^&*?]{8,20})$/;
        const reg = /^.{8,20}$/;
        return reg.test(password);
    },
    /**
     * 校验手机号码
     */
    isValidPhone: function (phone, isCN) {
        if (isCN) {
            return /^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/.test(phone);
        } else {
            return /^([0-9]{4,15})$/.test(phone);
        }
    },
    /**
     * 校验用户名：只包含数字、字母、中文、下划线(_)
     */
    isValidName: function (value) {
        const reg = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
        return reg.test(value);
    },
    /**
     * 清除微信昵称中的emoji
     * @param {*} str 
     */
    emojiTrim(str) {
        // const reg = /(?:[\u261D\u26F9\u270A-\u270D]|\uD83C[\uDF85\uDFC2-\uDFC4\uDFC7\uDFCA-\uDFCC]|\uD83D[\uDC42\uDC43\uDC46-\uDC50\uDC66-\uDC69\uDC6E\uDC70-\uDC78\uDC7C\uDC81-\uDC83\uDC85-\uDC87\uDCAA\uDD74\uDD75\uDD7A\uDD90\uDD95\uDD96\uDE45-\uDE47\uDE4B-\uDE4F\uDEA3\uDEB4-\uDEB6\uDEC0\uDECC]|\uD83E[\uDD18-\uDD1C\uDD1E\uDD1F\uDD26\uDD30-\uDD39\uDD3D\uDD3E\uDDD1-\uDDDD])(?:\uD83C[\uDFFB-\uDFFF])?|(?:[\u231A\u231B\u23E9-\u23EC\u23F0\u23F3\u25FD\u25FE\u2614\u2615\u2648-\u2653\u267F\u2693\u26A1\u26AA\u26AB\u26BD\u26BE\u26C4\u26C5\u26CE\u26D4\u26EA\u26F2\u26F3\u26F5\u26FA\u26FD\u2705\u270A\u270B\u2728\u274C\u274E\u2753-\u2755\u2757\u2795-\u2797\u27B0\u27BF\u2B1B\u2B1C\u2B50\u2B55]|\uD83C[\uDC04\uDCCF\uDD8E\uDD91-\uDD9A\uDDE6-\uDDFF\uDE01\uDE1A\uDE2F\uDE32-\uDE36\uDE38-\uDE3A\uDE50\uDE51\uDF00-\uDF20\uDF2D-\uDF35\uDF37-\uDF7C\uDF7E-\uDF93\uDFA0-\uDFCA\uDFCF-\uDFD3\uDFE0-\uDFF0\uDFF4\uDFF8-\uDFFF]|\uD83D[\uDC00-\uDC3E\uDC40\uDC42-\uDCFC\uDCFF-\uDD3D\uDD4B-\uDD4E\uDD50-\uDD67\uDD7A\uDD95\uDD96\uDDA4\uDDFB-\uDE4F\uDE80-\uDEC5\uDECC\uDED0-\uDED2\uDEEB\uDEEC\uDEF4-\uDEF8]|\uD83E[\uDD10-\uDD3A\uDD3C-\uDD3E\uDD40-\uDD45\uDD47-\uDD4C\uDD50-\uDD6B\uDD80-\uDD97\uDDC0\uDDD0-\uDDE6])|(?:[#\*0-9\xA9\xAE\u203C\u2049\u2122\u2139\u2194-\u2199\u21A9\u21AA\u231A\u231B\u2328\u23CF\u23E9-\u23F3\u23F8-\u23FA\u24C2\u25AA\u25AB\u25B6\u25C0\u25FB-\u25FE\u2600-\u2604\u260E\u2611\u2614\u2615\u2618\u261D\u2620\u2622\u2623\u2626\u262A\u262E\u262F\u2638-\u263A\u2640\u2642\u2648-\u2653\u2660\u2663\u2665\u2666\u2668\u267B\u267F\u2692-\u2697\u2699\u269B\u269C\u26A0\u26A1\u26AA\u26AB\u26B0\u26B1\u26BD\u26BE\u26C4\u26C5\u26C8\u26CE\u26CF\u26D1\u26D3\u26D4\u26E9\u26EA\u26F0-\u26F5\u26F7-\u26FA\u26FD\u2702\u2705\u2708-\u270D\u270F\u2712\u2714\u2716\u271D\u2721\u2728\u2733\u2734\u2744\u2747\u274C\u274E\u2753-\u2755\u2757\u2763\u2764\u2795-\u2797\u27A1\u27B0\u27BF\u2934\u2935\u2B05-\u2B07\u2B1B\u2B1C\u2B50\u2B55\u3030\u303D\u3297\u3299]|\uD83C[\uDC04\uDCCF\uDD70\uDD71\uDD7E\uDD7F\uDD8E\uDD91-\uDD9A\uDDE6-\uDDFF\uDE01\uDE02\uDE1A\uDE2F\uDE32-\uDE3A\uDE50\uDE51\uDF00-\uDF21\uDF24-\uDF93\uDF96\uDF97\uDF99-\uDF9B\uDF9E-\uDFF0\uDFF3-\uDFF5\uDFF7-\uDFFF]|\uD83D[\uDC00-\uDCFD\uDCFF-\uDD3D\uDD49-\uDD4E\uDD50-\uDD67\uDD6F\uDD70\uDD73-\uDD7A\uDD87\uDD8A-\uDD8D\uDD90\uDD95\uDD96\uDDA4\uDDA5\uDDA8\uDDB1\uDDB2\uDDBC\uDDC2-\uDDC4\uDDD1-\uDDD3\uDDDC-\uDDDE\uDDE1\uDDE3\uDDE8\uDDEF\uDDF3\uDDFA-\uDE4F\uDE80-\uDEC5\uDECB-\uDED2\uDEE0-\uDEE5\uDEE9\uDEEB\uDEEC\uDEF0\uDEF3-\uDEF8]|\uD83E[\uDD10-\uDD3A\uDD3C-\uDD3E\uDD40-\uDD45\uDD47-\uDD4C\uDD50-\uDD6B\uDD80-\uDD97\uDDC0\uDDD0-\uDDE6])\uFE0F/g
        const reg = emojiRegex();
        return str.replace(reg, '');
    },
    /**
     * 公钥
     */
    publicKey() {
        return 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANrSKMY2OEtNr+x1Z4PW9tEPAo26unZw' + 'AGwtZ2jcBmcxBKpkeBijb0xRHafjvHveOZSmu/YKAmI3VaMsS0t0io0CAwEAAQ==';
    },
    /**
     * SSO公钥
     */
    ssoPublicKey() {
        return 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJLrS7/xW+zabN+LC/HPuNJcmRO1Hf9ffWSyNfQWTF/wHt+e3z+0AKDKFz332JdozI55hOM/BHEPHB12phGqaUUCAwEAAQ==';
    },
    /**
     * 格式化字符串
     * @param {*} template "Hi, {name}"
     * @param {*} context "{name:'kygeng'}" => "Hi, kygeng"
     */
    formatString(template, context) {
        let tokenReg = /(\\)?\{([^\{\}\\]+)(\\)?\}/g;
        return template.replace(tokenReg, function (word, slash1, token, slash2) {
            if (slash1 || slash2) {
                return word.replace('\\', '');
            }
            let variables = token.replace(/\s/g, '').split('.');
            let currentObject = context;
            let i, length, variable;
            for (i = 0, length = variables.length; i < length; ++i) {
                variable = variables[i];
                currentObject = currentObject[variable];
                if (currentObject === undefined || currentObject === null) return '';
            }
            return currentObject;
        });
    },
    /**
     * 判断是否Android客户端
     */
    isAndroid() {
        let u = window.navigator.userAgent;
        return u.indexOf('Android') > -1 || u.indexOf('Adr') > -1;
    },
    /**
     * 判断是否IOS客户端
     */
    isIOS() {
        let u = window.navigator.userAgent;
        return u.indexOf('iPhone') > -1 || u.indexOf('iPad') > -1;
    },
    /**
     * 格式化数字
     * @param num  {number} 数字
     * @param format  {string} 格式化字符串
     * @author lzhang
     * @address http://numeraljs.com/
     */
    formatNumber(num = 0, format = '0,0') {
        return numeral(num).format(format);
    },
    /**
     * 格式化时间
     * @param {Date} date 需要格式化的日期对象
     *  @param format  {string} 格式化字符串
     */
    formatDate(date = new Date(), format = 'YYYY.MM.DD') {
        return moment(date.getTime()).format(format);
    },
    /**
     * 获取兼容时间串
     */
    getSafeDataStr(dataStr = '') {
        return dataStr.replace(/\-/g, '/');
    },
    /**
     * 转换模板到字符串
     * @param template {string} 模板 demo: "Total is {total}"
     * @param params  {object} 参数 demo: {total: 10}
     * @author lzhang
     */
    template2Text(template = '', params = {}) {
        return template.replace(/\{(\w+)\}/ig, (item) => {
            return params[item.replace(/[\{\}]/ig, '')] || '';
        });
    },
    /**
     * 高亮文本中的关键词
     * @param {string} text 需要高亮的文本
     * @param {string} keyword 关键词（多个关键词用空格分隔）
     * @param {boolean} highlight 是否对text进行高亮
     * @author lzhang
     */
    highLightText(text = '', keyword = '', highlight = true) {
        // 高亮信息
        return highlight && keyword.trim() ? text.replace(new RegExp('(' + keyword.split(' ').join('|') + ')', 'ig'), '<font class="highlight-text" color="red">$1</font>') : text;
    },
    /**
     * 获取url中search部分的参数(相同的参数会自动合并为数组)
     * @param {string} search url中的search部分 demo: ?p1=v1&p2=v2...
     * @param {string} prop 需要读取的属性名称（不传则返回全部属性） demo: p1
     * @author lzhang
     */
    getSearchParam(search = '', prop = '') {
        let params = search.match(/([\w\-]+)=([^&]*)/g);
        let result = {};

        if (params && params.length > 0) {
            params.forEach((item) => {
                let mp = item.split('=');
                if (mp && mp.length >= 1) {
                    if (result.hasOwnProperty(mp[0])) {
                        result[mp[0]] = [].concat(result[mp[0]], mp[1] || '');
                    } else {
                        result[mp[0]] = mp[1] || '';
                    }
                }
            });
        }
        return prop ? result[prop] : result;
    },
    /**
     * 更新search部分的参数值
     * @param {string} search url中的search部分 demo: ?p1=v1&p2=v2...
     * @param {string} prop 需要替换的属性名称（如果没有该属性，则新增） demo: ?p1=v1&p2=v2...
     * @param {string} val 需要替换的属性值
     */
    setSearchParam(search = '', prop = '', val = '') {
        if (search.trim()) {
            if (typeof this.getSearchParam(search, prop) !== 'undefined') {
                // 更新参数
                return search.replace(new RegExp(`(${prop})=([^&]*)`, 'g'), (p) => {
                    return p.replace(/=.*/g, '=' + val);
                });
            } else {
                // 追加参数
                return search += `&${prop}=${val}`;
            }
        } else {
            return `?${prop}=${val}`;
        }

    },
    /**
     * WebViewJavascriptBridge声明
     * @param {*} callback 
     */
    setupWebViewJavascriptBridge(callback) {
        if (window.WebViewJavascriptBridge) {
            return callback(window.WebViewJavascriptBridge);
        } else {
            document.addEventListener('WebViewJavascriptBridgeReady', function () {
                callback(window.WebViewJavascriptBridge);
            }, false);
        }
        if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
        window.WVJBCallbacks = [callback];
        let WVJBIframe = document.createElement('iframe');
        WVJBIframe.style.display = 'none';
        if (this.isAndroid()) {
            WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
        } else {
            WVJBIframe.src = 'https://__BRIDGE_LOADED__';
        }
        document.documentElement.appendChild(WVJBIframe);
        setTimeout(function () { document.documentElement.removeChild(WVJBIframe); }, 0);
    },
    /**
     * 发起微信登录
     */
    loginByWx() {
        let self = this;
        return new Promise(function (resolve, reject) {
            try {
                if (self.isIOS() || self.isAndroid()) {
                    self.setupWebViewJavascriptBridge((bridge) => {
                        bridge.callHandler('loginByWechat', (response) => {
                            resolve(response);
                        });
                    });
                } else {
                    reject('not mobile');
                }
            } catch (error) {
                reject(error);
            }
        });
    },
    /**
     * 同步登录状态到Native
     */
    syncLogin() {
        let self = this;
        return new Promise(function (resolve, reject) {
            try {
                if (self.isIOS() || self.isAndroid()) {
                    self.setupWebViewJavascriptBridge((bridge) => {
                        bridge.callHandler('syncLogin', (response) => {
                            resolve(response);
                        });
                    });
                } else {
                    reject('not mobile');
                }
            } catch (error) {
                reject(error);
            }
        });
    },
    /**
     * 调用APP接口
     * @param {string} fName API方法名 
     * @param {any} params 请求参数 (可选)
     */
    callAppAPI(fName, ...rest) {
        let result = {
            code: 0,
            data: null,
            msg: '',
            err: {}
        };
        return new Promise((resolve, reject) => {
            if (typeof fName === 'string' && fName.trim()) {
                try {
                    if (this.isAndroid() || this.isIOS()) {
                        
                        let args = [...rest];
                        if (args.length && typeof args[0] !== 'undefined') {
                            this.setupWebViewJavascriptBridge((bridge) => {
                                bridge.callHandler(fName, args[0], (response) => {
                                    let r = JSON.parse(response);
                                    if ('code' in r) {
                                        resolve(Object.assign(result, r));
                                    } else {
                                        resolve(Object.assign(result, {
                                            code: 0,
                                            data: r,
                                        }));
                                    }
                                });
                            });
                        } else {
                            this.setupWebViewJavascriptBridge((bridge) => {
                                bridge.callHandler(fName, (response) => {
                                    let r = JSON.parse(response);
                                    if ('code' in r) {
                                        resolve(Object.assign(result, r));
                                    } else {
                                        resolve(Object.assign(result, {
                                            code: 0,
                                            data: r,
                                        }));
                                    }
                                });
                            });
                        }
                    } else {
                        reject(Object.assign(result, {
                            code: 1,
                            msg: 'not mobile',
                        }));
                    }
                } catch (error) {
                    reject(Object.assign(result, {
                        code: -1,
                        err: error
                    }));
                }
            } else {
                reject(Object.assign(result, {
                    code: -1,
                    msg: '方法名称不能为空'
                }));
            }
        }).catch(e => {});
    },
    /**
     * 注册APP回调接口
     * @param {string} fName API方法名 
     * @param {function} jsCallBack 回调处理函数
     */
    registerAppAPI(fName, jsCallBack) {
        let result = {
            code: 0,
            data: null,
            msg: '',
            err: {}
        };
        return new Promise((resolve, reject) => {
            if (typeof fName === 'string' && fName.trim() && jsCallBack) {
                try {
                    if (this.isAndroid() || this.isIOS()) {
                        this.setupWebViewJavascriptBridge((bridge) => {
                            bridge.registerHandler(fName, (data, callback) => {
                                // 执行前端回调
                                callback(jsCallBack(data));

                                resolve(Object.assign(result, {
                                    code: 0,
                                }));
                            });
                        });
                    } else {
                        reject(Object.assign(result, {
                            code: 1,
                            msg: 'not mobile',
                        }));
                    }
                } catch (error) {
                    reject(Object.assign(result, {
                        code: -1,
                        err: error
                    }));
                }
            } else {
                reject(Object.assign(result, {
                    code: -1,
                    msg: '方法名称不能为空'
                }));
            }
        }).catch(e => {});
    },
    /**
     * 开启webview缩放功能
     * @param {*} enable 
     */
    enableZoom(enable) {
        return new Promise((resolve, reject) => {
            if (this.isAndroid() && enable) {
                let metaNode = document.querySelector('meta[name=viewport]');
                metaNode.content = 'width=device-width, initial-scale=1, maximum-scale=4';
            } else if (this.isAndroid() && !enable) {
                let metaNode = document.querySelector('meta[name=viewport]');
                metaNode.content = 'width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no';
            }
            this.callAppAPI('enableZoom', { enable: enable ? 1 : 0})
                .then((data) => {
                    resolve(data);
                }, (err) => {
                    reject(err);
                });
        });
    },
    /**
     * 开启横屏
     * @param {*} enable 
     *            1: 开启横屏
     *            2：关闭横屏
     */
    landscape(enable) {
        return new Promise((resolve, reject) => {
            this.callAppAPI('landscape', { enable: enable ? 1 : 0})
                .then((data) => {
                    resolve(data);
                }, (err) => {
                    reject(err);
                });
        });
    },
    /**
     * 是否横屏
     * @returns {*}
     */
    isLandScape() {
        return window.screen.width > window.screen.height;
    },
    /**
     * APP分享
     * @param {object} params
     *                  type        {string} 分享类型（default、wechat、moments、more）
     *                  title       {string} 分享标题
     *                  desc        {string} 分享描述
     *                  link        {string} 分享链接，该链接域名必须与当前企业的可信域名一致
     *                  imgUrl      {string} 分享图标
     *                  type        {string} 分享类型,music、video或link，不填默认为link
     *                  dataUrl     {string} 如果type是music或video，则要提供数据链接，默认为空
     */
    appShare(params) {
        let { protocol, host } = window.location;

        return new Promise((resolve, reject) => {
            if (params) {
                let imgUrl = params.imgUrl || `${protocol + '//' + host}/static/imgs/icon_share_default.png`;

                params = Object.assign({}, params, {
                    type: params.type || 'default',
                    // 分享摘要（默认追加来源信息）
                    desc: '来自#Analyst.ai# : ' + (params.desc || ''),
                    // 分享图标
                    imgUrl: /^http(s)?/.test(imgUrl) ? imgUrl : ('https:' + imgUrl)
                });

                this.callAppAPI('needShare', params).then((data) => {
                    resolve(data || {});
                }, (err) => {
                    reject({});
                }).catch(e => {
                    reject({});
                });
            } else {
                reject({
                    code: -1,
                    mesg: '缺少参数'
                });
            }
        }).catch(e => {});
    },
    /**
     * APP跳转个人中心
     * @param {object} params
     *                  email       {string} 用户邮箱
     *                  token       {string} 登录令牌
     */
    appGoProfile(params) {
        return new Promise((resolve, reject) => {
            if (params) {
                this.callAppAPI('getMyself', params).then((data) => {
                    resolve(data || {});
                }, (err) => {
                    reject({});
                }).catch(e => {
                    reject({
                        code: -1,
                        mesg: '缺少参数'
                    });
                });
            } else {
                reject({
                    code: -1,
                    mesg: '缺少参数'
                });
            }
        }).catch(e => {});
    },
    /**
     * 获取APP设备信息
     * 
     * @return {
     *      code: 0, 
     *      msg: "", 
     *      data: {
     *          key: "analyst_ai"(写死)
     *      }
     *  }
     */
    appEnvInfo() {
        return new Promise((resolve, reject) => {
            this.callAppAPI('getEnvInfo').then((data) => {
                resolve(data || {});
            }, (err) => {
                reject({});
            }).catch(e => {
                reject({});
            });
        }).catch(e => {});
    },
    /**
     * 在APP中打开指定的URL
     * 
     * @param {*} params 
     *                  url       {string} 请求链接
     */
    appOpenLink(params = {url: ''}) {
        return new Promise((resolve, reject) => {
            this.callAppAPI('openLink', params).then((data) => {
                resolve(data || {});
            }, (err) => {
                reject({});
            }).catch(e => {
                reject({});
            });
        }).catch(e => {});
    },
    /**
     * 改变APP屏幕方向
     * 
     * @param {*} params 
     *                  enable   {string} 1: 开启横屏  0：关闭横屏
     */
    appChangeScreenDirection(params = {enable: '0'}) {
        return new Promise((resolve, reject) => {
            this.callAppAPI('landscape', params).then((data) => {
                resolve(data || {});
            }, (err) => {
                reject({});
            }).catch(e => {
                reject({});
            });
        }).catch(e => {});
    },
    /**
     * 是否处于APP环境
     */
    isInApp() {
        return new Promise((resolve, reject) => {
            let ua = window.navigator.userAgent.toLowerCase();
            if (/analyst\.ai/i.test(ua)) {
                resolve(true);
            } else {
                resolve(false);
            }
        }).catch(e => {});
    },
    /** 
     * 检测是否在app中，同步方法
    */
    checkIsInApp() {
        let ua = window.navigator.userAgent.toLowerCase();
        if (/analyst\.ai/i.test(ua)) {
            return true;
        } else {
            return false;
        }
    },
    /**
     * 获取APP版本信息
     */
    getAppVersion() {
        let ua = window.navigator.userAgent.toLowerCase();
        let version = ua.match(/appversion=[^\s]+/g);
        if (version && version.length) {
            return (version[0] || '').replace('appversion=', '').trim();
        } else {
            return '';
        }
    },
    /**
     * 设置APP状态栏主题色
     * 
     * @param {object} params
     *                  frontColor      {string} 前景色
     *                  backgroundColor {string} 背景色 
     */
    setStatusBarTheme(params = {frontColor: 'black', backgroundColor: '#ffffff'}) {
        return new Promise((resolve, reject) => {
            if (params) {
                this.callAppAPI('getColor', params).then((data) => {
                    resolve(data || {});
                }, (err) => {
                    reject({});
                }).catch(e => {
                    reject({});
                });
            } else {
                reject({
                    code: -1,
                    mesg: '缺少参数'
                });
            }
        }).catch(e => {});
    },
    /**
     * 移除文本中的html标签
     * @param {string} htmlText 待清洗的html内容 demo: <font color="red">顺丰速递</font> => 顺丰速递
     */
    removeDomOfText(htmlText = '') {
        return htmlText.replace(/<\/?[^>]+>/g, '');
    },
    /**
     * 获取安全的html内容
     * @param {string} htmlText 待清洗的html内容 demo: <font color="red">顺丰速递</font> => 顺丰速递
     */
    getSafeDomOfText(htmlText = '') {
        return htmlText.replace(/<\/?(script)[^>]*>/g, '').replace(/\n/g, '');
    },
    /**
     * 格式化当前时间距离某个时间的时间间隔
     * @param {number} time 需要对比的时间戳（秒）
     */
    periodFormat(time, language = 'zh', format = 'YYYY.MM.DD') {
        // 恢复成毫秒
        // time = time * 1000;
        time = new Date().getTime() - time
        // time='20000'
        if (time < 60000) {
            // 不足1分钟
            return '刚刚';
        } else if (time < 3600000) {
            // 不足1小时
            return parseInt((time) / 60000, 10) + '分钟前';
        } else if (time < 24 * 3600000) {
            // 不足1天
            return parseInt((time) / 3600000, 10) + '小时前';
        } else {
            // 
            return this.formatDate(new Date(new Date().getTime() - time), format);
        }
    },
    /**
     * 检查当前登录状态
     */
    checkLoginValid() {
        return Cookies.get('userid') && Cookies.get('_identity');
    },
    /**
     * 删除cookie和localStorage中的信息
     */
    clearAccountInfo() {
        Cookies.remove('userid');
        Cookies.remove('_identity');
        window.localStorage.removeItem('user_email');
        window.localStorage.removeItem('user_openid');
    },
    /**
     * 获取请求form对象
     */
    getSearchParamsObj() {
        let PostForm = function() {};
        let headers = {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'};

        PostForm.prototype.append = function(key, val) {
            this[key] = val;
        };
        PostForm.prototype.get = function(key) {
            return this[key];
        };
        PostForm.prototype.has = function(key) {
            return this.hasOwnProperty(key);
        };

        return {
            form: new PostForm(),
            headers,
        };
    },
    /**
     * 判断是否是在微信环境中
     */
    isWeiXin() {
        let ua = window.navigator.userAgent.toLowerCase();
        // mozilla/5.0 (iphone; cpu iphone os 9_1 like mac os x) applewebkit/601.1.46 (khtml, like gecko)version/9.0 mobile/13b143 safari/601.1
        if (/micromessenger/i.test(ua)) {
            return true;
        } else {
            return false;
        }
    },
    /**
     * 获取当前Web所处环境
     */
    // getWebEnv() {
    //     return /m\.analyst\.ai/.test(window.location.hostname) ? ENV.PRO : ENV.DEV;
    // },
    getLocalIp() {

        axios.get('https://freegeoip.net/json/')
            .then((resp) => {
                window.localStorage.setItem('IP', resp.data.ip);
            })
            .catch((err) => {
                console.error(err);
            });
    },
    /**
     * 搜索日志上报
     * @param {*} keyword 搜索关键字 
     * @param {*} offset  翻页加载索引
     * @param {*} input_from  请求来源
     * @param {*} result 搜索结果
     * @param {*} responseTime 搜索请求响应时间
     * @param {*} request_id 请求信息
     * @param {*} last_request_id 上次请求信息
     */
    postSearchLog(param = {}) {
        if (!Cookies.get('MSESSIONID')) {
            Cookies.set('MSESSIONID', new Date().getTime());
        }
        axios.get('https://act.analyst.ai/performance',{
            params: {
                // 服务名称
                service: param.service || '',
                // 用户ID
                user_id: window.localStorage.getItem('user_email') || '',
                // 用户名称
                user_name: window.localStorage.getItem('user_name') || '',
                // 用户IP
                user_ip: window.localStorage.getItem('IP') || '',
                // 用户session
                session_id: Cookies.get('MSESSIONID') || '',
                // 设备信息
                device_info: window.navigator.userAgent || '',
                // 请求id
                request_id: param.request_id || '',
                // 查询语句
                query: param.keyword || '',
                // 渠道信息 
                channel: this.getOperatChannel(),
                // 请求来源
                input_from: param.input_from || '',
                // 响应时间
                first_response: param.responseTime || '',
                // 翻页信息
                start: param.offset || '',
                // 上次请求信息
                last_request_id: param.last_request_id || '',
                // 语种
                language: this.getClientLanguage().toUpperCase(),
                // 版本号
                version: this.getAppVersion()
            }
        });
    },
    /**
     * 点击日志上报
     * @param {*} click_id   搜图 就是图片id 搜表就是表格id
     * @param {*} click_pos  点击位置
     */
    postClickLog(param = {}) {
        if (!Cookies.get('MSESSIONID')) {
            Cookies.set('MSESSIONID', new Date().getTime());
        }
        axios.get('https://act.analyst.ai/hotclick',{
            params: {
                // 服务名称
                service: param.service || '',
                // 用户ID
                user_id: window.localStorage.getItem('user_email') || '',
                // 用户名称
                user_name: window.localStorage.getItem('user_name') || '',
                // 用户IP
                user_ip: window.localStorage.getItem('IP') || '',
                // 用户session
                session_id: Cookies.get('MSESSIONID') || '',
                // 请求id
                request_id: param.request_id || '',
                // 点击结果id
                click_id: param.click_id || '',
                // 点击位置 image_title,title,favorite,pic,export_data,export_pic,origin_pic
                click_pos: param.click_pos || '',
                // 渠道信息 
                channel: this.getOperatChannel(),
                // 语种
                language: this.getClientLanguage().toUpperCase(),
                // 版本号
                version: this.getAppVersion()
            }
        });
    },
    /**
     * 获取当前渠道
     */
    getOperatChannel() {
        return this.checkIsInApp() ? (this.isAndroid() ? 'app_and' : 'app_ios') : 'm_site';
    },
    /**
     * 获取当前网站的国际化语言类型
     */
    getClientLanguage() {
        if (window.localStorage && window.localStorage.getItem) {
            return window.localStorage.getItem('language') != 'en' ? 'zh-CN' : 'en';
        }
        return 'zh-CN';
    },
    /**
     * H5跳转新视图
     * 
     * @param {string} type     需要跳转的视图类型（h5[default]/native）
     * @param {object} url      需要跳转的URL
     * @param {array}  auth     授权参数
     * @param {object} params   附加参数
     */
    goNextView(history, config = {}) {
        if (this.checkIsInApp()) {
            // APP环境 (v1.2.0 版本开始支持原生跳转)
            if (this.getAppVersion() >= '1.2' && config.type) {
                // 视图地址
                let page_url = '';

                if (config.type === 'h5') {
                    let {pathname = '', search = '', hash = ''} = config.url;
                    page_url = '/portal' + pathname + search + hash;
                }

                return new Promise((resolve, reject) => {
                    this.callAppAPI('goNewPage', {
                        // 跳转方式
                        type: config.type || 'h5',
                        // 跳转原声视图名称
                        page: config.page,
                        // 附加参数
                        pageParams: {
                            url: page_url,
                            // 附加参数
                            ...(config.params || {})
                        }
                    }).then((data) => {
                        resolve(data || {});
                    }, (err) => {
                        reject({});
                    }).catch(e => {
                        reject({});
                    });
                }).catch(e => {});
            } else {
                // H5跳转
                history.push(config.url || {});
            }
        } else if (this.isWeiXin()) {
            // 微信环境（不支持location push）
            let {pathname = '', search = '', hash = ''} = config.url;
            let {protocol, host} = window.location;
            window.location.href = protocol + '//' + host + '/portal' + pathname + search + hash;
        } else {
            // H5跳转
            history.push(config.url || {});
        }
    },
    /**
     * H5回跳上一个视图
     */
    goPrevView(history, config = {}) {
        if (this.checkIsInApp()) {
            if (this.getAppVersion() >= '1.2') {
                return new Promise((resolve, reject) => {
                    this.callAppAPI('abcGoBack').then((data) => {
                        resolve(data || {});
                    }, (err) => {
                        reject({});
                    }).catch(e => {
                        reject({});
                    });
                }).catch(e => {});
            } else {
                if (Object.keys(config.url || {}).length) {
                    history.push(config.url || {});
                } else {
                    history.go(-1);
                }
            }
        } else {
            if (Object.keys(config.url || {}).length) {
                history.push(config.url || {});
            } else {
                history.go(-1);
            }
        }
    },
    /**
     * 获取唤起APP链接
     * @param {string} url      需要跳转的视图地址
     * @param {object} params   附加参数
     */
    getURLSchemaLink(url = '', params = {}) {
        const URLSchema = this.isAndroid() ? 'analystsearch' : 'analystSearch';
        const HOST      = this.isAndroid() ? 'analyst.ai' : '';

        // 需要打开的视图地址
        let openUrl = encodeURIComponent(url);
        // 附加参数
        let optionParams = [];
        for (let k in params) {
            optionParams.push(`${k}=${params[k]}`);
        }
        openUrl = `${openUrl}${optionParams.length ? '&' + optionParams.join('&') : ''}`;

        return {
            appSchema: `${URLSchema}://${HOST}?url=${openUrl}`,
            openUrl: openUrl
        };
    },
    /**
     * H5唤起Native
     * @param {string} url     需要跳转的视图地址
     * @param {object} params   附加参数
     */
    callAppView(url = '', params = {}) {
        const isWeiXin     = this.isWeiXin();
        const isAnalystAPP = this.checkIsInApp();

        if (isAnalystAPP) {
            return;
        }

        let {protocol, host}     = window.location;
        let {appSchema, openUrl} = this.getURLSchemaLink(url, params);

        if (isWeiXin) {
            // 跳转外部打开提示页
            window.location.href = `${protocol}//${host}/portal/notsupport-appcall?url=${openUrl}`;
        } else {
            // 唤起APP
            window.location.href = appSchema;

            setTimeout(function(){
                // 未安装APP时打开下载地址
                window.location.href = `${protocol}//${host}/portal/app-download?url=${openUrl}`;
            }, 3000);
        }
    },
    /**
     * 跳转APP下载地址
     */
    // getAPPDownloadURL(schema) {
    //     let {hostname}   = window.location;
    //     let download_url = '';

    //     const isAndroid = this.isAndroid();

    //     if (/m.analyst.ai/.test(hostname)) {
    //         // 正式环境
    //         if (isAndroid) {
    //             download_url = APP_DOWNLOAD_LINK.PRO.ANDROID;
    //         } else {
    //             download_url = APP_DOWNLOAD_LINK.PRO.IOS;
    //         }
    //     } else {
    //         // 预发布环境
    //         if (isAndroid) {
    //             download_url = APP_DOWNLOAD_LINK.DEV.ANDROID;
    //         } else {
    //             download_url = APP_DOWNLOAD_LINK.DEV.IOS;
    //         }
    //     }
    //     return download_url;
    // },
    /**
     * 从搜索运营位跳转原声搜索列表
     */
    openAppSearchResult(params = {}) {
        if (this.checkIsInApp()) {
            if (this.getAppVersion() >= '1.2') {
                return new Promise((resolve, reject) => {
                    this.callAppAPI('searchLink', {
                        data: {
                            type: 'h5',
                            pageParams: {
                                ...params
                            }
                        },
                        page: ''
                    }).then((data) => {
                        resolve(data || {});
                    }, (err) => {
                        reject({});
                    }).catch(e => {
                        reject({});
                    });
                }).catch(e => {});
            }
        }
    },
    /**
     * 关闭原声加载动画
     */
    hideNativeLoading() {
        let isAnalystAPP = this.checkIsInApp();
        let appVersion   = this.getAppVersion();

        if (isAnalystAPP) {
            if (appVersion >= '1.2.3') {
                return new Promise((resolve, reject) => {
                    this.callAppAPI('hideLoading').then((data) => {
                        resolve(data || {});
                    }, (err) => {
                        reject({});
                    }).catch(e => {
                        reject({});
                    });
                }).catch(e => {});
            }
        }
    },
    /**
     * 刷新当前页面
     * 
     * @param {object} params
     *                  keyword      {string} 搜索词
     *                  url          {string} 跳转地址
     */
    reloadPageWithPatams(params = {keyword: '', url: ''}) {
        return new Promise((resolve, reject) => {
            if (params) {
                this.callAppAPI('clickContentChange', params).then((data) => {
                    resolve(data || {});
                }, (err) => {
                    reject({});
                }).catch(e => {
                    reject({});
                });
            } else {
                reject({
                    code: -1,
                    mesg: '缺少参数'
                });
            }
        }).catch(e => {});
    },
    /**
     * 获取指定对象的数据类型
     * 
     * @param {any} obj 
     */
    getObjType(obj) {
        if (typeof obj === 'undefined') {
            return 'undefined';
        }
        let type = Object.prototype.toString.call(obj);
        if (type.indexOf('String') != -1) {
            return 'string';
        } else if (type.indexOf('Number') != -1) {
            return 'number';
        } else if (type.indexOf('Array') != -1) {
            return 'array';
        } else if (type.indexOf('Null') != -1) {
            return 'null';
        } else if (type.indexOf('Boolean') != -1) {
            return 'boolean';
        } else if (type.indexOf('Object') != -1) {
            return 'object';
        }
        return 'unknown';
    },
    /**
     * 把像素转换为rem
     * 
     * @param {number} px 像素值
     */
    pxToRem(px) {
        let baseFontSize = parseFloat(document.documentElement.style.fontSize || '0');
        return px / baseFontSize;
    },
    /**
     * 把rem转换为像素
     * 
     * @param {number} rem rem值
     */
    remToPx(rem) {
        let baseFontSize = parseFloat(document.documentElement.style.fontSize || '0');
        return rem * baseFontSize;
    },
    downloadImg(link = '', name = '') {
        let download = document.createElement('a');
        download.download = name;
        download.href = link;
        download.style.display = 'none';
        document.body.appendChild(download);
        download.click();
        setTimeout(function() {
            // download.remove();
        }, 500);
    },
    processEmoji(text = "") {
        let r = twemoji.parse(
          text,
          {
            callback: function(icon, options) {return '#';},
            attributes: function(icon) {
              return {code: twemoji.convert.toCodePoint(icon)}
            },
            className: "emoji",
          }
        );
        r = r.replace(/<img[^(\/>)]+\/>/ig, function(word) {
          let codes = word.match(/code="[0-9a-z]+"/ig);
          if (!codes) {
            return ""
          }
          let [code = ""] = codes;
          code = code.replace(/"/ig,'').replace('code=','');
          return `<span class="emoji emoji${code}"></span>`;
        })
        return r;
    }
};

// 通用的error事件 组要是处理 200后的success为 false事件
export function generalErrorHandle(res, dontShow) {
    let {
        success
    } = res;
    if (success) {
        return true
    } else {
        if (dontShow) return;
        let { message = "" } = res;
        // debugger;
        // notification.error({
        //     message: '错误',
        //     description: message,
        // });
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

// 生成guid  
export function guid() {
    return 'xxxxxxxx_xxxx_4xxx_yxxx_xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        let r = Math.random() * 16 | 0,
            v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}