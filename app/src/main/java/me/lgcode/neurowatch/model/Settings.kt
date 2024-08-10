package me.lgcode.neurowatch.model

data class Settings(
    var emailsEnabled: Boolean,
    var pushNotificationsEnabled: Boolean,
    var recipientAddress: String
)
