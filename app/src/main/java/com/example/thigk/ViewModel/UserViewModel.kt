package com.example.thigk.ViewModel
// Vai trò của UserViewModel là cung cấp dữ liệu cho UI và tồn tại khi thay đổi
//cấu hình. Một ViewModel hoạt động như 1 giao tiếp trung tâm giữa repository và UI
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.thigk.Data.UserDatabase
import com.example.thigk.Model.User
import com.example.thigk.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    //Khối init luoon được thực thi đầu tiên khi mô hình chế độ xem người dùng được gọi
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

}