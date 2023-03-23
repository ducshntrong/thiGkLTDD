package com.example.thigk.Repository
// một lớp trừu tượng repository truy cập vào nhiều nguồn dữ liệu. repository không phải là một
// phần của thư viện Thành phần kiến trúc, nhưng là phương pháp hay nhất được đề xuất để phân tách mã và kiến trúc
import androidx.lifecycle.LiveData
import com.example.thigk.Data.UserDao
import com.example.thigk.Model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    //userDao là đối tượng truy cập dữ liệu

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }

//    fun searchDatabase(searchQuery: String): Flow<List<User>>{
//        return userDao.searchDatabase(searchQuery)
//    }
}