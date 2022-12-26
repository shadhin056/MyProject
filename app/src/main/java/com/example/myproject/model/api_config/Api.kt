import com.example.myproject.model.CustomerRegResponse
import io.reactivex.Single
import retrofit2.http.*

interface Api {
    //API END POINT
    @FormUrlEncoded
    @POST("account/create")
    fun reqForRegistrationResponse(
        @Field("gender") gender: String?,
        @Field("name") name: String?,
        @Field("status") status: String?,
        @Field("email") email: String?,
    ): Single<CustomerRegResponse>

}