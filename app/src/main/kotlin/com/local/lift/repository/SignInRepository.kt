import com.local.lift.model.SignInRequest
import com.local.lift.model.SignInResponse
import retrofit2.Response

interface SignInRepository {
    suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse>
}
