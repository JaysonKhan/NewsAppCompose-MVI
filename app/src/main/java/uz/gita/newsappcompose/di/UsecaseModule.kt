package uz.gita.newsappcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.newsappcompose.domain.usecase.UseCase
import uz.gita.newsappcompose.domain.usecase.UseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UsecaseModule {

    @Binds
    fun bindUseCase(impl: UseCaseImpl): UseCase

}