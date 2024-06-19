import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tdl.R
import javax.inject.Inject

class MyViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    val spinnerValues : MutableList<String> = mutableListOf()
    init {
        //DaggerAppComponent.create().inject(this)
        spinnerValues.add(resourceProvider.getString(R.string.Spinner_Task))
        spinnerValues.add(resourceProvider.getString(R.string.Spinner_Sport))
        spinnerValues.add(resourceProvider.getString(R.string.Spinner_Doctor))
        spinnerValues.add(resourceProvider.getString(R.string.Spinner_School))
        spinnerValues.add(resourceProvider.getString(R.string.Spinner_Work))
    }


    fun getSelectedIndex(context: Context): Int {
        val sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val selectedType = sharedPreferences.getString("type", spinnerValues[0])
        return spinnerValues.indexOf(selectedType)
    }

    class AppViewModelFactory(private val resourceProvider: ResourceProvider) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
                return MyViewModel(resourceProvider = resourceProvider) as T
            }
            throw IllegalArgumentException()
        }
    }
}
