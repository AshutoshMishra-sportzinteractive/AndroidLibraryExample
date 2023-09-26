package com.sportzinteractive.baseprojectsetup.data.model.footballstandings


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("away_points_conceded")
    val awayPointsConceded: String?,
    @SerializedName("away_points_scored")
    val awayPointsScored: String?,
    @SerializedName("away_wins")
    val awayWins: String?,
    @SerializedName("carry_forward_points")
    val carryForwardPoints: String?,
    @SerializedName("draws")
    val draws: String?,
    @SerializedName("ga")
    val ga: String?,
    @SerializedName("gf")
    val gf: String?,
    @SerializedName("home_wins")
    val homeWins: String?,
    @SerializedName("is_qualified")
    val isQualified: Boolean?,
    @SerializedName("lost")
    val lost: String?,
    @SerializedName("match_result")
    val matchResult: MatchResult?,
    @SerializedName("noresult")
    val noresult: String?,
    @SerializedName("overall_points_per_match")
    val overallPointsPerMatch: Int?,
    @SerializedName("played")
    val played: String?,
    @SerializedName("points")
    val points: String?,
    @SerializedName("points_conceded")
    val pointsConceded: String?,
    @SerializedName("points_per_match")
    val pointsPerMatch: Int?,
    @SerializedName("points_scored")
    val pointsScored: String?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("position_status")
    val positionStatus: String?,
    @SerializedName("prev_position")
    val prevPosition: String?,
    @SerializedName("score_diff")
    val scoreDiff: String?,
    @SerializedName("team_display_name")
    val teamDisplayName: String?,
    @SerializedName("team_global_id")
    val teamGlobalId: Int?,
    @SerializedName("team_id")
    val teamId: Int?,
    @SerializedName("team_name")
    val teamName: String?,
    @SerializedName("team_short_name")
    val teamShortName: String?,
    @SerializedName("tied")
    val tied: String?,
    @SerializedName("total_points")
    val totalPoints: String?,
    @SerializedName("trump_matches_won")
    val trumpMatchesWon: String?,
    @SerializedName("wins")
    val wins: String?
)