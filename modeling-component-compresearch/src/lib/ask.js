/**
 * @description 异步请求核心
 * @author dhhuang1
 * @date 2018/5/8 下午2:33:41
 */

import axios from 'axios'
import Cookies from 'js-cookie';
import uuidv4 from 'uuid/v4';
import api from '../config'
import { matchDomain } from './match_domain'

let env = 'prod'
function requireEnv() {
    if (window.abc_sso_env) {
        env = window.abc_sso_env || 'prod';
    } else {
        if (window.location.host.indexOf('localhost') !== -1) {
            env = 'pre';
        } else if (window.location.host.indexOf('-dev') !== -1) {
            env = 'dev';
        } else if (window.location.host.indexOf('-pre') !== -1) {
            env = 'pre'
        } else {
            env = 'prod';
        }
    }
}
if (window.loginType === 'sso') {
    requireEnv()
}

const baseURL = matchDomain(env)

export default function ask(name, opt = {}) {

    //取传进来的用户信息
    let {
        headers,
        data,
        params = {},
        responseType,
        apiCfg = {}
    } = opt
    /**
     * 获取接口信息
     * 如果后期涉及到权限
     * 可以在接口信息里面
     * 设定 并取到
     */
    let {
        method,
        url
    } = Object.assign({}, api[name], apiCfg);

    let instance = axios.create({
        baseURL,
        // `withCredentials` 表示跨域请求时是否需要使用凭证
        withCredentials: false
    })

    // 响应中间处理层
    instance.interceptors.response.use(function (response) {
        // 请求成功后 处理在此
        return response.data
    }, function (error) {
        if (error && error.response && error.response.status === 401) {
            // Cookies.remove('token');
            // Cookies.remove('userId');
            // window.sso_tologin && window.sso_tologin();
        }
        // 请求失败 错误在此
        return Promise.reject(error)
    });

    // const userId = '80115926027532560';
    // const token = '$2a$10$SPDYpC8FLkRWUJzT0FyLVOKzaVlIRr4';
    const userId = Cookies.get('userId');
    const token = Cookies.get('token');
    
    params["userId"] = userId;
    params["token"] = token;

    params["request_id"] = uuidv4();
    if (method != "GET") {
        // debugger
        if (!data) {
            data = {};
        }
        data["userId"] = userId;
        data["token"] = token;
        params["request_id"] = uuidv4();
    }
    let promise = instance.request({
        responseType,
        url,
        method,
        headers,
        params,
        data
    })

    return promise
}