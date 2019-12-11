import React from 'react'
import ReactDOM from 'react-dom'
import {LocaleProvider} from "antd"
import zhCN from 'antd/lib/locale-provider/zh_CN';
import App from './App'
import {Provider} from 'mobx-react'
import {BrowserRouter} from 'react-router-dom'
import Store from './store'
import registerServiceWorker from './registerServiceWorker'

ReactDOM.render(
    <LocaleProvider locale={zhCN}>
        <Provider {...Store}>
            <BrowserRouter basename="/">
                <App/>
            </BrowserRouter>
        </Provider>
    </LocaleProvider>,
    document.getElementById('root')
)
registerServiceWorker()
