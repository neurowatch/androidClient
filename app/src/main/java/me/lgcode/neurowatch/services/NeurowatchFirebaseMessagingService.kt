package me.lgcode.neurowatch.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.lgcode.neurowatch.model.FCMTokenRequest
import me.lgcode.neurowatch.repo.NeurowatchRepo
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class NeurowatchFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var repo: NeurowatchRepo
    
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        scope.launch {
            repo.saveFcmToken(FCMTokenRequest(token))
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
}