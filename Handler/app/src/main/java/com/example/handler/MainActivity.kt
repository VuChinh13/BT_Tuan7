package com.example.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.handler.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cong = 0
    private var tru = 0
    private var nhan = 1
    private var randomNumber = 0

    private var handlerCong: Handler? = null
    private var handlerTru: Handler? = null
    private var handlerNhan: Handler? = null
    private var handlerRandom: Handler? = null

    // Tạo một thread riêng cho việc cộng
    private val backgroundThreadCong = Thread {
        Looper.prepare()  // Chuẩn bị Looper cho thread nền

        handlerCong = Handler(Looper.myLooper()!!)  // Handler gắn với Looper của thread này

        val runnableCong = object : Runnable {
            override fun run() {
                if (binding.btCong.text == "DỪNG CỘNG") {
                    cong++  // Thực hiện việc cộng
                    binding.tvCong.post {
                        binding.tvCong.text = "Giá trị hiện tại là: $cong"
                    }
                    handlerCong?.postDelayed(this, 1000L) // Tiếp tục gửi Runnable sau 1 giây
                }
            }
        }

        // Bắt đầu Runnable cho việc cộng
        handlerCong?.post(runnableCong)

        Looper.loop()  // Bắt đầu Looper của thread này
    }

    // Tạo một thread riêng cho việc trừ
    private val backgroundThreadTru = Thread {
        Looper.prepare()  // Chuẩn bị Looper cho thread nền

        handlerTru = Handler(Looper.myLooper()!!)  // Handler gắn với Looper của thread này

        val runnableTru = object : Runnable {
            override fun run() {
                if (binding.btTru.text == "DỪNG TRỪ") {
                    tru--  // Thực hiện việc trừ
                    binding.tvTru.post {
                        binding.tvTru.text = "Giá trị hiện tại là: $tru"
                    }
                    handlerTru?.postDelayed(this, 1000L) // Tiếp tục gửi Runnable sau 1 giây
                }
            }
        }

        // Bắt đầu Runnable cho việc trừ
        handlerTru?.post(runnableTru)

        Looper.loop()  // Bắt đầu Looper của thread này
    }

    // Tạo một thread riêng cho việc nhân
    private val backgroundThreadNhan = Thread {
        Looper.prepare()  // Chuẩn bị Looper cho thread nền

        handlerNhan = Handler(Looper.myLooper()!!)  // Handler gắn với Looper của thread này

        val runnableNhan = object : Runnable {
            override fun run() {
                // Thực hiện việc nhân
                nhan *= 2
                binding.tvNhan.post {
                    binding.tvNhan.text = "Giá trị hiện tại là: $nhan"
                }
                handlerNhan?.postDelayed(this, 5000L) // Tiếp tục gửi Runnable sau 5 giây
            }
        }

        // Bắt đầu Runnable cho việc nhân
        handlerNhan?.post(runnableNhan)

        Looper.loop()  // Bắt đầu Looper của thread này
    }

    // Tạo một thread riêng cho việc random
    private val backgroundThreadRandom = Thread {
        Looper.prepare()  // Chuẩn bị Looper cho thread nền

        handlerRandom = Handler(Looper.myLooper()!!)  // Handler gắn với Looper của thread này

        val runnableRandom = object : Runnable {
            override fun run() {
                // Sau 3 giây, tạo số ngẫu nhiên
                randomNumber = Random.nextInt(1, 100)
                binding.tvRandom.post {
                    binding.tvRandom.text = "Số ngẫu nhiên là: $randomNumber"
                }
                handlerRandom?.postDelayed(this, 3000L) // Tiếp tục gửi Runnable sau 3 giây
            }
        }

        // Bắt đầu Runnable cho việc random
        handlerRandom?.post(runnableRandom)

        Looper.loop()  // Bắt đầu Looper của thread này
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bắt đầu các thread nền khi ứng dụng được tạo
        backgroundThreadCong.start()
        backgroundThreadTru.start()
        backgroundThreadNhan.start()
        backgroundThreadRandom.start()

        // Xử lý button Cộng
        binding.btCong.setOnClickListener {
            if (binding.btCong.text == "DỪNG CỘNG") {
                binding.btCong.text = "CHẠY LẠI CỘNG"
            } else {
                binding.btCong.text = "DỪNG CỘNG"
                // Bắt đầu lại Runnable để cộng liên tục
                val runnableCong = object : Runnable {
                    override fun run() {
                        if (binding.btCong.text == "DỪNG CỘNG") {
                            cong++  // Thực hiện việc cộng
                            binding.tvCong.post {
                                binding.tvCong.text = "Giá trị hiện tại là: $cong"
                            }
                            handlerCong?.postDelayed(this, 1000L) // Tiếp tục gửi Runnable sau 1 giây
                        }
                    }
                }
                handlerCong?.post(runnableCong)  // Gửi lại runnable vào handler
            }
        }

        // Xử lý button Trừ
        binding.btTru.setOnClickListener {
            if (binding.btTru.text == "DỪNG TRỪ") {
                binding.btTru.text = "CHẠY LẠI TRỪ"
            } else {
                binding.btTru.text = "DỪNG TRỪ"
                // Bắt đầu lại Runnable để trừ liên tục
                val runnableTru = object : Runnable {
                    override fun run() {
                        if (binding.btTru.text == "DỪNG TRỪ") {
                            tru--  // Thực hiện việc trừ
                            binding.tvTru.post {
                                binding.tvTru.text = "Giá trị hiện tại là: $tru"
                            }
                            handlerTru?.postDelayed(this, 1000L) // Tiếp tục gửi Runnable sau 1 giây
                        }
                    }
                }
                handlerTru?.post(runnableTru)  // Gửi lại runnable vào handler
            }
        }
    }
}
