import { API_AUTHENTICATION_URL } from '../config';


async function authencationGetRequest(endpoint, jwt){
    try {
        const response = await fetch(API_AUTHENTICATION_URL + endpoint, {
          headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + jwt,
          },
          method: "get",
        });
    
        if (response.status === 200) {
          return;
        } else {
          const data = await response.json();
          return Promise.reject(data['message']);
        }
    } catch (error) {
        return Promise.reject("Error: " + error.message);
    }
}

async function validateToken(jwt){
    return authencationGetRequest("validatetoken", jwt)   
}

async function validateRole(jwt){
    return authencationGetRequest("validaterole", jwt);
}

async function authenticationPostRequest(endpoint, requestBody){
    try {
        const response = await fetch(API_AUTHENTICATION_URL + endpoint, {
            headers: {
                "Content-Type": "application/json",
            },
            method: "post",
            body: JSON.stringify(requestBody),
        });
    
        if (response.status === 200) {
            const data = await response.json();
            return Promise.all([data]);
        } else {
            const data = await response.json();
            return Promise.reject(data['message']); 
        }
    } catch (error) {
        return Promise.reject("Error: " + error.message);
    }
}

async function loginAuthenticationRequest(username, password){
    const requestBody = {
        username: username,
        password: password,
    };
    return authenticationPostRequest("login", requestBody)
}

async function registerAuthenticaionRequest(username, password, name, role){
    const requestBody ={
        username:username,
        password:password,
        name:name,
        role:role
    }
    return authenticationPostRequest("register", requestBody);
}

export {loginAuthenticationRequest, registerAuthenticaionRequest, validateRole, validateToken}
