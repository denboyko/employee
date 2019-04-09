import {SERVER_URL} from "./config";
import {createJsonHeaders} from "./Auth";

export function searchByName(search, page, perPage, callback) {
    return fetch(`${SERVER_URL}/employee?search=${search}&page=${page}&perPage=${perPage}`, {
        credentials: 'same-origin',
        headers: createJsonHeaders(),
        method: 'GET'
    }).then((response) => {
        return response.redirected ? (window.location.href = response.url) : response.json();
    }).then((json) => {
        if (callback)
            callback(json);
    }).catch(function (error) {
        console.log(error);
    });
}

export function getEmployee(id, callback) {
    return fetch(`${SERVER_URL}/employee/${id}`, {
        credentials: 'same-origin',
        headers: createJsonHeaders(),
        method: 'GET'
    }).then((response) => {
        return response.redirected ? (window.location.href = response.url) : response.json();
    }).then((employee) => {
        if (callback)
            callback(employee.name, employee.active, employee.depId);
    }).catch(function (error) {
        console.log(error);
    });
}

export function createEmployee(employee, callback) {
    return fetch(`${SERVER_URL}/employee`, {
        credentials: 'same-origin',
        headers: createJsonHeaders(),
        method: 'POST',
        body: JSON.stringify(employee)
    }).then((response) => {
        return response.redirected ? (window.location.href = response.url) : response.json();
    }).then((json) => {
        if (callback)
            callback(json);
    }).catch(function (error) {
        console.log(error);
    });
}

export function updateEmployee(employee, callback) {
    return fetch(`${SERVER_URL}/employee`, {
        credentials: 'same-origin',
        headers: createJsonHeaders(),
        method: 'PUT',
        body: JSON.stringify(employee)
    }).then((response) => {
        return response.redirected ? (window.location.href = response.url) : response.json();
    }).then((json) => {
        if (callback)
            callback(json);
    }).catch(function (error) {
        console.log(error);
    });
}

export function deleteEmployee(id, callback) {
    return fetch(`${SERVER_URL}/employee/${id}`, {
        credentials: 'same-origin',
        headers: createJsonHeaders(''),
        method: 'DELETE',
    }).then((response) => {
        return response.redirected ? (window.location.href = response.url) : response.json();
    }).then((json) => {
        if (callback)
            callback(json);
    }).catch(function (error) {
        console.log(error);
    });
}



