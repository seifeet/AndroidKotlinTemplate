package work.monkey.kotlintemplate.api

import android.os.Build
import work.monkey.kotlintemplate.api.model.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class Repo(private val apiService: ApiService) {
    fun getHistory(): Observable<MonkeyListResponse> {
        return apiService.getMonkeyList(authorization = Build.ID)
    }

    fun initApp(request: InitAppDTO): Single<InitAppResponse> {
        return apiService.initApp(authorization = Build.ID, request = request)
    }

    fun postFindMonkeys(request: FindMonkeyDTO): Single<FindMonkeyResponse> {
        return apiService.findMonkeys(authorization = Build.ID, request = request)
    }
}