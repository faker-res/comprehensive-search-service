var fs = require('fs')
var path = require('path')
var stripJsonComments = require('strip-json-comments')

var resolveMockDataPath = function(mockDir, filePath) {
    if (filePath.indexOf('/') === 0) {
        filePath = filePath.slice(1, filePath.length)
    }
    return path.resolve(mockDir, filePath)
}

var readFile = function(extname) {
    extname = extname || '.json'
    return function(filePath) {
        filePath += extname
        let exists = fs.existsSync(filePath)
        if (exists) {
            return fs.readFileSync(filePath, 'UTF-8')
        }
        return exists
    }
}

var readJSONFile = readFile()
var readMockData = function(filePath) {
    return readJSONFile(filePath)
}
var mockDir = path.resolve(__dirname, '../mock')

var getFilePath = require('./mockRouterMap').getFilePath

var initMockMiddleware = function(request, response, next) {
    var requestPath = request.path
    var method = request.method.toLowerCase()
    let mockDataPath = getFilePath(requestPath, method, request.xhr)
    if (mockDataPath) {
        let content = readMockData(resolveMockDataPath(mockDir, mockDataPath))
        if (content) {
            response.status(200).json(JSON.parse(stripJsonComments(content)))
        } else {
            var NO_FOUND_CODE = 404
            response.json(NO_FOUND_CODE, {
                code: NO_FOUND_CODE,
                msg: '接口数据未定义'
            })
        }
    } else {
        next()
    }
}

module.exports = initMockMiddleware
