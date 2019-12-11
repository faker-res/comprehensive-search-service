# NewsListCard

## 适用范围

适用于“上市公司”实体、“基金”实体、“基金公司”实体、“基金经理”实体、“分析师”实体、“行业”实体、“主题”实体的“新闻消息”组件内“新闻”、“公告”、“研报”的展示组件

## 代码示例

``` jsx
import { news, notices, reports } from '../../components/NewsListCard/data';
import NewsListCard from '../../components/NewsListCard';

<NewsListCard title="新闻" type="news" data={news}/>
<NewsListCard title="公告" type="notice" data={notices}/>
<NewsListCard title="研报" type="report" data={reports}/>
```

## API

|属性|说明|类型|默认值|
|:--:|:--:|:--:|:--:|
|title|头部标题|string||
|extra|头部右侧扩展|string/element|'更多>'|
|data|列表数据|array||
|onRightClick|头部右侧点击事件|func|undefind|

> data数据结构，参考新闻搜索、公告搜索、研报搜索结果数据结构，具体格式请看`data.js`

