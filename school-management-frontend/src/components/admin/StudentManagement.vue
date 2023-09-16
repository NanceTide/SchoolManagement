<script>
import axios from "axios";
import {API_URL, showError, showSuccess} from "@/utils";
import GradeManagement from "@/components/admin/GradeManagement.vue";

export default {
  components: {
    GradeManagement
  },


  created() {
    this.pageChange(1)
  },


  data() {
    return {
      dataList: [],
      gradeList: [],

      nowPage: 1,
      total: 1,

      editDialogVisible: false,
      gradeDialogVisible: false,
      insertDialogVisible: false,

      studentId: '',
      studentName: '',
      gender: '',
      birthday: '',
      enroll: '',
      nation: '',
      address: '',
      classId: '',

      searchStr: ''
    }
  },


  methods: {
    pageChange(nowPage) {
      axios.post(API_URL + '/admin/student/list', {
        page: nowPage
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          this.dataList = data.data
          data.data.forEach(item => item.gender = (item.gender === 1) ? '男' : '女')
        } else
          showError(data.message)
      }).catch(showError)

      axios.post(API_URL + '/admin/student/totalCount', null, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          this.total = data.data;
        }
      }).catch(null)
    },

    openEditDialog(row) {
      this.editDialogVisible = true
      this.studentId = row.studentId
      this.studentName = row.studentName
      this.gender = row.gender
      this.birthday = row.birthday
      this.enroll = row.enroll
      this.nation = row.nation
      this.address = row.address
      this.classId = row.classId
    },

    editStudent() {
      axios.post(API_URL + '/admin/student/update', {
        studentId: this.studentId,
        studentName: this.studentName,
        gender: this.gender === "男" ? 1 : 0,
        birthday: new Date(this.birthday).toLocaleDateString(),
        enroll: new Date(this.enroll).getFullYear(),
        nation: this.nation,
        address: this.address,
        classId: this.classId,
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          showSuccess("修改成功")
          this.pageChange(this.nowPage)
          this.editDialogVisible = false
        } else
          showError(data.message)
      }).catch(showError)
    },

    openInsertDialog() {
      this.insertDialogVisible = true
      this.studentId = ''
      this.studentName = ''
      this.gender = ''
      this.birthday = ''
      this.enroll = ''
      this.nation = ''
      this.address = ''
      this.classId = ''
    },

    insertStudent() {
      if(!this.studentId || !this.studentName || !this.birthday || !this.enroll || !this.nation || !this.address) {
        showError("未填写完全")
        return
      }
      axios.post(API_URL + '/admin/student/insert', {
        studentId: this.studentId,
        studentName: this.studentName,
        gender: this.gender === "男" ? 1 : 0,
        birthday: new Date(this.birthday).toLocaleDateString(),
        enroll: new Date(this.enroll).getFullYear(),
        nation: this.nation,
        address: this.address,
        classId: this.classId,
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          showSuccess("新增成功")
          this.pageChange(this.nowPage)
          this.insertDialogVisible = false
        } else
          showError(data.message)
      }).catch(showError)
    },

    deleteStudent(row) {
      axios.post(API_URL + '/admin/student/delete', {
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
          showSuccess("删除成功")
          this.pageChange(this.nowPage)
        } else
          showError(data.message)
      }).catch(showError)
    },

    openGradeDialog(row) {
      this.gradeDialogVisible = true
      this.studentId = row.studentId
      axios.post(API_URL + '/admin/student/grade', {
        studentId: this.studentId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          this.gradeList = data.data
        } else
          showError(data.message)
      }).catch(showError)
    },

    downloadFile() {
      axios.get(API_URL + "/admin/student/file", {
        responseType: 'blob',
        headers: {
          'Token': localStorage.getItem('Token')
        }
      }).then(response => {
            const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

            const url = window.URL.createObjectURL(blob);

            const a = document.createElement('a');
            a.href = url;
            a.download = '学生信息.xls';
            document.body.appendChild(a);
            a.click();

            // 释放对象URL资源
            window.URL.revokeObjectURL(url);
          })
          .catch(error => {
            showError('下载文件失败');
          });
    },

    searchStudent() {
      if(this.searchStr == null)
        this.pageChange(this.nowPage)

      axios.post(API_URL + '/admin/student/search', {
        like: this.searchStr
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          this.dataList = data.data
          this.total = this.dataList.length
        } else
          showError(data.message)
      }).catch(showError)
    }

  }
}
</script>

<template>

  <div style="margin: 5%">

    <el-card shadow="hover" style="margin-left: 5%; margin-right: 5%">

      <div style="text-align: center; font-size: 20px">学生管理</div>

