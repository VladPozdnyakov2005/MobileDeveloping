package com.example.mobiledeveloping.appinfo

import androidx.lifecycle.ViewModel
import com.example.appinfo.db.MainDb
import kotlinx.coroutines.Job


@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDb: MainDb
): ViewModel(){
    val mainList = mutableStateOf(emptyList<ListItem>())
    private var job: Job? = null

    fun getAllItemsByCategory(cat: String){
        job?.cancel()
        job = viewModelScope.launch{
            mainDb.dao.getAllItemsByCategory(cat).collect{list ->
                mainList.value = list
            }
        }
    }

    fun getFavorites(){
        job?.cancel()
        job = viewModelScope.launch{
            mainDb.dao.getFavorites().collect{list ->
                mainList.value = list
            }
        }
    }

    fun insertItem(item: ListItem) = viewModelScope.launch{
        mainDb.dao.insertItem(item)
    }

    fun deleteItem(item: ListItem) = viewModelScope.launch{
        mainDb.dao.deleteItem(item)
    }
}