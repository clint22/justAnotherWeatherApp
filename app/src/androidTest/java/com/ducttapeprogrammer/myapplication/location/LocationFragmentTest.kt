package com.ducttapeprogrammer.myapplication.location

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ducttapeprogrammer.myapplication.R
import com.ducttapeprogrammer.myapplication.ServiceLocator
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.source.FakeAndroidLocationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
class LocationFragmentTest {

    private lateinit var locationRepository: LocationRepository

    @Before
    fun initRepository() {
        locationRepository = FakeAndroidLocationRepository()
        ServiceLocator.locationRepository = locationRepository
    }

    @After
    fun cleanUpDb() {
        ServiceLocator.resetLocationRepository()
    }

    @Test
    fun currentLocation_DisplayedInUI() = runBlockingTest {

        locationRepository.insertPlace(
            Places(
                id = 1,
                latitude = 91.2,
                longitude = 93.2,
                region = "Raichur",
                state = "Karnataka",
                country = "India"
            )
        )

//        GIVEN
        val scenario =
            launchFragmentInContainer<LocationFragment>(Bundle(), R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
//        WHEN
        onView(withId(R.id.linearLayoutCurrentPlace)).perform(click())
        /*onView(withId(R.id.recycler_view_locations))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Raichur,Karnataka,India")), click()
                )
            )*/
        /*onView(withId(R.id.textViewCurrentPlace)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewCurrentPlace)).check(matches(withText("Your current location")))
        onView(withId(R.id.textViewLocationHeader)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewLocationHeader)).check(matches(withText("Locations")))*/

//        THEN
        verify(navController).navigate(R.id.action_locationFragment_to_forecastFragment, null)
        Thread.sleep(5000)

    }


}