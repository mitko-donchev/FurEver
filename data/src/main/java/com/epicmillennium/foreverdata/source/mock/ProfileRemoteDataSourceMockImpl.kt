package com.epicmillennium.foreverdata.source.mock

import com.epicmillennium.foreverdata.repository.profile.ProfileRemoteDataSource
import com.epicmillennium.fureverdomain.profile.AdoptionStatus
import com.epicmillennium.fureverdomain.profile.DogProfile
import com.epicmillennium.fureverdomain.profile.Gender
import com.epicmillennium.fureverdomain.profile.UserProfile
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.util.UUID
import kotlin.random.Random

class ProfileRemoteDataSourceMockImpl : ProfileRemoteDataSource {

    // User
    private val userProfile = UserProfile(
        "mock_user",
        "John Doe",
        LocalDate.of(2000, 1, 1),
        "EU",
        "A lover of libraries, coffee, and dogs.",
        "man_1.jpg"
    )

    override suspend fun getUserProfile(): UserProfile = userProfile

    override suspend fun addUserProfile(
        userId: String,
        name: String,
        birthdate: LocalDate,
        location: String,
        bio: String,
        picture: String
    ) {
        delay(2000)
    }

    override suspend fun updateUserProfile(
        name: String?,
        birthDate: LocalDate?,
        location: String,
        bio: String,
        picture: String?
    ) {
        delay(2000)
    }

    // Dog
    override suspend fun getDogProfiles(): List<DogProfile> {
        delay(1000)
        return (0 until 10).mapIndexed { index, _ ->  getRandomProfile(index, true) }.toList()
    }

    override suspend fun addDogProfile(
        id: String,
        name: String,
        age: Int,
        breed: String,
        gender: Gender,
        size: String,
        color: String,
        vaccinated: Boolean,
        neutered: Boolean,
        healthStatus: String,
        temperament: String,
        goodWithKids: Boolean,
        goodWithOtherDogs: Boolean,
        goodWithCats: Boolean,
        activityLevel: String,
        adoptionFee: Double,
        adoptionStatus: AdoptionStatus,
        description: String,
        photos: List<String>,
        location: String,
        arrivalDate: String,
        pendingAdoptersCount: Int,
        pendingAdopters: List<String>
    ) {
        delay(2000)
    }

    override suspend fun updateDogProfile(
        dogId: String,
        name: String?,
        age: Int?,
        breed: String?,
        gender: Gender?,
        size: String?,
        color: String?,
        vaccinated: Boolean?,
        neutered: Boolean?,
        healthStatus: String?,
        temperament: String?,
        goodWithKids: Boolean?,
        goodWithOtherDogs: Boolean?,
        goodWithCats: Boolean?,
        activityLevel: String?,
        adoptionFee: Double?,
        adoptionStatus: AdoptionStatus?,
        description: String?,
        photos: List<String>,
        location: String?,
        arrivalDate: String?,
        pendingAdoptersCount: Int?,
        pendingAdopters: List<String>?
    ) {
        delay(2000)
    }

    override suspend fun passDogProfile(dogProfile: DogProfile) {}

    override suspend fun likeDogProfile(dogProfile: DogProfile): String? = null

    private fun getRandomProfile(index: Int, isMale: Boolean): DogProfile {
        val name = getRandomName(isMale)
        val age = getRandomAge()
        val gender = if (isMale) Gender.MALE else Gender.FEMALE
        return DogProfile(
            getRandomDogId(),
            name,
            age,
            "Unknown",
            gender,
            "small",
            "brown",
            vaccinated = true,
            neutered = true,
            "healthy",
            "friendly",
            goodWithKids = true,
            goodWithOtherDogs = true,
            goodWithCats = true,
            "normal",
            0.0,
            AdoptionStatus.FOR_ADOPTION,
            "Cute doggy",
            listOf(getImage(index)),
            "Sofia",
            "01.01.2024",
            0,
            emptyList(),
        )
    }

    private fun getRandomDogId(): String = UUID.randomUUID().toString()
    private fun getRandomName(isMale: Boolean): String =
        if (isMale) maleNames.random() else femaleNames.random()

    private fun getRandomAge(): Int = Random.nextInt(0, 15)

    private fun getImage(index: Int): String = images[index]

