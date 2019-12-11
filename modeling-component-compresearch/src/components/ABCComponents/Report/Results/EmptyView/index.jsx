import React from "react";
import "./index.scss";
class EmptyView extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    let { language } = this.props;
    return (
      <div className="empty-mode-box">
        <img src={require("../../../../../assets/image/icons/emptyState.png")}/>
        <p className="default-text">暂无内容</p>
      </div>
    );
  }
}

export default EmptyView;
