<script>
import {API_URL, showError, showSuccess} from "@/utils";
import axios from "axios";

export default {
  created() {
    this.getApplication()
  },

  data() {
    return {
      dataList: [],
      classList: [],

      confirmDialogVisible: false,
      studentId: ''
    }
  },

  methods: {
    getApplication() {
      axios.post(API_URL + '/admin/application/list', null, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          this.dataList = data.data
        } else
          showError(data.message)
      }).catch(showError)
    },

    cancel(row) {
      axios.post(API_URL + '/admin/application/cancel', {
        studentId: row.studentId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("申请已驳回")
          this.getApplication()
        } else
          showError(data.message)
      }).catch(showError)
    },

    openConfirmDialog(row) {
      this.confirmDialogVisible = true
      this.studentId = row.studentId
      axios.post(API_URL + '/admin/department/major/class/list', {
        majorId: row.majorId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          this.classList = data.data
        } else
          showError(data.message)
      }).catch(showError)
    },

    confirm(row) {
      axios.post(API_URL + '/admin/application/confirm', {
        studentId: this.studentId,
        classId: row.classId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("申请已批准")
          this.getApplication()
          this.confirmDialogVisible = false
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
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px">申请审批</div>

      <el-table :data="dataList" stripe style="width: 100%" border>
        <el-table-column fixed prop="studentId" label="学号" />
        <el-table-column fixed prop="studentName" label="学生名" />
        <el-table-column fixed prop="majorName" label="希望转入专业名" />
        <el-table-column fixed prop="departmentName" label="希望转入院系名" />

        <el-table-column :width="210" label="操作">
          <template v-slot="scope">
            <el-popconfirm title="确定要驳回吗？" icon="" style="margin-right: 10px" @confirm="cancel(scope.row)">
              <el-button flex icon="el-icon-close" slot="reference">驳回</el-button>
            </el-popconfirm>
            <el-button icon="el-icon-check" @click="openConfirmDialog(scope.row)">批准</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-card>

    <el-dialog title="选择班级" :visible.sync="confirmDialogVisible">

      <el-table :data="classList" stripe style="width: 100%" border>

        <el-table-column prop="classId" label="班级号" />
        <el-table-column prop="className" label="班级名" />
        <el-table-column prop="size" label="人数" />

        <el-table-column label="操作" :width="110">
          <template v-slot="scope">
            <el-button icon="el-icon-check" @click="confirm(scope.row)">批准</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>


  </div>

</template>

<style>

</style>