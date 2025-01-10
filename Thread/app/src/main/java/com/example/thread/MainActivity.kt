package com.example.thread

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.thread.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var threadCong: Thread
    private var cong = 0
    private var tru = 0
    private var nhan = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bắt đầu thread
        startThreadTru()
        startThreadNhan()
        startThreadRandom()


        // Tạo đối tượng Runnable
        val runnableCong = object : Runnable {
            override fun run() {
                while (true) {  // Kiểm tra trạng thái interrupt
                    if (binding.btCong.text == "DỪNG CỘNG") {
                        cong++
                        runOnUiThread { // Cập nhật lên giao diện UI Thread
                            binding.tvCong.text = "Giá trị hiện tại là: $cong"
                        }
                        Thread.sleep(1000L)  // Ngủ 1 giây
                    }
                }
            }
        }

        threadCong = Thread(runnableCong)
        threadCong.start()

        binding.btCong.setOnClickListener {
            if (binding.btCong.text == "DỪNG CỘNG") {
                binding.btCong.text = "CHẠY LẠI CỘNG"
            } else {
                binding.btCong.text = "DỪNG CỘNG"
            }
        }


        binding.btTru.setOnClickListener {
            if (binding.btTru.text == "DỪNG TRỪ") {
                binding.btTru.text = "CHẠY LẠI TRỪ"
            } else {
                binding.btTru.text = "DỪNG TRỪ"
            }
        }


        binding.btNhan.setOnClickListener {
            if (binding.btNhan.text == "DỪNG NHÂN") {
                binding.btNhan.text = "CHẠY LẠI NHÂN"
            } else {
                binding.btNhan.text = "DỪNG NHÂN"
            }
        }


        binding.btRandom.setOnClickListener {
            if (binding.btRandom.text == "DỪNG RANDOM") {
                binding.btRandom.text = "CHẠY LẠI RANDOM"
            } else {
                binding.btRandom.text = "DỪNG RANDOM"
            }
        }
    }




    fun startThreadTru() {
        Thread {
            while (true) {
                if (binding.btTru.text == "DỪNG TRỪ") {
                    tru--
                    runOnUiThread {
                        binding.tvTru.text = "Giá trị hiện tại là: $tru"
                    }
                    Thread.sleep(1000L)
                    Log.d("Thread tag: ", "${Thread.currentThread().name}")
                }
            }
        }.start()  // Bắt đầu thread
    }


    fun startThreadNhan() {
        Thread {
            while(true) {
                if (binding.btNhan.text == "DỪNG NHÂN") {
                    nhan = nhan * 2
                    runOnUiThread {
                        binding.tvNhan.text = "$nhan"
                    }
                    Thread.sleep(5000L)
                }
            }
        }.start()  // Bắt đầu thread
    }

    fun startThreadRandom() {
        Thread {
            while(true){
                if (binding.btRandom.text == "DỪNG RANDOM") {
                    runOnUiThread {
                        binding.tvRandom.text = "${Random.nextInt(1,1000)}"
                    }
                    Thread.sleep(3000L)
                }
            }
        }.start()  // Bắt đầu thread
    }
}


