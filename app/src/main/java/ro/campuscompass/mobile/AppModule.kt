package ro.campuscompass.mobile

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ro.campuscompass.mobile.repository.Repository
import ro.campuscompass.mobile.screens.auth.SignInViewModel
import ro.campuscompass.mobile.services.auth.AuthenticationService
import ro.campuscompass.mobile.services.auth.EmailAndPasswordClient

val appModule = module {
    single {
        EmailAndPasswordClient()
    }
    single {
        Repository()
    }
    single {
        AuthenticationService(get(), get())
    }
    viewModel {
        SignInViewModel(get())
    }
}
