package com.example.coroutine
import kotlin.random.Random
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainScope = CoroutineScope(Dispatchers.Main)
    lateinit var congJob:Job
    lateinit var truJob:Job
    lateinit var nhanJob:Job
    lateinit var randomJob:Job
    private var cong:Int = 0
    private var tru:Int = 0
    private var nhan:Long = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khi mà bắt đầu chạy thì chạy tất cả các Job
        mainScope.launch {
            Cong()
            Tru()
            Nhan()
            Random()
        }

        binding.btCong.setOnClickListener {
            if (binding.btCong.text == "DỪNG CỘNG"){
                // chuyển đổi tên nút
                binding.btCong.text = "CHẠY LẠI CỘNG"
                congJob.cancel()
            }else {
                binding.btCong.text = "DỪNG CỘNG"
                mainScope.launch {
                    Cong()
                }
            }
        }

        binding.btTru.setOnClickListener {
            if (binding.btTru.text == "DỪNG TRỪ"){
                // chuyển đổi tên nút
                binding.btTru.text = "CHẠY LẠI TRỪ"
                truJob.cancel()
            }else {
                binding.btTru.text = "DỪNG TRỪ"
                mainScope.launch {
                    Tru()
                }
            }
        }

        binding.btNhan.setOnClickListener {
            if (binding.btNhan.text == "DỪNG NHÂN"){
                // chuyển đổi tên nút
                binding.btNhan.text = "CHẠY LẠI NHÂN"
                nhanJob.cancel()
            }else {
                binding.btNhan.text = "DỪNG NHÂN"
                mainScope.launch {
                    Nhan()
                }
            }
        }

        binding.btRadom.setOnClickListener {
            if (binding.btRadom.text == "DỪNG RANDOM"){
                // chuyển đổi tên nút
                binding.btRadom.text = "CHẠY LẠI RANDOM"
                randomJob.cancel()
            }else {
                binding.btRadom.text = "DỪNG RANDOM"
                mainScope.launch {
                    Random()
                }
            }
        }

    }

    private fun Cong(){
        this.congJob = mainScope.launch {
            repeat(1000) { // 1000 là số lần lặp
                delay(1000L)
                cong = cong + 1
                binding.tvCong.text = "Giá trị hiện tại là:" + cong.toString()
            }
        }
    }

    private fun Tru(){
        this.truJob = mainScope.launch {
            repeat(1000){
                delay(1000L)
                tru = tru - 1
                binding.tvTru.text = "Giá trị hiện tại là:" + tru.toString()
            }
        }
    }

    private fun Nhan(){
        this.nhanJob = mainScope.launch {
            repeat(1000){
                delay(5000L)
                nhan = nhan * 2
                binding.tvNhan.text = nhan.toString()
            }
        }
    }

    private fun Random(){
        this.randomJob = mainScope.launch {
            repeat(1000){
                delay(3000L)
                val randomIntRange = Random.nextInt(1, 1000)
                binding.tvRandom.text = randomIntRange.toString()
            }
        }
    }
}