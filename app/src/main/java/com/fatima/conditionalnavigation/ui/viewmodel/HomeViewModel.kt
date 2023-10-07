package com.fatima.conditionalnavigation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomeViewModel : ViewModel() {

    fun getAndroidTopics(): List<String> {
        return listOf(
            "Activity Lifecycle",
            "Fragments",
            "Intents and Intent Filters",
            "RecyclerView",
            "ViewPager",
            "Custom Views",
            "Permissions",
            "Android App Architecture (e.g., MVVM, MVP)",
            "Data Binding",
            "SharedPreferences",
            "Content Providers",
            "Networking (e.g., Retrofit, Volley)",
            "REST APIs",
            "JSON Parsing (e.g., Gson, Jackson)",
            "Services and Background Processing",
            "Broadcast Receivers",
            "Notifications",
            "App Widgets",
            "Material Design Guidelines",
            "User Interface (UI) Design",
            "Android Animation and Transitions",
            "Android Jetpack Components",
            "ViewModel",
            "LiveData",
            "Room",
            "Navigation",
            "WorkManager",
            "Dependency Injection (e.g., Dagger, Hilt)",
            "Testing (e.g., JUnit, Espresso)",
            "Debugging and Profiling"
        )

    }
}