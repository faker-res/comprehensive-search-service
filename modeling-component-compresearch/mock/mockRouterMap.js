const path2Regexp = require('path-to-regexp')
const MOCK_DATA_DIR = './data'
const fs = require('fs')
const path = require('path')
const ROUTE_MAP = './routeMap.json'

const initMockRouterReg = function (map) {
    var regMap = new Map()
    for (var pathReg in map) {
        var keyArr = map[pathReg].split(/\s/)
        var pathInfo = {}, urlReg
        if (keyArr.length > 1) {
            urlReg = keyArr[1]
            pathInfo.method = keyArr[0].toLowerCase()
        } else {
            urlReg = keyArr[0]
        }
        pathInfo.mockFile = MOCK_DATA_DIR + map[pathReg]
        regMap.set(path2Regexp(urlReg), pathInfo)
    }
    return regMap
}

let routeMapPath = path.join(__dirname, ROUTE_MAP)
let routeMap = JSON.parse(fs.readFileSync(routeMapPath))

const pathRegMap = initMockRouterReg(routeMap)

module.exports = {
    getFilePath (requestPath, method, isXhr) {
        var filePath = false
        pathRegMap.forEach(function (pathInfo, urlReg) {
            var limitMethod = pathInfo.method
            if (urlReg.test(requestPath)) {
                filePath = pathInfo.mockFile
                if (limitMethod && limitMethod !== method && isXhr) {
                    filePath = false
                }
            }
        })
        return filePath
    }
}
