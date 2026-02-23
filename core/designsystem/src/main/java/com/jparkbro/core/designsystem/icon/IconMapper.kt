package com.jparkbro.core.designsystem.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun String.toImageVector(): ImageVector? = when (this) {
    "ic_add"                          -> AddIcon
    "ic_add_notes_fill"               -> AddNotesFillIcon
    "ic_add_notes_outline"            -> AddNotesOutlineIcon
    "ic_alert"                        -> AlertIcon
    "ic_analytics_fill"               -> AnalyticsFillIcon
    "ic_analytics_outline"            -> AnalyticsOutlineIcon
    "ic_apparel"                      -> ApparelIcon
    "ic_arrow_back"                   -> ArrowBackIcon
    "ic_bolt"                         -> BoltIcon
    "ic_cake"                         -> CakeIcon
    "ic_calendar_month_fill"          -> CalendarFillIcon
    "ic_calendar_month_outline"       -> CalendarOutlineIcon
    "ic_calendar_today"               -> CalendarTodayIcon
    "ic_credit_card"                  -> CreditCardIcon
    "ic_delete"                       -> DeleteIcon
    "ic_dentistry"                    -> DentistryIcon
    "ic_directions_bike"              -> DirectionsBikeIcon
    "ic_directions_car"               -> DirectionsCarIcon
    "ic_directions_subway"            -> DirectionsSubwayIcon
    "ic_edit"                         -> EditIcon
    "ic_electric_car"                 -> ElectricCarIcon
    "ic_ev_station"                   -> EvStationIcon
    "ic_exercise"                     -> ExerciseIcon
    "ic_featured_seasonal_and_gifts"  -> FeaturedSeasonalAndGiftsIcon
    "ic_fitness_center"               -> FitnessCenterIcon
    "ic_flight"                       -> FlightIcon
    "ic_fork_spoon"                   -> ForkSpoonIcon
    "ic_healing"                      -> HealingIcon
    "ic_home"                         -> HomeIcon
    "ic_keyboard_arrow_down"          -> KeyboardArrowDownIcon
    "ic_keyboard_arrow_up"            -> KeyboardArrowUpIcon
    "ic_local_bar"                    -> LocalBarIcon
    "ic_local_cafe"                   -> LocalCafeIcon
    "ic_local_convenience_store"      -> LocalConvenienceStoreIcon
    "ic_local_gas_station"            -> LocalGasStationIcon
    "ic_local_hospital"               -> LocalHospitalIcon
    "ic_local_parking"                -> LocalParkingIcon
    "ic_local_pizza"                  -> LocalPizzaIcon
    "ic_local_shipping"               -> LocalShippingIcon
    "ic_local_taxi"                   -> LocalTaxiIcon
    "ic_lunch_dining"                 -> LunchDiningIcon
    "ic_moped"                        -> MopedIcon
    "ic_more_vert"                    -> MoreVertIcon
    "ic_movie"                        -> MovieIcon
    "ic_music_note"                   -> MusicNoteIcon
    "ic_pet_supplies"                 -> PetSuppliesIcon
    "ic_pill"                         -> PillIcon
    "ic_receipt_long_fill"            -> ReceiptLongFillIcon
    "ic_receipt_long_outline"         -> ReceiptLongOutlineIcon
    "ic_restaurant"                   -> RestaurantIcon
    "ic_settings_fill"                -> SettingsFillIcon
    "ic_settings_outline"             -> SettingsOutlineIcon
    "ic_shopping_cart"                -> ShoppingCartIcon
    "ic_sports_esports"               -> SportsEsportsIcon
    "ic_storefront"                   -> StorefrontIcon
    "ic_theaters"                     -> TheatersIcon
    "ic_timer"                        -> TimerIcon
    "ic_train"                        -> TrainIcon
    "ic_travel"                       -> TravelIcon
    "ic_videogame_asset"              -> VideogameAssetIcon
    else                              -> null
}
