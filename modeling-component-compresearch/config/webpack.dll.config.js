const path = require('path');
const webpack = require('webpack');

function getPath(tsPath) {
  return path.join(__dirname, '..', tsPath);
}

const vendors = [
  'antd',
  'react',
  'react-dom',
  'mobx',
  'mobx-react',
  'react-router',
  'highcharts',
];

module.exports = {
  node: {
    __dirname: true,
  },
  output: {
    path: getPath('public/static/js'), // 实际生成的目录
    filename: '[name].dll.js', // 注意这里的chunkhash
    library: '[name]',
  },
  entry: {
    vendors: vendors,
  },
  plugins: [
    new webpack.DllPlugin({
      path: getPath('public/static/js/manifest.json'),
      name: '[name]',
      context: '.'
    }),
    new webpack.optimize.UglifyJsPlugin({
      compress: {
        warnings: false,
        comparisons: false,
      },
      mangle: {
        safari10: true,
      },
      output: {
        comments: false,
        ascii_only: true,
      }
    }),
  ],
};

