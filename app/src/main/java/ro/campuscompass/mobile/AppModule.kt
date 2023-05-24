package ro.campuscompass.mobile

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ro.campuscompass.mobile.repository.Repository
import ro.campuscompass.mobile.screens.auth.SignInViewModel
import ro.campuscompass.mobile.screens.student.StudentMainViewModel
import ro.campuscompass.mobile.screens.landlord.LandLordPropertiesViewModel
import ro.campuscompass.mobile.screens.landlord.addproperty.AddPropertyViewModel
import ro.campuscompass.mobile.screens.student.StudentApplicationViewModel
import ro.campuscompass.mobile.services.auth.AuthenticationService
import ro.campuscompass.mobile.services.auth.EmailAndPasswordClient
import ro.campuscompass.mobile.services.student.StudentService
import ro.campuscompass.mobile.services.auth.LandlordService
import ro.campuscompass.mobile.services.auth.UniversityService

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
    single {
        UniversityService(get())
    }
    single {
        LandlordService(get())
    }
    single {
        StudentService(get())
    }
    viewModel {
        SignInViewModel(get())
    }
    viewModel {
        StudentMainViewModel(get())
    }
    viewModel {
        AddPropertyViewModel(get(), get())
    }
    viewModel {
        LandLordPropertiesViewModel(get())
    }
    viewModel {
        StudentApplicationViewModel(get())
    }
}
