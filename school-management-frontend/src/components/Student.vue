<script>
import StudentInfo from '@/components/student/StudentInfo.vue'
import GradeQuery from '@/components/student/GradeQuery.vue'
import ChangeApplication from '@/components/student/ChangeApplication.vue'
import AccountManagement from '@/components/student/AccountManagement.vue'

export default {
  components: {
    StudentInfo,
    GradeQuery,
    ChangeApplication,
    AccountManagement
  },
  data() {
    return {
      activeIndex: 'StudentInfo',
    }
  },
  methods: {
    handleSelect(index) {
      this.activeIndex = index
    }
  }
}
</script>

<template>
  <div>
    <el-header style="border: 1px solid #eee">
      <el-main>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item disabled style="color: black">学生学籍管理系统</el-menu-item>
          <el-menu-item index="StudentInfo">学生信息</el-menu-item>
          <el-menu-item index="GradeQuery">成绩查询</el-menu-item>
          <el-menu-item index="ChangeApplication">异动申请</el-menu-item>
          <el-menu-item index="AccountManagement">密码修改</el-menu-item>
        </el-menu>

      </el-main>
    </el-header>

    <StudentInfo v-show="activeIndex === 'StudentInfo'"/>
    <GradeQuery v-show="activeIndex === 'GradeQuery'"/>
    <ChangeApplication v-show="activeIndex === 'ChangeApplication'"/>
    <AccountManagement v-show="activeIndex === 'AccountManagement'"/>

  </div>
</template>

<style>

</style>