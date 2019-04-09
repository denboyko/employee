import {security_cookie_name, SERVER_URL} from "./config";
import cookie from "react-cookies";

export function login(username, password, onSuccess, onError) {

    let user = {
        username,
        password
    };

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    fetch(SERVER_URL + "/login", {
        method: "POST",
        headers: myHeaders,
        credentials: 'same-origin',
        body: JSON.stringify(user),
    }).then((response) => {
        if (response.ok) {
            // console.log(response);
            // response.headers.get('Authorization');
            let token = response.headers.get('Authorization');
            if (token !== null) {
                cookie.save(security_cookie_name, token, {path: '/'});
                // this.props.authenticateWithReferrer(token);
            }
            if(onSuccess){
                onSuccess(response);
            }
        } else {
            if(onError){
                onError(response);
            }
        }
    });
}

export function logout(callback) {
    cookie.remove(security_cookie_name, {path: '/'});
    if(callback)
        callback();
}

export function createJsonHeaders() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json;charset=UTF-8');
    headers.append('Authorization', 'Bearer ' + cookie.load(security_cookie_name));
    return headers;
}