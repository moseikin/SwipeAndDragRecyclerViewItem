package com.mosugu.swipedragrvadapterlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.mosugu.sndrvadapter.SwipeAndDragHelper
import com.mosugu.swipedragrvadapterlibrary.databinding.ActivityMainBinding
import org.apache.commons.lang3.RandomStringUtils
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val rvAdapter = RvAdapter(initDataLIst())

        ItemTouchHelper(SwipeAndDragHelper(rvAdapter)).attachToRecyclerView(binding.rv)



        binding.rv.adapter = rvAdapter

    }

    private fun initDataLIst() :  MutableList<String>{
        val dataList = arrayListOf<String>()
        for (i in 0 until 15) dataList.add(RandomStringUtils.randomAlphabetic(10))
        return dataList
    }
}