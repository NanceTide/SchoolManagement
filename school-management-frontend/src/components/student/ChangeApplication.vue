<script>
import axios from "axios";
import {API_URL, showError, showSuccess} from "@/utils";

export default {
  created () {

    axios.post(API_URL + '/student/application', null, {
      headers : {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Token': localStorage.getItem('Token')
      }
    }).then(({data}) => {
      if(!data)
        showError("后端服务器存在问题")
      if(data.status && data.data) {
        this.hasApplication = true
        this.applicationInfo = data.data
      } else if(!data.status)
        showError(data.message)
    }).catch(showError)

  },
  data() {
    return {
      hasApplication: false,
      applicationInfo: null,

      majorDialogFormVisible: false,
      departmentDialogFormVisible: false,

      majors: [],
      departments: [],

      majorTo: ''

    }
  },
  methods: {

    majorDialogForm() {
      this.majorDialogFormVisible = true
      this.majorTo = ''

      axios.post(API_URL + '/student/major/same', null, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          this.majors = data.data
        } else if (!data.status)
          showError(data.message)
      }).catch(showError)

    },

    majorApplicate() {

      axios.post(API_URL + '/student/application/same', {
        majorTo: this.majorTo
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status)
          showSuccess(data.message)
        else
          showError(data.message)
        this.majorDialogFormVisible = false
        location.reload()
      }).catch(showError)

    },


  }
}

</script>

<template>
  <div style="margin: 5%">

    <el-card shadow="hover" style="margin-left: 15%; margin-right: 15%">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px">异动申请</div>

      <div v-if="!hasApplication" style="text-align: center">
        您当前没有异动申请。
        <br/><br/>
        <el-button icon="el-icon-collection" @click="majorDialogForm">发起专业级异动申请</el-button>
        <el-button icon="el-icon-office-building">发起院系级异动申请</el-button>

      </div>

      <div v-else style="text-align: center">
        您有待批准中的异动申请。
        <br/><br/>
      </div>
    </el-card>

    <el-dialog title="专业级异动申请" :visible.sync="majorDialogFormVisible">

        <el-select v-model="majorTo" filterable placeholder="请选择" style="margin-left: 1%">
          <el-option
              v-for="item in majors"
              :key="item.majorId"
              :label="item.majorName"
              :value="item.majorId">
          </el-option>
        </el-select>

      <div slot="footer" class="dialog-footer">
        <el-button @click="majorDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="majorApplicate">确定</el-button>
      </div>

    </el-dialog>

  </div>
</template>

<style>

</style>