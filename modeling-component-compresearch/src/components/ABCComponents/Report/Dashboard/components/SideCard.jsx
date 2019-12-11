import React, { Component } from 'react';
import { Card, Avatar } from 'antd';
import ComTabs from './ComTabs';
import { getFullUrl } from '../../../../../lib/utils';
import './SideCard.less';
import empty from '../../../../../assets/image/empty.svg';

const gridStyle = {
  width: '33.333%',
  textAlign: 'center',
  height: '80px',
  padding: '18px 20px'
};
const cardStyle = {
  width: '300px',
  minHeight: '160px',
};
class SideCard extends Component {
  static defaultProps = {
    title: '',
    avatar: {
      shape: 'square',
      style: { width: '22px', height: '22px' },
    },
  };
  getShowSource = (source) => {
    let list = [...source];
    if (list.length < 9) {
      for (let i = list.length; i < 9; i++) {
        list.push({ isEmpty: true })
      }
    }
    return list;
  }
  getShowGrid = (d, index, i, src) => {
    const { avatar, isLoading } = this.props;
    if (!d.isEmpty) {
      return (<Card.Grid key={d.type} style={{...gridStyle, cursor: 'pointer'}}>
        <div data-listindex={index} data-i={i} onClick={this.chickHandle}>
          <Avatar className={index === 0 ? 'display_none' : ''} shape={avatar.shape} style={avatar.style} src={src} />
          <div className={`${index === 0 ? 'display_none' : ''} type_title`} >{d.typeName || '-'}</div>
          <div className={`${index === 1 ? 'display_none' : ''} stock_title`}>{d.secName || '-'}</div>
          <div className={`${index === 1 ? 'display_none' : ''} stock_code`}>{d.stock || '-'}</div>
        </div>
      </Card.Grid>);
    }
    if (d.isEmpty) {
      return (<Card.Grid key={d.type} style={gridStyle}>
        <div className='center_middle' style={{ height: '100%' }}>
          <div style={{ fontSize: '12px', color: '#CCCCCC' }}>暂无内容</div>
        </div>
      </Card.Grid>);
    }
  }
  getCardPage = (source, index) => {
    const { avatar, isLoading } = this.props;
    const showSource = this.getShowSource(source);
    
    return (
      <Card className="card" style={cardStyle}>
        {source.length > 0 && showSource.map((d, i) => {
          const src = d.typeLogo ? getFullUrl(d.typeLogo) : null;
          return this.getShowGrid(d, index, i, src);
        })}
        {
          source.length == 0 && !isLoading && (
            <Card.Grid style={{ width: '100%', height: '160px' }}>
              <div className="center_middle" style={{ height: '100%' }}>
                <img src={empty} alt={empty} style={{ width: '23px', marginRight: '15px' }} />
                <span style={{ fontSize: '12px', color: '#cccccc' }}>暂无内容</span>
              </div>
            </Card.Grid>
          )
        }
      </Card>
    );
  }
  chickHandle = (e) => {
    const { clickMostReadCallback, tabContentList } = this.props;
    const { listindex, i } = e.currentTarget.dataset;
    const sourceList = tabContentList[listindex];
    const source = sourceList[i];
    const name = listindex === '0' ? source.secName : source.typeName;
    const code = listindex === '0' ? source.stock : source.type;
    clickMostReadCallback(listindex, name, code);
  }
  render() {
    const { title, tabNameList, tabContentList, selectedIndex, isLoading } = this.props;
    return (
      <div className={'sideCard'} >
        <ComTabs
          title={title}
          tabNameList={tabNameList}
          tabContentList={[this.getCardPage(tabContentList[0], 0)]}
          selectedIndex={selectedIndex}
          isLoading={isLoading}
        />
      </div>
    );
  }
}

export default SideCard;
