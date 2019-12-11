import React from 'react';

class ErrorBoundary extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            hasError: false,
            error: {},
            info: {}
        };
    }

    componentDidCatch(error, info) {
        if (typeof this.props.onErrHandler === 'function') {
            this.props.onErrHandler({error, info});
        }

        this.setState({
            hasError: true,
            error,
            info
        });
    }

    render() {
        if (this.state.hasError) {
            return this.props.errorView ? this.props.errorView : (
                <React.Fragment>
                    <h1>Something went wrong.</h1>
                    <p>
                        {JSON.stringify(this.state.info)}
                    </p>
                </React.Fragment>
            );
        }
        return this.props.children || null;
    }
}

export default ErrorBoundary;