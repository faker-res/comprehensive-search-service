const querystring = require('querystring')
const path = require('path')
const os = require('os')
const rimraf = require('rimraf')
const async = require('async')
const exec = require('child_process').exec
const { key } = require('./proxy.config')
const globule = require('globule')

const neiBaseDir = path.resolve(os.homedir(), 'localMock', key)
const neiServerConfigFolder = path.resolve(neiBaseDir, './nei**')
let configPathArr = globule.find(neiServerConfigFolder)
let neiServerConfig = path.resolve(configPathArr[0], './server.config.js')

let lock = false

let reloadServerConfig = () => {
    if (lock) {
        return
    }
    lock = true
    // 只需要更新server.config.js文件（nei线上发生改变[添加、删除接口]，
    // nei update或nei update -w都会获得最新server.config.js文件）
    console.log('reload server config start')
    const neiBuild = `nei build -k ${key} -o ${neiBaseDir}`
    const neiUpdate = `cd ~/localMock/${key} && nei update`
    const cmdStr = (configPathArr && configPathArr.length) ? neiUpdate : neiBuild

    exec(cmdStr, (error, stdout, stderr) => {
        if (error) {
            console.log('cmd exec error:', error)
            console.log('cmd exec stdout:', stdout)
            console.log('cmd exec stderr:', stderr)
            return
        }
        console.log('reload success')
        lock = false
    })
}

const initMockRouterReg = function (map) {
    var regMap = new Map()
    for (var pathReg in map) {
        let url = pathReg.split(' ')[1]
        var content = map[pathReg]
        var pathInfo = {}
        pathInfo.method = pathReg.split(' ')[0].toLowerCase()
        pathInfo.id = content.id

        regMap.set(url, pathInfo)
    }
    return regMap
}

let existUrlAndGetId = (requestPath, method, isXhr) => {

    let { routes } = require(neiServerConfig)
    let pathRegMap = initMockRouterReg(routes)
    let existUrl = false
    let id = null
    pathRegMap.forEach(function (pathInfo, url) {
        let limitMethod = pathInfo.method
        if (url === requestPath) {
            existUrl = true
            id = pathInfo.id
            if (limitMethod && limitMethod !== method && isXhr) {
                existUrl = false
            }
        }
    })
    return { existUrl, id }
}

var getFromNEISite = (requestPath, method, id, callback) => {
    let params = {
        path: requestPath,
        type: 3, // api代理：3，页面代理：1
        key: key,
        id,
        method: method
    }
    let url = 'https://nei.netease.com/api/mockdata?' + querystring.stringify(params)

    require('https').get(url, function (res) {
        let ret = []
        res.on('data', function (chunk) {
            ret.push(chunk.toString())
        })
        res.on('end', function () {
            let json = null
            try {
                json = JSON.parse(ret.join(''))
            } catch (ex) {
                // ignore
            }
            if (json && json.code === 200) {
                // 成功
                if (json.result.error.length) {
                    console.log(`错误: ${json.result.error.map(err => err.message).join(', ')}`)
                }
                // 真正的 mock 数据
                callback(json.result.json)
            } else {
                callback(ret.join(''))
            }
        })
    }).on('error', function (error) {
        callback(error.message)
    })
}

var neiOnlineMockMiddleware = (request, response, next) => {
    let requestPath = request.path
    let method = request.method.toLowerCase()

    // 实时获取nei线上接口信息
    reloadServerConfig()

    let { existUrl, id } = existUrlAndGetId(requestPath, method, request.xhr)
    if (existUrl) {
        getFromNEISite(requestPath, method, id, (json) => {
            if (json) {
                response.status(200).json(json)
            } else {
                var NO_FOUND_CODE = 404
                response.json(NO_FOUND_CODE, {
                    code: NO_FOUND_CODE,
                    msg: '接口数据未定义'
                })
            }
        })
    } else {
        next()
    }
}

module.exports = neiOnlineMockMiddleware
