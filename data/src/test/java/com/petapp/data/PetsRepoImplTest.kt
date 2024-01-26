package com.petapp.data

import com.petapp.data.local.ApiKeysLocalSource
import com.petapp.data.local.PetsAuthLocalDataSource
import com.petapp.data.local.PetsLocalDataSource
import com.petapp.data.remote.PetsRemoteDataSource
import com.petapp.data.remote.model.LoginDto
import com.petapp.domain.pets.model.Pet
import com.petapp.domain.pets.model.Pets
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class, RxImmediateSchedulerExtension::class)
class PetsRepoImplTest {

    @MockK
    private lateinit var petsRemoteDataSource: PetsRemoteDataSource

    @MockK
    private lateinit var petsLocalDataSource: PetsLocalDataSource

    @MockK
    private lateinit var apiKeysLocalSource: ApiKeysLocalSource

    @MockK
    private lateinit var authLocalDataSource: PetsAuthLocalDataSource

    private lateinit var objectUnderTest: PetsRepoImpl
    private val mockPetId = 3
    private val mockClientId = "mockClientId"
    private val mockClientSecret = "mockClientSecret"
    private val mockToken = "mockToken"
    private val mockType = "mockType"
    private val mockExpiryIn = 3600L
    private val mockPage = 1

    @BeforeEach
    fun setUp() {
        objectUnderTest = PetsRepoImpl(
            petsRemoteDataSource = petsRemoteDataSource,
            petsLocalDataSource = petsLocalDataSource,
            apiKeysLocalSource = apiKeysLocalSource,
            authLocalDataSource = authLocalDataSource
        )
    }

    @Test
    fun `getPet with a given id, calls the local data source to retrieve data`() {
        //given
        val mockPet = mockk<Pet>()
        every { petsLocalDataSource.getPet(mockPetId) } returns Single.just(mockPet)

        //when
        val testObserver = objectUnderTest.getPet(mockPetId).test()

        //then
        testObserver
            .await()
            .assertResult(mockPet)
            .dispose()

        verify(exactly = 1) { petsLocalDataSource.getPet(mockPetId) }
    }

    @Test
    fun `getPets when access token is null, should get a valid token first then proceed with fetch pets`() {
        //given
        val mockPets = mockk<Pets>()
        val loginDto = mockk<LoginDto>()
        every { authLocalDataSource.getToken() } returns ""
        every { authLocalDataSource.getExpireTime() } returns 0
        every { loginDto.tokenType } returns mockType
        every { loginDto.accessToken } returns mockToken
        every { loginDto.expiresIn } returns mockExpiryIn
        every { authLocalDataSource.setToken("$mockType $mockToken") } just Runs
        every { authLocalDataSource.setExpireTime(any()) } just Runs
        every { apiKeysLocalSource.getClientId() } returns mockClientId
        every { apiKeysLocalSource.getClientSecret() } returns mockClientSecret
        every { petsLocalDataSource.getAllPets() } returns Observable.empty()
        every { petsRemoteDataSource.getAuthToken(mockClientId, mockClientSecret) } returns Single.just(loginDto)
        every { petsRemoteDataSource.getAnimals("$mockType $mockToken", mockPage) } returns Single.just(mockPets)
        every { petsLocalDataSource.savePets(mockPets) } returns Completable.complete()

        //when
        val testObserver = objectUnderTest.getPets(mockPage).test()

        //then
        testObserver
            .await()
            .assertResult(mockPets)
            .dispose()

        verify(exactly = 1) { authLocalDataSource.getToken() }
        verify(exactly = 1) { authLocalDataSource.getExpireTime() }
        verify(exactly = 1) { loginDto.tokenType }
        verify(exactly = 1) { loginDto.accessToken }
        verify(exactly = 1) { loginDto.expiresIn }
        verify(exactly = 1) { authLocalDataSource.setToken("$mockType $mockToken") }
        verify(exactly = 1) { authLocalDataSource.setExpireTime(any()) }
        verify(exactly = 1) { apiKeysLocalSource.getClientId() }
        verify(exactly = 1) { apiKeysLocalSource.getClientSecret() }
        verify(exactly = 1) { petsLocalDataSource.getAllPets() }
        verify(exactly = 1) { petsRemoteDataSource.getAuthToken(mockClientId, mockClientSecret) }
        verify(exactly = 1) { petsRemoteDataSource.getAnimals("$mockType $mockToken", mockPage) }
        verify(exactly = 1) { petsLocalDataSource.savePets(mockPets) }

    }

}