<!--      <el-button style="float: right" type="text" @click="openInsertDialog">新增</el-button>-->
<!--      <el-button type="text" @click="downloadFile">导出为 Excel</el-button>-->
<!--      <el-input placeholder="请输入内容" v-model="input3" class="input-with-select" style="width: 300px; margin: 0 auto; position: absolute">-->
<!--        <el-button slot="append" icon="el-icon-search"></el-button>-->
<!--      </el-input>-->

      <div style="margin: 8px 0 8px 0">
        <el-button style="float: left" @click="downloadFile">导出为 Excel</el-button>
        <el-button style="float: right" @click="openInsertDialog">新增</el-button>
        <div style="text-align: -webkit-center">
          <el-input placeholder="请输入内容" v-model="searchStr" class="input-with-select" style="width: 300px">
            <el-button slot="append" icon="el-icon-search" @click="searchStudent"></el-button>
          </el-input>
        </div>
      </div>

      <el-table :data="dataList" stripe style="width: 100%" border>
        <el-table-column fixed prop="studentId" label="学号" />
        <el-table-column fixed prop="studentName" label="姓名" />
        <el-table-column prop="gender" :min-width="50" label="性别" />
        <el-table-column prop="address" label="居住地" />
        <el-table-column prop="birthday" label="生日" />
        <el-table-column prop="enroll" :min-width="80" label="入学年份" />
        <el-table-column prop="nation" label="民族" />
        <el-table-column prop="departmentName" label="院系"/>
        <el-table-column prop="majorName" label="专业" />
        <el-table-column prop="className" label="班级" />

        <el-table-column fixed="right" :width="310" label="操作">
          <template v-slot="scope">
            <el-popconfirm title="确定要删除吗？" icon="" style="margin-right: 10px" @confirm="deleteStudent(scope.row)">
              <el-button flex icon="el-icon-delete" slot="reference">删除</el-button>
            </el-popconfirm>
            <el-button icon="el-icon-edit" @click="openEditDialog(scope.row)">编辑</el-button>
            <el-button icon="el-icon-tickets" @click="openGradeDialog(scope.row)">成绩</el-button>


          </template>
        </el-table-column>
      </el-table>

      <br/>
      <div style="text-align: center; font-size: smaller; color: lightgray">共 {{total}} 条数据</div>

      <el-pagination
          style="text-align: center"
          layout="prev, pager, next"
          :hide-on-single-page="true"
          :total="total"
          :current-page="nowPage"
          @current-change="pageChange">
      </el-pagination>

    </el-card>

    <el-dialog title="新增" :visible.sync="insertDialogVisible">
      <div>新增学生信息。</div><br/>
      <el-form id="userInput" label-width="80px">

        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="学号">
            <el-input v-model="studentId"/>
          </el-form-item>

          <el-form-item label="学生名">
            <el-input v-model="studentName"/>
          </el-form-item>

          <el-form-item label="性别">
            <el-radio-group v-model="gender">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="生日">
            <el-date-picker
                v-model="birthday"
                type="date"
                placeholder="选择日期"
                style="width: 100%;"/>
          </el-form-item>

          <el-form-item label="入学年">
            <el-date-picker
                v-model="enroll"
                type="year"
                placeholder="选择年"
                style="width: 100%;"/>
          </el-form-item>

          <el-form-item label="民族">
            <el-input v-model="nation"/>
          </el-form-item>

          <el-form-item label="居住地">
            <el-input v-model="address"/>
          </el-form-item>

          <el-form-item label="班级号">
            <el-input v-model="classId"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="insertStudent" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="编辑" :visible.sync="editDialogVisible">
      <div>修改学生信息。</div><br/>
      <el-form id="userInput" label-width="80px">

        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="学号">
            <el-input v-model="studentId" :disabled="true"/>
          </el-form-item>

          <el-form-item label="学生名">
            <el-input v-model="studentName"/>
          </el-form-item>

          <el-form-item label="性别">
            <el-radio-group v-model="gender">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="生日">
            <el-date-picker
                v-model="birthday"
                type="date"
                placeholder="选择日期"
                style="width: 100%;"/>
          </el-form-item>

          <el-form-item label="入学年">
            <el-date-picker
                v-model="enroll"
                type="year"
                placeholder="选择年"
                style="width: 100%;"/>
          </el-form-item>

          <el-form-item label="民族">
            <el-input v-model="nation"/>
          </el-form-item>

          <el-form-item label="居住地">
            <el-input v-model="address"/>
          </el-form-item>

          <el-form-item label="班级号">
            <el-input v-model="classId"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="editStudent" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>

    <GradeManagement
        :in-student-id="studentId"
        :in-data-list="gradeList"
        :grade-dialog-visible="gradeDialogVisible"
        @closeDialog="gradeDialogVisible = false"
    />

  </div>
</template>

<style>

</style>