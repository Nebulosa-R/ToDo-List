import React, { Component } from 'react';

import { BrowserRouter, Route, Link, withRouter } from 'react-router-dom';

class ItemList extends Component<{}, any> {

    constructor(props: any) {
        super(props);
        this.state = {
          items: []
        };
        //this.handleChange = this.handleChange.bind(this);
        //this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        const folder = await (await fetch(`/folders/detail/${this.props.match.params.id}`)).json();
        this.setState({items: folder.items});
        
    }

    render() {
        return(
            <div>
                "Items"
                {JSON.stringify(this.state.items)}
            </div>
        );
    }
}

//export default withRouter(ItemList);
