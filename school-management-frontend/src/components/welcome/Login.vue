<template>
  <div style="width: 400px; background-color: white; position:relative">
    <!--标题-->
    <div style="text-align: center; margin-top: 20vh">
      <div style="font-size: 30px; font-family: 黑体,serif">大学学籍管理系统</div>
      <br/>
      <div style="font-size: 20px; font-family: 黑体,serif">登录</div>
    </div>

    <!--输入框-->
    <el-form id="userInput">
      <el-form-item style="text-align: center; margin: 12vh 12% 0 12%">
        <el-input v-model="form.username" prefix-icon="el-icon-user"></el-input>
        <br/><br/>
        <el-input v-model="form.password" prefix-icon="el-icon-lock" show-password></el-input>
      </el-form-item>

      <el-form-item style="text-align: center; margin-top: 10vh">
        <el-button :icon="checkBottom" @click="handleLogin" circle></el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
  import {API_URL, login, showError, showSuccess} from "@/net";
  let vm = null;

  export default {
    created () {
      vm = this
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

        login(API_URL + "/login",
            {
              username: this.form.username,
              password: this.form.password
            },
            (message) => {
              showSuccess(message)
              this.$router.push("/student")
            })

        this.checkBottom = 'el-icon-check'
      }

    }
  }

  addEventListener('keydown', (event) => event.key === 'Enter' && vm.handleLogin())
</script>

<style>

</style>