<template>
  <div style="width: 400px; background-color: white; position:relative">
    <!--标题-->
    <div style="text-align: center; margin-top: 20vh">
      <div style="font-size: 30px; font-family: 黑体,serif">大学学籍管理系统</div>
      <br/>
      <div style="font-size: 20px; font-family: 黑体,serif">登录</div>
    </div>

    <!--输入框-->
    <div style="text-align: center; margin: 12vh 12% 0 12%">
      <el-input v-model="userForm.username" prefix-icon="el-icon-user"></el-input>
      <br/><br/>
      <el-input v-model="userForm.password" prefix-icon="el-icon-lock" show-password></el-input>
    </div>

    <div style="text-align: center; margin-top: 10vh">
      <el-button :icon="checkBottom" @click="handleLogin" circle></el-button>
    </div>

<!--    <div style="text-align: center; margin-top: 5vh;">-->
<!--      <el-link href="/register" :underline="false" style="text-decoration: none; font-size:12px; color:lightgrey; font-family: 黑体,serif">-->
<!--        忘记密码？-->
<!--      </el-link>-->
<!--    </div>-->
  </div>
</template>

<script>
  import axios from "axios";
  import {showError} from "@/net";

  export default {
    data() {
      return {
        userForm: {
          username: '',
          password: '',
        },
        checkBottom: 'el-icon-check'
      }
    },
    methods: {
      handleLogin() {
        this.checkBottom = 'el-icon-loading'

        if(this.userForm.password == null ||
          this.userForm.username == null ||
          this.userForm.username === "" ||
          this.userForm.password === "") {
          this.checkBottom = 'el-icon-check'
          showError('用户名或密码为空')
          return
        }

        this.$router.push("/inter")
        // axios.post("http://localhost:8080", JSON.stringify(user))
      }
    }
  }
</script>

<style>

</style>