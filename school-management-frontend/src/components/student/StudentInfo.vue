<script>
import axios from "axios";
import {API_URL, showError} from "@/utils";

export default {
  created () {

    axios.post(API_URL + '/student/info', null, {
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

    <el-card shadow="hover" style="margin-left: 15%; margin-right: 15%">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px">学生信息</div>

      <el-descriptions  :column="3" border direction="vertical" >

        <el-descriptions-item>
          <div slot="label">
            <i class="el-icon-user"/> 姓名
          </div>
          {{studentName}}
        </el-descriptions-item>
        <el-descriptions-item>
          <div slot="label">
            <i class="el-icon-tickets"/> 学号
          </div>
          {{studentId}}
        </el-descriptions-item>
        <el-descriptions-item>
          <div slot="label">
            <i class="el-icon-location-outline"/> 居住地
          </div>
          {{address}}
        </el-descriptions-item>
        <el-descriptions-item>
          <div slot="label">
            <i class="el-icon-male" v-if="gender==='男'"/>
            <i class="el-icon-female" v-else-if="gender==='女'"/>
            性别
          </div>
          {{gender}}
        </el-descriptions-item>
        <el-descriptions-item>
          <div slot="label">
            <i class="el-icon-date"/> 入学时间
          </div>
          {{enroll}}
        </el-descriptions-item>
        <el-descriptions-item>
          <div slot="label">
            <i class="el-icon-house"/> 民族
          </div>
          {{nation}}
        </el-descriptions-item>
        <el-descriptions-item>
          <div slot="label">
            <i class="el-icon-office-building"/> 院系
          </div>
          {{departmentName}}
        </el-descriptions-item>
        <el-descriptions-item>
          <div slot="label">
            <i class="el-icon-collection"/> 专业
          </div>
          {{majorName}}
        </el-descriptions-item>
        <el-descriptions-item>
          <div slot="label">
            <i class="el-icon-school"/> 班级
          </div>
          {{className_}}
        </el-descriptions-item>

      </el-descriptions>
    </el-card>

  </div>
</template>

<style>

</style>