import React from "react";
import "./index.scss";
class StandardEmpty extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    let { language } = this.props;
    return (
      <div className="compre-standard-empty-mode-box">
        <div>
          <img src={require("../../../assets/image/icons/emptyState.png")}/>
          <p className="default-text">暂无内容</p>
        </div>
      </div>
    );
  }
}

export default StandardEmpty;
