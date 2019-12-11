import React, { Component } from 'react';
import { List, Divider } from 'antd';
import Loading from './Loading';
// import ask from '../../lib/ask';
import './ImportantSearch.less';

class ImportantSearch extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  handleSelectType = (data, type) => {
    const { clickSearchType } = this.props;
    if (clickSearchType) clickSearchType(data, type);
  }
  render() {
    const { importantTypeList, isLoading } = this.props;
    const tabContent = <List
      style={{ backgroundColor: 'white', minHeight: '250px', visibility: `${isLoading ? 'hidden' : 'visible'}`}}
      dataSource={importantTypeList}
      renderItem={(item, i) => (
        <List.Item key={i}>
          <div style={{ width: '100%' }}>
            <div className={'important_type_item middle'} style={{ width: '100%' }}>
              <div className="col_center_middle important_item_title_container" >
                <img src={item.icon} alt={item.icon} />
                <div className="important_item_title">{item.title}</div>
              </div>
              <ul className="iportant_item_list">
                {
                  item.typeList && item.typeList.slice(0,36).map((ele, index) => {
                    return (
                      <li
                        key={index}
                        onClick={() => {
                          this.handleSelectType(ele, item.type);
                        }}
                      >
                        {ele.name}
                      </li>
                    );
                  })
                }
              </ul>
            </div>
            {
              i != importantTypeList.length - 1 && <Divider className="important_item_divider" style={{}} />
            }
          </div>
        </List.Item>
      )}
    />;
    return (
      <div className={'important_type_container'}>
        {isLoading == false ? tabContent : (
          <Loading
            isShow={isLoading}
            content={tabContent}
          />
        )}
      </div>
    );
  }
}
export default ImportantSearch;
