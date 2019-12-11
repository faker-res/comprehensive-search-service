#### 适用范围

置顶按钮：对antd中BackTop组件的封装

#### 组件路径

`/components/BackToTop/`

#### 使用说明

```
import BackToTop from '/components/BackToTop'

...
render() {
    return (
        <BackToTop />
    )
}
...
```

#### 参数

|参数|说明|类型|默认值|是否必须|
|:---:|:---:|:---:|:---:|:---:|
|target|设置目标元素，默认是window|function|()=>window|false|
|visibilityHeight|设置滚动多少才出现该按钮|number|200|false|
|onClick|点击置顶后的回调函数|function||false|
|wrapStyle|设置容器样式|object||false|
|iconStyle|icon样式，可通过fontSize，color设置icon大小颜色等，用法同antd中Icon组件配置|object||false|
