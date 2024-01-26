package com.rejzerek.stickmanwarriors.feature_stickman.presentation.add_edit_stickman

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rejzerek.stickmanwarriors.R
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.InvalidStickmanException
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.StickmanClass
import com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case.StickmanUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditStickmanViewModel @Inject constructor(
    private val stickmanUseCases: StickmanUseCases,
    savedStateHandle: SavedStateHandle
) :ViewModel() {

    private val _stickmanName = mutableStateOf("")
    val stickmanName: State<String> = _stickmanName

    private val _stickmanClass = mutableStateOf(StickmanClass.ARCHER)
    val stickmanClass: State<StickmanClass> = _stickmanClass

    private val _stickmanHealth = mutableIntStateOf(0)
    val stickmanHealth: State<Int> = _stickmanHealth

    private val _stickmanDefense = mutableIntStateOf(0)
    val stickmanDefense: State<Int> = _stickmanDefense

    private val _stickmanAttackDamage = mutableIntStateOf(0)
    val stickmanAttackDamage: State<Int> = _stickmanAttackDamage

    private val _stickmanImageResource = mutableIntStateOf(R.drawable.archer_img)
    val stickmanImageResource: State<Int> = _stickmanImageResource

    private val _stickmanWeapon = mutableStateOf("")
    val stickmanWeapon: State<String> = _stickmanWeapon

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentStickmanId: Int? = null

    init {
        savedStateHandle.get<Int>("stickmanId")?.let { stickmanId ->
            if(stickmanId != -1) {
                viewModelScope.launch {
                    stickmanUseCases.getStickman(stickmanId)?.also { stickman ->
                        currentStickmanId = stickman.id
                        _stickmanName.value = stickman.name
                        _stickmanClass.value = stickman.`class`
                        _stickmanHealth.intValue = stickman.health
                        _stickmanDefense.intValue = stickman.defense
                        _stickmanAttackDamage.intValue = stickman.attackDamage
                        _stickmanImageResource.intValue = stickman.imageResource
                        _stickmanWeapon.value = stickman.weapon
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditStickmanEvent) {
        when(event) {
            is AddEditStickmanEvent.EnteredName -> {
                _stickmanName.value = event.value
            }
            is AddEditStickmanEvent.EnteredClass -> {
                _stickmanClass.value = event.value
                _stickmanImageResource.intValue = event.image
            }
            is AddEditStickmanEvent.EnteredHealth -> {
                _stickmanHealth.intValue = event.value
            }
            is AddEditStickmanEvent.EnteredDefense -> {
                _stickmanDefense.intValue = event.value
            }
            is AddEditStickmanEvent.EnteredAttackDamage -> {
                _stickmanAttackDamage.intValue = event.value
            }
            is AddEditStickmanEvent.EnteredWeapon -> {
                _stickmanWeapon.value = event.value
            }
            is AddEditStickmanEvent.SaveStickman -> {
                viewModelScope.launch {
                    try {
                        stickmanUseCases.addStickman(
                            Stickman(
                                currentStickmanId,
                                stickmanClass.value,
                                stickmanName.value,
                                stickmanHealth.value,
                                stickmanAttackDamage.value,
                                stickmanDefense.value,
                                stickmanImageResource.value,
                                stickmanWeapon.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveStickman)
                    } catch(e: InvalidStickmanException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save stickman"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveStickman: UiEvent()
    }
}
