<script>
import axios from "axios";
import {API_URL, showError} from "@/utils";

export default {
  created () {

    axios.post(API_URL + '/student/grade', null, {
      headers : {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Token': localStorage.getItem('Token')
      }
    }).then(({data}) => {
      if(!data)
        showError("后端服务器存在问题")
      if(data.status) {
        this.gradeList = data.data;
      } else
        showError(data.message)
    }).catch(showError)

  },
  data() {
    return {
      studentId: '未指定',
    }
  },
  methods: {

  }
}

</script>

<template>
  <div style="margin: 5%">

    <el-card shadow="hover" style="margin-left: 15%; margin-right: 15%">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px">账户管理</div>


    </el-card>

  </div>
</template>

<style>

</style>