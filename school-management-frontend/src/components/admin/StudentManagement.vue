<script>
import axios from "axios";
import {API_URL, showError, showSuccess} from "@/utils";

export default {
  created() {
    this.pageChange(1)

    axios.post(API_URL + '/admin/student/totalCount', null, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Token': localStorage.getItem('Token')
      }
    }).then(({data}) => {
      if (!data)
        showError("后端服务器存在问题")
      if (data.status) {
        this.total = data.data;
      }
    })
  },
  data() {
    return {
      studentList: [],

      nowPage: 1,
      total: 1
    }
  },
  methods: {
    pageChange: function (nowPage) {
      axios.post(API_URL + '/admin/student/list', null, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        },
        params: {
          page : nowPage
        }

      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          this.studentList = data.data
        } else
          showError(data.message)
        data.data.forEach(item => item.gender = (item.gender === 1) ? '男' : '女')
      }).catch(showError)
    }
  }
}
</script>

<template>

  <div style="margin: 5%">

    <el-card shadow="hover" style="margin-left: 15%; margin-right: 15%">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px">学生管理</div>

      <el-table :data="studentList" stripe style="width: 100%" border>
        <el-table-column prop="studentId" label="学号" />
        <el-table-column prop="studentName" label="姓名" />
        <el-table-column prop="gender" label="性别" />
        <el-table-column prop="enroll" label="入学年份" />
        <el-table-column prop="departmentName" label="院系"/>
        <el-table-column prop="majorName" label="专业" />
        <el-table-column prop="className" label="班级" />
      </el-table>

      <br/>

      <el-pagination
          style="text-align: center"
          layout="prev, pager, next"
          :hide-on-single-page="true"
          :total="total"
          :current-page="nowPage"
          @current-change="pageChange">
      </el-pagination>

    </el-card>

  </div>
</template>

<style>

</style>