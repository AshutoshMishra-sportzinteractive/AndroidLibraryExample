package com.sportzinteractive.baseprojectsetup.data.model.footballfixtures


import com.google.gson.annotations.SerializedName

data class Matches(
    @SerializedName("end_date")
    val endDate: String?,
    @SerializedName("event_format")
    val eventFormat: String?,
    @SerializedName("event_group")
    val eventGroup: String?,
    @SerializedName("event_islinkable")
    val eventIslinkable: String?,
    @SerializedName("event_name")
    val eventName: String?,
    @SerializedName("event_stage")
    val eventStage: String?,
    @SerializedName("event_state")
    val eventState: String?,
    val eventStateTemp: String?,
    @SerializedName("event_status")
    val eventStatus: String?,
    @SerializedName("event_status_id")
    val eventStatusId: String?,
    @SerializedName("event_sub_status")
    val eventSubStatus: String?,
    @SerializedName("game_id")
    val gameId: String?,
    @SerializedName("is_rescheduled")
    val isRescheduled: String?,
    @SerializedName("is_tbc")
    val isTbc: String?,
    @SerializedName("league_code")
    val leagueCode: String?,
    @SerializedName("participants")
    val participants: List<Participant?>?,
    @SerializedName("result_code")
    val resultCode: String?,
    @SerializedName("result_sub_code")
    val resultSubCode: String?,
    @SerializedName("series_id")
    val seriesId: String?,
    @SerializedName("series_name")
    val seriesName: String?,
    @SerializedName("sport")
    val sport: String?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("tour_id")
    val tourId: String?,
    @SerializedName("tour_name")
    val tourName: String?,
    @SerializedName("venue_id")
    val venueId: String?,
    @SerializedName("venue_name")
    val venueName: String?,
    @SerializedName("winning_margin")
    val winningMargin: String?,
    var teamLogo:String?
)