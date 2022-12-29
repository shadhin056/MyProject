import com.example.myproject.model.CustomerLoginResponse
import com.example.myproject.model.CustomerRegResponse
import io.reactivex.Single
import retrofit2.http.*

interface Api {
    //API END POINT
    @POST("api/account/create")
    @FormUrlEncoded
    fun reqForRegistrationResponse(
        @Field("name") name: String?,
        @Field("accountNo") accountNo: String?,
        @Field("email") email: String?,
        @Field("userType") userType: String?,
        @Field("pin") pin: String?,
        @Field("dob") dob: String?,
        @Field("address") address: String?,
        @Field("gender") gender: String?,
        @Field("parentId") parentId: String?
    ): Single<CustomerRegResponse>

 //API END POINT
    @POST("api/login")
    @FormUrlEncoded
    fun reqForLoginResponse(
        @Field("accountNo") accountNo: String?,
        @Field("pin") pin: String?
    ): Single<CustomerLoginResponse>

}