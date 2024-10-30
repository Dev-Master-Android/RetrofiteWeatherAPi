package com.example.retrofiteweatherapi


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2

class WeatherActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewPager = findViewById(R.id.viewPager)
        adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Проверяем, если текущая страница последняя
                if (position == adapter.itemCount - 1) {
                    // Устанавливаем флаг, что последний фрагмент достигнут
                    isLastPage = true
                } else {
                    // Сбрасываем флаг, если не на последней странице
                    isLastPage = false
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                // Проверяем, если перелистывание закончилось и на последней странице
                if (state == ViewPager2.SCROLL_STATE_IDLE && isLastPage) {
                    // Отключаем возможность перелистывания назад
                    viewPager.isUserInputEnabled = false
                }
            }
        })
    }

    companion object {
        var isLastPage: Boolean = false
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

