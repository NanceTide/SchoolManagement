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
        this.studentId = data.data.studentId ? data.data.studentId : '未指定'
        this.address = data.data.address ? data.data.address : '未指定'

        if(data.data.gender === null)
          this.gender = '未指定'
        else if(data.data.gender === 1)
          this.gender = '男'
        else if(data.data.gender === 0)
          this.gender = '女'

        this.enroll = data.data.enroll ? data.data.enroll : '未指定'
        this.nation = data.data.nation ? data.data.nation : '未指定'
        this.departmentName = data.data.departmentName ? data.data.departmentName : '未指定'
        this.majorName = data.data.majorName ? data.data.majorName : '未指定'
        this.className_ = data.data.className ? data.data.className : '未指定'
      } else
        showError(data.message)
    }).catch(showError)
  },
  data() {
    return {
      studentName: '未指定',
      studentId: '未指定',
      address: '未指定',
      gender: '未指定',
      enroll: '未指定',
      nation: '未指定',
      departmentName: '未指定',
      majorName: '未指定',
      className_: '未指定'
    }
  },
  methods: {

  }
}

</script>

<template>
  <div style="margin: 5%">

    <el-descriptions title="学生信息" :column="3" border direction="vertical" style="margin-left: 20%; margin-right: 20%">

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"/> 姓名
        </template>
        {{studentName}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"/> 学号
        </template>
        {{studentId}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location-outline"/> 居住地
        </template>
        {{address}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-male"/> 性别
        </template>
        {{gender}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-date"/> 入学时间
        </template>
        {{enroll}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-house"/> 民族
        </template>
        {{nation}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-office-building"/> 院系
        </template>
        {{departmentName}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-collection"/> 专业
        </template>
        {{majorName}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-school"/> 班级
        </template>
        {{className_}}
      </el-descriptions-item>

    </el-descriptions>
  </div>
</template>

<style>

</style>