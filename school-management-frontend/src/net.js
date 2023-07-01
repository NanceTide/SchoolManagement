import axios from "axios"
import { Message } from 'element-ui';

function showError(message= "") {
    if(message == null || message === "")
        message = "发生了未知错误"
    Message.error({
        message: message,
        center: true
    })
}

function post(url, data, success, failure = showError) {
    axios.post(url, data, {
        headers: {
        //     TODO
        },
        // withCredentials: true,
    }).then(({data}) => {
        if(data.success)
            success(data.message, data.status)
        else
            failure(data.message + ' : ' + data.status)
    }).catch(showError)
}

export {showError, post}

