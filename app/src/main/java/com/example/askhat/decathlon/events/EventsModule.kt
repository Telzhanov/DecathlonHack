package com.example.askhat.decathlon.events

import com.example.askhat.decathlon.Constants
import com.example.askhat.decathlon.core.createService
import org.koin.dsl.module.module

val eventsModule = module {
    factory { createService<EventService>(get(), Constants.URL) }
}