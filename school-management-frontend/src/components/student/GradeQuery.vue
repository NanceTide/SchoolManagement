<script>
import axios from "axios";
import {API_URL, showError} from "@/utils";

export default {
  created () {
    axios.post(API_URL + '/student/selfInfo', null, {
      headers : {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Token': localStorage.getItem('Token')
      }
    }).then(({data}) => {
      if(!data)
        showError("后端服务器存在问题")
      if(data.status) {
        this.studentName = data.data.studentName ? data.data.studentName : '未指定'

      } else
        showError(data.message)
    }).catch(showError)
  },
  data() {
    return {
      studentName: '未指定',

    }
  },
  methods: {

  }
}

</script>

<template>

</template>

<style>

</style>