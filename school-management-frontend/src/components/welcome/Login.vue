<template>
  <div style="width: 400px; background-color: white; position:relative">
    <!--标题-->
    <div style="text-align: center; margin-top: 20vh">
      <div style="font-size: 30px; font-family: 黑体,serif">学生学籍管理系统</div>
      <br/>
      <div style="font-size: 20px; font-family: 黑体,serif">登录</div>
    </div>

    <!--输入框-->
    <el-form id="userInput">
      <el-form-item style="text-align: center; margin: 12vh 12% 0 12%">
        <el-input v-model="form.username" prefix-icon="el-icon-user"/>
        <br/><br/>
        <el-input v-model="form.password" prefix-icon="el-icon-lock" show-password/>
      </el-form-item>

      <el-form-item style="text-align: center; margin-top: 10vh">
        <el-button :icon="checkBottom" @click="handleLogin" circle/>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
  import {API_URL, showError, showSuccess} from "@/utils";
  import axios from "axios";
  let vm = null;
    function enterToLogin(event) {
      event.key === 'Enter' && vm.handleLogin()
    }

  export default {
    created () {
      vm = this;
      addEventListener('keydown', enterToLogin)
    },
    data() {
      return {
        form: {
          username: '',
          password: '',
        },
        checkBottom: 'el-icon-check'
      }
    },
    methods: {

      handleLogin() {
        this.checkBottom = 'el-icon-loading'
        if(!this.form.username || !this.form.password) {
          showError('用户名或密码为空')
          this.checkBottom = 'el-icon-check'
          return;
        }

        axios.post(API_URL + '/login',
            {
              username: this.form.username,
              password: this.form.password
            },
            {
              headers : {
                'Content-Type': 'application/x-www-form-urlencoded',
              }
            }
        ).then(({data}) => {
          if(data.status) {
            localStorage.setItem('Token', data.data)
            showSuccess(data.message)
            removeEventListener('keydown', enterToLogin)
            this.$router.push("/inter").catch(err => true)
          } else {
            showError(data.message)
          }
        }).catch(showError)

        this.checkBottom = 'el-icon-check'
      }

    }
  }

</script>

<style>

</style>