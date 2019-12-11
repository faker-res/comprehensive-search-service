
/**
 * @description sso拦截封装
 * @date 2018.03.26
 * @author xuzhaobin
 * @email zhbxu@abcft.com
 */
// jscookie
!function(e){var n=!1;if("function"==typeof define&&define.amd&&(define(e),n=!0),"object"==typeof exports&&(module.exports=e(),n=!0),!n){var o=window.Cookies,t=window.Cookies=e();t.noConflict=function(){return window.Cookies=o,t}}}(function(){function g(){for(var e=0,n={};e<arguments.length;e++){var o=arguments[e];for(var t in o)n[t]=o[t]}return n}return function e(l){function C(e,n,o){var t;if("undefined"!=typeof document){if(1<arguments.length){if("number"==typeof(o=g({path:"/"},C.defaults,o)).expires){var r=new Date;r.setMilliseconds(r.getMilliseconds()+864e5*o.expires),o.expires=r}o.expires=o.expires?o.expires.toUTCString():"";try{t=JSON.stringify(n),/^[\{\[]/.test(t)&&(n=t)}catch(e){}n=l.write?l.write(n,e):encodeURIComponent(String(n)).replace(/%(23|24|26|2B|3A|3C|3E|3D|2F|3F|40|5B|5D|5E|60|7B|7D|7C)/g,decodeURIComponent),e=(e=(e=encodeURIComponent(String(e))).replace(/%(23|24|26|2B|5E|60|7C)/g,decodeURIComponent)).replace(/[\(\)]/g,escape);var i="";for(var c in o)o[c]&&(i+="; "+c,!0!==o[c]&&(i+="="+o[c]));return document.cookie=e+"="+n+i}e||(t={});for(var a=document.cookie?document.cookie.split("; "):[],s=/(%[0-9A-Z]{2})+/g,f=0;f<a.length;f++){var p=a[f].split("="),d=p.slice(1).join("=");this.json||'"'!==d.charAt(0)||(d=d.slice(1,-1));try{var u=p[0].replace(s,decodeURIComponent);if(d=l.read?l.read(d,u):l(d,u)||d.replace(s,decodeURIComponent),this.json)try{d=JSON.parse(d)}catch(e){}if(e===u){t=d;break}e||(t[u]=d)}catch(e){}}return t}}return(C.set=C).get=function(e){return C.call(C,e)},C.getJSON=function(){return C.apply({json:!0},[].slice.call(arguments))},C.defaults={},C.remove=function(e,n){C(e,"",g(n,{expires:-1}))},C.withConverter=e,C}(function(){})});

var just_env = 'prod'; 
// 默认为正式环境
if(window.abc_sso_env){
    // 非 dev pre prod 域名规范需挂载变量
    just_env = abc_sso_env;
}else{
    // 如果没有挂载默认符合规范
    if(window.location.host.indexOf('localhost')!==-1) {
        just_env = 'pre';
    } else if(window.location.host.indexOf('-dev')!==-1) {
        // 测试环境
        just_env = 'dev';
    }else if(window.location.host.indexOf('-pre')!==-1){
        just_env = 'pre';
    }else{
        just_env = 'prod';
    }
}
var sso_baseUrl = 'https://passport.abcfintech.com';
switch(just_env){
    case 'local' : 
    case 'dev' : 
        sso_baseUrl = 'https://passport.abcfintech.com';
        break;
    case 'pre' : 
        sso_baseUrl = 'https://passport-pre.abcfintech.com';
        break;
    case 'prod' : 
        sso_baseUrl = 'https://passport.abcfintech.com';
        break;
    case 'case trial' :
        sso_baseUrl = 'https://passport.abcfintech.com';
        break;    
    default : 
        console.log('请定义正确的环境信息');
}
function sso_ajax(obj){
    // 默认参数
    var defaults = {
        type : 'get',
        data : {},
        url : '#',
        dataType : 'text',
        async : true,
        success : function(data){console.log(data)},
        error: function(data){console.log(data)}
    }
    // 处理形参，传递参数的时候就覆盖默认参数，不传递就使用默认参数
    for(var key in obj){//把输入的参数与设置的默认数据进行覆盖更新
        defaults[key] = obj[key];
    }
    // 1、创建XMLHttpRequest对象
    var xhr = null;
    if(window.XMLHttpRequest){
        xhr = new XMLHttpRequest();
    }else{
        xhr = new ActiveXObject('Microsoft.XMLHTTP');// 兼容ie的早期版本
    }
    // 把对象形式的参数转化为字符串形式的参数
    /* {username:'zhangsan','password':123} 转换为 username=zhangsan&password=123 */
    var param = '';
    for(var attr in obj.data){
        param += attr + '=' + obj.data[attr] + '&';
    }
    if(param){//substring(start, end)截取字符串去掉最后的&符号
        param = param.substring(0,param.length - 1);
    }
    // 处理get请求参数并且处理中文乱码问题
    if(defaults.type == 'get'){
        defaults.url += '?' + encodeURI(param);
    }
    // 2、准备发送（设置发送的参数）
    xhr.open(defaults.type,defaults.url,defaults.async); // 处理post请求参数并且设置请求头信息（必须设置）
    var data = null;
    if(defaults.type == 'post'){
        data = param;
        xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    //post模式下必须加的请求头，这个请求头是告诉服务器怎么去解析请求的正文部分。
    }
    // 3、执行发送动作
    xhr.send(data);
    // 处理同步请求，不会调用回调函数
    if(!defaults.async){
        if(defaults.dataType == 'json'){
            return JSON.parse(xhr.responseText);
        }else{
            return xhr.responseText;
        }
    }
    // 4、指定回调函数（处理服务器响应数据）
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4){
            //4 获取数据成功
            if(xhr.status == 200){
                //200 获取的数据格式正确
                var data = xhr.responseText;
                if(defaults.dataType == 'json'){
                    // data = eval("("+ data +")");
                    data = JSON.parse(data);
                    //JSON.parse把获取带的json格式的数据转化为js的对象形式可以使用
                }
                defaults.success(data);//回调函数
            }else{
                defaults.error(xhr.status);
            }
        }else{
            defaults.error(xhr.readyState);
        }
    }
}
function sso_getQueryString(name) {
    // 参数
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    }
    return undefined;
}
function getBackUrl() {
    var local = window.location;
    var baseUrl = local.protocol + '//' + local.host + local.pathname;
    
    if(local.hash){
        // 不支持hash 回调到域名 不再携带参数
        return encodeURIComponent(baseUrl);
    }else{
        // 链接已经存在ticket+userid 需要去掉
        var search = local.search;
        // del ?
        
        if(search){
            var query = search.substr(1);
            var _url = funcUrlDel(query,'ticket');
            _url = funcUrlDel(_url,'userId');
            return encodeURIComponent(baseUrl + '?' + _url + local.hash);
        }else{
            return encodeURIComponent(baseUrl);
        }
    }
}
// 删除参数
function funcUrlDel(url,name){
    if (url.indexOf(name)>-1) {
        var obj = {}
        var arr = url.split("&");
        for (var i = 0; i < arr.length; i++) {
            arr[i] = arr[i].split("=");
            obj[arr[i][0]] = arr[i][1];
        };
        delete obj[name];
        var delSearch = JSON.stringify(obj).replace(/[\"\{\}]/g,"").replace(/\:/g,"=").replace(/\,/g,"&");
        return delSearch
    }else{
        return url;
    }
}
function sso_getDomain(){
    var _location = window.location.hostname;
    var _index = _location.indexOf('.');
    var domain = undefined;
    if (
      !(_location == 'localhost') &&
      !/(?=(\b|\D))(((\d{1,2})|(1\d{1,2})|(2[0-4]\d)|(25[0-5]))\.){3}((\d{1,2})|(1\d{1,2})|(2[0-4]\d)|(25[0-5]))(?=(\b|\D))/.test(
        _location
      )
    ) {
      domain = _location.slice(_index);
    }
    return domain
  }
  
function sso_getTokenByTicket() {
    // 当ticket存在时获取token
    var resData = sso_ajax({
        type: "get",
        url: sso_baseUrl + '/sso/getTokenByTicket',
        data: {
            ticket: sso_getQueryString('ticket'),
            userId: sso_getQueryString('userId')
        },
        // 同步请求
        async: false,
        dataType: 'json'
    })
    var domain = sso_getDomain()
    // 写入cookie
    if (resData.errorCode != 511 && resData.errorCode != 41) {
        Cookies.remove('token', { domain: domain});
        Cookies.remove('userId', { domain: domain});
        
        Cookies.set('token', resData.data, { expires: 30, domain: domain })
        Cookies.set('userId', sso_getQueryString('userId'), { expires: 30, domain: domain })
    }else{
        sso_checkToken();
    }
}
function sso_checkToken(){
    var local = window.location;
    var domain = sso_getDomain();
    var resData = sso_ajax({
        type: "post",
        url: sso_baseUrl + '/sso/verifyToken',
        data: {
            token: Cookies.get('token'),
            userId: Cookies.get('userId')
        },
        // 同步请求
        async: false,
        dataType: 'json'
    })
    // 写入cookie
    if (resData.errorCode != 0) {
        // token无效
        Cookies.remove('token', {domain: domain});
        Cookies.remove('userId', {domain: domain});
        sso_tologin();
    }
}
function sso_logout() {
    // 退出登录
    var local = window.location;
    var domain = sso_getDomain(); 
    sso_ajax({
        type: "post",
        url: sso_baseUrl + '/sso/logout',
        data: {
            userId: Cookies.get('userId')
        },
        dataType: 'json',
        success: function(){
            Cookies.remove('token', {domain: domain});
            Cookies.remove('userId', {domain: domain});
            sso_tologin();
        }
    })
}
function sso_tologin() {
    // modify by kygeng
    // token无效优先进入默认首页，等待用户主动发起登录
    // 默认首页不再重定向
    if (window.location.pathname === '/') return;
    var _url = window.location.search;
    // 链接已经存在ticket+userid 需要去掉
    if(window.location.search){
        var query = window.location.search.substr(1);
        _url = funcUrlDel(query,'ticket');
        _url = funcUrlDel(_url,'userId');
        _url = '?' + _url; 
    }

    window.location.href = `/?redirect_url=${encodeURIComponent(window.location.pathname + _url)}`;

    // 去登陆页面 可以供子产品调用
    // if(window.abc_sso_terminal){
    //     // 定义了产品ID
    //     window.location.href =  sso_baseUrl + '/sso-login.html?back=' + 
    //     getBackUrl() + '&sso_terminal=' + window.abc_sso_terminal;
    // }else{
    //     window.location.href =  sso_baseUrl + '/sso-login.html?back=' + 
    //     getBackUrl();
    // }
}
(function(){
    if(sso_getQueryString('ticket') && sso_getQueryString('userId')){
        // 登录跳转回来
        sso_getTokenByTicket();
    }else{
        // 非登录跳转回来 验证token
        var local = window.location;
        if(!Cookies.get('token')){
           
            // cookie不存有token
            sso_tologin();
        }else{
            // cookie存有token
            sso_checkToken();
        }
    }
})()