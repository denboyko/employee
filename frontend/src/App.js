import React, {Component} from 'react';
import withStyles from "@material-ui/core/styles/withStyles";
import Home from "./component/Home";
import Login from "./component/Login";
import {security_cookie_name} from "./api/config";
import cookie from "react-cookies";
import {Route, Switch, withRouter} from "react-router-dom";
import Redirect from "react-router-dom/Redirect";



const styles = (theme) => ({
    app: {
        padding: 0,
    }
});

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [],
            search: "",
            authenticated: false,
        }
    }

    componentDidMount(){
        this.checkAuth();
    }

    checkAuth(){
        let token = cookie.load(security_cookie_name);
        if (token) {
            this.setState({authenticated: true});
        } else {
            this.setState({authenticated: false});
        }
    }

    render() {
        const {classes} = this.props;
        return (
            <div className={classes.app}>
                    <Switch>
                        <Route path={"/login"} component={()=> <Login redirect={this.state.authenticated} checkAuth={()=> this.checkAuth()}/>}/>
                        <PrivateRoute isAuthenticated={this.state.authenticated} path={"/"} component={()=> <Home checkAuth={()=> this.checkAuth()}/>}/>

                    </Switch>
                {/*<Notification/>*/}
            </div>
        );
    }
}

const PrivateRoute = ({component: Component, isAuthenticated, ...rest}) => (
    <Route
        {...rest}
        render={props =>
            isAuthenticated ? (
                <Component {...props} />
            ) : (
                <Redirect
                    to={{
                        pathname: "/login",
                        state: {from: props.location}
                    }}
                />
            )
        }
    />
);

export default withStyles(styles, {withTheme: true})(App);