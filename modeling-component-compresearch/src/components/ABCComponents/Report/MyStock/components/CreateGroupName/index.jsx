import React, { Component } from "react";
import { inject, observer } from "mobx-react";
import { Modal, Form, Input, message } from "antd";

import "./index.scss";
@inject(Stores => ({
  visible: Stores.addGroupName.visible,
  confirmLoading: Stores.addGroupName.confirmLoading,
  ModalText: Stores.addGroupName.ModalText,
  title: Stores.addGroupName.title,
  xhr_state: Stores.addGroupName.xhr_state,
  groupName: Stores.addGroupName.groupName,
  groupId: Stores.addGroupName.groupId,
  changeInputGroup: Stores.searchAddSingleInput.changeInputGroup,
  handleOk: Stores.addGroupName.handleOk,
  handleCancel: Stores.addGroupName.handleCancel,
  changeValue: Stores.addGroupName.changeValue,
  fetchMyStockGroupList: Stores.myStock.fetchMyStockGroupList,//引入mystock
  setCurrentGroupId: Stores.myStock.setCurrentGroupId,
  getStockListByGroupId: Stores.myStock.getStockListByGroupId,
}))
@observer
@Form.create()
class CreateGroupName extends Component {
  constructor(props) {
    super(props);
    this.addGroupNameStore = this.props;
  }
  handleOk = () => {
    const { handleOk, fetchMyStockGroupList, form,
      setCurrentGroupId, getStockListByGroupId, changeInputGroup } = this.props;
    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      //成功后修改当前被选中分组
      const suc_callback = function (groupId, groupName) {
        //获取分组列表
        fetchMyStockGroupList("fetchMyStockGroupList", {
          "groupType": -1
        }, () => {
          // 获取分组下股票列表
          getStockListByGroupId('getStockListByGroupId', {
            params: {
              market: 0,
              groupId: groupId,
              groupType: -1,
              limit: 20,
              offset: 0,
              sort: ''
            }
          })
          setCurrentGroupId(`${groupId},0`, groupName);
          changeInputGroup(groupId);
        });
      }
      handleOk(suc_callback);
      form.resetFields();
    });

  }
  handleCancel = () => {
    const { handleCancel, form } = this.props;
    form.resetFields();
    handleCancel();
  };
  onChange = event => {
    const { changeValue } = this.props;
    changeValue(event.target.value);
  };
  checkGroupName = (rule, value, callback) => {
    if (value && value.trim().length > 0) {
      callback();
      return;
    }
    callback('输入分组名称有误，请重新输入！');
  }
  render() {
    const FormItem = Form.Item;
    const { visible, confirmLoading, ModalText, form,
      title, groupName, fetchMyStockGroupList, xhr_state } = this.props;
    const { getFieldDecorator } = form;
    return (
      <Modal
        title={title}
        visible={visible}
        onOk={this.handleOk}
        confirmLoading={confirmLoading}
        onCancel={this.handleCancel}
        okText="保存"
        cancelText="取消"
      >
        <div>
          <FormItem
            label="分组名称"
            labelCol={{ span: 4 }}
            wrapperCol={{ span: 20 }}
          >
            {getFieldDecorator('分组名称', {
              initialValue: groupName,
              rules: [{ validator: this.checkGroupName, required: true, message: '分组名称不能为空!' }],
            })(
              <Input
                type="text"
                placeholder="请输入分组名称"
                // value={groupName}
                onChange={this.onChange}
                maxLength="16"
              />
            )}
          </FormItem>
        </div>
      </Modal>
    );
  }
}
export default CreateGroupName;
