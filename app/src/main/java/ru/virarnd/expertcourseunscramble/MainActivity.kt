package ru.virarnd.expertcourseunscramble

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.virarnd.expertcourseunscramble.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY = "uiState"
    }

    private lateinit var uiState: GameUiState
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GameViewModel

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(inputText: Editable?) {
            uiState = viewModel.handleInputText(userText = inputText.toString())
            uiState.update(binding = binding)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = (application as UnscrambleApp).viewModel
        uiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(KEY, GameUiState::class.java) as GameUiState
            } else {
                savedInstanceState.getSerializable(KEY) as GameUiState
            }
        }
        uiState.update(binding = binding)

        with(binding) {
            checkButton.setOnClickListener {
                uiState = viewModel.onCheck(userText = inputEditText.text.toString())
                uiState.update(binding = binding)
            }
            skipButton.setOnClickListener {
                uiState = viewModel.onSkip()
                uiState.update(binding = binding)
            }
            nextButton.setOnClickListener {
                uiState = viewModel.onNext()
                uiState.update(binding = binding)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.inputEditText.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputEditText.removeTextChangedListener(textWatcher)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }
}