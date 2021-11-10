package work.monkey.kotlintemplate.api

import android.security.KeyChainException
import java.security.KeyStore
import java.util.*
import javax.net.ssl.*
import java.security.SecureRandom


class MtlsHelper internal constructor(
    trustAll: Boolean = false
) {
    val trustManager: X509TrustManager
    val sslSocketFactory: SSLSocketFactory

    init {
        val trustManagers = if (trustAll) arrayOf(TrustAllTrustManager()) else systemTrustManagers()
        trustManager = trustManagers[0] as X509TrustManager

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustManagers, SecureRandom())
        sslSocketFactory = sslContext.socketFactory
    }

    class CertificateNotFoundException(message: String?) : Exception(message)
    class PrivateKeyNotFoundException(message: String?) : Exception(message)

    @Throws(
        InterruptedException::class,
        KeyChainException::class,
        CertificateNotFoundException::class,
        PrivateKeyNotFoundException::class
    )

    private fun systemTrustManagers(): Array<TrustManager> {
        val factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        factory.init(null as KeyStore?)
        val trustManagers = factory.trustManagers
        if (trustManagers.size != 1 || (trustManagers[0] !is X509TrustManager)) {
            throw IllegalStateException(
                "Unexpected default trust managers: ${Arrays.toString(trustManagers)}"
            )
        }

        return factory.trustManagers
    }
}