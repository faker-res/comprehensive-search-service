import React, { Component } from 'react';
import {
    Button, Popover, Tooltip, Icon,
    Alert, Tag, Select, message
} from 'antd';
import _ from 'lodash';
import './index.scss';
import ask from '../../lib/ask';
import favorited from './images/favorited.svg';
import add_favorite from './images/add_favorite.svg';

const CheckableTag = Tag.CheckableTag;
const Option = Select.Option;

export default class Favorite extends Component {
    static defaultProps = {
        title: '收藏',
        placement: "leftTop",
        dataSource: {},
        max: 5,
    }
    constructor(props) {
        super(props);
        this.state = {
            status: '',
            tags: [...props.dataSource.tags] || [],  //标签
            hasTags: [],  //已输入标签5个
            visible: false,
            tooltipVisible: false,
            inputVisible: false,
            inputValue: '',
            closable: true,
        }
    }

    componentDidMount() {
        this.fetchHadTags();
    }

    fetchHadTags = () => {
        let promise = ask("fetchTagsByUser", {
            params: {
                limit: 100
            }
        })
        promise.then((resp) => {
            if (resp && resp.code === 200) {
                console.log(resp);
                let hasTags = resp.data.map((tag) => {
                    return tag.name;
                });
                this.setState({
                    hasTags,
                })
            }
        })
    }

    componentDidUpdate = (preProps, preState) => {
        const { tags: [...tags] } = this.props.dataSource;
        if (!_.isEqual([...preProps.dataSource.tags], tags)) {
            this.setState({
                tags,
            })
        }
    }

    hideFavorite = () => {
        const { tags } = this.props.dataSource;
        this.setState({
            visible: false,
            tags: [...tags],
        });
    }

    handleVisibleChange = (visible) => {
        const { favorite_id } = this.props.dataSource;
        this.setState({
            visible: visible && !favorite_id,
            tooltipVisible: false,
        });
    }

    handleTooltipVisibleChange = (visible) => {
        this.setState({ tooltipVisible: visible });
    }

    handleClose = (removedTag) => {
        const tags = this.state.tags.filter(tag => tag !== removedTag);
        console.log(tags);
        this.setState({ tags });
    }

    showInput = () => {
        this.setState({ inputVisible: true }, () => this.input.focus());
    }

    handleInputChange = (value) => {
        this.setState({ inputValue: value });
    }

    handleInputConfirm = () => {
        const state = this.state;
        const inputValue = state.inputValue;
        let tags = state.tags;
        if (inputValue && tags.indexOf(inputValue) === -1) {
            tags = [...tags, inputValue];
        }
        console.log(tags);
        this.setState({
            tags,
            inputVisible: false,
            inputValue: '',
        });
    }

    saveFavorite = () => {
        const { onSave, dataSource } = this.props;
        const { source_id, favorite_id, type, title } = dataSource
        const { tags } = this.state;
        if (onSave) {
            onSave({
                source_id,
                tags,
                title,
                favorite_id,
                type,
            });
        }
        this.setState({
            visible: false,
        });
        this.fetchHadTags();
        message.success('收藏成功');
    }

    /**
     * 删除收藏
     */
    cancelFavorite = () => {
        const { cancelFavorite, dataSource } = this.props;
        const { favorite_id, source_id, type } = dataSource;
        this.setState({
            visible: false,
            tags: [],
        }, () => {
            if (cancelFavorite) {
                cancelFavorite({
                    favorite_id,
                    source_id,
                    type,
                });
            }
        });
        message.success('取消成功');
    }

    saveInputRef = input => this.input = input

    handleSelectedChange = (tags, option) => {
      console.log("change");
        const { max } = this.props;
        if (tags.length > max) {
            this.setState({
                showError: true,
                errorMessage: '最多只能添加5个标签',
            })
            return false;
        }
        // 标签名称不能超过20个字符串
        let tagArr = [];
        if (tags.length) {
            let tag = tags.pop();
            if(!tag.trim()){
                this.setState({
                    showError: true,
                    errorMessage: '标签名称不能为空',
                })
                return;
            }
            if (tag.length > 20) {
                return;
            }
            tagArr = tag.split(",");
            tagArr.forEach((t)=>{
                if(!t)return;
                if(tags.length === max){
                    return;
                }
                if(tags.indexOf(t) === -1){
                    tags.push(t)
                }
            })
        }
        
        this.setState({
            visible: true,
            tags,
            showError: false,
        });
    }

