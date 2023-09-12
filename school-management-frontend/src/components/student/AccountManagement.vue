<script>
import axios from "axios";
import {API_URL, showError, showSuccess} from "@/utils";

export default {
  created () {

  },
  data() {
    return {
      oldPassword : '',
      newPassword : '',
      newPasswordRepeat : ''
    }
  },
  methods: {

    changePassword() {
      if(this.newPassword !== this.newPasswordRepeat) {
        showError("两次输入的密码不一致")
        this.newPassword = ''
        this.newPasswordRepeat = ''
        return
      }

      if(this.newPassword.length > 32) {
        showError("密码过长")
        this.newPassword = ''
        this.newPasswordRepeat = ''
        return
      }

      axios.post(API_URL + '/student/account', {
        oldPassword: this.oldPassword,
        newPassword: this.newPassword
      }, {
        headers : {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess(data.message)
          localStorage.clear()
          this.$router.push('/')
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
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px">密码修改</div>

      <el-form id="userInput">
        <el-form-item style="text-align: center; width: 25%; min-width: 250px; margin: 0 auto">
          <el-input v-model="oldPassword" prefix-icon="el-icon-lock" placeholder="旧密码" show-password/>
          <div style="margin-top: 25px" />
          <el-input v-model="newPassword" prefix-icon="el-icon-lock" placeholder="新密码" show-password/>
          <div style="margin-top: 25px" />
          <el-input v-model="newPasswordRepeat" prefix-icon="el-icon-lock" placeholder="新密码" show-password/>
        </el-form-item>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="changePassword" circle/>
        </el-form-item>
      </el-form>


    </el-card>

  </div>
</template>

<style>

</style>