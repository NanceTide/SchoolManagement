<script>
import StudentInfo from './student/StudentInfo.vue'
import GradeQuery from './student/GradeQuery.vue'

export default {
  components: {
    StudentInfo,
    GradeQuery
  },
  data() {
    return {
      activeIndex: 'StudentInfo',
    }
  },
  methods: {
    handleSelect(key) {
      this.activeIndex = key
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
<!--          <el-menu-item index="CourseQuery">课程查询</el-menu-item>-->
          <el-menu-item index="GradeQuery">成绩查询</el-menu-item>
          <el-menu-item index="AccountManagement">账户管理</el-menu-item>
        </el-menu>

      </el-main>
    </el-header>

    <StudentInfo v-show="activeIndex === 'StudentInfo'"/>
    <GradeQuery v-show="activeIndex === 'GradeQuery'"/>


  </div>
</template>

<style>

</style>