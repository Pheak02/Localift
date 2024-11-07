import com.local.lift.model.SignInRequest
import com.local.lift.model.SignInResponse
import com.local.locallift.api.AuthService
import retrofit2.Response

class SignInRepositoryImpl(private val authService: AuthService) : SignInRepository {
    override suspend fun signIn(signInRequest: SignInRequest): Response<SignInResponse> {
        return authService.signIn(signInRequest)
    }
}
