package com.example.incred_interview.utils

import java.io.IOException

class NoConnectivityException: IOException() {

    override val message: String?
        get() = "No internet"
}