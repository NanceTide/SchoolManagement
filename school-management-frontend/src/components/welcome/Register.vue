<template>
  <div style="width: 400px; background-color: white; position:relative">
    <!--标题-->
    <div style="text-align: center; margin-top: 20vh">
      <div style="font-size: 30px; font-family: 黑体,serif">大学学籍管理系统</div>
      <br/>
      <div style="font-size: 20px; font-family: 黑体,serif">注册</div>
    </div>

    <!--输入框-->
    <div style="text-align: center; margin: 12vh 12% 0 12%">
      <el-input v-model="username" prefix-icon="el-icon-user"></el-input>
      <br/><br/>
      <el-input v-model="password" prefix-icon="el-icon-lock" show-password></el-input>
    </div>

    <div style="text-align: center; margin-top: 10vh">
      <el-button :icon="checkBottom" @click="setBottomLoading" circle></el-button>
    </div>

    <div style="text-align: center; margin-top: 5vh;">
      <el-link href="/login" :underline="false" style="text-decoration: none; font-size:12px; color:lightgrey; font-family: 黑体,serif">
        已有帐户？
      </el-link>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        username: '',
        password: '',
        checkBottom: 'el-icon-check'
      }
    },
    methods: {
      setBottomLoading() {
        if(this.checkBottom === 'el-icon-check')
          this.checkBottom = 'el-icon-loading'
        else
          this.checkBottom = 'el-icon-check'
      }
    }
  }
</script>

<style>

</style>