    private val images = listOf(
        "dog_1.jpg",
        "dog_2.jpg",
        "dog_3.jpg",
        "dog_4.jpg",
        "dog_5.jpg",
        "dog_6.jpg",
        "dog_7.jpg",
        "dog_8.jpg",
        "dog_9.jpg",
        "dog_10.jpg",
    )

    private val maleNames = listOf(
        "Ethan",
        "Reagan",
        "Bryson",
        "Blake",
        "Edgar",
        "Clark",
        "Dane",
        "Heath",
        "Charlie",
        "Elian",
        "Allen",
        "Walker",
        "Jadon",
        "Fernando",
        "Ellis",
        "Mohammed",
        "Kadin",
        "Joey",
        "Octavio",
        "Wyatt",
        "Aryan",
        "Cayden",
        "Jamari",
        "Donald",
        "Josue",
        "Kendrick",
        "Emmanuel",
        "Dustin",
        "Korbin",
        "Jasper",
        "Cameron",
        "Isiah",
        "Jeremy",
        "Alexzander",
        "Jared",
        "Bentley",
        "Oscar",
        "Ramon",
        "Jermaine",
        "John",
        "Tristian",
        "Jacob",
        "Yahir",
        "Giovanni",
        "Jaylon",
        "Marcus",
        "Javier",
        "Mathew",
        "Rayan",
        "Prince",
        "Jay",
        "Sincere",
        "Jesus",
        "Brayden",
        "Kayden",
        "Rhys",
        "Brodie",
        "Drake",
        "Landin",
        "Demetrius",
        "Mohamed",
        "Cason",
        "Calvin",
        "Maxwell",
        "Matias",
        "Anthony",
        "Liam",
        "Rodney",
        "Orion",
        "Ray",
        "August",
        "Matthew",
        "Jabari",
        "Joaquin",
        "Kole",
        "Brandon",
        "Konnor",
        "Rigoberto",
        "Jack",
        "Spencer",
        "Devan",
        "Aron",
        "Leo",
        "Marco",
        "Stephen",
        "Haiden",
        "Ian",
        "Coleman",
        "Levi",
        "Jayvion",
        "Keyon",
        "Brenton",
        "Payton",
        "Malachi",
        "Milton",
        "Tyrone",
        "Deegan",
        "Immanuel",
        "Eugene",
        "Harrison"
    )

    private val femaleNames = listOf(
        "Dominique",
        "Nyla",
        "Paulina",
        "Theresa",
        "Paula",
        "June",
        "Taniyah",
        "Zaniyah",
        "Kenna",
        "Lorelei",
        "Adeline",
        "Leyla",
        "Kennedy",
        "Fatima",
        "Emily",
        "Paityn",
        "Cadence",
        "Naima",
        "Khloe",
        "Justice",
        "Jaylyn",
        "Aleena",
        "Kaylie",
        "Nicole",
        "Brittany",
        "Sarai",
        "Bryanna",
        "Breanna",
        "Alejandra",
        "Imani",
        "Sophie",
        "Irene",
        "Alena",
        "Sharon",
        "Summer",
        "Danika",
        "Kiera",
        "Jocelynn",
        "Gabriella",
        "Jadyn",
        "Frances",
        "Ashtyn",
        "Skye",
        "Kaliyah",
        "Aliza",
        "Penelope",
        "Phoebe",
        "Addisyn",
        "Audrina",
        "Anya",
        "Aubrey",
        "Tessa",
        "Dayana",
        "Angie",
        "Hanna",
        "Iyana",
        "Carmen",
        "Leila",
        "Jaylah",
        "Meghan",
        "Aimee",
        "Madelynn",
        "Noemi",
        "Kamila",
        "Janiya",
        "Kaia",
        "Kenzie",
        "Camilla",
        "Nancy",
        "Ariana",
        "Magdalena",
        "Jamie",
        "Abagail",
        "Barbara",
        "Nayeli",
        "Raelynn",
        "Precious",
        "Lilia",
        "Jaycee",
        "Alaina",
        "Isabelle",
        "Adrianna",
        "Sanai",
        "Charlie",
        "Kelly",
        "Edith",
        "Gemma",
        "Raina",
        "Marisa",
        "Kierra",
        "Nola",
        "Rayna",
        "Kamora",
        "Dahlia",
        "Alondra",
        "Cassandra",
        "Whitney",
        "Adison",
        "Arely",
        "Carolina"
    )
}