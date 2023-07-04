import { Message } from 'element-ui';

const API_URL = 'http://localhost:8080'

function showError(message= "") {
    if(!message)
        message = "发生了未知错误"
    Message.error({
        message: message,
        center: true
    })
}

function showSuccess(message= "") {
    if(!message)
        message = "成功"
    Message.success({
        message: message,
        center: true
    })
}

export {API_URL, showError, showSuccess}

