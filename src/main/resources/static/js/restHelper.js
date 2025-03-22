let httpReqWithJsonResp = async (url, body, method) => {
    let options = {
        method: method,
        headers: {
            "Content-Type": "application/json",
        },
    };

    if (body != null) {
        options.body = JSON.stringify(body);
    }

    let resp = await fetch(url, options);

    try {
        let json = await resp.json();
        return json;
    } catch (error) {
        return { status: "fail", message: "error parsing response" };
    }
};

let httpAuthReqWithJsonResp = async (url, method, body, authToken) => {
    let headers = {
        "Content-Type": "application/json",
    };

    if (authToken != null) {
        headers.auth = `Bearer ${authToken}`;
    }

    let options = {
        method: method,
        headers,
    };

    if (body != null) {
        options.body = JSON.stringify(body);
    }

    let resp = await fetch(url, options);

    try {
        let json = await resp.json();
        return json;
    } catch (error) {
        return { status: "fail", message: "error parsing response" };
    }
};

const restHelper = { httpReqWithJsonResp, httpAuthReqWithJsonResp };

export default restHelper;
