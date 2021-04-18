package com.mishok.polygo.domain.start

import android.content.SharedPreferences
import com.google.android.gms.maps.MapFragment
import com.mishok.core_db_api.models.LocalBuildingInfo
import com.mishok.core_db_api.models.LocalBuildings
import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.core_db_api.providers.BuildingFiltersProvider
import com.mishok.core_db_api.providers.BuildingInfoProvider
import com.mishok.core_db_api.providers.BuildingsProvider
import com.mishok.core_db_api.providers.EmployeesProvider
import com.mishok.polygo.ui.map.MapsFragment
import com.mishok.polygo.utils.putBoolean
import dagger.internal.MapFactory
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
                latitude = MapsFragment.POLITECH_LATITUDE,
                longitude = MapsFragment.POLITECH_LONGITUDE,
                corpId = 0,
                time = "пн-пт 9:00 - 18:00 сб-вс 10:00 - 16:00",
                title = "Главное здание",
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
                position = "Доцент",
                avatar = "",
                saved = false
            ),
            LocalEmployees(
                name = "Аслямов Айрат Ахатович",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/7793",
                contacts = "aslyamov_aa@spbstu.ru",
                position = "Доцент",
                avatar = "",
                saved = false
            ),
            LocalEmployees(
                name = "Жук Александр Евгеньевич",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/98619",
                contacts = "zhuk_ae@spbstu.ru",
                position = "Доцент",
                avatar = "",
                saved = false
            ),
            LocalEmployees(
                name = "Бручас Евгений Витальевич",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/999343",
                contacts = "bruchas_ev@spbstu.ru",
                position = "Старший преподаватель",
                avatar = "",
                saved = false
            ),
            LocalEmployees(
                name = "Вуль Ольга Александровна",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/15086",
                contacts = "vul_oa@spbstu.ru",
                position = "Доцент",
                avatar = "",
                saved = false
            ),
            LocalEmployees(
                name = "Кокорин Михаил Станиславович",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/2953",
                contacts = "kokorin_ms@spbstu.ru",
                position = "Доцент",
                avatar = "",
                saved = false
            ),
            LocalEmployees(
                name = "Шавва Андрей Александрович",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/12706",
                contacts = "shavva_aa@spbstu.ru",
                position = "Старший преподаватель",
                saved = false,
                avatar = ""
            ),
            LocalEmployees(
                name = "Ненашев Валентин Сергеевич",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/15975",
                contacts = "nenashev_vs@spbstu.ru",
                position = "Ассистент",
                avatar = "",
                saved = false
            ),
            LocalEmployees(
                name = "Олехнович Янис Айгарсович",
                scheduleUrl = "https://ruz.spbstu.ru/teachers/12442",
                contacts = "olehnovich_yaa@spbstu.ru",
                position = "Инженер",
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
                buildingId = 0,
                category = "СЛУЖБА",
                title = "ffejwe",
                location = "fejfweo",
                saved = false
            ),
            LocalBuildingInfo(
                buildingId = 0,
                category = "СЛУЖБА",
                title = "ffejwe 111111",
                location = "fejfweo 111111111",
                saved = false
            )
        )
    }

}
