package ru.virarnd.expertcourseunscramble

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import ru.virarnd.expertcourseunscramble.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = GameViewModel(GameRepository.Base(ShuffleStrategy.Reverse()))

        val uiState: GameUiState = viewModel.init()

        /*
                val watcher: TextWatcher = object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        viewModel.beforeText(text = s).update(binding = binding)
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        viewModel.onText(text = s).update(binding = binding)
                    }

                    override fun afterTextChanged(editable: Editable?) {
                        viewModel.afterText(editableText = editable).update(binding = binding)
                    }
                }
        */

        uiState.update(binding = binding)
        with(binding) {
            checkButton.setOnClickListener {
                viewModel.onCheck(userText = inputEditText.text.toString()).update(binding = binding)
            }
            skipButton.setOnClickListener {
                viewModel.onSkip().update(binding = binding)
            }
            nextButton.setOnClickListener {
                viewModel.onNext().update(binding = binding)
            }

//            inputEditText.addTextChangedListener(watcher)
            inputEditText.addTextChangedListener { inputText ->
                viewModel.handleInputText(userText = inputText.toString())
                    .update(binding = binding)
            }
        }

    }
}