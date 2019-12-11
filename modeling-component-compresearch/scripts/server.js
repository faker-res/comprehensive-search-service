const port = 8080;
const fs = require('fs');
const chalk = require('chalk');
const express = require('express')
const path = require('path');

const app = express();
app.use(require('./../mock'));

app.get('/', function (req, res, next) {
  let result = ''
  var filepath = path.join(__dirname, './../index.html')
  try {
    result = fs.readFileSync(filepath)
  } catch (err) {
    result = err.toString()
  }
  res.set('content-type', 'text/html')
  res.write(result)
  res.end()
})
app.use('/static', express.static('./static'))

app.use(function (error, request, response, next) {
  console.error(error.stack)
  response.status(500).send(error.stack)
})


app.listen(port, function (err) {
  if (err) {
    return console.log(err);
  }
  console.log(chalk.cyan(`Starting the prod server on port:${port}`));
})

