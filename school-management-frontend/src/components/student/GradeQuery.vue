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
        this.gradeList = data.data
      } else
        showError(data.message)
    }).catch(showError)

  },
  data() {
    return {
      studentId: '未指定',
      studentName: '未指定',
      target: '',
      gradeList: [{
        courseId : '未指定',
        courseName : '未指定',
        credit : '未指定',
        grade : '成绩未出'
      }],


    }
  },
  methods: {

  }

}

</script>

<template>
  <div style="margin: 5%">

    <el-card shadow="hover" style="margin-left: 15%; margin-right: 15%">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px">成绩查询</div>

      <el-input prefix-icon="el-icon-search" v-model="target" size="default" placeholder="输入关键字搜索" />

      <br/><br/>

      <el-table :data="gradeList.filter(data =>
        !target
        || data.courseId.toLowerCase().includes(target.trim().toLowerCase())
        || data.courseName.toLowerCase().includes(target.trim().toLowerCase())
      )" stripe style="width: 100%" border>
        <el-table-column prop="courseId" label="课程号" />
        <el-table-column prop="courseName" label="课程名" />
        <el-table-column prop="credit" label="学分" sortable/>
        <el-table-column prop="grade" label="成绩" sortable/>
      </el-table>

    </el-card>

  </div>
</template>

<style>

</style>