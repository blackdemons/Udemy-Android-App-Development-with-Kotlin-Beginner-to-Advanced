package com.vid.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vid.mathgame.databinding.ActivityGameBinding
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding

    var correctAnswer = 0
    var userScope = 0
    var userLife = 3

    lateinit var timer: CountDownTimer
    private val startTimerInMillis: Long = 60000
    var timeLeftInMillis: Long = startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityGameBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.title = getString(R.string.btn_addition)

        gameContinue()

        binding.btnOk.setOnClickListener {

            val input = binding.etAnswer.text.toString()

            if (input == "") {
                Toast.makeText(
                    applicationContext, getString(R.string.quest_text),
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                pauseTimer()

                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer) {

                    userScope += 10
                    binding.tvQuest.text = getString(R.string.tv_QuestAnswerRight)
                    binding.tvScopeCount.text = userScope.toString()


                } else {

                    userLife--
                    binding.tvQuest.text = getString(R.string.tv_QuestAnswerFalse)
                    binding.tvLifeCount.text = userLife.toString()
                }

            }

        }
        binding.btnNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            binding.etAnswer.setText("")

            if(userLife == 0){
                Toast.makeText(applicationContext, getString(R.string.game_over), Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScope)
                startActivity(intent)
                finish()
            }else{
                gameContinue()
            }
        }

    }

    fun gameContinue() {
        val number1 = Random.nextInt(0, 100)
        val number2 = Random.nextInt(0, 100)

        binding.tvQuest.text = "$number1 + $number2"

        correctAnswer = number1 + number2

        starTimer()
    }

    fun starTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLife--

                binding.tvLifeCount.text = userLife.toString()
                binding.tvQuest.text = getString(R.string.tv_QuestAnswerTimerLeft)

            }

        }.start()
    }

    fun updateText() {
        val remainingTime : Int = (timeLeftInMillis / 1000).toInt()
        binding.tvTimeCount.text = String.format(Locale.getDefault(),"%02d", remainingTime)
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {
        timeLeftInMillis = startTimerInMillis
        updateText()
    }
}