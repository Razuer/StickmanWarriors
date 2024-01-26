package com.rejzerek.stickmanwarriors.feature_stickman.presentation.stickmen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rejzerek.stickmanwarriors.feature_stickman.domain.model.Stickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case.StickmanUseCases
import com.rejzerek.stickmanwarriors.feature_stickman.domain.util.OrderType
import com.rejzerek.stickmanwarriors.feature_stickman.domain.util.StickmanOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StickmenViewModel @Inject constructor(
    private val stickmanUseCases: StickmanUseCases
) : ViewModel(){

    private val _state = mutableStateOf(StickmenState())
    val state : State<StickmenState> = _state

    private var recentlyDeletedStickman : Stickman? = null

    private var getStickmenJob: Job? = null

    init {
        getStickmen(StickmanOrder.Name(OrderType.Ascending))
    }

    fun onEvent(event: StickmenEvent) {
        when(event) {
            is StickmenEvent.Order -> {
                if (state.value.stickmanOrder::class == event.stickmanOrder::class &&
                    state.value.stickmanOrder.orderType == event.stickmanOrder.orderType
                ) {
                    return
                }
                getStickmen(event.stickmanOrder)
            }
            is StickmenEvent.DeleteStickman -> {
                viewModelScope.launch {
                    stickmanUseCases.deleteStickman(event.stickman)
                    recentlyDeletedStickman = event.stickman
                }
            }
            is StickmenEvent.RestoreStickman -> {
                viewModelScope.launch {
                    stickmanUseCases.addStickman(recentlyDeletedStickman ?: return@launch)
                    recentlyDeletedStickman = null
                }

            }
            is StickmenEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }
    private fun getStickmen(stickmanOrder: StickmanOrder){
        getStickmenJob?.cancel()
        getStickmenJob = stickmanUseCases.getStickmen(stickmanOrder)
            .onEach { stickmen ->
                _state.value = state.value.copy(
                    stickmen = stickmen,
                    stickmanOrder = stickmanOrder
                )
            }
            .launchIn(viewModelScope)
    }
}