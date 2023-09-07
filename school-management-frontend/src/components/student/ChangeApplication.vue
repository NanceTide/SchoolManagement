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

      majorTo: '',
      departmentTo: '',
    }
  },
  methods: {

    majorDialogForm() {
      this.majorDialogFormVisible = true
      this.majorTo = ''

      axios.post(API_URL + '/student/major', null, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          let dataArray = data.data.filter(Array.isArray)[0];
          let remainingData = data.data.filter(item => !Array.isArray(item))[0]

          dataArray.forEach(item => item.disabled = item.majorId === remainingData.majorId && true)
          this.majors = dataArray
        } else if (!data.status)
          showError(data.message)
      }).catch(showError)
    },

    majorApplicate() {
      axios.post(API_URL + '/student/application/major', {majorTo: this.majorTo}, {
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

    departmentDialogForm() {
      this.departmentDialogFormVisible = true;
      this.departmentTo = [];

      axios.post(API_URL + '/student/department', null, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({ data }) => {
        if (!data) {
          showError("后端服务器存在问题")
          return
        }
        if (data.status) {
          let dataArray = data.data.filter(Array.isArray)[0]
          let remainingData = data.data.filter(item => !Array.isArray(item))[0]

          dataArray.forEach(item => {
            item.value = item.departmentId
            item.label = item.departmentName
            item.disabled = item.departmentId === remainingData.departmentId && true
          })

          this.departments = dataArray
        } else {
          showError(data.message)
        }
      }).catch(showError)
    },

    loadMajorData(node, resolve) {
      if(node.level === 0)
        return
      let departmentId = node.value
      axios.post(API_URL + '/public/department/major', {departmentId: departmentId}, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data) {
          showError("后端服务器存在问题")
          return
        }
        if (data.status) {
          data.data.forEach(child => {
            child.label = child.majorName
            child.value = child.majorId
            child.leaf = true
          })

          resolve(data.data)
        } else {
          showError(data.message)
        }
      }).catch(showError)
    },

    departmentApplicate() {
      this.majorTo = this.departmentTo[1]
      console.log(this.majorTo);

      axios.post(API_URL + '/student/application/major', {majorTo: this.majorTo}, {
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
        <el-button icon="el-icon-office-building" @click="departmentDialogForm">发起院系级异动申请</el-button>
      </div>

      <div v-else style="text-align: center">
        您有待批准中的异动申请。
        <br/><br/>
      </div>
    </el-card>

    <el-dialog title="专业级异动申请" :visible.sync="majorDialogFormVisible">
      <div>发起专业级异动申请来申请转到同院系的其他专业。申请将交由上级批准。</div><br/>

        <el-select v-model="majorTo" filterable placeholder="新专业">
          <el-option
              v-for="item in majors"
              :key="item.majorId"
              :label="item.majorName"
              :value="item.majorId"
              :disabled="item.disabled">
          </el-option>
        </el-select>

      <div slot="footer" class="dialog-footer">
        <el-button @click="majorDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="majorApplicate">申请</el-button>
      </div>
    </el-dialog>

    <el-dialog title="院系级异动申请" :visible.sync="departmentDialogFormVisible" placeholder="新专业">
      <div>发起院系级异动申请来申请转到其他院系的专业。申请将交由上级批准。</div><br/>

      <el-cascader
          v-model="departmentTo"
          :options="departments"
          :props="{lazy: true, lazyLoad: loadMajorData}"
          placeholder="请选择"
          filterable/>

      <div slot="footer" class="dialog-footer">
        <el-button @click="departmentDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="departmentApplicate">申请</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<style>

</style>