package com.vid.quizgame

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.vid.quizgame.databinding.ActivityQuizBinding
import kotlin.random.Random

class QuizActivity : AppCompatActivity() {
    lateinit var quizBinding: ActivityQuizBinding
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = database.reference.child("questions")

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val user = auth.currentUser
    private val scoreRef = database.reference

    var question = ""
    var answerA = ""
    var answerB = ""
    var answerC = ""
    var answerD = ""
    var correctAnswer = ""
    var questionCount = 0
    var questionNumber = 0

    var userAnswer = ""
    var userCorrect = 0
    var userWrong = 0


    lateinit var timer: CountDownTimer
    private val totalTime = 25000L
    var timerContinue = false
    var leftTime = totalTime

    val questions = HashSet<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        quizBinding = ActivityQuizBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(quizBinding.root)

        do {
            val number =  Random.nextInt(1, 11)
            Log.d("number", number.toString())
            questions.add(number)
        }while (questions.size < 5)

        Log.d("numberOfQuestions", questions.toString())
        gameLogic()

        quizBinding.btnNext.setOnClickListener {
            gameLogic()
            resetTimer()
        }
        quizBinding.btnFinish.setOnClickListener {
            sendScore()
        }

        quizBinding.tvQuestionAnswerA.setOnClickListener {
            pauseTimer()
            userAnswer = "a"
            if (correctAnswer == userAnswer) {
                quizBinding.tvQuestionAnswerA.setBackgroundColor(Color.GREEN)
                userCorrect++
                quizBinding.tvCorrectAnswerCount.text = userCorrect.toString()
            } else {
                quizBinding.tvQuestionAnswerA.setBackgroundColor(Color.RED)
                userWrong++
                quizBinding.tvWrongAnswerCount.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
        quizBinding.tvQuestionAnswerB.setOnClickListener {
            pauseTimer()
            userAnswer = "b"
            if (correctAnswer == userAnswer) {
                quizBinding.tvQuestionAnswerB.setBackgroundColor(Color.GREEN)
                userCorrect++
                quizBinding.tvCorrectAnswerCount.text = userCorrect.toString()
            } else {
                quizBinding.tvQuestionAnswerB.setBackgroundColor(Color.RED)
                userWrong++
                quizBinding.tvWrongAnswerCount.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
        quizBinding.tvQuestionAnswerC.setOnClickListener {
            pauseTimer()
            userAnswer = "c"
            if (correctAnswer == userAnswer) {
                quizBinding.tvQuestionAnswerC.setBackgroundColor(Color.GREEN)
                userCorrect++
                quizBinding.tvCorrectAnswerCount.text = userCorrect.toString()
            } else {
                quizBinding.tvQuestionAnswerC.setBackgroundColor(Color.RED)
                userWrong++
                quizBinding.tvWrongAnswerCount.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
        quizBinding.tvQuestionAnswerD.setOnClickListener {
            pauseTimer()
            userAnswer = "d"
            if (correctAnswer == userAnswer) {
                quizBinding.tvQuestionAnswerD.setBackgroundColor(Color.GREEN)
                userCorrect++
                quizBinding.tvCorrectAnswerCount.text = userCorrect.toString()
            } else {
                quizBinding.tvQuestionAnswerD.setBackgroundColor(Color.RED)
                userWrong++
                quizBinding.tvWrongAnswerCount.text = userWrong.toString()
                findAnswer()
            }
            disableClickableOfOptions()
        }
    }

    private fun gameLogic() {
        restoreOptions()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                questionCount = snapshot.childrenCount.toInt()
                if (questionNumber < questions.size) {

                    question = snapshot.child(questions.elementAt(questionNumber).toString()).child("q").value.toString()
                    answerA = snapshot.child(questions.elementAt(questionNumber).toString()).child("a").value.toString()
                    answerB = snapshot.child(questions.elementAt(questionNumber).toString()).child("b").value.toString()
                    answerC = snapshot.child(questions.elementAt(questionNumber).toString()).child("c").value.toString()
                    answerD = snapshot.child(questions.elementAt(questionNumber).toString()).child("d").value.toString()
                    correctAnswer =
                        snapshot.child(questions.elementAt(questionNumber).toString()).child("answer").value.toString()

                    quizBinding.tvQuestionTitle.text = question
                    quizBinding.tvQuestionAnswerA.text = answerA
                    quizBinding.tvQuestionAnswerB.text = answerB
                    quizBinding.tvQuestionAnswerC.text = answerC
                    quizBinding.tvQuestionAnswerD.text = answerD

                    quizBinding.progressBarQuiz.visibility = View.INVISIBLE
                    quizBinding.linearLayoutInfo.visibility = View.VISIBLE
                    quizBinding.linearLayoutQuestion.visibility = View.VISIBLE
                    quizBinding.linearLayoutButtons.visibility = View.VISIBLE

                    startTimer()
                } else {
                    val dialogMessage = AlertDialog.Builder(this@QuizActivity)
                    dialogMessage.setTitle("Quiz game")
                    dialogMessage.setMessage("Congratulations!!!\nYou have answer all the questions. Do you want to see the result?")
                    dialogMessage.setCancelable(false)
                    dialogMessage.setPositiveButton("See Result") { dialogWindow, position ->
                        sendScore()
                    }
                    dialogMessage.setNegativeButton("Play Again") { dialogWindow, position ->
                        val intent = Intent(this@QuizActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    dialogMessage.create().show()
                }
                questionNumber++
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    applicationContext,
                    error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun findAnswer() {
        when (correctAnswer) {
            "a" -> quizBinding.tvQuestionAnswerA.setBackgroundColor(Color.GREEN)
            "b" -> quizBinding.tvQuestionAnswerB.setBackgroundColor(Color.GREEN)
            "c" -> quizBinding.tvQuestionAnswerC.setBackgroundColor(Color.GREEN)
            "d" -> quizBinding.tvQuestionAnswerD.setBackgroundColor(Color.GREEN)
        }
    }

    private fun disableClickableOfOptions() {
        quizBinding.tvQuestionAnswerA.isClickable = false
        quizBinding.tvQuestionAnswerB.isClickable = false
        quizBinding.tvQuestionAnswerC.isClickable = false
        quizBinding.tvQuestionAnswerD.isClickable = false
    }

    private fun restoreOptions() {
        quizBinding.tvQuestionAnswerA.setBackgroundColor(Color.WHITE)
        quizBinding.tvQuestionAnswerB.setBackgroundColor(Color.WHITE)
        quizBinding.tvQuestionAnswerC.setBackgroundColor(Color.WHITE)
        quizBinding.tvQuestionAnswerD.setBackgroundColor(Color.WHITE)

        quizBinding.tvQuestionAnswerA.isClickable = true
        quizBinding.tvQuestionAnswerB.isClickable = true
        quizBinding.tvQuestionAnswerC.isClickable = true
        quizBinding.tvQuestionAnswerD.isClickable = true
    }

    private fun startTimer() {
        timer = object : CountDownTimer(leftTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                leftTime = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                disableClickableOfOptions()
                resetTimer()
                updateCountDownText()
                quizBinding.tvQuestionTitle.text =
                    "Sorry, Time is up! Continue with next question. "
                timerContinue = false
            }

        }.start()
        timerContinue = true
    }

    fun updateCountDownText() {
        val remainingTime: Int = (leftTime / 1000).toInt()
        quizBinding.tvTimeCount.text = remainingTime.toString()
    }

    private fun pauseTimer() {
        timer.cancel()
        timerContinue = false
    }

    fun resetTimer() {
        pauseTimer()
        leftTime = totalTime
        updateCountDownText()
    }

    fun sendScore() {
        user?.let {
            val userUID = user.uid
            scoreRef.child("scores").child(userUID).child("correct").setValue(userCorrect)
            scoreRef.child("scores").child(userUID).child("wrong").setValue(userWrong)
                .addOnSuccessListener {
                    Toast.makeText(
                        applicationContext,
                        "Score sent to database successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                    startActivity(intent)
                    finish()
                }
        }

    }
}