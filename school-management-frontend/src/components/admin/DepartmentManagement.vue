<script>
import axios from "axios";
import {API_URL, showError, showSuccess} from "@/utils";

export default {
  created() {
    this.pageChange(1)

    axios.post(API_URL + '/admin/department/totalCount', null, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Token': localStorage.getItem('Token')
      }
    }).then(({data}) => {
      if (!data)
        showError("后端服务器存在问题")
      if (data.status)
        this.total = data.data;
    })
  },


  data() {
    return {
      dataList: [],
      majorList: [],
      classList: [],

      nowPage: 1,
      total: 1,

      editDepartmentDialogVisible: false,
      insertDepartmentDialogVisible: false,
      majorDialogVisible: false,
      editMajorDialogVisible: false,
      insertMajorDialogVisible: false,
      classDialogVisible: false,
      editClassDialogVisible: false,
      insertClassDialogVisible: false,

      departmentId: '',
      departmentName: '',

      majorId: '',
      majorName: '',
      shareDepartmentId: '',
      shareDepartmentName: '',

      classId: '',
      className: '',
      shareMajorId: '',
      shareMajorName: ''

    }
  },


  methods: {
    pageChange(nowPage) {
      axios.post(API_URL + '/admin/department/list', {
        page: nowPage
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        },
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status)
          this.dataList = data.data
        else
          showError(data.message)
      }).catch(showError)
    },

    openEditDepartmentDialog(row) {
      this.editDepartmentDialogVisible = true
      this.departmentId = row.departmentId
      this.departmentName = row.departmentName
    },

    editDepartment() {
      axios.post(API_URL + '/admin/department/update', {
        departmentId: this.departmentId,
        departmentName: this.departmentName
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("修改成功")
          this.pageChange(this.nowPage)
          this.editDepartmentDialogVisible = false
        }
        else
          showError(data.message)
      }).catch(showError)
    },

    openInsertDepartmentDialog() {
      this.insertDepartmentDialogVisible = true
      this.departmentId = ''
      this.departmentName = ''
    },

    insertDepartment() {
      if(!this.departmentId || !this.departmentName) {
        showError("未填写完全")
        return
      }
      axios.post(API_URL + '/admin/department/insert', {
        departmentId: this.departmentId,
        departmentName: this.departmentName
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("新增成功")
          this.pageChange(this.nowPage)
          this.insertDepartmentDialogVisible = false
        }
        else
          showError(data.message)
      })
    },

    deleteDepartment(row) {
      axios.post(API_URL + '/admin/department/delete', {
        departmentId: row.departmentId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        },
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("删除成功")
          this.pageChange(this.nowPage)
        }
        else
          showError(data.message)
      }).catch(showError)
    },


    openMajorDialog(row) {
      this.departmentId = row.departmentId
      this.majorDialogVisible = true
      axios.post(API_URL + '/public/department/major', {
        departmentId: row.departmentId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status)
          this.majorList = data.data
        else
          showError(data.message)
      }).catch(showError)
    },

    openEditMajorDialog(row) {
      this.editMajorDialogVisible = true
      this.majorId = row.majorId
      this.majorName = row.majorName
      this.shareDepartmentId = row.departmentId
      this.shareDepartmentName = row.departmentName
    },

    editMajor() {
      axios.post(API_URL + '/admin/department/major/update', {
        majorId: this.majorId,
        majorName: this.majorName,
        departmentId: this.shareDepartmentId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        },
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("修改成功")
          this.openMajorDialog({
            departmentId: this.departmentId
          })
          this.editMajorDialogVisible = false
        }
        else
          showError(data.message)
      }).catch(showError)
    },

    openInsertMajorDialog() {
      this.insertMajorDialogVisible = true
      this.majorId = this.departmentId + 'XX'
      this.majorName = ''
      this.shareDepartmentId = this.departmentId
    },

    insertMajor() {
      if(!this.majorId || !this.majorName || !this.shareDepartmentId) {
        showError("未填写完全")
        return
      }
      axios.post(API_URL + '/admin/major/insert', {
        majorId: this.majorId,
        majorName: this.majorName,
        departmentId: this.shareDepartmentId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("新增成功")
          this.openMajorDialog( {
            departmentId: this.departmentId
          })
          this.insertMajorDialogVisible = false
        }
        else
          showError(data.message)
      })
    },

    deleteMajor(row) {
      axios.post(API_URL + '/admin/major/delete', {
        majorId: row.majorId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        },
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("删除成功")
          this.openMajorDialog({
            departmentId: this.departmentId
          })
        }
        else
          showError(data.message)
      }).catch(showError)
    },


    openClassDialog(row) {
      this.majorId = row.majorId
      this.classDialogVisible = true
      axios.post(API_URL + '/admin/department/major/class/list', {
        majorId: row.majorId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status)
          this.classList = data.data
        else
          showError(data.message)
      }).catch(showError)
    },

    openEditClassDialog(row) {
      this.editClassDialogVisible = true
      this.classId = row.classId
      this.className = row.className
      this.shareMajorId = row.majorId
      this.shareMajorName = row.majorName
    },

    editClass() {
      axios.post(API_URL + '/admin/department/major/class/update', {
        classId: this.classId,
        className: this.className,
        majorId: this.shareMajorId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        },
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        else if(data.status) {
          showSuccess("修改成功")
          this.openClassDialog({
            majorId: this.majorId
          })
          this.editClassDialogVisible = false
        }
        else
          showError(data.message)
      }).catch(showError)
    },

    openInsertClassDialog() {
      this.insertClassDialogVisible = true
      this.classId = this.majorId + 'XX'
      this.className = ''
      this.shareMajorId = this.majorId
    },

    insertClass() {
      if(!this.classId || !this.className || !this.shareMajorId) {
        showError("未填写完全")
        return
      }
      axios.post(API_URL + '/admin/class/insert', {
        classId: this.classId,
        className: this.className,
        majorId: this.shareMajorId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("新增成功")
          this.openClassDialog({
            majorId: this.majorId
          })
          this.insertClassDialogVisible = false
        }
        else
          showError(data.message)
      }).catch(showError)
    },

    deleteClass(row) {
      axios.post(API_URL + '/admin/class/delete', {
        classId: row.classId
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
          this.openClassDialog({
            majorId: this.majorId
          })
        }
        else
          showError(data.message)
      }).catch(showError)
    }

  }
}
</script>

