package app.a2ms.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.a2ms.aboutme.databinding.ActivityMainBinding

/**
 * Main Activity of the AboutMe app. This app demonstrates:
 *     * LinearLayout with TextViews, ImageView, Button, EditText, and ScrollView
 *     * ScrollView to display scrollable text
 *     * Getting user input with an EditText.
 *     * Click handler for a Button to retrieve text from an EditText and set it in a TextView.
 *     * Setting the visibility status of a view.
 *     * Data binding between MainActivity and activity_main.xml. How to remove findViewById,
 *       and how to display data in views using the data binding object.
 */

class MainActivity : AppCompatActivity() {
    // Binding object for MainActivity.
    // Name of the object is derived from the name of the activity or fragment.
    private lateinit var binding: ActivityMainBinding

    // Instance of MyName data class.
    private val myName: MyName = MyName("Innocent Dema Angwandia")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //using the data binding util to set the content view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
        binding.doneButton.setOnClickListener { addNickName(it) }
    }

    private fun addNickName(view: View) {
        //using the binding object to replace all calls to findViewById
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            // Invalidate all binding expressions and request a new rebind to refresh UI
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nickNameText.visibility = View.VISIBLE
        }
        //hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}
