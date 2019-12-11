process.env.BABEL_ENV = 'development';
process.env.NODE_ENV = 'development';
process.on('unhandledRejection', err => {
  throw err;
});


require('../config/env');

const fs = require('fs');
const chalk = require('chalk');
const webpack = require('webpack');
const WebpackDevMiddleware = require('webpack-dev-middleware')
const clearConsole = require('react-dev-utils/clearConsole');
const express = require('express')
const path = require('path');
const neiUpdate = require('./../mock/nei-mock');
const checkRequiredFiles = require('react-dev-utils/checkRequiredFiles');
const {
  choosePort,
  createCompiler,
  prepareProxy,
  prepareUrls,
} = require('react-dev-utils/WebpackDevServerUtils');
const openBrowser = require('react-dev-utils/openBrowser');
const paths = require('../config/paths');
const config = require('../config/webpack.config.dev');
const createDevServerConfig = require('../config/webpackDevServer.config');

const useYarn = fs.existsSync(paths.yarnLockFile);
const isInteractive = process.stdout.isTTY;

if (!checkRequiredFiles([paths.appHtml, paths.appIndexJs])) {
  process.exit(1);
}

const DEFAULT_PORT = parseInt(process.env.PORT, 10) || 3000;
const HOST = process.env.HOST || '0.0.0.0';

if (process.env.HOST) {
  console.log(
    chalk.cyan(
      `Attempting to bind to HOST environment variable: ${chalk.yellow(
        chalk.bold(process.env.HOST)
      )}`
    )
  );
  console.log(
    `If this was unintentional, check that you haven't mistakenly set it in your shell.`
  );
  console.log(`Learn more here: ${chalk.yellow('http://bit.ly/2mwWSwH')}`);
  console.log();
}

choosePort(HOST, DEFAULT_PORT)
  .then(port => {
    if (port == null) {
      return;
    }
    const protocol = process.env.HTTPS === 'true' ? 'https' : 'http';
    const appName = require(paths.appPackageJson).name;
    const urls = prepareUrls(protocol, HOST, port);
    const compiler = createCompiler(webpack, config, appName, urls, useYarn);
    const proxySetting = require(paths.appPackageJson).proxy;
    const proxyConfig = prepareProxy(proxySetting, paths.appPublic);
    const serverConfig = createDevServerConfig(
      proxyConfig,
      urls.lanUrlForConfig
    );
    const app = express();
    let proxyTarget = '';
    try {
      proxyTarget = JSON.parse(process.env.npm_config_argv).remain;
    } catch (ex) {
      console.log(ex)
    }

    const proxyName = proxyTarget === '' || proxyTarget.length === 0 ? 'local' : proxyTarget[0];
    console.log(proxyName);
    // mock接管
    const { proxyTable } = require(path.resolve(__dirname, './../mock/proxy.config.js'))
    if (proxyName === 'nei') {
      console.log('use nei mock data online')
      app.use(require(path.resolve(__dirname, './../mock/nei-online.js')))
    } else if (proxyName === 'local') {
      neiUpdate();
      app.use(require('./../mock'));
    } else if (proxyTable[proxyName]) {
      console.log(`use ${proxyName} proxy`)
      app.use(proxyTable[proxyName]['proxy'])
    }
    // handle fallback for HTML5 history API
    app.use(require('connect-history-api-fallback')())
    
    const devMiddleware = WebpackDevMiddleware(compiler, serverConfig);
    app.use(devMiddleware);

    app.use(function (error, request, response, next) {
      console.error(error.stack)
      response.status(500).send(error.stack)
    })

    app.listen(port, function (err) {
      if (err) {
        return console.log(err);
      }
      if (isInteractive) {
        clearConsole();
      }
      console.log(chalk.cyan('Starting the development server...\n'));
      openBrowser(urls.localUrlForBrowser);
    })

  })
  .catch(err => {
    if (err && err.message) {
      console.log(err.message);
    }
    process.exit(1);
  });