<template>

  <div style="margin: 5%">

    <el-card shadow="hover" style="margin-left: 15%; margin-right: 15%">
      <div style="text-align: center; font-size: 20px">院系管理</div>
      <el-button style="float: right" type="text" @click="openInsertDepartmentDialog">新增</el-button>

      <el-table :data="dataList" stripe style="width: 100%" border>
        <el-table-column prop="departmentId" label="院系号" />
        <el-table-column prop="departmentName" label="院系名" />

        <el-table-column label="操作" :width="310">
          <template v-slot="scope">
          <el-popconfirm title="确定要删除吗？" icon="" style="margin-right: 10px" @confirm="deleteDepartment(scope.row)">
            <el-button flex icon="el-icon-delete" slot="reference">删除</el-button>
          </el-popconfirm>
          <el-button icon="el-icon-edit" @click="openEditDepartmentDialog(scope.row)">编辑</el-button>
          <el-button icon="el-icon-collection" @click="openMajorDialog(scope.row)">专业</el-button>
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

    <el-dialog title="编辑" :visible.sync="editDepartmentDialogVisible" width="80%">
      <div>修改院系信息。</div><br/>
      <el-form id="userInput" label-width="80px">
        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="院系号">
            <el-input v-model="departmentId" :disabled="true"/>
          </el-form-item>

          <el-form-item label="院系名">
            <el-input v-model="departmentName"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="editDepartment" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="新增" :visible.sync="insertDepartmentDialogVisible">
      <div>新增院系。</div><br/>
      <el-form id="userInput" label-width="80px">
        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="院系号">
            <el-input v-model="departmentId"/>
          </el-form-item>

          <el-form-item label="院系名">
            <el-input v-model="departmentName"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="insertDepartment" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>


    <el-dialog title="专业管理" :visible.sync="majorDialogVisible" width="80%">
      <el-button style="float: right" type="text" @click="openInsertMajorDialog">新增</el-button>

      <el-table :data="majorList" stripe style="width: 100%" border>

        <el-table-column prop="majorId" label="专业号" />
        <el-table-column prop="majorName" label="专业名" />

        <el-table-column label="操作" :width="310">
          <template v-slot="scope">
            <el-popconfirm title="确定要删除吗？" icon="" style="margin-right: 10px" @confirm="deleteMajor(scope.row)">
              <el-button flex icon="el-icon-delete" slot="reference">删除</el-button>
            </el-popconfirm>
            <el-button icon="el-icon-edit" @click="openEditMajorDialog(scope.row)">编辑</el-button>
            <el-button icon="el-icon-collection" @click="openClassDialog(scope.row)">班级</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog title="编辑" :visible.sync="editMajorDialogVisible" width="60%">
      <div>修改专业信息。</div><br/>
      <el-form id="userInput" label-width="80px">
        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="专业号">
            <el-input v-model="majorId" :disabled="true"/>
          </el-form-item>

          <el-form-item label="专业名">
            <el-input v-model="majorName"/>
          </el-form-item>

          <el-form-item label="属院系号">
            <el-input v-model="shareDepartmentId"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="editMajor" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="新增" :visible.sync="insertMajorDialogVisible" width="60%">
      <div>新增专业信息。</div><br/>
      <el-form id="userInput" label-width="80px">
        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="专业号">
            <el-input v-model="majorId"/>
          </el-form-item>

          <el-form-item label="专业名">
            <el-input v-model="majorName"/>
          </el-form-item>

          <el-form-item label="属院系号">
            <el-input v-model="shareDepartmentId"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="insertMajor" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>


    <el-dialog title="班级管理" :visible.sync="classDialogVisible" width="60%">
      <el-button style="float: right" type="text" @click="openInsertClassDialog">新增</el-button>

      <el-table :data="classList" stripe style="width: 100%" border>
        <el-table-column prop="classId" label="班级号" />
        <el-table-column prop="className" label="班级名" />
        <el-table-column prop="size" label="人数" :width="60"/>


        <el-table-column label="操作" :width="210">
          <template v-slot="scope">
            <el-popconfirm title="确定要删除吗？" icon="" style="margin-right: 10px" @confirm="deleteClass(scope.row)">
              <el-button flex icon="el-icon-delete" slot="reference">删除</el-button>
            </el-popconfirm>
            <el-button icon="el-icon-edit" @click="openEditClassDialog(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog title="编辑" :visible.sync="editClassDialogVisible" width="40%">
      <div>修改班级信息。</div><br/>
      <el-form id="userInput" label-width="80px">
        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="班级号">
            <el-input v-model="classId" :disabled="true"/>
          </el-form-item>

          <el-form-item label="班级名">
            <el-input v-model="className"/>
          </el-form-item>

          <el-form-item label="属专业号">
            <el-input v-model="shareMajorId"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="editClass" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="新增" :visible.sync="insertClassDialogVisible" width="40%">
      <div>新增班级。</div><br/>
      <el-form id="userInput" label-width="80px">
        <div style="width: 30%; min-width: 300px; margin: 0 auto">
          <el-form-item label="班级号">
            <el-input v-model="classId"/>
          </el-form-item>

          <el-form-item label="班级名">
            <el-input v-model="className"/>
          </el-form-item>

          <el-form-item label="属专业号">
            <el-input v-model="shareMajorId"/>
          </el-form-item>
        </div>

        <div style="height: 30px"/>
        <el-form-item style="text-align: center">
          <el-button icon="el-icon-check" @click="insertClass" circle/>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>

</template>

<style>

</style>