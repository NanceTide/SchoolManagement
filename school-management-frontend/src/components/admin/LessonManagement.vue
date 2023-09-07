<script>
import axios from "axios";
import {API_URL, showError, showSuccess} from "@/utils";

export default {
  created() {
    this.pageChange(1)

    axios.post(API_URL + '/admin/course/totalCount', null, {
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
      courseList: [],

      nowPage: 1,
      total: 1
    }
  },
  methods: {
    pageChange: function (nowPage) {
      axios.post(API_URL + '/admin/course/list', null, {
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
          this.courseList = data.data
        } else
          showError(data.message)
      }).catch(showError)
    }
  }
}
</script>

<template>

  <div style="margin: 5%">

    <el-card shadow="hover" style="margin-left: 15%; margin-right: 15%">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px">课程管理</div>

      <el-table :data="courseList" stripe style="width: 100%" border>
        <el-table-column prop="courseId" label="课程号" />
        <el-table-column prop="courseName" label="课程名" />
        <el-table-column prop="credit" label="学分" />
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