package com.example.askhat.decathlon.events

import com.example.askhat.decathlon.Constants
import com.example.askhat.decathlon.core.createService
import org.koin.dsl.module.module

val eventsModule = module {
    single { createService<EventService>(get(), Constants.URL) }
}