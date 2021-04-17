package com.mishok.polygo.domain.start

import android.content.SharedPreferences
import com.mishok.core_db_api.models.LocalBuildingInfo
import com.mishok.core_db_api.models.LocalBuildings
import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.core_db_api.providers.BuildingFiltersProvider
import com.mishok.core_db_api.providers.BuildingInfoProvider
import com.mishok.core_db_api.providers.BuildingsProvider
import com.mishok.core_db_api.providers.EmployeesProvider
import com.mishok.polygo.utils.putBoolean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartInteractorImpl @Inject constructor(
    private val buildingsProvider: BuildingsProvider,
    private val employeesProvider: EmployeesProvider,
    private val buildingInfoProvider: BuildingInfoProvider,
    private val sharedPreferencesProvider: SharedPreferences,
    private val buildingInfoFiltersProvider: BuildingFiltersProvider,
    private val coroutineScope: CoroutineScope
) : StartInteractor {

    companion object {
        private const val KEY_FIRST_TIME = "key_first_time"
    }

    override fun initLocalDatabase() {
        if (sharedPreferencesProvider.getBoolean(KEY_FIRST_TIME, true)) {
            sharedPreferencesProvider.putBoolean(KEY_FIRST_TIME, false)
            coroutineScope.launch {
                employeesProvider.saveEmployees(listLocalEmployees())
                buildingInfoProvider.saveBuildingInfo(listBuildingInside())
                buildingsProvider.saveBuildings(listLocalBuildings())
            }
        }
    }

    private fun listLocalBuildings(): List<LocalBuildings> {
        return listOf(
            LocalBuildings(
                id = 0,
                distance = 0,
                audienceId = 0,
                saved = false,
                latitude = 0.0,
                longitude = 0.0,
                corpId = 0,
                time = 0,
                title = "Building 0",
                floorId = 0
            ),
            LocalBuildings(
                id = 1,
                distance = 0,
                audienceId = 0,
                saved = false,
                latitude = 0.0,
                longitude = 0.0,
                corpId = 0,
                time = 0,
                title = "Building 1",
                floorId = 0
            ),
            LocalBuildings(
                id = 2,
                distance = 0,
                audienceId = 0,
                saved = false,
                latitude = 0.0,
                longitude = 0.0,
                corpId = 0,
                time = 0,
                title = "Building 2",
                floorId = 0
            ),
            LocalBuildings(
                id = 3,
                distance = 0,
                audienceId = 0,
                saved = false,
                latitude = 0.0,
                longitude = 0.0,
                corpId = 0,
                time = 0,
                title = "Building 3",
                floorId = 0
            ),
            LocalBuildings(
                id = 4,
                distance = 0,
                audienceId = 0,
                saved = false,
                latitude = 0.0,
                longitude = 0.0,
                corpId = 0,
                time = 0,
                title = "Building 4",
                floorId = 0
            ),
            LocalBuildings(
                id = 5,
                distance = 0,
                audienceId = 0,
                saved = false,
                latitude = 0.0,
                longitude = 0.0,
                corpId = 0,
                time = 0,
                title = "Building 5",
                floorId = 0
            )
        )
    }

    private fun listLocalEmployees(): List<LocalEmployees> {
        return listOf(
            LocalEmployees(
                name = "Аксарин Юрий Анатольевич",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/1731",
                contacts = "aksarin_yua@spbstu.ru",
                position = "доцент",
                avatar = "",
                saved = false
            ),

            LocalEmployees(
                name = "Аслямов Айрат Ахатович",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/7793",
                contacts = "aslyamov_aa@spbstu.ru",
                position = "доцент",
                avatar = "",
                saved = false
            )
        )
    }

    private fun listBuildingInside(): List<LocalBuildingInfo> {
        return listOf(
            LocalBuildingInfo(
                buildingId = 0,
                category = "ЕДА",
                title = "ffejwe 0000",
                location = "fejfweo 00000",
                saved = false
            ),
            LocalBuildingInfo(
                buildingId = 1,
                category = "СЛУЖБА",
                title = "ffejwe",
                location = "fejfweo",
                saved = false
            ),
            LocalBuildingInfo(
                buildingId = 1,
                category = "СЛУЖБА",
                title = "ffejwe 111111",
                location = "fejfweo 111111111",
                saved = false
            )
        )
    }

}
