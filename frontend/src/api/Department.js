import {SERVER_URL} from "./config";
import {createJsonHeaders} from "./Auth";


export function getAllDepartments(callback) {
    return fetch(`${SERVER_URL}/department`, {
        credentials: 'same-origin',
        method: 'GET',
        headers: createJsonHeaders(''),
    }).then((response) => {
        return response.redirected ? (window.location.href = response.url) : response.json();
    }).then((json) => {
        if(callback)
            callback(json);
    }).catch(function (error) {
        console.log(error);
    });
}