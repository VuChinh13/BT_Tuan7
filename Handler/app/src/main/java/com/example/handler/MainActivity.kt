package com.example.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.handler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cong = 0
    private var tru = 0
    private val handler = Handler(Looper.getMainLooper())

    // Runnable để thực hiện việc cộng trong background thread
    private val runnableCong = object : Runnable {
        override fun run() {
            if (binding.btCong.text == "DỪNG CỘNG") {
                // Tạo một thread riêng để thực hiện tác vụ cộng trong background
                Thread {
                    // Thực hiện việc cộng trong background
                    cong++
                    // Sau khi thực hiện xong, cập nhật UI lên MainThread
                    handler.post {
                        binding.tvCong.text = "Giá trị hiện tại là: $cong"
                    }
                    Thread.sleep(1000L)
                    handler.post(this) // Tiếp tục gọi lại run() để thực hiện tiếp
                }.start() // Bắt đầu thread
            }
        }
    }

    private val runnableTru = object : Runnable {
        override fun run() {
            if (binding.btTru.text == "DỪNG TRỪ") {
                // Tạo một thread riêng để thực hiện tác vụ cộng trong background
                Thread {
                    // Thực hiện việc cộng trong background
                    tru--
                    // Sau khi thực hiện xong, cập nhật UI lên MainThread
                    handler.post {
                        binding.tvTru.text = "Giá trị hiện tại là: $tru"
                    }
                    Thread.sleep(1000L)
                    handler.post(this) // Tiếp tục gọi lại run() để thực hiện tiếp
                }.start() // Bắt đầu thread
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Chạy ban đầu
        handler.post(runnableCong)
      //  handler.post(runnableTru)

        // Đoạn code xử lý button cộng
        binding.btCong.setOnClickListener {
            if (binding.btCong.text == "DỪNG CỘNG") {
                binding.btCong.text = "CHẠY LẠI CỘNG"
                // Dừng việc cộng lại bằng cách hủy Runnable đã post
                handler.removeCallbacks(runnableCong)
            } else {
                binding.btCong.text = "DỪNG CỘNG"
                // Bắt đầu lại Runnable để cộng liên tục
                handler.post(runnableCong)
            }
        }

//        // Đoạn code xử lý button cộng
//        binding.btTru.setOnClickListener {
//            if (binding.btTru.text == "DỪNG TRỪ") {
//                binding.btTru.text = "CHẠY LẠI TRỪ"
//                // Dừng việc cộng lại bằng cách hủy Runnable đã post
//                handler.removeCallbacks(runnableCong)
//            } else {
//                binding.btTru.text = "DỪNG TRỪ"
//                // Bắt đầu lại Runnable để cộng liên tục
//                handler.post(runnableTru)
//            }
//        }
    }
}
