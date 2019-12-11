const proxy = require('http-proxy-middleware')
const proxyTarget = 'http://193.112.47.138'
const proxyTable = {
    'local': {
        proxy: [
            proxy('/invest-research-api', {
                target: 'http://192.168.3.132:23011/',
                changeOrigin: true,
            }),
            proxy('/search-api', {
                target: 'http://127.0.0.1:8001/',
                changeOrigin: true,
            }) 
        ]
    },
    'zp': {
        proxy: [
            proxy('/invest-research-api', {
                target: 'http://192.168.3.132:23011/',
                changeOrigin: true,
            }),
            proxy('/search-api', {
                target: 'http://127.0.0.1:8001/',
                changeOrigin: true,
            }) 
        ]
    },
    'dev': {
        proxy: [
            proxy('/invest-research-api', {
                target: 'http://tour-dev.modeling.ai/',
                changeOrigin: true,
            }),
            proxy('/search-api', {
                target: 'http://tour-dev.modeling.ai/',
                changeOrigin: true,
            }),
            proxy('/api', {
                target: 'http://tour-pre.modeling.ai/',
                changeOrigin: true,
            }),
            proxy('/invest-dc-inner-api', {
                target: 'http://tour-dev.modeling.ai/',
                changeOrigin: true,
            })
        ]
    },
    'pre': {
        proxy: [
            proxy('/invest-research-api', {
                target: 'http://tour-pre.modeling.ai/',
                changeOrigin: true,
            }),
            proxy('/search-api', {
                target: 'http://tour-pre.modeling.ai/',
                changeOrigin: true,
            }),
            proxy('/api', {
                target: 'http://tour-pre.modeling.ai/',
                changeOrigin: true,
            })
            ,
            proxy('/invest-dc-inner-api', {
                target: 'http://tour-dev.modeling.ai/',
                changeOrigin: true,
            })
        ]
    },
    'prod': {
        proxy: [
            proxy('/invest-research-api', {
                target: 'http://tour.modeling.ai/',
                changeOrigin: true,
            }),
            proxy('/search-api', {
                target: 'http://tour.modeling.ai/',
                changeOrigin: true,
            }),
            proxy('/invest-dc-inner-api', {
                target: 'http://tour-dev.modeling.ai/',
                changeOrigin: true,
            })
        ]
    },
};

module.exports = {
    // 项目的nei唯一标识
    key: 'ea841ba1ee66888a64063c4d7e356169',
    proxyTable
}