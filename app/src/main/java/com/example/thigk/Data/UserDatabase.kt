package com.example.thigk.Data
//UserDatabase chứa bộ csdl và đóng vai trò là điểm truy cập chính cho kết nối
// cơ bản với dữ liệu quan hệ
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thigk.Model.User

//Đặt versionlà 1. Bất cứ khi nào bạn thay đổi lược đồ, bạn sẽ phải tăng số phiên bản.
//Đặt exportSchema thành false, để không giữ các bản sao lưu lịch sử phiên bản lược đồ
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    //Cơ sở dữ liệu cần biết về DAO. khai báo một giá trị trừu tượng trả
    // về phần mở rộng UserDao
    abstract fun userDao(): UserDao

    //Đối tượng companion đồng hành cho phép các máy khách truy cập các phương thức để tạo hoặc lấy cơ
    //sở dữ liệu mà không cần khởi tạo lớp. Vì mục đích duy nhất của lớp này là cung cấp cơ sở dữ liệu
    companion object {
        //Biến INSTANCE sẽ giữ một tham chiếu đến cơ sở dữ liệu, khi một biến đã được tạo. Điều này giúp
        //tránh phải mở liên tục các kết nối tới cơ sở dữ liệu, điều này rất tốn kém về mặt tính toán.
        @Volatile
        private var INSTANCE: UserDatabase? = null
        //xác định phương thức getDatabase() có Context tham số mà trình tạo cơ sở dữ liệu sẽ cần.
        // Trả lại một loại UserDatabase
        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            //kiểm tra xem tempInstance có null hay không tức là chưa có cơ sở dữ liệu.
            if(tempInstance != null){
                return tempInstance
            }
            ////thêm một khối synchronized{}. Chuyển vào this để có thể truy cập ngữ cảnh
            //Gói mã để đưa cơ sở dữ liệu vào synchronized có nghĩa là mỗi lần chỉ có một luồng thực
            //thi có thể nhập khối mã này, điều này đảm bảo cơ sở dữ liệu chỉ được khởi tạo một lần.
            synchronized(this){
                ////gọi Room.databaseBuilder và cung cấp ngữ cảnh mà bạn đã chuyển vào,
                //lớp cơ sở dữ liệu và tên cho cơ sở dữ liệu, userDatabase
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}