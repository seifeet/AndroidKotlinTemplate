package work.monkey.kotlintemplate.api

import work.monkey.kotlintemplate.api.model.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ApiService {
    @Headers("User-Agent: Monkey")
    @GET(Constant.MONKEYS)
    fun getMonkeyList(@Header(Constant.AUTH_HEADER) authorization: String)
            : Observable<MonkeyListResponse>

    @POST(Constant.INIT_APP)
    fun initApp(
        @Header(Constant.AUTH_HEADER) authorization: String,
        @Body request: InitAppDTO
    ) : Single<InitAppResponse>

    @POST(Constant.FIND_MONKEYS)
    fun findMonkeys(
        @Header(Constant.AUTH_HEADER) authorization: String,
        @Body request: FindMonkeyDTO
    ) : Single<FindMonkeyResponse>
}