package org.d3ifcool.urana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main2.*
import org.d3ifcool.urana.adapter.OnBoardingAdapter
import org.d3ifcool.urana.data.OnBoarding

class MainActivity2 : AppCompatActivity() {

    private val onBoardingAdapter = OnBoardingAdapter(
        listOf(
            OnBoarding(
                "Apa itu URANA?",
                "Urana adalah aplikasi permainan jujur atau jujur, kamu jadi bisa mengenali temanmu lebih baik karena pertanyaan yang di ajukan dan jawaban yang diberikan",
                R.drawable.ob1
            ),
            OnBoarding(
                "Jangan Boong!",
                "Kamu dan temanmu akan bermain bersama dan menjawab pertanyaan dari kartu pertanyaan yang telah di sediakan. Saat giliranmu berlangsung, jawab dengan jujur ya!",
                R.drawable.ob2
            ),
            OnBoarding(
                "Bikin Pertanyaan Sendiri",
                "Di Urana kamu bisa membuat pertanyaan mu sendiri. Kemudian pertanyaanmu akan muncul di kartu pertanyaan secara acak pada saat bermain",
                R.drawable.ob3
            ),
            OnBoarding(
                "Sung Main Lah !",
                "Cara main nya gampang. Kamu tinggal pilih menu Mulai Permainan dan masukan nama pemain, lalu tekan tombol Mulai Permainan",
                R.drawable.ob4
            ),
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        onBoardingViewPager.adapter = onBoardingAdapter
        setupIndicators()
        setCurrentIndicator(0)
        onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        btn_next.setOnClickListener{
            if (onBoardingViewPager.currentItem + 1 < onBoardingAdapter.itemCount){
                onBoardingViewPager.currentItem += 1
            } else {
                Intent(applicationContext, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }

            }
        }
        btn_back.setOnClickListener{
            if (onBoardingViewPager.currentItem - 1 < onBoardingAdapter.itemCount){
                onBoardingViewPager.currentItem -= 1
            } else {
                Intent(applicationContext, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }

            }
        }
        tv_skip.setOnClickListener{
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}