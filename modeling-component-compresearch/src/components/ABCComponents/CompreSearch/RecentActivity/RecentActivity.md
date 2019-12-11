#### 适用范围

适用于综合搜索卡片分析师中最新动态列表的场景

> [设计稿地址](https://lanhuapp.com/web/#/item/board/detail?pid=6e513101-942f-474d-a617-349f4051f752&project_id=6e513101-942f-474d-a617-349f4051f752&image_id=d3952f7c-6b73-4802-98cc-d0ebfd15d78c)

#### 组件路径

`/components/RecentActivity/`

#### 使用说明

```
import RecentActivity from '/components/RecentActivity/'

...
render() {
    return (
        <RecentActivity title="最新动态" list={list} />
    )
}
...
```

#### 参数

|参数|说明|类型|默认值|是否必须|
|:---:|:---:|:---:|:---:|:---:|
|title|标题|string||true|
|list|列表|array||true|
|timeFromNow|格式化时间显示。设置为“o”时原时间输出；为“d”时会显示包括“今天”“昨天”“前天”和年月日，为“s”时仅当时间精确的时候启动，会在"d"的情况下多显示“刚刚”“几分钟前”类型|string||false|
|titleClick|标题点击时的回调，返回当前两个参数，第一个参数为包含当前index和当前项目数据的对象，第二个参数表示react包装的event事件|function||false|
|subTitClick|标题点击时的回调，返回当前两个参数，第一个参数为包含当前index和当前项目数据的对象，第二个参数表示react包装的event事件|function||false|
|moreBtnHref|更多按钮的链接|string||false|

##### `list`中各项股票信息说明

|参数|说明|类型|默认值|是否必须|
|:---:|:---:|:---:|:---:|:---:|
|id|股票id|string||true|
|time|日期|string||true|
|href|链接|string||false|
|title|标题|string||true|
|source|来源|string||false|