    handleSelectedSearch = (value) => {
        if(!value.trim()){
            this.setState({
                showError: true,
                errorMessage: '标签名称不能为空',
            })
            return;
        }
        if (this.isLongTag(value)) {
            return;
        }
        this.setState({
            showError: false,
            errorMessage: '',
        })
    }

    getContent = () => {
        const { max } = this.props;
        const { hasTags, tags, showError,
            errorMessage, closable, } = this.state;
        let lastFive = hasTags.slice(0, 5);
        return (
            <div className="favorite">
                <div className="header">
                    <span className="title">收藏</span>
                </div>
                <div className="body">
                    {
                        showError
                            ? <Alert type="error" message={errorMessage} banner />
                            : null
                    }
                    <div
                        ref={(node) => { this.tagWrapNode = node }}
                        className="tag-wrap"
                    >
                        <Select
                            mode="tags"
                            style={{ width: '100%' }}
                            value={tags}
                            placeholder="输入字段按enter键生成标签"
                            getPopupContainer={() => this.tagWrapNode}
                            onChange={this.handleSelectedChange}
                            onSearch={this.handleSelectedSearch}
                            notFoundContent={'未添加过标签'}
                            onBlur={()=>{
                                this.setState({
                                    showError: false,
                                    errorMessage: '',
                                })
                            }}
                        >
                            {
                                [...hasTags].map((tag) => {
                                    return (
                                        <Option key={tag}>{tag}</Option>
                                    )
                                })
                            }
                        </Select>
                    </div>
                    <div className="hasTags">
                        {lastFive.map(tag => (
                            <CheckableTag
                                key={tag}
                                checked={tags.indexOf(tag) > -1}
                                onChange={checked => this.handleChange(tag, checked)}
                            >
                                {tag}
                            </CheckableTag>
                        ))}
                    </div>
                </div>
                <div className="footer">
                    <div>
                        {/* TODO 同时分享给团队 */}
                    </div>
                    <div>
                        {
                            this.isFavorite()
                                ? <Button
                                    className="cancel"
                                    onClick={this.cancelFavorite}
                                >
                                    取消收藏
                                </Button>
                                : <Button
                                    className="cancel"
                                    onClick={this.hideFavorite}
                                >
                                    取消
                                </Button>
                        }
                        <Button
                            className="save"
                            onClick={this.saveFavorite}
                        >
                            保存
                        </Button>
                    </div>
                </div>
            </div>
        );
    }

    isFavorite = () => {
        return !!this.props.dataSource.favorite_id;
    }

    isLongTag = (tag) => {
        let flag = tag.length > 20;
        if(flag){
            this.setState({
                showError: true,
                errorMessage: '标签超长，不能超过20个字符'
            })
        }else{
            this.setState({
                showError: false,
                errorMessage: '',
            })
        }
        return flag;
    }

    handleChange(tag, checked) {
        if (this.isLongTag(tag)) {
            return false;
        }
        const { max } = this.props;
        const { tags } = this.state;
        const nextSelectedTags = checked
            ? [...tags, tag]
            : tags.filter(t => t !== tag);
        console.log('You are interested in: ', nextSelectedTags);
        if (nextSelectedTags.length > max){
            
            return false;  
        } 
        this.setState({ tags: nextSelectedTags });
    }
    render() {
        const { placement, dataSource } = this.props;
        const { favorite_id } = dataSource;
        const { visible, tooltipVisible } = this.state;
        return (
            <Popover
                className="favorite-popove"
                placement={placement}
                content={this.getContent()}
                // title="Title"
                trigger="click"
                visible={visible && !favorite_id}
                onVisibleChange={this.handleVisibleChange}
            >
              {
                  this.isFavorite()
                      ?
                        (
                        <span className="fav-container" onClick={this.cancelFavorite}>
                            <img className="fav-icon" 
                                src={favorited}  alt='取消收藏'/>
                                收藏
                        </span>
                        )
                      : 
                        (
                            <span className="fav-container">
                                <img className="fav-icon" 
                                    src={add_favorite}
                                    alt='添加收藏'/>
                                    收藏
                            </span>
                        )
              }
            </Popover>

        )
    }
}

