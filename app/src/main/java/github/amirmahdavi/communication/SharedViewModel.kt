package github.amirmahdavi.communication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    // Set it's type to Any to achieve all types
    // but you can specify exact type

    // Just for First Fragment
    val getData = MutableLiveData<Any>()
    fun setData(data: Any) { getData.value = data }

    // Just for Second Fragment
    val secondData = MutableLiveData<Any>().apply { value = "Initial Value" }
}
