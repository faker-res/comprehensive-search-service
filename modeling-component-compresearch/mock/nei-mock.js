const exec = require('child_process').exec
const fs = require('fs')
const path = require('path')
const os = require('os')
const globule = require('globule')
const rimraf = require('rimraf')
const async = require('async')
const { key } = require('./proxy.config')


const neiBaseDir = path.resolve(os.homedir(), 'localMock', key)
const copyTar = path.join(__dirname, './../mock/data')

// 判断文件/文件夹是否已存在
function fsExistsSync (path) {
    try {
        fs.accessSync(path, fs.F_OK)
    } catch (e){
        return false
    }
    return true
}

// 复制文件
let copyFile = (src, tar, cb) => {
    console.log('file update:', tar)
    let rs = fs.createReadStream(src)
    rs.on('error', (error) => {
        if (error) {
            console.log('file read error:', src)
        }
        cb && cb(error)
    })

    let ws = fs.createWriteStream(tar)
    ws.on('error', (error) => {
        if (error) {
            console.log('file write error:', tar)
        }
        cb && cb(error)
    })
    ws.on('close', (ex) => {
        cb && cb(ex)
    })

    rs.pipe(ws)
}

// 复制文件夹
let copyFolder = (srcDir, tarDir, cb) => {
    fs.readdir(srcDir, (error, files) => {
        if (error) {
            console.log('readdir error:', error)
            cb && cb(error)
            return
        }
        files.forEach((file) => {
            let srcPath = path.join(srcDir, file)
            let tarPath = path.join(tarDir, file)
            fs.stat(srcPath, (error, stats) => {
                if (error) {
                    console.log('stat error:', error)
                    return
                }
                if (stats.isDirectory()) {
                    console.log('mkdir:', tarPath)
                    fs.mkdir(tarPath, (error) => {
                        if (error && error.code !== 'EEXIST') {
                            console.log('mrdir error:', error)
                            return
                        }
                        // 无异常 或 已经存在的文件夹(error.code === 'EEXIST')，复制文件夹内容
                        copyFolder(srcPath, tarPath, cb)
                    })
                } else if (file === 'data.json') {
                    // 是文件，且文件名是 data.json
                    let newTarDir = tarDir + '.json'

                    if (!fsExistsSync(newTarDir)) {
                        copyFile(srcPath, newTarDir, cb)
                    } else {
                        console.log('file exist:', newTarDir)
                    }

                    // 删除data.json的上一级目录
                    rimraf(tarDir, (error) => {
                        if (error) {
                            console.log('rmdir error:', error)
                            return
                        }
                    })
                }
            })
        })
        // 为空时直接回调
        files.length === 0 && cb && cb('files is empty')
    })
}

let createMockData = (neiBaseDir) => {
    const copySrcGET = path.join(neiBaseDir, './mock/get')
    const copySrcPOST = path.join(neiBaseDir, './mock/post')
    copyFolder(copySrcGET, copyTar, (error) => {
        if (error) {
            console.log('copy get error:', error)
            return
        }
    })
    copyFolder(copySrcPOST, copyTar, (error) => {
        if (error) {
            console.log('copy post error:', error)
            return
        }
    })
}

// 从nei的server.config.js提取route map
let routeMap = (folderPath) => {
    let srcPath = path.resolve(folderPath, './server.config.js')
    let tarPath = path.join(__dirname, './routeMap.json')
    let serverContent = require(srcPath)
    let { routes } = serverContent

    // 将格式化后的数据写入tarPath所在文件
    fs.writeFile(tarPath, formatRoutes(routes), (error) => {
        if (error) {
            console.log('write file error:', error)
            return
        }
        console.log('update route map: success')
    })
}

// format server.config.js 的 routes，返回格式化后的对象
let formatRoutes = (routes) => {
    let result = {}
    for (let key in routes) {
        result[key] = key.split(' ')[1]
    }
    // JSON.stringify后两个参数可以让json文件换行、4空格缩进 格式化显示
    return JSON.stringify(result, null, 4)
}

let softUpdate = (cb) => {
    const neiServerConfig = path.resolve(neiBaseDir, './nei**')
    let configPathArr = globule.find(neiServerConfig)

    // 从nei拉取mock数据
    const neiBuild = `nei build -k ${key} -o ${neiBaseDir}`
    // nei update: 更新接口文件，但本地已存在的不覆盖；
    // nei update -w: 覆盖已存在的文件，但本地已存在、nei已删除的文件不处理（需要用户手动删除）。
    // const neiUpdate = `cd ~/localMock/${key} && nei update -w`
    const neiUpdate = `cd ~/localMock/${key} && nei update`
    const cmdStr = (configPathArr && configPathArr.length) ? neiUpdate : neiBuild
    console.log('nei exec start:', cmdStr)

    // 每次执行命令，总是先 nei build 或 nei update，然后更新本地的数据
    exec(cmdStr, (error, stdout, stderr) => {
        console.log('nei exec end')
        if (error) {
            cb && cb('cmd exec error')
            console.log('cmd exec error:', error)
            console.log('cmd exec stdout:', stdout)
            console.log('cmd exec stderr:', stderr)
            return
        }

        !configPathArr[0] && (configPathArr = globule.find(neiServerConfig))
        routeMap(configPathArr[0])
        createMockData(neiBaseDir)

        cb && cb()
    })
}

// 删除 ~/localMock/${key}文件
let removeLocalMock = (cb) => {
    console.log('remove localMock start:', neiBaseDir)
    rimraf(neiBaseDir, (error) => {
        if (error) {
            cb && cb('remove localMock error')
            console.log('remove localMock error:', error)
            return
        }
        console.log('remove localMock end')
        cb && cb()
    })
}

// 删除本工程mock/data下的文件
let removeProjectMockData = (cb) => {
    console.log('remove project mock data start')
    fs.readdir(copyTar, (error, files) => {
        if (error) {
            cb && cb('remove project mock data readdir error')
            console.log('readdir error:', error)
            return
        }
        files.forEach((file) => {
            let theFolder = path.join(copyTar, file)
            rimraf(theFolder, error => {
                if (error) {
                    cb && cb('remove project mock data error')
                    console.log('remove project mock data error:', error)
                    return
                }
                console.log('remove project mock data end')
            })
        })
        // 为空时直接回调
        files.length === 0 && console.log('project mock data is empty')
        cb && cb()
    })
}

let hardUpdate = () => {
    async.series([
        removeLocalMock, // 删除 ~/localMock/${key}文件
        removeProjectMockData, // 删除本工程mock/data下的文件
        softUpdate // 重新拉取
    ],
    (err, results) => {
        if (err) {
            console.log('async series error:', err)
        }
    })
}

module.exports = softUpdate;

